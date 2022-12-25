package com.rlj.internet_addresses;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;

public class LookupTask implements Callable<String> {

	private String line;

	LookupTask(String line) {
		this.line = line;
	}

	@Override
	public String call() {
		try {
			int spaceIndex = this.line.indexOf(' ');
			String ip = this.line.substring(0, spaceIndex);

			InetAddress ia = InetAddress.getByName(ip);
			StringBuilder builder = new StringBuilder(ia.getHostName());
			builder.append(" ");
			return builder.append(this.line.substring(spaceIndex)).toString();
		} catch (UnknownHostException ex) {
			System.err.println(ex.getMessage());
			return line;
		}
	}

}
