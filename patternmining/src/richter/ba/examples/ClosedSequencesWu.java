package richter.ba.examples;

import java.util.ArrayList;

import richter.ba.algorithms.BIDE;

public class ClosedSequencesWu {

	/**
	 * Analysis of Figure 3 in Thesis
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		ArrayList<String> sequences = new ArrayList<>();
		sequences.add("A B C D");
		sequences.add("B D E C");
		sequences.add("C F A");
		sequences.add("E A B G C");

		BIDE bide = new BIDE(3);
		bide.run(sequences);
		bide.printStatistics();
	}
}
