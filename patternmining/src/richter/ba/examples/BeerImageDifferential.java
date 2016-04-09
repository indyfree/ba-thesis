package richter.ba.examples;

import java.util.ArrayList;

import richter.ba.algorithms.NGram;
import richter.ba.utils.TagSet;
import richter.ba.utils.Tagger;

public class BeerImageDifferential {

	public static void main(String[] args) {
		final Tagger tagger = new Tagger(TagSet.GERMAN);

		ArrayList<String> beerAssociations = new ArrayList<>();
		beerAssociations.add("leistungsstark");
		beerAssociations.add("zuverlässig");
		beerAssociations.add("etwas teurer als andere");
		beerAssociations.add("preiswert");
		beerAssociations.add("recht kompliziert");
		beerAssociations.add("gute Qualität");
		beerAssociations.add("günstiger Preis");
		beerAssociations.add("rutscht etwas");

		ArrayList<String> taggedReviews = tagger.onlyTagList(beerAssociations);
		System.out.println(taggedReviews);

		NGram biGram = new NGram(2);
		biGram.run(taggedReviews);

		NGram uniGram = new NGram(1);
		uniGram.run(taggedReviews);
	}

}
