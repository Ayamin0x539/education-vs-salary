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

		browser.loadURL("https://www.google.com/maps/place/University+of+New+Haven/@41.292237,-72.961932,15z/data=!4m2!3m1!1s0x0:0x7e30804fafd0082?sa=X&ved=0ahUKEwiGtYiXyPDLAhVDwiYKHVjRAe0Q_BIIeTAK");
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
