package com.rlj.threads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultithreadedMaxFinder {

	public static int max(int[] data)
			throws InterruptedException, ExecutionException { if (1 == data.length) {
			return data[0];
		} else if (0 == data.length) {
			throw new IllegalArgumentException();
		}

		FindMaxTask task1 = new FindMaxTask(data, 0, data.length / 2);
		FindMaxTask task2 = new FindMaxTask(data, data.length / 2, data.length);

		// spawn 2 threads
		ExecutorService service = Executors.newFixedThreadPool(2);

		Future<Integer> res1 = service.submit(task1);
		Future<Integer> res2 = service.submit(task2);

		// Future::get() is blocking call
		return Math.max(res1.get(), res2.get());
	}

}
