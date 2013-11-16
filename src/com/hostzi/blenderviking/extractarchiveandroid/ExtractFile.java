/**
 * Copyright (C) 2013, Romain Estievenart
 * ExtractFile class
 * @author: Romain Estievenart
 * @date: 2013-11-07
 */
package com.hostzi.blenderviking.extractarchiveandroid;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.github.junrar.testutil.ExtractArchive;
import com.hostzi.blenderviking.extract_archive_android.R;
import com.hostzi.blenderviking.zip.ManipZip;
import com.snda.Andro7z.Andro7za;

/**
 * Extract file archive into a specific output directory
 * 
 * @author: Romain Estievenart
 * 
 */
public class ExtractFile {
	private Context ctx = null;
	private File archive = null, outputdir = null;

	/**
	 * Extract file archive into a specific output directory
	 * 
	 * @param _ctx
	 *            - Context
	 * @param archivePath
	 *            - The archive file path
	 * @param outputDirPath
	 *            - The output directory path
	 * @throws IOException
	 *             - IOException
	 */
	public ExtractFile(Context _ctx, String archivePath, String outputDirPath)
			throws IOException {
		ExtractFileInit(_ctx, new File(archivePath), new File(outputDirPath));
	}

	/**
	 * Extract file archive into a specific output directory
	 * 
	 * @param _ctx
	 *            - Context
	 * @param archive
	 *            - The archive file
	 * @param outputdir
	 *            - The output directory
	 * @throws IOException
	 *             - IOException
	 */
	private void ExtractFileInit(Context _ctx, File archive, File outputdir)
			throws IOException {
		if (!archive.exists())
			throw new FileNotFoundException("Archive file not found : "
					+ archive.getCanonicalPath());
		if (archive.isDirectory())
			throw new IOException("Archive file is a directory : "
					+ archive.getCanonicalPath());
		if (outputdir.exists() && !outputdir.isDirectory())
			throw new IOException("The output directory is not a folder : "
					+ outputdir.getCanonicalPath());

		this.ctx = _ctx;
		this.archive = archive;
		this.outputdir = outputdir;
	}

	/**
	 * Start the extract process
	 * 
	 * @throws IOException
	 */
	public void exec() throws IOException {
		AsyncTask<File, Integer, Boolean> task = null;

		if (archive.getName().matches("(?i)(.*?(.zip|cbz))"))
			task = new ExtractZipFileTask();
		else if (archive.getName().matches("(?i)(.*?(.rar|cbr))"))
			task = new ExtractRarFileTask();
		else if (archive.getName().matches("(?i)(.*?(.7z|cb7))"))
			task = new Extract7zFileTask();
		else
			throw new IOException("loading_file_error");

		if (!outputdir.exists())
			outputdir.mkdirs();

		if (task != null)
			task.execute(archive, outputdir);
	}

	/**
	 * Extract file archive into a specific output directory
	 * 
	 * @param _ctx
	 *            - Context
	 * @param archive
	 *            - The archive file
	 * @param outputdir
	 *            - The output directory
	 * @throws IOException
	 *             - IOException
	 */
	public ExtractFile(Context _ctx, File archive, File outputdir)
			throws IOException {
		ExtractFileInit(_ctx, archive, outputdir);
	}

	/**
	 * Extract zip file with a AsyncTask
	 * 
	 * @author Romain Estievenart
	 * 
	 */
	private class ExtractZipFileTask extends AsyncTask<File, Integer, Boolean> {
		/**
		 * Extract in background a zip archive into a directory File params[0] -
		 * the zip archive; File params[1] - the output directory;
		 */
		@Override
		protected Boolean doInBackground(File... params) {
			boolean result = false;
			if (params.length != 2)
				return result;

			try {
				result = ManipZip.extract(params[0], params[1]);
				return result;
			} catch (IOException e) {
				return result;
			}
		}

		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Toast.makeText(
					ctx,
					(result) ? R.string.loading_file_complete
							: R.string.loading_file_error, Toast.LENGTH_SHORT)
					.show();
		}
	}

	/**
	 * Extract rar file with a AsyncTask
	 * 
	 * @author Romain Estievenart
	 * 
	 */
	private class ExtractRarFileTask extends AsyncTask<File, Integer, Boolean> {
		/**
		 * Call in background the junrar library for extract a rar file File
		 * params[0] : archive file File params[1] : output directory
		 */
		@Override
		protected Boolean doInBackground(File... params) {
			File rarFile = params[0];
			File outputDir = params[1];
			if (ExtractArchive.extractArchive(rarFile, outputDir))
				return true;
			else
				return false;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Toast.makeText(
					ctx,
					(result) ? R.string.loading_file_complete
							: R.string.loading_file_error, Toast.LENGTH_SHORT)
					.show();
		}
	}

	/**
	 * Extract 7z file with a AsyncTask
	 * 
	 * @author Romain Estievenart
	 * 
	 */
	private class Extract7zFileTask extends AsyncTask<File, Integer, Boolean> {
		/**
		 * Call in background the J7zip library for extract a 7z file File
		 * params[0] : archive file File params[1] : output directory
		 */
		@Override
		protected Boolean doInBackground(File... params) {

			try {
				File archive7z = params[0];
				File outputDir = params[1];

				Andro7za a7z = new Andro7za();

				switch (a7z.printUsage(archive7z, outputDir)) {
				case 0: // Successful operation
				case 1: // Non fatal error(s) occured
					return true;
				case 2: // fatal error occured
					System.err.println(ctx
							.getString(R.string.loading_file_error));
					return false;
				case 8: // Not enough memory for operation
					System.err
							.println(ctx
									.getString(R.string.not_enough_memory_for_operation));
				default:
					return false;
				}
			} catch (Exception e) {
				return false;
			}
		}

		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			Toast.makeText(
					ctx,
					(result) ? R.string.loading_file_complete
							: R.string.loading_file_error, Toast.LENGTH_SHORT)
					.show();
		}
	}

}