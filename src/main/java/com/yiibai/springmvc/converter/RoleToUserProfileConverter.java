package com.yiibai.springmvc.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.yiibai.springmvc.entity.UserProfile;
import com.yiibai.springmvc.service.UserProfileService;

/**
 * @author Administrator 
 * 是需要映射单个 userProfile 的ID在实际的 UserProfile 实体在数据库中
 * source --- Object
 * target --- UserProfile
 */
@Component
public class RoleToUserProfileConverter implements Converter<Object, UserProfile> {

	@Autowired
	UserProfileService userProfileService;

	/**
	 * 将object转化为UserProfile
	 */
	public UserProfile convert(Object element) {
		Integer id = Integer.parseInt((String) element);
		UserProfile profile = userProfileService.findById(id);
		System.out.println("Profile : " + profile);
		return profile;
	}
}
