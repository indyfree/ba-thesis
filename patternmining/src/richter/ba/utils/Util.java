package richter.ba.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Stream;

import ca.pfv.spmf.input.sequence_database_list_strings.Sequence;
import ca.pfv.spmf.input.sequence_database_list_strings.SequenceDatabase;
import richter.ba.entities.SequentialRule;

public class Util {

	public static SequenceDatabase getSequenceDatabase(List<String> stringList) {
		SequenceDatabase sequenceDB = new SequenceDatabase();

		for (int i = 0; i < stringList.size(); i++) {
			String sequenceString = stringList.get(i);
			Sequence sequence = new Sequence(i);

			/*
			 * Set each whitespace seperated word as item in itemsset
			 */
			for (String item : sequenceString.split(" ")) {
				ArrayList<String> itemSet = new ArrayList<String>();
				itemSet.add(item);
				sequence.addItemset(itemSet);
			}

			sequenceDB.addSequence(sequence);
		}
		return sequenceDB;
	}

	public static Map<String, Integer> StringsToIntegerMap(List<String> input) {
		Map<String, Integer> stringToInt = new HashMap<String, Integer>();
		int value = 1;

		for (String sequence : input) {
			String[] items = sequence.split(" ");
			for (int i = 0; i < items.length; i++) {
				if (!stringToInt.containsKey(items[i])) {
					stringToInt.put(items[i], value++);
				}
			}
		}
		return stringToInt;
	}

	public static void writeStringSequencesToIntFile(List<String> sequences, Map<String, Integer> stringToInt,
			String filePath) {
		Writer fw = null;
		try {
			fw = new FileWriter(filePath);
			fw.write("");
			for (String sequence : sequences) {
				for (String item : sequence.split(" ")) {
					fw.append(stringToInt.get(item) + " -1 ");
				}
				fw.append("-2");
				fw.append(System.getProperty("line.separator"));
			}
		} catch (IOException e) {
			System.err.println("Could not create File");
		} finally {
			if (fw != null)
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	public static Map<String, Integer> readPatternsFromFile(String filePath, Map<String, Integer> patternLookup) {
		HashMap<String, Integer> patternToQuantity = new HashMap<String, Integer>();
		String line;

		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			while ((line = reader.readLine()) != null) {

				String pattern = line.substring(0, line.lastIndexOf("-1") - 1).replace(" -1 ", " ");

				if (patternLookup != null) {
					String[] patternInts = pattern.split(" ");
					pattern = "";
					for (String patternInt : patternInts) {
						pattern += Util.getKeyByValue(patternLookup, Integer.valueOf(patternInt)) + " ";
					}
				}
				patternToQuantity.put(pattern, Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1)));
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return Util.sortByValue(patternToQuantity);
	}

	public static ArrayList<SequentialRule> readRulesFromFile(String filePath, Map<String, Integer> patternLookup) {
		ArrayList<SequentialRule> rules = new ArrayList<>();
		String line;

		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			while ((line = reader.readLine()) != null) {

				String[] leftSet = line.substring(0, line.lastIndexOf("==>") - 1).split(",");
				int[] itemSetI = new int[leftSet.length];
				for (int i = 0; i < leftSet.length; i++) {
					itemSetI[i] = Integer.parseInt(leftSet[i].trim());
				}

				String[] rightSet = line.substring(line.lastIndexOf("==>") + 3, line.lastIndexOf("#SUP")).trim()
						.split(",");
				int[] itemSetJ = new int[rightSet.length];
				for (int i = 0; i < rightSet.length; i++) {
					itemSetJ[i] = Integer.parseInt(rightSet[i].trim());
				}

				int support = Integer.valueOf(line.substring(line.indexOf("#SUP:") + 4, line.indexOf("#CONF:")).trim());
				double confidence = Double.valueOf(line.substring(line.indexOf("#CONF:") + 6).trim());

				rules.add(new SequentialRule(itemSetI, itemSetJ, support, confidence));
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return rules;
	}

	/*
	 * Taken from http://stackoverflow.com/a/2904266
	 */
	public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
		for (Entry<T, E> entry : map.entrySet()) {
			if (Objects.equals(value, entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 * Taken from http://stackoverflow.com/a/2581754
	 *
	 * @param map
	 * @return
	 */
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		Map<K, V> result = new LinkedHashMap<>();
		Stream<Entry<K, V>> st = map.entrySet().stream();

		st.sorted(Comparator.comparing(e -> ((Entry<K, V>) e).getValue()).reversed())
				.forEachOrdered(e -> result.put(e.getKey(), e.getValue()));

		return result;
	}
}