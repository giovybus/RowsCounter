package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Giovanni Buscarino (giovybus) Copyright (c) 2015 <br>
 * <b>Email:</b> giovanni.buscarino[at]gmail.com<br>
 *
 * created on 25/apr/2015 10:43:47
 */
public class Counting {
	
	/**
	 * personal id of counting
	 */
	private int id;
	
	/**
	 * the id of project, (database table)
	 * foreign key
	 */
	private int idProject;
	
	/**
	 * date counting 
	 */
	private Date dateCounting;
	
	/**
	 * the number of pakage founded 
	 * in your project
	 */
	private long numberOfPack; 
	
	/**
	 * the number of source files
	 */
	private long numberOfFiles;
	
	/**
	 * total number of rows 
	 */
	private long numberOfRows;
	
	/**
	 * list of extension
	 */
	private ExtensionToCounting extensions;
	
	/**
	 * a list of files, relative
	 * this counting
	 */
	private List<FileSource>files;
	
	/**
	 * constructor
	 */
	public Counting(int id, int idProject, String dateCounting, long numberOfPack, 
			long numberOfFiles, long numberOfRows, ExtensionToCounting extensions) {
		this.id = id;
		this.idProject = idProject;
		setDateString(dateCounting);
		this.numberOfFiles = numberOfFiles;
		this.numberOfPack = numberOfPack;
		this.numberOfRows = numberOfRows;
		this.extensions = extensions;
	}
	
	/**
	 * constructor
	 */
	public Counting(int idProject, Date dateCounting, long numberOfPack, 
			long numberOfFiles, long numberOfRows, ExtensionToCounting extensions) {
		this.idProject = idProject;
		this.dateCounting = dateCounting;
		this.numberOfFiles = numberOfFiles;
		this.numberOfPack = numberOfPack;
		this.numberOfRows = numberOfRows;
		this.extensions = extensions;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param idProject the idProject to set
	 */
	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}
	
	/**
	 * @return the idProject
	 */
	public int getIdProject() {
		return idProject;
	}
	
	/**
	 * @param dateCounting the dateCounting to set
	 */
	public void setDateCounting(Date dateCounting) {
		this.dateCounting = dateCounting;
	}
	
	/**
	 * @return the dateCounting
	 */
	public Date getDateCounting() {
		return dateCounting;
	}
	
	/**
	 * @param numberOfFiles the numberOfFiles to set
	 */
	public void setNumberOfFiles(long numberOfFiles) {
		this.numberOfFiles = numberOfFiles;
	}
	
	/**
	 * @return the numberOfFiles
	 */
	public long getNumberOfFiles() {
		return numberOfFiles;
	}
	
	/**
	 * @param numberOfPack the numberOfPack to set
	 */
	public void setNumberOfPack(long numberOfPack) {
		this.numberOfPack = numberOfPack;
	}
	
	/**
	 * @return the numberOfPack
	 */
	public long getNumberOfPack() {
		return numberOfPack;
	}
	
	/**
	 * @param numberOfRows the numberOfRows to set
	 */
	public void setNumberOfRows(long numberOfRows) {
		this.numberOfRows = numberOfRows;
	}
	
	/**
	 * @return the numberOfRows
	 */
	public long getNumberOfRows() {
		return numberOfRows;
	}
	
	/**
	 * @param files the files to set
	 */
	public void setFiles(List<FileSource> files) {
		this.files = files;
	}
	
	/**
	 * @return the files
	 */
	public List<FileSource> getFiles() {
		return files;
	}
	
	
	/**
	 * @param extensions the extensions to set
	 */
	public void setExtensions(ExtensionToCounting extensions) {
		this.extensions = extensions;
	}
	
	public String getDateCountingIT(){
		SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return s.format(this.dateCounting);
	}

	public void setDateString(String dateCounting){
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			this.dateCounting = s.parse(dateCounting);
		}catch(Exception e){
			e.printStackTrace();
			this.dateCounting = null;
		}
	}

	/**
	 * @return
	 */
	public Object[] getObject(long diff) {
		return new Object[]{
				getDateCountingIT(),
				this.numberOfPack,
				this.numberOfFiles,
				this.numberOfRows,
				"ext",
				diff
		};
	}
}
