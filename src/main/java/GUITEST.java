import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class GUITEST extends Application {
    private final ObservableList<Process> readyProcessList = FXCollections.observableArrayList();
    private final ObservableList<Process> waitingProcessList = FXCollections.observableArrayList();



    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setWidth(450);
        stage.setHeight(550);
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<Process, String>("Name"));
        TableColumn sizeCol = new TableColumn("Size");
        sizeCol.setCellValueFactory(new PropertyValueFactory<Process, String>("Size"));
        TableColumn arrivalCol = new TableColumn("Arrival");
        arrivalCol.setCellValueFactory(new PropertyValueFactory<Process, String>("Arrival"));
        TableColumn statusCol = new TableColumn("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<Process, String>("Status"));
        TableColumn prioCol = new TableColumn("Priority");
        prioCol.setCellValueFactory(new PropertyValueFactory<Process, String>("Priority"));


    }
}
