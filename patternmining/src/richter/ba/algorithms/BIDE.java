package richter.ba.algorithms;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.pfv.spmf.algorithms.sequentialpatterns.BIDE_and_prefixspan_with_strings.AlgoBIDEPlus_withStrings;
import ca.pfv.spmf.input.sequence_database_list_strings.SequenceDatabase;
import richter.ba.utils.Util;

public class BIDE {
	final static Logger LOGGER = LoggerFactory.getLogger(BIDE.class);
	final static String FILEPATH = "BIDE_results.txt";

	private int minSup;
	private int totalSequences;
	private AlgoBIDEPlus_withStrings bide;

	public BIDE(int minSup) {
		this.setMinSup(minSup);
		this.bide = new AlgoBIDEPlus_withStrings();
	}

	public void run(List<String> input) {
		SequenceDatabase sequenceDB = Util.getSequenceDatabase(input);
		this.totalSequences = sequenceDB.size();

		computePatterns(sequenceDB, this.getMinSup());
		printResults();
	}

	public void printStatistics() {
		bide.printStatistics(0);
	}

	private void computePatterns(SequenceDatabase sequenceDB, int minSup) {
		try {
			long start = System.currentTimeMillis();
			this.bide.runAlgorithm(sequenceDB, FILEPATH, minSup);
			LOGGER.info("Mined {} Sequences with BIDE+ in {} seconds", sequenceDB.size(),
					(System.currentTimeMillis() - start) / 100);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void printResults() {
		Map<String, Integer> patternToQuantity = Util.readPatternsFromFile(FILEPATH, null);

		for (String pattern : patternToQuantity.keySet()) {
			int support = patternToQuantity.get(pattern);
			float percentage = (float) support * 100 / this.totalSequences;

			LOGGER.info("{} has been detected {} times and occurs in {}% of all sequences", pattern, support,
					percentage);
		}
	}

	public int getMinSup() {
		return minSup;
	}

	public void setMinSup(int minSup) {
		this.minSup = minSup;
	}
}
