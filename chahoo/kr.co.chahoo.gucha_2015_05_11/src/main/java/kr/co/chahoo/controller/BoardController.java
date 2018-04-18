package kr.co.chahoo.controller;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.chahoo.board.BoardServiceImpl;
import kr.co.chahoo.board.model.BoardDto;
import kr.co.chahoo.board.model.ContentDto;
import kr.co.chahoo.board.model.ContentTypeDto;
import kr.co.chahoo.board.model.ContentYearDto;
import kr.co.chahoo.common.FormatUtils;
import kr.co.chahoo.common.model.ConditionSet;
import kr.co.chahoo.file.FileServiceImpl;
import kr.co.chahoo.file.model.FileDto;
import kr.co.chahoo.member.model.MemberDto;
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

@Controller
@RequestMapping("/board")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	@Autowired private BoardServiceImpl boardService;
	@Autowired private FileServiceImpl fileService;
	@Autowired private MemberService memberService;
	
	/**
	 * 글쓰기 페이지
	 * @param board_id	게시판 아이디
	 * @param ctype		게시물 유형
	 * @return
	 */
	@RequestMapping(value = "/{board_id}/write", method = RequestMethod.GET)
	public String write(@PathVariable String board_id, @RequestParam(required=false, defaultValue="") String ctype, HttpServletRequest request, HttpServletResponse response, Principal principal) {
		logger.info("Board - Write {}" ,board_id);
		
		BoardDto boardDto = boardService.getBoard(board_id);
		request.setAttribute("boardDto", boardDto);	//게시판 정보
		
		request.setAttribute("board_id", board_id);	//게시판 아이디
		request.setAttribute("ctype", ctype);	//자료실 판별

		List<String> year_list = boardService.getContentYear();
		request.setAttribute("year_list", year_list);	//프로젝트 연도 목록
		request.setAttribute("toYear", FormatUtils.todayToString_YYYY());	//현재 연도
		
		List<ContentTypeDto> list =  boardService.getBoardContentTypeList(board_id);
		request.setAttribute("content_type_list", list);	//자료실 분류 목록
		
		return "board/write";
	}
	
	/**
	 * 글쓰기
	 * @param board_id			게시판 유형
	 * @param title				제목
	 * @param content			내용
	 * @param fileList			업로드된 파일 아이디 목록
	 * @param content_type_id	게시물 유형
	 * @param content_year		게시물 연도
	 * @return
	 */
	@RequestMapping(value = "/{board_id}/writeProcess", method = RequestMethod.POST)
	public String writeProcess(@PathVariable String board_id, 
			@RequestParam String title, @RequestParam String content,
			@RequestParam String fileList, 
			@RequestParam String content_type_id,
			@RequestParam String content_year,
			HttpServletRequest request, HttpServletResponse response, Principal principal) {
		logger.info("Board - WriteProcess {}" ,board_id);
		
		//자료실 이외의 게시물의 타입 설정
		if(content_type_id == null || content_type_id.equals(""))
		{
			content_type_id = "normal";
		}
		request.setAttribute("board_id", board_id);	//게시판 아이디
		
		//게시물 저장
		MemberDto memberDto = memberService.getMemberInfoEmail(principal.getName());
		
		ContentDto contentDto = new ContentDto();
		contentDto.setBoard_id(board_id);
		contentDto.setContent_text(content);
		contentDto.setMember_id(memberDto.getMember_id());
		contentDto.setTitle(title);
		contentDto.setContent_year(content_year);
		contentDto.setContent_type_id(content_type_id);
		
		boardService.createContent(contentDto);
		
		int content_id = boardService.getLastContentID();
		//첨부파일 여부 검사 및 게시글과 첨부파일 맵핑
		ArrayList<String> files = fileService.uploadFileCount(fileList);
		for(int i=0; i<files.size(); i++)
		{
			fileService.updateContentFile(content_id, board_id, files.get(i).toString());
			contentDto.setIs_file(1);
		}
		if(contentDto.getIs_file() == 1)
		{
			contentDto.setContent_id(content_id);
			boardService.updateContent(contentDto);
		}
		
		return "redirect:/board/"+board_id+"/list";
	}
	
	/**
	 * 
	 * @param page		페이지
	 * @param limit		목록 갯수
	 * @param stype		제목/내용 0/1
	 * @param ctype		게시물 유형
	 * @param year		게시물 연도
	 * @param keyword	검색어
	 * @param board_id	게시판 아이디
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value="{board_id}/list", method = RequestMethod.GET)
	public String list(
			@RequestParam(required=false, defaultValue="1") int page,
			@RequestParam(required=false, defaultValue="15") int limit,
			@RequestParam(required=false, defaultValue="0") int stype,
			@RequestParam(required=false, defaultValue="") String ctype,
			@RequestParam(required=false, defaultValue="") String year,
			@RequestParam(required=false) String keyword,
			@PathVariable String board_id,
			HttpServletRequest request, HttpServletResponse response, Principal principal) throws UnsupportedEncodingException {
		
		logger.info("Board - List {}",board_id);
		request.setCharacterEncoding("UTF-8");

		BoardDto boardDto = boardService.getBoard(board_id);
		request.setAttribute("boardDto", boardDto);	//게시판 정보
		
		List<ContentTypeDto> contentTypeList = boardService.getBoardContentTypeList(board_id);
		request.setAttribute("content_type_list", contentTypeList); //자료실 분류 목록
		
		List<ContentDto> list = new LinkedList<ContentDto>();
		
		//페이징 컨트롤 변수 초기화
		int pageGroupSize = 10;		
		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;
		int offset = endRow - limit;
		int count = 0;
		ConditionSet conditionSet = new ConditionSet();
		conditionSet.setOffset(offset);
		conditionSet.setLimit(limit);
		conditionSet.setKey(board_id);
		
		//프로젝트 연도 판별
		if(!year.equals(""))
		{
			conditionSet.setYear(year);
		}
		else
		{
			conditionSet.setYear("");
		}
		
		//검색어 존재여부에 따른 처리
		if(keyword == null) {
			if(ctype != null && !ctype.equals("") && !ctype.equals("all"))	//게시물 타입이 존재하는 경우
			{
				conditionSet.setCtype(ctype);
				list.addAll(boardService.getContentSearchList(conditionSet));
				count = boardService.getContentSearchCount(conditionSet);
			}
			else	//게시물 타입이 존재하지 않는 경우
			{
				list.addAll(boardService.getContentList(conditionSet));
				count = boardService.getAllCount(conditionSet);
			}
		}
		else {
			//검색어가 존재하는 경우
			if(ctype != null && !ctype.equals("") && !ctype.equals("all"))	//게시물 타입이 존재하는 경우
			{
				conditionSet.setCtype(ctype);
				conditionSet.setIntParam(stype);
				conditionSet.setStrParam(keyword);
				//System.out.println(conditionSet.toString());
				list.addAll(boardService.getContentSearchList(conditionSet));
				count = boardService.getContentSearchCount(conditionSet);
				request.setAttribute("stype", stype);
				request.setAttribute("keyword", keyword);
			}
			else	//게시물 타입이 존재하지 않는 경우
			{
				conditionSet.setIntParam(stype);
				conditionSet.setStrParam(keyword);
				//System.out.println(conditionSet.toString());
				list.addAll(boardService.getContentSearchList(conditionSet));
				count = boardService.getContentSearchCount(conditionSet);
				request.setAttribute("stype", stype);
				request.setAttribute("keyword", keyword);
			}
		}
		
		//프로젝트인 경우 연도목록을 설정
		if(ctype.equals("project"))
		{
			List<ContentYearDto> yearList = boardService.getContentYearCountList();
			request.setAttribute("yearList", yearList);
		}
		else
		{
			request.setAttribute("yearList", "");
		}
		
		//페이징 컨트롤 변수 계산
		int number = count - (page - 1) * limit;
		int pageGroupCount = count / (limit * pageGroupSize) + (count % (limit * pageGroupSize) == 0 ? 0 : 1);
		int numPageGroup = (int) Math.ceil((double)page/pageGroupSize);
		
		/*** Pagination Configuration ***********************************/
		request.setAttribute("currentPage", page);
		request.setAttribute("startRow", startRow);
		request.setAttribute("endRow", endRow);
		request.setAttribute("count", count);
		request.setAttribute("pageSize", limit); 
		request.setAttribute("number", number);
		request.setAttribute("pageGroupSize", pageGroupSize);
		request.setAttribute("numPageGroup", numPageGroup);
		request.setAttribute("pageGroupCount", pageGroupCount);
		/*****************************************************************/
		
		request.setAttribute("ctype", ctype);	//자료실 분류 값 
		request.setAttribute("list", list);	//게시물 목록
		
		return "board/list";
	}
	
	/**
	 * 글 상세보기
	 * @param board_id		게시판 유형
	 * @param content_id	게시물 아이디
	 * @return
	 */
	@RequestMapping(value = "/{board_id}/{content_id}/", method = RequestMethod.GET)
	public String detail(@PathVariable String board_id, @PathVariable int content_id, HttpServletRequest request, HttpServletResponse response, Principal principal) {
		logger.info("Board - detail {}" ,board_id);
		
		BoardDto boardDto = boardService.getBoard(board_id);
		request.setAttribute("boardDto", boardDto);	//게시판 정보
		request.setAttribute("board_id", board_id);	//게시판 아이디
		
		MemberDto memberDto = memberService.getMemberInfoEmail(principal.getName());
		request.setAttribute("member_id", memberDto.getMember_id());	//사용자 아이디
		
		ContentDto contentDto = boardService.getContent(content_id);
		request.setAttribute("contentDto", contentDto);	//게시물
		
		if(contentDto.getIs_file() > 0)
		{
			List<FileDto> fileDto = fileService.getContentFileList(content_id);
			request.setAttribute("fileDto", fileDto);	//첨부파일
		}
		
		return "board/detail";
	}
	
	/**
	 * 게시글 수정 페이지
	 * @param board_id		게시판 아이디
	 * @param content_id	게시물 아이디
	 * @return
	 */
	@RequestMapping(value = "/{board_id}/{content_id}/modify/", method = RequestMethod.GET)
	public String modify(@PathVariable String board_id, @PathVariable int content_id, HttpServletRequest request, HttpServletResponse response, Principal principal) {
		logger.info("Board - modify {}" ,board_id);
		
		ContentDto contentDto = boardService.getContent(content_id);
		request.setAttribute("contentDto", contentDto);
		
		BoardDto boardDto = boardService.getBoard(board_id);
		request.setAttribute("boardDto", boardDto);
		
		if(contentDto.getIs_file() > 0)
		{
			List<FileDto> fileDto = fileService.getContentFileList(content_id);
			request.setAttribute("fileDto", fileDto);
		}
		
		List<String> year_list = boardService.getContentYear();
		request.setAttribute("year_list", year_list);
		request.setAttribute("toYear", FormatUtils.todayToString_YYYY());
		
		List<ContentTypeDto> list =  boardService.getBoardContentTypeList(board_id);
		request.setAttribute("content_type_list_dto", list);
		request.setAttribute("board_id", board_id);
		
		return "board/modify";
	}
	
	/**
	 * 게시물 수정
	 * @param board_id			게시판 아이디
	 * @param title				게시물 제목
	 * @param content			게시물 내용
	 * @param fileList			첨부파일 아이디 목록
	 * @param content_type_id	게시물 유형
	 * @param content_year		게시물 연도
	 * @param content_id		게시물 아이디
	 * @param is_file			파일첨부 여부
	 * @return
	 */
	@RequestMapping(value = "/{board_id}/{content_id}/modifyProcess", method = RequestMethod.POST)
	public String modifyProcess(@PathVariable String board_id, 
			@RequestParam String title, @RequestParam String content,
			@RequestParam String fileList, 
			@RequestParam String content_type_id,
			@RequestParam String content_year,
			@PathVariable int content_id,
			@RequestParam int is_file,
			HttpServletRequest request, HttpServletResponse response, Principal principal) {
		logger.info("Board - Modify Process {}" ,board_id);
		
		//게시물 유형이 없는 경우 설정
		if(content_type_id == null || content_type_id.equals(""))
		{
			content_type_id = "normal";
		}
		
		//사용자 정보 획득
		MemberDto memberDto = memberService.getMemberInfoEmail(principal.getName());
		
		//변경 내용 설정
		ContentDto contentDto = new ContentDto();
		contentDto.setBoard_id(board_id);
		contentDto.setContent_text(content);
		contentDto.setMember_id(memberDto.getMember_id());
		contentDto.setTitle(title);
		contentDto.setContent_year(content_year);
		contentDto.setContent_type_id(content_type_id);
		contentDto.setContent_id(content_id);
		
		//파일첨부 변동내역 검사 및 업데이트
		ArrayList<String> files = fileService.uploadFileCount(fileList);
		for(int i=0; i<files.size(); i++)
		{
			fileService.updateContentFile(content_id, board_id, files.get(i).toString());
			contentDto.setIs_file(1);
		}
		
		List<FileDto> alfileList = fileService.getContentFileList(content_id);
		if(alfileList.size()>0)
		{
			contentDto.setIs_file(1);
		}
		else
		{
			contentDto.setIs_file(0);
		}
		
		//업데이트
		boardService.updateContent(contentDto);
		request.setAttribute("board_id", board_id);
		
		//타겟 페이지 설정
		if(board_id.equals("store"))
		{
			String parm = "?ctype="+content_type_id;
			
			return "redirect:/board/"+board_id+"/list"+parm;
		}
		else
		{
			return "redirect:/board/"+board_id+"/list";
		}
	}
	
	/**
	 * 조회수 증가
	 * @param board_id		게시판 아이디
	 * @param content_id	게시물 아이디
	 * @return
	 */
	@RequestMapping(value = "/{board_id}/{content_id}/count", method = RequestMethod.GET)
	public @ResponseBody String count(@PathVariable String board_id, @PathVariable int content_id, HttpServletRequest request, HttpServletResponse response, Principal principal) {
		logger.info("Board - count {}" ,board_id);
		
		boardService.updateCounts(content_id);
		
		return "success";
	}
	
	/**
	 * 게시물 삭제
	 * @param board_id		게시판 아이디
	 * @param content_id	게시물 아이디
	 * @return
	 */
	@RequestMapping(value = "{board_id}/{content_id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable String board_id, @PathVariable int content_id, HttpServletRequest request, HttpServletResponse response, Principal principal) {
		logger.info("Board - delete {}" ,content_id);
		
		boardService.deleteContent(content_id);
		
		return "redirect:/board/"+board_id+"/list";
	}
}
