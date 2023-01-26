package cpservice.board.dto;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

public class SPDTO extends PaginationInfo{
	private String tag;
	private String keyword;
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
	@Override
	public String toString() {
		return "SPDTO [" + (tag != null ? "tag=" + tag + ", " : "") + (keyword != null ? "keyword=" + keyword + ", " : "")
				+ "pageNo=" + super.getCurrentPageNo() + ", rcpp=" + super.getRecordCountPerPage() + ", totalRecordCount="
				+ super.getTotalRecordCount() + "]";
		
	}
	
}
