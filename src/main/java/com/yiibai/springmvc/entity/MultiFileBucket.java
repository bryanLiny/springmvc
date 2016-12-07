package com.yiibai.springmvc.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator multiple包装类
 *
 */
public class MultiFileBucket {

	List<FileBucket> files = new ArrayList<FileBucket>();

	/**
	 * 这个类可以处理多达3个文件上传
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
