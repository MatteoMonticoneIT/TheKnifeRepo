package theknife.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 * This class sets up a custom scroll bar<br>
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */

public class CustomScrollBar extends BasicScrollBarUI 
{
  @Override
  protected void    paintThumb          (Graphics g, JComponent c, Rectangle thumbBounds) 
  {
    g.setColor(new Color(135, 255, 135));
    g.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 10, 10);
  }

  @Override
  protected void    paintTrack          (Graphics g, JComponent c, Rectangle trackBounds) 
  {
    g.setColor(new Color(39, 142, 39));
    g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
  }

  @Override
  protected JButton createDecreaseButton(int orientation) {return removeButton();}

  @Override
  protected JButton createIncreaseButton(int orientation) {return removeButton();}

  private   JButton removeButton        () 
  {
    JButton button         = new JButton();
    button.setPreferredSize (new Dimension(0, 0));
    button.setMinimumSize   (new Dimension(0, 0));
    button.setMaximumSize   (new Dimension(0, 0));
    return button;
  }
}