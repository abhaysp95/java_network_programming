package com.rlj.threads;

import jakarta.xml.bind.DatatypeConverter;

public class CallbackDigestUserInterface {

	// to be called as callback
	public static void recieveDigest(byte[] digest, String filename) {
		StringBuffer buffer = new StringBuffer(filename);
		buffer.append(": ");
		buffer.append(DatatypeConverter.printHexBinary(digest));
		System.out.println(buffer);
	}

	public static void main(String[] args) {
		for (String filename: args) {
			Thread t = new Thread(new CallbackDigest(filename));
			t.start();
		}
		// System.out.println("Main out");  // just a check
	}

}
