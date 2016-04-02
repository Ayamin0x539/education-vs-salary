package userinput;

import java.util.Scanner;

public class PromptedInput {

	public static String prompt(String promptMessage) {
		System.out.println(promptMessage);
		Scanner scan = new Scanner(System.in);
		String uInput = scan.nextLine();
		return uInput;
	}
}
