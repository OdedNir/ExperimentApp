import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Research Data");
        // Pref size:
        primaryStage.setMinHeight(625);
        primaryStage.setMinWidth(1150);

        // Left side of stage:
        FormPane formPane = new FormPane();
        ResearchPane editableResearchPane = new ResearchPane(200, 340);
        ButtonPane buttonPane = new ButtonPane();
        FlowPane flowPane = new FlowPane(formPane, editableResearchPane, buttonPane);

        // right side of stage:
        ResearchViewPane researchViewPane = new ResearchViewPane(400, 340);
        researchViewPane.setAlignment(Pos.TOP_CENTER);
        TablePane tablePane = new TablePane(researchViewPane);
        tablePane.loadAllParticipantsFromFile();
        editableResearchPane.loadResearchesFromFile();

        // The main pane:
        HBox hBox = new HBox(flowPane, tablePane, researchViewPane);
        Scene scene = new Scene(hBox);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Button events:
        clickAddBtn(formPane, tablePane, buttonPane, editableResearchPane);
        clickClearBtn(formPane, buttonPane, editableResearchPane);
        clickRemoveBtn(formPane, tablePane, buttonPane, editableResearchPane);

        // Closing the stage event:
        primaryStage.setOnCloseRequest(e -> {
            tablePane.terminate();
        });
    }

    public void clickAddBtn(FormPane formPane, TablePane tablePane, ButtonPane buttonPane, ResearchPane researchPane) {
        buttonPane.getAddBut().setOnAction(e -> {
            String fName = formPane.getTfFirstName().getText();
            String lName = formPane.getTfLastName().getText();
            String id = formPane.getTfID().getText();
            String date = formPane.getTfBirthDate().getText();
            String gender = formPane.getGenderComboBox().getValue();
            String researchName = researchPane.getResearchesComboBox().getValue();
            String researchInfo = researchPane.getTextArea().getText();
            Participant p = new Participant(fName,lName, id, date, gender, researchName, researchInfo);
            if (!fName.isEmpty() && !lName.isEmpty() && !id.isEmpty() && !date.isEmpty()) {
                // Will add a participant only if the fields are not empty.
                if (tablePane.addParticipant(p)) {
                    clearFields(formPane, researchPane);
                }
            }
        });
    }

    public void clickRemoveBtn(FormPane formPane, TablePane tablePane, ButtonPane buttonPane, ResearchPane researchPane) {
        // Pressing the remove button will remove a participant from the table pane and will clear all text fields.
        buttonPane.getRemoveBut().setOnAction(e -> {
            String fName = formPane.getTfFirstName().getText();
            String lName = formPane.getTfLastName().getText();
            String id = formPane.getTfID().getText();
            if (!fName.isEmpty() && !lName.isEmpty()) {
                if (tablePane.removeByName(fName, lName)) {
                    clearFields(formPane, researchPane);
                }
            } else if (!id.isEmpty()) {
                if (tablePane.removeById(id)) {
                    clearFields(formPane, researchPane);
                }
            }
        });
    }

    public void clickClearBtn(FormPane formPane, ButtonPane buttonPane, ResearchPane researchPane) {
        // Pressing the clear button will clear all text fields.
        buttonPane.getClearBut().setOnAction(e -> {
            clearFields(formPane, researchPane);
        });

    }

    public void clearFields(FormPane formPane, ResearchPane researchPane) {
        formPane.getTfFirstName().clear();
        formPane.getTfLastName().clear();
        formPane.getTfID().clear();
        formPane.getTfBirthDate().clear();
        formPane.getGenderComboBox().valueProperty().setValue(FormPane.DEFAULT);
        researchPane.getResearchesComboBox().getEditor().clear();
        researchPane.getTextArea().clear();
    }
}
