package cpservice.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cpservice.board.domain.BoardVO;

@Service
public interface BoardService {

	public BoardVO select(int bno) throws Exception;

	public void regist(BoardVO vo) throws Exception;

	public void modify(BoardVO vo) throws Exception;

	public void remove(int bno) throws Exception;

	public int getCount() throws Exception;

	public List<BoardVO> getList(int start, int rcpp) throws Exception;
	
	public List<BoardVO> getList(String tag, String keyword, int start, int rcpp) throws Exception;

	public int getCountSearched(String tag, String keyword) throws Exception;
}
