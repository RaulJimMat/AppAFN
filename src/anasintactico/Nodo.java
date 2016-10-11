/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anasintactico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Raul
 */
public class Nodo implements Serializable {

    private static final char epsilon = (char) 0;
    private char Simbolo;
    private Nodo ApDer;
    private Nodo ApAbajo;
    private int idNodo;
    private static int ids = 0;

    public Nodo(char simbolo) {
        idNodo = ids++;
        this.Simbolo = simbolo;
        ApDer = null;
        ApAbajo = null;
    }

    public Nodo() {
        idNodo = ids++;
        ApDer = null;
        ApAbajo = null;
    }

    /**
     * @return the Simbolo
     */
    public char getSimbolo() {
        return Simbolo;
    }

    /**
     * @param Simbolo the Simbolo to set
     */
    public void setSimbolo(char Simbolo) {
        this.Simbolo = Simbolo;
    }

    /**
     * @return the ApDer
     */
    public Nodo getApDer() {
        return ApDer;
    }

    /**
     * @param ApDer the ApDer to set
     */
    public void setApDer(Nodo ApDer) {
        this.ApDer = ApDer;
    }

    /**
     * @return the ApAbajo
     */
    public Nodo getApAbajo() {
        return ApAbajo;
    }

    /**
     * @param ApAbajo the ApAbajo to set
     */
    public void setApAbajo(Nodo ApAbajo) {
        this.ApAbajo = ApAbajo;
    }

    @Override
    public String toString() {
        return "Nodo " + idNodo;
    }

    public TablaLL1 creaTabla(Nodo Inicial) {
        Nodo nuevoInicial = new Nodo('G');
        Nodo nvo = new Nodo(Inicial.getSimbolo());
        nvo.setApDer(new Nodo('$'));
        nuevoInicial.setApDer(nvo);
        nuevoInicial.setApAbajo(Inicial);
        HashSet<Character> Vt = ObtenerVt(nuevoInicial);
        HashSet<Character> Vn = ObtenerVn(nuevoInicial);
        System.out.println(Vt.toString());
        System.out.println(Vn.toString());
        char[] terminales, noTerminales;
        String[][] tabla;

        terminales = new char[Vt.size()];
        noTerminales = new char[Vn.size()];
        int i = 0;
        for (Character c : Vt) {
            terminales[i] = c;
            i++;
        }
        i = 0;
        for (Character c : Vn) {
            noTerminales[i] = c;
            i++;
        }

        tabla = new String[noTerminales.length][terminales.length];

        //Hacer la tabla
        String cad = "";
        Nodo Aux = Inicial;
        while (Aux != null) {
            cad = "";
            HashSet<Character> hs = First(nuevoInicial, Aux.getSimbolo());
            if (hs.contains(epsilon)) {
                HashSet<Character> hs2 = Follow(nuevoInicial, Aux.getSimbolo());
            }
            Nodo Aux2 = nuevoInicial, Aux3;
            while (Aux2 != null) {
                if (Aux2.getSimbolo() == Aux.getSimbolo()) {
                    Aux3 = Aux2.ApDer;
                    while (Aux3 != null) {
                        cad += Aux3.getSimbolo();
                        Aux3 = Aux3.getApDer();
                    }
                }
                Aux2 = Aux2.getApAbajo();
            }
            Aux = Aux.getApAbajo();
            System.out.println(hs.toString());
            System.out.println(cad);
        }

        return new TablaLL1(terminales, noTerminales, tabla);

    }

    public HashSet<Character> ObtenerVn(Nodo Ini) {
        HashSet<Character> c = new HashSet<>();
        Nodo Aux = Ini;
        while (Aux != null) {
            c.add(new Character(Aux.getSimbolo()));
            Aux = Aux.getApAbajo();
        }
        return c;
    }

    public HashSet<Character> ObtenerVt(Nodo Ini) {
        HashSet<Character> c = new HashSet<>();
        Nodo aux1, aux2, aux3;
        aux1 = Ini;
        while (aux1 != null) {
            aux2 = aux1.getApDer();
            while (aux2 != null) {
                aux3 = aux2;
                HashSet<Character> Vn = ObtenerVn(Ini);
                while (aux3 != null) {
                    if ((!Vn.contains(aux3.getSimbolo())) && (aux3.getSimbolo() != epsilon)) {
                        c.add(aux3.getSimbolo());
                    }
                    aux3 = aux3.getApDer();
                }
                aux2 = aux2.getApAbajo();
            }
            aux1 = aux1.getApAbajo();
        }
        return c;
    }

    private HashSet<Character> First(Nodo nuevoInicial, char nT) {
        Nodo Aux = nuevoInicial, Aux2, Aux3;
        HashSet<Character> hs = new HashSet<>();
        while (Aux != null) {
            if (Aux.getSimbolo() == nT) {
                Aux2 = Aux.getApDer();
                while (Aux2 != null) {
                    if (ObtenerVt(nuevoInicial).contains(Aux2.getSimbolo()) || (Aux2.getSimbolo() == epsilon)) {
                        hs.add(Aux2.getSimbolo());
                    } else {
                        UnionWith(hs, First(nuevoInicial, Aux2.getSimbolo()));
                    }
                    Aux2 = Aux2.getApAbajo();
                }
            }
            Aux = Aux.getApAbajo();

        }
        return hs;
    }

    private HashSet<Character> UnionWith(HashSet<Character> c, HashSet<Character> c2) {
        for (char e : c2) {
            c.add(e);
        }
        return c;
    }

    private HashSet<Character> Follow(Nodo nuevoInicial, char simbolo) {
        HashSet<Character> hs = new HashSet<>();
        Nodo Aux1 = nuevoInicial, Aux2, Aux3;
        while (Aux1 != null) {
            Aux2 = Aux1.getApDer();
            while (Aux2 != null) {
                if (Aux2.getSimbolo() == simbolo) {
                    
                }
                
                
                Aux2 = Aux2.getApAbajo();
            }
            Aux1 = Aux1.getApAbajo();
        }

        return hs;
    }

}
