package richter.ba.examples;

import java.util.ArrayList;

import richter.ba.algorithms.NGram;
import richter.ba.db.ReviewRepository;

public class NGramTest {

	public static void main(String[] args) {
		ReviewRepository rr = new ReviewRepository();
		ArrayList<String> productReviews = rr.getTokenizedReviewPosSequences(100000000);

		// Algorithm biGram = new NGram(2);
		// biGram.run(productReviews);

		NGram triGram = new NGram(2);
		triGram.run(productReviews, 10);
		//
		// BIDE bide = new BIDE(1);
		// bide.run(taggedProductReviews);

	}
}
