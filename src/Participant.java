import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Participant implements Serializable {

    private String firstName;
    private String lastName;
    private String id;
    private LocalDate birthDate;

    private static final int ID_LENGTH = 9;

    public Participant(String firstName, String lastName, String id, String birthDate) {
        setFirstName(firstName);
        setLastName(lastName);
        setID(id);
        setBirthDate(birthDate);
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

    public LocalDate getBirthDate() {
        return birthDate;
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

    private void setBirthDate(String birthDate) {
        if (birthDate == null){
            this.birthDate = null;
        } else {
            if (birthDate.length() != 10) {
                this.birthDate = null;
                return;
            }
            for (int i = 0; i < birthDate.length(); i++) {
                if ((i != 2 && i != 5) && (birthDate.charAt(i) < '0' || birthDate.charAt(i) > '9')) {
                    this.birthDate = null;
                    return;
                }
            }
            int day = Integer.parseInt(birthDate.substring(0, 2));
            int month = Integer.parseInt(birthDate.substring(3, 5));
            int year = Integer.parseInt(birthDate.substring(6, 10));
            this.birthDate = LocalDate.of(year, month, day);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return getFirstName().equals(that.getFirstName()) &&
                getLastName().equals(that.getLastName()) &&
                getId().equals(that.getId()) &&
                getBirthDate().equals(that.getBirthDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getId(), getBirthDate());
    }
}
