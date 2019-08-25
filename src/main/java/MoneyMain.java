import java.io.IOException;
import java.util.List;

public class MoneyMain {
    private static final String db = "afjoA7w6GW";
    private static final String table = "Entry";


    public static void main(String Args[]) throws IOException {


        String filePath = "C:\\Users\\Suyash Talwar\\Downloads\\";
//        String fileName = "Savings_Stmt_040819183852.xls";
//        String fileName = "Savings_Stmt_24081921403.XLS";
//        String fileName = "Savings_Stmt_240819234133.xls";
        String fileName = "Savings_Stmt_240819234313.xls";
        update(filePath,fileName);
    }

    private static void update(String filePath,String fileName) throws  IOException{
        String query = "select * from " + db + "." + table;
        List<Entry> entries = DataSurpler.readFile(filePath+fileName);

        DbHandler h = new DbHandler();
//        entries.forEach(entry -> System.out.println(entry.toString()));
        h.setEntries(entries);
        h.getFromDb(query);
//        h.dBentries.forEach(entry -> entry.print());
    }
}
