package com.rlj.internet_addresses;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SpamCheck {

	public static final String BLACKHOLE = "zen.spamhaus.org";

	public static boolean isSpammer(String addr) {
		try {
			InetAddress ia = InetAddress.getByName(addr);
			byte[] quad = ia.getAddress();  // last octet is first byte of this array
			String query = BLACKHOLE;
			for (byte b: quad) {  // last octet will be used first
				int unsignedByte = b < 0 ? b + 256 : b;
				query = unsignedByte + "." + query;
			}
			InetAddress.getByName(query);
			return true;
		} catch (UnknownHostException ex) {
			System.err.println(ex.getMessage());
			return false;
		}
	}

	public static void main(String[] args) {
		for (String arg: args) {
			if (isSpammer(arg)) {
				System.out.println("legit");
			} else {
				System.out.println("not legit");
			}
		}
	}

}
