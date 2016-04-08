package richter.ba.db;

import java.util.ArrayList;
import java.util.stream.Collectors;

import richter.ba.entities.Review;

public class ReviewRepository {
	final String URL = "jdbc:mysql://localhost:3306/review_ciao_2014";
	final String USER = "root";
	final String PASSWORD = "root";

	private DbDriver dbDriver;

	public ReviewRepository() {
		this.dbDriver = new DbDriver(URL, USER, PASSWORD);
	}

	public ArrayList<String> getReviewPosSequences(int numberReviews) {
		ArrayList<Review> reviews = dbDriver.getReviews(numberReviews);
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

	public ArrayList<String> getReviewsWithoutStopWords(int numberReviews) {
		ArrayList<Review> reviews = dbDriver.getReviews(numberReviews);

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

	public ArrayList<String> filterStopWords(ArrayList<String> reviews) {

		System.out.println(reviews.stream().filter(r -> r != null).filter(r -> r.contains("siehe"))
				.collect(Collectors.toList()).size());
		//
		// ArrayList<String> stopWords = new ArrayList<String>();
		// stopWords.add("nichts");
		// stopWords.add("siehe");
		//
		// for (String review : reviews) {
		// if (stopWords.contains(review)) {
		// reviews.remove(review);
		// }
		// }
		//
		return reviews;
	}

	public ArrayList<String> getTokenizedReviewPosSequences(int numberReviews) {
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

	public ArrayList<Review> getReviews() {
		return this.dbDriver.getReviews();
	}

	public ArrayList<Review> getReviews(int number) {
		return this.dbDriver.getReviews(number);
	}

	public void updateReviews(ArrayList<Review> reviews) {
		this.dbDriver.updateReviews(reviews);

	}

}
