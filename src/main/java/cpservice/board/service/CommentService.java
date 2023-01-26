package cpservice.board.service;

import java.util.List;

import cpservice.board.domain.CommentVO;

public interface CommentService {
	
	public List<CommentVO> getCommentList(int bno) throws Exception;
	
	public void modifyComment(CommentVO vo) throws Exception;
	
	public void removeComment(int cno) throws Exception;
	
	public void writeComment(CommentVO vo) throws Exception;
	
	public void writeReply(CommentVO vo) throws Exception;

}
