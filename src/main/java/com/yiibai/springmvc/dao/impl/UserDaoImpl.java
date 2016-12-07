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
 * Hibernate.initialize()方法解决session关闭，fetch=lazy延迟加载的问题
 * 在user中，userprofile为延迟加载对象
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
		crit.add(Restrictions.eq("ssoId", sso));// 添加约束条件
		AppUser user = (AppUser) crit.uniqueResult();
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());//用于在Session范围内显示初始化代理类实例
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<AppUser> findAllUsers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// 去除重复数据
		List<AppUser> users = (List<AppUser>) criteria.list();

		// No need to fetch userProfiles since we are not showing them on list
		// page. Let them lazy load.
		// Uncomment below lines for eagerly fetching of userProfiles if you
		// want.
		// 在列表页中不需要显示userprofile信息，所以可不需fetch lazy数据
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
