package com.qishunet.eaehweb.framework.servlet;

import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringServlet extends HttpServlet {
	private static final long serialVersionUID = 2986211859224866225L;
	private static Logger log = Logger.getLogger(SpringServlet.class);
	private static WebApplicationContext ctx;
	@Override
	public void init(){
		//启用Spring上下文容器
		log.info("初始化Spring Servlet------------------>>>");
		initBeanFactory();
	}

	@Override
	public void destroy(){
		super.destroy();
	}

	public void initBeanFactory(){
		if(ctx == null) {
			ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		}
	}

	public static WebApplicationContext getBeanFactory(){
		String names[]=ctx.getBeanDefinitionNames();
		if(names!=null){
			//log.info("====name:"+names);
		}
		return ctx;
	}
}
