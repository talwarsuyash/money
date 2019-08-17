import java.io.IOException;
import java.util.List;

public class MoneyMain {

    public static void main(String Args[]) throws IOException {
        String filePath = "C:\\Users\\Suyash Talwar\\Downloads\\";
//        String fileName = "Savings_Stmt_040819183852.xls";
        String fileName = "Savings_Stmt_170819161042.xls";
        List<Entry> entries = DataSurpler.readFile(filePath+fileName);

        DbHandler h = new DbHandler();

        h.setEntries(entries);
        h.getDbEntries();
//        h.dBentries.forEach(entry -> entry.print());
    }
}
