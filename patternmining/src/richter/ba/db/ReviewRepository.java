package richter.ba.db;

import java.util.ArrayList;

import richter.ba.entities.Review;

public class ReviewRepository {
	final String URL = "jdbc:mysql://localhost:3306/review_ciao_2014";
	final String USER = "root";
	final String PASSWORD = "root";

	private DbDriver dbDriver;

	public ReviewRepository() {
		this.dbDriver = new DbDriver(URL, USER, PASSWORD);
	}

	public ArrayList<Review> getReviews() {
		return this.dbDriver.getReviews();
	}

	public ArrayList<Review> getReviews(int number) {
		return this.dbDriver.getReviews(number);
	}
}
