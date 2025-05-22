package theknife.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import theknife.Controller;
import theknife.obj.review.Review;

/**
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */

public final class AddResponse extends javax.swing.JPanel 
{
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Creates a new {@code AddResponse} panel and initializes its components.
     * <p>
     * This constructor also sets the {@link Review} used to insert the response in it. 
     * </p>
     *
     * @param controller the {@link Controller} class that manages the screen layout
     * @param review the {@link Review} class that represents the review
     */
    public AddResponse(Controller controller, Review review) 
    {
      initComponents();
      this.controller = controller;
      this.review     = review;
      initGUI       ();
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Initialization">
    /**
     * Initializes the graphical user interface (GUI) for the {@code AddResponse} page.
     */
    private void initGUI()
    {
        initFields();
        initAddResponse();
        initEvents();
    }
    
    /**
     * Initializes the basic fields of the {@code AddResponse} panel.
     */
    private void initFields() {
        pnl_btns                 = new JPanel(new GridLayout(1, 2, 10, 10));
        pnl_btn_add              = new JPanel(new BorderLayout());
        pnl_btn_back             = new JPanel(new BorderLayout());
        lbl_title                = new JLabel(TITLE);
        btn_add                  = new JLabel(ADD_RESPONSE);
        btn_back                 = new JLabel(BACK);
        btn_addRounded           = new JLayer<>(btn_add,  BTN_LAYERUI);
        btn_backRounded          = new JLayer<>(btn_back, BTN_LAYERUI);
    }
    
    /**
     * Initializes the layout and appearance of the home page.
     */
    private void initAddResponse() {
        this.setLayout(new BorderLayout());
        
        pnl_btns.setBackground(BG_PNL_BTNS);
        pnl_btns.setBorder    (BorderFactory.createEmptyBorder());
        
        pnl_btn_add .setBackground(pnl_btns.getBackground());
        pnl_btn_back.setBackground(pnl_btns.getBackground());
        
        btn_add.setBackground         (BG_ADDRESPONSE_BTN);
        btn_add.setForeground         (FG_DEFAULT);
        btn_add.setHorizontalAlignment(JLabel.CENTER);
        btn_add.setVerticalAlignment  (JLabel.CENTER);
        btn_add.setFont               (this.getFont());
        btn_add.setOpaque             (true);
        
        btn_back.setBackground         (BG_BACK_BTN);
        btn_back.setForeground         (FG_DEFAULT);
        btn_back.setHorizontalAlignment(JLabel.CENTER);
        btn_back.setVerticalAlignment  (JLabel.CENTER);
        btn_back.setFont               (this.getFont());
        btn_back.setOpaque             (true);
        
        pnl_btn_add .add(btn_addRounded, BorderLayout.CENTER);
        pnl_btn_back.add(btn_backRounded,        BorderLayout.CENTER);
        
        pnl_btns.setPreferredSize(new Dimension(this.getWidth(), PNL_BTNS_HEIGHT));
        pnl_btns.add(pnl_btn_add,  BorderLayout.CENTER);
        pnl_btns.add(pnl_btn_back, BorderLayout.EAST);
        
        this.add(pnl_btns, BorderLayout.SOUTH);
    }
    
    /**
     * Sets up event listeners for user interaction.
     */
    private void initEvents() {
        btn_back.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                btn_back_MouseClicked(e);
            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn_back_MouseEntered(e);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn_back_MouseExited(e);
            }
        });
        
        btn_add.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                btn_add_MouseClicked(e);
            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn_add_MouseEntered(e);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn_add_MouseExited(e);
            }
        });
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Event Listeners">
    /**
     * Handles the click event on the back button {@link JLabel}.
     * <p>
     * When the button is clicked, the view switches to the {@code Home} screen canceling the login procedure.
     * </p>
     * 
     * @param e the mouse event triggered by clicking the button 
     */
    private void btn_back_MouseClicked(java.awt.event.MouseEvent e) {
        controller.getPanelMain().showCard(Page.REVIEW);
        controller.getPanelMain().getPanel().remove(this);
    }
    
    /**
     * Handles the hover event on the back button {@link JLabel}.
     * 
     * @param e the mouse event triggered by hovering to the button
     */
    private void btn_back_MouseEntered(java.awt.event.MouseEvent e) {
        btn_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn_back.setBackground(btn_back.getBackground().darker());
    }
    
    /**
     * Handles the exit hover event on the back button {@link JLabel}.
     * 
     * @param e the mouse event triggered by leaving the cursor from the button
     */
    private void btn_back_MouseExited(java.awt.event.MouseEvent e) {
        btn_back.setBackground(BG_BACK_BTN);
    }
    
    /**
     * Handles the click event on the add response button {@link JLabel}.
     * <p>
     * When the button is clicked, the filters will be applied and the view switches to the {@code Home} screen.
     * </p>
     * 
     * @param e the mouse event triggered by clicking the button 
     */
    private void btn_add_MouseClicked(java.awt.event.MouseEvent e) {
        
    }
    
    /**
     * Handles the hover event on the add response button {@link JLabel}.
     * 
     * @param e the mouse event triggered by hovering to the button
     */
    private void btn_add_MouseEntered(java.awt.event.MouseEvent e) {
        btn_add.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn_add.setBackground(btn_add.getBackground().darker());
    }
    
    /**
     * Handles the exit hover event on the add response button {@link JLabel}.
     * 
     * @param e the mouse event triggered by leaving the cursor from the button
     */
    private void btn_add_MouseExited(java.awt.event.MouseEvent e) {
        btn_add.setBackground(BG_ADDRESPONSE_BTN);
    }
    //</editor-fold>

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(41, 197, 87));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    //<editor-fold defaultstate="collapsed" desc="Consts">
    private final Color              FG_DEFAULT                = Color.BLACK;
    private final Color              BG_ADDRESPONSE_BTN        = new Color(0, 255, 0, 192);
    private final Color              BG_BACK_BTN               = new Color(255, 64, 0, 192);
    private final Color              BG_STAR                   = new Color(255, 215, 0);
    private final Color              BG_PNL_BTNS               = new Color(94, 168, 69);
    private final Border             PADDING_LBL               = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    private final Border             PADDING_TXT               = BorderFactory.createEmptyBorder(3, 3, 3, 3);
    private final Border             BORDER_PNL                = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
    private final String             TITLE                     = "Response";
    private final String             ADD_RESPONSE              = "Add";
    private final String             BACK                      = "Back";
    private final char               FULL_STAR                 = 'C';
    private final int                NORTH_CONTENT_HEIGHT      = 80;
    private final int                ARC                       = 50;
    private final int                PNL_BTNS_HEIGHT           = 80;
    private final int                LBL_RATING_WIDTH          = 125;
    private final RoundedComponentUI BTN_LAYERUI               = new RoundedComponentUI(ARC);
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Components">
    private JPanel             pnl_btns;
    private JPanel             pnl_btn_add;
    private JPanel             pnl_btn_back;
    private JScrollPane        scrlPnl_content;
    private JLabel             lbl_title;
    private JLabel             btn_add;
    private JLabel             btn_back;
    private JTextArea          txt_content;
    private JLayer<JComponent> btn_addRounded;
    private JLayer<JComponent> btn_backRounded;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Fields">
    private final Controller controller;
    private final Review     review;
    //</editor-fold>
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
