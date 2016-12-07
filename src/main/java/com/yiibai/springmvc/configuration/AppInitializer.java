package com.yiibai.springmvc.configuration;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/*
 * ��ʼ������
 * */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {

		return new Class[] { AppConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {

		return null;
	}

	@Override
	protected String[] getServletMappings() {

		return new String[] { "/" };
	}

	/*
	 * ������� (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.support.
	 * AbstractDispatcherServletInitializer#getServletFilters()
	 */
	@Override
	protected Filter[] getServletFilters() {
		Filter[] singleton = { new CORSFilter() };
		return singleton;
	}

	/*
	 * �û��ļ��ϴ� ע������� MultiPartConfigElement �� DispatcherServlet (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.support.
	 * AbstractDispatcherServletInitializer#customizeRegistration(javax.servlet.
	 * ServletRegistration.Dynamic)
	 */
	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		registration.setMultipartConfig(getMultipartConfigElement());
	}

	private MultipartConfigElement getMultipartConfigElement() {
		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(LOCATION, MAX_FILE_SIZE,
				MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
		return multipartConfigElement;
	}

	private static final String LOCATION = "D:/mytemp/"; // �ϴ�·��

	private static final long MAX_FILE_SIZE = 5242880; // 5MB : �ϴ��ļ�����С
	private static final long MAX_REQUEST_SIZE = 20971520; // 20MB : ��������С

	private static final int FILE_SIZE_THRESHOLD = 0; //

}
