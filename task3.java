import java.util.*;

class NotFoundException extends Exception
{
	public String toString()
	{
		return "\nPASSWORD||USERNAME MISMATCH";
	}
}
class MinBlncException extends Exception
{
	public String toString()
	{
		return "Debit amount exceeding your bank balance!!\nPlease try again";
	}
}
class TransferBlncException extends Exception
{
	public String toString()
	{
		return "Transfer amount exceeding your bank balance!! \nCannot Transfer Amount";
	}
}
interface Bank
{
	public void Credentials(String Username,String Password) throws NotFoundException;
	void Deposit(double blnc);
	public void Withdraw(double blnc) throws MinBlncException;
	void displayBalance();
	void Quit();
	public void Transfer(double blnc,long acc) throws TransferBlncException;
}
class Customer implements Bank
{
	String username[]=new String[10];
	String password[]=new String[10];
	double total;
	int flag=0;
	Customer(double cash)
	{
		total=cash;
	}
	public void Credentials(String u,String p) throws NotFoundException
	{
		int i,j,flag1=0,flag2=0;
		for(i=0;i<username.length;i++)
		{
			if(u.equals(username[i]))
				flag1=1;
		}
		for(j=0;j<password.length;j++)
		{
			if(p.equals(password[j]))
				flag2=1;
		}
		if(flag1==0||flag2==0)
			throw new NotFoundException();
		System.out.println("Password and username matched......");
		flag=1;
	}
	public void Deposit(double blnc)
	{
		total=total+blnc;
		System.out.println(blnc+" deposited Successfully.");
	}
	public void Withdraw(double blnc) throws MinBlncException
	{
		if(blnc>total)
			throw new MinBlncException();
		total=total-blnc;	
		System.out.println(blnc+" withdrawn Successfully.");
	}
	public void displayBalance()
	{
		System.out.println("Your Current bank balance is:"+total);
	}
	public void Quit()
	{
		System.out.println("Exited.");
	}
	public void Transfer(double blnc,long acc) throws TransferBlncException
	{
		if(blnc>total)
			throw new TransferBlncException();
		total=total-blnc;
		System.out.println("Amount transferred Successfully");
	}
}
class Main
{
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		Customer c=new Customer(0);			//money in the account is 0.0
		List<String> li=new LinkedList<String>();
		Date d=new Date();
		String u,p;
		int s,z=0,se;
		long acc;
		do{
		System.out.println("\n\n--------------------------------------");
		System.out.println("1.New Account\n2.Login\n3.Exit");
		System.out.print("Enter your choice: ");
		se=sc.nextInt();
		System.out.println("--------------------------------------\n\n");
		if(se==1)
		{
			System.out.print("Enter new Username:");
			c.username[z]=sc.next();
			System.out.print("Enter Password:");
			c.password[z]=sc.next();
			z++;
		}
		else if(se==2)
		{
			System.out.println("Enter your crendentials!!\n");
			System.out.print("Enter your Username:");
			u=sc.next();
			System.out.print("Enter your Password:");
			p=sc.next();
			try
			{
				c.Credentials(u,p);
			}
			catch(NotFoundException e)
			{
				System.out.println(e);
			}
			if(c.flag==0)
			{
				System.out.println("Oops!!Try Again");
			}
			else
			{
				int ch;
				double b;
				do{
					System.out.println("--------------------------------------");
					System.out.println("1.Deposit\n2.Withdraw\n3.Checkbalance\n4.Transfer\n5.Transaction History\n6.Exit\n");
					System.out.print("Enter your choice:");
					ch=sc.nextInt();
					System.out.println("--------------------------------------");
					switch(ch)
					{
						case 1:
							System.out.print("\nEnter amount to deposit:");
							b=sc.nextDouble();
							c.Deposit(b);
							li.add("Deposited"+b+" "+d.toString());
							break;
						case 2:
							System.out.print("Enter amount to withdraw:");
							b=sc.nextDouble();
							try
							{
								c.Withdraw(b);
								li.add("Withdrawn"+b+" "+d.toString());
								break;
							}
							catch(MinBlncException e)
							{
								System.out.println(e);
								break;
							}
						case 3:
							c.displayBalance();
							li.add("checked Balance"+d.toString());
							break;
						case 4:
							System.out.print("Enter Account Number to transfer:");
							acc=sc.nextLong();
							System.out.print("Enter amount to transfer:");
							b=sc.nextDouble();
							try
							{
								c.Transfer(b,acc);
								li.add("Transferred "+b+d.toString());
								break;
							}
							catch(TransferBlncException t)
							{
								System.out.println(t);
								break;
							}
						case 5:
							System.out.println("*****Transaction History******");
							for(String mm:li)
							{
								System.out.println(li);
								break;
							}
							break;
						case 6:
							c.Quit();
							break;
						default:
							System.out.println("Invalid Choice");
							break;	
					}
				}while(ch!=6);
			}
		}
		else
			System.out.println("Exited.");
		}while(se!=3);
	}
}