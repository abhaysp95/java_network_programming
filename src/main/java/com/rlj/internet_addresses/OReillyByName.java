package com.rlj.internet_addresses;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.stream.Stream;

public class OReillyByName {

	public static void main(String[] args) {
		try {
			InetAddress[] address = InetAddress.getAllByName("archlinux.org");
			Stream.of(address).forEach(System.out::println);
		} catch (UnknownHostException ex) {
			System.err.println(ex.getMessage());
		}
	}

}
