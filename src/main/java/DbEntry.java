
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;


public class DbEntry {

    Date date;
    String description;
    Double debit;
    Double credit;
    Double balance;
    SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
//    Timestamp timestamp;


//  public DbEntry(java.sql.Date date, String desc, Double amount, Double balance, Timestamp timestamp){
//        this.date  = date;
//        this.description=desc;
//        this.amount=amount;
//        this.balance=balance;
//        this.timestamp=timestamp;
//    }

    public DbEntry(String date, String desc, Double debit,Double credit, Double balance){
        try{
            this.date  = f.parse(date);
        }
        catch (Exception e ){
            e.printStackTrace();
        }
        this.description=desc;
        this.debit=debit;
        this.credit=credit;
        this.balance=balance;
    }


    @Override
    public String toString() {
        return f.format(date)+"\t||\t"+description+"\t||\t"+debit+"\t||\t"+credit+"\t||\t"+balance;
    }
    public void print(){
        System.out.println("Date: " + date );
        System.out.println("Desc: " + description);
        System.out.println("Debit : " + debit);
        System.out.println("Credit : " + credit);
        System.out.println("Balance : " + balance);
//        System.out.println("Timestamp : " + timestamp);
    }

        public String getEntryAsString(){
        return  f.format(this.date);
    }


}
