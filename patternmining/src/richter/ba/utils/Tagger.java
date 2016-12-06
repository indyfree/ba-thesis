package richter.ba.utils;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class Tagger {
	private MaxentTagger tagger;
	private static final Logger LOGGER = LoggerFactory.getLogger(Tagger.class);

	public Tagger(TagSet taggerModel) {
		this.tagger = new MaxentTagger(taggerModel.getModel());
	}

	public String getTagString(String stringToTag) {
		return tagger.tagString(stringToTag);
	}

	public String getTagOnlyString(String string) {
		String[] tokens = tagger.tagString(string).split("\\s+");
		String tagString = "";

		for (int i = 0; i < tokens.length; i++) {
			tokens[i] = tokens[i].substring(tokens[i].lastIndexOf("_") + 1);
			tagString += tokens[i] + " ";
		}
		return tagString.trim();
	}

	public List<String> tagList(List<String> list) {
		List<String> taggedList = new ArrayList<>();
		for (String element : list) {
			if (element != null) {
				taggedList.add(getTagString(element));
			}
		}
		return taggedList;
	}

	public List<String> onlyTagList(List<String> list) {
		List<String> taggedList = new ArrayList<>();
		for (String element : list) {
			if (element != null) {
				taggedList.add(getTagOnlyString(element));
			}
		}
		return taggedList;
	}

}
