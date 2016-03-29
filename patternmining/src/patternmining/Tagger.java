package patternmining;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class Tagger {
	private MaxentTagger tagger;
	private static final Logger LOGGER = LoggerFactory.getLogger(Tagger.class);

	public Tagger(TaggerModel taggerModel) {
		this.tagger = new MaxentTagger(taggerModel.getModel());
	}

	public String getTagString(String stringToTag) {
		return tagger.tagString(stringToTag);
	}

	public String getTagOnlyString(String string) {
		String[] tokens = tagger.tagString(string).split(" ");
		String tagString = "";

		for (int i = 0; i < tokens.length; i++) {
			tokens[i] = tokens[i].substring(tokens[i].lastIndexOf("_") + 1);
			tagString += tokens[i] + " ";
		}
		return tagString;
	}

	public void tagReviews(ArrayList<Review> list) {
		for (Review r : list) {
			if (r.getPro() != null) {
				r.setProPOS(getTagOnlyString(r.getPro()));
			}
			if (r.getContra() != null) {
				r.setContraPOS(getTagOnlyString(r.getContra()));
			}
		}
		LOGGER.info("{} elements tagged", list.size());
	}

	public ArrayList<String> tagList(ArrayList<String> list) {
		ArrayList<String> taggedList = new ArrayList<>();
		for (String element : list) {
			if (element != null) {
				taggedList.add(getTagString(element));
			}
		}
		LOGGER.info("{} elements tagged", taggedList.size());
		return taggedList;
	}

}
