package epam.jmp.muha.spring;

public abstract class C 
{
	private D objectD;
	
	public C() 
	{
		this.objectD = getObjectD();
	}
	
	public abstract D getObjectD();

	public void setObjectD(D objectD) {
		this.objectD = objectD;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((objectD == null) ? 0 : objectD.hashCode());
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
		C other = (C) obj;
		if (objectD == null) {
			if (other.objectD != null)
				return false;
		} else if (!objectD.equals(other.objectD))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "C [objectD=" + objectD + "]";
	}

}
