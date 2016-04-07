package richter.ba.entities;

public class SequentialRule {
	private int[] itemSetI;
	private int[] itemSetJ;
	private int support;
	private double confidence;

	public SequentialRule(int[] itemSetI, int[] itemSetJ, int support, double confidence) {
		this.itemSetI = itemSetI;
		this.itemSetJ = itemSetJ;
		this.support = support;
		this.confidence = confidence;
	}

	public int[] getItemSetI() {
		return itemSetI;
	}

	public int[] getItemSetJ() {
		return itemSetJ;
	}

	public int getSupport() {
		return support;
	}

	public double getConfidence() {
		return confidence;
	}

}
