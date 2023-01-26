package cpservice.board.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cpservice.board.domain.BoardVO;
import cpservice.board.mapper.BoardMapper;
import cpservice.board.search.Search;
import cpservice.board.service.BoardService;

@Repository
public class BoardServiceImpl implements BoardService {
	
	@Resource(name = "boardMapper")
	private BoardMapper dao;

	@Override
	public BoardVO select(int bno) throws Exception {
		// TODO Auto-generated method stub
		return dao.read(bno);
	}

	@Override
	public void regist(BoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.insert(vo);
	}

	@Override
	public void modify(BoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.update(vo);
	}

	@Override
	public void remove(int bno) throws Exception {
		// TODO Auto-generated method stub
		dao.delete(bno);
	}

	@Override
	public int getCount() throws Exception {
		// TODO Auto-generated method stub
		return dao.countAllList();
	}

	@Override
	public List<BoardVO> getList(int start, int rcpp) throws Exception {
		// TODO Auto-generated method stub
		return dao.search(new Search(start, rcpp));
	}

	@Override
	public List<BoardVO> getList(String tag, String keyword, int start, int rcpp) throws Exception {
		// TODO Auto-generated method stub
		return dao.search(new Search(tag, keyword, start, rcpp));
	}

	@Override
	public int getCountSearched(String tag, String keyword) throws Exception {
		// TODO Auto-generated method stub
		Search search = new Search(tag, keyword);
		return dao.countSearchList(search);
	}

}
