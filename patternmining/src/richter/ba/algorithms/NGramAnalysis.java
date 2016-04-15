package richter.ba.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import opennlp.tools.ngram.NGramGenerator;
import opennlp.tools.ngram.NGramModel;
import opennlp.tools.util.StringList;
import richter.ba.entities.NGram;

public class NGramAnalysis implements Algorithm {

	private int n;
	private List<NGram> nGrams;

	final static Logger LOGGER = LoggerFactory.getLogger(NGramAnalysis.class);

	public NGramAnalysis(int n) {
		this.n = n;
	}

	@Override
	public void run(List<String> input) {
		this.nGrams = getNGrams(n, input);
		this.printResults(this.nGrams.size());
	}

	public void run(List<String> input, int k) {
		this.nGrams = getNGrams(n, input);
		this.printResults(k);
	}

	private List<NGram> getNGrams(int n, List<String> sequenceList) {
		long startTime = System.currentTimeMillis();
		int totalCount = 0;

		NGramModel nGramModel = new NGramModel();
		for (String sequence : sequenceList) {

			List<String> nGrams = NGramGenerator.generate(Arrays.asList(sequence.split("\\s+")), n, "-");

			for (String nGram : nGrams) {
				nGramModel.add(new StringList(nGram));
				totalCount++;
			}
		}

		List<NGram> nGrams = new ArrayList<NGram>();
		for (String nGram : nGramModel.toDictionary().asStringSet()) {

			int count = nGramModel.getCount(new StringList(nGram));
			double frequency = count / totalCount;
			nGrams.add(new NGram(nGram, count, frequency));
		}

		LOGGER.info("Mined {} Sequences and found {} {}Grams in {} seconds", sequenceList.size(), nGrams.size(), this.n,
				(System.currentTimeMillis() - startTime) / 100);

		return nGrams;
	}

	private void printResults(int k) {
		for (int i = 0; i < k; i++) {
			NGram nGram = this.nGrams.get(i);
			LOGGER.info("[{}] occured {} times, thats {}% of all found {}-Grams", nGram.getContent(), nGram.getCount(),
					nGram.getFrequency(), this.n);
		}

	}

	// public void writeNGramsToFile(String fileName, int k) {
	// Writer fw = null;
	// try {
	// fw = new FileWriter(fileName);
	// for (int i = 0; i < k; i++) {
	// String nGram = (String) nGramToQuantity.keySet().toArray()[i];
	// fw.write(nGram + " : ");
	// fw.append(this.nGramToQuantity.get(nGram).toString());
	// // float percent = ((float) sortedMap.get(key) * 100 /
	// // countnGrams);
	// // fw.append(String.format("%.2f", percent));
	// fw.append(System.getProperty("line.separator"));
	// }
	// } catch (IOException e) {
	// System.err.println("Could not create File");
	// } finally {
	// if (fw != null)
	// try {
	// fw.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// }

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
