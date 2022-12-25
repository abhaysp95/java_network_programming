package com.rlj.internet_addresses;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class InterfaceUtils {
	public static void getInterfaceByName()
			throws SocketException {
		NetworkInterface ni = NetworkInterface.getByName("wlp5s0");
		if (null != ni) {
			System.out.println(ni);
		} else {
			System.out.println("No such interface");
		}
	}

	public static void getInterfaceByInetAddress()
			throws UnknownHostException, SocketException {
		InetAddress ia = InetAddress.getByName("127.0.0.1");
		NetworkInterface ni = NetworkInterface.getByInetAddress(ia);
		if (null != ni) {
			System.out.println(ni);
		} else {
			System.out.println("No such interface");
		}
	}

	public static void main(String[] args) {
		try {
			getInterfaceByName();
			getInterfaceByInetAddress();

			System.out.println("----------------------");

			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			// asIterator() is also present, but it consumes, and written as added functionality for parameters which require Iterator
			while (interfaces.hasMoreElements()) {
				NetworkInterface ni = interfaces.nextElement();
				if (null != ni) {
					System.out.println(ni);
				} else {
					System.out.println("No such interface");
				}
			}
		} catch (SocketException | UnknownHostException ex) {
			System.err.println(ex.getMessage());
		}
	}
}
