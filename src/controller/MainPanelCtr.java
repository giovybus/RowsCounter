package controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.Counting;
import model.DBMS_Counting;
import model.DBMS_Project;
import model.Project;
import view.MainGui;
import view.panels.CountingsPanel;
import view.panels.MainPanel;

/**
 * @author Giovanni Buscarino (giovybus) Copyright (c) 2015 <br>
 * <b>Email:</b> giovanni.buscarino[at]gmail.com<br>
 *
 * created on 27/apr/2015 16:00:08
 * 
 */
public class MainPanelCtr {
	private MainGui mainGui;
	
	private MainPanel mainPanel;
	private DBMS_Project db_project;
	private List<Project>projects;
	
	private CountingsPanel countPanel;
	private DBMS_Counting db_counting;
	private List<Counting>countings;	
	
	private JFileChooser fileChooser;
	
	/**
	 * 
	 */
	public MainPanelCtr(MainGui mainGui, MainPanel mainPanel, CountingsPanel countPanel) {
		this.mainGui = mainGui;
		this.mainPanel = mainPanel;
		this.countPanel = countPanel;
		
		this.db_project = new DBMS_Project();
		this.db_counting = new DBMS_Counting();
		
		this.mainPanel.getButtonExport().setEnabled(false);
		
		this.projects = db_project.getAllProjects();
		if(this.projects != null){
			fillTable();
		}
		
		setActionListeners();
	}

	/**
	 * 
	 */
	private void setActionListeners() {
		this.mainPanel.getButtonView().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(mainPanel.getSelectedRow() != -1){
					mainGui.removeMainPanel();
					mainGui.addCountingPanel();
				}else{
					JOptionPane.showMessageDialog(null, "You must selected a row from the table", 
							"warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		fileChooser = new JFileChooser(new File(System.getProperty("user.home") + "/Desktop"));
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setDialogTitle("select your root dir");
		
		this.mainPanel.getButtonCounting().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(mainPanel.getSelectedRow() != -1){
					fileChooser.setCurrentDirectory(new File(projects.get(mainPanel.getSelectedRow()).getAbsolutePath()));
				}
				
				if(fileChooser.showOpenDialog(fileChooser) == JFileChooser.APPROVE_OPTION){
					//start count
				}
				
			}
		});
		
		this.mainPanel.getButtonOpen().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String path = mainPanel.getSelectedRow() != -1 ? 
						(String) projects.get(mainPanel.getSelectedRow()).getAbsolutePath() 
						: null;
						
				if(Desktop.isDesktopSupported() && path != null){
					File dir = new File(path);
					Desktop desk = null;
					
					desk = Desktop.getDesktop();
					try{
						desk.open(dir);
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, "Non sono riuscito ad aprire la cartella", "I'm sorry", JOptionPane.ERROR_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null, "Non sono riuscito ad aprire la cartella", "I'm sorry", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
	}

	/**
	 * fill the table
	 */
	private void fillTable() {
		for(Project p : projects){
			mainPanel.addRow(p);
		}
		
	}

}
