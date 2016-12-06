package richter.ba.experiment;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import richter.ba.algorithms.BIDE;
import richter.ba.db.PosSequenceRepository;

public class PatternMiningReviews {

	final static Logger LOGGER = LoggerFactory.getLogger(PatternMiningReviews.class);

	/**
	 * Main Pattern Mining Method. This Method was used to perform Closed
	 * Sequential Pattern Mining on Reviews
	 *
	 * When mining ALL sequences this may run for several hours and take a lot
	 * of resources. Make sure you allocate sufficient memory to the Java
	 * instance. (E.g. pass -Xmx8192m argument)
	 *
	 * To mine less sequences pass number argument to getPosSequences() (E.g.
	 * sequenceRepository.getPosSequences(100000));
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		PosSequenceRepository sequenceRepository = new PosSequenceRepository();
		List<String> reviewSequences = sequenceRepository.getPosSequences(1000000);

		BIDE bide = new BIDE(reviewSequences.size() / 20);
		bide.run(reviewSequences);
	}
}
