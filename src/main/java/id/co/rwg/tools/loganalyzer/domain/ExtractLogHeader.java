package id.co.rwg.tools.loganalyzer.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * header table untuk log analizer
 * table name : log_analize_heder
 * 
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 */
@Entity
@Table(name="log_analize_heder")
public class ExtractLogHeader implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1453733194992334649L;
	/**
	 * primary key dari data <br/>
	 * column : pk 
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="pk")
	private Long id ;
	
	
	
	/**
	 * kode system yang di track
	 * column : system_code
	 */
	@Column(name="system_code" , length=32)
	private String systemCode ; 
	
	
	
	
	/**
	 * start time 
	 * column : start_time
	 */
	@Column(name="start_time")
	private Date startTime = new Date() ; 
	
	
	
	/**
	 * waktu berakhirnya batch 
	 * column : end_time
	 */
	@Column(name="end_time")
	private Date endTime ; 
	
	/**
	 * primary key dari data <br/>
	 * column : pk 
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * primary key dari data <br/>
	 * column : pk 
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * kode system yang di track
	 * column : system_code
	 */
	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}
	/**
	 * kode system yang di track
	 * column : system_code
	 */
	public String getSystemCode() {
		return systemCode;
	}
	
	
	/**
	 * start time 
	 * column : start_time
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * start time 
	 * column : start_time
	 */
	public Date getStartTime() {
		return startTime;
	}
	
	/**
	 * waktu berakhirnya batch 
	 * column : end_time
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * waktu berakhirnya batch 
	 * column : end_time
	 */
	public Date getEndTime() {
		return endTime;
	}

}
