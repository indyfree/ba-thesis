package richter.ba.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Filter {

	public static List<String> filterSequences(List<String> wordSequences, String[] excludeSequenceList) {
		List<String> filteredSequences = new ArrayList<String>();
		for (String sequence : wordSequences) {
			boolean onList = false;
			for (String word : sequence.split(" ")) {
				if (Arrays.asList(excludeSequenceList).contains(word)) {
					onList = true;
				}
			}
			if (!onList) {
				filteredSequences.add(sequence);
			}
		}
		return filteredSequences;
	}

	public static List<String> filterWords(List<String> wordSequences, String[] excludeWordList) {
		List<String> filteredSequences = new ArrayList<String>();
		for (String sequence : wordSequences) {

			String s = sequence;
			for (String stopWord : excludeWordList) {
				s = s.replace(stopWord, "").trim();
			}

			if (!s.equals("")) {
				filteredSequences.add(s);
			}

		}
		return filteredSequences;
	}

}
