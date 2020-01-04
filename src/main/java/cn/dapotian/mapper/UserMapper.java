package cn.dapotian.mapper;

import cn.dapotian.domain.User;

public interface UserMapper {
	/**
	 * 根据用户名查找对应的用户
	 * @param username	用户名
	 * @return
	 */
	User findUserByUsername(String username);

}
