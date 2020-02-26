import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class FormPane extends GridPane {

    private static final String MALE = "Male";
    private static final String FEMALE = "Female";
    public static final String DEFAULT = "Choose:";

    private TextField tfFirstName;
    private TextField tfLastName;
    private TextField tfID;
    private TextField tfBirthDate;
    private ComboBox<String> genderComboBox;

    public FormPane() {
        setDimensions();
        createTextFields();
    }

    private void setDimensions() {
        this.setAlignment(Pos.TOP_CENTER);
        this.setHgap(10);
        this.setVgap(10);
        int paddingVal = 25;
        this.setPadding(new Insets(paddingVal, paddingVal, 0, paddingVal));
    }

    private void createTextFields() {
        // Info pane:
        Text title = new Text("Participant Form:");
        title.setFont(Font.font("Helvetica Neue", FontWeight.NORMAL, 40));
        this.add(title, 0, 0, 2, 1);
        Label pFirstName = new Label("First name:");
        tfFirstName = new TextField();
        tfFirstName.setPromptText("First name");
        this.add(pFirstName, 0, 1);
        this.add(tfFirstName, 1, 1);
        Label pLastName = new Label("Last name:");
        tfLastName = new TextField();
        tfLastName.setPromptText("Last name");
        this.add(pLastName, 0, 2);
        this.add(tfLastName, 1, 2);
        Label pID = new Label("ID:");
        tfID = new TextField();
        tfID.setPromptText("Id (9 numbers)");
        this.add(pID, 0,  3);
        this.add(tfID, 1, 3);
        Label pBirthDate = new Label("Date of birth:");
        tfBirthDate = new TextField();
        tfBirthDate.setPromptText("(dd/mm/yyyy)");
        this.add(pBirthDate, 0, 4);
        this.add(tfBirthDate, 1, 4);
        genderComboBox = new ComboBox<>();
        ObservableList<String> genderList = FXCollections.observableArrayList(MALE, FEMALE);
        genderComboBox.setItems(genderList);
        Label pGender = new Label("Gender:");
        getGenderComboBox().valueProperty().setValue(DEFAULT);
        this.add(pGender, 0, 5);
        this.add(genderComboBox, 1, 5);
    }

    public TextField getTfFirstName() {
        return tfFirstName;
    }

    public TextField getTfLastName() {
        return tfLastName;
    }

    public TextField getTfID() {
        return tfID;
    }

    public TextField getTfBirthDate() {
        return tfBirthDate;
    }

    public ComboBox<String> getGenderComboBox() {
        return this.genderComboBox;
    }
}
