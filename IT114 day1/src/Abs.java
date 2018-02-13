
public class Abs {

	public int absval (int n)
	{
		if (n < 0)
			return -n;
		else
			return n;
	}
	public static void main(String[] args) {
		
		Abs abs = new Abs();
		
		int n = 5;
		int r = abs.absval(n);
		
		System.out.println(r);
	}

}
