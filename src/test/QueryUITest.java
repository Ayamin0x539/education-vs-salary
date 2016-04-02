package test;

import javax.swing.SwingUtilities;

import com.wolfram.alpha.WAEngine;

import globalconstants.Constants;
import queryui.QueryUI;

public class QueryUITest {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	WAEngine engine = new WAEngine();
		        engine.setAppID(Constants.APP_ID);
		        engine.addFormat("plaintext");
		        QueryUI queryUI = new QueryUI(engine);
		    }
		});
        
	}
}
