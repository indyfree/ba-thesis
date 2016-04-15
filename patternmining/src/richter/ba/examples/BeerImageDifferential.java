package richter.ba.examples;

import java.util.ArrayList;
import java.util.List;

import richter.ba.algorithms.NGramAnalysis;
import richter.ba.utils.TagSet;
import richter.ba.utils.Tagger;

public class BeerImageDifferential {

	public static void main(String[] args) {
		final Tagger tagger = new Tagger(TagSet.GERMAN);

		List<String> beerAssociations = new ArrayList<>();
		beerAssociations.add("leistungsstark");
		beerAssociations.add("zuverlässig");
		beerAssociations.add("etwas teurer als andere");
		beerAssociations.add("preiswert");
		beerAssociations.add("recht kompliziert");
		beerAssociations.add("gute Qualität");
		beerAssociations.add("günstiger Preis");
		beerAssociations.add("rutscht etwas");

		List<String> taggedReviews = tagger.onlyTagList(beerAssociations);
		System.out.println(taggedReviews);

		NGramAnalysis biGram = new NGramAnalysis(2);
		biGram.run(taggedReviews);

		NGramAnalysis uniGram = new NGramAnalysis(1);
		uniGram.run(taggedReviews);
	}

}
