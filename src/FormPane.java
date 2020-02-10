import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class FormPane extends GridPane {

    private static final String MALE = "Male";
    private static final String FEMALE = "Female";
    public static final String DEFAULT = "Choose:";

    private Button addBut;
    private Button removeBut;
    private Button clearBut;
    private TextField tfFirstName;
    private TextField tfLastName;
    private TextField tfID;
    private TextField tfBirthDate;
    private ComboBox<String> genderComboBox;


    public FormPane() {
        createTextFields();
        createButtonPane();
    }

    private void createTextFields() {
        this.setAlignment(Pos.TOP_CENTER);
        this.setHgap(10);
        this.setVgap(10);
        int paddingVal = 25;
        this.setPadding(new Insets(paddingVal, paddingVal, paddingVal, paddingVal));
        // Info pane:
        Text title = new Text("Participant Form:");
        title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
        this.add(title, 0, 0, 2, 1);
        Label pFirstName = new Label("First name:");
        tfFirstName = new TextField();
        this.add(pFirstName, 0, 1);
        this.add(tfFirstName, 1, 1);
        Label pLastName = new Label("Last name:");
        tfLastName = new TextField();
        this.add(pLastName, 0, 2);
        this.add(tfLastName, 1, 2);
        Label pID = new Label("ID:");
        tfID = new TextField();
        this.add(pID, 0,  3);
        this.add(tfID, 1, 3);
        Label pBirthDate = new Label("Date of birth\n(dd/mm/yyyy):");
        tfBirthDate = new TextField();
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

    private void createButtonPane() {
        int minWidth = 100;
        int minHeight = 50;
        addBut = new Button("ADD");
        addBut.setMinSize(minWidth, minHeight);
        removeBut = new Button("REMOVE");
        removeBut.setMinSize(minWidth, minHeight);
        clearBut = new Button("CLEAR");
        clearBut.setMinSize(minWidth, minHeight);
        // Button pane:
        HBox hBox = new HBox(addBut, removeBut, clearBut);
        hBox.setSpacing(20);
        this.add(hBox, 0, 6, 2,1);
        hBox.setAlignment(Pos.CENTER);
    }

    public Button getAddBut() {
        return addBut;
    }

    public Button getClearBut() {
        return clearBut;
    }

    public Button getRemoveBut() {
        return removeBut;
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
