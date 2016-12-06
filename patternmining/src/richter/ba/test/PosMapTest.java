package richter.ba.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import richter.ba.utils.PosMapper;

public class PosMapTest {

	@Test
	public void testUniversalPosMapper() {

		List<String> origin = new ArrayList<String>();
		origin.add("ADJD ADJA NN lala   NE ADV APPR");

		List<String> expected = new ArrayList<String>();
		expected.add("ADJ ADJ NOUN NOUN ADV ADP");

		List<String> actual = PosMapper.mapTags(origin, "maps/de-negra.map");
		System.out.println(actual);

		Assert.assertArrayEquals(expected.toArray(), actual.toArray());
	}
}
