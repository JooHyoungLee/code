package kr.co.chahoo.file.model;

import java.sql.Timestamp;

public class FileDto {
	
	public int file_id;			//파일 아이디
	public int content_id;		//게시물 아이디
	public String name;			//파일 이름
	public String ext;			//파일 확장자
	public long size;			//파일 크기
	public String size_txt;		//파일 크기 단위변환값
	public String path;			//파일 저장 경로
	public Timestamp reg_date;	//파일 등록 일시
	
	public FileDto() {
		super();
	}

	public FileDto(int file_id, int content_id, String name, String ext,
			long size, String size_txt, String path, Timestamp reg_date) {
		super();
		this.file_id = file_id;
		this.content_id = content_id;
		this.name = name;
		this.ext = ext;
		this.size = size;
		this.size_txt = size_txt;
		this.path = path;
		this.reg_date = reg_date;
	}

	public int getFile_id() {
		return file_id;
	}

	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}

	public int getContent_id() {
		return content_id;
	}

	public void setContent_id(int content_id) {
		this.content_id = content_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getSize_txt() {
		return size_txt;
	}

	public void setSize_txt(String size_txt) {
		this.size_txt = size_txt;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}

	@Override
	public String toString() {
		return "FileDto [file_id=" + file_id + ", content_id=" + content_id
				+ ", name=" + name + ", ext=" + ext + ", size=" + size
				+ ", size_txt=" + size_txt + ", path=" + path + ", reg_date="
				+ reg_date + "]";
	}

}
