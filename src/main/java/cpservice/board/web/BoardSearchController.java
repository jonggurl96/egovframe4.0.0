package cpservice.board.web;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cpservice.board.domain.BoardVO;
import cpservice.board.service.BoardService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@RestController
public class BoardSearchController {
	
	@Inject
	private BoardService service;
	
	@Value("#{pageConfigBean['page_size']}")
	private String pageSize;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardSearchController.class);

	@RequestMapping(value="/search", method=RequestMethod.POST)
	public ResponseEntity<List<BoardVO>> search(@RequestBody Map<String, Object> data) {
		
		ResponseEntity<List<BoardVO>> entity = null;
		List<BoardVO> list = null;
		
		String tag = (String) data.get("tag");
		String keyword = (String) data.get("keyword");
		int page = Integer.parseInt(String.valueOf(data.get("page"))); 
		int rcpp = Integer.parseInt(String.valueOf(data.get("rcpp")));
		int totalRecordCount = Integer.parseInt(String.valueOf(data.get("totalRecordCount")));
		
		logger.info("search(): tag = " + tag + ", keyword = " + keyword);
		logger.info("search(): page = " + page + ", rcpp = " + rcpp);
		logger.info("search(): totalRecordCount = " + totalRecordCount);
		
		PaginationInfo pageInfo = new PaginationInfo();
		pageInfo.setCurrentPageNo(page);
		pageInfo.setPageSize(Integer.parseInt(pageSize));
		pageInfo.setRecordCountPerPage(rcpp);
		pageInfo.setTotalRecordCount(totalRecordCount);

		try {
			if(tag == null) {
				//검색 query가 존재하지 않을 경우
				list = service.getList(pageInfo.getFirstRecordIndex(), rcpp);
			} else {
				//검색 query가 존재할 경우
				list = service.getList(tag, keyword, pageInfo.getFirstRecordIndex(), rcpp);
			}

			entity = new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
