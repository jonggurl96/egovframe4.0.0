package cpservice.board.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import cpservice.board.domain.BoardVO;
import cpservice.board.search.Search;

@Mapper("boardMapper")
public interface BoardMapper {

	public BoardVO read(int bno) throws Exception;
	
	public void insert(BoardVO vo) throws Exception;
	
	public void update(BoardVO vo) throws Exception;
	
	public void delete(int bno) throws Exception;
	
	public int countAllList() throws Exception;
	
	/**
	 * 페이징 및 키워드 검색
	 * @param tag: 검색할 항목
	 * @param keyword: 검색 내용
	 * @param start: 시작 인덱스
	 * @param rcpp: 검색 항목 개수
	 * @return
	 * @throws Exception
	 */
	public List<BoardVO> search(Search search) throws Exception;
	
	/**
	 * 검색된 전체 항목 개수
	 * @param tag
	 * @param keyword
	 * @return
	 * @throws Exception
	 */
	public int countSearchList(Search search) throws Exception;
}
