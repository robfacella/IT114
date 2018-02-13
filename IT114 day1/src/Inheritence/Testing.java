package Inheritence;

public class Testing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class First{
	int n = 5;
	public int getN()
	{
		return n;
	}
}

class Second extends First{
	int n = 10;
	public int getN()
	{
		return n;
	}
}