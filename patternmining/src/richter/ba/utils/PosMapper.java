package richter.ba.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PosMapper {

	public static List<String> mapTags(List<String> posStrings, String pathToMap) {
		Map<String, String> posMap = readMap(pathToMap);
		List<String> universalPosStrings = new ArrayList<String>();

		for (String nGram : posStrings) {
			String[] posTags = nGram.split("\\s+");
			String universalPosTags = "";
			for (String tag : posTags) {
				String uniTag = posMap.get(tag);
				if (uniTag != null) {
					universalPosTags += uniTag + " ";
				}
			}
			universalPosStrings.add(universalPosTags.trim());
		}
		return universalPosStrings;
	}

	private static Map<String, String> readMap(String pathToMap) {

		Map<String, String> posMap = new HashMap<String, String>();
		String line;

		try {
			BufferedReader reader = new BufferedReader(new FileReader(pathToMap));
			while ((line = reader.readLine()) != null) {

				String[] tokens = line.split("\\s+");
				String key = tokens[0];
				String value = tokens[1];

				posMap.put(key, value);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return posMap;
	}

}
