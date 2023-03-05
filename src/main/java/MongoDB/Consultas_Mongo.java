/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MongoDB;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.mongodb.BasicDBList;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.lt;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.InsertOneResult;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
/**
 *
 * @author Admin
 */
public class Consultas_Mongo {
    public static void insertarMantenimiento(String id_hab, String redactar,String fecha, String cp){
        String uri = "mongodb://localhost:27017";  //conexión

    	// crear un cliente de conexión con MongoDB
    	MongoClient mongoClient = MongoClients.create(uri);

   	// Crear un objeto que se conecta a una base de datos en concreta
    	MongoDatabase database = mongoClient.getDatabase("Hotel");
        
        InsertOneResult result = database.getCollection("Mantenimiento").insertOne(new Document()
        .append("id_habitacion",id_hab)
        .append("redactar", redactar)
        .append("fecha",fecha)
        .append("cp", cp));
        
        mongoClient.close();
    }
    
    public static void verMantenimientos(String id_hab){
        String uri = "mongodb://localhost:27017";  //conexión
    	// crear un cliente de conexión con MongoDB
    	MongoClient mongoClient = MongoClients.create(uri);
   	// Crear un objeto que se conecta a una base de datos en concreta
    	MongoDatabase database = mongoClient.getDatabase("Hotel");
        
        MongoCollection<Document> coleccion =database.getCollection("Mantenimiento");
        Bson projectionFields=Projections.fields(
                    Projections.include("id_habitacion","redactar", "fecha"),
                    Projections.excludeId());
        
        MongoCursor<Document> cursor = coleccion.find(lt("id_habitacion", id_hab))
                    .projection(projectionFields)
                    .sort(Sorts.descending("id_habitacion")).iterator();
        
        while(cursor.hasNext()){
            System.out.println(cursor.next().toJson());
        }
        
        //BasicDBList list = mantenimientos;
        
        
        mongoClient.close();
    }
    
    
    public static void main(String[] args) {
        verMantenimientos("1");
        //insertarMantenimiento("3", "Sabanas cambiadas", " "+LocalDate.now());
        
    }
    
    
}
