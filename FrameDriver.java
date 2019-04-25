import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Scanner;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.ImageIcon;

public class FrameDriver extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The panel that holds all of the other panels and arranges them correctly
	 */
	private JPanel panel0;
	// All of the panels that are added into panel 0
	private static JPanel panel1;
	private JLabel hammingDistlbl;
	private JTextField HammingDist;
	private JSlider slider;
	private JButton showStation;
	private JTextArea stationList;
	private JLabel compareWith;
	private JComboBox<String> stationDropDown;
	private JButton calculateHD;
	private JTextField distFld0;
	private JLabel dist0;
	private JTextField distFld1;
	private JLabel dist1;
	private JTextField distFld2;
	private JLabel dist2;
	private JTextField distFld3;
	private JLabel dist3;
	private JTextField distFld4;
	private JLabel dist4;
	private JButton addStation;
	private JTextField station;
	private JScrollPane js;
	private static ArrayList<String> STID;
	private BufferedWriter out;
	
	ImageIcon image = new ImageIcon("1.jpg");
	ImageIcon image1 = new ImageIcon("2.jpg");
	ImageIcon image2 = new ImageIcon("3.jpg");
	ImageIcon image3 = new ImageIcon("4.jpg");

	// Array to store distance hammming numbers by index
	private static int[] hammDist;
	private JLabel lblNewLabel;

	public static void readFile(String filename) throws FileNotFoundException {
		Scanner in = new Scanner(new File(filename));
		STID = new ArrayList<>();
		;
		while (in.hasNext()) {
			String next = in.nextLine();
			STID.add(next);

		}
		in.close();
	}


	private void setFields() {
		// The label for the Hamming distance in the top of the GUI panel
		hammingDistlbl = new JLabel("Enter Hamming Dist:");

		// The text field that updates with slider value initial value 1, uneditable
		HammingDist = new JTextField(13);
		HammingDist.setText("1");
		HammingDist.setEditable(false);

		// The show station button that takes the selected station and the selected
		// hamming distance and shows stations that fit
		// the criteria
		showStation = new JButton("Show Station");
		js = new JScrollPane();

		// The calculate button that shows the how many stations have each hamming
		// distance displayed in int values
		calculateHD = new JButton("Calculate HD");
		dist0 = new JLabel("Distance 0:");
		distFld0 = new JTextField(11);
		distFld0.setEditable(false);
		distFld1 = new JTextField(11);
		distFld1.setEditable(false);
		distFld2 = new JTextField(11);
		distFld2.setEditable(false);
		dist4 = new JLabel("Distance 4:");
		distFld4 = new JTextField(11);
		distFld4.setEditable(false);

		// creating slider setting minor tick spacing
		slider = new JSlider(SwingConstants.HORIZONTAL, 1, 4, 1);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);

		// creating table of values to label ticks
		Hashtable<Integer, JLabel> ticks = new Hashtable<>();
		ticks.put(1, new JLabel("1"));
		ticks.put(2, new JLabel("2"));
		ticks.put(3, new JLabel("3"));
		ticks.put(4, new JLabel("4"));
		slider.setLabelTable(ticks);
		slider.setPaintLabels(true);

		// The action listener for the showStation button calculates
		// the hamming distance then sets the text in stationList text
		// area
		showStation.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				stationList.setText("");
				ArrayList<String> a = new ArrayList<>();
				a = calcHammDist(stationDropDown.getSelectedItem().toString(), slider.getValue(), STID);
				for (String c : a) {
					stationList.append(c + "\n");
				}

			}

		});

		// The action listener for calculateHD takens the returned array from
		// CalcHammDist
		// and sets each value to its respective JTextBox
		calculateHD.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				calcHammDist(stationDropDown.getSelectedItem().toString(), slider.getValue(), STID);
				for (int a : hammDist) {
					System.out.println(a);
				}
				distFld0.setText(String.valueOf(hammDist[0]));
				distFld1.setText(String.valueOf(hammDist[1]));
				distFld2.setText(String.valueOf(hammDist[2]));
				distFld3.setText(String.valueOf(hammDist[3]));
				distFld4.setText(String.valueOf(hammDist[4]));
				double rand = Math.random();
				rand = rand * 10;
				if(rand * 10 >= 0 && rand <= 2) {
				lblNewLabel.setIcon(image);
				} else if(rand >= 3 && rand <= 6) {
					lblNewLabel.setIcon(image1);
				} else if (rand >= 7 && rand <= 8) {
					lblNewLabel.setIcon(image2);
				} else if(rand >= 9 && rand <= 10) {
					lblNewLabel.setIcon(image3);
				}
			}
		});
		slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				HammingDist.setText(String.valueOf(slider.getValue()));

			}

		});
	}

	public FrameDriver() {
		super("Hamming Distance");
		setSize(900, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setFields();
		stationDropDown = new JComboBox((STID.toArray()));

		// panel0 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		// BasicSplitPaneUI bsp = (BasicSplitPaneUI)panel0.getUI();
		// bsp.getDivider().setVisible(false);
		panel0 = new JPanel(new BorderLayout());
		// panel0.setLeftComponent(panel1);
		// panel0.setRightComponent(panel2);
		// panel0.setDividerSize(1);

		panel1 = new JPanel();
		JPanel panel3 = new JPanel();
		dist1 = new JLabel("Distance 1:");
		dist2 = new JLabel("Distance 2:");
		dist3 = new JLabel("Distance 3:");
		distFld3 = new JTextField(11);
		distFld3.setEditable(false);
		

		panel0.add(panel1, BorderLayout.CENTER);
		// panel0.setRightComponent(panel2);

		// The compareWith Label
		compareWith = new JLabel("Compare with:");
		
		lblNewLabel = new JLabel("");
		
		GroupLayout gl_panel1 = new GroupLayout(panel1);
		gl_panel1.setHorizontalGroup(
			gl_panel1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel1.createSequentialGroup()
					.addGroup(gl_panel1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel1.createSequentialGroup()
							.addGap(22)
							.addComponent(js, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel1.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel1.createSequentialGroup()
									.addComponent(compareWith)
									.addGap(53)
									.addComponent(stationDropDown, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
								.addComponent(calculateHD)
								.addGroup(gl_panel1.createSequentialGroup()
									.addComponent(hammingDistlbl)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(HammingDist, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
								.addComponent(panel3, 0, 0, Short.MAX_VALUE)
								.addComponent(showStation)
								.addGroup(gl_panel1.createSequentialGroup()
									.addGap(28)
									.addComponent(slider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE))
		);
		gl_panel1.setVerticalGroup(
			gl_panel1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel1.createSequentialGroup()
					.addGroup(gl_panel1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel1.createParallelGroup(Alignment.BASELINE)
								.addComponent(hammingDistlbl)
								.addComponent(HammingDist, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(7)
							.addComponent(slider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(showStation)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(js, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel1.createParallelGroup(Alignment.BASELINE)
								.addComponent(compareWith)
								.addComponent(stationDropDown, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(calculateHD)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel3, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 749, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

		// The text area that shows the results of showStation embedded in a JScrollPane
		stationList = new JTextArea(20, 24);
		js.setViewportView(stationList);

		// Add station button which takes a station id converts it to uppercase and
		// sorts it and redisplays it in JComboBox station
		addStation = new JButton("Add Station");
		station = new JTextField(4);

		// The action listener for addStation, checks to see if
		// the station is already in the array if not it makes the string
		// uppercase and then adds it to the array, sorts the array
		// based on alphabetics and then updates StationDropDown
		addStation.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					out = new BufferedWriter(new FileWriter("Mesonet.txt"));
					if (!STID.contains(station.getText())) {
						STID.add(station.getText());
						Collections.sort(STID, new Comparator<String>() {
							@Override
							public int compare(String s1, String s2) {
								return s1.compareToIgnoreCase(s2);

							}

						});

					}
					for (String str : STID) {
						out.write(str.toUpperCase() + "\n");
					}
					out.close();
					readFile("Mesonet.txt");
					stationDropDown.setModel(new DefaultComboBoxModel<String>((String[]) STID.toArray()));
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		});
		JPanel panel4 = new JPanel();

		panel4.add(addStation);
		panel4.add(station);
		GroupLayout gl_panel3 = new GroupLayout(panel3);
		gl_panel3.setHorizontalGroup(gl_panel3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel3.createSequentialGroup().addGroup(gl_panel3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel3.createSequentialGroup().addGap(20)
								.addGroup(gl_panel3.createParallelGroup(Alignment.LEADING).addComponent(dist0)
										.addComponent(dist1)
										.addGroup(gl_panel3.createParallelGroup(Alignment.TRAILING).addComponent(dist4)
												.addComponent(dist3).addComponent(dist2)))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_panel3.createParallelGroup(Alignment.LEADING)
										.addComponent(distFld2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(distFld3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(distFld1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(distFld4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(distFld0, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel3.createSequentialGroup().addContainerGap().addComponent(panel4,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(32, Short.MAX_VALUE)));
		gl_panel3.setVerticalGroup(gl_panel3.createParallelGroup(Alignment.LEADING).addGroup(gl_panel3
				.createSequentialGroup().addGap(5)
				.addGroup(gl_panel3.createParallelGroup(Alignment.BASELINE).addComponent(dist0).addComponent(distFld0,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(5)
				.addGroup(gl_panel3.createParallelGroup(Alignment.BASELINE).addComponent(dist1).addComponent(distFld1,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(5)
				.addGroup(gl_panel3.createParallelGroup(Alignment.BASELINE).addComponent(dist2).addComponent(distFld2,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel3.createParallelGroup(Alignment.BASELINE).addComponent(dist3).addComponent(distFld3,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel3.createParallelGroup(Alignment.BASELINE).addComponent(dist4).addComponent(distFld4,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addComponent(panel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(46, Short.MAX_VALUE)));
		panel3.setLayout(gl_panel3);
		panel1.setLayout(gl_panel1);

		getContentPane().add(panel0);

		setVisible(true);
	}

	public ArrayList<String> calcHammDist(String name, int HD, ArrayList<String> STID) {
		ArrayList<String> equalStations = new ArrayList<String>();
		hammDist = new int[] { 0, 0, 0, 0, 0 };
		char[] string1 = name.toCharArray();
		for (int city2 = 0; city2 < STID.size(); city2++) {
			int hammingDistance = 0;
			char[] string2 = STID.get(city2).toCharArray();
			for (int index = 0; index < string1.length; index++) {
				if (string1[index] != (string2[index])) {
					hammingDistance++;
				}
			}
			switch (hammingDistance) {
			case 0:
				hammDist[0]++;
				break;
			case 1:
				hammDist[1]++;
				break;
			case 2:
				hammDist[2]++;
				break;
			case 3:
				hammDist[3]++;
				break;
			case 4:
				hammDist[4]++;
				break;
			}
			if (hammingDistance == HD) {
				equalStations.add(STID.get(city2));
			}
		}
		return equalStations;
	}

	public static void main(String[] args) throws FileNotFoundException {
		readFile("Mesonet.txt");
		new FrameDriver();
	}
}
