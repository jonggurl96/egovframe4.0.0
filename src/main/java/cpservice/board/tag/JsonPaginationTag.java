package cpservice.board.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.DefaultPaginationManager;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationManager;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationRenderer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonPaginationTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	private String jsonPaginationInfo;
	private String type;
	private String jsFunction;

	@Override
	public int doEndTag() throws JspException {

		ObjectMapper mapper = new ObjectMapper();
		
		try {
			JspWriter out = pageContext.getOut();
			PaginationManager paginationManager;
			WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
			WebApplicationContext ctxd = RequestContextUtils.findWebApplicationContext((HttpServletRequest) pageContext.getRequest(), pageContext.getServletContext());

			if (ctx.containsBean("paginationManager")) {
				paginationManager = (PaginationManager) ctx.getBean("paginationManager");
			} else if (ctxd.containsBean("paginationManager")) {
				paginationManager = (PaginationManager) ctxd.getBean("paginationManager");
			} else {
				//bean 정의가 없다면 DefaultPaginationManager를 사용. 빈설정이 없으면 기본 적인 페이징 리스트라도 보여주기 위함.
				paginationManager = new DefaultPaginationManager();
			}
			
			PaginationInfo paginationInfo = (PaginationInfo) mapper.readValue(jsonPaginationInfo, PaginationInfo.class);
			
			PaginationRenderer paginationRenderer = paginationManager.getRendererType(type);
			String contents = paginationRenderer.renderPagination(paginationInfo, jsFunction);
			out.println(contents);
			return EVAL_PAGE;
		} catch (IOException e) {
			throw new JspException();
		}
	}

	public void setJsonPaginationInfo(String jsonPaginationInfo) {
		this.jsonPaginationInfo = jsonPaginationInfo;
	}
}
