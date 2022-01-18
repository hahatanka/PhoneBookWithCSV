
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    private static final int FIELDS = 3;


    public void createFile(String pathname) {
        try {
            File myObj = new File(pathname);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeToFile(String text, String filepath) {
        try {

            PrintWriter myPrintWriter = new PrintWriter(new BufferedWriter(new FileWriter(filepath, true)));

            myPrintWriter.println(text);
            myPrintWriter.flush();
            myPrintWriter.close();

            System.out.println("Successfully wrote to the file.");//TO MAKE SURE,THAT THE RECORD WAS SAVED TO TXT
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public ArrayList<Contact> readFullFile() {
        ArrayList<Contact> contacts = new ArrayList<>();

        try {
            File myObj = new File("/Users/rudens/Desktop/Java docs/PhoneBook.txt");
            Scanner myReader = new Scanner(myObj);
            do {
                String record = myReader.nextLine();
                String[] splitted = record.split(",");
                if (splitted.length == FIELDS) {
                    String name = splitted[0];
                    String phone = splitted[1];
                    String email = splitted[2];
                    Contact contact = new Contact(name, phone, email);
                    contacts.add(contact);
                }
            } while (myReader.hasNextLine());
            myReader.close();
            return contacts;

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }

    public void deleteRecord(String input) throws FileNotFoundException {

        String filePath = "/Users/rudens/Desktop/Java docs/PhoneBook.txt";
        String tempFile = "/Users/rudens/Desktop/Java docs/PhoneBookTemp.txt";
        File oldFile = new File(filePath);
        File newFile = new File(tempFile);

        Scanner myReader = new Scanner(oldFile);
        try{
            FileWriter fileWriter = new FileWriter(tempFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while (myReader.hasNextLine()){
                String record = myReader.nextLine();

                String[] splittedRecord = record.split(",");

                if(!(splittedRecord[0]).equalsIgnoreCase( input)){
                    printWriter.println(record);
                }
            }
            printWriter.flush();
            printWriter.close();
            fileWriter.close();
            bufferedReader.close();
            bufferedWriter.close();
            fileReader.close();

            oldFile.delete();
            File dump = new File(filePath);
            newFile.renameTo(dump);

        }catch (Exception e){
            System.out.println("ERROR DELETING THE RECORD");

        }
    }
}



