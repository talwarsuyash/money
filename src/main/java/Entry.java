
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;


public class Entry {

    String date;
    String description;
    Double amount;
    int type;
    DateFormat d,d2;

    public Entry(String date,String desc,String amount){

        this.date = date;
        this.description=desc;
        this.amount=Double.parseDouble(amount.replace(",",""));
        this.type=getType();
    }

    @Override
    public String toString() {
        return date+"\t||\t"+description+"\t||\t"+amount+"\t||\t"+type;
    }

    private int getType(){
        int  t = 1;
        String[] credits = {"OUTWARD","CREDIT"};
        String[] debits = {"WITHDRAWAL","PURCHASE","INWARD"};
        if(Arrays.stream(debits).parallel().anyMatch(description::contains))
            t=-1;
//        else if ((Arrays.stream(debits).parallel().anyMatch(description::contains)))
//            t='d';
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

