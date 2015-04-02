package id.co.rwg.tools.loganalyzer.batch;

import java.util.UUID;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;



/**
 * batch parameter untuk 
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 */
public abstract class BaseBatchParameter {
	
	
	
	
	/**
	 * penanda agar batch batch bisa tetap ter esksekusi
	 */
	private String incrementerVariable  ; 
	
	
	
	/**
	 * transalate dari job param ke dalam object
	 */
	public void translateToJobParameter ( JobParameters parameters) {
		if ( incrementerVariable== null || incrementerVariable.isEmpty())
			incrementerVariable  = UUID.randomUUID().toString(); 
		this.incrementerVariable = parameters.getString("uuid"); 
		fetchFromJobParameters(parameters);
	}
	
	/**
	 * konversi ke dalam job parameter
	 */
	public JobParameters translateToJobParameter ( ) {
		JobParametersBuilder bld = new JobParametersBuilder() ; 
		bld.addString("uuid", incrementerVariable); 
		generateBatchParameter(bld);
		return bld.toJobParameters(); 
	}
	
	
	
	/**
	 * generate batch parameter
	 */
	public abstract void generateBatchParameter (JobParametersBuilder builder )  ; 
	
	
	
	/**
	 * worker untuk mengambil data dari job parameters
	 */
	public abstract void fetchFromJobParameters (JobParameters parameters  )  ;
	
	
	
	
	

}
