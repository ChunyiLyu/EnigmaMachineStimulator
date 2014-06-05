// author: Chunyi Lyu 
// Conference project for Data Structures and Algorithms 
// The reflector class

public class Reflector extends Rotor {

	private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private int pos, code;

	private String[] Reflectors = { "YRUHQSLDPXNGOKMIEBFZCWVJAT",
			"FVPJIAOYEDRZXWGCTKUQSBNMHL" };

	public Reflector(int i, int pos) {
		super(i, pos);

	}

	public int getCode() {
		return this.code;
	}

	public int getPosition() {
		return this.pos;
	}

	public void rotate() {
		this.pos++;
		this.pos = pos % 26;
	}

	public static int modole(int a, int b) {
		int x = a % b;
		if (x < 0) {
			x += b;
		}
		return x;
	}

	public int front(int i) {
		return modole(Reflectors[this.code].charAt((i + this.pos) % 26)
				- this.pos - 'A', 26);
	}

}
