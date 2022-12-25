package com.rlj.threads;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import jakarta.xml.bind.DatatypeConverter;

public class DigestRunnable implements Runnable {
	private String filename;

	public DigestRunnable(String filename) {
		this.filename = filename;
	}

	@Override
	public void run() {
		try (FileInputStream in = new FileInputStream(this.filename)) {
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			DigestInputStream din = new DigestInputStream(in, sha);

			while(-1 != din.read());
			din.close();
			byte[] digest = sha.digest();

			StringBuilder builder = new StringBuilder(this.filename);
			builder.append(": ");
			builder.append(DatatypeConverter.printHexBinary(digest));
			System.out.println(builder.toString());

		} catch(IOException ex) {  // FileNotFoundException handled here too
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		} catch(NoSuchAlgorithmException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		for (String filename: args) {
			DigestRunnable dr = new DigestRunnable(filename);
			Thread t = new Thread(dr);
			t.run();
		}
	}
}
