package com.rlj.threads;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;
import java.util.zip.GZIPOutputStream;

public class GZipRunnable implements Runnable {

	private final File input;
	private Optional<String> parent;

	public GZipRunnable(File input, Optional<String> parent) {
		this.input = input;
		this.parent = parent;
	}

	@Override
	public void run() {
		if (!this.input.getName().endsWith(".gz")) {
			File output = new File(this.parent.orElse(input.getParent()), input.getName() + ".gz");
			if (!output.exists()) {
				try (InputStream in = new BufferedInputStream(new FileInputStream(this.input));
						OutputStream out = new BufferedOutputStream(new GZIPOutputStream(
								new FileOutputStream(output)))) {
					int b;
					while (-1 != (b = in.read())) {
						out.write(b);
					}
					out.flush();
					// System.out.println("=> " + output.getPath());
				} catch (IOException ex) {
					System.out.println(ex);
				}
			}
		}
	}

}
