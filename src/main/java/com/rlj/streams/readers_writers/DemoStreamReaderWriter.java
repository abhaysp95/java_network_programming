package com.rlj.streams.readers_writers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class DemoStreamReaderWriter {

	public void writeOutputStream(OutputStream out)
			throws UnsupportedEncodingException, IOException {
		OutputStreamWriter writer = new OutputStreamWriter(out, "CP1253");
		writer.write("ἦμος δ΄ ἠριγένεια φάνη ῥοδοδάκτυλος Ἠώς");
	}

	public String getMacCyrillicString(InputStream in)
			throws IOException {

		InputStreamReader reader = new InputStreamReader(in, "MacCyrillic");
		StringBuilder builder = new StringBuilder();
		int c = 0;
		while ((c = reader.read()) != -1) {
			builder.append((char) c);
		}

		return builder.toString();
	}

	public String getMacCyrillicStringBuffered(InputStream in)
			throws IOException {

		BufferedReader reader = new BufferedReader(
				new InputStreamReader(in, "MacCyrillic"));
		StringBuilder builder = new StringBuilder();
		int c = 0;
		while ((c = reader.read()) != -1) {
			builder.append((char) c);
		}

		return builder.toString();

	}
}
