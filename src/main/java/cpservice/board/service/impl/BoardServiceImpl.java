package cpservice.board.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cpservice.board.domain.BoardVO;
import cpservice.board.domain.SearchVO;
import cpservice.board.dto.SPDTO;
import cpservice.board.mapper.BoardMapper;
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
		vo.setRegdate(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
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
	public List<BoardVO> getList(SPDTO spdto) throws Exception {
		// TODO Auto-generated method stub
		SearchVO searchvo = new SearchVO(
				spdto.getTag(),
				spdto.getKeyword(),
				spdto.getFirstRecordIndex(),
				spdto.getRecordCountPerPage());
		
		spdto.setTotalRecordCount(dao.numberingRecords(searchvo));
		return dao.search(searchvo);
	}

}
