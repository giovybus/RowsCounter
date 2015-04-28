package view.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.FileSource;

/**
 * @author Giovanni Buscarino (giovybus) Copyright (c) 2015 <br>
 * <b>Email:</b> giovanni.buscarino[at]gmail.com<br>
 *
 * created on 26/apr/2015 17:17:04
 */
public class FilesPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public boolean IS_SEARCH = false;
	
	private JPanel panNorth;
	private JLabel labQuery;
	
	private JPanel panCenter;
	private JTable table;
	private DefaultTableModel tableModel;
	private String []col = {"Absolute path", "N° of rows"};
	private JScrollPane tableScroll;
	
	private JPanel panSouth;
	
	/**
	 * this button allow you to
	 * return in the main panel
	 * to view all countings 
	 */
	private JButton buttReturn;
	
	/**
	 * with this button you can
	 * open the file in the backup folder
	 * using notepad++
	 */
	private JButton buttOpenFile;
	
	private List<FileSource>files;
	
	/**
	 * 
	 */
	public FilesPanel() {
		super(new BorderLayout());
		initPanNorth();
		initPanCenter();
		initPanSouth();
		fillPanel();
	}


	/**
	 * 
	 */
	private void initPanNorth() {
		panNorth = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		labQuery = new JLabel();
		panNorth.add(labQuery);
	}


	/**
	 * 
	 */
	private void initPanCenter() {
		this.panCenter = new JPanel(new GridLayout(1, 1));
		
		initTable();
		this.panCenter.add(tableScroll);
	}


	/**
	 * 
	 */
	private void initTable() {
		tableModel = new DefaultTableModel(col, 0);
		table = new JTable(tableModel){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int r, int c){
				return false;
			}
		};
		
		table.getColumnModel().getColumn(1).setMaxWidth(80);
		table.getColumnModel().getColumn(1).setMinWidth(80);
		
		tableScroll = new JScrollPane(table);
			
	}

	/**
	 * 
	 */
	private void fillTable() {
		this.tableModel.setRowCount(0);
		if(this.files != null){
			for(FileSource f : this.files){
				addRow(f);
			}
		}
		
	}

	/**
	 * 
	 */
	private void initPanSouth() {
		panSouth = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		buttReturn = new JButton("Return");
		panSouth.add(buttReturn);
		
		buttOpenFile = new JButton("Open file");
		panSouth.add(buttOpenFile);
	}


	/**
	 * 
	 */
	private void fillPanel() {
		this.add(panNorth, BorderLayout.NORTH);
		this.add(panCenter, BorderLayout.CENTER);
		this.add(panSouth, BorderLayout.SOUTH);
	}
	
	/******************************************************************
	 * 
	 * 			public methods 
	 * 
	 * *****************************************************************/
	
	/**
	 * @return the buttReturn
	 */
	public JButton getButtReturn() {
		return buttReturn;
	}
	
	/**
	 * @return the buttOpenFile
	 */
	public JButton getButtOpenFile() {
		return buttOpenFile;
	}


	/**
	 * @param files
	 */
	public void setFiles(List<FileSource> files) {
		this.files = files;
		fillTable();		
	}
	
	public void addRow(FileSource f){
		tableModel.addRow(f.getObject());
	}
	
	/**
	 * @return the labQuery
	 */
	public JLabel getLabQuery() {
		return labQuery;
	}
	
	public void setLabQuery(String text){
		this.labQuery.setText(text);
	}
	
	/**
	 * @deprecated
	 * @return
	 */
	public String getAbsolutePathBackupSelectedFile(){
		if(this.table.getSelectedRow() != -1){
			return files.get(this.table.getSelectedRow()).getAbsolutePathBackup();
		}else{
			return null;
		}
	}
	
	/**
	 * @deprecated
	 * @return
	 */
	public String getAbsolutePathSelectedFile(){
		if(this.table.getSelectedRow() != -1){
			return files.get(this.table.getSelectedRow()).getAbsolutePath();
		}else{
			return null;
		}
	}
	
	/**
	 * questo metodo mi ritorna il percorso del file
	 * in modo intelligente. perchè se sto cercando 
	 * un file, lui mi deve dare il percorso del progetto
	 * se invece sto visionando i file di un conteggio
	 * avvenuto precedentemente lui mi deve mostrare
	 * il file memorizzato nel backup
	 * @return
	 */
	public String smartGetAbsolutePath(){
		if(IS_SEARCH){
			if(this.table.getSelectedRow() != -1){
				return files.get(this.table.getSelectedRow()).getAbsolutePath();
			}else{
				return null;
			}
		}else{
			if(this.table.getSelectedRow() != -1){
				return files.get(this.table.getSelectedRow()).getAbsolutePathBackup();
			}else{
				return null;
			}
		}
	}


	/**
	 * @return
	 */
	public long getRowOfFileSerched() {
		if(IS_SEARCH){
			if(this.table.getSelectedRow() != -1){
				return files.get(this.table.getSelectedRow()).getRowsCounting();
			}else{
				return 3;
			}
		}else{
			return 7;
		}
	}
}
