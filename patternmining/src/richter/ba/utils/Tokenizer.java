package richter.ba.utils;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

	public static List<String> tokenizeSequences(List<String> sequences, String[] splitter) {
		ArrayList<String> tokenizedSequences = new ArrayList<>();
		String regex = regexBuilder(splitter);

		for (String sequence : sequences) {
			if (sequence != null && !sequence.trim().isEmpty()) {
				String temp = sequence.replaceAll("\\s+", " ").trim();
				String[] tokens = temp.split(regex);
				for (String token : tokens) {
					String t = token.trim();
					if (!t.isEmpty()) {
						tokenizedSequences.add(t);
					}
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
