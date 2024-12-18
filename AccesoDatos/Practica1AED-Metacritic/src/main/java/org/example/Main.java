package org.example;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.lang.reflect.GenericArrayType;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String csvFile = "src/ficheros/metacritic_games.csv";
        String binaryFile = "src/ficheros/juegos.dat";
        String xmlFile = "src/ficheros/juegos.xml";
        //Ejercicio1
       ArrayList<Juegos> Listajuegos = leerCsv(csvFile);
       pasaraBinario(Listajuegos, binaryFile);
//        //Ejercicio2
        ficheroAleatorio(binaryFile);
        //Ejercicio3
        List<Juegos> juegos = leerJuegosDesdeBinario(binaryFile);
        //Ejercicio3
        GenerarXML(juegos, xmlFile);
        //Ejercicio4
        GenerarCarpetas(xmlFile);
        //Ejercicio5
        GenerarCarpetasGenero(juegos);
        //Ejercicio6
        juegosMasDispares(juegos);
        //Ejercicio7
        buscarJuegosRatingM();

    }

    public static ArrayList<Juegos> leerCsv(String Filename) {
        ArrayList<Juegos> listaJuegos = new ArrayList<>();
        String linea;

        try (BufferedReader br = new BufferedReader(new FileReader(Filename))) {
            linea = br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");

                Juegos juego = new Juegos(
                        datos[0],
                        datos[1],
                        datos[2],
                        datos[3],
                        datos[4],
                        datos[5],
                        datos[6],
                        Integer.parseInt(datos[7]),
                        Integer.parseInt(datos[8]),
                        Integer.parseInt(datos[9]),
                        Integer.parseInt(datos[10]),
                        Integer.parseInt(datos[11]),
                        Integer.parseInt(datos[12]),
                        Double.parseDouble(datos[13]),
                        Double.parseDouble(datos[14])

                );
                listaJuegos.add(juego);
                System.out.println(juego);
            }
            return listaJuegos;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void pasaraBinario(ArrayList<Juegos> listaJuegos, String fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(listaJuegos);
            System.out.println(listaJuegos.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //    A partir del fichero juegos.dat generado anteriormente, se pide que generes un
//    fichero de acceso aleatorio, llamado juegosOrdenado.dat, los cuales se ordenen
//    por fecha. En este caso, por un límite de 15 caracteres a los strings.
//    Para ordenar el fichero, es obligatorio usar la interfaz Comparable.
    public static void ficheroAleatorio(String filename) {
        List<Juegos> juegos = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            juegos = (ArrayList<Juegos>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("Fichero " + filename + " no encontrado");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        Collections.sort(juegos);
        try (RandomAccessFile raf = new RandomAccessFile("src/ficheros/juegosOrdenados.dat", "rw");) {
            for (Juegos juego : juegos) {
                raf.writeBytes(juego.toString() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

//        3.	A partir del fichero juegos.dat, generado automáticamente,
//        se pide que generes un fichero xml en el que se almacenen los
//        juegos por las plataformas. Un ejemplo de este xml tiene que ser

    public static List<Juegos> leerJuegosDesdeBinario(String filename) {
        List<Juegos> listaJuegos = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            listaJuegos = (List<Juegos>) ois.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return listaJuegos;
    }

    private static String limpiarNombrePlataforma(String nombre) {
        // Reemplaza espacios y caracteres no válidos por guiones bajos
        String limpio = nombre.replaceAll("[^a-zA-Z0-9]", "_");

        // Asegúrate de que no empiece con un número
        if (!limpio.isEmpty() && Character.isDigit(limpio.charAt(0))) {
            limpio = "_" + limpio; // Añade un guión bajo al inicio si comienza con un número
        }

        return limpio;
    }

    public static void GenerarXML(List<Juegos> juegos, String filename) {

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            DOMImplementation dom = dBuilder.getDOMImplementation();

            Document doc = dom.createDocument(null, "Plataformas", null);
            // Elemento raíz
            Element raiz = doc.getDocumentElement();

            Element nodoPlataforma = null, nodoJuegos = null, nodoJuego = null, nodoNombre = null, nodoDesarrollador = null;
            Text texto = null;
            String plataformaAnterior = "";
            Map<String, Element> plataformasMap = new HashMap<>(); // Mapa para almacenar nodos de plataformas

            for (Juegos Juegos : juegos) {
                String plataforma = limpiarNombrePlataforma(Juegos.getPlatform());
                System.out.println("Nombre de plataforma procesado: " + plataforma);

                // Validar el nombre de la plataforma
                if (plataforma.isEmpty() || !plataforma.matches("[a-zA-Z_][a-zA-Z0-9_]*")) {
                    System.err.println("Nombre de plataforma inválido: " + plataforma);
                    continue; // Salta este juego si el nombre es inválido
                }

                // Si la plataforma no existe en el mapa, la creamos
                nodoPlataforma = plataformasMap.get(plataforma);
                if (nodoPlataforma == null) {
                    nodoPlataforma = doc.createElement(plataforma);
                    plataformasMap.put(plataforma, nodoPlataforma);
                    raiz.appendChild(nodoPlataforma);

                    // Agregamos el nodo <juegos> al nuevo nodo de plataforma
                    nodoJuegos = doc.createElement("juegos");
                    nodoPlataforma.appendChild(nodoJuegos);
                }

                //añadimos juego a juegos
                nodoJuego = doc.createElement("juego");
                nodoJuegos = (Element) nodoPlataforma.getElementsByTagName("juegos").item(0); // Obtenemos el nodo <juegos> correspondiente
                nodoJuegos.appendChild(nodoJuego);

                //añadimos nombre a juego y añadimos texto
                nodoNombre = doc.createElement("nombre");
                nodoJuego.appendChild(nodoNombre);
                texto = doc.createTextNode(Juegos.getGame());
                nodoNombre.appendChild(texto);

                nodoDesarrollador = doc.createElement("desarrollador");
                nodoJuego.appendChild(nodoDesarrollador);
                texto = doc.createTextNode(Juegos.getDeveloper());
                nodoDesarrollador.appendChild(texto);
            }
            Source source = new DOMSource(doc);
            Result resultado = new StreamResult(new File("src/ficheros/juegos.xml"));

            // Transformer funciona igual que Document, necesitamos una "f�brica" que cree
            // objetos de tipo Transformer, pero aqu� lo simplifico un poco m�s

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, resultado);

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

//    4.	A partir del fichero XML, se pide que generes una estructura de carpetas que contenga toda
//    la información escrita en el XML. Es decir, que tendremos que crear una carpeta llamada Plataformas,
//    y dentro de esta una carpeta por cada plataforma que se haya leído, y dentro de cada carpeta un fichero.txt
//    con la información de los juegos presentes. Es decir, dentro de la carptea PC, tiene que haber un fichero llamado PC.txt,
//    donde en cada línea se almacenará el nombre y el desarrollador de cada elemento juego. No se pueden crear las carpetas de forma manual,
//    ya que puede ser que se añadan consolas nuevas (la PS5) a medida que se actualice el fichero, por lo que la
//    creación de las carpetas debe de ser automática. Podéis usar el método getNodeName(), el cual devuelve el nombre de un elemento (la etiqueta).

    public static void GenerarCarpetas(String xmlFilePath) {
        try {
            // Cargar el documento XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(xmlFilePath));

            // Normalizar el documento
            document.getDocumentElement().normalize();

            // Crear la carpeta principal "Plataformas"
            Path plataformasDir = Paths.get("src/ficheros/Plataformas");
            if (!Files.exists(plataformasDir)) {
                Files.createDirectories(plataformasDir);
            }

            // Obtener todas las plataformas
            NodeList plataformas = document.getDocumentElement().getChildNodes();

            for (int i = 0; i < plataformas.getLength(); i++) {
                Node plataformaNode = plataformas.item(i);

                if (plataformaNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element plataforma = (Element) plataformaNode;
                    String nombrePlataforma = plataforma.getNodeName();

                    // Crear carpeta para cada plataforma
                    Path plataformaDir = plataformasDir.resolve(nombrePlataforma);
                    if (!Files.exists(plataformaDir)) {
                        Files.createDirectories(plataformaDir);
                    }

                    // Crear archivo .txt para cada plataforma
                    Path archivoTxt = plataformaDir.resolve(nombrePlataforma + ".txt");
                    try (BufferedWriter writer = Files.newBufferedWriter(archivoTxt)) {
                        NodeList juegos = plataforma.getElementsByTagName("juego");
                        for (int j = 0; j < juegos.getLength(); j++) {
                            Element juego = (Element) juegos.item(j);
                            String nombreJuego = juego.getElementsByTagName("nombre").item(0).getTextContent();
                            String desarrollador = juego.getElementsByTagName("desarrollador").item(0).getTextContent();

                            // Escribir el nombre y desarrollador en el archivo
                            writer.write(nombreJuego + " - " + desarrollador);
                            writer.newLine();
                        }
                    }
                }
            }

            System.out.println("Estructura de carpetas creada con éxito.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    5.	Al dueño de la tienda le interesa saber los juegos con alta nota para saber cuáles comprar para
//    intentar revender. A partir del fichero juegos.dat generado anteriormente, se pide que generes un sistema
//    de carpetas, en el que se cree una carpeta por género (Acción, Role- Playing…)
//    y dentro de cada una generes ficheros de texto con toda la información de los juegos de dicho
//    género que tengan una nota de metacritic de 8 o superior.
    public static void GenerarCarpetasGenero(List<Juegos> juegos) {
        // Crear la carpeta principal "Plataformas"
        Path GenerosDir = Paths.get("src/ficheros/Generos");
        try {
            //crear directorio generos
            if (!Files.exists(GenerosDir)) {
                Files.createDirectories(GenerosDir);
            }
            //saber cuantos generos hay
            for (Juegos juego : juegos) {
                //comprobar nota
                if (juego.getMetascore() >= 8) {
                    String Genero = sanitizeFileName(juego.getGenre());
                    Path GeneroDir = GenerosDir.resolve(Genero);

                    if (!Files.exists(GeneroDir)) {
                        Files.createDirectories(GeneroDir);
                    }
                    Path archivotxt = GeneroDir.resolve(Genero + ".txt");

                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivotxt.toFile(), true))) {
                        writer.write(juego.toString());
                        writer.newLine();
                    }

                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para sanitizar nombres de archivos y carpetas
    private static String sanitizeFileName(String name) {
        return name.replaceAll("[<>:\"/\\|?*]", "_"); // Reemplaza caracteres no válidos por _
    }

    //    6.	A partir del fichero juegos.dat generado anteriormente, se quiere mostrar por pantalla los juegos más dispares,
//    es decir, aquellos juegos que tengan una mayor diferencia entre las notas de los críticos y las notas de los usuarios.Se quiere mostrar la
//    información de aquel juego que tenga mucha más nota de los críticos que de usuarios, y aquel juego que tenga mucha más nota de los usuarios
//    que de los críticos.
    public static void juegosMasDispares(List<Juegos> juegos) {
        Juegos masCriticosSobreUsuarios = null;
        Juegos masUsuariosSobreCriticos = null;
        double maxDiferenciaCriticosSobreUsuarios = Double.NEGATIVE_INFINITY;
        double maxDiferenciaUsuariosSobreCriticos = Double.NEGATIVE_INFINITY;

        for (Juegos juego : juegos) {
            double diferenciaCriticosSobreUsuarios = juego.getMetascore() - juego.getUserScore();
            double diferenciaUsuariosSobreCriticos = juego.getUserScore() - juego.getMetascore();

            // Verificar si la diferencia es la mayor encontrada (críticos sobre usuarios)
            if (diferenciaCriticosSobreUsuarios > maxDiferenciaCriticosSobreUsuarios) {
                maxDiferenciaCriticosSobreUsuarios = diferenciaCriticosSobreUsuarios;
                masCriticosSobreUsuarios = juego;
            }

            // Verificar si la diferencia es la mayor encontrada (usuarios sobre críticos)
            if (diferenciaUsuariosSobreCriticos > maxDiferenciaUsuariosSobreCriticos) {
                maxDiferenciaUsuariosSobreCriticos = diferenciaUsuariosSobreCriticos;
                masUsuariosSobreCriticos = juego;
            }
        }

        // Mostrar resultados
        if (masCriticosSobreUsuarios != null) {
            System.out.println("Juego con más diferencia a favor de críticos:"+ maxDiferenciaCriticosSobreUsuarios);
            System.out.println(masCriticosSobreUsuarios);
        } else {
            System.out.println("No se encontraron juegos con mayor nota de críticos que de usuarios.");
        }

        if (masUsuariosSobreCriticos != null) {
            System.out.println("Juego con más diferencia a favor de usuarios:"+ maxDiferenciaUsuariosSobreCriticos);
            System.out.println(masUsuariosSobreCriticos);
        } else {
            System.out.println("No se encontraron juegos con mayor nota de usuarios que de críticos.");
        }
    }
//    7.	A partir del fichero aleatorio generado anteriormente, se tiene que permitir la búsqueda de aquellos juegos que sean para
//    mayores de 18 (o M, según ESRB). Primero se tiene que hacer una búsqueda que devuelva las posiciones en las que se encuentra un juego
//    con rating M, darle esa información al usuario por consola, y luego permitir que seleccione el juego a visualizar
public static void buscarJuegosRatingM() {
    try (RandomAccessFile raf = new RandomAccessFile("src/ficheros/juegosOrdenados.dat", "r")) {
        List<Long> posicionesM = new ArrayList<>();
        String linea;
        long posicion = raf.getFilePointer();

        // Buscar juegos con rating "M" y guardar sus posiciones
        while ((linea = raf.readLine()) != null) {
            if (linea.contains("rating='M'")) { // Suponiendo que la línea contiene "Rating: M" para juegos mayores de 18
                posicionesM.add(posicion);
            }
            posicion = raf.getFilePointer();
        }

        // Mostrar las posiciones encontradas
        if (posicionesM.isEmpty()) {
            System.out.println("No se encontraron juegos con rating M.");
            return;
        }

        System.out.println("Juegos con rating M encontrados en las siguientes posiciones:");
        for (int i = 0; i < posicionesM.size(); i++) {
            System.out.println((i + 1) + ". Posición: " + posicionesM.get(i));
        }

        // Permitir al usuario seleccionar un juego para visualizar
        Scanner scanner = new Scanner(System.in);
        System.out.print("Seleccione el número del juego que desea visualizar: ");
        int seleccion = scanner.nextInt();

        if (seleccion < 1 || seleccion > posicionesM.size()) {
            System.out.println("Selección no válida.");
            return;
        }

        // Mostrar la información del juego seleccionado
        long posicionSeleccionada = posicionesM.get(seleccion - 1);
        raf.seek(posicionSeleccionada);
        String juegoInfo = raf.readLine();
        System.out.println("Información del juego seleccionado:");
        System.out.println(juegoInfo);

    } catch (IOException e) {
        System.err.println("Error al leer el archivo: " + e.getMessage());
    }
}
}



