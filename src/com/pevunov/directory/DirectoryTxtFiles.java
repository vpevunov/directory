/**
 * 
 */
package com.pevunov.directory;

import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author vpevunov
 *
 */
public class DirectoryTxtFiles {
	private static final String DIR = "tmp";
	
	public static void main(String[] args) {
		
		DirectoryTxtFiles me = new DirectoryTxtFiles();
		
		System.out.println("Explore /tmp directory using nio API:");
		me.listFilesTxtNio(FileSystems.getDefault().getSeparator() + DIR);

		System.out.println("\nIn case we are on Java lower than 7 we would explore /tmp directory using File API:");
		me.listFilesTxt(File.separator + DIR);
	}
	
	public void listFilesTxtNio(String dirName){
		
		Path folderPath = Paths.get(dirName);
		
		try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(folderPath, "*.txt")){
			for(Path dirEntry: dirStream){
				if(Files.isRegularFile(dirEntry)){
					System.out.println(dirEntry.getFileName());
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void listFilesTxt(String dirName){
		File dir = new File(dirName);
		if(dir.isDirectory()){
			for(File dirEntry: dir.listFiles()){
				if(dirEntry.isFile() && dirEntry.getName().endsWith(".txt")){
					System.out.println(dirEntry.getName());
				}
			}
		} else {
			System.out.println("No such folder: " + dirName);
		}
	}
}
