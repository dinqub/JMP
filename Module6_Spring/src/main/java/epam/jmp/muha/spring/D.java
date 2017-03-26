package epam.jmp.muha.spring;

public class D
{
	private String nameD;

	public D() {
		super();
	}

	public D(String nameD) {
		super();
		this.nameD = nameD;
	}

	@Override
	public String toString() {
		return "D [nameD=" + nameD + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nameD == null) ? 0 : nameD.hashCode());
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
		D other = (D) obj;
		if (nameD == null) {
			if (other.nameD != null)
				return false;
		} else if (!nameD.equals(other.nameD))
			return false;
		return true;
	}

	public String getNameD() {
		return nameD;
	}

	public void setNameD(String nameD) {
		this.nameD = nameD;
	}

	
}
