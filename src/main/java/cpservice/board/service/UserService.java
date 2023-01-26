package cpservice.board.service;

import cpservice.board.domain.UserVO;
import cpservice.board.dto.LoginDTO;

public interface UserService {

	public UserVO login(LoginDTO dto) throws Exception;
	
	public boolean regist(LoginDTO dto) throws Exception;
	
}
