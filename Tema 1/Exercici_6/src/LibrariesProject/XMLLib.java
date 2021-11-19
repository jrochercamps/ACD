/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LibrariesProject;

import Models.Empleado;
import Models.EmpleadosTags;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



/**
 *
 * @author Jose P.
 */
public class XMLLib {
    
    public Document ObreXML(String nom) throws IOException,SAXException, ParserConfigurationException, FileNotFoundException {

        // Creem una instància de DocumentBuilderFactory
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        // Amb la instància de DocumentBuilderFactory creem un DocumentBuilder
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        //I utilitzem el mètode "parse" de DocumentBuilder per obtindre el document
        Document doc = dBuilder.parse(new File(nom));

        return doc;
    }

    
    /**
     * Funció que rep un Document XML i retorna un array 
     * amb objectes de tipus mòdul
     * @param doc El document XML ja obert i carregat
     * @return El Array de Moduls
     */
    public Document cargarXML(String rutaArchivo) throws ParserConfigurationException, SAXException, IOException{
        
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
     
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        
        Document doc = dBuilder.parse(new File(rutaArchivo));     

        return doc;
    }

    public Document crearXML() throws ParserConfigurationException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document docXML = dBuilder.newDocument();
        
        return docXML;        
    }

    public Element crearNodo(Document doc, String textoEtiqueta) {
        
        Element el = doc.createElement(textoEtiqueta);
        doc.appendChild(el);

        return el;
    }

    Element crearNodo(Document miDocumento, Element nodoRaiz, String textoEtiqueta) {
        Element el = miDocumento.createElement(textoEtiqueta);
        nodoRaiz.appendChild(el);

        return el;
    }
    
    public Element crearElemento(Document doc, Element nodoPadre, String textoEtiqueta, String textoValor) {
        
        Element element = doc.createElement(textoEtiqueta);
        element.appendChild(doc.createTextNode(textoValor));
        nodoPadre.appendChild(element);

        return element;
    }

    public ArrayList<Object> cargarNodosXML(Document doc, String nodoNombre) {
        
        ArrayList<Object> empleadosObjetos = new ArrayList<>();        
                //"id","apellido","dep","salario"
            NodeList nodosLista = doc.getElementsByTagName(nodoNombre);          

            for (int i = 0; i < nodosLista.getLength(); i++) {
                Element empleado = (Element) nodosLista.item(i);
              
                int id = Integer.parseInt(
                        empleado.getElementsByTagName(EmpleadosTags.ID.toString())
                                .item(0)
                                .getFirstChild()
                                .getNodeValue());
                String apellido = empleado.getElementsByTagName(EmpleadosTags.APELLIDOS.toString())
                        .item(0)
                        .getFirstChild()
                        .getNodeValue();
                int departamento = Integer.parseInt(empleado.getElementsByTagName(EmpleadosTags.DEPARTAMENTO.toString())
                        .item(0)
                        .getFirstChild()
                        .getNodeValue());
                double salario = Double.parseDouble(empleado.getElementsByTagName(EmpleadosTags.SALARIO.toString())
                        .item(0)
                        .getFirstChild()
                        .getNodeValue());

                // Añadimos el empleado al ArrayList
                empleadosObjetos.add(new Empleado(id, apellido, departamento, salario));               
               
            }
        return empleadosObjetos;
    }
}
