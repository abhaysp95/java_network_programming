package com.rlj.streams;

import java.io.IOException;
import java.io.OutputStream;

public class CharacterGeneratorProtocol {

	public void generateCharactersByteByByte(OutputStream out)
		throws IOException {

		int firstChar = 33;
		int range = 94;
		int charPerLine = 72;

		int start = firstChar;
		while (true) {
			for (int i = start; i < start + charPerLine; i++) {
				out.write((i - firstChar) % range + firstChar);
			}
			out.write('\r');  // carriage return
			out.write('\n');  // line feed
			start = ((start + 1) - firstChar)
				% range + firstChar;
		}
	}

	public void generateCharactersArray(OutputStream out)
		throws IOException {

		int firstChar = 33;
		int range = 94;
		int charPerLine = 72;

		// write this much at a time
		byte[] line = new byte[charPerLine + 2];

		int start = firstChar;

		while (true) {
			for (int i = start; i < start + charPerLine; i++) {
				line[i - start] = (byte)((i - firstChar) % range + firstChar);
				line[charPerLine] = '\r';
				line[charPerLine + 1] = '\n';
			}
			out.write(line);
			out.flush();  // flushing is necessary (when not, it's very low overhead)

			start = ((start + 1) - firstChar) % range + firstChar;
		}
	}

}

/** Tip
 * 1. Try-with-resources can be used with any object that implement Closable interface (which is almost all of the objects which you need to dispose of )
 */
