package richter.ba.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import richter.ba.utils.Tokenizer;

public class TokenizerTest {

	@Test
	public void testTokenizer() {

		final String[] splitter = { "\\$\\[", "\\$\\.", "KON", "\\$\\," };

		final List<String> fullList = Arrays.asList(new String[] { "ADJD KON ADJD $. NN APPR PIDAT NN",
				"$[ $[ $[ CARD PTKNEG ADJD", "NE $, ADJD $, ADJD $, ADJD" });

		List<String> expectedList = Arrays.asList(
				new String[] { "ADJD", "ADJD", "NN APPR PIDAT NN", "CARD PTKNEG ADJD", "NE", "ADJD", "ADJD", "ADJD" });

		List<String> actualList = Tokenizer.tokenizeSequences(fullList, splitter);
		System.out.println("Actual: " + actualList);
		System.out.println("Expected: " + expectedList);

		Assert.assertArrayEquals(expectedList.toArray(), actualList.toArray());
	}

}
