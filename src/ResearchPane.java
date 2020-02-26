import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.io.RandomAccessFile;

public class ResearchPane extends GridPane {

    public static final String CHOOSE_ADD = "Choose/Add (Click Enter To Add):";
    private static final String FILE_NAME = "Researches.txt";

    private static ComboBox<String> researchesComboBox;
    private static ObservableList<String> researchesList = FXCollections.observableArrayList();
    private TextArea textArea;

    public ResearchPane(int height, int width) {
        setDimensions();
        creatComboBox();
        keyPressed();
        createTextArea(height, width);
    }

    private void setDimensions() {
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(10, 25, 0, 25));
    }

    private void keyPressed() {
        researchesComboBox.setOnKeyPressed(ke -> {
            KeyCode keyCode = ke.getCode();
            if (keyCode.equals(KeyCode.ENTER)) {
                String s = researchesComboBox.getValue();
                if (s != null) {
                    if (!s.equals("")) {
                        String research = researchesComboBox.getValue();
                        writeResearchToFile(research);
                        researchesList.add(research);
                    }
                }
            }
        });
    }

    private void writeResearchToFile(String research) {
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rw")){
            file.seek(file.length());
            research = research.substring(0,1).toUpperCase() + research.substring(1);
            file.writeUTF(research);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadResearchesFromFile() {
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "r")){
            while (file.getFilePointer() < file.length()) {
                researchesList.add(file.readUTF());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void creatComboBox() {
        researchesComboBox = new ComboBox<>();
        researchesComboBox.setItems(researchesList);
        researchesComboBox.setMinWidth(230);
        researchesComboBox.setEditable(true);
        researchesComboBox.setPromptText(CHOOSE_ADD);
        HBox hBox = new HBox(new Label("Researches:"), researchesComboBox);
        hBox.setSpacing(13);
        this.add(hBox, 0, 0);
    }

    private void createTextArea(int height, int width) {
        textArea = new TextArea();
        textArea.setPromptText("Add Research Info Here:");
        textArea.setPrefHeight(height);
        textArea.setPrefWidth(width);
        textArea.setWrapText(true);
        this.add(textArea, 0, 1);
    }

    public ComboBox<String> getResearchesComboBox() {
        return researchesComboBox;
    }

    public TextArea getTextArea() {
        return textArea;
    }
}
