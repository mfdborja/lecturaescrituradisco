package operardisco;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase que permite leer y escribir de una sola vez
 * todo un archivo de texto o cualquier objeto que tenga
 * implementada "Serializable".
 * Para ello, la clase que queramos guardar debe declararse asi:
 * class Nombre implements Serializable{.....
 * 
 * @author borjaro2000.tk - Borja Rodríguez
 * @version 1.0 - 20/06/2012
 */
public class OperacionesDisco<T> implements Serializable {
    //Con el template T permitimos que T sea cualquier tipo de dato. T podrá ser cualquier cosa.    
    
    /**
     * Objeto que representará lo que queremos guardar
     * o leer independientemente de su tipo.
     */
    T objeto;

    /**
     * Genera un objeto OperacionesDisco vacio.
     */
    public OperacionesDisco() {        
        objeto = null;
    }

    /**
     * Genera un objeto OperacionesDisco con el objeto que queremos guardar dentro.
     * @param objeto El objeto que queremos guardar en disco.
     */
    public OperacionesDisco(T objeto) {
        this.objeto = objeto;
    }

    /**
     * Asigna un objeto y desecha el anterior que tuviera asociado si lo hubiera.
     * @param objeto El objeto que queremos guardar en disco.
     */
    public void setObjeto(T objeto) {
        this.objeto = objeto;
    }

    /**
     * Devuelve el objeto que está contenido tanto si es el que hemos asociado ya o leido de disco.
     * @return null si no hay ningún objeto asociado o el objeto asociado si lo hay.
     */
    public T getObjeto() {
        return objeto;
    }

    /**
     * Indica si hay un objeto o no contenido.
     * @return Si hay o no un objeto del tipo especificado contenido.
     */
    public boolean hayObjeto() {
        if (objeto == null) {
            //Si está a null es que no hay nada
            return false;
        }
        return true;
    }

    /**
     * Guarda el objeto contenido en disco.
     * @param ruta Ruta del archivo de salida.
     * @return Si se realizo correctamente la operación.
     */
    public boolean GuardarObjetoEnDisco(String ruta) {
        //Devuelve false si no se guarda
        boolean temp = false; 

        //A través de este flujo escribiremos en disco los objetos.
        ObjectOutputStream flujoguardar;
        try {
            flujoguardar = new ObjectOutputStream(new FileOutputStream(
                    ruta));
            //Escribe un objeto en disco
            flujoguardar.writeObject((T) objeto);
            //Cierra el flujo.
            flujoguardar.close();
            //True si se guarda correctamente
            temp = true; 
        } catch (IOException e) {
        }
        //Devuelve el resultado
        return temp;
    }

    /**
     * Lee un objeto de disco y lo sustituye como objeto contenido.
     * El objeto de disco y el tipo donde queremos guardarlo han de ser iguales.
     * @param ruta Ruta del archivo de entrada.
     * @return El objeto leído de disco o null si falla.
     */
    public T CargarObjetoDeDisco(String ruta) {
        //Borramos todo por si no es posible leer nada.
        objeto=null;
        //El archivo que queremos leer
        File archivo = new File(ruta);
        //Si el archivo existe
        if (archivo.exists() == true) {
            //A través de este flujo podremos leer un objeto
            ObjectInputStream flujocargar;
            try {
                //Carga el objeto
                flujocargar = new ObjectInputStream(new FileInputStream(ruta));
                //Cambia el objeto antiguo por el nuevo
                setObjeto((T) flujocargar.readObject());
                //Cierra el flujo
                flujocargar.close();
            } catch (FileNotFoundException s) {
            } catch (ClassNotFoundException w) {
            } catch (IOException e) {
            }
        }
        //Devuelve lo leído o null si no hay nada
        return objeto;
    }

    /**
     * Guarda en un archivo de texto ascii de disco las cadenas de la lista. Cada 
     * cadena es guardada en una línea nueva.
     * @param ruta Ruta del archivo de salida.
     * @param listaCadenas Lista con las dístintas líneas del archivo.
     * @return Si la operación se realizó correctamente.
     */
    public static boolean guardarTextoPlano(String ruta, List<String> listaCadenas) {
        try {
            //A través de este objeto podremos escribir líneas de texto
            PrintWriter bufferLineas = new PrintWriter(ruta);

            for (int i = 0; i < listaCadenas.size(); i++) {
                //Mientras haya líneas intenta escribirlas en disco
                bufferLineas.println(listaCadenas.get(i));
            }
            //Cierra el flujo
            bufferLineas.close();
            //Devuelve el éxito de la operación
            return true;
        } catch (IOException ex) {
        }
        //Si llega aquí es que algo ha fallado
        return false;
    }

    /**
     * Lee un archivo de texto plano ascii de disco y devuelve cada línea en una 
     * posición de una lista.
     * @param ruta Ruta del archivo de entrada.
     * @return La lísta de String leídas de disco o null si falla.
     */
    public static List<String> cargarTextoPlano(String ruta) {
        //Lista que devolveremos
        List<String> lista = new LinkedList();
        //Buffer temporal
        String temp;
        try {
            //Con esto se pueden leer líneas de texto de un archivo
            BufferedReader bufferLectura = new BufferedReader(new FileReader(ruta));

            //Mientras pueda leer líneas del archivo las añade a la lista
            while ((temp = bufferLectura.readLine()) != null) {
                lista.add(temp);
            }
            //Devuelve la lista
            return lista;
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
        //Si llega aquí es que algo ha ido mal.
        return null;
    }
}