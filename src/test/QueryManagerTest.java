package test;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import querymanager.QueryManager;
import globalconstants.Constants;
import userinput.PromptedInput;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import com.wolfram.alpha.WAEngine;
import com.wolfram.alpha.WAException;
import com.wolfram.alpha.WAPlainText;
import com.wolfram.alpha.WAPod;
import com.wolfram.alpha.WAQuery;
import com.wolfram.alpha.WAQueryResult;
import com.wolfram.alpha.WASubpod;

public class QueryManagerTest {
	
	public static void parseCollegeTuitionQueryTest(String queryString) {
        WAEngine engine = new WAEngine();
        engine.setAppID(Constants.APP_ID);
        engine.addFormat("plaintext");
        WAQuery query = engine.createQuery();
        query.setInput(queryString);
        
        try {
            WAQueryResult queryResult = engine.performQuery(query);
            int tuition = QueryManager.parseCollegeTuitionQuery(queryResult);
            System.out.println("The tuition is $" + tuition + " per year.");
        } catch (WAException e) {
            e.printStackTrace();
        }
	}

	public static void parseSalaryQueryTest(String queryString) {
		WAEngine engine = new WAEngine();
        engine.setAppID(Constants.APP_ID);
        engine.addFormat("plaintext");
        WAQuery query = engine.createQuery();
        query.setInput(queryString);
        
        try {
            WAQueryResult queryResult = engine.performQuery(query);
            int salary = QueryManager.parseSalaryQuery(queryResult);
            System.out.println("The salary is $" + salary + " per year.");
        } catch (WAException e) {
            e.printStackTrace();
        }
	}

	public static void main(String[] args) {
		//parseCollegeTuitionQueryTest("college tuition for university of new haven");
		//parseSalaryQueryTest("average salary in new haven for computer science");
		
        Browser browser = new Browser();
        BrowserView view = new BrowserView(browser);

        JFrame frame = new JFrame("JxBrowser Google Maps");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(view, BorderLayout.CENTER);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        browser.loadURL("https://www.google.com/maps/search/universities+in+new+haven+50+miles/@41.2876503,-73.0250582,12z/data=!3m1!4b1");
	}

}
