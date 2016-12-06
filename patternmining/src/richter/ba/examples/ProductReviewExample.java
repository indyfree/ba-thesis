package richter.ba.examples;

import java.util.ArrayList;
import java.util.List;

import richter.ba.algorithms.BIDE;
import richter.ba.utils.TagSet;
import richter.ba.utils.Tagger;

public class ProductReviewExample {

	public static void main(String[] args) {
		final Tagger tagger = new Tagger(TagSet.GERMAN);

		List<String> productReviews = new ArrayList<>();
		productReviews.add("leistungsstark");
		productReviews.add("zuverlässig");
		productReviews.add("etwas teurer als andere");
		productReviews.add("preiswert");
		productReviews.add("recht kompliziert");
		productReviews.add("gute Qualität");
		productReviews.add("günstiger Preis");
		productReviews.add("rutscht etwas");

		List<String> taggedProductReviews = tagger.onlyTagList(productReviews);

		final int min_sup = 0;
		BIDE bide = new BIDE(min_sup);
		bide.run(taggedProductReviews);
	}

}
