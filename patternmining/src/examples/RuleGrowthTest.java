package examples;

import java.util.ArrayList;

import patternmining.DbDriver;
import patternmining.RuleGrowth;

public class RuleGrowthTest {

	public static void main(String[] args) {
		ArrayList<String> sequenceStringsTokenized = DbDriver.getReviewPosSequences(10);

		RuleGrowth ruleGrowth = new RuleGrowth(2, 0.5);
		ruleGrowth.run(sequenceStringsTokenized);
	}

}
