package richter.ba.examples;

import richter.ba.algorithms.NGramAnalysis;
import richter.ba.db.ReviewRepository;

public class NGramTest {

	public static void main(String[] args) {
		ReviewRepository rr = new ReviewRepository();
		// List<String> productReviews =
		// rr.getTokenizedReviewPosSequences(100000000);

		// Algorithm biGram = new NGram(2);
		// biGram.run(productReviews);

		NGramAnalysis triGram = new NGramAnalysis(2);
		// triGram.run(productReviews, 10);
		//
		// BIDE bide = new BIDE(1);
		// bide.run(taggedProductReviews);

	}
}
