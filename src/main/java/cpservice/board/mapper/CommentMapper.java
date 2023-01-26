package cpservice.board.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import cpservice.board.domain.CommentVO;

@Mapper("commentMapper")
public interface CommentMapper {
//list, update, delete
	public List<CommentVO> list(int bno) throws Exception;
	
	public void update(CommentVO vo) throws Exception;
	
	public void delete(int cno) throws Exception;
	
	public void writeComment(CommentVO vo) throws Exception;
	
	public void writeReply(CommentVO vo) throws Exception;
}
