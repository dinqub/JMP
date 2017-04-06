package epam.jmp.muha.model;

import java.sql.Timestamp;

public class Post 
{
	public int id;
	public int userId; 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
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
		Post other = (Post) obj;
		if (id != other.id)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
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
	public Post() {
		super();
	}
	public Post(int id, int userId, String text, Timestamp timestamp) {
		super();
		this.id = id;
		this.userId = userId;
		this.text = text;
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "Post [id=" + id + ", userId=" + userId + ", text=" + text + ", timestamp=" + timestamp + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public String text;
	public Timestamp timestamp;
}
