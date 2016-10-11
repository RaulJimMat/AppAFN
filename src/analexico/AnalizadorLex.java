/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analexico;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Raul
 */
public class AnalizadorLex {

    static final char FIN_CAD = (char) 3;

    private final AFD automata;
    Stack<Integer> pila;
    int i = 0;
    int iniLex = 0;
    int finLex = 0;
    public String cadena;
    
    public void reiniciarContadores(){
        i = 0;
        iniLex = 0;
        finLex = 0;
    }
    
    public AnalizadorLex(AFD automata) {
        this.automata = automata;
        cadena = null;
        pila = new Stack<>();
        String alf = "";
        for(Object c : this.automata.getAlfabeto()){
            alf = alf + c;
        }
        System.out.println(alf);
        ArrayList<int[]> ls = automata.getTransiciones();
        for(int [] e : ls){
            String aux = "";
            for (int i = 0; i < e.length; i++) {
                aux += (e[i] + ", ");
            }
            System.out.println(aux + "\n");
        }
        
    }
    

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String Cadena) {
        this.cadena = Cadena;
        this.cadena += FIN_CAD;
    }
    
    

    public int getToken() {
        pila.push(i);
        int current_state = 0;
        int prev_seen_acept_state = -1;
        int last_pos = 0;
        char[] cad = cadena.toCharArray();

        if (cad[i] == FIN_CAD) {
            return 0;
        }
        int res = 0;
        ArrayList<Character> listaChar = new ArrayList<>();
        for (Object car : automata.getAlfabeto()) {
            listaChar.add((Character) car);
        }

        while (cad[i] != FIN_CAD) {
            int edoSig = automata.getTransicionEdo(current_state)[listaChar.indexOf(new Character(cad[i]))];
            if (edoSig != -1) {
                current_state = edoSig;
                i++;
                if ((automata.getTransicionEdo(current_state)[automata.getTransicionEdo(current_state).length - 1]) != -1) {
                    last_pos = i;
                    finLex = i;
                    prev_seen_acept_state = current_state;
                }
            } else {
                if (prev_seen_acept_state == -1) {
                    //Error                  
                    current_state = 0;
                    i++;
                    return 1000;
                } else {
                    i = last_pos;
                    int[] edo = automata.getTransicionEdo(prev_seen_acept_state);
                    res = edo[edo.length - 1];
                    return res;
                }
            }
        }
        int[] edo = automata.getTransicionEdo(prev_seen_acept_state);
        res = edo[edo.length - 1];
        return res;

    }

    public void returnToken() {
        i = pila.pop();
    }

    public String getLexema() {
        iniLex = pila.peek();
        return cadena.substring(iniLex, finLex);
    }
}
