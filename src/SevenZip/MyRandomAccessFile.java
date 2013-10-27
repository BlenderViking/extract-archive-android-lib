package SevenZip;

public class MyRandomAccessFile extends SevenZip.IInStream {

	java.io.RandomAccessFile _file;

	MyRandomAccessFile(String filename, String mode) throws java.io.IOException {
		_file = new java.io.RandomAccessFile(filename, mode);
	}

	@Override
	public long Seek(long offset, int seekOrigin) throws java.io.IOException {
		if (seekOrigin == STREAM_SEEK_SET) {
			_file.seek(offset);
		} else if (seekOrigin == STREAM_SEEK_CUR) {
			_file.seek(offset + _file.getFilePointer());
		}
		return _file.getFilePointer();
	}

	@Override
	public int read() throws java.io.IOException {
		return _file.read();
	}

	@Override
	public int read(byte[] data, int off, int size) throws java.io.IOException {
		return _file.read(data, off, size);
	}

	public int read(byte[] data, int size) throws java.io.IOException {
		return _file.read(data, 0, size);
	}

	@Override
	public void close() throws java.io.IOException {
		_file.close();
		_file = null;
	}
}