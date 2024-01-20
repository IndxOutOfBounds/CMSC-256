package cmsc256;

public class CollegeStudent extends Person{
    private String level;
    public CollegeStudent() {
        super();
        level = "Freshman";
    }
    public CollegeStudent(String firstName, String middleName, String lastName, Address address, String phone, String email, String level) throws IllegalArgumentException{
        super(firstName, middleName, lastName, address, phone, email);
        if (level.equalsIgnoreCase("Freshman")|| level.equalsIgnoreCase("Sophomore")
                || level.equalsIgnoreCase("Junior") ||level.equalsIgnoreCase("Senior")
                || level.equalsIgnoreCase("Graduate")) {
            this.level = level;
        } else {
            throw new IllegalArgumentException();
        }
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        if (level.equalsIgnoreCase("Freshman")|| level.equalsIgnoreCase("Sophomore")
                || level.equalsIgnoreCase("Junior") ||level.equalsIgnoreCase("Senior")
                || level.equalsIgnoreCase("Graduate")) {
            this.level = level;
        }  else {
            throw new IllegalArgumentException();
        }
    }
    public String toString() {
        String endString;
        endString = super.toString() + "\n College Level: " + level;
        return endString;
    }
}
