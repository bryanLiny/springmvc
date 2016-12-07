package com.yiibai.springmvc.entity;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Administrator
 * 一个 multipart 请求获得上传文件的表示
 * 检索上传文件工具类
 *
 */
public class FileBucket {

	@NotNull
	MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
}
