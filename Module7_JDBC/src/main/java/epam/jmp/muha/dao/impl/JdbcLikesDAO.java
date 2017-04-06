package epam.jmp.muha.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

import javax.sql.DataSource;

import epam.jmp.muha.model.Like;
import epam.jmp.muha.model.User;

public class JdbcLikesDAO
{
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void insert(Like like)
	{		
		String sql = "INSERT INTO LIKES " +
				"(POSTID, USERID, TIMESTAMP) VALUES (?, ?, ?)";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, like.getPostId());
			ps.setInt(2, like.getUserId());
			ps.setTimestamp(3, like.getTimestamp());
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
	public void insertSet(Set<Like> likes)
	{		
		String sql = "INSERT INTO LIKES " +
				"(POSTID, USERID, TIMESTAMP) VALUES (?, ?, ?)";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			int i=0;
			int likesize = likes.size();
			for (Like like : likes) 
	        {
	        	ps.setInt(1, like.getPostId());
				ps.setInt(2, like.getUserId());
				ps.setTimestamp(3, like.getTimestamp());
	            ps.addBatch();
	            i++;

	            if (i % 1000 == 0 || i == likesize)
	            {
	                ps.executeBatch(); // Execute every 1000 items.
	            }
	        }
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

	public User findByCustomerId(int custId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}




