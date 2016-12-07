package com.yiibai.springmvc.dao;

import java.util.List;

import com.yiibai.springmvc.entity.AppUser;

public interface UserDao {

	/**
	 * ����id����
	 * @param id
	 * @return
	 */
	AppUser findById(int id);

	/**
	 * ����ssoid����
	 * @param sso
	 * @return
	 */
	AppUser findBySSO(String sso);

	/**
	 * �־û������棩����
	 * @param user
	 */
	void save(AppUser user);

	/**
	 * ɾ��
	 * @param sso
	 */
	void deleteBySSO(String sso);

	/**
	 * ��ȡ�б�
	 * @return
	 */
	List<AppUser> findAllUsers();
}
