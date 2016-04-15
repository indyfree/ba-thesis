package richter.ba.experiment;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import richter.ba.db.PosSequenceRepository;

public class NGramReviews {

	final static Logger LOGGER = LoggerFactory.getLogger(NGramReviews.class);

	public static void main(String[] args) {

		PosSequenceRepository seqRep = new PosSequenceRepository();
		List<String> reviewSequences = seqRep.getPosSequences();
		//
		// NGram uniGram = new NGram(1);
		// uniGram.run(reviewSequences, 10);
		// uniGram.writeNGramsToFile("UniGram_results.txt", 15);
		//
		// NGram biGram = new NGram(2);
		// biGram.run(reviewSequences, 10);
		// biGram.writeNGramsToFile("BiGram_results.txt", 15);
		//
		// NGram triGram = new NGram(3);
		// triGram.run(reviewSequences, 10);
		// triGram.writeNGramsToFile("TriGram_results.txt", 15);

		// NGram tetraGram = new NGram(4);
		// tetraGram.run(reviewSequences, 10);
		//
		// NGram pentaGram = new NGram(5);
		// pentaGram.run(reviewSequences, 10);

		double totalWords = 0;
		for (String sequence : reviewSequences) {
			totalWords += sequence.split(" ").length;
		}
		LOGGER.info("Average word count: {} ", totalWords / reviewSequences.size());

	}

}
