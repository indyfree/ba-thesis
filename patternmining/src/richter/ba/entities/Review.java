package richter.ba.entities;

import java.util.ArrayList;
import java.util.List;

public class Review {

	private int id;
	private String pro;
	private String contra;
	private String proPOS;
	private String contraPOS;

	public Review(int id) {
		this.setId(id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<String> getProContra() {
		List<String> proContra = new ArrayList<String>();
		proContra.add(pro);
		proContra.add(contra);
		return proContra;
	}

	public String getPro() {
		return pro;
	}

	public void setPro(String pro) {
		this.pro = pro;
	}

	public String getContra() {
		return contra;
	}

	public void setContra(String contra) {
		this.contra = contra;
	}

	public String getProPOS() {
		return proPOS;
	}

	public void setProPOS(String proPOS) {
		this.proPOS = proPOS;
	}

	public String getContraPOS() {
		return contraPOS;
	}

	public void setContraPOS(String contraPOS) {
		this.contraPOS = contraPOS;
	}

}
