package epam.jmp.muha.model;

import java.sql.Timestamp;

public class Like 
{
	public int postId;
	public int userId;
	public Timestamp timestamp;
	public Like() {
		super();
	}
	public Like(int postId, int userId, Timestamp timestamp) {
		super();
		this.postId = postId;
		this.userId = userId;
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "Like [postId=" + postId + ", userId=" + userId + ", timestamp=" + timestamp + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + postId;
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + userId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Like other = (Like) obj;
		if (postId != other.postId)
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	

}
