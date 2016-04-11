package richter.ba.experiment;

import java.util.List;

import richter.ba.db.PosSequenceRepository;
import richter.ba.db.ReviewRepository;

public class NGramReviews {

	public static void main(String[] args) {

		final String[] splitter = { "\\,", "\\.", "\\:", "\\;", "\\!", "\\?", "\\bund\\b", "\\boder\\b", "\\baber\\b" };
		final String[] stopWords = { "siehe", "-", "nichts", "nix", "entfällt", "s.o." };
		final String[] stopTags = { "$[", "XY" };

		ReviewRepository reviewRep = new ReviewRepository();
		List<String> posSequences = PreProcessor.run(reviewRep.getReviews(10000), splitter, stopWords, stopTags);

		PosSequenceRepository seqRep = new PosSequenceRepository();
		seqRep.writePosSequences(posSequences);
		// NGram biGram = new NGram(2);
		// biGram.run(posSequences, 10);

	}

}
