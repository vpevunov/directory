/**
 * 
 */
package com.pevunov.directory.model;

/**
 * @author vpevunov
 *
 */
public class FileMeta implements Comparable<FileMeta> {
	private long fileSize;
	private String fileName;
	
	public FileMeta(long fileSize, String fileName) {
		this.fileSize = fileSize;
		this.fileName = fileName;
	}
	
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Override
	public int compareTo(FileMeta fileMeta) {
		if(fileMeta != null){
			return Long.signum((this.fileSize == fileMeta.fileSize)
					?this.fileName.compareTo(fileMeta.fileName)
							:(this.fileSize - fileMeta.fileSize));
		} else {
			throw new NullPointerException();
		}
	}
}
