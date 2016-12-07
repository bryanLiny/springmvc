package com.yiibai.springmvc.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.yiibai.springmvc.entity.UserProfile;
import com.yiibai.springmvc.service.UserProfileService;

/**
 * @author Administrator 
 * ����Ҫӳ�䵥�� userProfile ��ID��ʵ�ʵ� UserProfile ʵ�������ݿ���
 * source --- Object
 * target --- UserProfile
 */
@Component
public class RoleToUserProfileConverter implements Converter<Object, UserProfile> {

	@Autowired
	UserProfileService userProfileService;

	/**
	 * ��objectת��ΪUserProfile
	 */
	public UserProfile convert(Object element) {
		Integer id = Integer.parseInt((String) element);
		UserProfile profile = userProfileService.findById(id);
		System.out.println("Profile : " + profile);
		return profile;
	}
}
