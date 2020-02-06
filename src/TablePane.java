import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.time.LocalDate;
import java.util.Collections;

public class TablePane extends StackPane {

    private static final int COLUMN_WIDTH = 150;
    private static final String FILE_NAME = "Participants.txt";

    private static ObservableList<Participant> ol = FXCollections.observableArrayList(
//            new Participant("oded", "nir", "205985948", "18/09/1994")
    );
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
        return ol.remove(index) != null;
    }

    public boolean removeByName(String fName, String lName) {
        Participant temp = new Participant(fName, lName, "", "");
        int index = Collections.binarySearch(ol, temp, new fNameLNameComparator());
        return ol.remove(index) != null;
    }

    public boolean loadParticipantsFromFile() {
        return true;
    }

    public boolean addParticipantToFile(Participant p) {

        return true;
    }
}
