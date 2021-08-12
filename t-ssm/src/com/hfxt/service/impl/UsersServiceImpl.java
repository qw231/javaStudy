package com.hfxt.service.impl;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hfxt.dao.UsersDao;
import com.hfxt.entity.Users;
import com.hfxt.service.UsersService;

@Service("usersServiceImpl")
@Transactional(propagation=Propagation.REQUIRED)
public class UsersServiceImpl implements UsersService{
	
	@Resource
	private UsersDao usersDao;

	@Override
	public Users getUsesByUsername(@Param("username") String username) {
		return usersDao.getUsesByUsername(username);
	}

}
