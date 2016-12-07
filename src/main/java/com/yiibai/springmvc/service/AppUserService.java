package com.yiibai.springmvc.service;

import java.util.List;

import com.yiibai.springmvc.entity.AppUser;

public interface AppUserService {

	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	AppUser findById(int id);

	/**
	 * 根据ssoid查找
	 * @param sso
	 * @return
	 */
	AppUser findBySSO(String sso);

	/**
	 * 保存
	 * @param user
	 */
	void saveUser(AppUser user);

	/**
	 * 更新
	 * @param user
	 */
	void updateUser(AppUser user);

	/**
	 * 删除
	 * @param sso
	 */
	void deleteUserBySSO(String sso);

	/**
	 * 获取列表
	 * @return
	 */
	List<AppUser> findAllUsers();

	/**
	 * sso唯一性
	 * @param id
	 * @param sso
	 * @return
	 */
	boolean isUserSSOUnique(Integer id, String sso);
}
