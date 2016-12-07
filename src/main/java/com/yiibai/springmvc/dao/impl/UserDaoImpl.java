package com.yiibai.springmvc.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.yiibai.springmvc.dao.AbstractDao;
import com.yiibai.springmvc.dao.UserDao;
import com.yiibai.springmvc.entity.AppUser;

/**
 * @author Administrator
 * 
 * Hibernate.initialize()�������session�رգ�fetch=lazy�ӳټ��ص�����
 * ��user�У�userprofileΪ�ӳټ��ض���
 *
 */
@Repository("appUserDao")
public class UserDaoImpl extends AbstractDao<Integer, AppUser> implements UserDao {

	public AppUser findById(int id) {
		AppUser user = getByKey(id);
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	public AppUser findBySSO(String sso) {
		System.out.println("SSO : " + sso);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));// ���Լ������
		AppUser user = (AppUser) crit.uniqueResult();
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());//������Session��Χ����ʾ��ʼ��������ʵ��
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<AppUser> findAllUsers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// ȥ���ظ�����
		List<AppUser> users = (List<AppUser>) criteria.list();

		// No need to fetch userProfiles since we are not showing them on list
		// page. Let them lazy load.
		// Uncomment below lines for eagerly fetching of userProfiles if you
		// want.
		// ���б�ҳ�в���Ҫ��ʾuserprofile��Ϣ�����Կɲ���fetch lazy����
		/*
		 * for(User user : users){ Hibernate.initialize(user.getUserProfiles());
		 * }
		 */
		return users;
	}

	public void save(AppUser user) {
		persist(user);
	}

	public void deleteBySSO(String sso) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		AppUser user = (AppUser) crit.uniqueResult();
		delete(user);
	}
}
