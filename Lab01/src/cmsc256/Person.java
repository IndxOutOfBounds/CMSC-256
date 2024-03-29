package cmsc256;

public class Person {
    private int id;
    private Name name;
    private Address homeAddress;
    private String phoneNumber;
    private String email;
    static int recordNumber;
    public Person() {

        name = new Name();

        homeAddress = new Address();

        phoneNumber = "None given";

        email = "None given";

        id = ++recordNumber;

    }
    public Person(String first, String middle, String last,  Address address, String phone, String email){
        name = new Name(first, middle, last);
        homeAddress = address;
        phoneNumber = phone;
        this.email = email;
        id = ++ recordNumber;
    }

    public int getId() {
        return id;
    }
    //// change getter?
    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String toString() {
        String output;
        output = name + "\n" + "Home Address: " + homeAddress + "\n" + "Phone Number: " + phoneNumber
        + "\n" + "Email Address: " + email + "\n" + "ID: " + id;
        return output;
    }
}
