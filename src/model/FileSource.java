package model;

/**
 * @author Giovanni Buscarino (giovybus) Copyright (c) 2015 <br>
 * <b>Email:</b> giovanni.buscarino[at]gmail.com<br>
 *
 * created on 25/apr/2015 10:38:40
 */
public class FileSource {
	
	/**
	 * id of foreign key
	 */
	private int idCounting;
	
	/**
	 * the absolute path of the file
	 * memorized in the project dir
	 */
	private String absolutePath;
	
	/**
	 * the absolute path of the file
	 * but this absolute path is the 
	 * reference to the backup of this file
	 */
	private String absolutePathBackup;
	
	/**
	 * the number of rows 
	 * of the file
	 */
	private long rowsCounting;
	
	/**
	 * 
	 */
	public FileSource(int idCounting, String absolutePath, String absolutePathBackup, long rowsCounting) {
		this.idCounting = idCounting;
		this.absolutePath = absolutePath;
		this.absolutePathBackup = absolutePathBackup;
		this.rowsCounting = rowsCounting;
	}
	
	/**
	 * @param idCounting the idCounting to set
	 */
	public void setIdCounting(int idCounting) {
		this.idCounting = idCounting;
	}
	
	/**
	 * @return the idCounting
	 */
	public int getIdCounting() {
		return idCounting;
	}
	
	/**
	 * @param absolutePath the absolutePath to set
	 */
	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}
	
	/**
	 * @return the absolutePath
	 */
	public String getAbsolutePath() {
		return absolutePath;
	}
	
	/**
	 * @param absolutePathBackup the absolutePathBackup to set
	 */
	public void setAbsolutePathBackup(String absolutePathBackup) {
		this.absolutePathBackup = absolutePathBackup;
	}
	
	/**
	 * @return the absolutePathBackup
	 */
	public String getAbsolutePathBackup() {
		return absolutePathBackup;
	}
	
	/**
	 * @return the rowsCounting
	 */
	public long getRowsCounting() {
		return rowsCounting;
	}
	
	/**
	 * @param rowsCounting the rowsCounting to set
	 */
	public void setRowsCounting(long rowsCounting) {
		this.rowsCounting = rowsCounting;
	}

	/**
	 * @return
	 */
	public Object[] getObject() {
		return new Object []{
				this.absolutePath,
				this.rowsCounting
		};
	}
}
