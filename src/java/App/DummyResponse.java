/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App;

import Utility.DBMYSQL.*;
import java.sql.*;
/**
 *
 * @author LENOVO
 */
public class DummyResponse {

    public DummyResponse(String json) {
        this.APIName = "default-apiname";
        this.JSON = "default-json";
    }
    
    public DummyResponse(String APIName, String JSON) {
        this.APIName = APIName;
        this.JSON = JSON;
    }
    
    
    public static DummyResponse getDummyResponse(String APIName) {
        String query = "select * from dummyresponses where APIName = \'"+APIName+"\'";
        DummyResponse dummyResponse = null; 
        DBConnector dbc = DBConnector.GetConnection(); 
        ResultSet rs = dbc.QueryExecRS(query);
        try {
            while(rs.next()) {
                if(dummyResponse == null) {
                    dummyResponse = new DummyResponse(APIName,"");
                }
                dummyResponse.skey = rs.getInt("skey");
                dummyResponse.APIName = rs.getString("APIName");
                dummyResponse.JSON = rs.getString("JSON");
            }
        }
        catch(Exception e) {
            System.out.println("getDummyResponse : " + e.toString());
        }
        return dummyResponse;
    }
    
    public static boolean CreateDummyResponse(String APIName, String JSON) {
        JSON = jsonFormatter(JSON);
        String insertQuery = "INSERT INTO dummyresponses(APIName,JSON) VALUES ('"+APIName+"','"+JSON+"')";
        DBConnector dbc = DBConnector.GetConnection(); 
        int rowsAffected = dbc.QueryExecUpd(insertQuery);
        return (rowsAffected > 0);
    }
    
    public boolean CreateDummyResponse() {
        JSON = jsonFormatter(JSON);
        String insertQuery = "INSERT INTO dummyresponses(APIName,JSON) VALUES ('"+APIName+"','"+JSON+"')";
        DBConnector dbc = DBConnector.GetConnection(); 
        int rowsAffected = dbc.QueryExecUpd(insertQuery);
        return (rowsAffected > 0);
    }

    public int getSkey() {
        return skey;
    }

    public String getAPIName() {
        return APIName;
    }

    public String getJSON() {
        return JSON;
    }

    public void setSkey(int skey) {
        this.skey = skey;
    }

    public void setAPIName(String APIName) {
        this.APIName = APIName;
    }

    public void setJSON(String JSON) {
        this.JSON = JSON;
    }
    
    static String jsonFormatter(String json){
        for(int i = 0 ; i < json.length(); ++i) {
            if(json.charAt(i) == '\'') {
                json = json.substring(0, i + 1) + "\'" + json.substring(i + 1);
            }
        }
        return json;
    }
    
    int skey;
    String APIName;
    String JSON;
}
