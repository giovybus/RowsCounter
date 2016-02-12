package controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import features.Backup;
import model.Counting;
import model.DBMS_Counting;
import model.DBMS_File;
import model.DBMS_Project;
import model.FileSource;
import model.Project;
import view.MainGui;
import view.panels.CountingsPanel;
import view.panels.FilesPanel;
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
	
	private FilesPanel panFiles;
	private List<FileSource>filesSearch;
	
	private static final String exstensions = "java,c,cpp,h,el,php,js, ";
	
	/**
	 * the query for the search
	 */
	private String query;
	
	/**
	 * 
	 */
	public MainPanelCtr(MainGui mainGui, MainPanel mainPanel, 
			CountingsPanel countPanel, FilesPanel panFiles) {
		this.mainGui = mainGui;
		this.mainPanel = mainPanel;
		this.countPanel = countPanel;
		this.panFiles = panFiles;
		
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
							//create the directory for this project
							new File(Backup.HOME_DIR + "/" + project.getId()).mkdir();
							
							countingTemp = new Counting(project.getId(), new Date(), 0, 0, 0, null);
						}
						
						mainPanel.addRow(project);
					}else{
						//this is an exist path
						project.setLatestCountingDate(new Date());
						countingTemp = new Counting(project.getId(), new Date(), 0, 0, 0, null);
					}
					
					List<String> temp = new ArrayList<String>();
					temp.add(dir.getAbsolutePath());
					
					db_counting.inserisci(countingTemp); 
					db_project.update(project);
					
					new File(Backup.HOME_DIR + "/" + project.getId() + "/" + countingTemp.getId()).mkdir();
					filesSource = new ArrayList<FileSource>();
					
					navigaDirectory(temp, exstensions, dir.list().length);
					
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
					filesSource = null;
					
					numeroRigheCodice = 0;
					numFile = 0;
					numSottoCartelle = 0;
					
					JOptionPane.showMessageDialog(null, "all done");
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
				searchEngine();
			}
		});
		
		this.mainPanel.getFieldSearch().addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					searchEngine();
				}
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		
		this.mainPanel.getButtonDelete().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(mainPanel.getSelectedRow() != -1){
					Project p = projects.get(mainPanel.getSelectedRow());
					
					int choose = JOptionPane.showConfirmDialog(null, "Are you sure to delete the project:\n" + p.getAbsolutePath(),
							"warning",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					
					if(choose == JOptionPane.YES_OPTION){
						if(db_project.delete(p)){
							refreshTable(p.getId());
						}else{
							JOptionPane.showMessageDialog(null, "Erorr to delete the project", 
									"error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}else{
					JOptionPane.showMessageDialog(null, "You must selected a row from the table", 
							"warning", JOptionPane.WARNING_MESSAGE);
				}
				
			}

		});
		
	}
	
	private void searchEngine(){
		query = mainPanel.getQuerySearch();
		if(query != null && !query.equals("")){
			//TODO
			filesSearch = new ArrayList<>();
			for(Project p : projects){
				File dir = new File(p.getAbsolutePath());
				if(dir.exists()){
					List<String> temp = new ArrayList<String>();
					temp.add(dir.getAbsolutePath());

					search(temp, exstensions, dir.list().length);
				
				}
			}
			
			//JOptionPane.showMessageDialog(null, filesSearch.size());
			panFiles.IS_SEARCH = true;
			panFiles.setLabQuery("query searched: " + query);
			panFiles.setFiles(filesSearch);
			
			mainGui.removeMainPanel();
			mainGui.addFilesPanel();
		}else{
			JOptionPane.showMessageDialog(null, "you must insert a query", 
					"notice", JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * fill the table
	 */
	private void fillTable() {
		for(Project p : projects){
			mainPanel.addRow(p);
		}
		
		mainPanel.getTable().addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(arg0.getClickCount() == 2){
					
					countings = db_counting.getCountingForProject(projects.get(mainPanel.getSelectedRow()));
					
					mainGui.removeMainPanel();
					countPanel.setCountings(countings);
					mainGui.addCountingPanel();
				}
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {}
			
			@Override
			public void mouseExited(MouseEvent arg0) {}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {}
		});
		
	}
	
	/**
	 * refresh table after delete
	 * a project
	 */
	private void refreshTable(int idToDelete) {
		for(int i=0; i<projects.size(); i++){
			if(projects.get(i).getId() == idToDelete){
				projects.remove(i);
				break;
			}
		}
		
		mainPanel.resetTable();
		fillTable();
		
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
	private void search(List <String> path, String estensione, int numDir){
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
						
						long parz = 0;
						try {
							BufferedReader buff = new BufferedReader(new FileReader(list[j].getAbsolutePath()));
							
							String s = new String();
							while((s = buff.readLine()) != null){
								parz++;
								
								if(s.contains(query)){
									System.out.println("*** trovato *** " + list[j].getAbsolutePath() + ", alla riga: " + parz);
									filesSearch.add(new FileSource(-1, list[j].getAbsolutePath(), "", parz));
								}
								
							}
							
							buff.close();
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						//System.out.println(list[j].getAbsolutePath()); 
					}
				}
		}
		
		if(numDir != -1) search(temp, est, contaDirectory);
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
						File backupped = new File(Backup.HOME_DIR + "/" + project.getId() + "/" 
						+ countingTemp.getId() + "/" + list[j].getName());
						
						FileSource source = new FileSource(countingTemp.getId(), list[j].getAbsolutePath(), 
								backupped.getAbsolutePath(), cont);
						
						try{
							Backup.copyFileUsingFileChannels(list[j], backupped);	
						}catch(Exception e){
							e.printStackTrace();
						}
						
						filesSource.add(source);
						db_fileSource.inserisci(source);
						numFile++;
						numeroRigheCodice += cont;
						
						System.out.println(list[j].getAbsolutePath()); 
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
