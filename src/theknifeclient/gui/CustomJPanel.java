package theknife.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.LinearGradientPaint;
import java.awt.MultipleGradientPaint.CycleMethod;
import javax.swing.JPanel;

/**
 * {@code CustomJPanel} is a subclass of {@link JPanel} that displays a custom background for the panel.
 * <p>
 * In the class there are defined all different color variations for each page.
 * </p>
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */
public class CustomJPanel extends JPanel
{
  //<editor-fold defaultstate="collapsed" desc="Constructor">
  /**
   * Constructs a new {@code CustomJPanel}
   * @param page to set up const for specific page
   */
  public CustomJPanel   (String page)                        
  {
    setPanelPage(page);
  } 
  /**
   * Constructs a new {@code CustomJPanel} with custom layout
   * @param layout in order to add a custom layot to the JPanel
   * @param page to set up const for specific page
   */
  public CustomJPanel   (LayoutManager layout, String page)  
  {
    super       (layout);
    setPanelPage(page); 
  }
  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Methods">
  private void setPanelPage(String page)
  {
    switch(page)
    {
      case "Home":
        setColor    = schemes[0];  
        setPosition = pos    [0];
        break;
      case "Login":
        setColor    = schemes[1];  
        setPosition = pos    [1];
        break;
      case "Login as a restaurateur":
        setColor    = schemes[1];  
        setPosition = pos    [1];
        break;
      case "Register":
        setColor    = schemes[1];  
        setPosition = pos    [1];
        break;   
      case "Register as a restaurateur":
        setColor    = schemes[1];  
        setPosition = pos    [1];
        break;   
      case "Advanced search":
    
      case "Restaurant":
    
      case "Review":
    
      case "Add Review":
    
      case "Add Response":   
    }
  }
  /**
    * Paints the component by drawing the background with a custom gradient.
    *
    * @param g - the {@link Graphics} context to use for painting
    */
  @Override
  protected void paintComponent(Graphics g) 
  {
    super.paintComponent(g);

    LinearGradientPaint lgp         = new LinearGradientPaint(0, 0, getWidth(), getHeight(), setPosition, setColor, CycleMethod.NO_CYCLE);
    Graphics2D g2d                  = (Graphics2D) g;
    
    g2d.setPaint(lgp);
    g2d.fillRect(0, 0, getWidth(), getHeight());
  }      
  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Fields">
  private float[] setPosition = null;
  private Color[] setColor    = null;
  //</editor-fold>
  //<editor-fold defaultstate="collapsed" desc="Consts">
  //Home
   private final Color[][] schemes = 
   {
     {new Color(100, 200, 100), new Color   (50,  200, 50 ),    new Color(20,  125, 20 )},
     {new Color(200, 255, 200), new Color   (175, 255, 175),    new Color(100, 200, 100)}
   };
   private final float[][] pos     = 
   {
     {0.0f, 0.50f, 1.0f},
     {0.0f, 0.50f, 1.0f}
   };
  //</editor-fold>
}
