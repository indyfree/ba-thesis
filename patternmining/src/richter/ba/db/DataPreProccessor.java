package richter.ba.db;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import richter.ba.entities.Review;
import richter.ba.utils.Tagger;
import richter.ba.utils.TagSet;

public class DataPreProccessor {

	final static Logger logger = LoggerFactory.getLogger(DataPreProccessor.class);
	final static Tagger tagger = new Tagger(TagSet.GERMAN);

	public static void tagReviews() {

		long startTime = System.currentTimeMillis();
		ReviewRepository rr = new ReviewRepository();
		ArrayList<Review> reviews = rr.getReviews();
		long endTime = System.currentTimeMillis();

		logger.info("{} Reviews read", reviews.size());
		logger.info("That took {} seconds", (endTime - startTime) / 1000);

		startTime = System.currentTimeMillis();
		tagger.tagReviews(reviews);
		endTime = System.currentTimeMillis();
		logger.info("That took {} seconds", (endTime - startTime) / 1000);

		startTime = System.currentTimeMillis();
		rr.updateReviews(reviews);
		endTime = System.currentTimeMillis();
		logger.info("Writing {} tagged reviews to DB ", reviews.size());
		logger.info("That took {} seconds", (endTime - startTime) / 1000);

	};
}
