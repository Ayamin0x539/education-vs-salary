package map;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import CSVParser.USCities;

public class MapUI {
	Browser browser;
	BrowserView view;
	JPanel panel;
	JFrame frame;

	public MapUI(String title, int width, int height, String profession, String location) {
		browser = new Browser();
		view = new BrowserView(browser);
        frame = new JFrame("JxBrowser Google Maps");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(view, BorderLayout.CENTER);
        frame.setSize(1920, 1080);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        USCities cities = new USCities();
        String coordinates = cities.getLocation(location);
        browser.loadURL("https://www.google.com/maps/search/" + (profession + " companies in " + location).replaceAll("\\s", "+") + "+50+miles/@" + coordinates + ",12z/data=!3m1!4b1");
	}
	
	public void execJavaScript(String script) {
		browser.executeJavaScript(script);
	}
	
	public void addTuitionSalaryInfo(String tuition, String salary, String univ) {
		JLabel label = new JLabel("<html><b>Tution</b>: $" + tuition + " at " + univ + "<br />" + "<b>Average Salary</b>: $" + salary);
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(label);
		frame.setJMenuBar(menuBar);
		frame.setVisible(false);
		frame.setVisible(true);
		frame.revalidate();
	}
}
