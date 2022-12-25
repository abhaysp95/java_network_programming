package com.rlj.threads;

import jakarta.xml.bind.DatatypeConverter;

public class InstanceCallbackDigestUserInterface {

	private String filename;
	private byte[] digest;

	public InstanceCallbackDigestUserInterface(String filename) {
		this.filename = filename;
	}

	public void retrieveDigest(byte[] digest, String filename) {
		this.digest = digest;
		System.out.println(this);  // print the hash
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer(filename);
		buffer.append(": ");
		buffer.append((null != this.digest  // this check seems redundant, cause this method itself will be called from that same thread which will calculate digest and each thread is having seperate object for callback
					? DatatypeConverter.printHexBinary(digest)
					: "Digest not available"));
		return buffer.toString();
	}

	public void calculateDigest() {
		Thread t = new Thread(new InstanceCallbackDigest(this.filename, this));
		t.start();
	}

	public static void main(String[] args) {
		for (String filename: args) {
			InstanceCallbackDigestUserInterface icdui
				= new InstanceCallbackDigestUserInterface(filename);
			icdui.calculateDigest();
		}
	}

}
