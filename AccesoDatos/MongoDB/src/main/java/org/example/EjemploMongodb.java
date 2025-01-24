package org.example;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import javax.print.Doc;
import java.util.ArrayList;

public class EjemploMongodb {
    public static void main(String[] args) {
        //nos conectamos a mongoDB
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        //Nos conectamos a la base de datos DAM
        MongoDatabase db = mongoClient.getDatabase("DAM");

        MongoCollection<Document> fiestas = db.getCollection("fiestasNo");

        //Creamos un documento
        Document fiesta = new Document();
        fiesta.append("nombre", "Fiesta de la primavera"); // {nombre : "Fiesta....}
        fiesta.append("DJ", "Kiko Rivera");
        fiesta.append("precio", 2000);
        fiesta.append("fecha", "2022-05-20");
        fiesta.append("aforomaximo", 2);

        Document lugar = new Document();
        lugar.append("nombre", "ofra sity");
        lugar.append("ciudad", "santa cruz");
        lugar.append("direccion", "calle la paz");
        lugar.append("okupao", true);
        //1-1 (1-N)
        fiesta.append("lugarFiesta", lugar);

        // N - M
        Document persona1 = new Document();
        persona1.append("nombre", "pepe");
        Document persona2 = new Document();
        persona2.append("nombre", "juan");

        ArrayList<Document> listaPersonas = new ArrayList<>();
        listaPersonas.add(persona1);
        listaPersonas.add(persona2);

        fiesta.append("asistentes", listaPersonas);

        fiestas.insertOne(fiesta);

        //Operacion Clave Condicion
        FindIterable<Document> result = fiestas.find(Filters.and(
                Filters.lt("precio",2000),
                Filters.eq("nombre","Fiesta de la primavera")
                ));

        for (Document doc : result) {
            System.out.println(doc.toJson());
        }

        //operacion con LIKE
        FindIterable<Document> result2 = fiestas.find(Filters.and(
                Filters.regex("DJ","%a"),
                Filters.regex("precio","^2")));
        for (Document doc : result2) {
            System.out.println(doc.toJson());
        }



        mongoClient.close();
    }
}
