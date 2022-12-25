package com.rlj.threads;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import jakarta.xml.bind.DatatypeConverter;
// import javax.xml.bind.DatatypeConverter;

public class DigestThread extends Thread {

	private String filename;

	public DigestThread(String filename) {
		this.filename = filename;
	}

	@Override
	public void run() {
		try {
			FileInputStream in = new FileInputStream(filename);
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			DigestInputStream din = new DigestInputStream(in, sha);

			while (-1 != din.read());
			din.close();
			byte[] digest = sha.digest();

			StringBuilder result = new StringBuilder(filename);
			result.append(": ");
			result.append(DatatypeConverter.printHexBinary(digest));
			System.out.println(result);
		} catch (IOException ex) {
			System.out.println(ex);
			ex.printStackTrace();
		} catch (NoSuchAlgorithmException ex) {
			System.out.println(ex);
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		for (String filename: args) {
			Thread t = new DigestThread(filename);
			t.start();
		}
	}
}
