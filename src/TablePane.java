import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

public class TablePane extends StackPane {

    private static final int COLUMN_WIDTH = 150;
    private static final String FILE_NAME = "Participants.txt";
    TableView<Participant> tableView;
    private static ObservableList<Participant> ol = FXCollections.observableArrayList();

    public TablePane(ResearchViewPane researchViewPane) {
        tableView = new TableView<>();
        tableView.setMinWidth(600);
        createAllColumns();
        tableView.setItems(ol);
        this.getChildren().add(tableView);
        rowClick(researchViewPane);
    }

    public void createAllColumns() {
        createColumn(tableView,"First Name", "firstName");
        createColumn(tableView, "Last Name", "lastName");
        createColumn(tableView, "ID", "id");
        createColumn(tableView, "Date Of Birth", "dateOfBirth");
        createColumn(tableView, "Gender", "gender");
    }

    public <T,K> void createColumn(TableView<T> tableView, String text, String attributeName) {
        TableColumn<T,K> column = new TableColumn<>(text);
        column.setMinWidth(COLUMN_WIDTH);
        column.setCellValueFactory(new PropertyValueFactory<>(attributeName));
        tableView.getColumns().add(column);
    }

    public boolean removeById(String id) {
        int index = binarySearchById(id);
        if (index < 0) return false;
        if (ol.remove(index) != null) {
            clearFile();
            saveAllParticipantsToFile();
            return true;
        }
        return false;
    }

    public int binarySearchById(String id) {
        return Collections.binarySearch(ol, new Participant("", "", id, "", "", "", ""), new idComparator());
    }

    public boolean removeByName(String fName, String lName) {
        int index = binarySearchByName(fName, lName);
        if (index < 0) return false;
        if (ol.remove(index) != null) {
            clearFile();
            saveAllParticipantsToFile();
        }
        return false;
    }

    public int binarySearchByName(String fName, String lName) {
        ol.sort(new fNameLNameComparator());
        int left = 0, right = ol.size() - 1, mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            int fNameValue = fName.compareTo(ol.get(mid).getFirstName());
            int lastNameValue = lName.compareTo(ol.get(mid).getLastName());
            if (fNameValue == 0 && lastNameValue == 0) {
                return mid;
            } else if (fNameValue < 0 || (fNameValue == 0 && lastNameValue < 0)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -mid - 1;
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
        String gender = file.readUTF();
        String rName = file.readUTF();
        String rInfo = file.readUTF();
        return new Participant(fName, lName, id, date, gender, rName, rInfo);
    }

    public boolean addParticipant(Participant p) {
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rw")) {
            file.seek(file.length());
            if (binarySearchByName(p.getFirstName(), p.getLastName()) < 0 && binarySearchById(p.getId()) < 0) { // Will not add if already exists
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
        file.writeUTF(p.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")));
        file.writeUTF(p.getGender());
        file.writeUTF(p.getResearchName());
        file.writeUTF(p.getResearchInfo());
    }

    public void rowClick(ResearchViewPane researchViewPane) {
        tableView.setRowFactory(tv -> {
            TableRow<Participant> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (row.getItem() == null) {
                    researchViewPane.getResearchNameField().clear();
                    researchViewPane.getResearchInfo().clear();
                } else {
                    researchViewPane.getResearchNameField().setText(row.getItem().getResearchName());
                    researchViewPane.getResearchInfo().setText(row.getItem().getResearchInfo());
                }
            });
            return row;
        });
    }

    public void terminate() {
        clearFile();
        saveAllParticipantsToFile();
    }
}
