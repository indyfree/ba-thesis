package patternmining;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.pfv.spmf.algorithms.sequentialpatterns.BIDE_and_prefixspan_with_strings.AlgoBIDEPlus_withStrings;
import ca.pfv.spmf.input.sequence_database_list_strings.Sequence;
import ca.pfv.spmf.input.sequence_database_list_strings.SequenceDatabase;

public class ClosedSequentialPattern {
	final static Logger LOGGER = LoggerFactory.getLogger(ClosedSequentialPattern.class);
	final static String FILEPATH = "Bide_results.txt";

	public static void main(String[] args) {

		long start = System.currentTimeMillis();
		AlgoBIDEPlus_withStrings bide = new AlgoBIDEPlus_withStrings();
		ArrayList<String> sequenceStrings = DbDriver.getReviewPosSequences(100000);
		SequenceDatabase sequenceDB = getSequenceDatabase(sequenceStrings);
		LOGGER.info("{} Reviews extracted that took {} seconds", sequenceStrings.size(),
				(System.currentTimeMillis() - start) / 100);

		try {
			bide.runAlgorithm(sequenceDB, FILEPATH, 10000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		printResultsToConsole(FILEPATH, sequenceDB.size());

	}

	public static void printResultsToConsole(String path, int totalSequences) {
		ArrayList<String> lines = new ArrayList<>();
		String line;

		HashMap<String, Integer> patternToFrequency = new HashMap<String, Integer>();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		for (String l : lines) {
			patternToFrequency.put(l.substring(0, l.indexOf("-1") - 1),
					Integer.parseInt(l.substring(l.lastIndexOf(" ") + 1)));

		}
		patternToFrequency = (HashMap<String, Integer>) Util.sortByValue(patternToFrequency);
		for (String pattern : patternToFrequency.keySet()) {
			int support = patternToFrequency.get(pattern);
			float percentage = (float) support * 100 / totalSequences;

			LOGGER.info("{} has been detected {} times and occurs in {}% of all sequences", pattern, support,
					percentage);
		}
	}

	public static SequenceDatabase getSequenceDatabase(ArrayList<String> stringList) {
		SequenceDatabase sequenceDB = new SequenceDatabase();

		for (int i = 0; i < stringList.size(); i++) {
			String sequenceString = stringList.get(i);
			Sequence sequence = new Sequence(i);
			sequence.addItemset(Arrays.asList(sequenceString.split(" ")));
			sequenceDB.addSequence(sequence);
		}
		return sequenceDB;
	}
}
