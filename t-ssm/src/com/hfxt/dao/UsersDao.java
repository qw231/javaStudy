package com.hfxt.dao;

import org.apache.ibatis.annotations.Param;

import com.hfxt.entity.Users;

public interface UsersDao {

	public Users getUsesByUsername(@Param("username")String username);
}
