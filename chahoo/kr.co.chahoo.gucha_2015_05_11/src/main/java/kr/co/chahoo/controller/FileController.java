package kr.co.chahoo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.chahoo.board.model.ContentDto;
import kr.co.chahoo.common.FormatUtils;
import kr.co.chahoo.file.model.FileDto;
import kr.co.chahoo.service.BoardService;
import kr.co.chahoo.service.FileService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/file")
public class FileController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	@Autowired private FileService fileService;
	@Autowired private BoardService boardService;
	
	/**
	 * 파일 업로드 프로세스
	 * @param board_id	게시판 아이디
	 * @param mRequest	파일
	 * @return
	 */
	@RequestMapping(value = "/{board_id}/uploadProcess", method = RequestMethod.POST)
	@ResponseBody
	public String uploadProcess(@PathVariable String board_id, HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest mRequest) {
		logger.info("File - uploadProcess");
		int ret = 0;
		try {
			request.setCharacterEncoding("UTF-8");
			MultipartFile file = mRequest.getFile("attachFile");
			ret = fileService.createContentFile(0, board_id, file);	

		} catch (Exception e) {
			e.printStackTrace();
			ret = 0;
		}
		return String.valueOf(ret); //업로드 성공시 파일 아이디를 반환
	}
	
	/**
	 * 파일 상세정보
	 * @param file_id	파일 아이디
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value="/{file_id}/content", method = RequestMethod.GET)
	public @ResponseBody FileDto fileDetail(@PathVariable int file_id, HttpServletRequest request, HttpServletResponse response, Principal principal) throws UnsupportedEncodingException {
		
		logger.info("File - Content");
		request.setCharacterEncoding("UTF-8");

		FileDto fileDto = fileService.getContentFile(file_id);
		
		return fileDto;
	}
	
	/**
	 * 파일 삭제
	 * @param file_id	파일 아이디
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value="/{file_id}/delete", method = RequestMethod.GET)
	@ResponseBody
	public String  delete(@PathVariable int file_id, HttpServletRequest request, HttpServletResponse response, Principal principal) throws UnsupportedEncodingException {
		
		logger.info("File - Delete");
		request.setCharacterEncoding("UTF-8");
		
		FileDto fileDto = fileService.getContentFile(file_id);
		int content_id = fileDto.getContent_id();
		fileService.deleteContentFile(file_id);	//파일 삭제
		
		if(content_id > 0)	//게시물과 연결되어 있는지 판별
		{
			List<FileDto> list =  fileService.getContentFileList(content_id);
			//파일 삭제 후 해당 게시물의 상태 업데이트
			if(list.size() == 0)
			{
				ContentDto contentDto = new ContentDto();
				contentDto.setIs_file(0);
				contentDto.setContent_id(content_id);
				boardService.updateContentFile(contentDto);
			}
		}
		
		return "success";
	}
	
	/**
	 * 파일 다운로드
	 * @param file_id 파일 아이디
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/{file_id}/download", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> downloadStuff(@PathVariable int file_id) throws IOException {
	    FileDto fileDto = fileService.getContentFile(file_id);
	    
	    File file = new File(fileDto.getPath());
	    
	    if(file.isFile())
	    {
	    	HttpHeaders respHeaders = new HttpHeaders();
			respHeaders.setContentType(MediaType.valueOf(FormatUtils.fileMineType(fileDto.getPath()))); //MineType 설정
		    respHeaders.setContentLength(fileDto.getSize());	//파일 크기 설정

		    //파일명 한글 깨짐 방지
		    String encordedFilename = URLEncoder.encode(fileDto.getName(),"UTF-8").replace("+", "%20");
		    respHeaders.set("Content-Disposition", "attachment;filename=" + encordedFilename + ";filename*= UTF-8''" + encordedFilename);

		    InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
		    return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
	    }
	    else
	    {
	    	return new ResponseEntity<InputStreamResource>(null, null, HttpStatus.BAD_REQUEST);
	    }
	    
	}
	
}
