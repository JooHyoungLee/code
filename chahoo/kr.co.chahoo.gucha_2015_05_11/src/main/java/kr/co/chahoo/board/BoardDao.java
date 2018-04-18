package kr.co.chahoo.board;

import java.util.List;

import kr.co.chahoo.board.model.BoardDto;
import kr.co.chahoo.board.model.ContentDto;
import kr.co.chahoo.board.model.ContentTypeDto;
import kr.co.chahoo.board.model.ContentYearDto;
import kr.co.chahoo.common.model.ConditionSet;

import org.mybatis.spring.support.SqlSessionDaoSupport;


public class BoardDao extends SqlSessionDaoSupport{
	
	
	public List<ContentDto> getContentList(ConditionSet conditionSet) {
		List<ContentDto> list = getSqlSession().selectList("board.getContentList", conditionSet);
		return list;
	}

	public ContentDto getContent(int content_id) {
		ContentDto contentDto = getSqlSession().selectOne("board.getContent", content_id);
		return contentDto;
	}

	public int createContent(ContentDto boardContentDto) {
		int ret = 0;
		try {
			getSqlSession().insert("board.createContent", boardContentDto);
			ret = 1;
		} catch (Exception e) {
			e.printStackTrace();
			ret = 0;
		}
		return ret;
	}

	public int updateContent(ContentDto boardContentDto) {
		int ret = 0;
		try {
			getSqlSession().update("board.updateContent", boardContentDto);
			ret = 1;
		} catch (Exception e) {
			e.printStackTrace();
			ret = 0;
		}
		return ret;
	}

	public int deleteContent(int contentId) {
		int ret = 0;
		try {
			getSqlSession().update("board.deleteContent", contentId);
			ret = 1;
		} catch (Exception e) {
			e.printStackTrace();
			ret = 0;
		}
		return ret;
	}

	public List<BoardDto> getBoardList() {
		List<BoardDto> list = getSqlSession().selectList("board.getBoardList");
		return list;
	}

	public List<ContentTypeDto> getBoardContentTypeList(String keyword) {
		List<ContentTypeDto> list = getSqlSession().selectList("board.getBoardContentTypeList",keyword);
		return list;
	}
	
	public List<ContentDto> getContentSearchList(ConditionSet conditionSet) {
		List<ContentDto> list = getSqlSession().selectList("board.getContentSearchList", conditionSet);
		return list;
	}

	public int getContentSearchCount(ConditionSet conditionSet) {
		int count = getSqlSession().selectOne("board.getContentSearchCount",conditionSet);
		return count;
	}
	
	public int getAllCount(ConditionSet conditionSet) {
		int count = getSqlSession().selectOne("board.getAllCount", conditionSet);
		return count;
	}
	
	public int getLastContentID() {
		int ret = getSqlSession().selectOne("board.getLastContentID");
		return ret;
	}
	
	public void updateCounts(int content_id) {
		System.out.println("updateCounts:"+content_id);
		getSqlSession().update("board.updateCounts", content_id);
		
	}
	public int updateContentFile(ContentDto boardContentDto) {
		int ret = getSqlSession().update("board.updateContentFile",boardContentDto);
		return ret;
	}
	
	public BoardDto getBoard(String board_id) {
		BoardDto boardDto = getSqlSession().selectOne("board.getBoard", board_id);
		return boardDto;
	}
	
	public List<String> getContentYear() {
		List<String> list = getSqlSession().selectList("board.getContentYear");
		return list;
	}
	
	public List<ContentYearDto> getContentYearCountList() {
		List<ContentYearDto> list = getSqlSession().selectList("board.getContentYearCountList");
		return list;
	}
}
