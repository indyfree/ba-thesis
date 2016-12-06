package richter.ba.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import opennlp.tools.ngram.NGramGenerator;
import opennlp.tools.ngram.NGramModel;
import opennlp.tools.util.StringList;
import richter.ba.entities.NGram;
import richter.ba.utils.Util;

public class NGramAnalysis {

	private int n;
	private List<NGram> nGrams;

	final static Logger LOGGER = LoggerFactory.getLogger(NGramAnalysis.class);

	public NGramAnalysis(int n) {
		this.n = n;
	}

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
			double frequency = Util.round(((double) count / totalCount) * 100, 3);
			nGrams.add(new NGram(nGram, count, frequency));
		}
		Collections.sort(nGrams);
		return nGrams;
	}

	private void printResults(int k) {
		for (int i = 0; i < k; i++) {
			NGram nGram = this.nGrams.get(i);
			LOGGER.info("[{}] occured {} times, thats {}% of all found {}-Grams", nGram.getContent(), nGram.getCount(),
					nGram.getFrequency(), this.n);
		}

	}
}
