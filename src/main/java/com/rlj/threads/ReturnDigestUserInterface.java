package com.rlj.threads;

import jakarta.xml.bind.DatatypeConverter;

public class ReturnDigestUserInterface {

	public static void main(String[] args) {
		/* for (String filename: args) {
			ReturnDigest dr = new ReturnDigest(filename);
			dr.start();

			StringBuilder builder = new StringBuilder(filename);
			builder.append(": ");
			builder.append(DatatypeConverter.printHexBinary(dr.getDigest()));

			System.out.println(builder);




			// try {  // a workaround
			// 	dr.join();

			// 	StringBuilder builder = new StringBuilder(filename);
			// 	builder.append(": ");
			// 	builder.append(DatatypeConverter.printHexBinary(dr.getDigest()));

			// 	System.out.println(builder);
			// } catch (InterruptedException ex) {
			// 	ex.printStackTrace();
			// }
		} */

		// race condition (that only, if you're lucky or else nullptrex)
		/* ReturnDigest[] drs = new ReturnDigest[args.length];
		for (int i = 0; i < args.length; i++) {
			drs[i] = new ReturnDigest(args[i]);
			drs[i].start();
		}

		for (int i = 0; i < args.length; i++) {
			StringBuffer buffer = new StringBuffer(args[i]);
			buffer.append(": ");
			buffer.append(DatatypeConverter.printHexBinary(drs[i].getDigest()));
			System.out.println(buffer);
		} */

		// polling
		ReturnDigest[] drs = new ReturnDigest[args.length];
		for (int i = 0; i < args.length; i++) {
			drs[i] = new ReturnDigest(args[i]);
			drs[i].start();
		}

		// for me main thread is busy waiting and digest threads aren't getting chance to calculate direst, thus code is stuck
		for (int i = 0; i < args.length; i++) {
			while (true) {
				byte[] digest = drs[i].getDigest();
				if (null != digest) {
					StringBuffer buffer = new StringBuffer(args[i]);
					buffer.append(": ");
					buffer.append(DatatypeConverter.printHexBinary(digest));
					System.out.println(buffer);
					break;
				}
			}
		}


	}

}
