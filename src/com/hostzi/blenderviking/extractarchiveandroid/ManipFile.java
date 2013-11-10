/**
 * Copyright (C) 2013, Romain Estievenart
 * ManipFile class
 * @author: Romain Estievenart
 * @date: 2013-11-07
 */
package com.hostzi.blenderviking.extractarchiveandroid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.HashSet;
import java.util.Set;

public class ManipFile {

	public static Set<String> scanDir(String theDirectory) {
		File currDir = new File(theDirectory);
		if (currDir.exists() && !currDir.isDirectory())
			return null;
		String fileList[] = currDir.list();
		Set<String> finalListFiles = new HashSet<String>();
		String as[];
		int j = (as = fileList).length;
		for (int i = 0; i < j; i++) {
			String file = as[i];
			Set<String> temp = scanDir(theDirectory + File.separator + file);
			if (temp == null)
				finalListFiles.add(theDirectory + File.separator + file);
			else
				finalListFiles.addAll(temp);
		}

		return finalListFiles;
	}

	private static void moveDirectory(File input, File output) {
		if (!output.exists())
			output.mkdir();
		File inDir[] = input.listFiles();
		File afile[];
		int j = (afile = inDir).length;
		for (int i = 0; i < j; i++) {
			File f = afile[i];
			move(f, new File(output, f.getName()));
		}

	}

	private static boolean moveFile(File input, File output) {
		if (!output.exists()) {
			boolean result = input.renameTo(output);
			if (!result) {
				result = true;
				result &= copyFile(input, output);
				if (result)
					result &= input.delete();
			}
			return result;
		} else {
			return false;
		}
	}

	public static void rename(String input, String output) {
		move(input, output);
	}
	
	public static void rename(File input, File output) {
		move(input, output);
	}
	
	public static void move(String input, String output) {
		move(new File(input), new File(output));
	}
	
	public static void move(File input, File output) {
		if (input.isFile())
			moveFile(input, output);
		else if (input.isDirectory())
			moveDirectory(input, output);
	}

	private static void copyDirectory(File input, File output) {
		if (!output.exists())
			output.mkdir();
		File inDir[] = input.listFiles();
		File afile[];
		int j = (afile = inDir).length;
		for (int i = 0; i < j; i++) {
			File f = afile[i];
			copy(f, new File(output, f.getName()));
		}

	}

	private static boolean copyFile(File input, File output) {
		if (!output.exists()) {
			try {
				output.createNewFile();
			} catch (IOException e) {
				System.err.println(e.getMessage());
				return false;
			}
		}

		FileChannel source = null;
		FileChannel destination = null;

		try {
			source = new FileInputStream(input).getChannel();
			destination = new FileOutputStream(output).getChannel();
			destination.transferFrom(source, 0, source.size());
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
			return false;
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return false;
		} finally {
			if (source != null) {
				try {
					source.close();
				} catch (IOException e) {
					System.err.println(e.getMessage());
					return false;
				}
			}
			if (destination != null) {
				try {
					destination.close();
				} catch (IOException e) {
					System.err.println(e.getMessage());
					return false;
				}
			}
		}
		return true;
	}

	public static void copy(File input, File output) {
		if (input.isFile())
			copyFile(input, output);
		else if (input.isDirectory())
			copyDirectory(input, output);
	}
	
	public static void copy(String input, String output) {
		copy(new File(input), new File(output));
	}

	private static boolean deleteDirectory(File directory) {
		File inDir[] = directory.listFiles();
		File afile[];
		int j = (afile = inDir).length;
		for (int i = 0; i < j; i++) {
			File f = afile[i];
			delete(f);
		}

		return directory.delete();
	}

	private static boolean deleteFile(File file) {
		return file.delete();
	}

	public static boolean delete(File file) {
		if (file.exists()) {
			if (file.isFile())
				return deleteFile(file);
			if (file.isDirectory())
				return deleteDirectory(file);
		}
		return false;
	}
	
	public static boolean delete(String file) {
		return delete(new File(file));
	}

	public static void deleteAll(File[] files){
		for(File file: files)
			delete(file);
	}
	
	public static void deleteAll(String[] files){
		for(String file: files)
			delete(file);
	}
}