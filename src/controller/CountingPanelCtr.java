package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import model.DBMS_File;
import model.FileSource;

import view.MainGui;
import view.panels.CountingsPanel;
import view.panels.FilesPanel;

/**
 * @author Giovanni Buscarino (giovybus) Copyright (c) 2015 <br>
 * <b>Email:</b> giovanni.buscarino[at]gmail.com<br>
 *
 * created on 27/apr/2015 19:39:18
 */
public class CountingPanelCtr {
	private MainGui mainGui;
	private CountingsPanel countingPanel;
	private FilesPanel filePanel;
	
	private DBMS_File db_file;
	
	/**
	 * 
	 */
	public CountingPanelCtr(MainGui mainGui, CountingsPanel countingPanel, FilesPanel filePanel) {
		this.mainGui = mainGui;
		this.countingPanel = countingPanel;
		this.filePanel = filePanel;
		
		this.db_file = new DBMS_File();
		
		this.countingPanel.getButtonCounting().setEnabled(false);
		this.countingPanel.getButtonDelete().setEnabled(false);
		this.countingPanel.getButtonExport().setEnabled(false);
		this.countingPanel.getButtonOpen().setEnabled(false);
		
		setActionListener();
	}

	/**
	 * 
	 */
	private void setActionListener() {
		this.countingPanel.getButtonReturn().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CountingPanelCtr.this.mainGui.removeCountingPanel();
				CountingPanelCtr.this.mainGui.addMainPanel();
				
			}
		});
		
		this.countingPanel.getButtView().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(countingPanel.getSelectedRow() != -1){
					mainGui.removeCountingPanel();
					List<FileSource>files = db_file.getFilesByCounting(countingPanel.getCountingSelected());
					
					filePanel.setFiles(files);
					filePanel.IS_SEARCH = false;
					mainGui.addFilesPanel();
				}else{
					JOptionPane.showMessageDialog(null, "You must selected a row from the table", 
							"warning", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
	}
	
}
