/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analexico;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Raul
 */
public class AFN {

    private static int IdAFN = 0;

    private static final char epsilon = (char) 0;

    private static int IdEdoAct = 0;

    public class Estado {

        int IdEdo;
        boolean aceptacion;
        HashSet<Transicion> Transiciones;
        int Token;

        public Estado() {
            IdEdo = IdEdoAct++;
            aceptacion = false;
            Transiciones = new HashSet<>();
        }

        public boolean AddTransicion(char simbolomax, Estado destino) {
            return Transiciones.add(new Transicion(simbolomax, destino));
        }

        public boolean AddTransicion(char simbolomax, char simbolomin, Estado destino) {
            return Transiciones.add(new Transicion(simbolomax, simbolomin, destino));
        }

        HashSet<Estado> Mover(char s) {
            HashSet<Estado> R = new HashSet<>();
            for (Transicion t : Transiciones) {
                if (t.getSimbolo() == s) {
                    R.add(t.getEstadoSig());
                }
            }
            return R;
        }

        public int getIdEdo() {
            return IdEdo;
        }

        public void setIdEdo(int IdEdo) {
            this.IdEdo = IdEdo;
        }

        public boolean isAceptacion() {
            return aceptacion;
        }

        public void setAceptacion(boolean aceptacion) {
            this.aceptacion = aceptacion;
        }

        public HashSet<Transicion> getTransiciones() {
            return Transiciones;
        }

        public void setTransiciones(HashSet<Transicion> Transiciones) {
            this.Transiciones = Transiciones;
        }

        public int getToken() {
            return Token;
        }

        public void setToken(int Token) {
            this.Token = Token;
        }

        @Override
        public String toString() {
            return "Estado " + IdEdo;
        }

    }

    public class Transicion {

        char simbolomax;
        char simbolomin;
        boolean rango;
        Estado estadoSig;

        public Transicion(char simbolomax, Estado estadoSig) {
            this.simbolomax = simbolomax;
            this.estadoSig = estadoSig;
            rango = false;
        }

        public Transicion(char simbolomax, char simbolomin, Estado estadoSig) {
            this.simbolomax = simbolomax;
            this.simbolomin = simbolomin;
            this.estadoSig = estadoSig;
            rango = true;
        }

        public char getSimbolo() {
            return simbolomax;
        }

        public Estado getEstadoSig() {
            return estadoSig;
        }

        public char getSimbolomin() {
            return simbolomin;
        }

        public boolean isRango() {
            return rango;
        }

        @Override
        public String toString() {
            if (rango) {
                return "Rango=" + simbolomax + "-" + simbolomin + ", estadoSig=" + estadoSig.getIdEdo();
            }
            return "Simbolo=" + simbolomax + ", estadoSig=" + estadoSig.getIdEdo();
        }

    }

    Estado inicial;
    HashSet<Estado> Estados;
    HashSet<Estado> EdosAcept;
    HashSet<Character> Alfabeto;
    int idAFN;

    public Estado getInicial() {
        return inicial;
    }

    public void setInicial(Estado inicial) {
        this.inicial = inicial;
    }

    public HashSet<Estado> getEstados() {
        return Estados;
    }

    public void setEstados(HashSet<Estado> Estados) {
        this.Estados = Estados;
    }

    public HashSet<Estado> getEdosAcept() {
        return EdosAcept;
    }

    public void setEdosAcept(HashSet<Estado> EdosAcept) {
        this.EdosAcept = EdosAcept;
    }

    public HashSet<Character> getAlfabeto() {
        return Alfabeto;
    }

    public void setAlfabeto(HashSet<Character> Alfabeto) {
        this.Alfabeto = Alfabeto;
    }

    public int getIdAFN() {
        return idAFN;
    }

    public void setIdAFN(int idAFN) {
        this.idAFN = idAFN;
    }

    @Override
    public String toString() {
        return ("AFN" + idAFN);
    }

    public AFN() {
        inicial = null;
        Estados = new HashSet<>();
        EdosAcept = new HashSet<>();
        Alfabeto = new HashSet<>();
        idAFN = IdAFN++;
    }

    public void CreaBasico(char simbolomax, char simbolomin, boolean range) {
        Estado ini = new Estado();
        Estado fin = new Estado();
        if (range) {
            ini.AddTransicion(simbolomax, simbolomin, fin);
            for (int i = (int) simbolomin; i <= (int) simbolomax; i++) {
                Alfabeto.add((char) i);
            }
        } else {
            ini.AddTransicion(simbolomax, fin);
            Alfabeto.add(simbolomax);
        }
        fin.setAceptacion(true);
        inicial = ini;
        Estados.add(ini);
        Estados.add(fin);
        EdosAcept.add(fin);
    }

    public AFN Union(AFN f) {
        Estado nvoIni, nvoFin;
        nvoIni = new Estado();
        nvoFin = new Estado();
        nvoIni.AddTransicion(epsilon, this.inicial);
        nvoIni.AddTransicion(epsilon, f.inicial);
        for (Estado e : this.EdosAcept) {
            e.AddTransicion(epsilon, nvoFin);
            e.setAceptacion(false);
        }
        for (Estado e : f.EdosAcept) {
            e.AddTransicion(epsilon, nvoFin);
            e.setAceptacion(false);
        }
        this.Estados.add(nvoIni);
        this.Estados.add(nvoFin);
        nvoFin.setAceptacion(true);
        this.inicial = nvoIni;
        this.EdosAcept.clear();
        this.EdosAcept.add(nvoFin);
        this.Estados = UnirEstados(this.Estados, f.Estados);
        this.Alfabeto = UnirAlfabeto(this.Alfabeto, f.Alfabeto);
        return this;
    }

    public AFN Concatenacion(AFN f) {
        for (Estado e : this.EdosAcept) {
            e.setAceptacion(false);
            for (Transicion t : f.inicial.getTransiciones()) {
                e.AddTransicion(t.getSimbolo(), t.getEstadoSig());
            }
        }
        this.EdosAcept.clear();
        this.EdosAcept = f.EdosAcept;
        f.Estados.remove(f.inicial);
        this.Estados = UnirEstados(this.Estados, f.Estados);
        this.Alfabeto = UnirAlfabeto(this.Alfabeto, f.Alfabeto);
        return this;
    }

    public AFN CerraduraPos() {
        Estado nvoIni, nvoFin;
        nvoIni = new Estado();
        nvoFin = new Estado();
        nvoFin.setAceptacion(true);
        for (Estado e : EdosAcept) {
            e.setAceptacion(false);
            e.AddTransicion(epsilon, inicial);
            e.AddTransicion(epsilon, nvoFin);
        }
        nvoIni.AddTransicion(epsilon, inicial);
        inicial = nvoIni;
        this.Estados.add(nvoFin);
        this.Estados.add(nvoIni);
        EdosAcept.clear();
        EdosAcept.add(nvoFin);
        return this;
    }

    public AFN CerraduraKlin() {
        this.CerraduraPos();
        for (Estado e : EdosAcept) {
            inicial.AddTransicion(epsilon, e);
        }
        return this;
    }

    public AFN Opc() {
        Estado nvoIni, nvoFin;
        nvoIni = new Estado();
        nvoFin = new Estado();
        nvoFin.setAceptacion(true);
        for (Estado e : EdosAcept) {
            e.setAceptacion(false);
            e.AddTransicion(epsilon, nvoFin);
        }
        nvoIni.AddTransicion(epsilon, inicial);
        nvoIni.AddTransicion(epsilon, nvoFin);
        inicial = nvoIni;
        EdosAcept.clear();
        EdosAcept.add(nvoFin);
        Estados.add(nvoFin);
        Estados.add(nvoIni);
        return this;
    }

    private HashSet<Estado> UnirEstados(HashSet<Estado> f1, HashSet<Estado> f2) {
        for (Estado e : f2) {
            f1.add(e);
        }
        return f1;
    }

    private HashSet<Character> UnirAlfabeto(HashSet<Character> a1, HashSet<Character> a2) {
        for (Character c : a2) {
            a1.add(c);
        }
        return a1;
    }

    private HashSet<Estado> UnionWith(HashSet<Estado> c, HashSet<Estado> c2) {
        for (Estado e : c2) {
            c.add(e);
        }
        return c;
    }

    public HashSet<Estado> CerraduraEpsilon(Estado e) {
        HashSet<Estado> c = new HashSet<>();
        Stack<Estado> p = new Stack<>();
        Estado aux = e;
        p.push(aux);
        while (!(p.empty())) {
            aux = p.pop();
            if (c.add(aux)) {
                for (Transicion t : aux.getTransiciones()) {
                    if (!t.isRango()) {                         //Si no es rango analiza si es una transicion epsilon
                        if (t.simbolomax == epsilon) {
                            p.push(t.getEstadoSig());
                        }
                    }
                }
            }
        }
        return c;
    }

    public HashSet<Estado> CerraduraEpsilon(HashSet<Estado> Edos) {
        HashSet<Estado> c = new HashSet<>();
        for (Estado e : Edos) {
            c = UnionWith(c, CerraduraEpsilon(e));
        }
        return c;
    }

    public HashSet<Estado> Mover(Estado edo, char s) {
        HashSet<Estado> R = new HashSet<>();
        for (Transicion t : edo.getTransiciones()) {
            if (t.isRango()) {
                if (((int) s >= (int) t.getSimbolomin()) && ((int) s <= (int) t.getSimbolo())) {
                    R.add(t.getEstadoSig());
                }
            } else {
                if (t.getSimbolo() == s) {
                    R.add(t.getEstadoSig());
                }
            }
        }
        return R;
    }

    public HashSet<Estado> Mover(HashSet<Estado> Edos, char s) {
        HashSet<Estado> R = new HashSet<>();
        for (Estado e : Edos) {
            R = UnionWith(R, Mover(e, s));
        }
        return R;
    }

    public HashSet<Estado> Ir_A(Estado e, char c) {
        HashSet<Estado> R = CerraduraEpsilon(Mover(e, c));
        return R;
    }

    public HashSet<Estado> Ir_A(HashSet<Estado> Edos, char c) {
        HashSet<Estado> R = new HashSet<>();
        for (Estado e : Edos) {
            R = UnionWith(R, Ir_A(e, c));
        }
        return R;
    }

    public AFN UnionEspecial(ArrayList<AFN> afns) {
        AFN Res = new AFN();
        Estado nvoIni = new Estado();
        for (AFN a : afns) {
            nvoIni.AddTransicion(epsilon, a.inicial);
            Res.UnirAlfabeto(Res.Alfabeto, a.Alfabeto);
            Res.UnionWith(Res.Estados, a.Estados);
            Res.UnionWith(Res.EdosAcept, a.EdosAcept);
        }
        Res.Estados.add(nvoIni);
        Res.inicial = nvoIni;
        return Res;
    }

    public AFD toAFD() {
        ArrayList<HashSet> listaI = new ArrayList<>();
        Queue<HashSet> colaNoMarcada = new LinkedList<>();
        Object[] alfa = Alfabeto.toArray();
        AFD afd = new AFD(Alfabeto);
        HashSet<Estado> i0 = this.CerraduraEpsilon(inicial);
        listaI.add(i0);
        colaNoMarcada.add(i0);
        while (!colaNoMarcada.isEmpty()) {
            HashSet<Estado> Ij = colaNoMarcada.poll();
            int[] renglon = new int[alfa.length + 1];
            for (int i = 0; i < alfa.length; i++) {
                char c = (char) alfa[i];
                HashSet<Estado> aux = this.Ir_A(Ij, c);
                if (aux.isEmpty()) {
                    renglon[i] = -1;
                } else { //He aqui el problema, No asigna el token porque no entra aqui
                    int com = 0;
                    for (HashSet<Estado> e : listaI) {
                        if (e.equals(aux)) {
                            com = -1;
                            break;
                        }
                    }
                    if (com == 0) {
                        listaI.add(aux);
                        colaNoMarcada.add(aux);
                    }
                    renglon[i] = listaI.indexOf(aux);
                }
                for (Estado edo : Ij) {
                    if (EdosAcept.contains(edo)) {
                        renglon[alfa.length] = edo.getToken();
                        break;
                    } else {
                        renglon[alfa.length] = -1;
                    }
                }
            }
            afd.addRenglon(renglon);
        }

        return afd;
    }

}
