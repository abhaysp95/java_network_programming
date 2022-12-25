package com.rlj.threads;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GZipAllFiles {

	public final static int THREAD_COUT = 4;


	public static void main(String ...args) {
		ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUT);

		for (String filename: args) {
			File f = new File(filename);
			// System.out.println("==> " + filename);
			if (f.exists()) {
				if (f.isDirectory()) {
					for (File file: f.listFiles()) {
						if (!file.isDirectory()) {  // single recurse
							Runnable task = new GZipRunnable(file, null);
							pool.submit(task);
						}
					}
				} else {
					pool.submit(new GZipRunnable(f, null));
				}
			}
		}

		pool.shutdown();
	}

}
