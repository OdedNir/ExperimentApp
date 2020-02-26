import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Participant implements Serializable {

    private String firstName;
    private String lastName;
    private String id;
    private LocalDate dateOfBirth;
    private String gender;
    private String researchName;
    private String researchInfo;

    private static final int ID_LENGTH = 9;

    public Participant(String firstName, String lastName, String id, String birthDate, String gender, String researchName, String researchInfo) {
        setFirstName(firstName);
        setLastName(lastName);
        setID(id);
        setDateOfBirth(birthDate);
        setGender(gender);
        setResearchName(researchName);
        setResearchInfo(researchInfo);
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

    public String getResearchName() {
        return researchName;
    }

    public String getResearchInfo() {
        return researchInfo;
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

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setResearchName(String researchName) {
        this.researchName = researchName;
    }

    public void setResearchInfo(String researchInfo) {
        this.researchInfo = researchInfo;
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

    @Override
    public String toString() {
        return "Participant{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id='" + id + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", researchName='" + researchName + '\'' +
                ", researchInfo='" + researchInfo + '\'' +
                '}';
    }
}
