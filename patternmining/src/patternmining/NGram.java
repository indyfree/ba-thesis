package patternmining;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NGram {

	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(NGram.class);
		//
		// long startTime = System.currentTimeMillis();
		// ArrayList<Review> reviews = DbDriver.getReviews(20000);
		// long endTime = System.currentTimeMillis();
		//
		// logger.info("{} Reviews read", reviews.size());
		// logger.info("That took {} seconds", (endTime - startTime) / 1000);
		//
		// startTime = System.currentTimeMillis();
		// Tagger.tagReviews(reviews);
		// endTime = System.currentTimeMillis();
		// logger.info("That took {} seconds", (endTime - startTime) / 1000);
		//
		// System.out.println(reviews.get(4).getContraPOS());
		//
		// DbDriver.tagReviews(reviews);

		String test = "Das ist ein_ alt_er _ Baum";

		System.out.println(Tagger.getTagString(test));

		System.out.println(Tagger.getTagOnlyString(test));
	}

}
