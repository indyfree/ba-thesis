package richter.ba.experiment;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import richter.ba.algorithms.NGramAnalysis;
import richter.ba.db.PosSequenceRepository;
import richter.ba.utils.PosMapper;

public class NGramReviews {

	final static Logger LOGGER = LoggerFactory.getLogger(NGramReviews.class);

	public static void main(String[] args) {

		PosSequenceRepository seqRep = new PosSequenceRepository();
		List<String> reviewSequences = seqRep.getPosSequences();

		List<String> universalPosSequences = PosMapper.mapTags(reviewSequences, "maps/de-negra.map");

		// NGramAnalysis uniGram = new NGramAnalysis(1);
		// uniGram.run(universalPosSequences, 5);
		//
		// // NGramAnalysis uniGramU = new NGramAnalysis(2);
		// // uniGramU.run(universalPosSequences, 10);
		//
		// NGramAnalysis biGram = new NGramAnalysis(2);
		// biGram.run(universalPosSequences, 5);
		//
		// // uniGram.writeNGramsToFile("UniGram_results.txt", 15);
		//
		// NGramAnalysis triGram = new NGramAnalysis(3);
		// triGram.run(universalPosSequences, 5);
		// triGram.writeNGramsToFile("TriGram_results.txt", 15);

		NGramAnalysis tetraGram = new NGramAnalysis(4);
		tetraGram.run(reviewSequences, 5);

		NGramAnalysis pentaGram = new NGramAnalysis(5);
		pentaGram.run(reviewSequences, 5);

		// double totalWords = 0;
		// for (String sequence : reviewSequences) {
		// totalWords += sequence.split(" ").length;
		// }
		// LOGGER.info("Average word count: {} ", totalWords /
		// reviewSequences.size());

	}

}
