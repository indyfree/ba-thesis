package richter.ba.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import richter.ba.utils.Filter;

public class StopWordListTest {

	private static final String[] stopWords = { "World", "Foo" };
	private static final List<String> fullList = Arrays.asList("Hello World, Hello Foo, Bar, World, Hello".split(", "));

	@Test
	public void testFilterSequences() {

		System.out.println(fullList);

		List<String> expectedList = Arrays.asList("Bar, Hello".split(", "));

		List<String> actualList = Filter.filterSequences(fullList, stopWords);
		System.out.println(actualList);

		Assert.assertArrayEquals(expectedList.toArray(), actualList.toArray());
	}

	@Test
	public void testFilterWords() {
		System.out.println(fullList);

		List<String> expectedList = Arrays.asList("Hello, Hello, Bar, Hello".split(", "));

		List<String> actualList = Filter.filterWords(fullList, stopWords);
		System.out.println(actualList);

		Assert.assertArrayEquals(expectedList.toArray(), actualList.toArray());
	}
}
