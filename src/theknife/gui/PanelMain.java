package theknife.gui;

import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 * @author Matteo Monticone     761701 (CO)
 * @author Damiano De Mutiis    ------ (CO)
 * @author Matteo Porto Bonacci ------ (CO)
 * @author Mattia Tamburo       ------ (CO)
 */

public class PanelMain {
    private JPanel panel;
    private CardLayout card;

    public PanelMain(JPanel panel, CardLayout card) {
        setCard(card);
        setPanel(panel);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel == null ? new JPanel(this.getCard()) : panel;
    }

    public CardLayout getCard() {
        return card;
    }

    public void setCard(CardLayout card) {
        this.card = card == null ? new CardLayout() : card;
    }
    
    public void showCard(String name) {
        this.getCard().show(this.getPanel(), name);
    }
    
}
