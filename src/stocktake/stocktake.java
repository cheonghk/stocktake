package stocktake;

import java.sql.ResultSet;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.util.Set;
import java.util.HashSet;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.stage.WindowEvent;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.scene.control.Alert;
import java.sql.SQLException;
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.OutputStream;
/*import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;*/

public class stocktake extends Application{    
    MenuBar menuBar = new MenuBar();
    Menu menuFile = new Menu("File");
    Menu menuStart = new Menu("Start");
    MenuItem menuItemExport = new MenuItem("Export table");
    MenuItem menuItemExportI = new MenuItem("Export tableI");
    MenuItem menuItemExportTab2 = new MenuItem("Export tableTab2");
    MenuItem menuItemStart = new MenuItem("Start");
    ObservableList<ModelTable> dataObject = FXCollections.observableArrayList();
    ObservableList<ModelTable> dataObjectI = FXCollections.observableArrayList();
    ObservableList<ModelTable> dataObjectTab2 = FXCollections.observableArrayList();
    TextField textFieldForFile = new TextField();
    Label labelFileNo = new Label("File no : ");
    data theFullData = new data(); 
    data theData = new data();
    
   // ExportFile ef = new ExportFile();
    //stocktakeUIcontrol UIcontrol = new stocktakeUIcontrol();    
    Label labelForNum2;
    Label labelForNumI2;
    Label labelForNumTab2 = new Label("   Total No. of files: ");
    Label remainLabel = new Label("   Remains :");
    Button removeButton = new Button("Remove duplicates");
    Button removeButton2 = new Button("Remove duplicates");
    Boolean isStart = false;
   
	public void start(Stage primaryStage) {
     isStart(false);
        menuBar.getMenus().add(menuFile);
        menuBar.getMenus().add(menuStart);
        /*menuFile.getItems().add(menuItemExport);
        menuFile.getItems().add(menuItemExportI);
        menuFile.getItems().add(menuItemExportTab2);*/
        menuStart.getItems().add(menuItemStart);  
        theData.connectData();
        theFullData.connectData();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        
     /*  menuItemExport.setOnAction(new EventHandler<ActionEvent>(){
    	   public void handle(ActionEvent e){
                 exportFile();
        }});
       menuItemExportI.setOnAction(new EventHandler<ActionEvent>(){
    	   public void handle(ActionEvent e){
               exportFileI();
        }});
       menuItemExportTab2.setOnAction(new EventHandler<ActionEvent>(){
    	   public void handle(ActionEvent e){
    		   exportFileTab2();
    		   }});       */
       Label labelForNum = new Label("Number: ");
       labelForNum2 = new Label(Integer.toString(dataObject.size()));
       Label labelForNumI = new Label("Number: ");
       labelForNumI2 = new Label(Integer.toString(dataObjectI.size()));
       textFieldForFile.setDisable(true);
       textFieldForFile.setMaxWidth(100);
       
       menuItemStart.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e) { 
            if(isStart==false){
            try{ 
                ResultSet rs = theFullData.getFullDataConnection();
                while(rs.next()){
                    theFullData.getFullDataConnection2();
                    dataObjectTab2.add(new ModelTable(theFullData.getFileNo(), theFullData.getStatus(),theFullData.getClientCode(),
                theFullData.getClientName(),theFullData.getFileType(), theFullData.getFileRoom(),theFullData.getTheLocastion_No(),
                theFullData.getYear(), theFullData.getMonth(),theFullData.getSEQ(), theFullData.getTheLastLocNo(),
                theFullData.getDEPT()));
                }
                
            }catch(SQLException sqle){
                sqle.printStackTrace();
            }
            labelForNumTab2.setText("   Total No. of files: "+Integer.toString(dataObjectTab2.size()));
            remainLabel.setText("   Remains :"+Integer.toString(dataObjectTab2.size()));
            isStart(true);
            isStart=true;
        }}
        });
        
        
        //Handling the list of table              
        textFieldForFile.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event){
                theData.selectFileNo();
                if(event.getCode().equals(javafx.scene.input.KeyCode.ENTER)) {
                    if(textFieldForFile.getText().matches("\\d{5,}")){
                        try{theData.readData(textFieldForFile.getText());                       
                        if(theData.getStatus().equals("A")){                          
                            dataObject.add(new ModelTable(theData.getFileNo(), theData.getStatus(),theData.getClientCode(),
                theData.getClientName(),theData.getFileType(), theData.getFileRoom(),theData.getTheLocastion_No(),
                theData.getYear(), theData.getMonth(),theData.getSEQ(), theData.getTheLastLocNo(),
                theData.getDEPT()));
                            dataObjectTab2.removeIf(reMoveFile ->reMoveFile.getFILE_NO().equals(textFieldForFile.getText()));         
                        }                       
                        else if (theData.getStatus().equals("B")){             
                            dataObjectI.add(new ModelTable(theData.getFileNo(), theData.getStatus(),theData.getClientCode(),
                                    theData.getClientName(),theData.getFileType(), theData.getFileRoom(),theData.getTheLocastion_No(),
                                    theData.getYear(), theData.getMonth(),theData.getSEQ(), theData.getTheLastLocNo(),
                                    theData.getDEPT()));
                        dataObjectTab2.removeIf(reMoveFile ->reMoveFile.getFILE_NO().equals(textFieldForFile.getText()));}
                        }
                        catch(Exception e){
                            dataObjectI.add(new ModelTable(textFieldForFile.getText(),"-","-","-","-","-","-","-","-","-","-","-"));}
                    }
                 theData.setAllNull();
                 textFieldForFile.clear();
                 labelForNum2.setText(Integer.toString(dataObject.size()));
                 labelForNumI2.setText(Integer.toString(dataObjectI.size()));
        }}});    

        //Creat table
        TableView <ModelTable>table = new TableView <ModelTable>(); 
        table.setMinHeight(100);
        table.setItems(dataObject);

        TableColumn column1 = new TableColumn("File No");
        column1.setSortable(false);
        column1.setPrefWidth(150);
        column1.setCellValueFactory(new PropertyValueFactory("FILE_NO"));
        TableColumn column2 = new TableColumn ("File Status");
        column2.setSortable(false);
        column2.setPrefWidth(150);
        column2.setCellValueFactory(new PropertyValueFactory("FILE_STATUS"));
        TableColumn column3 = new TableColumn ("Client Code");
        column3.setSortable(false);
        column3.setPrefWidth(150);
        column3.setCellValueFactory(new PropertyValueFactory("CLIENT_CODE"));
        TableColumn column4 = new TableColumn("Client Name");
        column4.setSortable(false);
        column4.setPrefWidth(150);
        column4.setCellValueFactory(new PropertyValueFactory("CLIENT_NAME"));
        TableColumn column5 = new TableColumn("File Type");
        column5.setSortable(false);
        column5.setPrefWidth(150);
        column5.setCellValueFactory(new PropertyValueFactory("FILE_TYPE"));
        TableColumn column6 = new TableColumn ("Filing Room");
        column6.setSortable(false);
        column6.setPrefWidth(150);
        column6.setCellValueFactory(new PropertyValueFactory("FILING_ROOM"));
        TableColumn column7 = new TableColumn("Location No");
        column7.setSortable(false);
        column7.setPrefWidth(150);
        column7.setCellValueFactory(new PropertyValueFactory("LOCATION_NO"));
        TableColumn column8 = new TableColumn("YEAR");
        column8.setSortable(false);
        column8.setPrefWidth(150);
        column8.setCellValueFactory(new PropertyValueFactory<>("YEAR"));
        TableColumn column9 = new TableColumn ("MONTH");
        column9.setSortable(false);
        column9.setPrefWidth(150);
        column9.setCellValueFactory(new PropertyValueFactory<>("MONTH"));
        TableColumn column10 = new TableColumn ("SEQ");
        column10.setSortable(false);
        column10.setPrefWidth(150);
        column10.setCellValueFactory(new PropertyValueFactory<>("SEQ"));
        TableColumn column11 = new TableColumn("Last Loc no");
        column11.setSortable(false);
        column11.setPrefWidth(150);
        column11.setCellValueFactory(new PropertyValueFactory("LAST_LOC_NO"));
        TableColumn column12 = new TableColumn("DEPARTMENT");
        column12.setSortable(false);
        column12.setPrefWidth(150);
        column12.setCellValueFactory(new PropertyValueFactory("DEPT"));        
       
        table.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7, column8
        , column9, column10, column11, column12);       
        
        //Creat table for innormal case
        TableView <ModelTable>tableI = new TableView<ModelTable>();
        tableI.setMinHeight(100);
        tableI.setItems(dataObjectI);
        
        TableColumn columnI1 = new TableColumn("File No");
        columnI1.setSortable(false);
        columnI1.setPrefWidth(150);
        columnI1.setCellValueFactory(new PropertyValueFactory("FILE_NO"));
        TableColumn columnI2 = new TableColumn ("File Status");
        columnI2.setSortable(false);
        columnI2.setPrefWidth(150);
        columnI2.setCellValueFactory(new PropertyValueFactory("FILE_STATUS"));
        TableColumn columnI3 = new TableColumn ("Client Code");
        columnI3.setSortable(false);
        columnI3.setPrefWidth(150);
        columnI3.setCellValueFactory(new PropertyValueFactory("CLIENT_CODE"));
        TableColumn columnI4 = new TableColumn("Client Name");
        columnI4.setSortable(false);
        columnI4.setPrefWidth(150);
        columnI4.setCellValueFactory(new PropertyValueFactory("CLIENT_NAME"));
        TableColumn columnI5 = new TableColumn("File Type");
        columnI5.setSortable(false);
        columnI5.setPrefWidth(150);
        columnI5.setCellValueFactory(new PropertyValueFactory("FILE_TYPE"));
        TableColumn columnI6 = new TableColumn ("Filing Room");
        columnI6.setSortable(false);
        columnI6.setPrefWidth(150);
        columnI6.setCellValueFactory(new PropertyValueFactory("FILING_ROOM"));
        TableColumn columnI7 = new TableColumn("Location No");
        columnI7.setSortable(false);
        columnI7.setPrefWidth(150);
        columnI7.setCellValueFactory(new PropertyValueFactory("LOCATION_NO"));
        TableColumn columnI8 = new TableColumn("YEAR");
        columnI8.setSortable(false);
        columnI8.setPrefWidth(150);
        columnI8.setCellValueFactory(new PropertyValueFactory<>("YEAR"));
        TableColumn columnI9 = new TableColumn ("MONTH");
        columnI9.setSortable(false);
        columnI9.setPrefWidth(150);
        columnI9.setCellValueFactory(new PropertyValueFactory<>("MONTH"));
        TableColumn columnI10 = new TableColumn ("SEQ");
        columnI10.setSortable(false);
        columnI10.setPrefWidth(150);
        columnI10.setCellValueFactory(new PropertyValueFactory<>("SEQ"));
        TableColumn columnI11 = new TableColumn("Last Loc no");
        columnI11.setSortable(false);
        columnI11.setPrefWidth(150);
        columnI11.setCellValueFactory(new PropertyValueFactory("LAST_LOC_NO"));
        TableColumn columnI12 = new TableColumn("DEPARTMENT");
        columnI12.setSortable(false);
        columnI12.setPrefWidth(150);
        columnI12.setCellValueFactory(new PropertyValueFactory("DEPT"));
      
        tableI.getColumns().addAll(columnI1, columnI2, columnI3, columnI4, columnI5, columnI6, columnI7, columnI8
        , columnI9, columnI10, columnI11, columnI12);        
        
        //Structurising-------
        //2 Tabs
        TabPane tabPane = new TabPane();
        tabPane.setTabMinWidth(30);
        Tab tab1 = new Tab("Scanned lists");
        tab1.setClosable(false);
        Tab tab2 = new Tab("Full list");
        tab2.setClosable(false);
        ScrollPane spTable = new ScrollPane();
        spTable.setContent(table);
        ScrollPane spTableI = new ScrollPane();
        spTableI.setContent(tableI);
        
        BorderPane bpOuterMost = new BorderPane();
        VBox vboxOutsideTab_Top = new VBox();
        vboxOutsideTab_Top.getChildren().addAll(menuBar, tabPane);
        bpOuterMost.setTop(vboxOutsideTab_Top);
        
        HBox hboxForFileInput = new HBox();
        hboxForFileInput.getChildren().addAll(labelFileNo, textFieldForFile);

        HBox hboxUnder = new HBox();
        HBox hboxUnder2 = new HBox();
        hboxUnder.getChildren().addAll(labelForNum, labelForNum2);
        hboxUnder2.getChildren().addAll(labelForNumI, labelForNumI2);       
        
        BorderPane bpUnder = new BorderPane();
        bpUnder.setLeft(hboxUnder);
        bpUnder.setRight(removeButton);
        BorderPane bpUnder2 = new BorderPane();
        bpUnder2.setLeft(hboxUnder2);
        bpUnder2.setRight(removeButton2);
        
        removeButton.setDisable(true);
        removeButton.setOnAction(event -> {removeDupOblist();});
        removeButton2.setDisable(true);
        removeButton2.setOnAction(event -> {removeDupOblistI();});
        
        VBox VBtableNbpUnder = new VBox();
        VBtableNbpUnder.getChildren().addAll(table, bpUnder);
        SplitPane splitPane = new SplitPane();       
        splitPane.getItems().addAll(VBtableNbpUnder, tableI);
        splitPane.setOrientation(Orientation.VERTICAL);
        table.setPrefHeight(screenHeight);//occupy a space        
        tableI.setMaxHeight(screenHeight-table.getMinHeight());
        VBtableNbpUnder.setMaxHeight(screenHeight-tableI.getMinHeight());//limit the height of table
        
        BorderPane borderPaneInsideTab1 = new BorderPane();        
        borderPaneInsideTab1.setTop(hboxForFileInput);
        borderPaneInsideTab1.setCenter(splitPane);
        borderPaneInsideTab1.setBottom(bpUnder2);
        borderPaneInsideTab1.setMaxHeight(screenHeight);
        tab1.setContent(borderPaneInsideTab1);        
        tabPane.getTabs().addAll(tab1, tab2);
        
        //===========================================================================
        //Tab2 element...
        BorderPane borderPaneInsideTab2 = new BorderPane();
        tab2.setContent(borderPaneInsideTab2);
              
        TableView <ModelTable>tableTab2 = new TableView <ModelTable>(); 
        tableTab2.setItems(dataObjectTab2);
        tableTab2.setPrefSize(200,450);

        TableColumn column1tab2 = new TableColumn("File No");
        column1tab2.setSortable(false);
        column1tab2.setPrefWidth(150);
        column1tab2.setCellValueFactory(new PropertyValueFactory("FILE_NO"));
        TableColumn column2tab2 = new TableColumn ("File Status");
        column2tab2.setSortable(false);
        column2tab2.setPrefWidth(150);
        column2tab2.setCellValueFactory(new PropertyValueFactory("FILE_STATUS"));
        TableColumn column3tab2 = new TableColumn ("Client Code");
        column3tab2.setSortable(false);
        column3tab2.setPrefWidth(150);
        column3tab2.setCellValueFactory(new PropertyValueFactory("CLIENT_CODE"));
        TableColumn column4tab2 = new TableColumn("Client Name");
        column4tab2.setSortable(false);
        column4tab2.setPrefWidth(150);
        column4tab2.setCellValueFactory(new PropertyValueFactory("CLIENT_NAME"));
        TableColumn column5tab2 = new TableColumn("File Type");
        column5tab2.setSortable(false);
        column5tab2.setPrefWidth(150);
        column5tab2.setCellValueFactory(new PropertyValueFactory("FILE_TYPE"));
        TableColumn column6tab2 = new TableColumn ("Filing Room");
        column6tab2.setSortable(false);
        column6tab2.setPrefWidth(150);
        column6tab2.setCellValueFactory(new PropertyValueFactory("FILING_ROOM"));
        TableColumn column7tab2 = new TableColumn("Location No");
        column7tab2.setSortable(false);
        column7tab2.setPrefWidth(150);
        column7tab2.setCellValueFactory(new PropertyValueFactory("LOCATION_NO"));
        TableColumn column8tab2 = new TableColumn("YEAR");
        column8tab2.setSortable(false);
        column8tab2.setPrefWidth(150);
        column8tab2.setCellValueFactory(new PropertyValueFactory<>("YEAR"));
        TableColumn column9tab2 = new TableColumn ("MONTH");
        column9tab2.setSortable(false);
        column9tab2.setPrefWidth(150);
        column9tab2.setCellValueFactory(new PropertyValueFactory<>("MONTH"));
        TableColumn column10tab2 = new TableColumn ("SEQ");
        column10tab2.setSortable(false);
        column10tab2.setPrefWidth(150);
        column10tab2.setCellValueFactory(new PropertyValueFactory<>("SEQ"));
        TableColumn column11tab2 = new TableColumn("LAST_LOC_NO");
        column11tab2.setSortable(false);
        column11tab2.setPrefWidth(150);
        column11tab2.setCellValueFactory(new PropertyValueFactory("LAST_LOC_NO"));
        TableColumn column12tab2 = new TableColumn("DEPT");
        column12tab2.setSortable(false);
        column12tab2.setPrefWidth(150);
        column12tab2.setCellValueFactory(new PropertyValueFactory("DEPT"));
        
        //ObservableList itemsTab2 = tableTab2.getItems();
        tableTab2.getColumns().addAll(column1tab2, column2tab2, column3tab2, column4tab2, column5tab2, column6tab2,
        column7tab2, column8tab2, column9tab2, column10tab2, column11tab2, column12tab2);   
          
        Button buttonTab2 = new Button("Update of data");
        GridPane gp = new GridPane();
        gp.add(buttonTab2, 0, 0);
        gp.add(labelForNumTab2, 3, 0);
        gp.add(remainLabel, 6, 0);
        
        ScrollPane spTableTab2 = new ScrollPane();
        spTableTab2.setContent(tableTab2);
        borderPaneInsideTab2.setCenter(tableTab2);
        borderPaneInsideTab2.setBottom(gp);      
        buttonTab2.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e) {            
        remainLabel.setText("   Remains : "+Integer.toString(dataObjectTab2.size()));
        }});
    
//=============================================================================================

        ScrollPane spOuterMost = new ScrollPane();
        spOuterMost.setContent(bpOuterMost);
        primaryStage.setTitle("Stocktake System");
        Scene scene = new Scene(spOuterMost, screenWidth/1.5, screenHeight/1.5);
        bpOuterMost.prefWidthProperty().bind(scene.widthProperty());

        primaryStage.setScene(scene);
        primaryStage.show();
        
        primaryStage.setOnCloseRequest((WindowEvent e)-> {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Please ensure the data are saved otherwise they will be lost!");
            Optional <ButtonType> result = a.showAndWait();
            if(result.get() == ButtonType.OK){
                primaryStage.close();
            }
            primaryStage.showAndWait();
        });
    }    
	//****************************The end of start******************************
	
		
    public void isStart(Boolean isReady){        
        if(true){textFieldForFile.setDisable(false);
        removeButton.setDisable(false);
        removeButton2.setDisable(false);
     }
        if(false){textFieldForFile.setDisable(true);
    }}
    
    public boolean isDataValid(String text){
    	try {
        theData.readData(text);        
        if(theData.getFileNo().equals(text)){
            return true;
        }}catch(Exception sqle) {
        return false;
    }return false;}    	
    
    public void removeDupOblist(){
        textFieldForFile.setDisable(true);
        Set<String> theSet = new HashSet<String>();
        for(ModelTable dataO: dataObject){
        theSet.add(dataO.getFILE_NO());}
         dataObject.clear();
         for(String setFileNo: theSet){
         theData.readData(setFileNo);
         dataObject.add(new ModelTable(theData.getFileNo(), theData.getStatus(),theData.getClientCode(),
                 theData.getClientName(),theData.getFileType(), theData.getFileRoom(),theData.getTheLocastion_No(),
                 theData.getYear(), theData.getMonth(),theData.getSEQ(), theData.getTheLastLocNo(),
                 theData.getDEPT()));}
         labelForNum2.setText(Integer.toString(dataObject.size()));
         textFieldForFile.setDisable(false);
         theData.setAllNull();
    }

    public void removeDupOblistI(){
    textFieldForFile.setDisable(true);
    Set<String> theSet = new HashSet<String>();
        for(ModelTable dataO: dataObjectI){
        theSet.add(dataO.getFILE_NO());}
        dataObjectI.clear();
        for(String setFileNo: theSet){
            if(isDataValid(setFileNo)){
             theData.readData(setFileNo);
             dataObjectI.add(new ModelTable(theData.getFileNo(), theData.getStatus(),theData.getClientCode(),
                     theData.getClientName(),theData.getFileType(), theData.getFileRoom(),theData.getTheLocastion_No(),
                     theData.getYear(), theData.getMonth(),theData.getSEQ(), theData.getTheLastLocNo(),
                     theData.getDEPT()));
         }else
         {  dataObjectI.add(new ModelTable(setFileNo,"-","-","-","-","-","-","-","-","-","-","-"));}
    }
        labelForNumI2.setText(Integer.toString(dataObjectI.size()));
        textFieldForFile.setDisable(false);
        theData.setAllNull();
    }    
 
    //======================================================================    
    
  
        
    public static void main(String args[])throws Exception{
        launch(args);
    }
}
