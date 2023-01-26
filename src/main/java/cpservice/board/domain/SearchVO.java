package cpservice.board.domain;

public class SearchVO {

	private String tag, keyword;
	private int start, rcpp;
	
	public SearchVO() {
		super();
	}
	
	public SearchVO(String tag, String keyword) {
		super();
		this.tag = tag;
		this.keyword = keyword;
	}

	public SearchVO(int start, int rcpp) {
		super();
		this.start = start;
		this.rcpp = rcpp;
	}

	public SearchVO(String tag, String keyword, int start, int rcpp) {
		super();
		this.tag = tag;
		this.keyword = keyword;
		this.start = start;
		this.rcpp = rcpp;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getRcpp() {
		return rcpp;
	}
	public void setRcpp(int rcpp) {
		this.rcpp = rcpp;
	}
	@Override
	public String toString() {
		return "Search [tag=" + tag + ", keyword=" + keyword + ", start=" + start + ", rcpp=" + rcpp + "]";
	}
	
}
