package epam.jmp.muha.model;

import java.sql.Timestamp;


public class User 
{
	public int id;
	public String name;
	public String surName;
	public Timestamp bithday;
	
	public User() {
		super();
	}
	public User(int id, String name, String surName, Timestamp bithday) {
		super();
		this.id = id;
		this.name = name;
		this.surName = surName;
		this.bithday = bithday;
	}
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
	}
	public Timestamp getBithday() {
		return bithday;
	}
	public void setBithday(Timestamp bithday) {
		this.bithday = bithday;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bithday == null) ? 0 : bithday.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surName == null) ? 0 : surName.hashCode());
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
		User other = (User) obj;
		if (bithday == null) {
			if (other.bithday != null)
				return false;
		} else if (!bithday.equals(other.bithday))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surName == null) {
			if (other.surName != null)
				return false;
		} else if (!surName.equals(other.surName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surName=" + surName + ", bithday=" + bithday + "]";
	}

	public void setLastName(String lastName) {
		this.surName = lastName;
	}
	
	

}
