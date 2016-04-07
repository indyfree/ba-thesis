package examples;

import java.util.ArrayList;

import patternmining.BIDE;
import patternmining.LAPIN;

public class ClosedSequencesWu {

	public static void main(String[] args) {

		ArrayList<String> sequences = new ArrayList<>();
		sequences.add("A B C D");
		sequences.add("B D E C");
		sequences.add("C F A");
		sequences.add("E A B G C");

		BIDE bide = new BIDE(3);
		bide.run(sequences);
		bide.printStatistics();

		LAPIN lapin = new LAPIN(3);
		lapin.run(sequences);
		lapin.printStatistics();
	}
}
