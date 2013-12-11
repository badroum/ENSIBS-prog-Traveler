import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImageJPanel extends JFrame
{
    private static final long   serialVersionUID    = 1L;
    private JPanel      _panel;
     
    public ImageJPanel(Itineraire iti_aff)
    {
        // OUVRE L'IMAGE
        java.awt.Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("carte_france_ville.jpg");  
        
        // CREE PANEL
        _panel = new JPanel( new BorderLayout() );
         
        //TRACE L'ITINERAIRE SUR LA CARTE  
        /* On s'assure que l'image est complètement chargée */
        image = new ImageIcon(image).getImage();

        /* On crée la nouvelle image */
        BufferedImage bufferedImage = new BufferedImage(
                    image.getWidth(null),
                    image.getHeight(null),
                    BufferedImage.TYPE_INT_ARGB );
        //*
        Graphics2D g = (Graphics2D)bufferedImage.getGraphics(); // on récupère le contexte graphique de la BufferedImage   
        
        g.drawImage(image, 0, 0, this);
        
        g.setColor( Color.green ); 
        g.fillOval(Main.position[iti_aff.population[0][0]][0], Main.position[iti_aff.population[0][0]][1], 10, 10);
        
        g.setColor( Color.red );// on met l'état de couleur rouge à la BufferedImage
        for (int i = 1; i < iti_aff.population[0].length; i++) {
        	int x1 = Main.position[iti_aff.population[0][i-1]][0];
        	int y1 = Main.position[iti_aff.population[0][i-1]][1];
        	int x2 = Main.position[iti_aff.population[0][i]][0];
        	int y2 = Main.position[iti_aff.population[0][i]][1];
        	g.fillOval(x1, y1, 5, 5);
        	g.fillOval(x2, y2, 5, 5);
        	
        	g.drawLine(x1,y1,x2,y2);	//y2
		}
        
        g.drawLine(		Main.position[iti_aff.population[0][iti_aff.population[0].length-1]][0],	//x1
						Main.position[iti_aff.population[0][iti_aff.population[0].length-1]][1],		//y1
						Main.position[iti_aff.population[0][0]][0],	//x2
						Main.position[iti_aff.population[0][0]][1]);	//y2
        	// on dessine les vecteurs de coordonées les ville de l'itineraire
        g.dispose();

        //* AJOUTE IMAGE DANS PANEL
        
        image = bufferedImage;
        ImageIcon icon = new ImageIcon(image);
        _panel.add( new JLabel(icon) );

        //*/ AJOUTE PANEL DANS LA FENETRE
        add(_panel);
     
        // AFFICHE LA FENETRE
        setTitle("ITINERAIRE");
        setSize(470,500);
        setVisible(true);
        setLocationRelativeTo(null);               
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
       	this.setVisible(true);
    }
    
}