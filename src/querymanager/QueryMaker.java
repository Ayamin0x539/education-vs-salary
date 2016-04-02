package querymanager;

import com.wolfram.alpha.WAEngine;
import com.wolfram.alpha.WAException;
import com.wolfram.alpha.WAQuery;
import com.wolfram.alpha.WAQueryResult;

public class QueryMaker {

	public static WAQueryResult make(WAEngine engine, WAQuery query, String input) {
		query.setInput(input);
		try {
			return engine.performQuery(query);
		} catch (WAException e) {
			System.out.println("Invalid query.");
			e.printStackTrace();
		}
		return null;
	}
}
