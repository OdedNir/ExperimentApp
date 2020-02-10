import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class ResearchPane extends GridPane {

    public static final String DEFAULT = "Choose:";

    private ComboBox<String> researchesComboBox;
    private ObservableList<String> researchesList = FXCollections.observableArrayList(
            ("Blood"), ("Sweat")
    );

    private TextArea textArea;

    public ResearchPane() {
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(0, 25, 0, 25));
        creatComboBox();
        createTextArea();
    }

    private void creatComboBox() {
        researchesComboBox = new ComboBox<>();
        researchesComboBox.setItems(researchesList);
        researchesComboBox.setMaxWidth(150);
        researchesComboBox.valueProperty().setValue(DEFAULT);
        HBox hBox = new HBox(new Label("Researches:"), researchesComboBox);
        hBox.setSpacing(10);
        this.add(hBox, 0, 0);
    }

    private void createTextArea() {
        textArea = new TextArea();
        textArea.setPrefHeight(200);
        textArea.setPrefWidth(340);
        textArea.setWrapText(true);
        // for the text to be written from right to left:
        //textArea.nodeOrientationProperty().setValue(NodeOrientation.RIGHT_TO_LEFT);
        this.add(textArea, 0, 1);
    }
}
