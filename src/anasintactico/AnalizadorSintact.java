/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anasintactico;

import analexico.AnalizadorLex;

/**
 *
 * @author Raul
 */
public class AnalizadorSintact {
    
    static class Tokens{
        static final int MAS = 10;
        static final int MENOS = 20;
        static final int POR = 30;
        static final int DIV = 40;
        static final int POT = 50;
        static final int PAR_IZQ = 60;
        static final int PAR_DER = 70;
        static final int SIN = 80;
        static final int COS = 90;
        static final int TAN = 100;
        static final int LOG = 110;
        static final int LN = 120;
        static final int EXP = 130;
        static final int NUM = 140;
        }
    
    AnalizadorLex analizador;

    public AnalizadorSintact(AnalizadorLex analizador, String Cadena) {
        this.analizador = analizador;
        this.analizador.setCadena(Cadena);
    }
    
    
    
    public boolean E(){
        if(T())
            if(Ep())
                return true;
        return false;
    }

    private boolean Ep() {
        int t = analizador.getToken();
        if(t == Tokens.MAS || t == Tokens.MENOS){
            if(T())
                if(Ep())
                    return true;
            return false;
        }
        analizador.returnToken();
        return true;
    }
 
    private boolean T() {
        if(P())
            if(Tp())
                return true;
        return false;
    }

    private boolean Tp() {
        int t = analizador.getToken();
        if(t == Tokens.POR || t == Tokens.DIV){
            if(P())
                if(Tp())
                    return true;
            return false;
        }
        analizador.returnToken();
        return true;
    }

    private boolean P() {
        if(F())
            if(Pp())
                return true;
        return false;
    }

    private boolean Pp() {
        int t = analizador.getToken();
        if(t == Tokens.POT){
            if(F())
                if(Pp())
                    return true;
            return false;
        }
        analizador.returnToken();
        return true;
    }

    private boolean F() {
        int t = analizador.getToken();
        switch(t){
            case Tokens.PAR_IZQ:
                if(E()){
                    t = analizador.getToken();
                    if(t == Tokens.PAR_DER)
                        return true;
                }
                return false;
            
            case Tokens.SIN:
                t = analizador.getToken();
                if(t == Tokens.PAR_IZQ){
                    if(E()){
                        t = analizador.getToken();
                        if(t == Tokens.PAR_DER){
                            return true;
                        }
                    }
                }
                return false;
                
            case Tokens.COS:
                t = analizador.getToken();
                if(t == Tokens.PAR_IZQ){
                    if(E()){
                        t = analizador.getToken();
                        if(t == Tokens.PAR_DER){
                            return true;
                        }
                    }
                }
                return false;
                
            case Tokens.TAN:
                t = analizador.getToken();
                if(t == Tokens.PAR_IZQ){
                    if(E()){
                        t = analizador.getToken();
                        if(t == Tokens.PAR_DER){
                            return true;
                        }
                    }
                }
                return false;
                
            case Tokens.LOG:
                t = analizador.getToken();
                if(t == Tokens.PAR_IZQ){
                    if(E()){
                        t = analizador.getToken();
                        if(t == Tokens.PAR_DER){
                            return true;
                        }
                    }
                }
                return false;
                
            case Tokens.LN:
                t = analizador.getToken();
                if(t == Tokens.PAR_IZQ){
                    if(E()){
                        t = analizador.getToken();
                        if(t == Tokens.PAR_DER){
                            return true;
                        }
                    }
                }
                return false;
                
            case Tokens.EXP:
                t = analizador.getToken();
                if(t == Tokens.PAR_IZQ){
                    if(E()){
                        t = analizador.getToken();
                        if(t == Tokens.PAR_DER){
                            return true;
                        }
                    }
                }
                return false;
            
            case Tokens.NUM:
                return true;        
        }
        
        return false;
                
    }
    
}
