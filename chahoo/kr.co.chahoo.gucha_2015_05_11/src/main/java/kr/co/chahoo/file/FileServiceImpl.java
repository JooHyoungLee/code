package kr.co.chahoo.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import kr.co.chahoo.common.FormatUtils;
import kr.co.chahoo.file.model.FileDto;
import kr.co.chahoo.service.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService{
	
	@Autowired private FileDao fileDao;
	@Inject private FileSystemResource fsResource;
	
	@Override
	public List<FileDto> getContentFileList(int content_id) {
		List<FileDto> list = fileDao.getContentFileList(content_id);
		for(int i=0; i<list.size(); i++)
		{
			list.get(i).setSize_txt(FormatUtils.convertToStringRepresentation(list.get(i).getSize()));
		}
		return list;
	}

	@Override
	public int getAllCount() {
		int ret = fileDao.getAllCount();
		return ret;
	}

	@Override
	public int createContentFile(int content_id, String board_id, MultipartFile multipartFile) throws IOException {
		
		String fileOriginalName = multipartFile.getOriginalFilename();
		String fileExt = FormatUtils.getExtension(fileOriginalName);
		long fileSize = multipartFile.getSize();
		
		StringBuilder originalBuilder = new StringBuilder();
		originalBuilder.append(fsResource.getPath());
		originalBuilder.append("/").append(board_id);
		originalBuilder.append("/").append(content_id);
		String path = originalBuilder.toString();
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		originalBuilder.append("/").append(fileOriginalName);
		String filePath = originalBuilder.toString();
		
		byte[] bytes = multipartFile.getBytes();
		File file = new File(filePath);
		
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		fileOutputStream.write(bytes);
		fileOutputStream.close();
		
		FileDto fileDto = new FileDto();
		fileDto.setContent_id(content_id);
		fileDto.setExt(fileExt);
		fileDto.setName(fileOriginalName);
		fileDto.setPath(filePath);
		fileDto.setSize(fileSize);
		
		int ret = fileDao.createContentFile(fileDto);
		return ret;
	}

	@Override
	public int updateContentFile(int content_id, String boardType,String fileId_str) {
		
		int file_id = Integer.parseInt(fileId_str);
		FileDto orgFileDto = fileDao.getContentFile(file_id);
		
		StringBuilder originalBuilder = new StringBuilder();
		originalBuilder.append(fsResource.getPath());
		originalBuilder.append("/").append(boardType);
		originalBuilder.append("/").append(content_id);
		String path = originalBuilder.toString();
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		originalBuilder.append("/").append(orgFileDto.getName());
		String filePath = originalBuilder.toString();
		
		if(FormatUtils.fileIsLive(orgFileDto.getPath()))
		{
			FormatUtils.fileMove(orgFileDto.getPath(), filePath);
			
			FileDto fileDto = new FileDto();
			fileDto.setPath(filePath);
			fileDto.setContent_id(content_id);
			fileDto.setFile_id(file_id);
			
			int ret = fileDao.updateContentFile(fileDto);
			return ret;
		}
		else
		{
			return 0;
		}
	}

	@Override
	public int deleteContentFile(int file_id) {
		int ret = fileDao.deleteContentFile(file_id);
		return ret;
	}

	@Override
	public ArrayList<String> uploadFileCount(String fileList) {
		
		ArrayList<String> list = new ArrayList<String>();
		try {
			if(!fileList.equals(""))
			{
				String str[] = fileList.split("##");
				for(int i=0; i<str.length; i++)
				{
					list.add(str[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public FileDto getContentFile(int file_id) {
		FileDto fileDto = fileDao.getContentFile(file_id);
		return fileDto;
	}

	@Override
	public int getLastFileID() {
		int ret = fileDao.getLastFileID();
		return ret;
	}
}
