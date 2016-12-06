package richter.ba.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import richter.ba.entities.Review;

class DbDriver {

	private String url;
	private String user;
	private String password;

	protected DbDriver(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

	protected ArrayList<Review> getReviews() {
		return getReviews(-1);
	}

	protected ArrayList<Review> getReviews(int number) {
		ArrayList<Review> reviews = new ArrayList<Review>();

		String sqlStatement = "select * from reviews_ciao_condensed";
		if (number > -1) {
			sqlStatement += " limit ?";
		}

		try (Connection connection = DriverManager.getConnection(this.url, this.user, this.password);) {
			try (PreparedStatement statement = connection.prepareStatement(sqlStatement)) {

				if (number > -1) {
					statement.setInt(1, number);
				}

				ResultSet results = statement.executeQuery();

				while (results.next()) {
					int id = results.getInt("review_id");
					String pro = results.getString("pro");
					String contra = results.getString("contra");
					reviews.add(new Review(id, pro, contra));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reviews;
	}

	protected List<String> getPosSequences(int number) {
		List<String> posSequences = new ArrayList<String>();

		String sqlStatement = "select * from review_sequences_pos";
		if (number > -1) {
			sqlStatement += " limit ?";
		}

		try (Connection connection = DriverManager.getConnection(this.url, this.user, this.password);) {
			try (PreparedStatement statement = connection.prepareStatement(sqlStatement)) {

				if (number > -1) {
					statement.setInt(1, number);
				}

				ResultSet results = statement.executeQuery();
				while (results.next()) {
					posSequences.add(results.getString("sequence").trim());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return posSequences;
	}

	protected void writePosSequences(List<String> posSequences) {
		excecuteStatement(
				"CREATE TABLE IF NOT EXISTS `review_ciao_2014`.`review_sequences_pos` (`id` INT NOT NULL AUTO_INCREMENT, `sequence` mediumtext NOT NULL, PRIMARY KEY (`id`)) ENGINE = MyISAM;");
		excecuteStatement("TRUNCATE TABLE `review_ciao_2014`.`review_sequences_pos`;");

		try (Connection connection = DriverManager.getConnection(this.url, this.user, this.password);) {
			try (PreparedStatement statement = connection
					.prepareStatement("INSERT INTO `review_ciao_2014`.`review_sequences_pos` (sequence) VALUES (?)")) {
				for (String sequence : posSequences) {
					statement.setString(1, sequence);
					statement.addBatch();
					statement.clearParameters();
				}
				statement.executeBatch();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void excecuteStatement(String sql) {
		try (Connection connection = DriverManager.getConnection(this.url, this.user, this.password);) {
			try (Statement stm = connection.createStatement()) {
				stm.executeUpdate(sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
