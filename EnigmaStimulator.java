// author: Chunyi Lyu 
// Conference project for Data Structures and Algorithms 
// GUI enigma machine stimulator

import javax.swing.*;
import java.awt.*;
import java.awt.event.*; // ActionListener, ActionEvent
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import javax.swing.event.*; // ChangeListener, ChangeEvent

public class EnigmaStimulator extends JFrame {

	// string objects for generating the keyboard
	private static String all = "QWERTYUIOPASDFGHJKLZXCVBNM";
	private static String zero = "QWERTYUIOP";
	private static String first = "ASDFGHJKL";
	private static String second = "ZXCVBNM";

	private JComboBox inner, middle, outer, reflector;
	private JCheckBox file;
	private static final String[] Rotors = { "Rotor I", "Rotor II",
		"Rotor III", "Rotor IV", "Rotor V", "Rotor VI", "Rotor VII",
	"Rotor VIII" };
	private static final String[] Reflectors = { "Reflector B", "Reflector C" };
	private String[] position;
	private int[] pos;
	private ArrayList<JButton> positions;
	private EnigmaMachine machine;
	private JPanel respond0, respond1, respond2, key0, key1, key2;
	private JPanel response;
	private TreeMap<Character, JButton> letterButtons, keys;

	// listener classes
	private class AListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			update0();
		}
	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			redraw();
			for (int i = 0; i < all.length(); i++) {
				Character c = all.charAt(i);
				if (event.getSource().equals(keys.get(c))) {
					char temp = all.charAt(i);
					update1(temp);
				}
			}
			update3();
		}
	}

	private class FileListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			update2();
			update3();
		}
	}

	private class PositionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == positions.get(0)) {
				update4(0);
				update3();
			} else if (event.getSource() == positions.get(1)) {
				update4(1);
				update3();
			} else if (event.getSource() == positions.get(2)) {
				update4(2);
				update3();
			} else {
				update4(3);
				update3();
			}
		}
	}

	// constructor
	public EnigmaStimulator() {
		// initialize all of the listeners
		AListener a1 = new AListener();
		AListener a2 = new AListener();
		AListener a3 = new AListener();
		AListener a4 = new AListener();
		AListener a5 = new AListener();
		ButtonListener b = new ButtonListener();
		FileListener f = new FileListener();
		PositionListener p = new PositionListener();

		// initialize the EnigmaMachine, default positions of all are "A".
		this.pos = new int[4];
		this.position = new String[4];
		this.positions = new ArrayList<JButton>();
		for (int i = 0; i < 4; i++) {
			this.pos[i] = 0;
			this.position[i] = "" + (char) ('A' + this.pos[i]);
			this.positions.add(new JButton(this.position[i]));
			this.positions.get(i).addActionListener(p);
		}

		JPanel position = new JPanel();
		position.setLayout(new GridLayout(1, 5));
		position.add(this.positions.get(0));
		position.add(this.positions.get(1));
		position.add(this.positions.get(2));
		position.add(this.positions.get(3));
		JPanel empty = new JPanel();
		position.add(empty);

		// initialize the EnigmaMachine
		Rotor outer = new Rotor(0, this.pos[0]);
		Rotor middle = new Rotor(0, this.pos[1]);
		Rotor inner = new Rotor(0, this.pos[2]);
		Reflector reflector = new Reflector(0, this.pos[3]);
		this.machine = new EnigmaMachine(reflector, outer, middle, inner);

		this.letterButtons = new TreeMap<Character, JButton>();
		this.keys = new TreeMap<Character, JButton>();

		// add fields to their actionListers
		this.inner = new JComboBox(Rotors);
		this.inner.setSelectedIndex(0);
		this.inner.addActionListener(a1);

		this.middle = new JComboBox(Rotors);
		this.middle.setSelectedIndex(1);
		this.middle.addActionListener(a2);

		this.outer = new JComboBox(Rotors);
		this.outer.setSelectedIndex(2);
		this.outer.addActionListener(a3);

		this.reflector = new JComboBox(Reflectors);
		this.reflector.addActionListener(a4);

		this.file = new JCheckBox("Convert a whole file");
		this.file.addActionListener(f);

		this.respond0 = new JPanel();
		this.respond1 = new JPanel();
		this.respond2 = new JPanel();

		this.key0 = new JPanel();
		this.key1 = new JPanel();
		this.key2 = new JPanel();

		// creates the keyboard for input, and the 'lampboard' for output
		for (int i = 0; i < zero.length(); i++) {
			String now = zero.substring(i, i + 1);
			Character c = now.charAt(0);
			JButton temp0 = new JButton(now);
			this.keys.put(c, temp0);
			this.key0.add(this.keys.get(c));
			this.keys.get(c).addActionListener(b);

			JButton temp = new JButton(now);
			temp.setBackground(Color.GRAY);
			temp.setOpaque(true);
			temp.setBorderPainted(false);

			this.letterButtons.put(c, temp);
			this.respond0.add(this.letterButtons.get(c));
		}

		for (int i = 0; i < first.length(); i++) {
			String now = first.substring(i, i + 1);
			Character c = now.charAt(0);
			JButton temp0 = new JButton(now);

			this.keys.put(c, temp0);
			this.key1.add(this.keys.get(c));
			this.keys.get(c).addActionListener(b);

			JButton temp = new JButton(now);
			temp.setBackground(Color.GRAY);
			temp.setOpaque(true);
			temp.setBorderPainted(false);

			this.letterButtons.put(c, temp);
			this.respond1.add(this.letterButtons.get(c));
		}

		for (int i = 0; i < second.length(); i++) {
			String now = second.substring(i, i + 1);
			Character c = now.charAt(0);
			JButton temp0 = new JButton(now);

			this.keys.put(c, temp0);
			this.key2.add(this.keys.get(c));
			this.keys.get(c).addActionListener(b);

			JButton temp = new JButton(now);
			temp.setBackground(Color.GRAY);
			temp.setOpaque(true);
			temp.setBorderPainted(false);

			this.letterButtons.put(c, temp);
			this.respond2.add(this.letterButtons.get(c));
		}

		// Create the upper control panel
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(1, 5));
		controlPanel.add(this.reflector);
		controlPanel.add(this.outer);
		controlPanel.add(this.middle);
		controlPanel.add(this.inner);
		controlPanel.add(this.file);

		JPanel up = new JPanel();
		up.setLayout(new GridLayout(2, 1));
		up.add(controlPanel);
		up.add(position);

		// create the panel reflects the encoding
		this.response = new JPanel();
		response.add(respond0);
		response.add(respond1);
		response.add(respond2);

		// create the keyboard panel
		JPanel keyboard = new JPanel();
		keyboard.setLayout(new GridLayout(3, 1));
		keyboard.add(key0);
		keyboard.add(key1);
		keyboard.add(key2);

		// put everything together in one panel
		JPanel panel = new JPanel();
		BorderLayout layout = new BorderLayout();
		layout.setVgap(70);
		panel.setLayout(layout);
		panel.setBackground(Color.DARK_GRAY);
		panel.add(up, BorderLayout.NORTH);
		panel.add(this.response, BorderLayout.CENTER);
		panel.add(keyboard, BorderLayout.SOUTH);

		// initialize the overall JFrame
		this.add(panel);
		this.setSize(900, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	// return integer representation of rotors and reflector
	public static int getCodeName(String s) {
		if (s.equals("Rotor I")) {
			return 0;
		} else if (s.equals("Rotor II")) {
			return 1;
		} else if (s.equals("Rotor III")) {
			return 2;
		} else if (s.equals("Rotor IV")) {
			return 3;
		} else if (s.equals("Rotor V")) {
			return 4;
		} else if (s.equals("Rotor VI")) {
			return 5;
		} else if (s.equals("Rotor VII")) {
			return 6;
		} else if (s.equals("Rotor VIII")) {
			return 7;
		} else if (s.equals("Reflector B")) {
			return 0;
		} else if (s.equals("Reflector C")) {
			return 1;
		} else {
			return -1;
		}
	}

	// update EnigmaMachine according to the control panel
	public void update0() {
		String in = (String) this.inner.getSelectedItem();
		String mi = (String) this.middle.getSelectedItem();
		String ou = (String) this.outer.getSelectedItem();
		String re = (String) this.reflector.getSelectedItem();

		int out = getCodeName(ou);
		int mid = getCodeName(mi);
		int inn = getCodeName(in);
		int ref = getCodeName(re);

		System.out.printf("left : %s, mid: %s, right %s, ref: %s \n",
				getCodeName(ou), getCodeName(mi), getCodeName(in),
				getCodeName(re));

		Rotor inner = new Rotor(inn, 0);
		Rotor middle = new Rotor(mid, 0);
		Rotor outer = new Rotor(out, 0);
		Reflector reflector = new Reflector(ref, 0);
		this.machine = new EnigmaMachine(reflector, outer, middle, inner);
	}

	// redraw the response panel to the original state
	public void redraw() {
		for (int i = 0; i < this.letterButtons.size(); i++) {
			Character ch = (char) ('A' + i);
			this.letterButtons.get(ch).setBackground(Color.gray);
		}
	}

	// update the response panel to reflect the encoding, change the background
	// color of one button to be red
	public void update1(char ch) {
		System.out.printf("input: %c \n", ch);
		System.out.printf("left pos: %d \n", this.machine.getPosition(0));
		System.out.printf("middle pos: %d \n", this.machine.getPosition(1));
		System.out.printf("right pos: %d \n", this.machine.getPosition(2));
		System.out.printf("reflector pos: %d \n", this.machine.getPosition(3));

		int e = this.machine.convert(ch);
		System.out.println(e);
		char encode = (char) ('A' + e);
		System.out.println(encode);
		letterButtons.get(encode).setBackground(Color.RED);
	}

	// create a Input Window if the user wants to encode/decode an entire .txt
	// file
	public void update2() {
		if (this.file.isSelected()) {
			String filename = JOptionPane
					.showInputDialog("name of the file that you want to encode/decode:");

			if (filename == null || filename.equals("")) {
				return;
			}
			String output = JOptionPane.showInputDialog("output name:");
			if (output == null || output.equals("")) {
				return;
			}
			machine.convert(filename, output);

		}
	}

	// update the position of rotors and reflector
	public void update3() {
		for (int i = 0; i < 4; i++) {
			this.pos[i] = this.machine.getPosition(i);
			this.position[i] = "" + (char) ('A' + this.pos[i]);
			this.positions.get(i).setLabel(this.position[i]);
		}
	}

	// update rotors' positions accordingly
	public void update4(int i) {
		((Rotor) this.machine.getRotor(i)).rotate();
	}

	// run the program
	public static void main(String[] args) {
		EnigmaStimulator s = new EnigmaStimulator();
	}
}