J7Zip 4.43 alpha 2 : Java 7-Zip handler   (http://p7zip.sourceforge.net/)
---------------------------------------

 J7zip can handle 7-zip archives (http://www.7-zip.org)

 This version can :
 - Lists contents of archive
 - Tests archive files
 - Extracts files from archive to their full paths in the current directory

 This version supports only :
 - non-encrypted archives
 - archives with Copy or LZMA codec.


 J7zip provides also the JAVA LZMA SDK.


 LZMA is default and general compression method of 7z format
 in 7-Zip compression program (www.7-zip.org). LZMA provides high 
 compression ratio and very fast decompression.

 LZMA is an improved version of famous LZ77 compression algorithm. 
 It was improved in way of maximum increasing of compression ratio,
 keeping high decompression speed and low memory requirements for 
 decompressing.


LICENSE
-------
 LGPL (see LGPL.txt)
 read also : http://www.gnu.org/licenses/lgpl-java.html

How To Compile
--------------
 - with ant (any system with http://ant.apache.org/):
    - ant clean   (remove all built items)
    - ant         (build dist/J7zip.jar)


  Warning : this code compiles only with JAVA 5


How To Use
----------

  1) the executable J7zip
  -----------------------

  - Lists contents of archive : 
	java -jar dist/J7zip.jar l archive.7z

	java -jar dist/J7zip.jar l archive.7z file1 file2 file3
	(lists only file1 file2 file3)

  - Tests archive files
	java -jar dist/J7zip.jar t archive.7z

	java -jar dist/J7zip.jar t archive.7z file1 file2 file3
	(tests only file1 file2 file3)

  - Extracts files from archive to their full paths in the current directory
	java -jar dist/J7zip.jar x archive.7z

	java -jar dist/J7zip.jar x archive.7z file1 file2 file3
	(extracts only file1 file2 file3)

  2) the jar JZip.jar
  -------------------
  Please read SevenZip\J7zip.java and SevenZip\ArchiveExtractCallback.java as samples.

   the public API of J7Zip.jar is :
    SevenZip.Archive.SevenZip.Handler
    SevenZip.Archive.SevenZipEntry
    SevenZip.Archive.IArchiveExtractCallback
    SevenZip.Archive.IInArchive 


  3) the executable LZMA
  ----------------------
  bench      : java -cp dist/J7zip.jar SevenZip.LzmaAlone b
  compress   : java -Xmx512m -cp dist/J7zip.jar SevenZip.LzmaAlone e file file.lzma
  decompress : java -cp dist/J7zip.jar SevenZip.LzmaAlone d file.lzma file


TODO
----

	1) support creation of 7-zip archives

	2) improves speed

	3) support compression methods : PPMd, BZip2

	4) support encrypted archives

	5) multithreaded code

	6) support building with gcj
