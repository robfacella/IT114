import java.util.Scanner;

public class Grade 
{

	public static void year()
	{
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter your year: ");
		
		int year = scan.nextInt();
		//char status = 'U';

		//break to bottom continue to top
		//default catch all
		switch (year)
		{
			case 0: System.out.println("You're a Freshman.");
				break;
			case 1: System.out.println("You're a Sophomore.");
			break;
			case 2: System.out.println("You're a Junior.");
			break;
			case 3: System.out.println("You're a Senior.");
			break;
			default: System.out.println("Drop-out.");
		}
		
	}
	
	public static void grade()
	{
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter a score: ");
		
		int score = scan.nextInt();
		
		char grade = 'F';
		
		if (score >= 90)
		{
			grade = 'A';
		}
		else if (score >= 80)
		{
			grade = 'B';
		}
		else if (score >= 70)
		{
			grade = 'C';
		}
		else if (score >= 60)
		{
			grade = 'D';
		}
		else
			grade = 'F';
		
		
		System.out.println(grade);
	}
	
	public static void main(String[] args) 
	{
		grade();
		year();
	}

}
