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
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setFields() {
		hammingDistlbl = new JLabel("Enter Hamming Dist:");
		HammingDist = new JTextField(13);
		showStation = new JButton("Show Station");
		stationList = new JTextArea(20, 24);
		compareWith = new JLabel("Compare with:                    ");
		stationDropDown = new JComboBox(STID.toArray());
		//creating slider setting minor tick spacing
		slider = new JSlider(SwingConstants.HORIZONTAL, 1, 4, 1);
		slider.setSize(50, 50);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		//creating table of values to label ticks
		Hashtable<Integer, JLabel> ticks = new Hashtable<>();
		ticks.put(1, new JLabel("1"));
		ticks.put(2, new JLabel ("2"));
		ticks.put(3,  new JLabel ("3"));
		ticks.put(4, new JLabel ("4"));
		slider.setLabelTable(ticks);
		slider.setPaintLabels(true);
	}
	public FrameDriver() {
		super("Hamming Distance");
		setSize(600,800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setFields();
		panel0 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel1, panel2);
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel1.setSize(new Dimension(300, 800));
		panel1.add(hammingDistlbl);
		panel1.add(HammingDist);
		panel1.add(slider);
		panel1.add(showStation);
		panel1.add(stationList);
		panel1.add(compareWith);
		panel1.add(stationDropDown);
		panel0.add(panel1);
		panel0.add(panel2);
		add(panel0);
		panel0.setDividerSize(300);
		setVisible(true);
	}