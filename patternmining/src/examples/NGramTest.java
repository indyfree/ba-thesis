package examples;

import java.util.ArrayList;
import java.util.Arrays;

import algorithm.Algorithm;
import patternmining.BIDE;
import patternmining.NGram;
import patternmining.Tagger;
import patternmining.TaggerModel;

public class NGramTest {

	public static void main(String[] args) {
		ArrayList<String> productReviews = new ArrayList<>();

		productReviews.addAll(Arrays.asList("Super Aufnahmen, gute Bedienung, wertig".split(",")));
		productReviews.addAll(Arrays.asList("klein, leicht, sch�nes Design".split(",")));
		productReviews.add("Der Akku");
		productReviews.add("Der Sucher fehlt");

		Tagger tagger = new Tagger(TaggerModel.GERMAN);

		ArrayList<String> taggedProductReviews = tagger.onlyTagList(productReviews);

		Algorithm biGram = new NGram(2);
		biGram.run(taggedProductReviews);

		BIDE bide = new BIDE(1);
		bide.run(taggedProductReviews);

	}
}
