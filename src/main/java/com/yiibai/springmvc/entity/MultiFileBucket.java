package com.yiibai.springmvc.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator multiple��װ��
 *
 */
public class MultiFileBucket {

	List<FileBucket> files = new ArrayList<FileBucket>();

	/**
	 * �������Դ�����3���ļ��ϴ�
	 */
	public MultiFileBucket() {
		files.add(new FileBucket());
		files.add(new FileBucket());
		files.add(new FileBucket());
	}

	public List<FileBucket> getFiles() {
		return files;
	}

	public void setFiles(List<FileBucket> files) {
		this.files = files;
	}
}
