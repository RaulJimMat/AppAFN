/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analexico;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Raul
 */
public class SaveLoadAFD {

    public AFD fromFile(File f) {
        AFD afd = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            afd = (AFD)ois.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaveLoadAFD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(SaveLoadAFD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ois.close();
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(SaveLoadAFD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return afd;
    }

    void toFile(AFD afd, File f) throws IOException {
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(afd);
        oos.close();
        fos.close();
    }

    }
