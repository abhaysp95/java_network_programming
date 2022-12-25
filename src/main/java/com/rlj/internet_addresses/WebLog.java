package com.rlj.internet_addresses;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class WebLog {

	public static void main(String[] args) {
		try (FileInputStream file = new FileInputStream(args[0]);
				BufferedReader reader = new BufferedReader(
					new InputStreamReader(file))) {

			String entry = reader.readLine();
			while (null != entry) {
				int spaceIndex = entry.indexOf(' ');
				String ip = entry.substring(0, spaceIndex);
				String rest = entry.substring(spaceIndex);

				try {
					InetAddress ia = InetAddress.getByName(ip);
					System.out.println(ia.getHostName() + rest);
				} catch (UnknownHostException ex) {
					System.out.println(ex.getMessage());
				}
			}
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

}
