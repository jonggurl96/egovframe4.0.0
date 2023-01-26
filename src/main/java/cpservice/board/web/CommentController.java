package cpservice.board.web;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cpservice.board.domain.CommentVO;
import cpservice.board.service.CommentService;

@RestController
@RequestMapping("/comments/*")
public class CommentController {
	
	@Inject
	private CommentService service;
	
	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

	@GetMapping("all/{bno}")
	public ResponseEntity<List<CommentVO>> getCommentsFromContent(@PathVariable("bno") int bno) {
		ResponseEntity<List<CommentVO>> entity = null;
		logger.info("get all comments from bno: " + bno);
		try {
			entity = new ResponseEntity<>(service.getCommentList(bno), HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@DeleteMapping("{cno}")
	public ResponseEntity<String> removeComment(@PathVariable("cno") int cno) {
		ResponseEntity<String> entity = null;
		logger.info("delete comment cno: " + cno);
		try {
			service.removeComment(cno);
			entity = new ResponseEntity<String>("삭제되었습니다.", HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
