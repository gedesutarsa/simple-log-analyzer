package id.co.rwg.tools.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * log line
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 */
@Entity
@Table(name="log_line"  , indexes={
		@Index(columnList= "system_code,logger_priority" , name="idx_logline_logged_system" ) 
		
		
})
public class LogLine implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -401703545286871341L;
	
	
	
	




	/**
	 * primary key dari data <br/>
	 * column : log_id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="log_id")
	private Long id  ;
	
	
	
	
	
	/**
	 * column : system_code
	 * system yang di log
	 */
	@Column(name="system_code" , length=32)
	private String systemCode ; 
	
	
	
	/**
	 * waktu data di log
	 * column : log_date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="log_date")
	private Date logDate;
	
	
	
	
	
	/**
	 * nama thread yang menulis data<br/>
	 * column : thread_name
	 */
	@Column(name="thread_name" , length=64)
	private String threadName ; 
	
	
	
	
	/**
	 * nama priority dari log : ERROR,DEBUG,TRACE,INFO<br/>
	 * column : logger_priority
	 */
	@Column(name="logger_priority", length=16)
	private String loggerPriority; 
	
	
	
	
	/**
	 * fqcn dari class yang membuat masalah 
	 * column : code_fqcn
	 */
	@Column(name="code_fqcn" , length=512)
	private String codeFqcn ; 
	
	/**
	 * column : code_line_no
	 * line yang mengeluarkan log message
	 */
	@Column(name="code_line_no")
	private Integer codeLinePosition ; 
	
	
	/**
	 * method yang errorr, 
	 * column :  code_method
	 */
	@Column(name="code_method" , length=128)
	private String codeMethod ; 
	
	
	
	/**
	 * message error, ini biasanya sebelum stack trace
	 *  column : err_message
	 */
	@Lob
	@Column(name="err_message" )
	private String message ; 
	
	
	
	
	
	/**
	 * stack trace dari error. full blown trace
	 * column : err_stack_trace
	 */
	@Lob
	@Column(name="err_stack_trace" )
	private String stackTrace ; 
	
	/**
	 * fqcn dari class yang membuat masalah 
	 * column : code_fqcn
	 */
	public void setCodeFqcn(String codeFqcn) {
		this.codeFqcn = codeFqcn;
	}
	/**
	 * fqcn dari class yang membuat masalah 
	 * column : code_fqcn
	 */
	public String getCodeFqcn() {
		return codeFqcn;
	}

	
	/**
	 * primary key dari data <br/>
	 * column : log_id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * primary key dari data <br/>
	 * column : log_id
	 */
	public Long getId() {
		return id;
	}
	
	
	
	/**
	 * waktu data di log
	 * column : log_date
	 */
	public Date getLogDate() {
		return logDate;
	}
	/**
	 * waktu data di log
	 * column : log_date
	 */
	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}
	

	
	/**
	 * nama thread yang menulis data<br/>
	 * column : thread_name
	 */
	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}
	/**
	 * nama thread yang menulis data<br/>
	 * column : thread_name
	 */
	public String getThreadName() {
		return threadName;
	}
	
	/**
	 * nama priority dari log : ERROR,DEBUG,TRACE,INFO<br/>
	 * column : logger_priority
	 */
	public String getLoggerPriority() {
		return loggerPriority;
	}
	/**
	 * nama priority dari log : ERROR,DEBUG,TRACE,INFO<br/>
	 * column : logger_priority
	 */
	public void setLoggerPriority(String loggerPriority) {
		this.loggerPriority = loggerPriority;
	}
	
	
	
	
	/**
	 * column : system_code
	 * system yang di log
	 */
	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}
	/**
	 * column : system_code
	 * system yang di log
	 */
	public String getSystemCode() {
		return systemCode;
	}
	
	
	/**
	 * method yang errorr, 
	 * column :  code_method
	 */
	public void setCodeMethod(String codeMethod) {
		this.codeMethod = codeMethod;
	}
	/**
	 * method yang errorr, 
	 * column :  code_method
	 */
	public String getCodeMethod() {
		return codeMethod;
	}
	
	/**
	 * column : code_line_no
	 * line yang mengeluarkan log message
	 */
	public Integer getCodeLinePosition() {
		return codeLinePosition;
	}
	/**
	 * column : code_line_no
	 * line yang mengeluarkan log message
	 */
	public void setCodeLinePosition(Integer codeLinePosition) {
		this.codeLinePosition = codeLinePosition;
	}

	
	/**
	 * message error, ini biasanya sebelum stack trace
	 *  column : err_message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * message error, ini biasanya sebelum stack trace
	 *  column : err_message
	 */
	public String getMessage() {
		return message;
	}
	
	
	/**
	 * stack trace dari error. full blown trace
	 * column : err_stack_trace
	 */
	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}
	/**
	 * stack trace dari error. full blown trace
	 * column : err_stack_trace
	 */
	public String getStackTrace() {
		return stackTrace;
	}
}
