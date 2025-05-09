package theknife.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 * CustomScrollBar is a subclass of {@link BasicScrollBarUI} that customizes the appearance
 * of a Swing scrollbar. It overrides the painting of the thumb and track, and removes
 * the default increase and decrease buttons.
 * <p>
 * The thumb is drawn as a light green rounded rectangle, while the track is filled
 * with a darker green color. The increase and decrease buttons are replaced with
 * invisible components by returning buttons with zero size.
 * </p>
 * <p>
 * This class can be used to give scrollbars a more modern or themed appearance in
 * Swing-based GUI applications.
 * </p>
 *
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */
public final class CustomScrollBar extends BasicScrollBarUI 
{
  /**
   * Paints the scrollbar thumb, which is the draggable part indicating the current position.
   *
   * @param g           the graphics context used for painting
   * @param c           the component to which the scrollbar belongs
   * @param thumbBounds the bounds (position and size) of the thumb
   */
  @Override
  protected final void    paintThumb          (Graphics g, JComponent c, Rectangle thumbBounds) 
  {
    g.setColor(new Color(135, 255, 135));
    g.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 10, 10);
  }

  /**
   * Paints the scrollbar track, which is the area over which the thumb moves.
   *
   * @param g           the graphics context used for painting
   * @param c           the component to which the scrollbar belongs
   * @param trackBounds the bounds (position and size) of the track
   */
  @Override
  protected final void    paintTrack          (Graphics g, JComponent c, Rectangle trackBounds) 
  {
    g.setColor(new Color(39, 142, 39));
    g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
  }
  
  /**
   * Creates the decrease button for the scrollbar. This implementation returns
   * an invisible button with no size.
   *
   * @param orientation the orientation of the button
   * @return a zero-sized invisible {@link JButton}
   */
  @Override
  protected final JButton createDecreaseButton(int orientation) {return removeButton();}

  /**
   * Creates the increase button for the scrollbar. This implementation returns
   * an invisible button with no size.
   *
   * @param orientation the orientation of the button
   * @return a zero-sized invisible {@link JButton}
   */
  @Override
  protected final JButton createIncreaseButton(int orientation) {return removeButton();}

  /**
   * Returns an invisible JButton with no size, used to suppress the default scrollbar buttons.
   *
   * @return a {@link JButton} with zero preferred, minimum, and maximum size
   */
  private         JButton removeButton        () 
  {
    JButton button         = new JButton();
    button.setPreferredSize (new Dimension(0, 0));
    button.setMinimumSize   (new Dimension(0, 0));
    button.setMaximumSize   (new Dimension(0, 0));
    return button;
  }
}