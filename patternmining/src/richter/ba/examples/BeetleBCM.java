package richter.ba.examples;

import java.util.ArrayList;
import java.util.List;

import richter.ba.algorithms.BIDE;
import richter.ba.utils.TagSet;
import richter.ba.utils.Tagger;

public class BeetleBCM {
	public static void main(String[] args) {
		final Tagger tagger = new Tagger(TagSet.ENGLISH);

		List<String> beetleAssociations = new ArrayList<>();
		beetleAssociations.add("fun to drive");
		beetleAssociations.add("neat colors");
		beetleAssociations.add("German car");
		beetleAssociations.add("inexpensive");
		beetleAssociations.add("low sticker price");
		beetleAssociations.add("good gas mileage");
		beetleAssociations.add("good traction");
		beetleAssociations.add("good winter car");
		beetleAssociations.add("easy to park");
		beetleAssociations.add("silver");
		beetleAssociations.add("green");
		beetleAssociations.add("30 mpg");
		beetleAssociations.add("40 mpg");

		System.out.println(tagger.tagList(beetleAssociations));

		List<String> taggedBeetleAssociations = tagger.onlyTagList(beetleAssociations);
		final int min_sup = 0;
		BIDE bide = new BIDE(min_sup);
		bide.run(taggedBeetleAssociations);
	}

}
