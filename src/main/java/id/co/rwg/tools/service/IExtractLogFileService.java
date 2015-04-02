package id.co.rwg.tools.service;

import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

/**
 * interface untuk extract log dari log4j file log
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 */
public interface IExtractLogFileService {
	
	
	
	
	/**
	 * extract file log4j 
	 * @param fileNames list of file absolute path 
	 */
	public Long extractLogFile ( Collection<String> fileNames , String systemCode ) throws Exception ; 

}
