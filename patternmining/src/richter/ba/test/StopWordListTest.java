package richter.ba.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import richter.ba.utils.Filter;

public class StopWordListTest {

	@Test
	public void testFilterSequences() {
		List<String> fullList = Arrays.asList("Hello World, Hello Foo, Bar, World, Hello".split(", "));
		System.out.println(fullList);

		List<String> stopWords = Arrays.asList("World, Foo".split(", "));
		System.out.println(stopWords);

		List<String> expectedList = Arrays.asList("Bar, Hello".split(", "));

		List<String> actualList = Filter.filterSequences(fullList, stopWords);
		System.out.println(actualList);

		Assert.assertArrayEquals(expectedList.toArray(), actualList.toArray());
	}

	@Test
	public void testFilterWords() {
		List<String> fullList = Arrays.asList("Hello World, Hello Foo, Bar, World, Hello".split(", "));
		System.out.println(fullList);

		List<String> stopWords = Arrays.asList("World, Foo".split(", "));
		System.out.println(stopWords);

		List<String> expectedList = Arrays.asList("Hello, Hello, Bar, Hello".split(", "));

		List<String> actualList = Filter.filterWords(fullList, stopWords);
		System.out.println(actualList);

		Assert.assertArrayEquals(expectedList.toArray(), actualList.toArray());
	}
}
