import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class FrameDriver extends JFrame{

	private JSplitPane panel0;
	private JPanel panel1;
	private JPanel panel2;
	private JLabel hammingDistlbl;
	private JTextField HammingDist;
	private JSlider slider;
	private JButton showStation;
	private JTextArea stationList;
	private JLabel compareWith;
	private JComboBox stationDropDown;
	private JButton calculateHD;
	private JLabel dist0;
	private JLabel dist1;
	private JLabel dist2;
	private JLabel dist3;
	private JLabel dist4;
	private JButton addStation;
	private JTextField station;
	private static ArrayList<String> STID;
	
	public static void readFile(String filename) throws FileNotFoundException {
		Scanner in = new Scanner(new File(filename));
		STID = new ArrayList<>();;
		while (in.hasNext()) {
			String next = in.nextLine();
			STID.add(next);
			
		}
		in.close();
	}