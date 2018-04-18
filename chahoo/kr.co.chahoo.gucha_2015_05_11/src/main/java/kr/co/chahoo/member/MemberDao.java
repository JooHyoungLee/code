package kr.co.chahoo.member;

import java.util.List;

import kr.co.chahoo.member.model.GradeDto;
import kr.co.chahoo.member.model.GroupDto;
import kr.co.chahoo.member.model.MemberDto;
import kr.co.chahoo.security.CustomUserDetails;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class MemberDao extends SqlSessionDaoSupport{
	
	public int checkEmail(String email) {
		int ret = getSqlSession().selectOne("member.checkEmail", email);
		return ret;
	}

	public List<GroupDto> getGroupList() {
		List<GroupDto> list = getSqlSession().selectList("member.getGroupList");
		return list;
	}

	public List<GradeDto> getGradeList(String group_id) {
		List<GradeDto> list = getSqlSession().selectList("member.getGradeList", group_id);
		return list;
	}

	public List<MemberDto> getMemberList() {
		List<MemberDto> list = getSqlSession().selectList("member.getMemberList");
		return list;
	}

	public MemberDto getMemberInfo(String member_id) {
		MemberDto memberDto = getSqlSession().selectOne("member.getMemberInfo", member_id);
		return memberDto;
	}

	public int createMember(MemberDto memberDto) {
		return getSqlSession().insert("member.createMember", memberDto);
	}

	public int updateMemberInfo(MemberDto memberDto) {
		return getSqlSession().update("member.updateMemberInfo", memberDto);
	}

	public int updatePassword(MemberDto memberDto) {
		return getSqlSession().update("member.updatePassword", memberDto);
	}

	public int updateMemberStatus(MemberDto memberDto) {
		return getSqlSession().update("member.updateMemberStatus", memberDto);
	}

	public int deleteMember(String member_id) {
		return getSqlSession().update("member.deleteMember", member_id);
	}
	public int login(CustomUserDetails customUserDetails) {
		int ret = 0;
		try {
			ret = getSqlSession().selectOne("member.login", customUserDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	public MemberDto getMemberInfoEmail(String email) {
		MemberDto memberDto = getSqlSession().selectOne("member.getMemberInfoEmail", email);
		return memberDto;
	}
}
