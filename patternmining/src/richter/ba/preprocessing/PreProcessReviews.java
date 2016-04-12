package richter.ba.preprocessing;

import java.util.List;

import richter.ba.db.PosSequenceRepository;
import richter.ba.db.ReviewRepository;
import richter.ba.entities.Review;

public class PreProcessReviews {

	public static void main(String[] args) {
		ReviewRepository reviewRepo = new ReviewRepository();
		List<Review> reviews = reviewRepo.getReviews();

		final String[] splitter = { "\\,", "\\.", "\\:", "\\;", "\\!", "\\?", "\\bund\\b", "\\boder\\b", "\\baber\\b" };
		final String[] stopWords = { "siehe", "-", "nichts", "nix", "entfällt", "s.o." };
		final String[] stopTags = { "$[", "XY" };
		List<String> reviewSequences = PreProcessor.run(reviews, splitter, stopWords, stopTags);

		PosSequenceRepository posRepo = new PosSequenceRepository();
		posRepo.writePosSequences(reviewSequences);
	}

}
