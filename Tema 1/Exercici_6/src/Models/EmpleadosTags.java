/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author JPC
 */
//Enumerado para los tags de XML
public enum EmpleadosTags
{
    NODO_RAIZ {
        public String toString() {return "Empleados";}
    },
    EMPLEADO {
        public String toString() {return "empleado";}
    },
    ID {
        public String toString() {return "id";}
    },

    APELLIDOS {
        public String toString() {return "apellidos";}
    },

    DEPARTAMENTO {
        public String toString() {return "departamento";}
    },

    SALARIO {
        public String toString() {return "salario";}
    }  
}
