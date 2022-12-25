package com.rlj.internet_addresses;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ReverseTest {

	public static void main(String[] args) {
		try {
			InetAddress ia = InetAddress.getByName("8.8.8.8");
			System.out.println(ia.getCanonicalHostName());
		} catch (UnknownHostException ex) {
			System.err.println(ex.getMessage());
		}
	}

}
