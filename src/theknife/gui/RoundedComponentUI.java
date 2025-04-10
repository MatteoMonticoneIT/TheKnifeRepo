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
 * @author Matteo Monticone     761701 (CO)
 * @author Damiano De Mutiis    ------ (CO)
 * @author Matteo Porto Bonacci ------ (CO)
 * @author Mattia Tamburo       ------ (CO)
 */

public class RoundedComponentUI extends LayerUI<JComponent> {
    private int arc;

    public RoundedComponentUI(int arc) {
        setArc(arc);
    }

    public int getArc() {
        return arc;
    }

    public void setArc(int arc) {
        this.arc = arc;
    }
    
    @Override
    public void paint(Graphics g, JComponent c) {
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
