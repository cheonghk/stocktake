package stocktake;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ModelTable {
    private StringProperty FILE_NO = new SimpleStringProperty();
    private StringProperty FILE_STATUS = new SimpleStringProperty();
    private StringProperty CLIENT_CODE = new SimpleStringProperty();
    private StringProperty CLIENT_NAME = new SimpleStringProperty();
    private StringProperty FILE_TYPE = new SimpleStringProperty();
    private StringProperty FILING_ROOM = new SimpleStringProperty();
    private StringProperty LOCATION_NO= new SimpleStringProperty();
    private StringProperty YEAR = new SimpleStringProperty();
    private StringProperty MONTH = new SimpleStringProperty();
    private StringProperty SEQ = new SimpleStringProperty();
    private StringProperty LAST_LOC_NO = new SimpleStringProperty();
    private StringProperty DEPT= new SimpleStringProperty();

    
    public ModelTable(String fileNo, String fileStatus, String clientCode, String clientName, 
            String fileType, String filingRoom, String LOCATION_NO, String year, String month, String seq, 
            String LAST_LOC_NO, String DEPT){
        this.FILE_NO= new SimpleStringProperty(fileNo);
        this.FILE_STATUS= new SimpleStringProperty(fileStatus);
        this.CLIENT_CODE= new SimpleStringProperty(clientCode);
        this.CLIENT_NAME= new SimpleStringProperty(clientName);
        this.FILE_TYPE= new SimpleStringProperty(fileType);
        this.FILING_ROOM= new SimpleStringProperty(filingRoom);
        this.LOCATION_NO= new SimpleStringProperty(LOCATION_NO);
        this.YEAR= new SimpleStringProperty(year);
        this.MONTH= new SimpleStringProperty(month);
        this.SEQ= new SimpleStringProperty(seq);
        this.LAST_LOC_NO= new SimpleStringProperty(LAST_LOC_NO);
        this.DEPT= new SimpleStringProperty(DEPT);
    }
    
    public String getFILE_NO(){
        return FILE_NO.get();     
    }
    public String getFILE_STATUS(){
        return FILE_STATUS.get();        
    }   
    public String getCLIENT_CODE(){
        return CLIENT_CODE.get();    
    }
    public String getCLIENT_NAME(){        
        return CLIENT_NAME.get();    
    }
    public String getFILE_TYPE(){
        return FILE_TYPE.get();    
    }
    public String getFILING_ROOM(){
        return FILING_ROOM.get();    

    }
    public String getLOCATION_NO(){
        return LOCATION_NO.get();    
    }
    public String getYEAR(){
        return YEAR.get();    
    }
    public String getMONTH(){
        return MONTH.get();    
    }
    public String getSEQ(){
        return SEQ.get();    
    }
    public String getLAST_LOC_NO(){
        return LAST_LOC_NO.get();    
    }
   public String getDEPT(){
        return DEPT.get();    
    }
}