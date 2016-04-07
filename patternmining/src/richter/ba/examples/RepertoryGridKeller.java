package richter.ba.examples;

import java.util.ArrayList;

import richter.ba.utils.Tagger;
import richter.ba.utils.TaggerModel;

public class RepertoryGridKeller {

	public static void main(String[] args) {
		final Tagger tagger = new Tagger(TaggerModel.ENGLISH);

		ArrayList<String> reperpetoryGrid = new ArrayList<>();
		reperpetoryGrid.add("No mystique");
		reperpetoryGrid.add("Classy");
		reperpetoryGrid.add("Low price");
		reperpetoryGrid.add("Non european");
		System.out.println(tagger.tagList(reperpetoryGrid));

	}

}
