package cpservice.board.web;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cpservice.board.domain.BoardVO;
import cpservice.board.dto.SPDTO;
import cpservice.board.service.BoardService;

@RestController
public class BoardSearchController {

	@Inject
	private BoardService service;

	@Value("#{pageConfigBean['page_size']}")
	private String pageSize;

	private static final Logger logger = LoggerFactory.getLogger(BoardSearchController.class);

	@PostMapping("/search")
	public ResponseEntity<List<BoardVO>> search(@RequestBody SPDTO spdto) {
		
		ResponseEntity<List<BoardVO>> entity = null;
		
		logger.info("search(): tag = " + spdto.getTag() + ", keyword = " + spdto.getKeyword());
		logger.info("search(): page = " + spdto.getCurrentPageNo() + ", rcpp = " + spdto.getRecordCountPerPage());
		
		spdto.setPageSize(Integer.parseInt(pageSize));
		
		try {
			entity = new ResponseEntity<>(service.getList(spdto), HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
