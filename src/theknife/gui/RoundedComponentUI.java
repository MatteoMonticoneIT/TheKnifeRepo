package theknife.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JComponent;
import javax.swing.JLayer;
import javax.swing.plaf.LayerUI;


/**
 * A custom {@link LayerUI} implementation that applies a rounded clipping mask to the wrapped {@link JComponent}, allowing for smooth rounded corners.
 * <p>
 * This UI delegate can be used with a {@code JLayer} to visually enhance components without modifying their internal rendering logic.
 * </p>
 * 
 * @author Matteo Monticone     761701 (CO)
 * @author Damiano De Mutiis    ------ (CO)
 * @author Matteo Porto Bonacci ------ (CO)
 * @author Mattia Tamburo       ------ (CO)
 */
public final class RoundedComponentUI extends LayerUI<JComponent> {
    private int arc;

    /**
     * Constructs a new {@code RoundedComponentUI} with the specified corner radius.
     *
     * @param arc the corner arc radius in pixels
     */
    public RoundedComponentUI(int arc) {
        this.setArc(arc);
    }

    /**
     * Returns the corner arc radius used for rounding the component.
     *
     * @return the arc radius in pixels
     */
    public final int getArc() {
        return arc;
    }

    /**
     * Sets the corner arc radius used for rounding the component.
     *
     * @param arc the arc radius in pixels
     */
    public final void setArc(int arc) {
        this.arc = arc;
    }
    
    /**
     * Overrides the {@code paint} method from {@link javax.swing.plaf.LayerUI LayerUI}{@code <JComponent>}.
     * <p>
     * Applies a rounded clipping shape to the wrapped component before painting, allowing for custom rendering effects.<br>
     * </p>
     * For more information on customizing the layer behavior, see {@link javax.swing.plaf.LayerUI LayerUI}{@code <JComponent>}.
     * 
     * @param g the {@code Graphics} context to use for painting
     * @param c the component being painted (expected to be a {@code JLayer})
     */
    @Override
    public final void paint(Graphics g, JComponent c) {
        Graphics2D g2D = (Graphics2D) g.create();
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        JLayer<?> layer = (JLayer<?>) c;
        JComponent view = (JComponent) layer.getView();
        
        Shape shape = new RoundRectangle2D.Double(0, 0, view.getWidth(), view.getHeight(), this.getArc(), this.getArc());
        g2D.setClip(shape);
        
        super.paint(g2D, c);
        g2D.dispose();
    }
    
}
