package id.co.rwg.tools.loganalyzer.batch.extractor;

import id.co.rwg.tools.loganalyzer.batch.ExtractLog4jLogFileParameter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

/**
 * 
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 */
public class LogFileReader implements ItemReader<String>{
	
	private static Logger logger = LoggerFactory.getLogger(clazz)
	
	/**
	 * token pendanda start of logging
	 */
	private String startToken = "[start-of-log]"; 
	
	
	/**
	 * ini harus sama dengan panjang string {@link #startToken}
	 */
	private int lengthOfStartToken = "[start-of-log]".length() ; 
	
	private BufferedReader bufferedReader ; 
	
	 
	
	
	
	
	private StringBuffer latestReadedData = new StringBuffer();
	ExtractLog4jLogFileParameter jobParam  ; 
	int currentIndex = 0 ; 
	
	
	
	@BeforeStep
	public void beforeStep(StepExecution stepExecution) throws Exception {
		jobParam= new ExtractLog4jLogFileParameter()  ; 
		jobParam.fetchFromJobParameters(stepExecution.getJobParameters());
		openNextFile();
		
	}
	
	
	
	protected void openNextFile() throws Exception{
		if ( bufferedReader!= null){
			bufferedReader.close();
			bufferedReader = null ;  
		}
		if ( currentIndex> jobParam.getFilePaths().length)
			return ; 
		
		String filePath = jobParam.getFilePaths()[currentIndex];
		
		bufferedReader = new BufferedReader(new FileReader(filePath)); 
		currentIndex++ ; 
	} ; 
	
	@AfterStep
	public ExitStatus afterStep(StepExecution stepExecution) throws Exception{
		bufferedReader.close();
		return ExitStatus.COMPLETED ; 
	}
	
	
	 

	@Override
	public String read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
		if ( bufferedReader== null)
			return null ; 
		
	 	String  line = null ; 
		do {
			line  = bufferedReader.readLine() ; 
			if ( line== null) {// kalau ada next file baca
				if ( latestReadedData.length()>0){
					String retval =  latestReadedData.toString() ;
					latestReadedData = new StringBuffer(); 
					return retval ; 
				}
				if ( jobParam.getFilePaths().length> currentIndex) {
					openNextFile();
				}
				break ; 
			}
			latestReadedData.append(line);
			latestReadedData.append(System.lineSeparator());
			int idx =  latestReadedData.indexOf(startToken) ;
			if ( idx== -1)
				continue ; 
			String retval = latestReadedData.substring(0 , idx )   ; 
			int startNext = idx + lengthOfStartToken ;
			if ( startNext < latestReadedData.length()){
				String swap = latestReadedData.substring(startNext); 
				latestReadedData = new StringBuffer(swap); 
			}else{
				latestReadedData = new StringBuffer();
			}
			if ( retval.isEmpty())
				continue ; 
			return retval  ;
			
		}while ( line!= null); 
		return null;
	}
	
	
	
	
	

}
