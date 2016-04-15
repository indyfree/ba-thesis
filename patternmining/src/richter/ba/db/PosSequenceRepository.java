package richter.ba.db;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PosSequenceRepository {
	final String URL = "jdbc:mysql://localhost:3306/review_ciao_2014";
	final String USER = "root";
	final String PASSWORD = "root";

	final static Logger LOGGER = LoggerFactory.getLogger(PosSequenceRepository.class);

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
		long startTime = System.currentTimeMillis();
		this.dbDriver.writePosSequences(posSequences);
		LOGGER.info("Wrote {} Sequences of Pos-tagged Reviews to Database in {} seconds", posSequences.size(),
				(System.currentTimeMillis() - startTime) / 100);
	}
}
