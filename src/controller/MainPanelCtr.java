package controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.Counting;
import model.DBMS_Counting;
import model.DBMS_File;
import model.DBMS_Project;
import model.FileSource;
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
	private Project project;
	
	private CountingsPanel countPanel;
	private DBMS_Counting db_counting;
	private List<Counting>countings;	
	private Counting countingTemp;
	
	private JFileChooser fileChooser;
	
	private DBMS_File db_fileSource;
	private List<FileSource>filesSource;
	private long numSottoCartelle = 0;
	private long numFile = 0;
	private long numeroRigheCodice = 0;
	
	/**
	 * 
	 */
	public MainPanelCtr(MainGui mainGui, MainPanel mainPanel, CountingsPanel countPanel) {
		this.mainGui = mainGui;
		this.mainPanel = mainPanel;
		this.countPanel = countPanel;
		
		this.db_project = new DBMS_Project();
		this.db_counting = new DBMS_Counting();
		this.db_fileSource = new DBMS_File();
		
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
					countings = db_counting.getCountingForProject(projects.get(mainPanel.getSelectedRow()));
					
					mainGui.removeMainPanel();
					countPanel.setCountings(countings);
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
					File dir = fileChooser.getSelectedFile();
					
					project = db_project.getProjectByPath(dir.getAbsolutePath());
					countingTemp = null;
					if(project == null){
						//this is a new path
						project = new Project(dir.getAbsolutePath(), new Date());
						if(db_project.insert(project)){
							countingTemp = new Counting(project.getId(), new Date(), 0, 0, 0, null);
						}
						
						mainPanel.addRow(project);
					}else{
						//this is an exist path
						countingTemp = new Counting(project.getId(), new Date(), 0, 0, 0, null);
					}
					
					List<String> temp = new ArrayList<String>();
					temp.add(dir.getAbsolutePath());
					
					db_counting.inserisci(countingTemp); 
					filesSource = new ArrayList<FileSource>();
					
					navigaDirectory(temp, "java, ", dir.list().length);
					
					System.out.println(numSottoCartelle + ", " + numFile + ", " + numeroRigheCodice);
					countingTemp.setNumberOfPack(numSottoCartelle);
					countingTemp.setNumberOfRows(numeroRigheCodice);
					countingTemp.setNumberOfFiles(numFile);
					db_counting.update(countingTemp);
					
					countingTemp.setFiles(filesSource);
					project.addCounting(countingTemp);
					
					if(projects == null)projects = new ArrayList<Project>();
					projects.add(project);
					
					project = null;
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
		
		this.mainPanel.getButtonSearch().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//TODO
				
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
	
	/**
	 * src - primoPak
	 *  |		|- prova.java
	 * 	|		|- altroPak
	 * 	|				|-settings.java
	 * 	|
	 *  |		|- secondoPak
	 *  |				|-frame.java
	 *  |				|-gui.java
	 *  |
	 *  |
	 *  |
	 * 	|- main.java
	 * 
	 * 
	 * @param path
	 * @param estensione
	 * @param numDir
	 */
	private void navigaDirectory(List <String> path, String estensione, int numDir){
		//for(int i=0; i<path.size(); i++)System.out.println(path.get(i));
		
		
		List <String> temp = new ArrayList<String>();
		int contaDirectory = 0;
		String est = estensione;
		
		if(numDir == 0) contaDirectory = -1;
		
		for(int i=0; i<path.size(); i++){
			
			File f = new File(path.get(i));
				
				File []list = f.listFiles();
				
				for(int j=0; j<list.length; j++){
					if(list[j].isDirectory()){
						temp.add(list[j].getAbsolutePath());
						numSottoCartelle++;
						contaDirectory++;
					}
					else if(controllaEstensione(list[j], estensione)){
						//System.out.println("Dimensione del file: " + list[j].length());
						long cont = contaRighe(list[j]);
						FileSource source = new FileSource(countingTemp.getId(), list[j].getAbsolutePath(), "-", cont);
						filesSource.add(source);
						db_fileSource.inserisci(source);
						numFile++;
						numeroRigheCodice += cont;
						
//						System.out.println(list[j].getAbsolutePath()); 
					}
				}
		}
		
		if(numDir != -1) navigaDirectory(temp, est, contaDirectory);
	}
	
	private boolean controllaEstensione(File file, String estensione){
		String []estensioni = estensione.split(",");
		boolean controllo = false;		
		String ext = file.getAbsolutePath();
				
		String prova = "";		
		StringTokenizer str = new StringTokenizer(ext, ".");
		while(str.hasMoreTokens()){
			prova = str.nextToken();
		}
		
		
		for(int i=0; i<(estensioni.length-1); i++){			
			if(prova.toLowerCase().equals(estensioni[i].toLowerCase().trim())){
				//System.out.println(prova + " " + estensioni[i].toLowerCase().trim());
				controllo = true;
				break;
			}
		}
		//System.out.println(controllo + " " + estensione + " " + file.getAbsolutePath());
		return controllo;
	}
	
	private long contaRighe(File file){
		long parz = 0;
		try {
			BufferedReader buff = new BufferedReader(new FileReader(file.getAbsolutePath()));
			
			while((buff.readLine()) != null){
				parz++;
			}
			
			buff.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parz;
	}

}
