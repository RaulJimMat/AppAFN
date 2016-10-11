/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anasintactico;

import java.util.Stack;

/**
 *
 * @author Raul
 */
public class AnalizadorSin {
    
    char[] Cadena;
    Stack pila;

    public AnalizadorSin(String Cadena) {
        String Cad = Cadena + "$";
        this.Cadena = Cad.toCharArray();
        pila.push('$');
    }
    
    public void analizar(){
        
    }
    
    
}
