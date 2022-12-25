package com.rlj.threads;

import java.io.File;
import java.util.List;

public class ReadLogFileThread {

	private Logfile logfile;
	private Thread[] threads;
	private List<String> entries;

	public ReadLogFileThread() {
		// initialize logfile here
		// have bunch of threads done (either here or some other way
	}

	public void readLogFile() {
		while(true) {
			String entry = logfile.readLogFile();
			if (entry == null) {
				for (Thread thread: threads) thread.interrupt();  // interrupt all the threads
				break;
			}
			synchronized(entries) {
				entries.add(entry);
				entries.notifyAll();
			}
		}
	}

}
