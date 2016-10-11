/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anasintactico;

import analexico.AFD;
import analexico.AnalizadorLex;
import analexico.SaveLoadAFD;
import java.io.File;

/**
 *
 * @author Raul
 */
public class GenSintactico {
    
    
   private static class Token {

        static int PUNTO_COMA = 10;
        static int FLECHA = 20;
        static int OR = 30;
        static int SIMBOLO = 40;
        static int ESPACIO = 50;

    }

    private AnalizadorLex a;
    Nodo Principal = null;
    public GenSintactico(AFD afd) {
        a = new AnalizadorLex(afd);
        Principal= new Nodo();
    }

    public String A(String cad) {
        a.setCadena(cad);
        a.reiniciarContadores();
        
        if (G(Principal)) {
            if ((getToken() == 0) && (Principal != null)) {    
                System.out.println(Principal.getSimbolo()+"  "+Principal.getApDer().getSimbolo());
                System.out.println(Principal.getApAbajo().getSimbolo()+"  "+Principal.getApAbajo().getApDer().getSimbolo());
                return "Cadena sintacticamente correcta";
            }
           
        }
        return "Cadena sintacticamente incorrecta";
    }

    boolean G(Nodo NodoRef) {
        if(Lista_Reglas(NodoRef)){
            return true;
        }else{
            NodoRef = null;
        }
        return false;
    }

    private boolean Lista_Reglas(Nodo NodoRef) {
        int t;
        Nodo NodoRef2 = new Nodo();
        if (Regla(NodoRef)) {            
            t = getToken();            
            if (t == Token.PUNTO_COMA) {                
                int tok2=getToken();
                if(tok2==Token.SIMBOLO){
                    a.returnToken();                    
                    if(Lista_Reglas(NodoRef2)){
                        NodoRef.setApAbajo(NodoRef2);                        
                    }
                    return true;
                }
                return tok2==0;                                                
            } 
            return false;
        }
        return false;
    }

//    private boolean Lista_Reglas_p() {
//        int t;
//        //x = getEdoScanner();
//        if (Regla()) {
//            t = a.getToken();
//            if (t == Token.PUNTO_COMA) {
//                return Lista_Reglas_p();
//            }
//            return false;
//        }
//        //SetEdoScanner(x);
//        return true;
//    }

    private boolean Regla(Nodo NodoRef) {
        int t;
        Nodo NodoRef2 = new Nodo();
        if (Lado_Izq(NodoRef)) {
            t = getToken();            
            if (t == Token.FLECHA) {
                if(Lados_Derechos(NodoRef2)){                  
                    NodoRef.setApDer(NodoRef2);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean Lado_Izq(Nodo NodoRef) {
        int t = getToken();        
        if (t == Token.SIMBOLO){
//            NodoRef = new Nodo(a.getLexema().charAt(0));
            NodoRef.setSimbolo(a.getLexema().charAt(0));            
            return true;
        }
        return false;
    }

    private boolean Lados_Derechos(Nodo NodoRef) {
        int t;
        Nodo NodoRef2 = new Nodo();
        if (lado_Derecho(NodoRef)) {            
            t = getToken();            
            if(t == Token.OR){
                if(Lados_Derechos(NodoRef2)){
                    NodoRef.setApAbajo(NodoRef2);
                    return true;
                }
                return false;
            }
            a.returnToken();
            return true;
        }
        return false;
    }

//    private boolean Lados_Derechos_p() {
//        //x = getEdoScanner;
//        if (lado_Derecho()) {
//            return Lados_Derechos_p();
//        }
//        //SetEdoScanner(x);
//        return true;
//    }

    private boolean lado_Derecho(Nodo NodoRef) {        
        return Lista_Simbolos(NodoRef);
    }

    private boolean Lista_Simbolos(Nodo NodoRef) {
        int t;
        //edo_scanner Temp;
        Nodo NodoRef2 = new Nodo();
        t = getToken();        
        if (t == Token.SIMBOLO) {
            //NodoRef = new Nodo(a.getLexema().charAt(0));
            NodoRef.setSimbolo(a.getLexema().charAt(0));           
            //Temp = getEdoScanner();
            if (Lista_Simbolos(NodoRef2)) {
                NodoRef.setApDer(NodoRef2);
                return true;
            }
            a.returnToken();
            //setEdoScanner(Temp);
            return true;
        }
        return false;
    }

//    private boolean Lista_Simbolos_p() {
//        int t = a.getToken();
//        if (t == Token.SIMBOLO) {
//            return Lista_Simbolos_p();
//        }
//        return true;
//    }

    
    int getToken() {
        int token;
        do{
            token = a.getToken();
        }while (token == 50);
        return token;
    }
          
    public static void main(String[] args) {
        String cad="A -> A a | B C D ;";
        SaveLoadAFD load= new SaveLoadAFD(); 
        AFD afd = load.fromFile(new File("autoGram"));
        GenSintactico gens= new GenSintactico(afd);        
        System.out.println(gens.A(cad));
    }
}
