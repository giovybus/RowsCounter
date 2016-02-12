package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import view.MainGui;
import view.panels.FilesPanel;

/**
 * @author Giovanni Buscarino (giovybus) Copyright (c) 2015 <br>
 * <b>Email:</b> giovanni.buscarino[at]gmail.com<br>
 *
 * created on 28/apr/2015 13:19:47
 */
public class FilesPanelCtr {
	private MainGui mainGui;
	private FilesPanel filePanel;
	
	/**
	 * 
	 */
	public FilesPanelCtr(MainGui mainGui, FilesPanel filePanel) {
		this.mainGui = mainGui;
		this.filePanel = filePanel;
		
		setActionListener();
	}

	/**
	 * 
	 */
	private void setActionListener() {
		this.filePanel.getButtReturn().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainGui.removeFilesPanel();
				mainGui.addCountingPanel();
			}
		});
		
		this.filePanel.getButtOpenFile().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(filePanel.isRowSelected()){
					String path = filePanel.smartGetAbsolutePath();
					if(path != null){
						try{
							Runtime.getRuntime().exec("./npp/notepad++.exe " + path + " -n" + filePanel.getRowOfFileSerched() 
									+ " -nosession -ro");
							
						}catch(Exception ee){
							ee.printStackTrace();
						}	
					}else{
						JOptionPane.showMessageDialog(null, "the file not exist", "notice", JOptionPane.WARNING_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null, "you must select a row of the table", "notice", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		//this.filePanel.getTable().setAutoCreateRowSorter(true);
		this.filePanel.getTable().addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.getClickCount() == 2){
					String path = filePanel.smartGetAbsolutePath();
					if(path != null){
						try{
							Runtime.getRuntime().exec("./npp/notepad++.exe " + path + " -n" + filePanel.getRowOfFileSerched() 
									+ " -nosession -ro");
							
						}catch(Exception ee){
							ee.printStackTrace();
						}	
					}else{
						JOptionPane.showMessageDialog(null, "the file not exist", "notice", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		
		this.filePanel.getButtPDF().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Pdf pdf = new Pdf();
				pdf.scriviPdf(filePanel.getFiles());
			}
		});
	}
}
