import java.util.*;

class GuessNumber
{
	void guessNum()
	{
		Scanner sc=new Scanner(System.in);
		int t;
		int num=1+(int)(100*Math.random());
		System.out.println("\n\nA number is selected between 1 to 100");
		System.out.print("In how many trails you can guess that number: ");
		t=sc.nextInt();
		System.out.println("\n\n-----------------------------");
		System.out.println("Guess the number within "+t+" trails");
		System.out.println("Total score : "+t*10);
		System.out.println("-----------------------------\n\n");
		int n,i;
		for(i=0;i<t;i++)
		{
			System.out.print("Guess the number: ");
			n=sc.nextInt();
			if(n==num)
			{
				System.out.println("\nCongratulations!!you guessed the correct number :)");
				System.out.println("Your score is "+(t-i)*10);
				break;
			}
			else if(num>n)
			{
				System.out.println("\nThe number is greater than "+n);
				System.out.println("Number of trails available: "+(t-(i+1)));
			}
			else if(num<n)
			{
				System.out.println("\nThe number is less than "+n);
				System.out.println("Number of trails available: "+(t-(i+1)));
			}
		}
		if(i==t)
		{
			System.out.println("\n\n\nYou have exceeded your "+t+" trails");
			System.out.println("Better Luck next time :(");
			System.out.println("The actual number is "+num);
		}
	}
}

class Main
{
	public static void main(String args[])
	{
		GuessNumber gn=new GuessNumber();
		gn.guessNum();
	}
}