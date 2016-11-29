/**
 * @Copyright :2014-2019 DATAROOT .co .Ltd. All Rights Reserved
 * @Title:WebExceptionHandler.java
 * @Date:2015.6.16
 * @author:zhangcheng
 * @Version:V1.0.0
 * ----------------------------------------------------------------------------
 *   Revise Date    Revise By      Description
 *
 */
package com.qishunet.eaehweb.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;



public class WebExceptionHandler implements HandlerExceptionResolver {

	/**
	 * logger
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(WebExceptionHandler.class);
	/**
	 * DEBUG MODE
	 */
	private boolean debug = false;

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse reponse, Object handler, Exception exception) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ex", exception.getClass().getSimpleName());
		
		
		model.put("errormsg", exception.getMessage());
		
		
		model.put("debug", debug);
		if (debug) {
			// 
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter(stringWriter);
			exception.printStackTrace(printWriter);
			model.put("stackTrace", stringWriter.getBuffer().toString());

			// 
			if (logger.isDebugEnabled()) {
				logger.debug(exception.getMessage(), exception);
			} else {
				// 
				logger.warn(exception.getMessage(), exception);
			}
		}
		return new ModelAndView("common/error", model);
	}


}