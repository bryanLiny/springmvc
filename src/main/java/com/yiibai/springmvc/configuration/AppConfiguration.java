package com.yiibai.springmvc.configuration;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.yiibai.springmvc.converter.RoleToUserProfileConverter;
import com.yiibai.springmvc.interceptor.HandshakeInterceptor;
import com.yiibai.springmvc.websocket.MyWebSocketHandler;

@Configuration
@EnableWebMvc
@EnableWebSocket
@ComponentScan(basePackages = "com.yiibai.springmvc")
public class AppConfiguration extends WebMvcConfigurerAdapter implements WebSocketConfigurer{

	@Autowired
	RoleToUserProfileConverter roleToUserProfileConverter;
	
	@Resource  
    MyWebSocketHandler handler;

	/*
	 * 配置视图的寻找器
	 */
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	/*
	 * 配置 ResourceHandler 静态资源(如js/css等)
	 */

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}

	/*
	 * 配置消息包，以支持[国际化]消息属性文件
	 */

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}

	/**
	 * 注册转换器 
	 * Configure Converter to be used. In our example, we need a converter
	 * to convert string values[Roles] to UserProfiles in newUser.jsp
	 */
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(roleToUserProfileConverter);
	}

	/**
	 * Optional. It's only required when handling '.' in @PathVariables which
	 * otherwise ignore everything after last '.' in
	 * 
	 * @PathVaidables argument. It's a known bug in Spring
	 *                [https://jira.spring.io/browse/SPR-6164], still present in
	 *                Spring 4.1.7. This is a workaround for this issue.
	 */
	@Override
	public void configurePathMatch(PathMatchConfigurer matcher) {
		matcher.setUseRegisteredSuffixPatternMatch(true);
	}
	
	/**
	 * 配置StandardServletMultipartResolver
	 * 一个标准实现MultipartResolver接口，
	 * 基于Servlet3.0 javax.servlet.http.Part API
	 * @return
	 */
	@Bean(name = "multipartResolver")
	public StandardServletMultipartResolver resolver() {
		return new StandardServletMultipartResolver();
	}

	/* 注册websocket消息推送
	 * 需要监听的地址列表
	 * registry.addHandler加入
	 * (non-Javadoc)
	 * @see org.springframework.web.socket.config.annotation.WebSocketConfigurer#registerWebSocketHandlers(org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry)
	 */
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		
		registry.addHandler(handler, "/wsMy").addInterceptors(new HandshakeInterceptor());  
        registry.addHandler(handler, "/wsMy/sockjs").addInterceptors(new HandshakeInterceptor()).withSockJS(); 
		
	}
}
