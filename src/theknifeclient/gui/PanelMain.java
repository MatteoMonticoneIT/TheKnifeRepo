package theknife.gui;

import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 * A utility class that wraps a {@link JPanel} managed by a {@link CardLayout}, allowing for dynamic switching between different views (cards) in a GUI.
 * <p>
 * This class simplifies the management of multiple components displayed one at a time within the same container, by providing convenient access to the panel and layout.
 * </p>
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */
public final class PanelMain {
    //<editor-fold defaultstate="collapsed" desc="Components">
    /**
     * The {@code PanelMain} {@link JPanel}
     */
    private final JPanel panel;
    
    /**
     * The {@code PanelMain} {@link CardLayout}
     */
    private final CardLayout card;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Constructs a new {@code PanelMain} with a {@link CardLayout}-managed {@code JPanel}.
     */
    public PanelMain() {
        this.card  = new CardLayout();
        this.panel = new JPanel(this.getCard());
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters">
    /**
     * Returns the main {@code JPanel} container.
     *
     * @return the panel managed by this class
     */
    public final JPanel getPanel() {
        return panel;
    }
    
    /**
     * Returns the {@code CardLayout} used to manage the panel's content.
     *
     * @return the card layout manager
     */
    public final CardLayout getCard() {
        return card;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * Shows the card with the specified name.
     *
     * @param name the name of the component to be shown
     */
    public final void showCard(String name) {
        this.getCard().show(this.getPanel(), name);
    }
    //</editor-fold>
}
