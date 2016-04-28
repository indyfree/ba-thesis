package richter.ba.experiment;

import java.util.List;

import richter.ba.algorithms.BIDE;
import richter.ba.db.PosSequenceRepository;

public class PatternMiningReviews {

	public static void main(String[] args) {

		PosSequenceRepository seqRep = new PosSequenceRepository();
		List<String> reviewSequences = seqRep.getPosSequences(1000000);

		BIDE bide = new BIDE(reviewSequences.size() / 20);
		bide.run(reviewSequences);
	}
}
