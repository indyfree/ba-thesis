package examples;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.pfv.spmf.algorithms.sequentialpatterns.BIDE_and_prefixspan_with_strings.AlgoBIDEPlus_withStrings;
import ca.pfv.spmf.input.sequence_database_list_strings.SequenceDatabase;
import patternmining.ClosedSequentialPattern;

public class BideTest {
	final static Logger LOGGER = LoggerFactory.getLogger(ClosedSequentialPattern.class);
	final static String FILEPATH = "Bide_test.txt";

	public static void main(String[] args) {

		long start = System.currentTimeMillis();
		AlgoBIDEPlus_withStrings bide = new AlgoBIDEPlus_withStrings();
		ArrayList<String> sequenceStrings = new ArrayList();
		sequenceStrings.add("ADJA NN NN");
		sequenceStrings.add("NN");
		sequenceStrings.add("ADJA NN");
		sequenceStrings.add("ADJA");
		SequenceDatabase sequenceDB = ClosedSequentialPattern.getSequenceDatabase(sequenceStrings);
		LOGGER.info("{} Reviews extracted that took {} seconds", sequenceStrings.size(),
				(System.currentTimeMillis() - start) / 100);

		try {
			bide.runAlgorithm(sequenceDB, FILEPATH, 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ClosedSequentialPattern.printResultsToConsole(FILEPATH, sequenceDB.size());

	}
}
