package id.co.rwg.tools.loganalyzer.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * ini method yang di 
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 */
@Table(name="log_analized_method" , uniqueConstraints= {
		@UniqueConstraint(columnNames={"analyzed_fqcn","analyzed_method"} , name="unq_analyzed_mth_fqcn_mthd")
})
@Entity
public class AnalizedMethod implements Serializable{
	
	
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7922207040353863052L;



	/**
	 * id internal data. hash -- &lt; fqcn + # + method
	 * column : pk 
	 */
	@Id
	@Column(name="pk")
	private String id; 
	
	
	
	/**
	 * FQCN dari data
	 * column : fqcn
	 */
	@Column(name="analyzed_fqcn", length=512)
	private String fqcn ; 
	
	
	
	/**
	 * method yang di analyze
	 * column : analyzed_method
	 */
	@Column(name="analyzed_method" , length=512)
	private String method ; 

	
	
	/**
	 * id internal data. hash -- &lt; fqcn + # + method
	 * column : pk 
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * id internal data. hash -- &lt; fqcn + # + method
	 * column : pk 
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * FQCN dari data
	 * column : fqcn
	 */
	public String getFqcn() {
		return fqcn;
	}
	/**
	 * FQCN dari data
	 * column : fqcn
	 */
	public void setFqcn(String fqcn) {
		this.fqcn = fqcn;
	}
	
	/**
	 * method yang di analyze
	 * column : analyzed_method
	 */
	public String getMethod() {
		return method;
	}
	
	/**
	 * method yang di analyze
	 * column : analyzed_method
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	

}
