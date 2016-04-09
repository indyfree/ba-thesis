package richter.ba.utils;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

	public static List<String> tokenizeSequences(List<String> sequences, String[] splitter) {
		ArrayList<String> tokenizedSequences = new ArrayList<>();
		String regex = regexBuilder(splitter);
		System.out.println(regex);

		for (String sequence : sequences) {
			String[] token = sequence.split(regex);
			for (String t : token) {
				t = t.trim();
				if (!t.isEmpty()) {
					tokenizedSequences.add(t);
				}
			}
		}
		return tokenizedSequences;
	}

	private static String regexBuilder(String[] elements) {
		String regex = "";

		for (String e : elements) {
			regex += e + "|";
		}
		regex = regex.substring(0, regex.length() - 1);

		return regex;
	}
}
