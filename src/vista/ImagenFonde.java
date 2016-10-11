package vista;


import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.border.Border;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Raul
 */
public class ImagenFonde implements Border{
    
    public BufferedImage back;

    public ImagenFonde() {
        try {
            URL imaUrl= new URL(getClass().getResource("/Users/Raul/NetBeansProjects/AppAFN/imageEscom.jpg").toString());
            back = ImageIO.read(imaUrl);
        } catch (Exception e) {
            System.out.println("No se encontro imagen");
        }
    }
    

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawImage(back,(x + (width - back.getWidth()) / 2), (y + (height - back.getHeight()) / 2), null);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(0, 0, 0, 0);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
    
    
    
}
