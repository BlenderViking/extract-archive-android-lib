package com.hostzi.blenderviking.extractarchiveandroid;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.github.junrar.testutil.ExtractArchive;
import com.hostzi.blenderviking.extract_archive_android.R;
import com.hostzi.blenderviking.zip.ManipZip;
import com.snda.Andro7z.Andro7za;

/**
 * Extract file archive into a specific output directory
 * 
 * @author Estievenart Romain
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
		String TAG = getClass().getSimpleName();
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
			outputdir.mkdir();

		if (task != null) {
			try {
				task.execute(archive, outputdir);
				task.get();
			} catch (InterruptedException e) {
				Log.e(TAG, e.getMessage());
			} catch (ExecutionException e) {
				Log.e(TAG, e.getMessage());
			}
		}
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
			String[] str = new String[3];
			str[0] = "x";
			// archive file path
			str[1] = params[0].getAbsolutePath();

			// outputdir path
			str[2] = params[1].getAbsolutePath() + File.separator;

			try {
				// create tmp file
				if(copie7z(params[0]))
				{
				// SevenZip.J7zip.main(str);
					Andro7za a7z = new Andro7za();
					a7z.printUsage();
					
					File destination = new File("/mnt/sdcard/abcdefghijqlmnopqrstuvwxyz123456789.7z");
					if(destination.exists())
						destination.delete();
							
		        }
				return true;
			} catch (Exception e) {
				return false;
			}
		}

		private boolean copie7z(File source) throws IOException {
			File dir = new File("/mnt/sdcard/extractarchiveandroid/");
			if(!dir.exists())
				dir.mkdir();
			else if (!dir.isDirectory())
				return false;
			
			File destination = new File("/mnt/sdcard/abcdefghijqlmnopqrstuvwxyz123456789.7z");
			if(destination.exists())
				destination.delete();
			
			boolean resultat = false;

			java.io.FileInputStream sourceFile = null;
			java.io.FileOutputStream destinationFile = null;
			try {
				destination.createNewFile();
				sourceFile = new java.io.FileInputStream(source);
				destinationFile = new java.io.FileOutputStream(destination);
				byte buffer[] = new byte[512 * 1024];
				int nbLecture;
				while ((nbLecture = sourceFile.read(buffer)) != -1) {
					destinationFile.write(buffer, 0, nbLecture);
				}

				resultat = true;
			} catch (java.io.FileNotFoundException f) {
			} catch (java.io.IOException e) {
			} finally {
				try {
					sourceFile.close();
				} catch (Exception e) {}
				try {
					destinationFile.close();
				} catch (Exception e) {}
			}
			return (resultat);
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