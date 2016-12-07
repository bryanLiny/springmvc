package com.yiibai.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yiibai.springmvc.dao.UserDao;
import com.yiibai.springmvc.entity.AppUser;
import com.yiibai.springmvc.service.AppUserService;

@Service("appUserService")
@Transactional
public class AppUserServiceImpl implements AppUserService {

	@Autowired
	private UserDao dao;

	public AppUser findById(int id) {
		return dao.findById(id);
	}

	public AppUser findBySSO(String sso) {
		AppUser user = dao.findBySSO(sso);
		return user;
	}

	public void saveUser(AppUser user) {
		dao.save(user);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate
	 * update explicitly. Just fetch the entity from db and update it with
	 * proper values within transaction. It will be updated in db once
	 * transaction ends.
	 *
	 * 从数据库中查找需要更新的对象数据，重新set新值，在hibernate事物结束会被自动提交，
	 * 所以在此并不需要重复调用save或者update方法
	 */
	public void updateUser(AppUser user) {
		AppUser entity = dao.findById(user.getId());
		if (entity != null) {
			entity.setSsoId(user.getSsoId());
			entity.setPassword(user.getPassword());
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmail(user.getEmail());
			entity.setUserProfiles(user.getUserProfiles());
		}
	}

	public void deleteUserBySSO(String sso) {
		dao.deleteBySSO(sso);
	}

	public List<AppUser> findAllUsers() {
		return dao.findAllUsers();
	}

	public boolean isUserSSOUnique(Integer id, String sso) {
		AppUser user = findBySSO(sso);
		return (user == null || ((id != null) && (user.getId() == id)));
	}
}
