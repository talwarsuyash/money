import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class MoneyMain {
    private static final String db = "afjoA7w6GW";
    private static final String table = "Entry";
    static DbHandler h = new DbHandler();
    static String query = "select * from " + db + "." + table+" ";
    public static void main(String Args[]) throws Exception {


        String filePath = "C:\\Users\\Suyash Talwar\\Downloads\\";
//        String fileName = "Savings_Stmt_040819183852.xls";
//        String fileName = "Savings_Stmt_24081921403.XLS";
//        String fileName = "Savings_Stmt_240819234133.xls";
//        String fileName = "Savings_Stmt_240819234313.xls";
//        String fileName = "Savings_Stmt_300819232910.xls";
//        update(filePath,fileName);
//        showSpent();
        showMonth("august",2019);
    }

    private static void showSpent() throws Exception{
        List<DbEntry> dayOnes = h.getAllFromDb("SELECT * FROM `Entry` WHERE Description like \"%SALARY%\"");
        dayOnes.add(h.getLast());
        double lastBalance = dayOnes.get(0).balance;
        DecimalFormat f2 = new DecimalFormat("#.##");
//        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");

        for(DbEntry entry:dayOnes.subList(1,dayOnes.size())){
            LocalDate localDate = entry.date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String whichMonth = localDate.getMonth().toString().substring(0,3)+"\t";
            int whichYear = localDate.getYear();
            System.out.println("Net Spent for "+whichMonth+whichYear +"\t"+ f2.format(Math.abs(entry.balance-lastBalance-entry.credit)));
            lastBalance=entry.balance;
        }

    }

    private static void showMonth(String month,int year) throws Exception{
        Date date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(month);
        DecimalFormat df = new DecimalFormat("00");
        String d = df.format(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonth().getValue());
        String q= query+"where date like "+"\"%/"+d+"/"+year+"\"";
        System.out.println(q);
        List<DbEntry> monthEntries = h.getAllFromDb(q);
        monthEntries.forEach(entry ->System.out.println(entry));
        

    }

    private static void update(String filePath,String fileName) throws  IOException{

        List<Entry> entries = DataSurpler.readFile(filePath+fileName);


//        entries.forEach(entry -> System.out.println(entry.toString()));
        h.setEntries(entries);
        h.getAllFromDb(query);

//        h.dBentries.forEach(entry -> entry.print());
    }
}
