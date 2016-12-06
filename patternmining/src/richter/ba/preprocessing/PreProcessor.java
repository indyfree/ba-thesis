package richter.ba.preprocessing;

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

        // 1. transform pro and contra reviews to a list of strings sequences
		List<String> reviewSequences = reviews.stream().map(r -> r.getProContra()).flatMap(l -> l.stream())
				.collect(Collectors.toList());

        // 2. tokenize list according to defined splitters 
		List<String> tokenizedSequences = Tokenizer.tokenizeSequences(reviewSequences, splitter);

        // 3. remove sequence with a stopwords in it
		List<String> filteredSequences = Filter.filterSequences(tokenizedSequences, stopWords);

        // 4. pos-tag strings according to German Tagset
		Tagger tagger = new Tagger(TagSet.GERMAN);
		List<String> taggedSequences = tagger.onlyTagList(filteredSequences);

        // 5. filter a list of predefined stoptags out
		List<String> filteredTaggedSequences = Filter.filterWords(taggedSequences, stopTags);

		return filteredTaggedSequences;

	}
}
