package map;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

public class MapUI {
	Browser browser;
	BrowserView view;
	JFrame browserFrame;
	
	public MapUI(String title, int width, int height) {
		browser = new Browser();
		browserFrame = new JFrame(title);
        browserFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        browserFrame.add(view, BorderLayout.CENTER);
        browserFrame.setSize(width, height);
        browserFrame.setLocationRelativeTo(null);
        browserFrame.setVisible(true);
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("maps.html").getFile());
        System.out.println(file.getAbsolutePath());
 
        browser.loadURL(file.getAbsolutePath());
	}
}
