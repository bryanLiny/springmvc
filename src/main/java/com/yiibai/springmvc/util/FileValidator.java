package com.yiibai.springmvc.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.yiibai.springmvc.entity.FileBucket;

/**
 * @author Administrator 
 * 文件验证 以验证用户确实选择了要上传的文件
 *
 */
@Component
public class FileValidator implements Validator{

	public boolean supports(Class<?> clazz) {
		return FileBucket.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		FileBucket file = (FileBucket) obj;

		if (file.getFile() != null) {
			if (file.getFile().getSize() == 0) {
				errors.rejectValue("file", "missing.file");
			}
		}
	}
}
