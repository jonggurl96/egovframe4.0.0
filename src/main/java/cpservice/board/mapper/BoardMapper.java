package cpservice.board.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import cpservice.board.domain.BoardVO;
import cpservice.board.domain.SearchVO;

@Mapper("boardMapper")
public interface BoardMapper {

	public BoardVO read(int bno) throws Exception;
	
	public void insert(BoardVO vo) throws Exception;
	
	public void update(BoardVO vo) throws Exception;
	
	public void delete(int bno) throws Exception;
	
	public int numberingRecords(SearchVO searchvo) throws Exception;
	
	public List<BoardVO> search(SearchVO searchvo) throws Exception;

}
