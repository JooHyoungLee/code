package kr.co.chahoo.file;

import java.util.List;

import kr.co.chahoo.file.model.FileDto;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class FileDao extends SqlSessionDaoSupport{
	
	public List<FileDto> getContentFileList(int content_id) {
		List<FileDto> list = getSqlSession().selectList("file.getContentFileList", content_id);
		return list;
	}
	
	public FileDto getContentFile(int file_id) {
		FileDto fileDto = getSqlSession().selectOne("file.getContentFile", file_id);
		return fileDto;
	}

	public int getAllCount() {
		int ret = 0;
		try {
			ret = getSqlSession().selectOne("file.getAllCount");
		} catch (Exception e) {
			e.printStackTrace();
			ret = 0;
		}
		return ret;
	}
	
	public int getLastFileID()
	{
		int ret = getSqlSession().selectOne("file.getLastFileID");
		return ret;
	}

	public int createContentFile(FileDto fileDto) {
		int ret = 0;
		try {
			getSqlSession().insert("file.createContentFile", fileDto);
			ret = getLastFileID();
		} catch (Exception e) {
			ret = 0;
			e.printStackTrace();
		}
		System.out.println("createContentFile RET: "+ret);
		return ret;
	}

	public int updateContentFile(FileDto fileDto) {
		int ret = 0;
		try {
			getSqlSession().update("file.updateContentFile", fileDto);
			ret = 1;
		} catch (Exception e) {
			e.printStackTrace();
			ret = 0;
		}
		return ret;
	}

	public int deleteContentFile(int fileId) {
		int ret = 0;
		try {
			getSqlSession().delete("file.deleteContentFile", fileId);
			ret = 1;
		} catch (Exception e) {
			e.printStackTrace();
			ret = 0;
		}
		return ret;
	}
}
