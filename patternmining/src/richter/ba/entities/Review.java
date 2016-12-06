package richter.ba.entities;

import java.util.ArrayList;
import java.util.List;

public class Review {

	private int id;
	private String pro;
	private String contra;

	public Review(int id, String pro, String contra) {
		this.id = id;
		this.pro = pro;
		this.contra = contra;
	}

	public int getId() {
		return id;
	}

	public String getPro() {
		return pro;
	}

	public String getContra() {
		return contra;
	}

	public List<String> getProContra() {
		List<String> proContra = new ArrayList<String>();
		proContra.add(pro);
		proContra.add(contra);
		return proContra;
	}
}
