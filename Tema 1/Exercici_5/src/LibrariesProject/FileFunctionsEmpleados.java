/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LibrariesProject;

import Models.Empleado;
import Models.EmpleadosTags;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author JPC
 */
public class FileFunctionsEmpleados {
    
    private String ficheroObjectEmpleado = "ObjectEmple.dat";
    private String ficheroXML = "Empleados.xml";    
    private int _registro = 36; 
    
    public Empleado[] importarFichero(Empleado[] empleados, RandomAccessFile file) throws IOException {
        int tam = (int) (file.length()/_registro);
        int posicion =0, id, dep, j;
        double salario;
        String apellidos;
        char apellido[]= new char[10];
        
        empleados = new Empleado[tam];
        
        j=0;
        for(;;){
            id = file.readInt();
            for(int i=0; i<apellido.length;i++)
                apellido[i] = file.readChar();
            apellidos = new String(apellido);
            dep=file.readInt();
            salario = file.readDouble();
            
            empleados[j] = new Empleado();
            empleados[j].apellidos = apellidos;
            empleados[j].departamento = dep;
            empleados[j].id = id;
            empleados[j].salario = salario;
            j++;
            
        posicion+=_registro;
        if(file.getFilePointer()==file.length())
   
            break;
        }
        return empleados;
        
    }

    public void consultarEmpleadoPorID(int id, RandomAccessFile file) {
        int posicion = (id -1) * _registro, dep;
        char apellido[] = new char[10], aux;
        Double salario;

        try {
            if(posicion>=file.length())
                System.out.println("ID: "+id+" no existe empleado...");
            else{
                file.seek(posicion);
                id = file.readInt();
                
                //recorro uno a uno los caracteres del apellido
                for(int i = 0; i < apellido.length; i++) {
                    aux = file.readChar();
                    apellido[i] = aux; //los voy guardando en el array                    
                }
                
                String apellidos = new String(apellido);
                dep = file.readInt(); //obtengo dep
                salario = file.readDouble(); //obtengo salario
                
                System.out.printf("ID: %s, Apellido: %10s,Departamento: %d, Salario: %.2f %n",id, apellidos.trim(), dep, salario);
            }
        } catch (IOException ex) {
            Logger.getLogger(FileFunctionsEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actualizarSalario(int id, double sumarSalario, RandomAccessFile file) {
        double salario_antiguo,salario_actual;
        int posicion = (id -1) * _registro, dep;
        char apellido[] = new char[10], aux;
        
        
        try {
            if(posicion>=file.length())
                System.out.println("ID: "+id+" no existe empleado...");
            else{
                file.seek(posicion);
                id = file.readInt();
                
                //recorro uno a uno los caracteres del apellido
                for(int i = 0; i < apellido.length; i++) {
                    aux = file.readChar();
                    apellido[i] = aux; //los voy guardando en el array                    
                }
                
                String apellidos = new String(apellido);
                dep = file.readInt(); //obtengo dep
                salario_antiguo = file.readDouble(); //obtengo salario
                file.seek(posicion+4+20+4);
                salario_actual = salario_antiguo+sumarSalario;
                file.writeDouble(salario_actual);            
                
                System.out.printf("ID: %s, Apellido: %10s,Departamento: %d, Salario antiguo: %.2f %n, Salario actualizado:%.2f %n",id, apellidos.trim(), dep, salario_antiguo, salario_actual);
            }
        } catch (IOException ex) {
            Logger.getLogger(FileFunctionsEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                if (file != null) {
                    file.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        
    }

    public void sumaAgrupadaPorDepartamento(int idBuscar, RandomAccessFile file) {
        
        //int posicion = (id -1) * _registro, dep;
        int posicion = 0, dep, idEmpleado;
        char apellido[] = new char[10], aux;
        Double salario, sumaSalarios=0.0;

        try {
            for(;;){
                if(posicion>=file.length())
                    System.out.println("No hay empleados");
                else{
                    file.seek(posicion);
                    idEmpleado = file.readInt();

                    //recorro uno a uno los caracteres del apellido
                    for(int i = 0; i < apellido.length; i++) {
                        aux = file.readChar();
                        apellido[i] = aux; //los voy guardando en el array                    
                    }

                    String apellidos = new String(apellido);
                    dep = file.readInt(); //obtengo dep
                    salario = file.readDouble(); //obtengo salario
                    
                    if(idBuscar == dep){
                        System.out.printf("ID: %s, Apellido: %10s,Departamento: %d, Salario: %.2f %n",idEmpleado, apellidos.trim(), dep, salario);                     
                        sumaSalarios+=salario;
                    }
                    
                }
            
                posicion += _registro;
                //Si he recorrido todos los bytes salgo del for
                if (file.getFilePointer() == file.length()) {
                    break;
                }
                
            }
            System.out.printf("El salario total del departamento %d es %.2f %n",idBuscar,sumaSalarios);
            
            
        } catch (IOException ex) {
            Logger.getLogger(FileFunctionsEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void serializarEmpleados2Fichero(Empleado[] empleados) throws FileNotFoundException, IOException {
        File fich = new File(ficheroObjectEmpleado);
        FileOutputStream fileout = new FileOutputStream(fich);
        ObjectOutputStream dataOS = new ObjectOutputStream(fileout);
        
        for (Empleado empleado : empleados) {
            dataOS.writeObject(empleado);
        }
        
        dataOS.close(); 
    }

    public Empleado[] serializarFichero2Empleado() throws FileNotFoundException, IOException, ClassNotFoundException {
        
        File fich = new File(ficheroObjectEmpleado);
        FileInputStream fileIn = new FileInputStream(fich);
        ObjectInputStream dataOS = new ObjectInputStream(fileIn);
        
        ArrayList<Empleado> empleadosArrayList = new ArrayList<>();
        Empleado empleado;
        
        try{
        while(true){
            empleado = (Empleado)dataOS.readObject();
            empleadosArrayList.add(empleado);
        }        
        }catch(IOException | ClassNotFoundException e){}
        
        dataOS.close();
        
        Empleado[] empleadosArray = new Empleado[empleadosArrayList.size()];
        
        return empleadosArrayList.toArray(empleadosArray);
    }

    public void serializarEmpleados2FicheroXML(Empleado[] empleados) throws TransformerConfigurationException, TransformerException, ParserConfigurationException {
        // Instanciamos la libreria
        XMLLib libreriaXML = new XMLLib();

        // Creamos el Fichero XML
        Document miDocumento = libreriaXML.crearXML();

        // Creamos el nodo
        Element nodoRaiz = libreriaXML.crearNodo(miDocumento, "empleados");

        //Mapeamos el objeto Empleados a los nodos
        for (Empleado empleado : empleados) {
           
            Element nodo = libreriaXML.crearNodo(miDocumento, nodoRaiz, "empleado");
           
            libreriaXML.crearElemento(miDocumento, nodo, "id",Integer.toString(empleado.getId()));            
            libreriaXML.crearElemento(miDocumento, nodo, "apellidos", empleado.getApellido().trim());
            libreriaXML.crearElemento(miDocumento, nodo, "departamento", Integer.toString(empleado.getDepartamento()));
            libreriaXML.crearElemento(miDocumento, nodo, "salario", Double.toString(empleado.getSalario()));
        }

        // Escritura en fichero
        TransformerFactory transformerFactory = TransformerFactory.newInstance();

      
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(miDocumento);
        StreamResult result = new StreamResult(new File(ficheroXML));

        transformer.transform(source, result);

    }

    public Empleado[] serializarFicheroXML2Empleado(Empleado[] empleados) throws ParserConfigurationException, SAXException, IOException {
        // Instanciamos la libreria
        XMLLib libreriaXML = new XMLLib();
        Document doc = libreriaXML.cargarXML(ficheroXML);
        
        //Mapeamos en memoria los empleados
        ArrayList<Object> empleadosObjectsLists = libreriaXML.cargarNodosXML(doc,EmpleadosTags.EMPLEADO.toString());
        
        //Inicializamos el array
        empleados = new Empleado[empleadosObjectsLists.size()];
        
        // Y lo pasamos a nuestro array  
        for (int i = 0; i < empleadosObjectsLists.size(); i++) {
            empleados[i] = (Empleado) empleadosObjectsLists.get(i);            
        }        
        return empleados;
    }

}
