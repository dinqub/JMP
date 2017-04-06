package epam.jmp.muha.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import epam.jmp.muha.model.Post;

public class JdbcPostsDAO 
{
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void insert(Post post)
	{
		
		String sql = "INSERT INTO POSTS " +
				"(USERID, TEXT, TIMESTAMP) VALUES (?, ?, ?)";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setInt(1, post.getUserId());
			ps.setString(2, post.getText());
			ps.setTimestamp(3, post.getTimestamp());
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
	public void insertList(List<Post> posts)
	{
		
		String sql = "INSERT INTO POSTS " +
				"(USERID, TEXT, TIMESTAMP) VALUES (?, ?, ?)";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			
	        for (Post post : posts) 
	        {
	        	ps.setInt(1, post.getUserId());
				ps.setString(2, post.getText());
				ps.setTimestamp(3, post.getTimestamp());
	            ps.addBatch();
	        }
	        ps.executeBatch();
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

}




