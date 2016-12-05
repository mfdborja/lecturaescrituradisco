package operardisco;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Borja Rodríguez - borjaro2000.tk
 */
public class OperarDisco {
    public static void main(String[] args) {
        //Un entero ----------------------------------------------------------------------        
        // Ruta del archivo - Directorio actual
        String ruta = "archivo1.objeto";
        //Objeto a guardar       
        int prueba1 = 4;
        //Resultado
        boolean resultado;

        //Guardar el objeto
        OperacionesDisco<Integer> guardaDisco = new OperacionesDisco<Integer>(prueba1);
        resultado = guardaDisco.GuardarObjetoEnDisco(ruta);

        //Lo guarda o no
        if (resultado) {
            System.out.println("Todo ha funcionado correctamente al guardar.");
        } else {
            System.out.println("Algo ha fallado al guardar.");
        }

        //Ahora a sacarlo del disco
        OperacionesDisco<Integer> cargaDisco = new OperacionesDisco<Integer>();
        cargaDisco.CargarObjetoDeDisco(ruta);
        int salida = cargaDisco.getObjeto();

        if (cargaDisco.hayObjeto()) {
            System.out.println("Primer ejemplo de salida: " + salida);
        } else {
            System.out.println("No se han obtenido datos.");
        }

        //Un String  ---------------------------------------------------------------------
        // Ruta del archivo - Directorio actual
        String ruta2 = "archivo2.objeto";
        //Objeto a guardar       
        String prueba2 = "Este es un texto de prueba.";
        //Resultado
        boolean resultado2;

        //Guardar el objeto
        OperacionesDisco<String> guardaDisco2 = new OperacionesDisco<String>(prueba2);
        resultado2 = guardaDisco2.GuardarObjetoEnDisco(ruta2);

        //Lo guarda o no
        if (resultado2) {
            System.out.println("Todo ha funcionado correctamente al guardar.");
        } else {
            System.out.println("Algo ha fallado al guardar.");
        }

        //Ahora a sacarlo del disco
        OperacionesDisco<String> cargaDisco2 = new OperacionesDisco<String>();
        cargaDisco2.CargarObjetoDeDisco(ruta2);
        String salida2 = cargaDisco2.getObjeto();

        if (cargaDisco2.hayObjeto()) {
            System.out.println("Segundo ejemplo de salida: " + salida2);
        } else {
            System.out.println("No se han obtenido datos.");
        }      
        
        //Varias cadenas en texto plano ------------------------------------------
        List<String> listaCadenas=new ArrayList();
        String ruta4="archivo3.txt";
        listaCadenas.add("ARCHIVO DE PRUEBA");
        listaCadenas.add("");
        listaCadenas.add("Esta es una prueba para escribir varias ");
        listaCadenas.add("líneas en un archivo de texto editable con cualquier editor de textos.");
        listaCadenas.add("");
        listaCadenas.add("www.borjaro2000.tk");
        
        System.out.println("Guardando texto plano.");
        OperacionesDisco.guardarTextoPlano(ruta4, listaCadenas);
        
        List<String> salidaTextoPlano=OperacionesDisco.cargarTextoPlano("coneditor.txt");
        
        if(salidaTextoPlano==null){
            System.out.println("No se leyó nada del fichero plano.");
        }else{
            for(int i=0;i<salidaTextoPlano.size();i++){
                System.out.println(salidaTextoPlano.get(i));
            }
        }
    }
}
