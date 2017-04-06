package epam.jmp.muha.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

import javax.sql.DataSource;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import epam.jmp.muha.model.Friend;

public class JdbcFriendshipsDAO 
{
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void insert(Friend friend){
		
		String sql = "INSERT INTO FRIENDSHIPS " +
				"(USERID1, USERID2, TIMESTAMP) VALUES (?, ?, ?)";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, friend.getUser1());
			ps.setInt(2, friend.getUser2());
			ps.setTimestamp(3, friend.getTimestamp());
			ps.executeUpdate();
			ps.close();
		}
		catch (MySQLIntegrityConstraintViolationException e)
		{
			System.out.println(e);		
		} 
		catch (SQLException e) {
			throw new RuntimeException(e);			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
public void insertSet(Set<Friend> friends){
		
		String sql = "INSERT INTO FRIENDSHIPS " +
				"(USERID1, USERID2, TIMESTAMP) VALUES (?, ?, ?)";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			int i=0;
			int friendsize = friends.size();
			for (Friend friend : friends) 
	        {
				ps.setInt(1, friend.getUser1());
				ps.setInt(2, friend.getUser2());
				ps.setTimestamp(3, friend.getTimestamp());
	            ps.addBatch();
	            i++;

	            if (i % 1000 == 0 || i == friendsize)
	            {
	                ps.executeBatch(); // Execute every 1000 items.
	            }
	        }
		}
		catch (MySQLIntegrityConstraintViolationException e)
		{
			System.out.println(e);		
		} 
		catch (SQLException e) {
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




