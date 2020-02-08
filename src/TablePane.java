import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

public class TablePane extends StackPane {

    private static final int COLUMN_WIDTH = 150;
    private static final String FILE_NAME = "Participants.txt";

    private static ObservableList<Participant> ol = FXCollections.observableArrayList();
    private TableView<Participant> tableView;

    public TablePane() {
        tableView = new TableView<>();
        TableColumn<Participant, String> column1 = new TableColumn<>("First Name");
        column1.setMinWidth(COLUMN_WIDTH);
        column1.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn<Participant, String> column2 = new TableColumn<>("Last Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        column2.setMinWidth(COLUMN_WIDTH);
        TableColumn<Participant, String> column3 = new TableColumn<>("ID");
        column3.setCellValueFactory(new PropertyValueFactory<>("id"));
        column3.setMinWidth(COLUMN_WIDTH);
        TableColumn<Participant, LocalDate> column4 = new TableColumn<>("Birth Date");
        column4.setMinWidth(COLUMN_WIDTH);
        column4.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
        tableView.getColumns().add(column4);
        tableView.setMinWidth(600);
        this.getChildren().add(tableView);
        tableView.setItems(ol);
    }

    public TableView<Participant> getTableView() {
        return tableView;
    }

    public ObservableList<Participant> getOl() {
        return ol;
    }

    public boolean removeById(String id) {
        Participant temp = new Participant("", "", id, "");
        int index = Collections.binarySearch(ol, temp, new idComparator());
        if (ol.remove(index) != null) {
            clearFile();
            saveAllParticipantsToFile();
            return true;
        }
        return false;
    }

    public boolean removeByName(String fName, String lName) {
        Participant temp = new Participant(fName, lName, "", "");
        int index = Collections.binarySearch(ol, temp, new fNameLNameComparator());
        if (ol.remove(index) != null) {
            clearFile();
            saveAllParticipantsToFile();
        }
        return false;
    }

    public void loadAllParticipantsFromFile() {
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "r")) {
            while (file.getFilePointer() < file.length()) {
                ol.add(readParticipant(file));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearFile() {
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rw")) {
            file.setLength(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAllParticipantsToFile() {
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rw")) {
            for (Participant participant : ol) {
                writeParticipant(participant, file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Participant readParticipant(RandomAccessFile file) throws IOException{
        String fName = file.readUTF();
        String lName = file.readUTF();
        String id = file.readUTF();
        String date = file.readUTF();
        return new Participant(fName, lName, id, date);
    }

    public boolean addParticipant(Participant p) {
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rw")) {
            file.seek(file.length());
            if (Collections.binarySearch(ol, p, new fNameLNameComparator()) < 0) { // Will not add if already exists
                writeParticipant(p, file);
                ol.add(p);
                return true;
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    public void writeParticipant(Participant p, RandomAccessFile file) throws IOException {
        file.writeUTF(p.getFirstName());
        file.writeUTF(p.getLastName());
        file.writeUTF(p.getId());
        String date = p.getBirthDate().format(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
        file.writeUTF(date);
    }

    public void terminate() {
        clearFile();
        saveAllParticipantsToFile();
    }
}
