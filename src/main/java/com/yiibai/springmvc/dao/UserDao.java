package com.yiibai.springmvc.dao;

import java.util.List;

import com.yiibai.springmvc.entity.AppUser;

public interface UserDao {

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
	 * 持久化（保存）数据
	 * @param user
	 */
	void save(AppUser user);

	/**
	 * 删除
	 * @param sso
	 */
	void deleteBySSO(String sso);

	/**
	 * 获取列表
	 * @return
	 */
	List<AppUser> findAllUsers();
}
