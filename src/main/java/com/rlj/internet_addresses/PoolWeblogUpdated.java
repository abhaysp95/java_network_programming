package com.rlj.internet_addresses;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class PoolWeblogUpdated {

	private static final int NUM_THREADS = 4;

	public static void main(String ...args) {
		ExecutorService inService = Executors.newFixedThreadPool(NUM_THREADS);
		ExecutorService outService = Executors.newFixedThreadPool(NUM_THREADS);
		Queue<LogEntry> results = new LinkedList<LogEntry>();

		AtomicInteger count = new AtomicInteger(0);

		try (FileInputStream file = new FileInputStream(args[0]);
				BufferedReader reader = new BufferedReader(
					new InputStreamReader(file))) {
			String line = reader.readLine();
			while (null != line) {
				LookupTask task = new LookupTask(line);
				Future<String> future = inService.submit(task);
				LogEntry result = new LogEntry(line, future);
				results.add(result);
				line = reader.readLine();
				count.incrementAndGet();
			}

			while (count.get() != 0) {
				outService.submit(() -> {  // get the output as it comes
					try {
						LogEntry entry = results.remove();
						try {
							System.out.println(entry.future.get());
						} catch(ExecutionException | InterruptedException ex) {
							System.out.println(entry.original);
							System.err.println(ex.getMessage());
						}
						count.decrementAndGet();
					} catch (NoSuchElementException ex) {
						// ... do nothing ?
					}
				});
			}
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
	}

	private static class LogEntry {
		String original;
		Future<String> future;

		LogEntry(String original, Future<String> future) {
			this.original = original;
			this.future = future;
		}
	}
}
