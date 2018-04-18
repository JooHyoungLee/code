package kr.co.chahoo.service;

import java.util.List;

import kr.co.chahoo.member.model.GradeDto;
import kr.co.chahoo.member.model.GroupDto;
import kr.co.chahoo.member.model.MemberDto;
import kr.co.chahoo.security.CustomUserDetails;

public interface MemberService {
	
	/**
	 * 이메일 중복검사
	 * @param email	이메일
	 * @return
	 */
	public int checkEmail(String email);
	
	/**
	 * 사용자 고유아이디 생성
	 * @return
	 */
	public String makeMemberID();
	
	/**
	 * 로그인 확인
	 * @param customUserDetails
	 * @return
	 */
	public int login(CustomUserDetails customUserDetails);
	
	/**
	 * 부서 목록
	 * @return
	 */
	public List<GroupDto> getGroupList();
	
	/**
	 * 직급 목록
	 * @param group_id	부서 아이디
	 * @return
	 */
	public List<GradeDto> getGradeList(String group_id);
	
	/**
	 * 사용자 목록
	 * @return
	 */
	public List<MemberDto> getMemberList();
	
	/**
	 * 사용자 정보
	 * @param member_id	사용자 아이디
	 * @return
	 */
	public MemberDto getMemberInfo(String member_id);
	
	/**
	 * 사용자 정보
	 * @param email	사용자 이메일
	 * @return
	 */
	public MemberDto getMemberInfoEmail(String email);
	
	/**
	 * 사용자 생성
	 * @param memberDto	사용자 정보
	 * @return
	 */
	public int createMember(MemberDto memberDto);
	
	/**
	 * 사용자 정보 수정
	 * @param memberDto	사용자 정보
	 * @return
	 */
	public int updateMemberInfo(MemberDto memberDto);
	
	/**
	 * 비밀번호 변경
	 * @param memberDto	비밀번호
	 * @return
	 */
	public int updatePassword(MemberDto memberDto);
	
	/**
	 * 사용자 상태변경
	 * @param memberDto	사용자 정보
	 * @return
	 */
	public int updateMemberStatus(MemberDto memberDto);
	
	/**
	 * 사용자 삭제
	 * @param member_id	사용자 아이디
	 * @return
	 */
	public int deleteMember(String member_id);
}
