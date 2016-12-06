package richter.ba.entities;

public class NGram implements Comparable<NGram> {

	private String content;
	private int count;
	private double frequency;

	public NGram(String content, int count, double frequency) {
		this.setContent(content);
		this.setCount(count);
		this.setFrequency(frequency);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getFrequency() {
		return frequency;
	}

	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}

	@Override
	public int compareTo(NGram o) {
		return o.getCount() - this.count;
	}
}
