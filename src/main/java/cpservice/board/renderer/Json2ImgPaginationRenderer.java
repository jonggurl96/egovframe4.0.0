package cpservice.board.renderer;

import javax.servlet.ServletContext;

import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import cpservice.cmmn.web.EgovImgPaginationRenderer;

public class Json2ImgPaginationRenderer extends EgovImgPaginationRenderer {

	private static final Logger logger = LoggerFactory.getLogger(Json2ImgPaginationRenderer.class);
	
	@Override
	public void initVariables() {
		// TODO Auto-generated method stub
		super.initVariables();
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		super.setServletContext(servletContext);
	}

	public String renderPagination(String json, String jsFunction) {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		PaginationInfo paginationInfo = null;
		try {
			paginationInfo = (PaginationInfo)mapper.readValue(json, PaginationInfo.class);
			logger.info("renderPagination........................................." + paginationInfo.getLastPageNoOnPageList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.renderPagination(paginationInfo, jsFunction);
	}

	
}
