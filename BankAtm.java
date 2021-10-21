import java.util.*;
 class user
{
    int acc_no=0;
    String name=" ";
    String type=" ";
    int balance=0;
}
class transaction 
{
    int money;
    String type;
    long date;
    void add(int money,String type,long date)
    {
        this.money=money;
        this.type=type;
        this.date=date;
    }
}
class InvalidAccountException extends Exception{
    InvalidAccountException(String msg)
    {
        super(msg);
    }
}
class Testexception{
    public  Boolean checkaccount(int c)throws InvalidAccountException
    {
        if(c==1 || c==2)
        {
            return true;
        }
        else throw new InvalidAccountException("choose valid option!!");

    }
    public Boolean validbal(int n)throws InvalidAccountException
    {
        if(n>500)
        {
            return true;
        }
        else throw new InvalidAccountException("Minimum balance should be maintained!!");
    }
}
public class BankAtm extends transaction{
    public static void main(String args[]){
    Scanner sc=new Scanner(System.in);
    HashMap<Integer,user> hm=new HashMap<Integer,user>();
    HashMap<Integer, ArrayList<transaction>> hs=new HashMap<Integer, ArrayList<transaction>>(); 
    int choice=0,amount=0,deposit=0,withdraw=0,acc=0;
    Testexception ex=new Testexception();
    while(true)
    {
        System.out.println("------------------------");
        System.out.println(" 1.CREATE ACCOUNT \n 2.DEPOSIT \n 3.WITHDRAWL \n 4.Balance \n 5.Mini Statement \n 6.Exit");
        System.out.println("-----------------");
        System.out.println("Enter your choice:");
        choice=sc.nextInt();
        switch(choice)
        {
            case 1: user obj=new user();
                    System.out.println("Enter account number:");
                    try{
                    acc=sc.nextInt();
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                        String ch=sc.next();
                        break;
                    }
                    if(hm.get(acc)==null)
                    {
                    obj.acc_no=acc;
                    System.out.println("Enter account Holder name:");
                    obj.name=sc.next();
                    System.out.println("Choose account type:(1.Savings/2.Current)");
                    int c=sc.nextInt();
                    try
                    {   
                    ex.checkaccount(c);                
                    if(c==1)
                    {
                        obj.type="Savings";
                    }
                    else if(c==2)
                    {
                        obj.type="Current";
                    }
                    }
                    catch (Exception e)
                    {
                        System.out.println(e);
                        break;
                    }
                    System.out.println("Enter amount you want to deposit(Minimum Rs.3000");
                    obj.balance=sc.nextInt();
                    deposit=obj.balance;
                    if(deposit>=3000){
                    ArrayList<transaction> as = new ArrayList<transaction>();
                    transaction obj1=new transaction();
                    long millis=System.currentTimeMillis();  
                    obj1.add(deposit,"Credit",millis);
                    System.out.println("Account created Successfully!!!");
                    hm.put(acc,obj);
                    as.add(obj1);
                    hs.put(acc,as);
                    System.out.println("Successfully deposited!!");
                    }
                    else System.out.println("Deposit atleast Rs.3000 to open your Account!");
                    }
                    else System.out.println("Account already exist!!!");
                    break;
            case 2: System.out.println("Enter account number:");
                    try{
                    acc=sc.nextInt();
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                        String ch=sc.next();
                        break;
                    }
                    if(hm.get(acc)!=null)
                    {
                    System.out.println("Enter amount you want to deposit:");
                    deposit=sc.nextInt();
                    transaction obj2=new transaction();
                    ArrayList<transaction> ap=hs.get(acc);
                    long millis1=System.currentTimeMillis();
                    obj2.add(deposit,"Credit",millis1);
                    ap.add(obj2);
                    hs.put(acc,ap);
                    user obj3=(user)hm.get(acc);
                    obj3.balance=obj3.balance+deposit;
                    System.out.println("Successfully deposited!!");
                    }
                    else System.out.println("Account Not found!!!");
                    break;
            case 3: System.out.println("Enter account number:");
                    try{
                    acc=sc.nextInt();
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                        String ch=sc.next();
                        break;
                    }
                    if(hm.get(acc)!=null)
                    {
                    System.out.println("Enter amount you want to withdraw:");
                    withdraw=sc.nextInt();
                    user obj4=(user)hm.get(acc);
                    int ab=obj4.balance-withdraw;
                    try{
                        ex.validbal(ab);
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                        break;
                    }
                    ArrayList<transaction> ak=hs.get(acc);
                    transaction ob4=new transaction();
                    long millis2=System.currentTimeMillis();
                    ob4.add(withdraw,"Debit",millis2);
                    ak.add(ob4);
                    hs.put(acc,ak);
                    obj4.balance=obj4.balance-withdraw;
                    System.out.println("Please collect your amount!");
                    }
                    else System.out.println("Account Not found!!!");
                    break;
            case 4: System.out.println("Enter account number:");
                    try{
                    acc=sc.nextInt();
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                        String ch=sc.next();
                        break;
                    }
                    user o=new user();
                    o=(user)hm.get(acc);
                    if(o!=null)
                    System.out.println("Available balance is:"+o.balance);
                    else System.out.println("Enter valid account number!!");
                    break;
            case 5:System.out.println("Enter account number:");
                   try{
                    acc=sc.nextInt();
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                        break;
                    }
                   int i=0;
                   if(hm.get(acc)!=null)
                   {
                   user obj5=(user)hm.get(acc);
                   ArrayList<transaction> ar=hs.get(acc);
                   System.out.println("--------------------------MINI STATEMENT---------------------");
                   System.out.println("Account number:"+obj5.acc_no);
                   System.out.println("NAME:"+obj5.name);
                   System.out.println("Type:"+obj5.type);
                   System.out.println("      DATE & TIME      "+ "\t"  +"\t"+" TRANSACTION TYPE    MONEY ");
                   while(i<ar.size())
                   {
                       transaction r=(transaction) ar.get(i);
                       java.util.Date date=new java.util.Date(r.date);  
                       System.out.println(date+"       "+r.type + " \t      "+r.money);
                       i++;
                   }
                   System.out.println("Total balance is:"+obj5.balance);
                   System.out.println("End of the statement..........");
                   }
                   else System.out.println("Account Not found!!!");
                   break;
            case 6:System.exit(6);
            default:System.out.println("Enter correct option");      
        }

    }
  }
}