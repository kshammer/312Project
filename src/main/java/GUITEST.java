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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import static javafx.application.Application.launch;

public class GUITEST extends Application {
    
    public static final ObservableList<Process> allProcessList = FXCollections.observableArrayList();
    public static final ObservableList<Process> readyProcessList = FXCollections.observableArrayList();
    public static final ObservableList<Process> waitProcessList = FXCollections.observableArrayList();
    private final ObservableList<Process> waitingProcessList = FXCollections.observableArrayList();
    
    static protected TextArea textArea;
    final static String mem = "Memory";
    static OS os = new OS();
    private static TableView readyTable;
    private static TableView waitTable;
    private static TableView jobsTable;
    private BorderPane layout;
    private Stage window;
    private HBox inField;
    private HBox upField;
    private VBox lowField;
    private TextField input;
    public static BarChart<String,Number> bc;

    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage stage) {
        window = stage;
        layout = new BorderPane();
        stage.setWidth(900);
        stage.setHeight(900);
        
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis(0.0, 4100.0, 1.0);
        bc = new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Memory In Use");
        xAxis.setLabel("Used");       
        yAxis.setLabel("Memory");
        
        XYChart.Series series = new XYChart.Series();
        series.setName("Memory");
        series.getData().add(new XYChart.Data(mem, 4096 - os.MEM()));
        
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
        
        TableColumn nameCol2 = new TableColumn("Process");
        nameCol2.setCellValueFactory(new PropertyValueFactory<Process, String>("name"));
        TableColumn sizeCol2 = new TableColumn("Size");
        sizeCol2.setCellValueFactory(new PropertyValueFactory<Process, String>("size"));
        TableColumn arrivalCol2 = new TableColumn("Arrival Time");
        arrivalCol2.setCellValueFactory(new PropertyValueFactory<Process, String>("arrival"));
        TableColumn statusCol2 = new TableColumn("Status");
        statusCol2.setCellValueFactory(new PropertyValueFactory<Process, String>("status"));

        TableColumn nameCol3 = new TableColumn("Process");
        nameCol3.setCellValueFactory(new PropertyValueFactory<Process, String>("name"));
        TableColumn sizeCol3 = new TableColumn("Size");
        sizeCol3.setCellValueFactory(new PropertyValueFactory<Process, String>("size"));
        
        this.readyProcessList.setAll(Scheduler.getReadyQueue().stream().collect(Collectors.toList()));
        this.allProcessList.setAll(this.os.processes.stream().collect(Collectors.toList()));
        this.waitProcessList.setAll(Scheduler.getWaitQueue().stream().collect(Collectors.toList()));
        readyTable = new TableView();

        readyTable.setItems(this.readyProcessList);
        readyTable.getColumns().addAll(nameCol, sizeCol, arrivalCol, statusCol);


        waitTable = new TableView();
        waitTable.setItems(this.waitProcessList);
        waitTable.getColumns().addAll(nameCol2, sizeCol2, arrivalCol2, statusCol2);
        

        jobsTable = new TableView();
        jobsTable.setItems(this.allProcessList);
        jobsTable.getColumns().addAll(nameCol3, sizeCol3);
        
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
        
        bc.getData().addAll(series);
        upField = new HBox();
        upField.setSpacing(10);
        upField.setPadding(new Insets(10, 10, 10, 10));
        upField.getChildren().addAll(jobsBox, waitBox, readyBox, bc);
        
        
        
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
                                }
                            else if(i==3)
                                if(command.length < 2)
                                    os.EXE2();
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
                    input.clear();
                    update();
                }
            }
        });
        
        Scene scene = new Scene(layout, 900, 600);
        window.setScene(scene);

        window.show();



    }

    public static void update() {

        ObservableList<Process> readyUpdate = FXCollections.observableArrayList(os.scheduler.getReadyQueue());
        ObservableList<Process> waitUpdate = FXCollections.observableArrayList(os.scheduler.getWaitQueue());
        ObservableList<Process> allUpdate = FXCollections.observableArrayList(os.processes);

        readyProcessList.clear();
        readyProcessList.setAll(readyUpdate);
        waitProcessList.clear();
        waitProcessList.setAll(waitUpdate);
        allProcessList.clear();
        allProcessList.setAll(allUpdate);
        System.out.println(readyUpdate.get(0).getName());
        System.out.println(readyProcessList.get(0).getName());
        readyTable.setItems(readyProcessList);
        waitTable.setItems(waitProcessList);
        jobsTable.setItems(allProcessList);
        
        
        bc.getData().clear();
        XYChart.Series series = new XYChart.Series();
        series.setName("Memory");
        series.getData().add(new XYChart.Data(mem, 4096 - os.MEM()));
        
        bc.getData().addAll(series);
        

    }
    
    public static void output(String message)
    {
        textArea.appendText(message + "\n");
    }
}
