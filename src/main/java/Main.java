import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

       Menu menu = new Menu();

        try {
            menu.start();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

}





