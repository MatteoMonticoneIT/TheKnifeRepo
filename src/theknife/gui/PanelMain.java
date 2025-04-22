package theknife.gui;

import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 * A utility class that wraps a {@link JPanel} managed by a {@link CardLayout}, allowing for dynamic switching between different views (cards) in a GUI.
 * <p>
 * This class simplifies the management of multiple components displayed one at a time within the same container, by providing convenient access to the panel and layout.
 * </p>
 * 
 * <p><b>Usage example:</b></p>
 * <pre>{@code
 * PanelMain panelMain = new PanelMain();
 * panelMain.getPanel().add(new LoginPanel(), "login");
 * panelMain.getPanel().add(new DashboardPanel(), "dashboard");
 * panelMain.showCard("login");
 * }</pre>
 * 
 * @author Matteo Monticone     761701 (CO)
 * @author Damiano De Mutiis    ------ (CO)
 * @author Matteo Porto Bonacci ------ (CO)
 * @author Mattia Tamburo       ------ (CO)
 */
public final class PanelMain {
    private final JPanel panel;
    private final CardLayout card;
    
    /**
     * Constructs a new {@code PanelMain} with a {@link CardLayout}-managed {@code JPanel}.
     */
    public PanelMain() {
        this.card  = new CardLayout();
        this.panel = new JPanel(this.getCard());
    }

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
    
    /**
     * Shows the card with the specified name.
     *
     * @param name the name of the component to be shown
     */
    public final void showCard(String name) {
        this.getCard().show(this.getPanel(), name);
    }
    
}
