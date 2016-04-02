package map;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

public class MapUI {
	Browser browser;
	BrowserView view;
	JPanel panel;
	JFrame frame;

	public MapUI(String title, int width, int height) {
		browser = new Browser();
		view = new BrowserView(browser);
        frame = new JFrame("JxBrowser Google Maps");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(view, BorderLayout.CENTER);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

//		ClassLoader classLoader = this.getClass().getClassLoader();
//		File file = new File(classLoader.getResource("maps.html").getFile());
//		System.out.println(file.getAbsolutePath());

        browser.loadURL("https://www.google.com/maps/search/universities+in+new+haven+50+miles/@41.2876503,-73.0250582,12z/data=!3m1!4b1");
	}
	
	public void execJavaScript(String script) {
		browser.executeJavaScript(script);
	}
	
	public void addTuitionSalaryInfo(String tuition, String salary) {
		JLabel label = new JLabel("<html><b>Tution</b>: " + tuition + "<br />" + "<b>Average Salary</b>: " + salary);
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(label);
		frame.setJMenuBar(menuBar);
		frame.setVisible(false);
		frame.setVisible(true);
		frame.revalidate();
	}
}
