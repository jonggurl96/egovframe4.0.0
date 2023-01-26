package cpservice.board.domain;

public class CommentVO {

	private int cno, bno, groupNo, groupOrder, depth, parentCno; 
	private String writer, content, regdate;
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public int getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}
	public int getGroupOrder() {
		return groupOrder;
	}
	public void setGroupOrder(int groupOrder) {
		this.groupOrder = groupOrder;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getParentCno() {
		return parentCno;
	}
	public void setParentCno(int parentCno) {
		this.parentCno = parentCno;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "CommentVO [cno=" + cno + ", bno=" + bno + ", groupNo=" + groupNo + ", groupOrder=" + groupOrder
				+ ", depth=" + depth + ", parentCno=" + parentCno + ", writer=" + writer + ", content=" + content
				+ ", regdate=" + regdate + "]";
	}
}
