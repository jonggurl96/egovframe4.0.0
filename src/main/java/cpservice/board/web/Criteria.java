package cpservice.board.web;

public class Criteria {

	private int bno;
	private int page;
	private int rcpp;
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRcpp() {
		return rcpp;
	}
	public void setRcpp(int rcpp) {
		this.rcpp = rcpp;
	}
	@Override
	public String toString() {
		return "Criteria [bno=" + bno + ", page=" + page + ", rcpp=" + rcpp + "]";
	}
	
	
}
