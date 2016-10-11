/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anasintactico;

import java.util.HashSet;

/**
 *
 * @author Raul
 */
public class TablaLL1 {
    
    char[] terminales;
    char[] noTerminales;
    String[][] tabla;

    public TablaLL1(HashSet<Character> Vt,HashSet<Character> Vn) {
        terminales = new char[Vt.size()];
        noTerminales = new char[Vn.size()];
        int i = 0;
        for(Character c :Vt){
            terminales[i] = c;
            i++;
        }
        i = 0;
        for(Character c :Vn){
            noTerminales[i] = c;
            i++;
        }
        System.out.println("Terminales");
        for(char ch14 : terminales){
            System.out.println(ch14);
        }
        
        System.out.println("No Terminales");
        for(char ch14 : noTerminales){
            System.out.println(ch14);
        }
        
         
    }

    public TablaLL1(char[] terminales, char[] noTerminales, String[][] tabla) {
        this.terminales = terminales;
        this.noTerminales = noTerminales;
        this.tabla = tabla;
    }
    
    
    
}
