/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LibrariesProject;

import org.json.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.xml.sax.SAXException;

/**
 *
 * @author JPC
 */
public class JSONLib {
/*1
    public JSONObject creaJSON() throws JSONException{

    // L'arrel del document serà un objecte "Curs"
    JSONObject curs=new JSONObject();
    // Que contindrà una llista de mòduls:
    JSONArray jsarray = new JSONArray();

    // Anem a omplir la llista obtenint els
    // diferents mòduls com a JSONObject
    // (veure el mètode getModulJSON de la classe Modul)
    for(Modul m:this.Curs)  {
        JSONObject modul_json=m.getModulJSON();
        // Afegim l'ojbecte JSON al vector
        jsarray.put(modul_json);
    }  
    
    // Creem l'element arrel amb la clau "curs"
    // i posem com a valor el vector.
    curs.put("curs", jsarray);

    // Retornem el curs
    return (curs);

}
   */ 
    public void EscriuJSON(String filename, JSONObject jso) throws JSONException{

    try {
        FileWriter file = new FileWriter(filename);
        file.write(jso.toString(4)); // 4 són els espais d'indentació
        file.close();

    } catch (IOException e) {
        System.out.println("Error, no es pot crear el fitxer "+filename);
    }
}
    
    public JSONObject LligJSON(String filename){
    try {
        // Amb FileReader llegirem caràcter a 
        // caràcter el fitxer i l'afegim al string myJson
        FileReader file = new FileReader(filename); 
        String myJson="";
    
        int i;
        while ((i=file.read()) != -1) 
            myJson=myJson+((char) i);

        //System.out.println(myJson);
        file.close();
        
        // I fem ús del constructor de JSONObject
        // al que li passem un string amb el JSON:
        return (new JSONObject(myJson));

    
    } catch (Exception e)
    {
        System.out.println("Error llegint el fitxer");
        return null;
    }

}
    
    //TO-DO
    public void MostraJson(JSONObject json) throws JSONException{
    /*
    // amb el mètode getJSONArray obtenim el primer
    // element "curs", que era una llista
    JSONArray jsa=json.getJSONArray("curs");

    // I ara recorrem aquesta llista:
    for (int i = 0; i < jsa.length(); i++) {
        // Agafem cada element de l'array amb "get"
        JSONObject modul=(JSONObject)jsa.get(i);
        // Amb el get anterior tindrem objectes JSON 
        // de mòduls, tipus:
        // {"nom": "Modul", "hores": hores, "nota": nota }
        // Als valors d'aquests parells també accedirem amb get:
        System.out.println("Modul: "+ modul.get("nom"));
        System.out.println("Hores: "+ modul.get("hores"));
        
        System.out.println("Nota: "+modul.get("nota"));

    }
 */ 
}
  
}
