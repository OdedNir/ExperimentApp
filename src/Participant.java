import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.TreeMap;

public class Participant implements Serializable {

    private String firstName;
    private String lastName;
    private String id;
    private LocalDate dateOfBirth;
    // TODO: Add these to the TableView:
    private String gender;


    private TreeMap<String, String> researches;

    private static final int ID_LENGTH = 9;

    public Participant(String firstName, String lastName, String id, String birthDate, String gender) {
        setFirstName(firstName);
        setLastName(lastName);
        setID(id);
        setDateOfBirth(birthDate);
        this.gender = gender;
        researches = new TreeMap<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getId() {
        return id;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private void setID(String id) {
        if (id.length() != ID_LENGTH) {
            this.id = null;
            return;
        }
        for (int i = 0; i < id.length(); i++) {
            if (id.charAt(i) < '0' || id.charAt(i) > '9') {
                this.id = null;
                return;
            }
        }
        this.id = id;
    }

    private void setDateOfBirth(String dateOfBirth) {
        if (dateOfBirth == null){
            this.dateOfBirth = null;
        } else {
            if (dateOfBirth.length() != 10) {
                this.dateOfBirth = null;
                return;
            }
            for (int i = 0; i < dateOfBirth.length(); i++) {
                if ((i != 2 && i != 5) && (dateOfBirth.charAt(i) < '0' || dateOfBirth.charAt(i) > '9')) {
                    this.dateOfBirth = null;
                    return;
                }
            }
            int day = Integer.parseInt(dateOfBirth.substring(0, 2));
            int month = Integer.parseInt(dateOfBirth.substring(3, 5));
            int year = Integer.parseInt(dateOfBirth.substring(6, 10));
            this.dateOfBirth = LocalDate.of(year, month, day);
        }
    }

    public TreeMap<String, String> getResearches() {
        return researches;
    }

    public void addResearch(String research, String info) {
        researches.put(research, info);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return getFirstName().equals(that.getFirstName()) &&
                getLastName().equals(that.getLastName()) &&
                getId().equals(that.getId()) &&
                getDateOfBirth().equals(that.getDateOfBirth());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getId(), getDateOfBirth());
    }
}
