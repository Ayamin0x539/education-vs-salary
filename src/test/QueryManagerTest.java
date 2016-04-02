package test;

import querymanager.QueryManager;
import globalconstants.Constants;
import userinput.PromptedInput;

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
		parseCollegeTuitionQueryTest("college tuition for university of new haven");
		parseSalaryQueryTest("average salary in new haven for computer science");
	}

}
