package cpservice.board.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cpservice.board.domain.UserVO;
import cpservice.board.dto.LoginDTO;
import cpservice.board.mapper.UserMapper;
import cpservice.board.service.UserService;

@Repository
public class UserServiceImpl implements UserService {
	
	@Resource(name = "userMapper")
	private UserMapper dao;

	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return dao.login(dto);
	}

	@Override
	public boolean regist(LoginDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return dao.regist(dto);
	}

}
