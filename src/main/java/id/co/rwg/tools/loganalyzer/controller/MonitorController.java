package id.co.rwg.tools.loganalyzer.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 */
@Controller
public class MonitorController {
	
	
	Logger logger = LoggerFactory.getLogger(MonitorController.class.getName()); 
	
	@RequestMapping(value={"index.jsp"})
	public ModelAndView index () {
		logger.debug("tes debug");
		logger.info("tes info");
		logger.error("tes error");
		error1("asas");
		error2("kuda", 1);
		ModelAndView r = new ModelAndView(); 
		return r ; 
		
	}
	
	
	void error1( String sample1 ) {
		try {
			int i = 1/0 ;
			System.out.println(i);
		} catch (Exception e) {
			logger.error("ini devide by 0" , e);
		}
	}
	void error2( String sample1 , int angka) {
		try {
			Date date = null ; 
			date.after(new Date()); 
		} catch (Exception e) {
			logger.error("ini null pointer" , e);
		}
	}

}
