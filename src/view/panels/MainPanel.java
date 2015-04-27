package view.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Project;

/**
 * @author Giovanni Buscarino (giovybus) Copyright (c) 2015 <br>
 * <b>Email:</b> giovanni.buscarino[at]gmail.com<br>
 *
 * created on 26/apr/2015 17:15:59
 */
public class MainPanel extends JPanel{

	private JPanel panCenter;
	private JTable table;
	private DefaultTableModel tableModel;
	private String []col = {"Path root project", "Last counting"};
	private JScrollPane tableScroll;
	
	private JPanel panSouth;
	
	/**
	 * this button allow you 
	 * to view details of the
	 * project selected  
	 */
	private JButton buttView;
	
	/**
	 * this button open the filechooser
	 * for a new counting
	 */
	private JButton buttCounting; 
	
	/**
	 * this button allow you to
	 * delete a row selected in the
	 * table
	 */
	private JButton buttDelete;
	
	/**
	 * with this button you can
	 * export the project report 
	 * in PDF
	 */
	private JButton buttExport;
	
	/**
	 * this button open the directory
	 * of the project
	 */
	private JButton buttOpen;
	
	/**
	 * a list of all projects 
	 * (this list is filled by database)
	 */
	private List<Project>projects;
	
	/**
	 * default constructor
	 */
	public MainPanel() {
		super(new BorderLayout());
		
		initSouthPanel();
		initCenterPanel();
		fillPanel();
	}

	/**
	 * 
	 */
	private void initSouthPanel() {
		panSouth = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		initButtView();
		panSouth.add(buttView);
	}

	/**
	 * 
	 */
	private void initButtView() {
		buttView = new JButton("View project");		
	}

	/**
	 * 
	 */
	private void initCenterPanel() {
		panCenter = new JPanel(new GridLayout(1, 1));
		
		initTable();
		panCenter.add(tableScroll);
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
		
		tableScroll = new JScrollPane(table);
		
	}

	/**
	 *  fill the panel
	 */
	private void fillPanel() {
		this.add(panCenter, BorderLayout.CENTER);
		this.add(panSouth, BorderLayout.SOUTH);
		
	}
	
	
	/******************************************************************
	 * 
	 * 			public methods 
	 * 
	 * *****************************************************************/
	
	public JButton getButtonView(){
		return this.buttView;
	}
	 
}
