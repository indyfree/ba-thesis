package richter.ba.db;

import java.util.List;

public class PosSequenceRepository {
	final String URL = "jdbc:mysql://localhost:3306/review_ciao_2014";
	final String USER = "root";
	final String PASSWORD = "root";


	private DbDriver dbDriver;

	public PosSequenceRepository() {
		this.dbDriver = new DbDriver(URL, USER, PASSWORD);
	}

	public List<String> getPosSequences(int n) {
		return this.dbDriver.getPosSequences(n);
	}

	public List<String> getPosSequences() {
		return this.dbDriver.getPosSequences(-1);
	}

	public void writePosSequences(List<String> posSequences) {
		this.dbDriver.writePosSequences(posSequences);
	}
}
