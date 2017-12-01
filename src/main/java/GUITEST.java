import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Application;

import java.util.stream.Collectors;

import static javafx.application.Application.launch;

public class GUITEST extends Application {
    private final ObservableList<Process> readyProcessList = FXCollections.observableArrayList();
    private final ObservableList<Process> waitingProcessList = FXCollections.observableArrayList();
    
    static protected TextArea textArea;

    OS os = new OS();
    private TableView readyTable;
    private TableView waitTable;
    private TableView jobsTable;
    private BorderPane layout;
    private Stage window;
    private HBox upperBox;
    private VBox lowerBox;

    /*public static void main(String[] args){
        launch(args);
    }*/
    @Override
    public void start(Stage stage) {
        window = stage;
        layout = new BorderPane();
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
        this.readyProcessList.setAll(Scheduler.getReadyQueue().stream().collect(Collectors.toList()));
        readyTable = new TableView();
        readyTable.setItems(this.readyProcessList);
        readyTable.getColumns().addAll(nameCol, sizeCol, arrivalCol, statusCol, prioCol);
        
        waitTable = new TableView();
        waitTable.setItems(this.readyProcessList);
        waitTable.getColumns().addAll(nameCol, sizeCol, arrivalCol, statusCol, prioCol);
        
        jobsTable = new TableView();
        jobsTable.setItems(this.readyProcessList);
        jobsTable.getColumns().addAll(nameCol, sizeCol);
        
        VBox jobsBox = new VBox();
        jobsBox.setSpacing(5);
        Text jobsTitle = new Text("Available Jobs");
        jobsTitle.setStyle("-fx-font-size: 18px");
        jobsBox.getChildren().addAll(jobsTitle, jobsTable);
        
        VBox waitBox = new VBox();
        waitBox.setSpacing(10);
        Text waitTitle = new Text("Wait Queue");
        waitTitle.setStyle("-fx-font-size: 18px");
        waitBox.getChildren().addAll(waitTitle, waitTable);
        
        VBox readyBox = new VBox();
        readyBox.setSpacing(10);
        Text readyTitle = new Text("Ready Queue");
        readyTitle.setStyle("-fx-font-size: 18px");
        readyBox.getChildren().addAll(readyTitle, readyTable);
        
        upperBox = new HBox();
        upperBox.setSpacing(10);
        upperBox.setPadding(new Insets(10, 10, 10, 10));
        upperBox.getChildren().addAll(jobsBox, waitBox, readyBox);
        
        
        
        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setFocusTraversable(false);
        textArea.setPrefRowCount(3);
        textArea.setPrefColumnCount(50);
        textArea.autosize();
        
        lowerBox = new VBox();
        lowerBox.setSpacing(10);
        lowerBox.setPadding(new Insets(10, 10, 10, 10));
        lowerBox.getChildren().addAll(textArea);
        
        layout.setTop(upperBox);
        layout.setBottom(lowerBox);
        
        Scene scene = new Scene(layout, 900, 600);
        window.setScene(scene);

        window.show();

    }
}
