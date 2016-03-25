package patternmining;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class Tagger {
	private static MaxentTagger tagger;
	private static final String MODEL = "taggers/german-hgc.tagger";
	private static final Logger LOGGER = LoggerFactory.getLogger(Tagger.class);

	public static String getTagString(String stringToTag) {
		if (tagger == null) {
			tagger = new MaxentTagger(MODEL);
		}
		return tagger.tagString(stringToTag);
	}

	public static String getTagOnlyString(String string) {
		if (tagger == null) {
			tagger = new MaxentTagger(MODEL);
		}

		String[] tokens = tagger.tagString(string).split(" ");
		String tagString = "";

		for (int i = 0; i < tokens.length; i++) {
			tokens[i] = tokens[i].substring(tokens[i].lastIndexOf("_") + 1);
			tagString += tokens[i] + " ";
		}
		return tagString;
	}

	public static void tagReviews(ArrayList<Review> list) {

		if (tagger == null) {
			tagger = new MaxentTagger(MODEL);
		}

		for (Review r : list) {
			if (r.getPro() != null) {
				r.setProPOS(getTagString(r.getPro()));
			}
			if (r.getContra() != null) {
				r.setContraPOS(getTagString(r.getContra()));
			}
		}
		LOGGER.info("{} elements tagged", list.size());
	}

	public static ArrayList<String> tagList(ArrayList<String> list) {

		ArrayList<String> taggedList = new ArrayList<>();

		if (tagger == null) {
			tagger = new MaxentTagger(MODEL);
		}

		for (String element : list) {
			if (element != null) {
				taggedList.add(getTagString(element));
			}
		}
		LOGGER.info("{} elements tagged", taggedList.size());
		return taggedList;
	}

}
