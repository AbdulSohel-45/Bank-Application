package oops;
import java.util.Scanner;
public class BankApplication {
static int i;
static String option;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String input=" ";
		i=0;
		while(!(input.equalsIgnoreCase("yes"))) {
			System.out.print("Enter the role : ");
			String access=sc.next();
			switch(access) {
			case "Manager":
				Managers m = new Managers();
				m.change();
				m.deposit();
				m.withdrawal();
				break;
			case "Accountant":
				Accountant a = new Managers();
				a.deposit();
				a.withdrawal();
				break;
			case "Clerk":
				Clerk c = new Clerk();
				c.display();
				break;
			case "User":
				User u1 =new User();
				u1.details();
				u1.check(i);
				i++;
				System.out.print("Do you wish to Deposit or Withdraw the money or change the data : ");
				option=sc.next();
				if(option.equalsIgnoreCase("deposit")) {
					u1.deposit();
				}
				else if(option.equalsIgnoreCase("withdraw")) {
					u1.withdrawal();
				}
				else {
					Clerk c1 = new Clerk();
					c1.change();
				}
			}
			System.out.println("Do you wish to exit the application");
			input = sc.next();
		}
	}
}
interface Change{
	public void change();
}
interface DepositAndWithdrawal{
	public void deposit();
	public void withdrawal();
}
class Clerk implements Change{
	Scanner sc = new Scanner(System.in);
	static String newName;
	static long phoneNumber;
	 static String change;
	 User u = new User();
public void change() {
	System.out.println("What do you want to change Name or Phone Number : ");
	change=sc.nextLine();
	if(change.equalsIgnoreCase("name")) {
		System.out.print("Enter the New Name : ");
		newName=sc.nextLine();
		u.dataStore();
	}
	else if(change.equalsIgnoreCase("Phone Number")) {
		System.out.println("Enter the new Phone Number");
		phoneNumber=sc.nextLong();
		u.dataStore();
	}
}
public void display() {
	Managers m = new Managers();
	m.change();
}
}
abstract class Accountant implements DepositAndWithdrawal{
	public void deposit() {
			int count=0;
			String arr[]=User.s.split("  ");
			for(int k=0;k<arr.length;k++) {
				String arr1[]=arr[k].split(" ");
				for(int m=0;m<arr1.length;m++) {
					if(arr1[m].equals("deposited")) {
						System.out.println(arr[k]);
						count++;
					}
				}
			}
			if(count==0) {
				System.out.println("No deposites are done yet");
			}
//			totalDeposit+=User.deposit;
//			System.out.println("Total amount of deposits = "+totalDeposit);
	}
	public void withdrawal() {
			String arr[]=User.s.split("  ");
			int count=0;
			for(int k=0;k<arr.length;k++) {
				String arr1[]=arr[k].split(" ");
				for(int m=0;m<arr1.length;m++) {
					if(arr1[m].equals("withdrawn")) {
						System.out.println(arr[k]);
						count++;
					}
				}
			}
			if(count==0) {
				System.out.println("No withdraws are done yet");
		}
//			totalWithdraw+=User.withdrawal;
//			System.out.println("Total amount of Withdraws made = "+totalWithdraw);
	}
}
class Managers extends Accountant{
	public void change() {
		String arr[]=User.s.split("  ");
		for(int k=0;k<arr.length;k++) {
			String arr1[]=arr[k].split(" ");
			for(int m=0;m<arr1.length;m++) {
				if(arr1[m].equals("Name")) {
					System.out.println(arr[k]);
				}
				else if(arr1[m].equals("Phone")) {
					System.out.println(arr[k]);
				}
			}
		}
		if(Clerk.newName==null&&Clerk.phoneNumber==0)
			System.out.println("No Changes are made yet");
	}
}
class User{
	Scanner sc = new Scanner(System.in);
	static int deposit;
	static int withdrawal;
	static String name;
	static long accountNo;
	int availBalance=5000;
	static String s ="";
	static long a[]=new long[100];
	static int b[]=new int[100];
	public void check(int i) {
		for(int j=0;j<=i;j++) {
		if(accountNo==a[j]) {
			availBalance=b[j];
		}
		else {
			store(i);
		}
	}
}
	public void store(int i) {
		a[i]=accountNo;
		b[i]=availBalance;
	}
	public void details() {
		System.out.print("Enter the Name : ");
		name = sc.nextLine();
		System.out.print("Enter the Account Number  : ");
		accountNo=sc.nextLong();
	}
	public void deposit() {
		System.out.print("Enter the amount you want to deposit : ");
		deposit=sc.nextInt();
		availBalance+=deposit;
		b[BankApplication.i-1]=availBalance;
		System.out.println("Available Balance : "+availBalance);
		dataStore();
	}
	public void withdrawal() {
		System.out.print("Enter the amount you want to withdraw : ");
		withdrawal=sc.nextInt();
		if(withdrawal>availBalance) {
			withdrawal=0;
			System.out.println("Insufficient Balance");
		}
		else {
			availBalance-=withdrawal;
			dataStore();
		}
		b[BankApplication.i-1]=availBalance;
		System.out.println("Available Balance : "+availBalance);
	}
	public void dataStore() {
		String s1="";
		if(BankApplication.option.equalsIgnoreCase("deposit")){
			s1=" has deposited rupees "+String.valueOf(deposit)+" only";
		}
		if(BankApplication.option.equalsIgnoreCase("withdraw")) {
			s1=" has withdrawn rupees "+String.valueOf(withdrawal)+" only";
		}
		if(BankApplication.option.equalsIgnoreCase("change")) {
			if(Clerk.change.equalsIgnoreCase("name"))
			s1=" has changed their Name to "+Clerk.newName;
			else
				s1=" has changed their Phone Number to "+Clerk.phoneNumber;
		}
		s=s+name+" with Account No "+String.valueOf(accountNo)+s1+"  ";
	}
}

