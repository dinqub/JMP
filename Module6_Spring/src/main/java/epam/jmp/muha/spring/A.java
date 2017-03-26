package epam.jmp.muha.spring;

/**
 * Spring bean
 * 
 */
public class A
{
	private String nameA;

	public A() 	{	}
	
	public A(String name) 
	{
		super();
		this.nameA = name;
	}

	public String getName() {
		return nameA;
	}

	public void setName(String name) 
	{
		this.nameA = name;
	}

	@Override
	public String toString() {
		return "A [name=" + nameA + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nameA == null) ? 0 : nameA.hashCode());
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
		A other = (A) obj;
		if (nameA == null) {
			if (other.nameA != null)
				return false;
		} else if (!nameA.equals(other.nameA))
			return false;
		return true;
	}

}