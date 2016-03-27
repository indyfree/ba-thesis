package patternmining;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbDriver {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/review_ciao_2014";
	private static final String USER = "root";
	private static final String PASSWORD = "root";

	public static ArrayList<Review> getReviews() {
		return getReviews(-1);
	}

	public static ArrayList<Review> getReviews(int number) {
		ArrayList<Review> reviews = new ArrayList<Review>();

		try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);) {
			try (PreparedStatement statement = connection.prepareStatement("select * from reviews_ciao")) {

				ResultSet results = statement.executeQuery();

				while (results.next()) {
					int id = results.getInt("review_id");
					String pro = results.getString("pro");
					String contra = results.getString("contra");

					Review review = new Review(id);

					if (pro != null && pro.trim() != "") {
						review.setPro(pro.trim());
					}

					if (contra != null && contra.trim() != "") {
						review.setContra(contra.trim());
					}

					reviews.add(review);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reviews;
	}

	public static void writeTaggedReviews(ArrayList<Review> reviews) {
		try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);) {
			try (PreparedStatement statement = connection
					.prepareStatement("UPDATE reviews_ciao SET pro_tagged = ?, contra_tagged =? WHERE review_id = ?")) {
				for (Review r : reviews) {
					statement.setString(1, r.getProPOS());
					statement.setString(2, r.getContraPOS());
					statement.setInt(3, r.getId());
					statement.executeUpdate();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
