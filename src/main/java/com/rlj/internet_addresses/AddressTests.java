package com.rlj.internet_addresses;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.stream.Stream;

public class AddressTests {

	public static int getVersion(InetAddress ia) {
		byte[] addr = ia.getAddress();
		// Stream.of(addr).forEach(System.out::println);
		return addr.length == 4
			? 4 : (addr.length == 16
					? 16 : -1);
	}

	public static void main(String[] args) {
		try {
			InetAddress me = InetAddress.getLocalHost();
			int res = AddressTests.getVersion(me);
			if (-1 == res) {
				System.out.println("IP version: invalid");
			} else {
				System.out.println("IP version: " + res);
			}
		} catch (UnknownHostException ex) {
			System.err.println(ex.getMessage());
		}
	}

}
