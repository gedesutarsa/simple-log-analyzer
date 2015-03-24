package id.co.rwg.tools.batch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

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
	
	
	
	/**
	 * token pendanda end of log
	 */
	private String endToken = "[end-log]"; 
	
	
	/**
	 * ini harus sama dengan panjang string {@link #endToken}
	 */
	private int lengthOfEndToken = 9 ; 
	
	private BufferedReader bufferedReader ; 
	
	
	/**
	 * path dari log file yang di baca
	 */
	private String logFilePath ; 
	private StringBuffer latestReadedData = new StringBuffer();
	
	
	
	
	@BeforeStep
	public void beforeStep(StepExecution stepExecution) throws Exception {
		bufferedReader = new BufferedReader( new FileReader(new File(logFilePath))); 
	}
	
	@AfterStep
	public ExitStatus afterStep(StepExecution stepExecution) throws Exception{
		bufferedReader.close();
		return ExitStatus.COMPLETED ; 
	}
	
	
	 

	@Override
	public String read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
		
	 	String  line = null ; 
		do {
			line  = bufferedReader.readLine() ; 
			if ( line== null) {
				break ; 
			}
			latestReadedData.append(line); 
			int idx =  latestReadedData.indexOf(endToken) ;
			if ( idx== -1)
				continue ; 
			String retval = latestReadedData.substring(0 , idx )   ; 
			int startNext = idx + lengthOfEndToken ;
			if ( startNext < latestReadedData.length()){
				String swap = latestReadedData.substring(startNext); 
				latestReadedData = new StringBuffer(swap); 
			}else{
				latestReadedData = new StringBuffer();
			}
			return retval  ;
			
		}while ( line!= null); 
		return latestReadedData.toString();
	}
	
	
	
	
	public static void main(String[] args) {
		StringBuffer test = new StringBuffer(); 
		test.append("sample[end-log]dodo"); 
		int idx =  test.indexOf("[end-log]");
		System.out.println(  test.subSequence(0, idx));
		if ( idx + 9 < test.length()){
			System.out.println(test.substring(idx+9));
		}else {
			System.out.println("maaf habis");
		}
	}
	

}
