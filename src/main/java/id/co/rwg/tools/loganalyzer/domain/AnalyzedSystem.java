package id.co.rwg.tools.loganalyzer.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * system yang di analyze
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 */
@Entity
@Table(name="log_system")
public class AnalyzedSystem implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5737988154185123375L;



	/**
	 * kode system
	 * column : sys_code
	 */
	@Id
	@Column(name="sys_code")
	private String code ; 
	
	
	
	
	/**
	 * method-method yang di watch. ini untuk memfilter class apa saja error yang yang di hilight. ini untuk memudahkan batch membaca data. method apa saja yang di perlu di pastikan perlu di track statistik error nya
	 */
	@Column(name="watched_packages")
	private String watchedPackages ; 
	
	
	
	/**
	 * nama system yang bermasalah 
	 * column : sys_name
	 */
	@Column(name="sys_name")
	private String name ; 
	
	
	/**
	 * nama system yang bermasalah 
	 * column : sys_name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * nama system yang bermasalah 
	 * column : sys_name
	 */
	public String getName() {
		return name;
	}
	
	
	/**
	 * kode system
	 * column : sys_code
	 */	
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * kode system
	 * column : sys_code
	 */
	public String getCode() {
		return code;
	}

}
