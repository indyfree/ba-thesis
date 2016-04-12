package richter.ba.examples;

import richter.ba.algorithms.RuleGrowth;
import richter.ba.db.ReviewRepository;

public class RuleGrowthTest {

	public static void main(String[] args) {
		ReviewRepository rr = new ReviewRepository();
		// ArrayList<String> sequenceStringsTokenized =
		// rr.getReviewPosSequences(10);

		RuleGrowth ruleGrowth = new RuleGrowth(2, 0.5);
		// ruleGrowth.run(sequenceStringsTokenized);
	}

}
