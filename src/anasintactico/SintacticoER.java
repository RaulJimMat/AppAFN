package anasintactico;

import analexico.AFN;
import analexico.AnalizadorLex;

/**
 *
 * @author AndresGH
 */
public class SintacticoER {
    AnalizadorLex anlex;
    private final int OR=10;
    private final int CONC=20;
    private final int CERR_POS=30;    
    private final int OPCION=40;
    private final int SIMB=50;
    private final int PAR_IZQ=60;
    private final int PAR_DER=70;
    private final int CERR_KLIN=80;

  public SintacticoER(AnalizadorLex analizador, String Cadena) {
        this.anlex = analizador;
        this.anlex.setCadena(Cadena);
  }
    
    public boolean E(AFN f){
        if(T(f))
            if(Ep(f))
                return true;
        return false;
    }
    
    public boolean Ep(AFN f){
        int tok;
        AFN f2= new AFN();
        tok= anlex.getToken();
        if(tok==OR){
            if(T(f2)){
                f.Union(f2);
                if(Ep(f))
                    return true;
            }
            return false;
        }
        anlex.returnToken();
        return true;
    }
    
    public boolean T(AFN f){
        if(C(f))
            if(Tp(f))
                return true;
        return false;        
    }
    
    public boolean Tp(AFN f){
        int tok;
        AFN f2= new AFN();
        tok= anlex.getToken();
        if(tok == CONC){
            if(C(f2)){
                f.Concatenacion(f2);
                if(Tp(f))
                    return true;
            }
            return false;
        }
        anlex.returnToken();
        return true;
    }
    
    public boolean C(AFN f){
        if(F(f))
            if(Cp(f))
                return true;
        return false;
    }
    
    public boolean Cp(AFN f){
        int tok= anlex.getToken();
        switch(tok){
            case CERR_POS: f.CerraduraPos();
            break;
            
            case CERR_KLIN: f.CerraduraKlin();
            break;
            
            case OPCION: f.Opc();
            break;
            
            default: 
                anlex.returnToken();
                return true;
        }
        return Cp(f);
    }
    
    public boolean F(AFN f){
        int tok= anlex.getToken();
        switch(tok){
            case SIMB:{
                f.CreaBasico(anlex.getLexema().charAt(0),anlex.getLexema().charAt(0),false);
                return true;
            }            
            
            case PAR_IZQ:{
                if(E(f)){
                    tok=anlex.getToken();
                    if(tok==PAR_DER)
                        return true;
                }
                return false;
            }              
        }
        return false; 
    }


}
