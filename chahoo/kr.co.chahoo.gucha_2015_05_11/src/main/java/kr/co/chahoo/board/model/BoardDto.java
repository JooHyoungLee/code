package kr.co.chahoo.board.model;

public class BoardDto {
	
	public String board_id;		//게시판 아이디
	public String board_group;	//게시판 그룹
	public String name;			//게시판 이름
	public String desc;			//게시판 설명
	
	public BoardDto() {
		super();
	}

	public BoardDto(String board_id, String board_group, String name,
			String desc) {
		super();
		this.board_id = board_id;
		this.board_group = board_group;
		this.name = name;
		this.desc = desc;
	}

	public String getBoard_id() {
		return board_id;
	}

	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}

	public String getBoard_group() {
		return board_group;
	}

	public void setBoard_group(String board_group) {
		this.board_group = board_group;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "BoardDto [board_id=" + board_id + ", board_group="
				+ board_group + ", name=" + name + ", desc=" + desc + "]";
	}
}
