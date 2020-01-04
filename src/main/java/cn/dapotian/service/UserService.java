package cn.dapotian.service;

import org.springframework.stereotype.Service;

import cn.dapotian.domain.User;


public interface UserService {
	/**
	 * 根据用户名查找对应的用户
	 * @param username	用户名
	 * @return
	 */
	User findUserByUsername(String username);
}
