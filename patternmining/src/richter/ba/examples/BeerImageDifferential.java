package richter.ba.examples;

import java.util.ArrayList;

import richter.ba.utils.Tagger;
import richter.ba.utils.TagSet;

public class BeerImageDifferential {

	public static void main(String[] args) {
		final Tagger tagger = new Tagger(TagSet.GERMAN);

		ArrayList<String> beerAssociations = new ArrayList<>();
		beerAssociations.add("Das iPhone hat ein gro�es Display");
		beerAssociations.add("sympathische Marke");
		beerAssociations.add("guter Geschmack");
		beerAssociations.add("gute Herkunft");
		beerAssociations.add("nat�rliches Bier");
		beerAssociations.add("gute Werbung");

		System.out.println(tagger.tagList(beerAssociations));

	}

}
