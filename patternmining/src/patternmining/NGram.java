package patternmining;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import opennlp.tools.ngram.NGramModel;
import opennlp.tools.util.StringList;

public class NGram {

	final static Logger LOGGER = LoggerFactory.getLogger(NGram.class);

	public static void main(String[] args) {
		final Tagger tagger = new Tagger(TaggerModel.GERMAN);

		long start = System.currentTimeMillis();
		ArrayList<Review> reviews = DbDriver.getReviews(200000);
		LOGGER.info("{} Reviews extracted that took {} seconds", reviews.size(),
				(System.currentTimeMillis() - start) / 100);

		ArrayList<String> reviewTags = new ArrayList<String>();
		for (Review review : reviews) {
			if (review.getProPOS() != null) {
				reviewTags.add(review.getProPOS());
			}
			if (review.getContraPOS() != null) {
				reviewTags.add(review.getContraPOS());
			}
		}
		start = System.currentTimeMillis();
		HashMap<String, Integer> biGrams = getNGramsWithFrequency(2, reviewTags);
		LOGGER.info("{} different BiGrams in {} sequences identified, that took {} seconds", biGrams.size(),
				reviewTags.size(), (System.currentTimeMillis() - start) / 100);

		start = System.currentTimeMillis();
		HashMap<String, Integer> triGrams = getNGramsWithFrequency(3, reviewTags);
		LOGGER.info("{} different TriGrams in {} sequences identified, that took {} seconds", triGrams.size(),
				reviewTags.size(), (System.currentTimeMillis() - start) / 100);

		start = System.currentTimeMillis();
		writeNGramsToFile(biGrams, "BiGrams.txt");
		writeNGramsToFile(triGrams, "TriGrams.txt");
		LOGGER.info("NGrams sorted and wrote to file, that took {} seconds",
				(System.currentTimeMillis() - start) / 100);

		String test = "Das iPhone hat ein groﬂes Display";
		System.out.println(nGrams(2, test));
		System.out.println(nGrams(3, test));

		System.out.println(tagger.getTagOnlyString(test));

		System.out.println(nGrams(2, tagger.getTagOnlyString(test)));
		System.out.println(nGrams(3, tagger.getTagOnlyString(test)));

		ArrayList<String> reperpetoryGrid = new ArrayList<>();
		reperpetoryGrid.add("No mystique");
		reperpetoryGrid.add("Classy");
		reperpetoryGrid.add("Low price");
		reperpetoryGrid.add("Non european");
		System.out.println(tagger.tagList(reperpetoryGrid));

	}

	private static HashMap<String, Integer> getNGramsWithFrequency(int n, ArrayList<String> nGramList) {
		final NGramModel nGramModel = new NGramModel();
		for (String sequence : nGramList) {
			for (String nGram : nGrams(n, sequence)) {
				nGramModel.add(new StringList(nGram));
			}
		}

		final HashMap<String, Integer> nGramMap = new HashMap<String, Integer>();
		for (String nGram : nGramModel.toDictionary().asStringSet()) {
			nGramMap.put(nGram, nGramModel.getCount(new StringList(nGram)));
		}

		return nGramMap;
	}

	private static void writeNGramsToFile(HashMap<String, Integer> nGramMap, String fileName) {
		Writer fw = null;
		try {
			Map<String, Integer> sortedMap = Util.sortByValue(nGramMap);
			fw = new FileWriter(fileName);
			for (String key : sortedMap.keySet()) {
				fw.write(key + ",");
				fw.append(sortedMap.get(key).toString());
				// float percent = ((float) sortedMap.get(key) * 100 /
				// countnGrams);
				// fw.append(String.format("%.2f", percent));
				fw.append(System.getProperty("line.separator"));
			}
		} catch (IOException e) {
			System.err.println("Could not create File");
		} finally {
			if (fw != null)
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * Taken from http://stackoverflow.com/a/3656824
	 *
	 * @param n
	 * @param str
	 * @return
	 */
	private static List<String> nGrams(int n, String str) {
		List<String> ngrams = new ArrayList<String>();
		String[] words = str.split(" ");
		for (int i = 0; i < words.length - n + 1; i++)
			ngrams.add(concat(words, i, i + n));
		return ngrams;
	}

	private static String concat(String[] words, int start, int end) {
		StringBuilder sb = new StringBuilder();
		for (int i = start; i < end; i++)
			sb.append((i > start ? " " : "") + words[i]);
		return sb.toString();
	}

}
