package com.rlj.internet_addresses;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyAddress {

	public static void main(String[] args) {
		try {
			InetAddress me = InetAddress.getLocalHost();
			String dottedQuad = me.getHostAddress();
			System.out.println("My address: " + dottedQuad + ", " + me);
		} catch (UnknownHostException ex) {
			System.err.println(ex.getMessage());
		}
	}

}
