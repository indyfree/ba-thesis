package richter.ba.db;

import java.util.ArrayList;

import richter.ba.entities.Review;

public class ReviewRepository {
	public static ArrayList<String> getReviewPosSequences(int numberReviews) {
		ArrayList<Review> reviews = DbDriver.getReviews(numberReviews);
		ArrayList<String> reviewPosSequences = new ArrayList<String>();

		for (Review review : reviews) {
			if (review.getProPOS() != null) {
				reviewPosSequences.add(review.getProPOS());
			}
			if (review.getContraPOS() != null) {
				reviewPosSequences.add(review.getContraPOS());
			}
		}
		return reviewPosSequences;
	}

	public static ArrayList<String> getTokenizedReviewPosSequences(int numberReviews) {
		ArrayList<String> sequences = getReviewPosSequences(numberReviews);
		ArrayList<String> tokenizedReviews = new ArrayList<>();

		for (String sequence : sequences) {
			String[] token = sequence.split("\\$,|\\$\\.|\\$\\[|KON");
			for (String t : token) {
				t = t.trim();
				if (!t.isEmpty()) {
					tokenizedReviews.add(t);
				}
			}
		}
		return tokenizedReviews;
	}

}
