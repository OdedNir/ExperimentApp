import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class App extends Application {

    // TODO:
    //  Add a status label for:
    //  1. added participant
    //  2. removed participant
    //  3. participant that was not added (Already exists).


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Data Tracking");
        FormPane formPane = new FormPane();
        TablePane tablePane = new TablePane();
        tablePane.loadAllParticipantsFromFile();
        HBox hBox = new HBox(formPane, tablePane);
        Scene scene = new Scene(hBox, 1000, 500);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(350);
        primaryStage.setMinWidth(400);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();

        clickAddBtn(formPane, tablePane);
        clickClearBtn(formPane);
        clickRemoveBtn(formPane, tablePane);

        primaryStage.setOnCloseRequest(e -> {
            tablePane.terminate();
        });
    }

    public void clickAddBtn(FormPane formPane, TablePane tablePane) {
        formPane.getAddBut().setOnAction(e -> {
            String fName = formPane.getTfFirstName().getText();
            String lName = formPane.getTfLastName().getText();
            String id = formPane.getTfID().getText();
            String date = formPane.getTfBirthDate().getText();
            Participant p = new Participant(fName,lName, id, date);
            if (!fName.isEmpty() && !lName.isEmpty() && !id.isEmpty() && !date.isEmpty()) {
                // Will add a participant only if the fields are not empty.
                if (tablePane.addParticipant(p)) {
                    clearTextFields(formPane);
                }
            }
        });
    }

    public void clickRemoveBtn(FormPane formPane, TablePane tablePane) {
        // Pressing the remove button will remove a participant from the table pane and will clear all text fields.
        formPane.getRemoveBut().setOnAction(e -> {
            String fName = formPane.getTfFirstName().getText();
            String lName = formPane.getTfLastName().getText();
            String id = formPane.getTfID().getText();
            String date = formPane.getTfBirthDate().getText();
            if (!fName.isEmpty() && !lName.isEmpty()) {
                if (tablePane.removeByName(fName, lName)) {
                    clearTextFields(formPane);
                }
            } else if (!id.isEmpty()) {
                if (tablePane.removeById(id)) {
                    clearTextFields(formPane);
                }
            }
        });
    }

    public void clickClearBtn(FormPane formPane) {
        // Pressing the clear button will clear all text fields.
        formPane.getClearBut().setOnAction(e -> {
            clearTextFields(formPane);
        });

    }

    public void clearTextFields(FormPane formPane) {
        formPane.getTfFirstName().clear();
        formPane.getTfLastName().clear();
        formPane.getTfID().clear();
        formPane.getTfBirthDate().clear();
    }
}
