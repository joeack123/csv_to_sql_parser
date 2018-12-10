import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class csv_to_sql {
    public static void main(String[] args) {
        //All the input needed for the input and output file paths
        String filePath = "C:/Users/joeac/OneDrive/Desktop/Hogwarts/";
        Scanner s = new Scanner(System.in);
        System.out.print("Enter a input file name: ");
        String csvFile = s.next();
        System.out.print("Enter an output file name: ");
        String outFile = s.next();
        File newFile = new File(outFile);

        //Used to know how many lines to write out before creating a new Insert Statement
        System.out.print("Enter the number of lines in you want to insert into: ");
        int linesInCSVFile = s.nextInt();
        //Creates the specific values for the insert statements
        System.out.println("Enter the values you want to insert");
        StringBuilder sb = new StringBuilder();
        int counter  = 0;
        while(counter < linesInCSVFile) {
            sb.append(s.next()+", ");
            counter++;
        }
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath+csvFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath+outFile));
            bw.write("INSERT INTO "+ csvFile.substring(0, csvFile.lastIndexOf('.'))
                    +"("+sb.toString().substring(0, sb.toString().lastIndexOf(','))+") VALUES\n");
            while((line = br.readLine()) != null) {
                String[] values = line.split(",");
                for(int i = 0; i < values.length; i+=linesInCSVFile) {
                    bw.write("("+Arrays.toString(values).replace("[","").replace("]","")+"),\n");
                }
            }
            br.close();
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            try {
                newFile.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
