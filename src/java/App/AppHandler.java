package App;


import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import Utility.DBMYSQL.*;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LENOVO
 */
@Path("app/{url}")
public class AppHandler {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String GetApps(@PathParam("url")String APIName) {
        DBConnector dbc = DBConnector.GetConnection();
        return DummyResponse.getDummyResponse(APIName).getJSON();
        
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String CreateJson(@PathParam("url")String APIName, String json) {
        String response = "Fail";
        
        DBConnector dbc = DBConnector.GetConnection();
        
        DummyResponse dummyResponse = new DummyResponse(APIName,json);
        boolean isSuccess = dummyResponse.CreateDummyResponse();
        response = (isSuccess)?"Pass":dbc.getError();
        return "{\"status\":\""+response+"\""+"\""+APIName+"\""+"}";
    }
    
}
