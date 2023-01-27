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
				spdto.getKeyword());
		
		spdto.setTotalRecordCount(dao.numberingRecords(searchvo));
		
		/**
		 * AJAX 사용하지 않는 대신 한 번에 표시되는 페이지 리스트에 존재하는 모든 레코드를 읽기 위한 레코드의 수
		 */
		int recordCountOnFullPageList = (spdto.getLastPageNoOnPageList() - spdto.getFirstPageNoOnPageList() + 1) * spdto.getRecordCountPerPage();
		recordCountOnFullPageList = spdto.getTotalRecordCount() < recordCountOnFullPageList ? spdto.getTotalRecordCount() : recordCountOnFullPageList;
		
		/**
		 * 1 ~ 10 중 어느 페이지가 오더라도 1페이지부터 검색
		 * 검색하려는 전체 범위 중 첫 번째 인덱스 계산
		 */
		int firstRecordIndex = spdto.getFirstRecordIndex();
		firstRecordIndex -= firstRecordIndex % recordCountOnFullPageList;
		
		searchvo.setStart(firstRecordIndex);
		searchvo.setRcpp(recordCountOnFullPageList);
		
		return dao.search(searchvo);
	}

}
