package epam.jmp.muha;

public class NewPoint 
{
	public long a;
	public long b;
	
	public NewPoint() {
		super();
	}

	public NewPoint(long a, long b) {
		super();
		this.a = a;
		this.b = b;
	}


	public long getA() {
		return a;
	}

	public void setA(long a) {
		this.a = a;
	}

	public long getB() {
		return b;
	}

	public void setB(long b) {
		this.b = b;
	}

	@Override
	public String toString() {
		return "NewPoint [a=" + a + ", b=" + b + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (a ^ (a >>> 32));
		result = prime * result + (int) (b ^ (b >>> 32));
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
		NewPoint other = (NewPoint) obj;
		if (a != other.a)
			return false;
		if (b != other.b)
			return false;
		return true;
	}


	
}
