package com.rlj.threads;

import java.util.List;

public class ProcessEntryWorker {

	private List<String> entries;

	public void processEntry() {

		synchronized(entries) {
			while (entries.isEmpty()) {
				try {
					entries.wait();
				} catch (InterruptedException ex) {
					System.err.println(ex.getMessage());
					return;
				}
			}
			String entry = entries.remove(entries.size() - 1);
			// process the entry
		}

	}

}
