package cpservice.board.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cpservice.board.domain.CommentVO;
import cpservice.board.mapper.CommentMapper;
import cpservice.board.service.CommentService;

@Repository
public class CommentServiceImpl implements CommentService {
	
	@Resource(name = "commentMapper")
	private CommentMapper dao;

	@Override
	public List<CommentVO> getCommentList(int bno) throws Exception {
		// TODO Auto-generated method stub
		return dao.list(bno);
	}

	@Override
	public void modifyComment(CommentVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.update(vo);
	}

	@Override
	public void removeComment(int cno) throws Exception {
		// TODO Auto-generated method stub
		dao.delete(cno);
	}

	@Override
	public void writeComment(CommentVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.writeComment(vo);
	}

	@Override
	public void writeReply(CommentVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.writeReply(vo);
	}
	
}
