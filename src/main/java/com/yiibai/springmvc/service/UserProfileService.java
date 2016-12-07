package com.yiibai.springmvc.service;

import java.util.List;

import com.yiibai.springmvc.entity.UserProfile;

public interface UserProfileService {

	UserProfile findById(int id);

	UserProfile findByType(String type);

	List<UserProfile> findAll();
}
