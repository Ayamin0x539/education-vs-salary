package querymanager;

import com.wolfram.alpha.WAPlainText;
import com.wolfram.alpha.WAPod;
import com.wolfram.alpha.WAQueryResult;
import com.wolfram.alpha.WASubpod;

public class QueryManager {

	/**
	 * Returns the dollar amount cost per year of college tuition
	 * resulted from a particular query.
	 * @param queryResult
	 * @return
	 */
	public static int parseCollegeTuitionQuery(WAQueryResult queryResult) {
		int tuition = 0;
		if (queryResult.isError()) {
			System.out.println("Query error");
			System.out.println("  error code: " + queryResult.getErrorCode());
			System.out.println("  error message: " + queryResult.getErrorMessage());
		} else if (!queryResult.isSuccess()) {
			System.out.println("Query was not understood; no results available.");
		} else {
			// Got a result.
			System.out.println("Successful query. Pods follow:\n");
			for (WAPod pod : queryResult.getPods()) {
				if (!pod.isError()) {
					// System.out.println(pod.getTitle());
					if(pod.getTitle().equals("Result")) {
							for (WASubpod subpod : pod.getSubpods()) {
								for (Object element : subpod.getContents()) {
									if (element instanceof WAPlainText) {
										String plaintext_tuition = ((WAPlainText) element).getText();
										if(plaintext_tuition.length() == 0 || plaintext_tuition.charAt(0) != '$') {
											System.out.println("Error parsing tuition result."); 
											break;
										}
										// Replaces all non-digits with the empty string.
										tuition = Integer.parseInt(plaintext_tuition.replaceAll("[\\D]", ""));
									}
								}
							}
							break;
					}
				}
			}
		}
		return tuition;
	}

}
