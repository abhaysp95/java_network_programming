package com.rlj.streams;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class DemoInputStream {

	public void printFromBuffer(byte[] buffer) {
		System.out.println(new String(buffer, StandardCharsets.UTF_8));
	}

	public byte[] readByteByByte(InputStream in)
			throws IOException {
		byte[] buffer = new byte[10];
		for (int i = 0; i < buffer.length; i++) {
			int b = in.read();
			if (-1 == b) {
				break;
			}
			buffer[i] = (byte)b;
		}

		return buffer;
	}

	public byte[] readChunk(InputStream in)
			throws IOException {
		int bytesToRead = 1024;
		int bytesRead = 0;
		byte[] buffer = new byte[bytesToRead + 1];

		while (bytesRead < bytesToRead) {
			bytesToRead += in.read(buffer, bytesRead, bytesToRead - bytesRead);
		}

		return buffer;
	}

	// with check whether the stream actually ended or no data is available in the stream
	public byte[] readChunk2(InputStream in)
			throws IOException {
		int bytesToRead = 1024;
		int bytesRead = 0;
		byte[] buffer = new byte[bytesToRead + 1];

		while (bytesRead < bytesToRead) {
			int result = in.read(buffer, bytesRead, bytesToRead - bytesRead);
			if (-1 == result) {  // end of stream
				in.close();
				break;
			}
			bytesRead += result;
		}

		return buffer;
	}

	public byte[] readUsingAvailabe(InputStream in)
			throws IOException {
		int bytesAvailable = in.available();
		/** NOTE:
		 * Note that while some implementations of `InputStream` will return the total number of bytes in the stream, many will not. It is never correct to use the return value of this method to allocate a buffer intended to hold all data in this stream.
		 */
		byte[] buffer = new byte[bytesAvailable];
		in.read(buffer, 0, bytesAvailable);

		return buffer;
	}
}
