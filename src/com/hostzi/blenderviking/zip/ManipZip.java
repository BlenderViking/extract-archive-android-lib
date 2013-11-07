/**
 * Copyright (C) 2013, Romain Estievenart
 * ManipZip class
 * @author: Romain Estievenart
 * @date: 2013-11-07
 */
package com.hostzi.blenderviking.zip;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ManipZip {

	/**
	 * Extract a archive into a specific directory
	 * 
	 * @param zipFile
	 *            - the zip archive
	 * @param outputDir
	 *            - the output directory
	 * @return - return result of the extract
	 * @throws IOException
	 */
	public static boolean extract(File zipFile, File outputDir)
			throws IOException {

		ZipInputStream stream = null;
		ZipEntry entry = null;

		// security check
		if (zipFile == null || outputDir == null || zipFile.isDirectory()
				|| outputDir.isFile() || !zipFile.exists())
			return false;

		// check directory output directory exists
		// if doesn't exist, make it.
		if (!outputDir.exists())
			outputDir.mkdir();

		stream = new ZipInputStream(new FileInputStream(zipFile));

		while ((entry = stream.getNextEntry()) != null) {
			// check if the entry is a directory
			if (entry.isDirectory())
				new File(outputDir.getAbsolutePath() + File.separator
						+ entry.getName()).mkdir();
			else
				extractFile(outputDir, entry, stream);
		}
		stream.close();

		return true;
	}

	/**
	 * Extract a ZipEntry into a file
	 * 
	 * @param outputDir
	 *            - the output directory
	 * @param entry
	 *            - the ZipEntry
	 * @param stream
	 *            - the ZipInputStream
	 * @throws IOException
	 *             - call IOException
	 */
	private static void extractFile(File outputDir, ZipEntry entry,
			ZipInputStream stream) throws IOException {

		FileOutputStream fos = new FileOutputStream(outputDir.getAbsolutePath()
				+ File.separator + entry.getName());

		byte[] buffer = new byte[2048];
		BufferedOutputStream bfout = new BufferedOutputStream(fos, 4096);
		int len = 0;
		while ((len = stream.read(buffer)) > 0)
			bfout.write(buffer, 0, len);

		bfout.close();
		fos.close();
	}

}
