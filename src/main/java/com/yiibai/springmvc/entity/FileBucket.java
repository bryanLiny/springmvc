package com.yiibai.springmvc.entity;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Administrator
 * һ�� multipart �������ϴ��ļ��ı�ʾ
 * �����ϴ��ļ�������
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
