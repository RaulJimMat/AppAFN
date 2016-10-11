/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analexico;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Raul
 */
public class AFD implements Serializable{
    
    Object[] Alfabeto;
    ArrayList<int []> transiciones;

    public AFD(HashSet<Character> Alfabeto) {
        this.Alfabeto =  Alfabeto.toArray();
        transiciones = new ArrayList<>();
    }
    

    public void addRenglon(int[] newrenglon){
        transiciones.add(newrenglon);
    }

    @Override
    public String toString() {
        return "AFD{" + "transiciones=\n" + transiciones + "\n}";
    }

    public Object[] getAlfabeto() {
        return Alfabeto;
    }

    public ArrayList<int[]> getTransiciones() {
        return transiciones;
    }
    
    public int[] getTransicionEdo(int i) {
        return transiciones.get(i);
    }   
    
    public void save(File f){
        SaveLoadAFD safd = new SaveLoadAFD();
        try {
            safd.toFile(this, f);
        } catch (IOException ex) {
            Logger.getLogger(AFD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
