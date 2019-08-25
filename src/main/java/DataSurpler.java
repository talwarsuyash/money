

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


import java.io.File;


import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DataSurpler {

    public static List<Entry> readFile(String filePathWithName) throws IOException {
        File doc = new File(filePathWithName);
        String htmlData = new String(Files.readAllBytes(Paths.get(filePathWithName)));

        Document d = Jsoup.parse(htmlData);
        return getBodyData(d);
    }



    private static  List<Entry> getBodyData(Document d) {

        List<Element> elements = d.body().getElementsByTag("form")
                .get(0)
//                .getElementsByTag("Table");
                    .getElementsByTag("td");
        List<String> elementStrings = new ArrayList<>();
        elements.forEach(element -> elementStrings.add(element.text()));
//        elementStrings.removeIf(item -> item == null || "".equals(item));
        List<Entry> entries = new ArrayList<>();


        for(int tCount=45;tCount<elementStrings.size();tCount+=6){
//            System.out.println(tCount+"\t"+elementStrings.get(tCount));

            String item = elementStrings.get(tCount);
            if(item.equals("Total"))
                break;
            if(item.contains("/") && item.length()==10) {
                String date = item;
                String desc = elementStrings.get(tCount+1);
                String debit = elementStrings.get(tCount+2);
                String credit = elementStrings.get(tCount+3);
                entries.add(new Entry(date, desc, debit,credit));;
            }
        }
                entries.forEach(entry -> System.out.println(entry));
//                entries.removeIf(entry -> entry.description.contains("POS TRANSACTION "));
        return  entries;
//        int max = entries.get(0).description.length();
//        for(Entry entry :entries){
//            if(entry.description.length()>max){
//                max=entry.description.length();
//            }
//        }
//        System.out.print(max);
    }

}
