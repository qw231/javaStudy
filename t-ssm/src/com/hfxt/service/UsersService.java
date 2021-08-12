package com.hfxt.service;

import org.apache.ibatis.annotations.Param;

import com.hfxt.entity.Users;

public interface UsersService {
	public Users getUsesByUsername(@Param("username")String username);
}
