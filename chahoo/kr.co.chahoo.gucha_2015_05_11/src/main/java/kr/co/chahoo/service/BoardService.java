package kr.co.chahoo.service;

import java.util.List;

import kr.co.chahoo.board.model.BoardDto;
import kr.co.chahoo.board.model.ContentDto;
import kr.co.chahoo.board.model.ContentTypeDto;
import kr.co.chahoo.board.model.ContentYearDto;
import kr.co.chahoo.common.model.ConditionSet;

public interface BoardService {
	
	/**
	 * 게시물 목록
	 * @param conditionSet 조건 데이터
	 * @return
	 */
	public List<ContentDto> getContentList(ConditionSet conditionSet);
	
	/**
	 * 게시물 상세 정보
	 * @param content_id	게시물 아이디
	 * @return
	 */
	public ContentDto getContent(int content_id);
	
	/**
	 * 게시물 검색 결과
	 * @param conditionSet	조건 데이터
	 * @return
	 */
	public List<ContentDto> getContentSearchList(ConditionSet conditionSet);
	
	/**
	 * 게시판별 총 게시물 수
	 * @param conditionSet	조건 데이터(게시판 아이디)
	 * @return
	 */
	public int getAllCount(ConditionSet conditionSet);
	
	/**
	 * 게시판별 검색결과 수
	 * @param conditionSet	조건데이터
	 * @return
	 */
	public int getContentSearchCount(ConditionSet conditionSet);
	
	/**
	 *	게시물 생성 
	 * @param boardContentDto 게시물 정보
	 * @return
	 */
	public int createContent(ContentDto boardContentDto);
	
	/**
	 * 게시물 수정
	 * @param boardContentDto	게시물 정보
	 * @return
	 */
	public int updateContent(ContentDto boardContentDto);
	
	/**
	 * 게시물 첨부파일 수정
	 * @param boardContentDto	게시물 정보
	 * @return
	 */
	public int updateContentFile(ContentDto boardContentDto);
	
	/**
	 * 게시물 삭제
	 * @param contentId	게시물 아이디
	 * @return
	 */
	public int deleteContent(int contentId);
	
	/**
	 * 게시판 목록
	 * @return
	 */
	public List<BoardDto> getBoardList();
	
	/**
	 * 게시판 상세정보
	 * @param board_id	게시판 아이디
	 * @return
	 */
	public BoardDto getBoard(String board_id);
	
	/**
	 * 게시물 타입 목록
	 * @param board_id	게시판 아이디
	 * @return
	 */
	public List<ContentTypeDto> getBoardContentTypeList(String board_id);
	
	/**
	 * 최근 등록된 게시물 아이디 반환
	 * @return
	 */
	public int getLastContentID();
	
	/**
	 * 조회수 증가
	 * @param content_id	게시물 아이디
	 */
	public void updateCounts(int content_id);
	
	/**
	 * 자료실 - 프로젝트 연도 목록 반환
	 * @return
	 */
	public List<String> getContentYear();
	
	/**
	 * 자료실 - 프로젝트 연도별 게시물 수 반환
	 * @return
	 */
	public List<ContentYearDto> getContentYearCountList();
}
