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

	public DbDriver(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public ArrayList<Review> getReviews() {
		return getReviews(-1);
	}

	public ArrayList<Review> getReviews(int number) {
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
					String proPOS = results.getString("pro_tagged");
					String contraPOS = results.getString("contra_tagged");

					Review review = new Review(id);

					if (pro != null && pro.trim() != "") {
						review.setPro(pro.trim());
					}

					if (contra != null && contra.trim() != "") {
						review.setContra(contra.trim());
					}

					if (proPOS != null && proPOS.trim() != "") {
						review.setProPOS(proPOS.trim());
					}

					if (contraPOS != null && contraPOS.trim() != "") {
						review.setContraPOS(contraPOS.trim());
					}

					reviews.add(review);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reviews;
	}

	public void updateReviews(ArrayList<Review> reviews) {
		try (Connection connection = DriverManager.getConnection(this.url, this.user, this.password);) {
			try (PreparedStatement statement = connection.prepareStatement(
					"UPDATE reviews_ciao SET pro = ?, contra = ?, pro_tagged = ?, contra_tagged =? WHERE review_id = ?")) {
				for (Review r : reviews) {
					statement.setString(1, r.getPro());
					statement.setString(2, r.getContra());
					statement.setString(3, r.getProPOS());
					statement.setString(4, r.getContraPOS());
					statement.setInt(5, r.getId());
					statement.executeUpdate();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<String> getPosSequences(int n) {
		// TODO Auto-generated method stub
		return null;

	}

	public void writePosSequences(List<String> posSequences) {
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
