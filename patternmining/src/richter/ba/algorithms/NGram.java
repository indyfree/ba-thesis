package richter.ba.algorithms;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import opennlp.tools.ngram.NGramGenerator;
import opennlp.tools.ngram.NGramModel;
import opennlp.tools.util.StringList;
import richter.ba.utils.Util;

public class NGram implements Algorithm {

	private int n;
	private HashMap<String, Integer> nGramToQuantity;

	final static Logger LOGGER = LoggerFactory.getLogger(NGram.class);

	public NGram(int n) {
		this.n = n;
	}

	@Override
	public void run(List<String> input) {
		this.nGramToQuantity = getNGramsWithQuantity(n, input);
		this.printResults(this.nGramToQuantity.size());
	}

	public void run(List<String> input, int k) {
		this.nGramToQuantity = getNGramsWithQuantity(n, input);
		this.printResults(k);
	}

	private HashMap<String, Integer> getNGramsWithQuantity(int n, List<String> sequenceList) {
		long startTime = System.currentTimeMillis();

		NGramModel nGramModel = new NGramModel();
		for (String sequence : sequenceList) {

			List<String> nGrams = NGramGenerator.generate(Arrays.asList(sequence.split(" ")), n, " ");

			for (String nGram : nGrams) {
				nGramModel.add(new StringList(nGram));
			}
		}

		HashMap<String, Integer> nGramMap = new HashMap<String, Integer>();
		for (String nGram : nGramModel.toDictionary().asStringSet()) {
			nGramMap.put(nGram, nGramModel.getCount(new StringList(nGram)));
		}

		nGramMap = (HashMap<String, Integer>) Util.sortByValue(nGramMap);

		LOGGER.info("Mined {} Sequences and found {} {}Grams in {} seconds", sequenceList.size(), nGramMap.size(),
				this.n, (System.currentTimeMillis() - startTime) / 100);

		return nGramMap;
	}

	private void printResults(int k) {
		for (int i = 0; i < k; i++) {
			String nGram = (String) nGramToQuantity.keySet().toArray()[i];
			LOGGER.info("[{}] occured {} times", nGram, nGramToQuantity.get(nGram));
		}

	}

	public void writeNGramsToFile(String fileName) {
		Writer fw = null;
		try {
			fw = new FileWriter(fileName);
			for (String key : this.nGramToQuantity.keySet()) {
				fw.write(key + ",");
				fw.append(this.nGramToQuantity.get(key).toString());
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

	// /**
	// * Taken from http://stackoverflow.com/a/3656824
	// *
	// * @param n
	// * @param str
	// * @return
	// */
	// private List<String> nGrams(int n, String str) {
	// List<String> ngrams = new ArrayList<String>();
	// String[] words = str.split(" ");
	// for (int i = 0; i < words.length - n + 1; i++)
	// ngrams.add(concat(words, i, i + n));
	// return ngrams;
	// }
	//
	// private String concat(String[] words, int start, int end) {
	// StringBuilder sb = new StringBuilder();
	// for (int i = start; i < end; i++)
	// sb.append((i > start ? " " : "") + words[i]);
	// return sb.toString();
	// }

	@Override
	public void printStatistics() {
		// TODO Auto-generated method stub

	}

}
