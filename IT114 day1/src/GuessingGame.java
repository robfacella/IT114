//Robert Facella
//IT 114
import java.util.*;

public class GuessingGame {

	
	public static void game()
	{
		Scanner scan = new Scanner(System.in);
		
		Random randnum = new Random();
		
		String again = "y";
		
		while ((again.equalsIgnoreCase("yes")) || (again.equalsIgnoreCase("y")))
		{
			int rand = randnum.nextInt(100);
			int num = -9999;
			
			while (num != rand)
			{
				System.out.println("Enter a number between 0 and 100: ");
				num = scan.nextInt();
				
				System.out.println("You entered: " + num);
				
				if (num > rand)
				{
					if (num - rand > 20)
					{
						System.out.println("Way too high!");
					}
					System.out.println("Guess lower.");
				}
				else if (num < rand)
				{
					if (rand - num > 20)
					{
						System.out.println("Way too low!");
					}
					System.out.println("Guess higher.");
				}
				
			}
			System.out.println("The number was: " + rand);
			
			System.out.println("Play again? (y/n): ");
			again = scan.next();
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		game();
		
	}

}
