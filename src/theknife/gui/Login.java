package theknife.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A panel that represents the login screen in the application.
 * <p>
 * This class contains the graphical elements for the login interface, including a 
 * button that switches the view to the home screen when clicked. It is managed 
 * by the {@link PanelMain} class using a {@link CardLayout}.
 * </p>
 * 
 * @author Matteo Monticone     761701 (CO)
 * @author Damiano De Mutiis    ------ (CO)
 * @author Matteo Porto Bonacci ------ (CO)
 * @author Mattia Tamburo       ------ (CO)
 */
public final class Login extends javax.swing.JPanel {

    /**
     * Creates a new {@code Login} panel and initializes its components.
     * <p>
     * This constructor also sets the main panel to control the screen transitions
     * using {@link PanelMain#showCard}.
     * </p>
     * 
     * @param main the main panel that manages the screen layout
     */
    public Login(PanelMain main) {
        initComponents();
        pnl_main = main;
        initGUI();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Initialization">
    /**
     * Initializes the graphical user interface (GUI) for the login page.
     */
    private void initGUI() {
        initFields();
        initLogin();
        initEvents();
    }
    
    /**
     * Initializes the basic fields of the login panel.
     */
    private void initFields() {
        pnl_grid          = new JPanel(new GridLayout(6, 1, 0, 10));
        pnl_title         = new JPanel(new BorderLayout());
        lbl_title         = new JLabel(Page.LOGIN);
        btn_home          = new JLabel("Example");
        txt_emailUsername = new JTextField(PLACEHOLDER[0]);
        txt_password      = new JTextField(PLACEHOLDER[1]);
    }
    
    /**
     * Initializes the layout and appearance of the login page.
     */
    private void initLogin() {
        this.setLayout(new BorderLayout());
        pnl_grid .setBackground(this.getBackground());
        pnl_title.setBackground(this.getBackground());
        
        txt_emailUsername.setForeground(Color.GRAY);
        txt_password     .setForeground(Color.GRAY);
        
        btn_home.setPreferredSize(new Dimension(this.getWidth(), 100));
        btn_home.setBackground(Color.GREEN);
        btn_home.setHorizontalAlignment(JLabel.CENTER);
        btn_home.setVerticalAlignment  (JLabel.CENTER);
        btn_home.setOpaque(true);
        
        lbl_title.setBackground(this.getBackground());
        lbl_title.setFont(new Font("Consolas", Font.BOLD, 48));
        lbl_title.setHorizontalAlignment(JLabel.CENTER);
        lbl_title.setVerticalAlignment  (JLabel.CENTER);
        lbl_title.setOpaque(true);
        
        pnl_title.setPreferredSize(new Dimension(this.getPreferredSize().width, 100));
        pnl_title.add(lbl_title, BorderLayout.CENTER);
        
        pnl_grid.add(txt_emailUsername);
        pnl_grid.add(txt_password);
        pnl_grid.add(new JLabel());
        pnl_grid.add(new JLabel());
        pnl_grid.add(btn_home);
        pnl_grid.add(new JLabel());
        
        this.add(pnl_title, BorderLayout.NORTH);
        this.add(pnl_grid,  BorderLayout.CENTER);
    }
    
    /**
     * Sets up event listeners for user interaction.
     */
    private void initEvents() {
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                login_ComponentResized(e);
            }
        });
        
        txt_emailUsername.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                txt_emailUsername_FocusGained(e);
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                txt_emailUsername_FocusLost(e);
            }
        });
        
        txt_password.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                txt_password_FocusGained(e);
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                txt_password_FocusLost(e);
            }
        });
        
        btn_home.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                btn_home_MouseClicked(e);
            }
        });
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Event Listeners">
    /**
     * Handles the resize event for the login {@link JPanel}.
     * When resized, resizes the padding of the components.
     */
    private void login_ComponentResized(java.awt.event.ComponentEvent e) {
        int[] padding = {(int) (this.getWidth() * 0.1), (int) (this.getHeight() * 0.1)}; //0 = width; 1 = height;
        this.setBorder(BorderFactory.createEmptyBorder(padding[1], padding[0], padding[1], padding[0]));
    }
    
    /**
     * Handles the gaining focus event on the email or username {@link JTextField}
     * @param e the mouse event triggered by gaining focus
     */
    private void txt_emailUsername_FocusGained(java.awt.event.FocusEvent e) {
        if (txt_emailUsername.getText().equals(PLACEHOLDER[0])) {
            txt_emailUsername.setText("");
            txt_emailUsername.setForeground(Color.BLACK);
        }
    }
    
    /**
     * Handles the losing focus event on the email or username {@link JTextField}
     * @param e the mouse event triggered by gaining focus
     */
    private void txt_emailUsername_FocusLost(java.awt.event.FocusEvent e) {
        if (txt_emailUsername.getText().isEmpty()) {
            txt_emailUsername.setText(PLACEHOLDER[0]);
            txt_emailUsername.setForeground(Color.GRAY);
        }
    }
    
    /**
     * Handles the gaining focus event on the password {@link JTextField}
     * @param e the mouse event triggered by gaining focus
     */
    private void txt_password_FocusGained(java.awt.event.FocusEvent e) {
        if (txt_password.getText().equals(PLACEHOLDER[1])) {
            txt_password.setText("");
            txt_password.setForeground(Color.BLACK);
        }
    }
    
    /**
     * Handles the losing focus event on the password {@link JTextField}
     * @param e the mouse event triggered by gaining focus
     */
    private void txt_password_FocusLost(java.awt.event.FocusEvent e) {
        if (txt_password.getText().isEmpty()) {
            txt_password.setText(PLACEHOLDER[1]);
            txt_password.setForeground(Color.GRAY);
        }
    }
    
    /**
     * Handles the click event on the home {@link JLabel}.
     * <p>
     * When the button is clicked, the view transitions to the {@code Home} screen.
     * </p>
     *
     * @param e the mouse event triggered by clicking the button
     */
    private void btn_home_MouseClicked(java.awt.event.MouseEvent e) {
        pnl_main.showCard(Page.HOME);
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

        setBackground(new java.awt.Color(153, 255, 102));
        setMinimumSize(new java.awt.Dimension(854, 480));
        setPreferredSize(new java.awt.Dimension(854, 480));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 854, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    //<editor-fold defaultstate="collapsed" desc="Components">
    private final String[] PLACEHOLDER = {
        "Your email or username",
        "Your password"
    };
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Components">
    private final PanelMain  pnl_main;
    private       JPanel     pnl_grid;
    private       JPanel     pnl_title;
    private       JLabel     lbl_title;
    private       JLabel     btn_home;
    private       JTextField txt_emailUsername;
    private       JTextField txt_password;
    //</editor-fold>
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
