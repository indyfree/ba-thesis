package patternmining;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import algorithm.Algorithm;
import ca.pfv.spmf.algorithms.sequential_rules.rulegrowth.AlgoRULEGROWTH;

public class RuleGrowth implements Algorithm {

	final static Logger LOGGER = LoggerFactory.getLogger(RuleGrowth.class);
	final static String INPUT_FILEPATH = "RuleGrowth_input.txt";
	final static String RESULT_FILEPATH = "RuleGrowth_results.txt";

	private int minSup;
	private double minConf;
	private int totalSequences;
	private AlgoRULEGROWTH ruleGrowth;
	private Map<String, Integer> posToInt;

	public RuleGrowth(int minSup, double minConf) {
		this.minSup = minSup;
		this.minConf = minConf;
		this.ruleGrowth = new AlgoRULEGROWTH();
	}

	@Override
	public void run(List<String> input) {
		this.totalSequences = input.size();
		this.posToInt = Util.StringsToIntegerMap(input);

		Util.writeStringSequencesToIntFile(input, this.posToInt, INPUT_FILEPATH);

		this.computeRules(this.minSup, this.minConf);
		this.printResults();

	}

	private void computeRules(int minSup, double minConf) {
		try {
			long start = System.currentTimeMillis();
			this.ruleGrowth.runAlgorithm(INPUT_FILEPATH, RESULT_FILEPATH, minSup, minConf);
			LOGGER.info("Mined {} Sequences with RuleGrowth in {} seconds", totalSequences,
					(System.currentTimeMillis() - start) / 100);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void printResults() {
		for (SequentialRule rule : Util.readRulesFromFile(this.RESULT_FILEPATH, this.posToInt)) {
			for (int setElement : rule.getItemSetI()) {
				System.out.print(setElement + ",");
			}
			System.out.print(" ===> ");
			for (int setElement : rule.getItemSetJ()) {
				System.out.print(setElement + ",");
			}
			System.out.print(" Support: " + rule.getSupport() + "\n");

		}
	}

	@Override
	public void printStatistics() {
		this.ruleGrowth.printStats();

	}

}
