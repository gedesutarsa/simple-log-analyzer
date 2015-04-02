package id.co.rwg.tools.loganalyzer;

import java.util.ArrayList;

import id.co.rwg.tools.loganalyzer.batch.ExtractLog4jLogFileParameter;
import id.co.rwg.tools.service.IExtractLogFileService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath*:/META-INF/spring/applicationContext-batch-infrastructure.xml"  ,
		"classpath*:/META-INF/spring/applicationContext-batch-parse-log.xml" ,
		"classpath*:/META-INF/spring/application-context-batch-db-test.xml"
})
public class AbsorbLog4jTest {
	
	
	@Autowired
	IExtractLogFileService extractLogFileService  ;
	
	
	@Test
	public void launchExtractLogFile () throws Exception {
		ArrayList<String> files = new ArrayList<>() ; 
		files.add("/Users/gedesutarsa/Documents/projects/danamon-mpn/wokspaces/ws-svn/simple-log-analyzer/src/main/resources/sample.log") ; 
		
		
		
		extractLogFileService.extractLogFile(files, "tes"); 
		
		 
	}
}
