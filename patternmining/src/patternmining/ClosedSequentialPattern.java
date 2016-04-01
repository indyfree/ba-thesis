package patternmining;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.pfv.spmf.algorithms.sequentialpatterns.BIDE_and_prefixspan_with_strings.AlgoBIDEPlus_withStrings;
import ca.pfv.spmf.input.sequence_database_list_strings.Sequence;
import ca.pfv.spmf.input.sequence_database_list_strings.SequenceDatabase;

public class ClosedSequentialPattern {
	final static Logger LOGGER = LoggerFactory.getLogger(ClosedSequentialPattern.class);
	final static String FILEPATH = "Bide_results.txt";
	final static int NUMBEROFREVIEWS = 50000;

	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		ArrayList<String> sequenceStrings = DbDriver.getReviewPosSequences(NUMBEROFREVIEWS);
		LOGGER.info("{} Reviews extracted, that took {} seconds", sequenceStrings.size(),
				(System.currentTimeMillis() - start) / 100);

		start = System.currentTimeMillis();
		SequenceDatabase sequenceDB = getSequenceDatabase(sequenceStrings);
		LOGGER.info("{} Sequences put in SequenceDatabase, that took {} seconds", sequenceStrings.size(),
				(System.currentTimeMillis() - start) / 100);

		int minSupp = sequenceDB.size() / 4;
		computeClosedSequentialPatternsBIDEPLUS(sequenceDB, minSupp);
	}

	public static void computeClosedSequentialPatternsBIDEPLUS(SequenceDatabase sequenceDB, int minSup) {
		long start;
		AlgoBIDEPlus_withStrings bide = new AlgoBIDEPlus_withStrings();

		try {
			start = System.currentTimeMillis();
			bide.runAlgorithm(sequenceDB, FILEPATH, minSup);
			LOGGER.info("{} Sequences analyzed with BIDE+, that took {} seconds \n", sequenceDB.size(),
					(System.currentTimeMillis() - start) / 100);
		} catch (IOException e) {
			e.printStackTrace();
		}

		bide.printStatistics(sequenceDB.size());
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
			String pattern = "(" + l.substring(0, l.lastIndexOf("-1") - 1).replace(" -1 ", ");(") + ")";
			patternToFrequency.put(pattern, Integer.parseInt(l.substring(l.lastIndexOf(" ") + 1)));

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

			// sequence.addItemset(Arrays.asList(sequenceString.split(" ")));

			for (String item : sequenceString.split(" ")) {
				ArrayList<String> itemSet = new ArrayList();
				itemSet.add(item);
				sequence.addItemset(itemSet);
			}

			sequenceDB.addSequence(sequence);
		}
		return sequenceDB;
	}
}
