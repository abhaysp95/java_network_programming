package com.rlj.threads;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class InstanceCallbackDigest implements Runnable {

	private final String DIGESTALGO = "SHA-256";

	private String filename;
	private InstanceCallbackDigestUserInterface callback;

	public InstanceCallbackDigest(String filename,
			InstanceCallbackDigestUserInterface callback) {

		this.filename = filename;
		this.callback = callback;
	}

	@Override
	public void run() {
		try {
			InputStream in = new FileInputStream(this.filename);
			MessageDigest sha = MessageDigest.getInstance(DIGESTALGO);
			in = new DigestInputStream(in, sha);

			while (-1 != in.read());
			in.close();
			this.callback.retrieveDigest(sha.digest(), this.filename);

		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		} catch (NoSuchAlgorithmException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

}
