package id.co.rwg.tools.loganalyzer.batch;



import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

/**
 * extract log4j logger file
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 */
public class ExtractLog4jLogFileParameter extends BaseBatchParameter{
	
	
	
	
	
	static final String FILE_PATH_PREFIX ="filePath" ; 
	
	static final String FILE_COUNT_KEY ="filePathCount" ; 
	
 
	
	
	
	/**
	 * path. kalau file yang di scan ada banyak file
	 */
	private String []filePaths ; 
	
	
	
	
	/**
	 * nama system yang di log
	 */
	private String systemCode ; 
	
	
	
	/**
	 * id header. ini refer ke table : log_analize_heder
	 */
	private Long headerId ; 
	
	
	@Override
	public void generateBatchParameter(JobParametersBuilder builder) {
		
		 builder.addString("system", systemCode) ;
		 builder.addLong("headerId", headerId); 
		  
		 if ( filePaths!= null && filePaths.length>0) {
			 int cnt =filePaths.length ; 
			 builder.addLong(   FILE_COUNT_KEY  , new Long(cnt)) ; 
			 for ( int i= 0 ; i< cnt ;i++) {
				 builder.addString(FILE_PATH_PREFIX +i, filePaths[i]); 
			 }
		 }else{
			 builder.addLong(   FILE_COUNT_KEY  , 0L) ;
		 }
	}
	
 	
	@Override
	public void fetchFromJobParameters(JobParameters parameters) {
	 	this.headerId = parameters.getLong("headerId");  
	 	this.systemCode = parameters.getString("system"); 
	 	int cnt =  parameters.getLong(FILE_COUNT_KEY).intValue();
	 	if ( cnt>0) {
	 		this.filePaths = new String[cnt]; 
	 		for ( int i = 0 ; i < cnt ;i++) {
		 		this.filePaths[i] = parameters.getString(FILE_PATH_PREFIX +i); 
		 	}
	 	}
	 	
		
	}
	
	/**
	 * nama system yang di log
	 */
	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}
	/**
	 * nama system yang di log
	 */
	public String getSystemCode() {
		return systemCode;
	}
	
	
	
	
	/**
	 * path. kalau file yang di scan ada banyak file
	 */
	public void setFilePaths(String[] filePaths) {
		this.filePaths = filePaths;
	}
	/**
	 * path. kalau file yang di scan ada banyak file
	 */
	public String[] getFilePaths() {
		return filePaths;
	}
	
	/**
	 * id header. ini refer ke table : log_analize_heder
	 */
	public Long getHeaderId() {
		return headerId;
	}
	/**
	 * id header. ini refer ke table : log_analize_heder
	 */
	public void setHeaderId(Long headerId) {
		this.headerId = headerId;
	}

}
