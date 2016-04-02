package examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.pfv.spmf.input.sequence_database_list_strings.Sequence;
import ca.pfv.spmf.input.sequence_database_list_strings.SequenceDatabase;

public class MiningSequentialPatternsAgrawan {

	public static void main(String[] args) {
		SequenceDatabase sequenceDB = new SequenceDatabase();

		List<String[][]> sequences = new ArrayList<String[][]>();
		sequences.add(new String[][] { { "3" }, { "9" } });
		sequences.add(new String[][] { { "1", "2" }, { "3" }, { "4", "6", "7" } });
		sequences.add(new String[][] { { "3" }, { "4", "7" }, { "9" } });
		sequences.add(new String[][] { { "3", "5", "7" } });
		sequences.add(new String[][] { { "9" } });

		for (String[][] sequence : sequences) {
			Sequence s = new Sequence(sequences.indexOf(sequence));
			for (String[] itemSets : sequence) {
				s.addItemset(Arrays.asList(itemSets));
			}
			sequenceDB.addSequence(s);
		}

		System.out.println(sequenceDB.toString());

		// computeClosedSequentialPatternsBIDEPLUS(sequenceDB, 2);

	}

}
