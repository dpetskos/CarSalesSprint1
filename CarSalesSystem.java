import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CarSalesSystem extends JFrame implements ActionListener, ChangeListener {

   public static final double APP_VERSION = 1.0;

   private String file;

   private JMenuBar menuBar = new JMenuBar();
   private JMenu fileMenu = new JMenu("File");
   private JMenuItem aboutItem = new JMenuItem("About");
   private JMenuItem exitItem = new JMenuItem("Exit");

   private JPanel topPanel = new JPanel(new BorderLayout());
   private JPanel titlePanel = new JPanel(new GridLayout(2, 1));
   private JLabel pictureLabel = new JLabel();
   private JLabel carCoLabel = new JLabel("Car Company's Name", JLabel.CENTER);
   private JLabel salesSysLabel = new JLabel("Σύστημα πωλήσεων αυτοκινήτων", JLabel.CENTER);

   private JTabbedPane theTab = new JTabbedPane(JTabbedPane.LEFT);

   private JLabel statusLabel = new JLabel();

   private WindowCloser closer = new WindowCloser();

   public CarSalesSystem(String f) {
	  super("Car Sales");
	  file = f;

	  addWindowListener(closer);
	  theTab.addChangeListener(this);

	  setSize(780, 560);

	  Container c = getContentPane();

	  // create menu bar
	  menuBar.add(fileMenu);
	  fileMenu.add(aboutItem);
	  fileMenu.add(exitItem);
	  aboutItem.addActionListener(this);
	  exitItem.addActionListener(this);

	  // add menu bar
	  setJMenuBar(menuBar);

	  String currentFont = carCoLabel.getFont().getName();
	  carCoLabel.setFont(new Font(currentFont, Font.BOLD, 26));
	  salesSysLabel.setFont(new Font(currentFont, Font.PLAIN, 16));

	  // load the picture into the top panel
	  pictureLabel.setIcon(new ImageIcon("src/uom.png"));
	  titlePanel.add(carCoLabel);
	  titlePanel.add(salesSysLabel);
	  topPanel.add(pictureLabel, "West");
	  topPanel.add(titlePanel, "Center");

	  WelcomePanel welcomePanel = new WelcomePanel(this, f);

	  theTab.add("Main Tab", welcomePanel);

	  theTab.addChangeListener(welcomePanel);

	  theTab.setSelectedIndex(0);

	  // set border on status bar label to make it look like a panel
	  statusLabel.setBorder(new javax.swing.border.EtchedBorder());

	  c.setLayout(new BorderLayout());
	  c.add(topPanel, "North");
	  c.add(theTab, "Center");
	  c.add(statusLabel, "South");

   }

   public void actionPerformed(ActionEvent ev) {
	  if (ev.getSource() == aboutItem) {

	  } else if (ev.getSource() == exitItem)
		 closing();
   }

   public void stateChanged(ChangeEvent ev) {
	  if (ev.getSource() == theTab)
		 statusLabel.setText("Current panel: " + theTab.getTitleAt(theTab.getSelectedIndex()));
   }

   public void closing() {

	  System.exit(0); // shut down jvm
   }

   public static void main(String[] args) {
	  CarSalesSystem carSales = new CarSalesSystem("src/cars.dat");
	  carSales.setVisible(true);
   }

   class WindowCloser extends WindowAdapter {
	  public void windowClosing(WindowEvent ev) {
		 closing();
	  }
   }

}
