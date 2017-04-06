package epam.jmp.muha.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import epam.jmp.muha.model.User;

public class JdbcUserDAO 
{
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void insert(User user){
		
		String sql = "INSERT INTO USER " +
				"( NAME, SURNAME, BIRTHDATE) VALUES (?, ?, ?)";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getSurName());
			ps.setTimestamp(3, user.getBithday());
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}

	public List<User> findPopularUsers(int friensCount, int likesCount) 
	{
		
		String dropSQL = "DROP TABLE IF EXISTS temp; ";
		String creatTemporaryTableSQL ="CREATE TEMPORARY TABLE temp " +
					 "SELECT COUNT(user.id) as friendsCount,user.id as userId,user.name as userName,user.surname as userSurname,user.birthdate as userBirthday "+
					 "FROM user INNER JOIN friendships ON friendships.userId1=user.Id OR friendships.userId2=user.Id  Group By (user.Id) HAVING friendsCount > ? ; ";
		String selectPopularUserSQL ="SELECT temp.friendsCount as friendCount, COUNT(likes.postId) as likesCount,temp.userId as userId,temp.userName as UserName ,temp.userSurname as userSurname,temp.userBirthday as userBirthday, likes.timestamp "+
					 "from ((temp INNER JOIN posts ON posts.userId=temp.userId) "+
					 "INNER JOIN likes ON likes.postId=posts.id )  GROUP BY (likes.postID) HAVING likesCount > ? and likes.timestamp BETWEEN '2015-03-01' AND '2015-03-31';";
		
		Connection conn = null;
		List<User> usersList = new ArrayList<User>();
		
		try {
			conn = dataSource.getConnection();
			Statement st = conn.createStatement();
			st.executeUpdate(dropSQL);
			PreparedStatement ps = conn.prepareStatement(creatTemporaryTableSQL);
			User user = null;
			ps.setInt(1, friensCount);			
			ps.execute();
			ps = conn.prepareStatement(selectPopularUserSQL);
			ps.setInt(1, likesCount);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) 
			{
				if (friensCount < rs.getInt("friendCount") && likesCount < rs.getInt("likesCount"))
				{
					user = new User(
							rs.getInt("userId"),
							rs.getString("userName"), 
							rs.getString("userSurname"),
							rs.getTimestamp("userBirthday")
					);
					usersList.add(user);
				}
			}
			rs.close();
			ps.close();
			return usersList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}
}




