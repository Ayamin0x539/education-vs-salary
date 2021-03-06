package querymanager;

import com.wolfram.alpha.WAPlainText;
import com.wolfram.alpha.WAPod;
import com.wolfram.alpha.WAQueryResult;
import com.wolfram.alpha.WASubpod;

public class QueryManager {

	/**
	 * Parses a query result for of tuition from a college.
	 * @param queryResult The result of a query of the form "college tuition for ${COLLEGE_NAME}".
	 * @return The dollar amount of tuition for a particular college.
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
	
	/**
	 * Parses a query result for the average salary of a profession.
	 * @param queryResult The result of a query of the form "average salary in ${CITY} for ${PROFESSION}".
	 * @return The dollar amount of salary for a particular profession.
	 */
	public static int parseSalaryQuery(WAQueryResult queryResult) {
		int salary = 0;
		if (queryResult.isError()) {
			System.out.println("Query error");
			System.out.println("  error code: " + queryResult.getErrorCode());
			System.out.println("  error message: " + queryResult.getErrorMessage());
		} else if (!queryResult.isSuccess()) {
			System.out.println("Query was not understood; no results available.");
		} else {
			for (WAPod pod : queryResult.getPods()) {
				if (!pod.isError()) {
					if(pod.getTitle().equals("Related occupations")) {
							for (WASubpod subpod : pod.getSubpods()) {
								for (Object element : subpod.getContents()) {
									if (element instanceof WAPlainText) {
										String plaintext_salary_info = ((WAPlainText) element).getText();
										if(plaintext_salary_info.length() == 0 ) {
											System.out.println("Error parsing tuition result."); 
											break;
										}
										String lines[] = plaintext_salary_info.split("\\$");
										String salary_inf = lines[1].substring(0, 10);
										salary  = Integer.parseInt(salary_inf.replaceAll("[\\D]", ""));
										
									}
								}
							}
							break;
					}
				}
			}
		}
		return salary;
	}
	
	/**
	 * Parses a query result for the colleges in a region
	 * @param queryResult The result of a query of the form "colleges in ${REGION}".
	 * @return The array of colleges in that region
	 */
	public static String[] parseCollegesInRegion(WAQueryResult queryResult) {
		String[] colleges = null;
		if (queryResult.isError()) {
			System.out.println("Query error");
			System.out.println("  error code: " + queryResult.getErrorCode());
			System.out.println("  error message: " + queryResult.getErrorMessage());
		} else if (!queryResult.isSuccess()) {
			System.out.println("Query was not understood; no results available.");
		} else {
			for (WAPod pod : queryResult.getPods()) {
				if (!pod.isError()) {
					if(pod.getTitle().equals("Result")) {
							for (WASubpod subpod : pod.getSubpods()) {
								for (Object element : subpod.getContents()) {
									if (element instanceof WAPlainText) {
										String plaintext_colleges = ((WAPlainText) element).getText();
										colleges = plaintext_colleges.split("\\r?\\n");
										colleges[colleges.length - 1] = null;
									}
								}
							}
							break;
					}
				}
			}
		}
		return colleges;
	}

}
