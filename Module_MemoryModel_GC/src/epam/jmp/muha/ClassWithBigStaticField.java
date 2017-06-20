package epam.jmp.muha;

public class ClassWithBigStaticField 
{
	public static int mb = 1024*1024;
	public static byte[][] arr = new byte[mb][3000];
	int id;

	public ClassWithBigStaticField(int id) {
		super();
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ClassWithBigStaticField [id=" + id + "]";
	}
}
