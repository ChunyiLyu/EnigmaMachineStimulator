// author: Chunyi Lyu 
// Conference project for Data Structures and Algorithms 
// The rotor class

public class Rotor {
	private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private int pos, code;

	private static String[] rotors = { "EKMFLGDQVZNTOWYHXUSPAIBRCJ",
			"AJDKSIRUXBLHWTMCQGZNPYFVOE", "BDFHJLCPRTXVZNYEIWGAKMUSQO",
			"ESOVPZJAYQUIRHXLNFTGKDCMWB", "VZBRGITYUPSDNHLXAWMJQOFECK",
			"JPGVOUMFYQBENHZRDKASXLICTW", "NZJHGRCXMYSWBOUFAIVLPEKQDT",
			"FKQHTLXOCBJSPDZRAMEWNIUYGV", };

	private static String[] rotorsBack = { "UWYGADFPVZBECKMTHXSLRINQOJ",
			"AJPCZWRLFBDKOTYUQGENHXMIVS", "TAGBPCSDQEUFVNZHYIXJWLRKOM",
			"HZWVARTNLGUPXQCEJMBSKDYOIF", "QCYLXWENFTZOSMVJUDKGIARPHB",
			"SKXQLHCNWARVGMEBJPTYFDZUIO", "QMGYVPEDRCWTIANUXFKZOSLHJB",
			"QJINSAYDVKBFRUHMCPLEWZTGXO", };

	private static String[] notchPoint = { "Q", "E", "V", "J", "Z", "ZM", "ZM",
			"ZM" };

	public Rotor(int i, int pos) {
		this.pos = pos;
		this.code = i;
	}

	public int getCode() {
		return this.code;
	}

	public static int modole(int a, int b) {
		int x = a % b;
		if (x < 0) {
			x += b;
		}
		return x;
	}

	public void setPosition(int pos) {
		this.pos = pos;
	}

	public int getPosition() {
		return this.pos;
	}

	public void rotate() {
		this.pos++;
		this.pos = pos % 26;
	}

	public void setCode(int pos) {
		for (int i = 0; i < pos; i++) {
			this.rotate();
		}
	}

	public boolean isNotch() {
		if (this.code <= 4) {
			return notchPoint[this.code].charAt(0) == (char) ('A' + this.pos);
		} else {
			return ((char) ('A' + this.pos)) == 'Z'
					|| ((char) ('A' + this.pos)) == 'M';
		}
	}

	public int front(int i) {
		return modole(rotors[this.code].charAt((i + this.pos) % 26) - this.pos
				- 'A', 26);
	}

	public int back(int i) {
		return modole(rotorsBack[this.code].charAt((i + this.pos) % 26)
				- this.pos - 'A', 26);
	}

}
