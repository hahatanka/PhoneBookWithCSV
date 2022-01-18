
import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.Date;

public class Menu {
    String userChoice;
    Phonebook phonebook = new Phonebook();
    private Object Date = new Date();

    public void start() throws FileNotFoundException {

        String[] choices = {"Add a Contact", "View All Contacts", "Find a Contact"};
        userChoice = (String) JOptionPane.showInputDialog(null, "Welcome to Library Management System. " +
                        "Please choose what you want to do:", "MENU",
                JOptionPane.QUESTION_MESSAGE,
                null,
                choices,
                choices[0]);

        if (userChoice== null) {
            handleExit();
        }
        switch (userChoice){
            case "Add a Contact":
               phonebook.addContact();
                start();
               break;
            case "View All Contacts":
                contactActions(phonebook.showAllContacts());
                start();
                break;
            case "Find a Contact":
                String input = JOptionPane.showInputDialog(null, "Please enter the name of the contact");
                Contact contact = phonebook.findContact(input);
                contactActions(contact);
                start();
                break;

            default:
                start();
                break;
        }
    }

    void contactActions(Contact contact) throws FileNotFoundException {

        do {
            String[] choices = {"Call", "Send Email", "Edit", "Delete"};
            String userChoice = (String) JOptionPane.showInputDialog(null, "You found " + "\n"
                            + contact + "\n" + "\nPlease choose what you want to do: ", "MENU",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    choices,
                    choices[0]);
            if (userChoice == null) {
                handleExit();
            }
            switch (userChoice) {
                case "Call":
                    calling(contact.getName(), contact.phone);
                    break;
                case "Send Email":
                    phonebook.sendEmail(contact.name, contact.email);
                    break;
                case "Edit":
                    phonebook.editContact(contact);
                    break;
                case "Delete":
                    phonebook.removeContact(contact);
                    break;
                default:
                    break;
            }
        } while (contact != null);
        handleExit();
    }


    private void calling(String name, String phone) {

        JOptionPane.showMessageDialog(null, "CALLING " + name + "... \n"+phone,
                "PHONE CALL", JOptionPane.OK_CANCEL_OPTION);
    }


    private void handleExit() throws FileNotFoundException {
        int userChoice2;

            userChoice2 = JOptionPane.showConfirmDialog(null, "Exit the program?", "WARNING", JOptionPane.YES_NO_OPTION);

            if (userChoice2 != 0) {
                start();
            }
            System.exit(0);
        }

    }
