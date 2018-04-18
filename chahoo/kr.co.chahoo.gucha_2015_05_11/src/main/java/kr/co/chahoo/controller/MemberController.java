package kr.co.chahoo.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.chahoo.member.model.GradeDto;
import kr.co.chahoo.member.model.GroupDto;
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
@RequestMapping("/member")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired private MemberService memberService;
	
	/**
	 * 회원가입 페이지
	 * @return
	 */
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(HttpServletRequest request, HttpServletResponse response, Principal principal) {
		logger.info("Member - Join");
		
		List<GroupDto> group_list = memberService.getGroupList();
		request.setAttribute("group_list", group_list);
		
		return "member/join";
	}
	
	/**
	 * 회원가입 프로세스
	 * @param name			회원 이름
	 * @param password		비밀번호
	 * @param email			이메일
	 * @param group_id		부서 아이디
	 * @param grade_id		직급 아이디
	 * @param cell_phone	휴대전화
	 * @param tele_phone	내선번호
	 * @return
	 */
	@RequestMapping(value = "/joinProcess", method = RequestMethod.POST)
	public String joinProcess(
			@RequestParam String name, 
			@RequestParam String password,
			@RequestParam String email, 
			@RequestParam String group_id,
			@RequestParam String grade_id,
			@RequestParam String cell_phone,
			@RequestParam String tele_phone,
			HttpServletRequest request, HttpServletResponse response, Principal principal) {
		logger.info("Member - JoinProcess");
		
		MemberDto memberDto = new MemberDto();
		memberDto.setCell_phone(cell_phone);
		memberDto.setEmail(email);
		memberDto.setGrade_id(grade_id);
		memberDto.setGroup_id(group_id);
		memberDto.setIs_status(1);
		memberDto.setMember_id(memberService.makeMemberID());
		memberDto.setName(name);
		memberDto.setPassword(password);
		memberDto.setTele_phone(tele_phone);
		
		int ret = memberService.createMember(memberDto);
		if(ret == 1)	//성공
		{
			return "redirect:/";
		}
		else	//실패
		{
			return "redirect:/";
		}
	}
	
	/**
	 * 직급 목록
	 * @param group_id	부서 아이디
	 * @return
	 */
	@RequestMapping(value = "/{group_id}/grade/list", method = RequestMethod.GET)
	public @ResponseBody List<GradeDto> grade_list(@PathVariable String group_id, HttpServletRequest request, HttpServletResponse response, Principal principal) {
		logger.info("Member - Grade List");
		
		List<GradeDto> grade_list = memberService.getGradeList(group_id);
		
		return grade_list;
	}
	
	/**
	 * 이메일 중복검사
	 * @param email	이메일
	 * @return
	 */
	@RequestMapping(value = "/{email}/check", method = RequestMethod.GET)
	public @ResponseBody int email_check(@PathVariable String email, HttpServletRequest request, HttpServletResponse response, Principal principal) {
		logger.info("Member - Email Check");
		
		int ret = memberService.checkEmail(email);
		
		return ret;
	}
	
	/**
	 * 회원 삭제
	 * @param member_id	회원 고유 아이디
	 * @return
	 */
	@RequestMapping(value = "/{member_id}/delete", method = RequestMethod.GET)
	public @ResponseBody String deleteMember(@PathVariable String member_id, HttpServletRequest request, HttpServletResponse response, Principal principal) {
		logger.info("Member - Delete Member");
		
		int ret = memberService.deleteMember(member_id);
		
		if(ret == 1)
		{
			return "success";
		}
		else
		{
			return "fail";
		}
	}
	
	/**
	 * 회원 상세 정보
	 * @return
	 */
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(HttpServletRequest request, HttpServletResponse response, Principal principal) {
		logger.info("Member - Profile");
		
		MemberDto memberDto = memberService.getMemberInfoEmail(principal.getName()); //세션에서 이메일 획득
		
		List<GroupDto> group_list = memberService.getGroupList();
		request.setAttribute("group_list", group_list);
		request.setAttribute("memberDto", memberDto);
		
		return "member/profile";
	}
	
	/**
	 * 회원정보 수정 프로세스 - 각각의 항목이 있는 경우에만 데이터 변경
	 * @param new_password	새로운 비밀번호
	 * @param group_id		부서 아이디
	 * @param grade_id		직급아이디
	 * @param cell_phone	휴대전화
	 * @param tele_phone	내선번호
	 * @return
	 */
	@RequestMapping(value = "/profileProcess", method = RequestMethod.POST)
	public String profileProcess(
			@RequestParam String new_password,
			@RequestParam String group_id,
			@RequestParam String grade_id,
			@RequestParam String cell_phone,
			@RequestParam String tele_phone,
			HttpServletRequest request, HttpServletResponse response, Principal principal) {
		logger.info("Member - profileProcess");
		
		MemberDto memberDto = memberService.getMemberInfoEmail(principal.getName());
		if(!new_password.equals(""))
		{
			memberDto.setPassword(new_password);
			memberService.updatePassword(memberDto);
		}
		
		if(!cell_phone.equals(""))
		{
			memberDto.setCell_phone(cell_phone);
		}
		
		if(!tele_phone.equals(""))
		{
			memberDto.setTele_phone(tele_phone);
		}
		else
		{
			memberDto.setTele_phone("");
		}
		
		memberDto.setGrade_id(grade_id);
		memberDto.setGroup_id(group_id);
		memberDto.setIs_status(1);
		
		int ret = memberService.updateMemberInfo(memberDto);
		if(ret == 1)
		{
			//성공
			return "redirect:/main/";
		}
		else
		{
			//실패
			return "redirect:/main/";
		}
	}
}
