package richter.ba.examples;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import richter.ba.algorithms.Algorithm;
import richter.ba.algorithms.BIDE;
import richter.ba.algorithms.LAPIN;
import richter.ba.db.DbDriver;

public class BideTest {
	final static Logger LOGGER = LoggerFactory.getLogger(LAPIN.class);

	public static void main(String[] args) {

		// ArrayList<String> sequenceStrings =
		// DbDriver.getReviewPosSequences(500000);
		ArrayList<String> sequenceStringsTokenized = DbDriver.getTokenizedReviewPosSequences(100000);
		final int SUPPORT = sequenceStringsTokenized.size() / 20;

		//
		// NGram biGram = new NGram(2);
		// biGram.run(sequenceStrings);

		// Algorithm lapin = new LAPIN(SUPPORT);
		// lapin.run(sequenceStringsTokenized);
		// lapin.printStatistics();
		//
		Algorithm bide = new BIDE(SUPPORT);
		bide.run(sequenceStringsTokenized);
		bide.printStatistics();
	}
}
