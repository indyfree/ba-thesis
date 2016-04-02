package examples;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import patternmining.Algorithm;
import patternmining.ClosedSequentialPattern_BIDE_Memory;
import patternmining.ClosedSequentialPattern_LAPIN_File;
import patternmining.DbDriver;

public class BideTest {
	final static Logger LOGGER = LoggerFactory.getLogger(ClosedSequentialPattern_LAPIN_File.class);
	final static String FILEPATH = "Bide_test.txt";

	public static void main(String[] args) {

		ArrayList<String> sequenceStrings = DbDriver.getReviewPosSequences(100000);
		final int SUPPORT = sequenceStrings.size() / 4;

		//
		// NGram biGram = new NGram(2);
		// biGram.run(sequenceStrings);

		Algorithm lapin = new ClosedSequentialPattern_LAPIN_File(SUPPORT);
		lapin.run(sequenceStrings);
		lapin.printStatistics();

		Algorithm bide = new ClosedSequentialPattern_BIDE_Memory(SUPPORT);
		bide.run(sequenceStrings);
		bide.printStatistics();
	}
}
