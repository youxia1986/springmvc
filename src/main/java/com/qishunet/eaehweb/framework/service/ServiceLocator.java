package com.qishunet.eaehweb.framework.service;

import org.springframework.beans.factory.BeanFactory;

import com.qishunet.eaehweb.framework.servlet.SpringServlet;
public class ServiceLocator {
	private static ServiceLocator serviceLocator ;

    /**
     * Creates a new ServiceLocator object.
     */
    private ServiceLocator() {

    }

    public static ServiceLocator getInstance() {
		if (serviceLocator == null) {
			serviceLocator = new ServiceLocator();
		}
		return serviceLocator;
	}

    private BeanFactory getFactory() {
    	return SpringServlet.getBeanFactory();
    }
     
    public Object getService(final String service) {
        return getFactory().getBean(service);
    }
}
