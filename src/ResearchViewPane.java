import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class ResearchViewPane extends GridPane {

    private TextField researchNameField;
    private TextArea researchInfo;

    public ResearchViewPane(int height, int width) {
        setDimensions();
        createLabels();
        createTextArea(height, width);
    }

    private void setDimensions() {
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(10, 25, 0, 25));
    }

    private void createLabels() {
        Label researchNameLabel = new Label("Research Name:");
        researchNameField = new TextField();
        researchNameField.setEditable(false);
        researchNameField.setPromptText("Research Name");
        HBox hBox = new HBox(researchNameLabel, researchNameField);
        hBox.setSpacing(15);
        this.add(hBox, 0, 0);
    }

    private void createTextArea(int height, int width) {
        researchInfo = new TextArea();
        researchInfo.setPromptText("Research info");
        researchInfo.setPrefHeight(height);
        researchInfo.setPrefWidth(width);
        researchInfo.setWrapText(true);
        researchInfo.editableProperty().setValue(false);
        researchInfo.setOnKeyPressed(ke -> {
            KeyCode keyCode = ke.getCode();
            if (keyCode.equals(KeyCode.ENTER)) {
                // TODO: edit participant research info
            }
        });
        this.add(researchInfo, 0, 1);
    }

    public TextField getResearchNameField() {
        return researchNameField;
    }

    public TextArea getResearchInfo() {
        return researchInfo;
    }
}
