package kr.co.chahoo.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.chahoo.common.FormatUtils;
import kr.co.chahoo.common.model.ConditionSet;
import kr.co.chahoo.event.model.EventDto;
import kr.co.chahoo.member.model.MemberDto;
import kr.co.chahoo.service.EventService;
import kr.co.chahoo.service.MemberService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * http://fullcalendar.io/docs/ 참조
 */
@Controller
@RequestMapping("/event")
public class EventController {
	
	private static final Logger logger = LoggerFactory.getLogger(EventController.class);
	@Autowired private EventService eventService;
	@Autowired private MemberService memberService;
	
	/**
	 * 이벤트 메인
	 * @return
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletRequest request, HttpServletResponse response, Principal principal) {
		logger.info("Evnet - Main");
		
		MemberDto memberDto = memberService.getMemberInfoEmail(principal.getName());
		request.setAttribute("member_id", memberDto.getMember_id());
		
		return "event/main";
	}
	
	/**
	 * 이벤트 삭제
	 * @param event_id	이벤트 아이디
	 * @return
	 */
	@RequestMapping(value = "/{event_id}/delete", method = RequestMethod.GET)
	public @ResponseBody String delete(@PathVariable int event_id, HttpServletRequest request, HttpServletResponse response, Principal principal) {
		logger.info("Evnet - Delete");
		
		EventDto eventDto = eventService.getEvent(event_id);
		
		//등록자가 아닌경우 실패 처리
		MemberDto memberDto = memberService.getMemberInfoEmail(principal.getName());
		if(!eventDto.getMember_id().equals(memberDto.getMember_id()))
		{
			return "failed";
		}
		
		int ret = eventService.deleteEvent(event_id);
		if(ret == 1)
		{
			return "success";
		}
		else
		{
			return "failed";
		}
	}
	
	/**
	 * 이벤트 목록
	 * @param date	이벤트 기준 날짜
	 * @return
	 */
	@RequestMapping(value = "/{date}/list", method = RequestMethod.GET)
	public @ResponseBody String range(@PathVariable String date, HttpServletRequest request, HttpServletResponse response, Principal principal) {
		logger.info("Evnet - Range {}",date);
		
		ConditionSet conditionSet = new ConditionSet();
		conditionSet.setDateFrom(FormatUtils.string_YYYY_MM_DDToTimestamp(date));
		conditionSet.setDateTo(FormatUtils.string_YYYY_MM_DDToTimestampAddOneMonth(date));
		
		List<EventDto> list = eventService.getEventRagneList(conditionSet);

		String json = eventService.eventJsonList(list);
		
		response.setHeader("Content-Type:", "application/json;charset=UTF-8"); //text/html;charset=UTF-8"
		
		return json;
	}
	
	/**
	 * 이벤트 등록
	 * @param start		시작일시
	 * @param end		종료일시
	 * @param all_day	하루종일 체크
	 * @param title		이벤트 제목
	 * @param desc		이벤트 설명
	 * @return
	 */
	@RequestMapping(value = "/process", method = RequestMethod.POST)
	@ResponseBody
	public int eventProcess(
			@RequestParam String start, 
			@RequestParam String end,
			@RequestParam int all_day, 
			@RequestParam String title,
			@RequestParam String desc,
			HttpServletRequest request, HttpServletResponse response, Principal principal) {
		logger.info("Event - eventProcess");
		
		MemberDto memberDto = memberService.getMemberInfoEmail(principal.getName());
		
		EventDto eventDto = new EventDto();
		eventDto.setAll_day(all_day);
		eventDto.setDescription(desc);
		eventDto.setEnd(FormatUtils.string_YYYY_MM_DD_HHMMToTimestamp(end));
		eventDto.setStart(FormatUtils.string_YYYY_MM_DD_HHMMToTimestamp(start));
		eventDto.setTitle(title);
		eventDto.setMember_id(memberDto.getMember_id());
		
		int ret = eventService.createEvent(eventDto);
		if(ret == 1)
		{
			int lastEventId = eventService.getLastEventID();
			return lastEventId;
		}
		else
		{
			//실패
			return 0;
		}
	}
}
