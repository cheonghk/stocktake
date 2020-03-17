package stocktake;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class data {
    private Connection connect = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    private String theFileNo = null;
    private String theStatus = null;
    private String theClientCode = null;   
    private String theClientName = null;
    private String theFileType = null;
    private String theFileRoom = null;
    private String theLocastion_No =null;
    private String theYear = null;
    private String theMonth = null;
    private String theSeq= null;
    private String theLastLocNo= null;
    private String theDept= null;
    String url = "jdbc:mysql://127.0.0.1:3306/stocktake";
    
    public void connectData(){
        try {
            Class.forName("com.mysql.jdbc.Driver");            
            connect = DriverManager.getConnection(url, "root", "1234");
        }
        catch(ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }catch(SQLException sqle){
            sqle.printStackTrace();
    }
    }
    
    public void selectFileNo(){  
        try {
           preparedStatement = connect.prepareStatement("select * from stocktake2 where File_no =?");
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
        }}       
    
    public ResultSet getFullDataConnection(){
     try{
           preparedStatement = connect.prepareStatement("select * from stocktake2");
            resultSet = preparedStatement.executeQuery();            
             }catch(SQLException sqle){
            sqle.printStackTrace();
        }return resultSet;
    }          

    public void getFullDataConnection2(){
        try{
            theFileNo = resultSet.getString("FILE_NO");   
            theStatus = resultSet.getString("FILE_STATUS");            
            theClientCode = resultSet.getString("CLIENT_CODE");
            theClientName = resultSet.getString("CLIENT_NAME");
            theFileType = resultSet.getString("FILE_TYPE");
            theFileRoom = resultSet.getString("FILING_ROOM");
            theLocastion_No = resultSet.getString("LOCATION_NO");
            theYear = resultSet.getString("YEAR");
            theMonth = resultSet.getString("MONTH");
            theSeq= resultSet.getString("SEQ");
            theLastLocNo=resultSet.getString("LAST_LOC_NO");
            theDept = resultSet.getString("DEPT");
            }
             catch(SQLException sqle){
            sqle.printStackTrace();}
        }//catch(SQLException sqle){
           // sqle.printStackTrace();

    
    public void readData(String fileNo) { 
        try{
            preparedStatement.setString(1, fileNo);
        resultSet = preparedStatement.executeQuery();         
        while(resultSet.next()){
            theFileNo = resultSet.getString("FILE_NO");   
            theStatus = resultSet.getString("FILE_STATUS");            
            theClientCode = resultSet.getString("CLIENT_CODE");
            theClientName = resultSet.getString("CLIENT_NAME");
            theFileType = resultSet.getString("FILE_TYPE");
            theFileRoom = resultSet.getString("FILING_ROOM");
            theLocastion_No = resultSet.getString("LOCATION_NO");
            theYear = resultSet.getString("YEAR");
            theMonth = resultSet.getString("MONTH");
            theSeq= resultSet.getString("SEQ");
            theLastLocNo=resultSet.getString("LAST_LOC_NO");
            theDept = resultSet.getString("DEPT");
            }        
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }      
    }
    
    public String getFileNo(){
        return theFileNo;
    }
    public String getStatus(){
        return theStatus;
    }
    public String getClientCode(){
        return theClientCode;
    }
    public String getClientName(){
        return theClientName;
    }
    public String getFileType(){
    return theFileType;
    }
    public String getFileRoom(){
    return theFileRoom;
    }
    public String getTheLocastion_No(){
    return theLocastion_No;
    }
    public String getYear(){
    return theYear;
    }
    public String getMonth(){
    return theMonth;
    }
    public String getSEQ(){
    return theSeq;
    }
    public String getTheLastLocNo(){
    return theLastLocNo;
    }
    public String getDEPT(){
    return theDept;
    }
    
    public void setAllNull(){
    theFileNo = theStatus = theClientCode = theClientName =theFileType =
    theFileRoom =  theLocastion_No = theYear = theMonth = 
    theSeq= theLastLocNo= theDept=null;
    }
}
            