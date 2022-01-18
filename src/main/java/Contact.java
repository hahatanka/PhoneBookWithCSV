

public class Contact {

    public String name;
    public String phone;
    public String email;

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return
                "\nName: "  + name +
                ", Phone: " + phone +
                ", Email: " + email ;
    }
}
