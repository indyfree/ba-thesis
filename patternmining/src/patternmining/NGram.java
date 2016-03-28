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

	public static void main(String[] args) {

		Logger logger = LoggerFactory.getLogger(NGram.class);
		ArrayList<Review> reviews = DbDriver.getReviews(200);
		final int NUMBERREVIEWS = reviews.size();

		NGramModel ngramModel = new NGramModel();

		long startTime = System.currentTimeMillis();

		for (int n = 2; n <= 3; n++) {

			ArrayList<String> ngramList = new ArrayList<String>();
			int countnGrams = 0;

			for (Review r : reviews) {
				if (r.getPro() != null) {
					for (String ngram : ngrams(n, r.getProPOS())) {
						ngramList.add(ngram);
						ngramModel.add(new StringList(ngram));
						countnGrams++;
					}
				}

				if (r.getContra() != null) {
					for (String ngram : ngrams(n, r.getContraPOS())) {
						ngramList.add(ngram);
						ngramModel.add(new StringList(ngram));
						countnGrams++;
					}
				}
			}

			long endTime = System.currentTimeMillis();
			logger.info("{}Gram took {} seconds", n, (endTime - startTime) / 1000);

			Map<String, Integer> ngramMap = new HashMap<String, Integer>();

			startTime = System.currentTimeMillis();
			for (String ngram : ngramList) {
				ngramMap.put(ngram, ngramModel.getCount(new StringList(ngram)));
			}

			endTime = System.currentTimeMillis();
			logger.info("Sorting took {} seconds", (endTime - startTime) / 1000);

			startTime = System.currentTimeMillis();
			Writer fw = null;
			try {

				Map<String, Integer> sortedMap = sortByValue(ngramMap);

				fw = new FileWriter(n + "gram.txt");
				for (String key : sortedMap.keySet()) {
					fw.write(key + ",");
					fw.append(sortedMap.get(key).toString() + ",");
					float percent = ((float) sortedMap.get(key) * 100 / countnGrams);
					fw.append(String.format("%.2f", percent));
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
			endTime = System.currentTimeMillis();
			logger.info("Writing took {} seconds", (endTime - startTime) / 1000);
		}
	}

	/**
	 * Taken from http://stackoverflow.com/a/3656824
	 *
	 * @param n
	 * @param str
	 * @return
	 */
	public static List<String> ngrams(int n, String str) {
		List<String> ngrams = new ArrayList<String>();
		String[] words = str.split(" ");
		for (int i = 0; i < words.length - n + 1; i++)
			ngrams.add(concat(words, i, i + n));
		return ngrams;
	}

	public static String concat(String[] words, int start, int end) {
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
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		Map<K, V> result = new LinkedHashMap<>();
		Stream<Entry<K, V>> st = map.entrySet().stream();

		st.sorted(Comparator.comparing(e -> e.getValue())).forEachOrdered(e -> result.put(e.getKey(), e.getValue()));

		return result;
	}

}
