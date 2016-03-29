package patternmining;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.pfv.spmf.algorithms.sequentialpatterns.BIDE_and_prefixspan_with_strings.AlgoBIDEPlus_withStrings;
import ca.pfv.spmf.input.sequence_database_list_strings.Sequence;
import ca.pfv.spmf.input.sequence_database_list_strings.SequenceDatabase;

public class ClosedSequentialPattern {
	final static Logger LOGGER = LoggerFactory.getLogger(ClosedSequentialPattern.class);

	public static void main(String[] args) {

		long start = System.currentTimeMillis();
		AlgoBIDEPlus_withStrings bide = new AlgoBIDEPlus_withStrings();
		ArrayList<String> sequenceStrings = DbDriver.getReviewPosSequences(800000);
		SequenceDatabase sequenceDB = getSequenceDatabase(sequenceStrings);
		LOGGER.info("{} Reviews extracted that took {} seconds", sequenceStrings.size(),
				(System.currentTimeMillis() - start) / 100);
		LOGGER.info("DB Size: {}", sequenceDB.size());

		try {
			bide.runAlgorithm(sequenceDB, "result.txt", 200000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static SequenceDatabase getSequenceDatabase(ArrayList<String> stringList) {
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
