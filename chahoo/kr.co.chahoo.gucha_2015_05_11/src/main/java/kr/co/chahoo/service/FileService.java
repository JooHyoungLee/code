package kr.co.chahoo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kr.co.chahoo.file.model.FileDto;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	/**
	 * 첨부파일 목록
	 * @param content_id	게시물 아이디
	 * @return
	 */
	public List<FileDto> getContentFileList(int content_id);
	
	/**
	 * 파일 상세 정보
	 * @param file_id	파일 상세정보
	 * @return
	 */
	public FileDto getContentFile(int file_id);
	
	/**
	 * 전체  파일 수
	 * @return
	 */
	public int getAllCount();
		
	/**
	 * 파일 생성
	 * @param content_id	게시물 아이디
	 * @param board_id		게시판 아이디
	 * @param multipartFile	파일
	 * @return
	 * @throws IOException
	 */
	public int createContentFile(int content_id, String board_id, MultipartFile multipartFile) throws IOException;
	
	/**
	 * 파일 수정
	 * @param content_id	게시물 아이디
	 * @param boardType		게시판 아이디
	 * @param fileId_str	파일 아이디
	 * @return
	 */
	public int updateContentFile(int content_id, String boardType, String fileId_str);
	
	/**
	 * 파일 삭제
	 * @param file_id	파일 아이디
	 * @return
	 */
	public int deleteContentFile(int file_id);
	
	/**
	 * 파일 아이디 분리
	 * @param fileList	구분자 '##'으로 생성된 파일아이디 목록
	 * @return
	 */
	public ArrayList<String> uploadFileCount(String fileList);
	
	/**
	 * 마지막 업로드된 파일아이디 반환
	 * @return
	 */
	public int getLastFileID();
	
}
