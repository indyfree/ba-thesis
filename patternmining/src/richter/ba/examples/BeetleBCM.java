package richter.ba.examples;

import java.util.ArrayList;

import richter.ba.utils.Tagger;
import richter.ba.utils.TagSet;

public class BeetleBCM {
	public static void main(String[] args) {
		final Tagger tagger = new Tagger(TagSet.ENGLISH);

		ArrayList<String> beetleAssociations = new ArrayList<>();
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

	}

}
