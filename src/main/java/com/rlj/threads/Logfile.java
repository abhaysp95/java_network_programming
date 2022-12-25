package com.rlj.threads;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;

public class Logfile {

	private Writer out;

	/* public Logfile(Writer out) {
		this.out = out;
	} */

	public Logfile(File f) throws IOException {
		this.out = new BufferedWriter(new FileWriter(f));
	}

	// just a demo object
	public String readLogFile() { return new Date().toString(); }

	public synchronized void writeEntry(String msg)
			throws IOException {

		// either synchronize 'out' or this method itself
		// synchronized(this.out) {  // you can use 'this' only to synchronize for Logfile object, whenever writeEntry is called
			Date d = new Date();
			this.out.write(d.toString());
			this.out.write('\t');
			this.out.write(msg);
			this.out.write("\r\n");
		// }
	}

	public void close() throws IOException {
		// this.out.flush();
		this.out.close();  // close() first flushes then closes
	}
}
