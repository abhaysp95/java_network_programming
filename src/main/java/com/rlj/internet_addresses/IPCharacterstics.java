package com.rlj.internet_addresses;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * IPCharacterstics
 */
public class IPCharacterstics {

	public static void main(String ...args) {
		for (String arg: args) {
			try {
				InetAddress address = InetAddress.getByName(arg);

				if (address.isAnyLocalAddress()) {
					System.out.println(address + " is a wildcard address");
				}
				if (address.isLoopbackAddress()) {
					System.out.println(address + " is a loopback address");
				}
				if (address.isLinkLocalAddress()) {
					System.out.println(address + " is a link-local address");
				} else if (address.isSiteLocalAddress()) {
					System.out.println(address + " is a site-local address");
				} else {
					System.out.println(address + " is a global address");
				}

				if (address.isMulticastAddress()) {
					if (address.isMCGlobal()) {
						System.out.println(address + " is a global multicast address");
					} else if (address.isMCOrgLocal()) {
						System.out.println(address + " is a organization wide multicast address");
					} else if (address.isMCSiteLocal()) {
						System.out.println(address + " is a subnet wide multicast address");
					} else if (address.isMCNodeLocal()) {
						System.out.println(address + " is a interface-local mutlicast address");
					} else {
						System.out.println(address + " is a unknown mutlicast address");
					}
				} else {
					System.out.println(address + " is a unicast address");
				}
			} catch(UnknownHostException ex) {
				System.err.println("could not resolve " + arg);
			}
		}
	}
}
