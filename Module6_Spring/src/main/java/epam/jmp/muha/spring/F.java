package epam.jmp.muha.spring;

public class F 
{
	private String nameF;
	
	public F() 
	{
		System.out.println("F_class:Constructor without parameters");
	}
	public F(String nameF) 
	{
		super();
		this.nameF = nameF;
	}

	public String getNameF() {
		return nameF;
	}

	public void setNameF(String nameF) 
	{
		System.out.println("F_class:Set parameter by setter");
		this.nameF = nameF;
	}
	@Override
	public String toString() {
		return "F [nameF=" + nameF + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nameF == null) ? 0 : nameF.hashCode());
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
		F other = (F) obj;
		if (nameF == null) {
			if (other.nameF != null)
				return false;
		} else if (!nameF.equals(other.nameF))
			return false;
		return true;
	}
	public void init()
	{
	      System.out.println("Bean F is going through init.");
	}

	public void destroy() 
	{
	     System.out.println("Bean F will destroy now.");
	}

}
