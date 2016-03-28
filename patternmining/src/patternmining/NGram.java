package patternmining;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import opennlp.tools.ngram.NGramModel;
import opennlp.tools.util.StringList;

public class NGram {

	final static Logger logger = LoggerFactory.getLogger(NGram.class);

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		ArrayList<Review> reviews = DbDriver.getReviews(200000);
		logger.info("{} Reviews extracted that took {} seconds", reviews.size(),
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
		logger.info("{} different BiGrams in {} sequences identified, that took {} seconds", biGrams.size(),
				reviewTags.size(), (System.currentTimeMillis() - start) / 100);

		start = System.currentTimeMillis();
		HashMap<String, Integer> triGrams = getNGramsWithFrequency(3, reviewTags);
		logger.info("{} different TriGrams in {} sequences identified, that took {} seconds", triGrams.size(),
				reviewTags.size(), (System.currentTimeMillis() - start) / 100);

		start = System.currentTimeMillis();
		writeNGramsToFile(biGrams, "BiGrams.txt");
		writeNGramsToFile(triGrams, "TriGrams.txt");
		logger.info("NGrams sorted and wrote to file, that took {} seconds",
				(System.currentTimeMillis() - start) / 100);
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
			Map<String, Integer> sortedMap = sortByValue(nGramMap);
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

	/**
	 * Taken from http://stackoverflow.com/a/2581754
	 *
	 * @param map
	 * @return
	 */
	private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		Map<K, V> result = new LinkedHashMap<>();
		Stream<Entry<K, V>> st = map.entrySet().stream();

		st.sorted(Comparator.comparing(e -> e.getValue())).forEachOrdered(e -> result.put(e.getKey(), e.getValue()));

		return result;
	}

}
