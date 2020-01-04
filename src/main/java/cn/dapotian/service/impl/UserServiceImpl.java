package cn.dapotian.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.sym.Name;

import cn.dapotian.InitUser;
import cn.dapotian.domain.User;
import cn.dapotian.service.UserService;
@Service
public class UserServiceImpl implements UserService{

	@Override
	public User findUserByUsername(String username) {
		InitUser initUser = new InitUser();
		List<User> users = initUser.init();
		for(User user : users) {
			String name = user.getUsername();
			if (username.equals(name)) {
				return user;
			}
		}
		return null;
	}

}
