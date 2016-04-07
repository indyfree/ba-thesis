package richter.ba.db;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import richter.ba.entities.Review;
import richter.ba.utils.Tagger;
import richter.ba.utils.TaggerModel;

public class DataPreProccessor {

	final static Logger logger = LoggerFactory.getLogger(DataPreProccessor.class);
	final static Tagger tagger = new Tagger(TaggerModel.GERMAN);

	public static void tagReviews() {

		long startTime = System.currentTimeMillis();
		ArrayList<Review> reviews = DbDriver.getReviews();
		long endTime = System.currentTimeMillis();

		logger.info("{} Reviews read", reviews.size());
		logger.info("That took {} seconds", (endTime - startTime) / 1000);

		startTime = System.currentTimeMillis();
		tagger.tagReviews(reviews);
		endTime = System.currentTimeMillis();
		logger.info("That took {} seconds", (endTime - startTime) / 1000);

		startTime = System.currentTimeMillis();
		DbDriver.updateReviews(reviews);
		endTime = System.currentTimeMillis();
		logger.info("Writing {} tagged reviews to DB ", reviews.size());
		logger.info("That took {} seconds", (endTime - startTime) / 1000);

	};
}
