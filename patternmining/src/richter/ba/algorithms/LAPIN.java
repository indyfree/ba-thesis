package richter.ba.algorithms;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.pfv.spmf.algorithms.sequentialpatterns.lapin.AlgoLAPIN_LCI;
import richter.ba.utils.Util;

public class LAPIN implements Algorithm {
	final static Logger LOGGER = LoggerFactory.getLogger(LAPIN.class);
	final static String INPUT_FILEPATH = "LAPIN_input.txt";
	final static String RESULT_FILEPATH = "LAPIN_results.txt";

	private int minSup;
	private int totalSequences;
	private AlgoLAPIN_LCI lapin;
	private Map<String, Integer> posToInt;

	public LAPIN(int minSup) {
		this.setMinSup(minSup);
		this.lapin = new AlgoLAPIN_LCI();
	}

	@Override
	public void run(List<String> input) {
		this.totalSequences = input.size();
		this.posToInt = Util.StringsToIntegerMap(input);

		Util.writeStringSequencesToIntFile(input, this.posToInt, INPUT_FILEPATH);

		computePatterns(this.getMinSup());
		printResults();
	}

	@Override
	public void printStatistics() {
		lapin.printStatistics();
	}

	private void computePatterns(int minSup) {
		try {
			long start = System.currentTimeMillis();
			this.lapin.runAlgorithm(INPUT_FILEPATH, RESULT_FILEPATH, (double) minSup / totalSequences);
			LOGGER.info("Mined {} Sequences with LAPIN in {} seconds", totalSequences,
					(System.currentTimeMillis() - start) / 100);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void printResults() {
		Map<String, Integer> patternToQuantity = Util.readPatternsFromFile(RESULT_FILEPATH, this.posToInt);

		for (String pattern : patternToQuantity.keySet()) {
			int support = patternToQuantity.get(pattern);
			float percentage = (float) support * 100 / this.totalSequences;

			LOGGER.info("{} has been detected {} times and appears in {}% of all sequences", pattern, support,
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
