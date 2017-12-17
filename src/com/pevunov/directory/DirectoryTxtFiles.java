/**
 * Write a simple Java application that gets a list of all files in the /tmp directory that have a .txt suffix.
 * The application then prints out the size and name of the files in ascending order by size and name.
 */
package com.pevunov.directory;

import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.pevunov.directory.model.FileMeta;

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
		List<FileMeta> txtFiles = new ArrayList<FileMeta>();
		try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(folderPath, "*.txt")){
			for(Path dirEntry: dirStream){
				if(Files.isRegularFile(dirEntry)){
					//System.out.println(Files.size(dirEntry) + "\t" +dirEntry.getFileName());
					txtFiles.add(new FileMeta(Files.size(dirEntry), dirEntry.getFileName().toString()));
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		Collections.sort(txtFiles, Collections.reverseOrder());
		//System.out.println("Sorted list of files:");
		printFileList(txtFiles);
	}
	
	public void listFilesTxt(String dirName){
		File dir = new File(dirName);
		List<FileMeta> txtFiles = new ArrayList<FileMeta>();
		if(dir.isDirectory()){
			for(File dirEntry: dir.listFiles()){
				if(dirEntry.isFile() && dirEntry.getName().endsWith(".txt")){
					//System.out.println(dirEntry.length() + "\t" + dirEntry.getName());
					txtFiles.add(new FileMeta(dirEntry.length(), dirEntry.getName()));
				}
			}
		} else {
			System.out.println("No such folder: " + dirName);
		}
		Collections.sort(txtFiles, Collections.reverseOrder());
		//System.out.println("Sorted list of files:");
		printFileList(txtFiles);
	}
	
	private void printFileList(List<FileMeta> fileList){
		if(fileList != null){
			System.out.println("Size \t File Name");
			for(FileMeta fMeta: fileList){
				System.out.println(fMeta.getFileSize() + "\t" + fMeta.getFileName());
			}
		}
	}
}
