package id.co.rwg.tools.dao;

/**
 * dao untuk akses data
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 */
public interface IGeneralPurposeDao {
	
	
	
	
	/**
	 * insert data
	 */
	public <DATA> DATA insert( DATA data) throws Exception ;
	
	
	
	/**
	 * update data
	 */
	public <DATA> void update ( DATA data ) throws Exception ;
	
	
	/**
	 * hapus data
	 */
	public <DATA> void delete ( DATA data ) throws Exception ;
	
	
	
	

}
