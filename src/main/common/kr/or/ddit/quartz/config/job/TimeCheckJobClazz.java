package kr.or.ddit.quartz.config.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class TimeCheckJobClazz extends QuartzJobBean {

	//스케줄러에 의해서 콜백되는 매서드 
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		System.out.println("timecheckjob:"+new SimpleDateFormat("yyyy/MM/dd hh24:mm:ss").format(new Date()));
		
	}

}
