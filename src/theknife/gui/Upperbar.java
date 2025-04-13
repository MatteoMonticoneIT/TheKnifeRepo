package theknife.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 * The Upperbar class represents the upper navigation bar of the application.
 * <p>
 * It contains a search bar, an advanced search button, and a login button. The components
 * are arranged in a {@link BorderLayout} and styled with custom UI elements. This class
 * handles events such as button clicks and text input to interact with the rest of the application.
 * </p>
 * 
 * @author Matteo Monticone     761701 (CO)
 * @author Damiano De Mutiis    ------ (CO)
 * @author Matteo Porto Bonacci ------ (CO)
 * @author Mattia Tamburo       ------ (CO)
 */
public final class Upperbar extends javax.swing.JPanel {

    /**
     * Creates a new {@code Upperbar} panel and initializes its components.
     * <p>
     * This constructor also sets the main panel to control the screen transitions
     * using {@link PanelMain#showCard}.
     * </p>
     * 
     * @param main the main panel that manages the screen layout
     */
    public Upperbar(PanelMain main) {
        initComponents();
        pnl_main = main;
        initGUI();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Initialization">
    /**
     * Initializes the graphical user interface (GUI) for the upper bar.
     */
    private void initGUI() {
        initFields();
        initUpperbar();
        initEvents();
    }
    
    /**
     * Initializes all the fields in the upper bar.
     */
    private void initFields() {
        pnls_upperbar      = new JPanel[3]; //0 = west; 1 = center; 2 = east;
        pnl_searchbar      = new JPanel(new BorderLayout());
        txt_searchbar      = new JTextField();
        btn_advancedSearch = new JLabel();
        btn_login          = new JLabel("Accedi");
    }
    
    /**
     * Initializes the layout and appearance of the upper bar.
     * Adds the search bar, advanced search button, and login button to the upper bar.
     */
    private void initUpperbar() {
        txt_searchbar.setBorder(BorderFactory.createEmptyBorder(0, PADDING_SEARCHBAR, 0, PADDING_SEARCHBAR));
        txt_searchbar.setFont(new Font("Consolas", Font.PLAIN, 32));
        
        btn_advancedSearch.setPreferredSize(new Dimension(WIDTH_ADVANCED_SEARCH, pnl_searchbar.getPreferredSize().height));
        btn_advancedSearch.setBorder(null);
        btn_advancedSearch.setOpaque(true);
        
        btn_login.setBorder(null);
        btn_login.setOpaque(true);
        btn_login.setHorizontalAlignment(JLabel.CENTER);
        btn_login.setVerticalAlignment  (JLabel.CENTER);
        
        for (int i = 0; i < pnls_upperbar.length; i++) {
            pnls_upperbar[i] = new JPanel(new BorderLayout());
            pnls_upperbar[i].setPreferredSize(new Dimension(WIDTH_SIDEBUTTONS, this.getPreferredSize().height));
            pnls_upperbar[i].setBackground   (this.getBackground());
            pnls_upperbar[i].setBorder       (new LineBorder(Color.BLACK, 10));
        }
        
        RoundedComponentUI layerUI = new RoundedComponentUI(ARC);
        
        JLayer<JComponent> txt_searchbarRounded      = new JLayer<>(txt_searchbar,      layerUI);
        JLayer<JComponent> btn_advancedSearchRounded = new JLayer<>(btn_advancedSearch, layerUI);
        JLayer<JComponent> pnl_searchbarRounded      = new JLayer<>(pnl_searchbar,      layerUI);
        
        pnls_upperbar[1].setBorder(BorderFactory.createEmptyBorder(PADDING_UPPERBAR[1], PADDING_UPPERBAR[0], PADDING_UPPERBAR[1], PADDING_UPPERBAR[0]));
        pnl_searchbar   .setBackground(Color.WHITE);
        pnl_searchbar   .add(txt_searchbarRounded,      BorderLayout.CENTER);
        pnl_searchbar   .add(btn_advancedSearchRounded, BorderLayout.EAST);
        pnls_upperbar[1].add(pnl_searchbarRounded);
        pnls_upperbar[2].add(btn_login);
        
        this.setLayout(new BorderLayout());
        this.add(pnls_upperbar[0], BorderLayout.WEST);
        this.add(pnls_upperbar[1], BorderLayout.CENTER);
        this.add(pnls_upperbar[2], BorderLayout.EAST);
    }
    
    /**
     * Initializes the event listeners for components in the upper bar.
     * <p>
     * Listens for mouse clicks on the advanced search button, login button, and text input in the search bar.
     * </p>
     */
    private void initEvents() {
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                upperbar_ComponentResized(e);
            }
        });
        
        btn_advancedSearch.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                btn_advancedSearch_MouseClicked(e);
            }
        });
        
        btn_login.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                btn_login_MouseClicked(e);
            }
        });
        
        txt_searchbar.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                txt_searchbar_KeyTyped(e);
            }
            
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                txt_searchbar_KeyPressed(e);
            }
        });
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Event Listeners">
    /**
     * Handles the resize event for the upper bar {@link JPanel}.
     * When resized, resizes the padding of the components.
     */
    private void upperbar_ComponentResized(java.awt.event.ComponentEvent e) {
        int[] padding = {(int) (pnls_upperbar[1].getWidth() * 0.1), (int) (pnls_upperbar[1].getHeight() * 0.2)}; //0 = width; 1 = height;
        pnls_upperbar[1].setBorder(BorderFactory.createEmptyBorder(padding[1], padding[0], padding[1], padding[0]));
        
        padding[0] = (int) (txt_searchbar.getWidth() * 0.03);
        txt_searchbar.setBorder(BorderFactory.createEmptyBorder(0, padding[0], 0, padding[0]));
    }
    
    /**
     * Handles the click event for the advanced search button.
     * When clicked, it switches to the "advancedSearch" screen.
     */
    private void btn_advancedSearch_MouseClicked(java.awt.event.MouseEvent e) {
        System.out.println("click");
    }
    
    /**
     * Handles the click event for the login button.
     * When clicked, it switches to the "login" screen.
     */
    private void btn_login_MouseClicked(java.awt.event.MouseEvent e) {
        pnl_main.showCard("login");
    }
    
    /**
     * Handles the key typed event for the search bar.
     * Prevents the user from typing more than the maximum allowed characters in the search bar.
     */
    private void txt_searchbar_KeyTyped(java.awt.event.KeyEvent e) {
        if (txt_searchbar.getText().length() >= MAX_SEARCHBAR_LENGTH)
            e.consume();
        
        if (ctrlA_Pressed && Character.isLetterOrDigit(e.getKeyChar())) {
            txt_searchbar.setText(String.valueOf(e.getKeyChar()));
            ctrlA_Pressed = false;
        }
    }
    
    private void txt_searchbar_KeyPressed(java.awt.event.KeyEvent e) {
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_A) {
            txt_searchbar.selectAll();
            ctrlA_Pressed = true;
        }
    }
    //</editor-fold>
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(153, 255, 153));
        setMinimumSize(new java.awt.Dimension(854, 100));
        setPreferredSize(new java.awt.Dimension(854, 100));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 854, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    //<editor-fold defaultstate="collapsed" desc="Consts">
    private final int   WIDTH_SIDEBUTTONS     = 100;
    private final int   WIDTH_ADVANCED_SEARCH = 60;
    private final int[] PADDING_UPPERBAR      = {65, 20}; //0 = width; 1 = height
    private final int   PADDING_SEARCHBAR     = 14;
    private final int   ARC                   = 50;
    private final int   MAX_SEARCHBAR_LENGTH  = 64;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Components">
    private final PanelMain  pnl_main;
    private       JPanel[]   pnls_upperbar;
    private       JPanel     pnl_searchbar;
    private       JTextField txt_searchbar;
    private       JLabel     btn_advancedSearch;
    private       JLabel     btn_login;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Fields">
    private boolean ctrlA_Pressed;
    //</editor-fold>
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
