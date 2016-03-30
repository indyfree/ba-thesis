package examples;

import java.util.ArrayList;

import patternmining.Tagger;
import patternmining.TaggerModel;

public class BeerImageDifferential {

	public static void main(String[] args) {
		final Tagger tagger = new Tagger(TaggerModel.GERMAN);

		ArrayList<String> beerAssociations = new ArrayList<>();
		beerAssociations.add("hohe Qualität");
		beerAssociations.add("sympathische Marke");
		beerAssociations.add("guter Geschmack");
		beerAssociations.add("gute Herkunft");
		beerAssociations.add("natürliches Bier");
		beerAssociations.add("gute Werbung");

		System.out.println(tagger.tagList(beerAssociations));

	}

}
