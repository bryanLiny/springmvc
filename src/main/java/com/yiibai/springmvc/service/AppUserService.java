package com.yiibai.springmvc.service;

import java.util.List;

import com.yiibai.springmvc.entity.AppUser;

public interface AppUserService {

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
	 * ����
	 * @param user
	 */
	void saveUser(AppUser user);

	/**
	 * ����
	 * @param user
	 */
	void updateUser(AppUser user);

	/**
	 * ɾ��
	 * @param sso
	 */
	void deleteUserBySSO(String sso);

	/**
	 * ��ȡ�б�
	 * @return
	 */
	List<AppUser> findAllUsers();

	/**
	 * ssoΨһ��
	 * @param id
	 * @param sso
	 * @return
	 */
	boolean isUserSSOUnique(Integer id, String sso);
}
