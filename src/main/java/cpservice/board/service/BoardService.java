package cpservice.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cpservice.board.domain.BoardVO;
import cpservice.board.dto.SPDTO;

@Service
public interface BoardService {

	public BoardVO select(int bno) throws Exception;

	public void regist(BoardVO vo) throws Exception;

	public void modify(BoardVO vo) throws Exception;

	public void remove(int bno) throws Exception;

	public List<BoardVO> getList(SPDTO spdto) throws Exception;
}
