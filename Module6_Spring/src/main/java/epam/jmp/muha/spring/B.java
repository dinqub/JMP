package epam.jmp.muha.spring;

public class B 
{
	private A objectA;

	public B() 	{	}
	
	public B(A objectA) 
	{
		super();
		this.objectA = objectA;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((objectA == null) ? 0 : objectA.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "B [objectA=" + objectA + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		B other = (B) obj;
		if (objectA == null) {
			if (other.objectA != null)
				return false;
		} else if (!objectA.equals(other.objectA))
			return false;
		return true;
	}

	public A getObjectA() {
		return objectA;
	}

	public void setObjectA(A objectA) {
		this.objectA = objectA;
	}


}
