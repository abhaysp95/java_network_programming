package com.rlj.threads;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ReturnDigest extends Thread {

	private String filename;
	private byte[] digest;

	public ReturnDigest(String filename) {
		this.filename = filename;
	}

	@Override
	public void run() {
		try {
			InputStream in = new FileInputStream(this.filename);
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			in = new DigestInputStream(in, sha);

			while (-1 != in.read());
			in.close();

			this.digest = sha.digest();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		} catch (NoSuchAlgorithmException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

	public byte[] getDigest() {
		return this.digest;
	}

}
