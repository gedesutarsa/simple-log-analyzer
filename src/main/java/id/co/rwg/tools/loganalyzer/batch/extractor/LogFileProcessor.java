package id.co.rwg.tools.loganalyzer.batch.extractor;


import java.text.SimpleDateFormat;

import id.co.rwg.tools.loganalyzer.batch.ExtractLog4jLogFileParameter;
import id.co.rwg.tools.loganalyzer.domain.LogLine;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;

/**
 * 
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 */
public class LogFileProcessor implements ItemProcessor<String, LogLine>{
	
	
	
	
	
	/**
	 * nama system yang di log
	 */
	private String systemName ; 
	/**
	 * marker mulai stack trace
	 */
	static final String MARKER_STACKTRACE = "[stack-trace]:" ; 
	
	//2015-03-18 10:12:30,031
	static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-mm-dd hh:MM:ss,SS");  
	
	
	
	/**
	 * marker message
	 */
	static final String MARKER_MESSAGE = "[message]:" ; 
	
	
	
	/**
	 * panjang dari {@link #MARKER_STACKTRACE}
	 */
	static final int LENGTH_OF_MARKER_STACKTRACE = MARKER_STACKTRACE.length() ;
	
	
	
	/**
	 * pangajang dari marker {@link #MARKER_MESSAGE}
	 */
	static final int LENGTH_OF_MARKER_MESSAGE = MARKER_MESSAGE.length() ;
	
	
	
	@BeforeStep
	public void beforeStep(StepExecution stepExecution) throws Exception {
		ExtractLog4jLogFileParameter param = new ExtractLog4jLogFileParameter(); 
		param.fetchFromJobParameters(stepExecution.getJobParameters());
		this.systemName = param.getSystemCode() ; 
	}
	
	
	@Override
	public LogLine process(String rawLog) throws Exception {
		LogLine retval = new LogLine() ; 
		String msg = rawLog ; 
		
		int idxTrace  =  msg.indexOf(MARKER_STACKTRACE) ;
		int idxMessage = msg.indexOf(MARKER_MESSAGE); 
		
		String trace = msg.substring( idxTrace + LENGTH_OF_MARKER_STACKTRACE) ; 
		String logMsg  =msg.substring(idxMessage + LENGTH_OF_MARKER_MESSAGE , idxTrace);  
		String msgBeforeMessage  = msg.substring(0 , idxMessage) ;  
		
		int idxThreadStart = msgBeforeMessage.indexOf("[") ; 
		int idxThreadEnd = msg.indexOf("]") ; 
		
		String time = msgBeforeMessage.substring(0 , idxThreadStart); 
		String thread = msgBeforeMessage.substring(idxThreadStart +1 , idxThreadEnd); 
		String msgAfterThrad = msgBeforeMessage.substring(idxThreadEnd +1); 
		
		
		int idxPrioriytyStart = msgAfterThrad.indexOf("[") ; 
		int idxPrioriytyEnd = msgAfterThrad.indexOf("]") ;
		String methodName = msgAfterThrad.substring(idxPrioriytyEnd +1).trim(); 
		String logPriority = msgAfterThrad.substring(idxPrioriytyStart + 1 , idxPrioriytyEnd).trim(); 
		
		retval.setLoggerPriority(logPriority);
		try {
			retval.setLogDate(DATE_FORMATTER.parse(time.trim()));
		} catch (Exception e) {
			// TODO: handle exception
		}
		int indexKurungBuka = methodName.indexOf("(") ; 
		int indexKurungTutup = methodName.indexOf(")") ; 
		String methodTanpaLine = methodName.substring(0 , indexKurungBuka );
		String lineNumber = methodName.substring(indexKurungBuka +1, indexKurungTutup);
		int indexMethodName = methodTanpaLine.lastIndexOf(".") ; 
		String methodNameOnly = methodTanpaLine.substring(  indexMethodName  +1) ;
		String className = methodTanpaLine.substring(0 , indexMethodName ) ; 
		
		
		
		
		
		retval.setCodeFqcn(className);
		try {
			retval.setCodeLinePosition(Integer.parseInt(lineNumber));
		} catch (Exception e) {
			retval.setCodeLinePosition(-1);
		}
		
		retval.setCodeMethod(methodNameOnly);
		retval.setMessage(logMsg);
		retval.setStackTrace(trace);
		retval.setSystemCode(systemName);
		retval.setThreadName(thread);
		System.out.println(retval.toString());
		return retval;
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		String msg = "2015-03-18 10:12:30,031 [main] - [ INFO] org.springframework.web.context.ContextLoader.initWebApplicationContext(285) [message]:Root WebApplicationContext: initialization started[stack-trace]:Ini trace yah" ;
		/*
		int idxTrace  =  msg.indexOf(MARKER_STACKTRACE) ;
		int idxMessage = msg.indexOf(MARKER_MESSAGE); 
		
		String trace = msg.substring( idxTrace + LENGTH_OF_MARKER_STACKTRACE) ; 
		String logMsg  =msg.substring(idxMessage + LENGTH_OF_MARKER_MESSAGE , idxTrace);  
		String msgBeforeMessage  = msg.substring(0 , idxMessage) ;  
		
		int idxThreadStart = msgBeforeMessage.indexOf("[") ; 
		int idxThreadEnd = msg.indexOf("]") ; 
		
		String time = msgBeforeMessage.substring(0 , idxThreadStart); 
		String thread = msgBeforeMessage.substring(idxThreadStart +1 , idxThreadEnd); 
		String msgAfterThrad = msgBeforeMessage.substring(idxThreadEnd +1); 
		
		
		int idxPrioriytyStart = msgAfterThrad.indexOf("[") ; 
		int idxPrioriytyEnd = msgAfterThrad.indexOf("]") ;
		String methodName = msgAfterThrad.substring(idxPrioriytyEnd +1).trim(); 
		String logPriority = msgAfterThrad.substring(idxPrioriytyStart + 1 , idxPrioriytyEnd).trim(); 
		
		System.out.println(trace);
		System.out.println(logMsg);
		System.out.println("msgBeforeMessage:" + msgBeforeMessage);
		System.out.println("msgAfterThrad:" + msgAfterThrad);
		System.out.println("time : " + time);
		System.out.println("thread:" + thread);
		System.out.println("methodName:" + methodName);
		System.out.println("logPriority:" + logPriority);
		try {
			Date tgl = (Date) DATE_FORMATTER.parseObject(time.trim()) ;  
			System.out.println(tgl);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		int indexKurungBuka = methodName.indexOf("(") ; 
		int indexKurungTutup = methodName.indexOf(")") ; 
		String methodTanpaLine = methodName.substring(0 , indexKurungBuka ); 
		System.out.println("methodTanpaLine:" + methodTanpaLine);
		String lineNumber = methodName.substring(indexKurungBuka +1, indexKurungTutup); 
		System.out.println("lineNumber:" + lineNumber);
		int indexMethodName = methodTanpaLine.lastIndexOf(".") ; 
		String methodNameOnly = methodTanpaLine.substring(  indexMethodName  +1) ; 
		System.out.println(methodNameOnly);
		String className = methodTanpaLine.substring(0 , indexMethodName ) ; 
		System.out.println("className:" + className);*/
		
		LogFileProcessor proc = new LogFileProcessor() ; 
		try {
			System.out.println(  proc.process(msg).toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
				
	}

	/**
	 * nama system yang di log
	 */
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	/**
	 * nama system yang di log
	 */
	public String getSystemName() {
		return systemName;
	}
}
