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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yiibai.springmvc.entity.Employee;
import com.yiibai.springmvc.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class AppController {
	@Autowired
	EmployeeService service;

	@Autowired
	MessageSource messageSource;

	/*
	 * ��ȡԱ���б�
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listEmployees(ModelMap model) {

		List<Employee> employees = service.findAllEmployees();
		model.addAttribute("employees", employees);
		return "allemployees";
	}

	/*
	 * ����Ա��
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newEmployee(ModelMap model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		model.addAttribute("edit", false);
		return "registration";
	}

	/*
	 * ����Ա�� post
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveEmployee(@Valid Employee employee, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [ssn] should be
		 * implementing custom @Unique annotation and applying it on field [ssn]
		 * of Model class [Employee].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you
		 * can fill custom errors outside the validation framework as well while
		 * still using internationalized messages.
		 * 
		 */
		if (!service.isEmployeeSsnUnique(employee.getId(), employee.getSsn())) {
			FieldError ssnError = new FieldError("employee", "ssn", messageSource.getMessage("non.unique.ssn",
					new String[] { employee.getSsn() }, Locale.getDefault()));
			result.addError(ssnError);
			return "registration";
		}

		service.saveEmployee(employee);

		model.addAttribute("success", "Employee " + employee.getName() + " registered successfully");
		return "success2";
	}

	/*
	 * �༭Ա��
	 */
	@RequestMapping(value = { "/edit-{ssn}-employee" }, method = RequestMethod.GET)
	public String editEmployee(@PathVariable String ssn, ModelMap model) {
		Employee employee = service.findEmployeeBySsn(ssn);
		model.addAttribute("employee", employee);
		model.addAttribute("edit", true);
		return "registration";
	}

	/*
	 * �༭Ա��post
	 */
	@RequestMapping(value = { "/edit-{ssn}-employee" }, method = RequestMethod.POST)
	public String updateEmployee(@Valid Employee employee, BindingResult result, ModelMap model,
			@PathVariable String ssn) {

		if (result.hasErrors()) {
			return "registration";
		}

		if (!service.isEmployeeSsnUnique(employee.getId(), employee.getSsn())) {
			FieldError ssnError = new FieldError("employee", "ssn", messageSource.getMessage("non.unique.ssn",
					new String[] { employee.getSsn() }, Locale.getDefault()));
			result.addError(ssnError);
			return "registration";
		}

		service.updateEmployee(employee);

		model.addAttribute("success", "Employee " + employee.getName() + " updated successfully");
		return "success2";
	}

	/*
	 * ͨ��SSNɾ��Ա��
	 */
	/**
	 * @param ssn
	 *            Ψһ
	 * @return
	 */
	@RequestMapping(value = { "/delete-{ssn}-employee" }, method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable String ssn) {
		service.deleteEmployeeBySsn(ssn);
		return "redirect:/employees/list";
	}
}
