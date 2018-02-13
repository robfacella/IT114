package threading;

public class ThreadStuff extends Thread{

	public ThreadStuff()
	{
		super();
	}
	public ThreadStuff(String name)
	{
		super(name);
	}
	
	public void run(){
		for(int i = 0; i<10; i++)
		{
			System.out.println(getName() + ": " + i);
			try {
				sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(getName() + ": done.");
	}
	
	
	
	public static void main(String[] args)
	{
		ThreadStuff ts = new ThreadStuff("Hamzamich");
		ts.start();
		ThreadStuff ts2 = new ThreadStuff("MelonHead");
		ts2.start();
		ThreadStuff ts3 = new ThreadStuff("Pizza");
		ts3.start();
		System.out.println("Main");
	}
}
