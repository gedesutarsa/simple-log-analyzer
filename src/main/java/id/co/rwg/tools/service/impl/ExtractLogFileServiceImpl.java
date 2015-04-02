package id.co.rwg.tools.service.impl;

import java.util.Collection;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import id.co.rwg.tools.dao.IGeneralPurposeDao;
import id.co.rwg.tools.loganalyzer.batch.ExtractLog4jLogFileParameter;
import id.co.rwg.tools.loganalyzer.domain.ExtractLogHeader;
import id.co.rwg.tools.service.IExtractLogFileService;

/**
 * worker untuk extract file log
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 */
public class ExtractLogFileServiceImpl implements IExtractLogFileService{
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(ExtractLogFileServiceImpl.class.getName()); 
	@Autowired
	@Qualifier(value="general-purpose-dao")
	IGeneralPurposeDao generalPurposeDao ; 
	
	
	@Autowired
	@Qualifier(value="web-async-task-executor")
	TaskExecutor taskExecutor ; 
	
	@Autowired
	@Qualifier(value="transactionManager")
	PlatformTransactionManager transactionManager ; 
	
	
	@Autowired
	@Qualifier(value="batchEngineJobLauncher")
	private JobLauncher jobLauncher;
	
	
	@Autowired
	@Qualifier(value="bulk-absorb-un-compressed-log")
	Job extractLogJob  ; 
	

	@Override
	public Long extractLogFile(final Collection<String> fileNames, String systemCode)
			throws Exception {
		TransactionTemplate tmpl = new TransactionTemplate(transactionManager);
		final ExtractLogHeader h = new ExtractLogHeader(); 
		h.setStartTime(new Date());
		h.setSystemCode(systemCode);
		
		
		
		
		final Long swap = tmpl.execute(new TransactionCallback<Long>() {
			@Override
			public Long doInTransaction(TransactionStatus stts) {
				try {
					generalPurposeDao.insert(h); 
				} catch (Exception e) {
					stts.setRollbackOnly();
					logger.error("gagal menyimpan data header. error di laporkan : " + e.getMessage() , e);
					return null ; 
				}
				return h.getId() ; 
			}
		});
		
		
		if ( swap != null ) {
			
			
			taskExecutor.execute(new Runnable() {
				
				@Override
				public void run() {
					logger.debug("hore. ini start thread untuk run batch");
					ExtractLog4jLogFileParameter param  = new ExtractLog4jLogFileParameter(); 
					param.setHeaderId(swap);
					String[] arr = new String[fileNames.size()] ; 
					fileNames.toArray(arr); 
					param.setFilePaths(arr);
					try {
						jobLauncher.run(extractLogJob, param.translateToJobParameter());
					} catch (Exception e) {
						logger.debug("gagal start job. error : " + e.getMessage() , e);
					}
					
					
				}
			});
			 
			
		}
		return swap ; 
		
		
	}

}
