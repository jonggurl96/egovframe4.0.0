package cpservice.board.mapper;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import cpservice.board.domain.UserVO;
import cpservice.board.dto.LoginDTO;

@Mapper("userMapper")
public interface UserMapper {

	public UserVO login(LoginDTO dto) throws Exception;
	
	public boolean regist(LoginDTO dto) throws Exception;
}
