package controller;

import java.io.FileOutputStream;
import java.util.List;

import javax.swing.JOptionPane;

import view.panels.FilesPanel;
import main.RowsCounter;
import model.FileSource;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author Giovanni Buscarino (giovybus) Copyright (c) 2015 <br>
 * <b>Email:</b> giovanni.buscarino[at]gmail.com<br>
 *
 * created on 01/nov/2015 21:24:07
 */
public class Pdf {
	private String estensione = ".pdf";
	private Document pdf;
	
	public Pdf(){
//		inizializzaCartella();
		//scriviPdf(obj);
	}

	public void scriviPdf(List<FileSource>filesSource){
//		String percorsoFinale = Rows.percorsoPdf + getSottoCartella() + "/" + nomeFile +  estensione;
		String percorsoFinale = RowsCounter.PATH_DESKTOP + "s" +  estensione;
		pdf = new Document();
		
		try{
			PdfWriter.getInstance(pdf, new FileOutputStream(percorsoFinale));
			pdf.open();
			
			pdf.addAuthor("Giovanni Buscarino ABZ 2013 (c)");
			pdf.addCreator("Giovanni Buscarino ABZ 2013 (c)");
			pdf.addCreationDate();
			
			if(filesSource != null && filesSource.size() > 0){
				Paragraph testata = new Paragraph(filesSource.get(0).getAbsolutePath() 
						+ ", (" + filesSource.get(0).getIdCounting() + ")");
				
				pdf.add(testata);
				pdf.add(new Paragraph("\n"));
				
				PdfPTable tabella = new PdfPTable(2);
				
				tabella.setHorizontalAlignment(Element.ALIGN_CENTER);
							
				String []caption = FilesPanel.col ;
				
				for(int i=0; i<caption.length; i++){
					tabella.addCell(new Paragraph(caption[i]));
				}
				
				String separatore = JOptionPane.showInputDialog(null, "separatori, inserisci intero");
				if(separatore == null || separatore.equals(""))separatore = "0";
				
				for(int i=0; i<filesSource.size(); i++){
					System.out.println(filesSource.get(i).getRowsCounting() + ", " + 
								filesSource.get(i).getAbsolutePathSmart(Integer.parseInt(separatore)));
					tabella.addCell(new Paragraph(filesSource.get(i).getAbsolutePathSmart(Integer.parseInt(separatore))));
					tabella.addCell(new Paragraph(filesSource.get(i).getRowsCounting() + ""));
				}
				
				pdf.add(tabella);
				pdf.close();
			}
						
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Non sono riuscito a esportare il file in pdf", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		
		
		/*int choose = JOptionPane.showConfirmDialog(null, "Il File PDF è stato esportato correttamente\nVuoi Aprire la cartalladove si trova ??", "Tutto Bene", JOptionPane.YES_NO_OPTION);
		
		if(choose == JOptionPane.YES_OPTION){
			
			File file = new File(RowsCounter.PATH_DESKTOP + getSottoCartella());
			Desktop desk = null;
			
			if(Desktop.isDesktopSupported()){
				desk = Desktop.getDesktop();
				
				try{
					desk.open(file);
				}catch(Exception e){
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Non sono riuscito ad aprire la cartella", "I'm Sorry", JOptionPane.ERROR_MESSAGE);
				}
			}else{
				JOptionPane.showMessageDialog(null, "Non sono riuscito ad aprire la cartella", "I'm Sorry", JOptionPane.ERROR_MESSAGE);
			}
		}*/
	}

}
