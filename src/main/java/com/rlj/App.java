package com.rlj;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.rlj.streams.CharacterGeneratorProtocol;
import com.rlj.streams.DemoInputStream;
import com.rlj.streams.readers_writers.DemoStreamReaderWriter;

public class App
{
    public static void main( String[] args )
    {
		try {
			methodForStreams();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

	// Streams
	public static void methodForStreams()
			throws IOException {

		// Demo output stream
		// CharacterGeneratorProtocol cgp = new CharacterGeneratorProtocol();
		// cgp.generateCharactersByteByByte(System.out);
		// cgp.generateCharactersArray(System.out);

		DemoInputStream dis = new DemoInputStream();
		// byte[] output = dis.readByteByByte(App.class.getClassLoader().getResourceAsStream("input.txt"));
		// byte[] output = dis.readChunk(App.class.getClassLoader().getResourceAsStream("input.txt"));
		// byte[] output = dis.readChunk2(App.class.getClassLoader().getResourceAsStream("input.txt"));
		byte[] output = dis.readUsingAvailabe(App.class.getClassLoader().getResourceAsStream("input.txt"));
		dis.printFromBuffer(output);

		/* DemoStreamReaderWriter dsrw = new DemoStreamReaderWriter();
		dsrw.writeOutputStream(new FileOutputStream("OdysseyB.txt"));
		dsrw.getMacCyrillicString(App.class.getClassLoader().getResourceAsStream("OdysseyB.txt")); */
	}
}
