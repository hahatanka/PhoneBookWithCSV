import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class Phonebook {

    FileHandler fileHandler = new FileHandler();
    private static String value = "";
    String pathname = "/Users/rudens/Desktop/Java docs/PhoneBook.txt";
    Date date = new Date();


    public Contact showAllContacts() {
        ArrayList<Contact> contacts = fileHandler.readFullFile();
        ArrayList<String> names = new ArrayList<>();

        for (Contact contact : contacts) {
            names.add(contact.getName());
        }
        System.out.println(names); //JUST TO CHECK IF NAMES CORRESPOND

        String[] array = names.toArray(new String[names.size()]);

        System.out.println(Arrays.toString(array));
        String choice = (String) JOptionPane.showInputDialog(null,
                "choose a contact", "MENU",
                JOptionPane.QUESTION_MESSAGE,
                null,
                array,
                array[0]);
        Contact contact = findContact(choice);
        return contact;
    }

    public void addContact() {
        fileHandler.createFile(pathname);

        JTextField nameField = new JTextField(10);
        JTextField phoneField = new JTextField(10);
        JTextField emailField = new JTextField(20);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Name"));
        myPanel.add(nameField);
        myPanel.add(Box.createHorizontalStrut(15));
        myPanel.add(new JLabel("Phone"));
        myPanel.add(phoneField);
        myPanel.add(new JLabel("Email"));
        myPanel.add(emailField);

        JOptionPane.showConfirmDialog(null, myPanel,
                "Please enter the NAME, PHONE and EMAIL", JOptionPane.OK_CANCEL_OPTION);
        try {
            String name = nameField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();

             if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                 JOptionPane.showMessageDialog(null,
                         "ERROR, fill in all the fields");

             }else if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty() &&
                     phone.matches("[0-9]+") && phone.length() <= 15) {

                     Contact contact = new Contact(name, phone, email);
                     fileHandler.writeToFile((name + "," + phone + "," + email), pathname);
                     JOptionPane.showMessageDialog(null,
                             "Contact " + contact.name + " added successfully");
                 } else {
                     JOptionPane.showMessageDialog(null,
                             "ERROR, use numerics to enter phone number, max 15");
                 }

            } catch(Exception ex){
                JOptionPane.showMessageDialog(null, "ERROR");
        }
    }

    public Contact findContact(String input) {
        for (Contact contact : fileHandler.readFullFile()) {
            if (contact.getName().equalsIgnoreCase(input)) {
                return contact;
            }
        }
        return null;
    }


    public void removeContact(Contact contact) throws FileNotFoundException {
        String recordToRemove = contact.getName();
        fileHandler.deleteRecord(recordToRemove);
    }

    public void editContact(Contact contact) {
        JTextField nameField = new JTextField(contact.name, 10);
        JTextField phoneField = new JTextField(contact.phone, 10);
        JTextField emailField = new JTextField(contact.email, 20);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Name"));
        myPanel.add(nameField);
        myPanel.add(Box.createHorizontalStrut(15));
        myPanel.add(new JLabel("Phone"));
        myPanel.add(phoneField);
        myPanel.add(new JLabel("Email"));
        myPanel.add(emailField);

        JOptionPane.showConfirmDialog(null, myPanel,
                "Please make the necessary changes", JOptionPane.OK_CANCEL_OPTION);

        try {
            removeContact(contact);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        contact.setName(nameField.getText());
        contact.setPhone(phoneField.getText());
        contact.setEmail(emailField.getText());
        try {
            fileHandler.writeToFile((nameField.getText() + "," + phoneField.getText() + "," + emailField.getText()), pathname);
        } catch (Exception e) {
            System.out.println("error");
        }
        JOptionPane.showMessageDialog(null, contact +
                " has been changed successfully");
    }

    public void sendEmail(String name, String email) {
        String pathname2 = "/Users/rudens/Desktop/Java docs/MailBox.txt";
        fileHandler.createFile(pathname2);

        JTextArea text = new JTextArea(5, 20);
        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("You are sending email to " + name +", " + email));
        myPanel.add(Box.createHorizontalStrut(15));
        myPanel.add(new JLabel("Email Text"));
        myPanel.add(text);

        JOptionPane.showConfirmDialog(null,myPanel,
                "SEND EMAIL", JOptionPane.OK_CANCEL_OPTION);

        if (!text.getText().isEmpty()) {
            String emailMessage = date + " ---- " + text.getText();
            fileHandler.writeToFile(emailMessage , pathname2);
            JOptionPane.showMessageDialog(null, "Email sent to " + name +
                    ". Check MailBox.txt for details");

        }
    }
}


