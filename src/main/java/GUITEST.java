import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import static javafx.application.Application.launch;

public class GUITEST extends Application {
    public final ObservableList<Process> allProcessList = FXCollections.observableArrayList();
    public final ObservableList<Process> readyProcessList = FXCollections.observableArrayList();
    public final ObservableList<Process> waitProcessList = FXCollections.observableArrayList();
    private final ObservableList<Process> waitingProcessList = FXCollections.observableArrayList();
    
    static protected TextArea textArea;

    static OS os = new OS();
    private TableView readyTable;
    private TableView waitTable;
    private TableView jobsTable;
    private BorderPane layout;
    private Stage window;
    private HBox inField;
    private HBox upField;
    private VBox lowField;
    private TextField input;

    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage stage) {
        window = stage;
        layout = new BorderPane();
        stage.setWidth(450);
        stage.setHeight(550);
        TableView<Process> processTable = new TableView<>();
        ObservableList<Process> processList = FXCollections.observableArrayList();
        processTable.setItems(processList);
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<Process, String>("name"));
        TableColumn sizeCol = new TableColumn("Size");
        sizeCol.setCellValueFactory(new PropertyValueFactory<Process, String>("size"));
        TableColumn arrivalCol = new TableColumn("Arrival");
        arrivalCol.setCellValueFactory(new PropertyValueFactory<Process, String>("arrival"));
        TableColumn statusCol = new TableColumn("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<Process, String>("state"));
        this.readyProcessList.setAll(Scheduler.getReadyQueue().stream().collect(Collectors.toList()));
        this.allProcessList.setAll(this.os.processes.stream().collect(Collectors.toList()));
        this.waitProcessList.setAll(Scheduler.getWaitQueue().stream().collect(Collectors.toList()));
        readyTable = new TableView();

        readyTable.setItems(this.readyProcessList);
        readyTable.getColumns().addAll(nameCol, sizeCol, arrivalCol, statusCol);


        waitTable = new TableView();
        waitTable.setItems(this.waitProcessList);
        waitTable.getColumns().addAll(nameCol, sizeCol, arrivalCol, statusCol);
        

        jobsTable = new TableView();
        jobsTable.setItems(this.allProcessList);
        jobsTable.getColumns().addAll(nameCol, sizeCol, arrivalCol, statusCol);
        
        input = new TextField();
        
        
        VBox jobsBox = new VBox();
        jobsBox.setSpacing(10);
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
        
        inField = new HBox();
        inField.setSpacing(10);
        inField.getChildren().addAll(input);
        
        upField = new HBox();
        upField.setSpacing(10);
        upField.setPadding(new Insets(10, 10, 10, 10));
        upField.getChildren().addAll(jobsBox, waitBox, readyBox);
        
        
        
        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setFocusTraversable(false);
        textArea.setPrefRowCount(3);
        textArea.setPrefColumnCount(50);
        textArea.autosize();
        
        lowField = new VBox();
        lowField.setSpacing(10);
        lowField.setPadding(new Insets(10, 10, 10, 10));
        lowField.getChildren().addAll(textArea, inField);
        
        layout.setTop(upField);
        layout.setBottom(lowField);
        
        String[] validCommands = new String[6];
        validCommands[0] = "PROC";
        validCommands[1] = "MEM";
        validCommands[2] = "LOAD";
        validCommands[3] = "EXE";
        validCommands[4] = "RESET";
        validCommands[5] = "EXIT";
        
        input.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    boolean valid = false;
                    String[] command = input.getText().split(" ");
                    for(int i = 0; i < validCommands.length; i++)
                    {
                        if(command[0].equalsIgnoreCase(validCommands[i]))
                        {
                            valid = true;
                            if(i == 0)
                                textArea.appendText(os.PROC() + "\n");
                            else if(i == 1)
                                textArea.appendText(os.MEM() + "\n");
                            else if(i == 2)
                                if(command.length < 2)
                                    textArea.appendText("Invalid Load\n");
                                else
                                {
                                    
                                    os.LOAD(command[1]);
                                    update();
                                    textArea.appendText(Scheduler.getReadyQueue().toString());
                                }
                            else if(i==3)
                                if(command.length < 2)
                                    textArea.appendText("Invalid Exe\n");
                                else
                                    os.EXE(Integer.valueOf(command[1]));
                            else if(i==4)
                                os.RESET();
                            else if(i == 5)
                                os.EXIT();
                        }
                    }
                    if(!valid)
                        textArea.appendText("Error: Invalid command\n");
                }
            }
        });
        
        Scene scene = new Scene(layout, 900, 600);
        window.setScene(scene);

        window.show();



    }

    public void update() {

        ObservableList<Process> readyUpdate = FXCollections.observableArrayList(os.scheduler.getReadyQueue());
        ObservableList<Process> waitUpdate = FXCollections.observableArrayList(os.scheduler.getWaitQueue());

        readyProcessList.clear();
        readyProcessList.setAll(readyUpdate);
        waitProcessList.clear();
        waitProcessList.setAll(waitUpdate);
        System.out.println(readyUpdate.get(0).getName());
        System.out.println(readyProcessList.get(0).getName());
        readyTable.setItems(readyProcessList);
        waitTable.setItems(waitProcessList);


    }
}
