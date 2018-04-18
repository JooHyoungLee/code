package kr.co.chahoo.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.chahoo.board.model.BoardDto;
import kr.co.chahoo.board.model.ContentDto;
import kr.co.chahoo.board.model.ContentTypeDto;
import kr.co.chahoo.board.model.ContentYearDto;
import kr.co.chahoo.common.model.ConditionSet;
import kr.co.chahoo.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired private BoardDao boardDao;
	
	@Override
	public List<ContentDto> getContentList(ConditionSet conditionSet) {
		List<ContentDto> list = boardDao.getContentList(conditionSet);
		return list;
	}

	@Override
	public ContentDto getContent(int content_id) {
		ContentDto contentDto = boardDao.getContent(content_id);
		return contentDto;
	}

	@Override
	public int createContent(ContentDto boardContentDto) {
		int ret = boardDao.createContent(boardContentDto);
		return ret;
	}

	@Override
	public int updateContent(ContentDto boardContentDto) {
		int ret = boardDao.updateContent(boardContentDto);
		return ret;
	}

	@Override
	public int deleteContent(int contentId) {
		int ret = boardDao.deleteContent(contentId);
		return ret;
	}

	@Override
	public List<BoardDto> getBoardList() {
		List<BoardDto> list = boardDao.getBoardList();
		return list;
	}

	@Override
	public List<ContentTypeDto> getBoardContentTypeList(String board_id) {
		String keyword = "";
		if(board_id.equals("store"))
		{
			keyword = board_id;
		}
		else
		{
			keyword = "all";
		}
		List<ContentTypeDto> list = boardDao.getBoardContentTypeList(keyword);
		return list;
	}

	@Override
	public List<ContentDto> getContentSearchList(ConditionSet conditionSet) {
		List<ContentDto> list = boardDao.getContentSearchList(conditionSet);
		return list;
	}

	@Override
	public int getContentSearchCount(ConditionSet conditionSet) {
		int ret = boardDao.getContentSearchCount(conditionSet);
		return ret;
	}

	@Override
	public int getAllCount(ConditionSet conditionSet) {
		int ret = boardDao.getAllCount(conditionSet);
		return ret;
	}

	@Override
	public int getLastContentID() {
		int ret = boardDao.getLastContentID();
		return ret;
	}

	@Override
	public void updateCounts(int content_id) {
		boardDao.updateCounts(content_id);
	}

	@Override
	public int updateContentFile(ContentDto boardContentDto) {
		int ret = boardDao.updateContentFile(boardContentDto);
		return ret;
	}

	@Override
	public BoardDto getBoard(String board_id) {
		BoardDto boardDto = boardDao.getBoard(board_id);
		return boardDto;
	}

	@Override
	public List<String> getContentYear() {
		List<String> list = boardDao.getContentYear();
		return list;
	}

	@Override
	public List<ContentYearDto> getContentYearCountList() {
		List<ContentYearDto> list = boardDao.getContentYearCountList();
		return list;
	}

}
