import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ButtonPane extends HBox{

    private Button addBut;
    private Button removeBut;
    private Button clearBut;

    public ButtonPane() {
        int minWidth = 100;
        int minHeight = 50;
        addBut = new Button("ADD");
        addBut.setMinSize(minWidth, minHeight);
        removeBut = new Button("REMOVE");
        removeBut.setMinSize(minWidth, minHeight);
        clearBut = new Button("CLEAR");
        clearBut.setMinSize(minWidth, minHeight);
        this.getChildren().addAll(addBut, removeBut, clearBut);
        setDimensions();
    }

    private void setDimensions() {
        this.setSpacing(20);
        this.setPadding(new Insets(25, 25, 25, 25));
        this.setAlignment(Pos.CENTER);
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
}
