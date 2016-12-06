package richter.ba.examples;

import java.util.ArrayList;
import java.util.List;

import richter.ba.algorithms.BIDE;
import richter.ba.utils.TagSet;
import richter.ba.utils.Tagger;

public class BeerImageDifferential {

	public static void main(String[] args) {
		final Tagger tagger = new Tagger(TagSet.GERMAN);

		List<String> beerAssociations = new ArrayList<>();
		beerAssociations.add("hohe Qualität");
		beerAssociations.add("sympathische Marke");
		beerAssociations.add("guter Geschmack");
		beerAssociations.add("gute Herkunft");
		beerAssociations.add("natürliches Bier");
		beerAssociations.add("gute Werbung");

		List<String> taggedBeerAssociations = tagger.onlyTagList(beerAssociations);

		final int min_sup = 0;
		BIDE bide = new BIDE(min_sup);
		bide.run(taggedBeerAssociations);
	}

}
