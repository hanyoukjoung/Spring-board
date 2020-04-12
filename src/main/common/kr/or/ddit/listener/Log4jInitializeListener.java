package kr.or.ddit.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import kr.or.ddit.utiles.Log4jInitializeClazz;

public class Log4jInitializeListener implements
		ServletContextAttributeListener, ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("ServletContext(application) GC전에 전파되는 이벤트의 청취자");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("ServletContext(application) 최초 인스턴스화 직후 전파되는 이벤트의 청취자");
		Log4jInitializeClazz.init();
	}

	@Override
	public void attributeAdded(ServletContextAttributeEvent event) {
		System.out.println("ServletContext(applicaition).setAttribute(키, 값); 저장시마다 전파되는 이벤트 청취자");
		
		ServletContext application = event.getServletContext();
		
		// application.setAttribute(키,값)
		String key = event.getName();
		Object value = event.getValue();
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent arg0) {
		System.out.println("ServletContext(applicaition).removeAttribute(키); 삭제시마다 전파되는 이벤트 청취자");
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent arg0) {
		System.out.println("ServletContext(applicaition).setAttribute(동일키, 상이값); 갱신시마다 전파되는 이벤트 청취자");
	}

}




