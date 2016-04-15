package richter.ba.experiment;

import java.util.List;

import richter.ba.algorithms.Algorithm;
import richter.ba.algorithms.LAPIN;
import richter.ba.db.PosSequenceRepository;

public class PatternMiningReviews {

	public static void main(String[] args) {

		PosSequenceRepository seqRep = new PosSequenceRepository();
		List<String> reviewSequences = seqRep.getPosSequences();

		Algorithm bide = new LAPIN(reviewSequences.size() / 20);
		bide.run(reviewSequences);
	}
}
