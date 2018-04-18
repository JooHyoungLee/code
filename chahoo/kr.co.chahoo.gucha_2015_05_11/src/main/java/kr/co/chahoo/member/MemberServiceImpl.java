package kr.co.chahoo.member;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.chahoo.member.model.GradeDto;
import kr.co.chahoo.member.model.GroupDto;
import kr.co.chahoo.member.model.MemberDto;
import kr.co.chahoo.security.CustomUserDetails;
import kr.co.chahoo.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired MemberDao memberDao;
	
	@Override
	public int checkEmail(String email) {
		int ret = memberDao.checkEmail(email);
		return ret;
	}

	@Override
	public List<GroupDto> getGroupList() {
		List<GroupDto> list = memberDao.getGroupList();
		return list;
	}

	@Override
	public List<GradeDto> getGradeList(String group_id) {
		List<GradeDto> list = memberDao.getGradeList(group_id);
		return list;
	}

	@Override
	public List<MemberDto> getMemberList() {
		List<MemberDto> list = memberDao.getMemberList();
		return list;
	}

	@Override
	public MemberDto getMemberInfo(String member_id) {
		MemberDto memberDto = memberDao.getMemberInfo(member_id);
		return memberDto;
	}

	@Override
	public int createMember(MemberDto memberDto) {
		int ret = 0;
		try {
			ret = memberDao.createMember(memberDto);
		} catch (Exception e) {
			e.printStackTrace();
			ret = 0;
		}
		return ret;
	}

	@Override
	public int updateMemberInfo(MemberDto memberDto) {
		int ret = 0;
		try {
			ret = memberDao.updateMemberInfo(memberDto);
		} catch (Exception e) {
			e.printStackTrace();
			ret = 0;
		}
		return ret;
	}

	@Override
	public int updatePassword(MemberDto memberDto) {
		int ret = 0;
		try {
			ret = memberDao.updatePassword(memberDto);
		} catch (Exception e) {
			e.printStackTrace();
			ret = 0;
		}
		return ret;
	}

	@Override
	public int updateMemberStatus(MemberDto memberDto) {
		int ret = 0;
		try {
			ret = memberDao.updateMemberStatus(memberDto);
		} catch (Exception e) {
			e.printStackTrace();
			ret = 0;
		}
		return ret;
	}

	@Override
	public int deleteMember(String member_id) {
		int ret = 0;
		try {
			ret = memberDao.deleteMember(member_id);
		} catch (Exception e) {
			e.printStackTrace();
			ret = 0;
		}
		return ret;
	}

	@Override
	public String makeMemberID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	@Override
	public int login(CustomUserDetails customUserDetails) {
		int ret = memberDao.login(customUserDetails);
		return ret;
	}

	@Override
	public MemberDto getMemberInfoEmail(String email) {
		MemberDto memberDto = memberDao.getMemberInfoEmail(email);
		return memberDto;
	}

}
