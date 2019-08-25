
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;


public class Entry {

    String date;
    String description;
    Double debit;
    Double credit;
    int type;
    DateFormat d,d2;

    public Entry(String date,String desc,String debit,String credit){

        this.date = date;
        this.description=desc;
        this.debit=debit.isEmpty()?0.0:Double.parseDouble(debit.replace(",",""));
        this.credit=credit.isEmpty()?0.0:Double.parseDouble(credit.replace(",",""));
        this.type=getType();
    }

    @Override
    public String toString() {
        return date+"\t||\t"+description+"\t||\t"+debit+"\t||\t"+credit+"\t||\t"+type;
    }

    private int getType(){
        int  t = 5;
        if(this.debit==0.0)
            t=1;
        else if (this.credit==0.0)
            t=-1;
        return t;
    }
//    private String parseDate(String date){
//        d = new SimpleDateFormat("dd/mm/yyyy");
//        try {
//            d.parse(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return
//    }
}

