

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbHandler {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private final String db = "afjoA7w6GW";
    private final String table = "Entry";
    private final String selectAll = "select * from " + db + "." + table;

    List<DbEntry> dBentries = new ArrayList<>();

    public DbHandler() {
        try {
            // Statements allow to issue SQL queries to the database
            connect = ConnectionFactory.getConnection();
            statement = connect.createStatement();
            // Result set get the result of the SQL query
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

    private void writeMetaData(ResultSet resultSet) throws SQLException {
        //  Now get some metadata from the database
        // Result set get the result of the SQL query

        System.out.println("The columns in the table are: ");

        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
            System.out.println("Column " + i + " " + resultSet.getMetaData().getColumnName(i));
        }
    }

    public void getFromDb(String query) {
        // ResultSet is initially before the first data set


        try {
            resultSet = statement
                    .executeQuery(query);
            while (resultSet.next()) {
                // It is possible to get the columns via name
                // also possible to get the columns via the column number
                // which starts at 1
                // e.g. resultSet.getSTring(2);
                String date = resultSet.getString("Date");
                Double debit = resultSet.getDouble("Debit");
                Double credit = resultSet.getDouble("Credit");
                String desc = resultSet.getString("Description");
//                String type = resultSet.getString("Type");
                Double balance = resultSet.getDouble("Balance");
//                Timestamp timestamp = resultSet.getTimestamp("Timestamp");
//                DbEntry entry = new DbEntry(date, desc, amount, balance, timestamp);
                DbEntry entry = new DbEntry(date, desc, debit,credit, balance);
                dBentries.add(entry);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void setEntries(List<Entry> entries) {
        if (dBentries.size() == 0)
            getFromDb(selectAll);
        String query = "insert into " + db + "." + table + " values (?, ?, ?, ?,? )";
//        String query = "insert into "+db+"."+table+" values (?, ?, ?, ? ,?,?)";

        System.out.println("Number for existing entries :"+ dBentries.size() );
        Entry firstEntry = entries.get(0);
        int count = 0;
        boolean start = false;
        for (DbEntry dBentry : dBentries) {
            if (dBentry.date.equals(firstEntry.date) && dBentry.debit.equals(firstEntry.debit) && dBentry.credit.equals(firstEntry.credit)) {
                start = true;
            }
            if (start)
                count++;

        }

        System.out.println("Number for Duplicate entries :"+ count);
        try {
            preparedStatement = connect
                    .prepareStatement(query);
            // "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
            // Parameters start with 1

            Double old = getBalance();
            Double balance;

            for(int i=count;i<entries.size();i++){
                Entry entry = entries.get(i);
                balance = old + (entry.type * (entry.type==1?entry.credit:entry.debit));
                try{

                    preparedStatement.setString(1, entry.date);
                    preparedStatement.setString(2, entry.description);
                    preparedStatement.setDouble(3, entry.debit);
                    preparedStatement.setDouble(4, entry.credit);
                    preparedStatement.setDouble(5, balance);
    //                    preparedStatement.setTimestamp(5, new Timestamp(new java.util.Date().getTime()));
                    preparedStatement.executeUpdate();
                    preparedStatement.clearParameters();
                    old = balance;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            }catch(SQLException e){
                e.printStackTrace();
            }
        int entriesUpdated = entries.size()-count>0?entries.size()-count:0;
        System.out.println("Number for entries updated :"+ entriesUpdated);

    }

    public Double getBalance() {
        return dBentries.get(dBentries.size() - 1).balance;
    }

//    public int getMultiplier(){
//        return entries.get(entries.size()-1).amount;
//    }
}
