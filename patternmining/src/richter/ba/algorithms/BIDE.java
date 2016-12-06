package richter.ba.algorithms;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import ca.pfv.spmf.algorithms.sequentialpatterns.BIDE_and_prefixspan_with_strings.AlgoBIDEPlus_withStrings;
import ca.pfv.spmf.input.sequence_database_list_strings.SequenceDatabase;
import richter.ba.utils.Util;

public class BIDE {
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void printResults() {
		Map<String, Integer> patternToQuantity = Util.readPatternsFromFile(FILEPATH, null);

		for (String pattern : patternToQuantity.keySet()) {
			int support = patternToQuantity.get(pattern);
			float percentage = (float) support * 100 / this.totalSequences;
		}
	}

	public int getMinSup() {
		return minSup;
	}

	public void setMinSup(int minSup) {
		this.minSup = minSup;
	}
}
