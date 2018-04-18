package kr.co.chahoo.controller;

import javax.servlet.http.HttpSession;

import kr.co.chahoo.board.BoardServiceImpl;
import kr.co.chahoo.member.model.MemberDto;
import kr.co.chahoo.security.CustomUserDetails;
import kr.co.chahoo.service.MemberService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired MemberService memberService;
	@Autowired private BoardServiceImpl boardService;
	
	/**
	 * 로그인 페이지
	 * @param session
	 * @return
	 */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(HttpSession session) {
        logger.info("Welcome login! {}", session.getId());
        return "login";
    }
    
    /**
     * 로그아웃 페이지
     * @param session
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        logger.info("Welcome logout! {}", session.getId());
        session.invalidate(); //세션 초기화
        return "login";
    }
    
    /**
     * 로그인 체크
     * @param session
     * @return
     */
    @RequestMapping(value = "login_check", method = RequestMethod.GET)
    public String login_success(HttpSession session) {
        CustomUserDetails userDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getDetails();
        CustomUserDetails loginDto = new CustomUserDetails(userDetails.getUsername(), userDetails.getPassword());
        int ret = 0;
        try {
			ret = memberService.login(loginDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
        if(ret == 1)	//로그인 성공시
        {
        	MemberDto memberDto = memberService.getMemberInfoEmail(loginDto.getUsername());
        	logger.info("Welcome login_success! {}, {}", session.getId(), userDetails.getUsername() );
            session.setAttribute("memberName", memberDto.getName());
            session.setAttribute("memberId", memberDto.getMember_id());
            return "redirect:/main/";
        }
        else	//로그인 실패
        {
        	session.invalidate();
        	return "redirect:/login?parm=1";
        }
        
    }
    
    /**
     * 중복로그인 처리
     */
    @RequestMapping(value = "login_duplicate", method = RequestMethod.GET)
    public void login_duplicate() {     
        logger.info("Welcome login_duplicate!");
    }

}
