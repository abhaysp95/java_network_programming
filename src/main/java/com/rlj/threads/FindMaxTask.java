package com.rlj.threads;

import java.util.concurrent.Callable;

public class FindMaxTask implements Callable<Integer> {

	private int[] data;
	private int start;
	private int end;

	public FindMaxTask(int[] data, int start, int end) {
		this.data = data;
		this.start = start;
		this.end = end;
	}

	@Override
	public Integer call() {
		int max = -1;
		for (int i = this.start; i < this.end; i++) {
			max = (max < data[i] ? data[i] : max);
		}

		return max;
	}

}
