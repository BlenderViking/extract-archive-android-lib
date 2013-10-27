/*
 * Copyright (c) 2007 innoSysTec (R) GmbH, Germany. All rights reserved.
 * Original author: Edmund Wagner
 * Creation date: 26.11.2007
 *
 * Source: $HeadURL$
 * Last changed: $LastChangedDate$
 * 
 * 
 * the unrar licence applies to all junrar source and binary distributions 
 * you are not allowed to use this source to re-create the RAR compression algorithm
 *
 * Here some html entities which can be used for escaping javadoc tags:
 * "&":  "&#038;" or "&amp;"
 * "<":  "&#060;" or "&lt;"
 * ">":  "&#062;" or "&gt;"
 * "@":  "&#064;" 
 */
package com.github.junrar.rarfile;

import android.util.Log;

import com.github.junrar.io.Raw;

/**
 * Mac File attribute header
 * 
 */
public class MacInfoHeader extends SubBlockHeader {
	private final String TAG = getClass().getSimpleName();

	public static final short MacInfoHeaderSize = 8;

	private int fileType;
	private int fileCreator;

	public MacInfoHeader(com.github.junrar.rarfile.SubBlockHeader subHead,
			byte[] macHeader) {
		super(subHead);
		int pos = 0;
		fileType = Raw.readIntLittleEndian(macHeader, pos);
		pos += 4;
		fileCreator = Raw.readIntLittleEndian(macHeader, pos);
	}

	/**
	 * @return the fileCreator
	 */
	public int getFileCreator() {
		return fileCreator;
	}

	/**
	 * @param fileCreator
	 *            the fileCreator to set
	 */
	public void setFileCreator(int fileCreator) {
		this.fileCreator = fileCreator;
	}

	/**
	 * @return the fileType
	 */
	public int getFileType() {
		return fileType;
	}

	/**
	 * @param fileType
	 *            the fileType to set
	 */
	public void setFileType(int fileType) {
		this.fileType = fileType;
	}

	@Override
	public void print() {
		super.print();
		Log.d(TAG, "filetype: " + fileType);
		Log.d(TAG, "creator :" + fileCreator);
	}

}
