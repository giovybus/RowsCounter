package features;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author Giovanni Buscarino (giovybus) Copyright (c) 2015 <br>
 * <b>Email:</b> giovanni.buscarino[at]gmail.com<br>
 *
 * created on 28/apr/2015 13:43:05
 */
public class Backup {
	public static final String HOME_DIR = "./backup";
	
	/**
	 * this method create the directory 
	 * if not exist
	 */
	public static void init(){
		if(!new File(HOME_DIR).exists()){
			new File(HOME_DIR).mkdir();
		}
	}
	
	/**
	 * questo metodo è stato copiato da: http://examples.javacodegeeks.com/core-java/io/file/4-ways-to-copy-file-in-java/
	 * secondo la guida è il più veloce
	 * @param source
	 * @param dest
	 * @throws IOException
	 */
	public static void copyFileUsingFileChannels(File source, File dest)
			throws IOException {
		FileChannel inputChannel = null;
		FileChannel outputChannel = null;
		try {
			inputChannel = new FileInputStream(source).getChannel();
			outputChannel = new FileOutputStream(dest).getChannel();
			outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
			
			System.out.println("FILE COPIATO");
		} finally {
			inputChannel.close();
			outputChannel.close();
		}
	}
}
