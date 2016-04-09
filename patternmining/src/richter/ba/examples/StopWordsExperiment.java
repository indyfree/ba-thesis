package richter.ba.examples;

import java.util.ArrayList;
import java.util.stream.Collectors;

import richter.ba.db.ReviewRepository;

public class StopWordsExperiment {

	public static void main(String[] args) {
		ReviewRepository rr = new ReviewRepository();

		rr.filterStopWords((ArrayList<String>) rr.getReviews(1000000).stream().map(r -> r.getContra())
				.collect(Collectors.toList()));
	}
}
