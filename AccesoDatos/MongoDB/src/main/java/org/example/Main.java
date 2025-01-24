package org.example;

import com.mongodb.client.*;
import com.mongodb.client.model.*;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Scanner;
import java.util.regex.Matcher;

import static java.util.Arrays.asList;

public class Main {
    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase db = mongoClient.getDatabase("Disney");
        MongoDatabase studentsDB = mongoClient.getDatabase("estudiantes");
        MongoDatabase barriosDB = mongoClient.getDatabase("Barrios");
        MongoCollection<Document> disney = db.getCollection("Disney");
        MongoCollection<Document> students = studentsDB.getCollection("students");
        MongoCollection<Document> barrios = barriosDB.getCollection("barrios");

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMenu de Consultas a la Base de Datos ");
            System.out.println("-------------------Disney---------------------------:");
            System.out.println("1. Películas estrenadas antes de 1950");
            System.out.println("2. Películas con duración mayor a 90 minutos");
            System.out.println("3. Películas con puntuación IMDb superior a 9");
            System.out.println("4. Películas en inglés o español");
            System.out.println("5. Películas producidas por 'Walt Disney' con puntuación superior a 7.5");
            System.out.println("6. Películas de la década de 2000 con metascore superior a 90");
            System.out.println("7. Películas sin presupuesto especificado");
            System.out.println("8. Películas distribuidas por 'Buena Vista Distribution' estrenadas después de 1996");
            System.out.println("9. Películas dirigidas por 'Walt Disney'");
            System.out.println("10. Películas donde el idioma incluye 'English' y 'Spanish'");
            System.out.println("11. Películas protagonizadas por Robin Williams o Tom Hanks");
            System.out.println("12. Películas donde todos los elementos en 'Music by' empiezan por 'l' o terminan por 'h'");
            System.out.println();
            System.out.println("-------------------Estudiantes---------------------------:");
            System.out.println("13. Estudiantes que han sacado más de 75 en cualquier tipo de prueba");
            System.out.println("14. Estudiantes que han sacado menos de 5 en su examen");
            System.out.println("15. Estudiantes cuyo nombre empieza por 'A'");
            System.out.println("16. Borrar estudiantes que han sacado menos de 10 en el examen");
            System.out.println("17. Modificar estudiantes que no tengan el campo 'scores' y ponerles como nombre 'FANTASMA'");
            System.out.println("18. Añadir campo 'pierdeEvContinua' con valor 'true' a los estudiantes modificados");
            System.out.println("19. Cambiar el nombre del campo 'pierdeEvContinua' a 'tieneMatriculaDeHonor'");
            System.out.println("20. Modificar la nota de los deberes en 50 puntos para aquellos alumnos que han suspendido el examen");
            System.out.println("21. Estudiantes que han sacado más de 90 en el examen y menos de 50 en el quiz");
            System.out.println();
            System.out.println("-----------------------Barrios-------------------------------");
            System.out.println("22.Devuelve el máximo, mínimo y media total de la población.");
            System.out.println("23.Devuelve la población total por barrio.");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    peliculasEstrenadasAntesDe1950(disney);
                    break;
                case 2:
                    peliculasConDuracionMayorA90(disney);
                    break;
                case 3:
                    peliculasConPuntuacionImdbMayorA9(disney);
                    break;
                case 4:
                    peliculasEnInglesOEnEspanol(disney);
                    break;
                case 5:
                    peliculasProducidasPorWaltDisneyConPuntuacionMayorA75(disney);
                    break;
                case 6:
                    peliculasDeLos2000ConMetascoreMayorA90(disney);
                    break;
                case 7:
                    peliculasSinPresupuesto(disney);
                    break;
                case 8:
                    peliculasDistribuidasPorBuenaVista(disney);
                    break;
                case 9:
                    peliculasDirigidasPorWaltDisney(disney);
                    break;
                case 10:
                    peliculasConIdiomasEnglishYSpanish(disney);
                    break;
                case 11:
                    peliculasConRobinWilliamsOTomHanks(disney);
                    break;
                case 12:
                    peliculasConMusicaQueEmpiezaConL(disney);
                    break;
                case 13:
                    estudiantesConMasDe75(students);
                    break;
                case 14:
                    estudiantesConMenosDe5(students);
                    break;
                case 15:
                    estudiantesQueEmpiezanPorA(students);
                    break;
                case 16:
                    borrarEstudiantesConMenosDe10(students);
                    break;
                case 17:
                    modificarEstudiantesFantasma(students);
                    break;
                case 18:
                    modificarEstudiantesFantasmaConPierdeEvContinua(students);
                    break;
                case 19:
                    modificarEstudiantesFantasmaConTieneMatriculaDeHonor(students);
                    break;
                case 20:
                    modificarNotadeberes(students);
                    break;
                case 21:
                    estudiantes90examenyMenosde50quiz(students);
                    break;
                case 22:
                    poblacionTotal(barrios);
                    break;
                case 23:
                    poblacionPorBarrio(barrios);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);


        Bson agrupar = Aggregates.group("$Language", Accumulators.sum("num_Pelis", 1),
                Accumulators.max("max_nota","$imdb"));

        // for(Document p : lista)
        disney.aggregate(asList(agrupar)).forEach(p -> System.out.println(p.toJson()));


        scanner.close();
    }

    public static void peliculasEstrenadasAntesDe1950(MongoCollection<Document> disney) {
        FindIterable<Document> result = disney.find(Filters.lt("Release Date (datetime)", "1950-01-01"));
        for (Document doc : result) {
            System.out.println(doc.toJson());
        }
    }

    public static void peliculasConDuracionMayorA90(MongoCollection<Document> disney) {
        FindIterable<Document> result = disney.find(Filters.gt("Running time (int)", 90));
        for (Document doc : result) {
            System.out.println(doc.toJson());
        }
    }

    public static void peliculasConPuntuacionImdbMayorA9(MongoCollection<Document> disney) {
        FindIterable<Document> result = disney.find(Filters.gt("imdb", 8.0));
        for (Document doc : result) {
            System.out.println(doc.toJson());
        }
    }

    public static void peliculasEnInglesOEnEspanol(MongoCollection<Document> disney) {
        FindIterable<Document> result = disney.find(Filters.or(Filters.eq("Language", "English"), Filters.eq("Language", "Spanish")));
        for (Document doc : result) {
            System.out.println(doc.toJson());
        }
    }

    public static void peliculasProducidasPorWaltDisneyConPuntuacionMayorA75(MongoCollection<Document> disney) {
        FindIterable<Document> result = disney.find(Filters.and(Filters.eq("Produced by", "Walt Disney"), Filters.gt("imdb", 7.5))).sort(new Document("imdb", 1));
        for (Document doc : result) {
            System.out.println(doc.toJson());
        }
    }

    public static void peliculasDeLos2000ConMetascoreMayorA90(MongoCollection<Document> disney) {
        FindIterable<Document> result = disney.find(Filters.and(Filters.gte("Release Date (datetime)", "2000-01-01"), Filters.lt("Release Date (datetime)", "2010-01-01"), Filters.gt("metascore", 70)));
        for (Document doc : result) {
            System.out.println(doc.toJson());
        }
    }

    public static void peliculasSinPresupuesto(MongoCollection<Document> disney) {
        FindIterable<Document> result = disney.find(Filters.exists("Budget", false));
        for (Document doc : result) {
            System.out.println(doc.toJson());
        }
    }

    public static void peliculasDistribuidasPorBuenaVista(MongoCollection<Document> disney) {
        FindIterable<Document> result = disney.find(Filters.and(Filters.eq("Distributed by", "Buena Vista Distribution"), Filters.gt("Release Date (datetime)", "1996-01-01"))).sort(new Document("Release Date (datetime)", -1));
        for (Document doc : result) {
            System.out.println(doc.toJson());
        }
    }

    public static void peliculasDirigidasPorWaltDisney(MongoCollection<Document> disney) {
        FindIterable<Document> result = disney.find(Filters.regex("Directed by", "Walt Disney"));
        for (Document doc : result) {
            System.out.println(doc.toJson());
        }
    }

    public static void peliculasConIdiomasEnglishYSpanish(MongoCollection<Document> disney) {
        FindIterable<Document> result = disney.find(Filters.and(Filters.in("Language", "English", "Spanish")));
        for (Document doc : result) {
            System.out.println(doc.toJson());
        }
    }

    public static void peliculasConRobinWilliamsOTomHanks(MongoCollection<Document> disney) {
        FindIterable<Document> result = disney.find(Filters.in("Starring", asList("Robin Williams", "Tom Hanks")));
        for (Document doc : result) {
            System.out.println(doc.toJson());
        }
    }

    public static void peliculasConMusicaQueEmpiezaConL(MongoCollection<Document> disney) {
        FindIterable<Document> result = disney.find(Filters.all("Music by", Filters.or(Filters.regex("Music by", "^l"), Filters.regex("Music by", "h$"))));
        for (Document doc : result) {
            System.out.println(doc.toJson());
        }
    }

    //    Dado el data set de estudiantes:
//    1. Devuelve todos aquellos estudiantes que hayan sacado más de un 75 en cualquier tipo de prueba.
    public static void estudiantesConMasDe75(MongoCollection<Document> estudiantes) {
        FindIterable<Document> result = estudiantes.find(Filters.elemMatch("scores", Filters.gt("score", 75)));
        for (Document doc : result) {
            System.out.println(doc.toJson());
        }
    }

    //    2. Devuelve todos aquellos estudiantes que hayan sacado menos de un 5 en su examen. Usa elemMatch.

    public static void estudiantesConMenosDe5(MongoCollection<Document> estudiantes) {
        FindIterable<Document> result = estudiantes.find(Filters.elemMatch("scores", Filters.and(Filters.lt("score", 5)
                , Filters.eq("type", "exam"))));
        for (Document doc : result) {
            System.out.println(doc.toJson());
        }
    }

    //    3. Devuelve aquellos estudiantes que empiecen por A.
    public static void estudiantesQueEmpiezanPorA(MongoCollection<Document> estudiantes) {
        FindIterable<Document> result = estudiantes.find(Filters.regex("name", "^A"));
        for (Document doc : result) {
            System.out.println(doc.toJson());
        }
    }
//4. Borra aquellos alumnos que hayan sacado menos de un 10 en el examen.
    public static void borrarEstudiantesConMenosDe10(MongoCollection<Document> estudiantes) {
        estudiantes.deleteMany(Filters.elemMatch("scores", Filters.and(Filters.lt("score", 10)
                , Filters.eq("type", "exam"))));
    }
//5. Modifica aquellos alumnos que no tengan el campo scores, y ponle como nombre "FANTASMA".
    public static void modificarEstudiantesFantasma(MongoCollection <Document> estudiantes) {
       estudiantes.updateMany(Filters.exists("scores",false ), Updates.set("name", "FANTASMA"));
    }
//6. A este alumno que acabas de modificar, añadele un campo con nombre "pierdeEvContinua", con valor true.
    public static void modificarEstudiantesFantasmaConPierdeEvContinua(MongoCollection <Document> estudiantes) {
        estudiantes.updateMany(Filters.eq("name", "FANTASMA"),
                Updates.set("pierdeEvContinua", true));
    }
//7. Ay, me he equivocado!!! El campo no se llama "pierdeEvContinua", si no "tieneMatriculaDeHonor"… usa el operador rename.
    public static void modificarEstudiantesFantasmaConTieneMatriculaDeHonor(MongoCollection <Document> estudiantes) {
        estudiantes.updateMany(Filters.eq("name", "FANTASMA"),
                Updates.rename("pierdeEvContinua", "tieneMatriculaDeHonor"));
    }
//8. Usando el operador "inc" de la clase Updates, modifica la nota de los deberes en 50 puntos para aquellos alumnos que hayan suspendido el examen.
        public static void modificarNotadeberes(MongoCollection <Document> estudiantes){

        }
//9. Devuelve aquellos estudiantes que hayan sacado más de un 90 en el examen y menos de 50 en el quiz.
    public static void estudiantes90examenyMenosde50quiz (MongoCollection <Document> estudiantes){
        


}

//POJO
//    • Devuelve el máximo, mínimo y media total de la población.
    public static void poblacionTotal(MongoCollection <Document> barrios){
        Bson group =
                Aggregates.group(null,
                        Accumulators.min("min", "$TOTAL"),
                        Accumulators.avg("avg", "$TOTAL"),
                        Accumulators.max("max", "$TOTAL"));

        barrios.aggregate(asList(group)).forEach(document -> System.out.println(document.toJson()));
    }

//    • Devuelve la población total por barrio.
    public static void poblacionPorBarrio(MongoCollection <Document> barrios){
       Bson group = Aggregates.group("$DISTRITO",Accumulators.sum("poblacion_total","$TOTAL"));
       Bson select = Aggregates.project(Projections.fields(Projections.include("poblacion_total")));
        barrios.aggregate(asList(group,select)).forEach(document -> System.out.println(document.toJson()));
    }

//    • Devuelve los 5 barrios más pequeños por área. Quita el identificador y el
//    resto de valores, saca sólo el nombre del barrio y su área (para esto usa la etapa Project)
    // • Devuelve los 5 barrios más pequeños por área. Quita el identificador y el resto de
    // valores, saca sólo el nombre del barrio y su área (para esto usa la etapa Project)
    public static void barriosMasPequenosPorArea(MongoCollection <Document> barrios) {
        Bson select = Aggregates.project(Projections.fields(Projections.excludeId(), Projections.include("BARRIO", "AREA")));
        Bson sort = Aggregates.sort(Sorts.ascending("AREA"));
        Bson limit = Aggregates.limit(5);
        barrios.aggregate(asList(select, sort, limit)).forEach(document -> System.out.println(document.toJson()));
    }
    // • ¿Cuántos barrios hay por distrito? Usa sum.
    // $count -> { sum : 1} -> sum("numeroBarrios",1)
    public static void barriosPorDistrito(MongoCollection <Document> barrios) {
        Bson group =Aggregates.group("$DISTRITO"
                ,Accumulators.sum("poblacion_total",1)); // esto es igual a count()
        barrios.aggregate(asList(group)).forEach(document -> System.out.println(document.toJson()));
    }

    // • ¿Cuál es el barrio de Ofra donde vivan más mujeres? Haz esta consulta en dos pasos.
    // select max(mujeres) from tabla;
    public static void barrioOfraMasMujeres(MongoCollection <Document> barrios) {
        Bson match = Aggregates.match(Filters.eq("OFRA - COSTA SUR"));
        Bson group = Aggregates.group(null, Accumulators.max("maxMujeres", "$MUJERES"));
        barrios.aggregate(asList(match, group)).forEach(document -> System.out.println(document.toJson()));
    }

}


