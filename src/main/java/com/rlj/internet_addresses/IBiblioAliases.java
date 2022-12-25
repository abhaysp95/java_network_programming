package com.rlj.internet_addresses;

import java.net.InetAddress;

public class IBiblioAliases {
	public static void main(String[] args) {
		try {
			InetAddress ibiblio = InetAddress.getByName("www.ibiblio.org");
			InetAddress helios = InetAddress.getByName("helios.ibiblio.org");
			if (helios.equals(ibiblio)) {  // InetAddress uses IP address only for hashCode(), thus different domain names can be equal
				System.out.println("same");
			} else {
				System.out.println("same");
			}
		} catch (Exception e) {
			System.err.println("host lookup failed");
		}
	}
}
