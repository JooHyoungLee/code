package kr.co.chahoo.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.chahoo.board.model.ContentDto;
import kr.co.chahoo.common.FormatUtils;
import kr.co.chahoo.common.model.ConditionSet;
import kr.co.chahoo.event.model.EventDto;
import kr.co.chahoo.member.model.MemberDto;
import kr.co.chahoo.service.BoardService;
import kr.co.chahoo.service.EventService;
import kr.co.chahoo.service.MemberService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/main")
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired private MemberService memberService;
	@Autowired private BoardService boardService;
	@Autowired private EventService eventService;
	
	/**
	 * 메인 페이지
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(HttpServletRequest request, HttpServletResponse response, Principal principal) {
		logger.info("Main -");
		
		//최근 공지사항
		ConditionSet noticeSet = new ConditionSet();
		noticeSet.setKey("notice");
		noticeSet.setLimit(5);
		noticeSet.setOffset(0);
		
		List<ContentDto> noticeList = boardService.getContentList(noticeSet);
		int noticeCount = boardService.getAllCount(noticeSet);
		
		//최근 자료실
		ConditionSet storeSet = new ConditionSet();
		storeSet.setKey("store");
		storeSet.setLimit(5);
		storeSet.setOffset(0);
		
		List<ContentDto> storeList = boardService.getContentList(storeSet);
		int storeCount = boardService.getAllCount(storeSet);
		
		//금주 이벤트 획득
		ConditionSet eventSet = new ConditionSet();
		String today = FormatUtils.todayToString_YYYY_MM_DD();
		eventSet.setDateFrom(FormatUtils.string_YYYY_MM_DDToTimestamp(today));
		eventSet.setDateTo(FormatUtils.string_YYYY_MM_DDToTimestampAddOneMonth(today));
		
		List<EventDto> eventList = eventService.getEventList(eventSet);
		
		//페이지 맵핑
		request.setAttribute("noticeCount", noticeCount);
		request.setAttribute("storeCount", storeCount);
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("storeList", storeList);
		request.setAttribute("eventList", eventList);
		
		//사용자 정보 맵핑
		MemberDto memberDto = memberService.getMemberInfoEmail(principal.getName());
		request.setAttribute("member_id", memberDto.getMember_id());
		
		return "main";
	}
	
}
