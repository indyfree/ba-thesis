package richter.ba.examples;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import richter.ba.entities.NGram;
import richter.ba.utils.Util;

public class CalculateGoogleNGramFreq {

	final static Logger LOGGER = LoggerFactory.getLogger(CalculateGoogleNGramFreq.class);

	public static void main(String[] args) {
		List<NGram> googleNGrams = readGoogle1Grams("googlebooks-1gram-freq.txt");
		for (NGram nGram : googleNGrams) {
			LOGGER.info("{} has been found in {}% of all NGrams", nGram.getContent(), nGram.getFrequency());
		}
	}

	private static List<NGram> readGoogle1Grams(String path) {
		List<NGram> google1Grams = new ArrayList<NGram>();
		String line;

		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			LOGGER.info("Reading Google Books German 1-Grams");
			int totalCount = 0;
			while ((line = reader.readLine()) != null) {

				String[] tokens = line.split("\\s+");
				String nGram = tokens[0];
				int count = Integer.valueOf(tokens[2]);
				totalCount = Integer.valueOf(tokens[4]);
				double frequency = Util.round(((double) count / totalCount) * 100, 3);
				google1Grams.add(new NGram(nGram, count, frequency));

			}
			LOGGER.info("Total count of: {} 1-Grams", totalCount);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Collections.sort(google1Grams);
		return google1Grams;
	}

}
