package cpservice.board.web;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Map<String, Object>> search(@RequestBody SPDTO spdto) {
		
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		
		logger.info("search(): tag = " + spdto.getTag() + ", keyword = " + spdto.getKeyword());
		logger.info("search(): page = " + spdto.getCurrentPageNo() + ", rcpp = " + spdto.getRecordCountPerPage());
		
		spdto.setPageSize(Integer.parseInt(pageSize));
		map.put("pageInfo", spdto);
		
		try {
			map.put("list", service.getList(spdto));
			entity = new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
