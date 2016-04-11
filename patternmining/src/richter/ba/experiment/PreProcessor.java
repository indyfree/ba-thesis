package richter.ba.experiment;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import richter.ba.entities.Review;
import richter.ba.utils.Filter;
import richter.ba.utils.TagSet;
import richter.ba.utils.Tagger;
import richter.ba.utils.Tokenizer;

public class PreProcessor {

	final static Logger LOGGER = LoggerFactory.getLogger(PreProcessor.class);

	public static List<String> run(List<Review> reviews, String[] splitter, String[] stopWords, String[] stopTags) {

		long startTime = System.currentTimeMillis();
		List<String> reviewSequences = reviews.stream().map(r -> r.getProContra()).flatMap(l -> l.stream())
				.collect(Collectors.toList());
		LOGGER.info("Collected {} Phrases in {} seconds", reviewSequences.size(),
				(System.currentTimeMillis() - startTime) / 100);

		startTime = System.currentTimeMillis();
		List<String> tokenizedSequences = Tokenizer.tokenizeSequences(reviewSequences, splitter);
		LOGGER.info("Tokenized to {} Sequences in {} seconds", tokenizedSequences.size(),
				(System.currentTimeMillis() - startTime) / 100);

		startTime = System.currentTimeMillis();
		List<String> filteredSequences = Filter.filterSequences(tokenizedSequences, stopWords);
		LOGGER.info("Filtered to {} Sequences in {} seconds", filteredSequences.size(),
				(System.currentTimeMillis() - startTime) / 100);

		Tagger tagger = new Tagger(TagSet.GERMAN);
		List<String> taggedSequences = tagger.onlyTagList(filteredSequences);

		startTime = System.currentTimeMillis();
		List<String> filteredTaggedSequences = Filter.filterWords(taggedSequences, stopTags);
		LOGGER.info("Filtered to {} Tag-Sequences in {} seconds", filteredTaggedSequences.size(),
				(System.currentTimeMillis() - startTime) / 100);

		return filteredTaggedSequences;

	}
}
