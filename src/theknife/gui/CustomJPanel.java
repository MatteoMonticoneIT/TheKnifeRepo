package theknife.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.LinearGradientPaint;
import java.awt.MultipleGradientPaint.CycleMethod;
import javax.swing.JPanel;


public class CustomJPanel extends JPanel
{
    
  public CustomJPanel   ()                      {} 
  public CustomJPanel   (LayoutManager layout)  {super(layout);}
  
  @Override
  protected void paintComponent(Graphics g) 
  {
    super.paintComponent(g);
    
    float[]             fractions   = {0.0f, 0.50f, 1.0f};
    Color[]             colors      = {new Color(100, 200, 100), new Color(50, 200, 50), new Color(20, 125, 20)};
    LinearGradientPaint lgp         = new LinearGradientPaint(0, 0, getWidth(), getHeight(), fractions, colors, CycleMethod.NO_CYCLE);
    Graphics2D g2d                  = (Graphics2D) g;
    
    g2d.setPaint(lgp);
    g2d.fillRect(0, 0, getWidth(), getHeight());
  }      
}
