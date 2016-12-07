package com.yiibai.springmvc.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.yiibai.springmvc.entity.AppUser;
import com.yiibai.springmvc.entity.UserProfile;
import com.yiibai.springmvc.service.AppUserService;
import com.yiibai.springmvc.service.UserProfileService;

@Controller
@RequestMapping("/user")
@SessionAttributes("roles")
public class AppUserController {

	@Autowired
	AppUserService appUserService;

	@Autowired
	UserProfileService userProfileService;

	@Autowired
	MessageSource messageSource;

	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {

		List<AppUser> users = appUserService.findAllUsers();
		model.addAttribute("users", users);
		return "userslist";
	}

	/**
	 * ��ȡע����棬����{}��user����
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		AppUser user = new AppUser();
		model.addAttribute("appUser", user);
		model.addAttribute("edit", false);
		return "registration2";
	}

	/**
	 * ����user���ݣ���������
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
	public String saveUser(@Valid AppUser user, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "registration2";
		}

		/*
		 * ȷ��user������ssoid����Ψһ�� ��ȡmessage.properties�ļ��е�Ψһ����ʾ��Ϣ �����Ψһ����ע����棬��������ʾ
		 */
		if (!appUserService.isUserSSOUnique(user.getId(), user.getSsoId())) {
			FieldError ssoError = new FieldError("user", "ssoId", messageSource.getMessage("non.unique.ssoId",
					new String[] { user.getSsoId() }, Locale.getDefault()));
			result.addError(ssoError);
			return "registration2";
		}

		appUserService.saveUser(user);

		model.addAttribute("success",
				"User " + user.getFirstName() + " " + user.getLastName() + " registered successfully");
		// return "success";
		return "registrationsuccess";
	}

	/**
	 * ��ȡ�༭����
	 * 
	 * @param ssoId
	 * @param model
	 * @return ���ر༭����
	 */
	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String ssoId, ModelMap model) {
		AppUser user = appUserService.findBySSO(ssoId);
		model.addAttribute("appUser", user);
		model.addAttribute("edit", true);
		return "registration2";
	}

	/**
	 * ����user����
	 * 
	 * @param user
	 * @param result
	 * @param model
	 * @param ssoId
	 * @return
	 */
	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.POST)
	public String updateUser(@Valid AppUser user, BindingResult result, ModelMap model, @PathVariable String ssoId) {

		if (result.hasErrors()) {
			return "registration2";
		}

		/*
		 * Uncomment below 'if block' if you WANT TO ALLOW UPDATING SSO_ID in
		 * UI which is a unique key to a User.
		 * if (!appUserService.isUserSSOUnique(user.getId(), user.getSsoId())) {
			FieldError ssoError = new FieldError("user", "ssoId", messageSource.getMessage("non.unique.ssoId",
					new String[] { user.getSsoId() }, Locale.getDefault()));
			result.addError(ssoError);
			return "registration";
		}
		 */

		appUserService.updateUser(user);

		model.addAttribute("success",
				"User " + user.getFirstName() + " " + user.getLastName() + " updated successfully");
		return "registrationsuccess";
	}

	
	/**
	 * ɾ��user
	 * @param ssoId
	 * @return �ض����б�ҳ
	 */
	@RequestMapping(value = { "/delete-user-{ssoId}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String ssoId) {
		appUserService.deleteUserBySSO(ssoId);
		return "redirect:/user/list";
	}

	/**
	 * ��ʼ��������������
	 */
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
	}
}
