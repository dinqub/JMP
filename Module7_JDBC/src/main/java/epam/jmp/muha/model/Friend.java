package epam.jmp.muha.model;

import java.sql.Timestamp;

import org.apache.commons.lang3.tuple.Pair;

public class Friend
{
	public int user1;
	public int user2;
	public Timestamp timestamp;
	
	public Friend() {
		super();
	}

	public int getUser1() {
		return user1;
	}

	public void setUser1(int user1) {
		this.user1 = user1;
	}

	public int getUser2() {
		return user2;
	}

	public void setUser2(int user2) {
		this.user2 = user2;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Friend(int user1, int user2, Timestamp timestamp) {
		super();
		this.user1 = user1;
		this.user2 = user2;
		this.timestamp = timestamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + user1;
		result = prime * result + user2;
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
		Friend other = (Friend) obj;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (user1 != other.user1)
			return false;
		if (user2 != other.user2)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Friend [user1=" + user1 + ", user2=" + user2 + ", timestamp=" + timestamp + "]";
	}

	

}
