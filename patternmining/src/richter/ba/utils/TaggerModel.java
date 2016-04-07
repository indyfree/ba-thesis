package richter.ba.utils;

public enum TaggerModel {
	GERMAN("taggers/german-hgc.tagger"), ENGLISH("taggers/english-left3words-distsim.tagger");

	private final String model;

	TaggerModel(String model) {
		this.model = model;
	}

	public String getModel() {
		return model;
	}

}
