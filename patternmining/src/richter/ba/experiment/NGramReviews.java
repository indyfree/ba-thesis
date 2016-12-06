package richter.ba.experiment;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import richter.ba.algorithms.NGramAnalysis;
import richter.ba.db.PosSequenceRepository;

public class NGramReviews {

	final static Logger LOGGER = LoggerFactory.getLogger(NGramReviews.class);

	/**
	 * Main N-Gram Analysis Method. This Method was used to perform the N-Gram
	 * Analysis on Product Reviews Database
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		LOGGER.info("Performing N-Gram Analysis on Product Reviews...");
		PosSequenceRepository seqRep = new PosSequenceRepository();
		List<String> reviewSequences = seqRep.getPosSequences();

		NGramAnalysis uniGram = new NGramAnalysis(1);
		uniGram.run(reviewSequences, 5);

		NGramAnalysis biGram = new NGramAnalysis(2);
		biGram.run(reviewSequences, 5);

		NGramAnalysis triGram = new NGramAnalysis(3);
		triGram.run(reviewSequences, 5);

		double totalWords = 0;
		for (String sequence : reviewSequences) {
			totalWords += sequence.split(" ").length;
		}
		LOGGER.info("Average word count: {} ", totalWords / reviewSequences.size());

	}

}
