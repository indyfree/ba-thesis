package richter.ba.examples;

import java.util.ArrayList;
import java.util.Arrays;

import richter.ba.algorithms.Algorithm;
import richter.ba.algorithms.BIDE;
import richter.ba.algorithms.NGram;
import richter.ba.utils.Tagger;
import richter.ba.utils.TagSet;

public class NGramTest {

	public static void main(String[] args) {
		ArrayList<String> productReviews = new ArrayList<>();

		productReviews.addAll(Arrays.asList("Super Aufnahmen, gute Bedienung, wertig".split(",")));
		productReviews.addAll(Arrays.asList("klein, leicht, schönes Design".split(",")));
		productReviews.add("Der Akku");
		productReviews.add("Der Sucher fehlt");

		Tagger tagger = new Tagger(TagSet.GERMAN);

		ArrayList<String> taggedProductReviews = tagger.onlyTagList(productReviews);

		Algorithm biGram = new NGram(2);
		biGram.run(taggedProductReviews);

		BIDE bide = new BIDE(1);
		bide.run(taggedProductReviews);

	}
}
