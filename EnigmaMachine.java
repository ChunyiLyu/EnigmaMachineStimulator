// author: Chunyi Lyu 
// Conference project for Data Structures and Algorithms 
// Enigma machine, able to convert an entire file, or word by word

import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;

public class EnigmaMachine {

	private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private Rotor[] rotors;
	private Reflector reflector;

	public EnigmaMachine(Reflector reflector, Rotor outer, Rotor middle,
			Rotor inner) {
		this.rotors = new Rotor[] { outer, middle, inner };
		this.reflector = reflector;
	}

	public int getPosition(int i) {
		if (i < 3) {
			return this.rotors[i].getPosition();
		}
		return this.reflector.getPosition();
	}

	public Object getRotor(int i) {
		if (i < 3) {
			return this.rotors[i];
		}
		return this.reflector;
	}

	public void convert(String filename, String out) {
		try {
			FileReader inReader = new FileReader(filename);
			PrintStream outWriter = new PrintStream(out);

			while (inReader.ready()) {

				Boolean[] checker = new Boolean[3];
				checker[2] = true;
				checker[1] = false;
				checker[0] = false;

				if (this.rotors[2].isNotch()) {
					checker[1] = true;
				}
				if (this.rotors[1].isNotch()) {
					checker[0] = checker[1] = true;
				}

				for (int i = 0; i < 3; i++) {
					if (checker[i]) {
						this.rotors[i].rotate();
					}
				}

				char ch = (char) inReader.read();
				System.out.printf("current character is : %c \n", ch);
				if (Character.isLowerCase(ch)) {
					ch = Character.toUpperCase(ch);
				}

				int index = ALPHABET.indexOf(ch);

				if (index == -1) {
					outWriter.print(ch);
				} else {
					index = this.rotors[2].front(index);
					index = this.rotors[1].front(index);
					index = this.rotors[0].front(index);
					index = this.reflector.front(index);
					index = this.rotors[0].back(index);
					index = this.rotors[1].back(index);
					index = this.rotors[2].back(index);
					char result = ALPHABET.charAt(index);
					outWriter.print(result);
				}
			}
			inReader.close();
			outWriter.close();
		} catch (java.io.IOException e) {
			System.out.println("FILE NOT FOUND");
		}

	}

	public int convert(char ch) {
		Boolean[] checker = new Boolean[3];
		checker[2] = true;
		checker[1] = false;
		checker[0] = false;

		if (this.rotors[2].isNotch()) {
			checker[1] = true;
		}
		if (this.rotors[1].isNotch()) {
			checker[0] = checker[1] = true;
		}

		for (int i = 0; i < 3; i++) {
			if (checker[i]) {
				this.rotors[i].rotate();
			}
		}

		int index = ALPHABET.indexOf(ch);
		index = this.rotors[2].front(index);
		index = this.rotors[1].front(index);
		index = this.rotors[0].front(index);
		index = this.reflector.front(index);
		index = this.rotors[0].back(index);
		index = this.rotors[1].back(index);
		index = this.rotors[2].back(index);
		System.out.printf("output: = %c \n", (char) 'A' + index);
		return index;
	}

	// uncomment for testing
	/*
	 * public static void main(String args[]) { EnigmaMachine c = new
	 * EnigmaMachine(new Rotor(2, 23), new Rotor(3, 11),new Rotor(0, 4), new
	 * Reflector(0, 0)); System.out.printf("left pos: %d \n", c.getPosition(0));
	 * System.out.printf("middle pos: %d \n", c.getPosition(1));
	 * System.out.printf("right pos: %d \n", c.getPosition(2));
	 * System.out.printf("reflector pos: %d \n", c.getPosition(3));
	 * System.out.printf("%c \n", (char) 'A' + c.convert('F'));
	 * System.out.printf("%c \n", (char) 'A' + c.convert('R'));
	 * System.out.printf("%c \n", (char) 'A' + c.convert('O'));
	 * System.out.printf("%c \n", (char) 'A' + c.convert('M')); c.convert("out",
	 * "test.txt"); }
	 */
}
