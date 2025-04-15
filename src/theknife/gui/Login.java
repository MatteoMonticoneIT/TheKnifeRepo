package theknife.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

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
        pnl_grid                 = new JPanel    (new GridBagLayout());
        pnl_btn_login            = new JPanel    (new BorderLayout());
        lbl_title                = new JLabel    (Page.LOGIN);
        btn_login                = new JLabel    ("Login");
        txt_emailUsername        = new JTextField(PLACEHOLDER[0]);
        txt_password             = new JTextField(PLACEHOLDER[1]);
        layerUI                  = new RoundedComponentUI(ARC);
        txt_emailUsernameRounded = new JLayer<>(txt_emailUsername, layerUI);
        txt_passwordRounded      = new JLayer<>(txt_password,      layerUI);
    }
    
    /**
     * Initializes the layout and appearance of the login page.
     */
    private void initLogin() {
        final Color  BG_TEXTFIELD      = new Color(255, 255, 255, 192);
        final Color  FG_TEXTFIELD      = Color.GRAY;
        final Border PADDING_TEXTFIELD = BorderFactory.createEmptyBorder(0, 10, 0, 10);
        
        this.setLayout(new BorderLayout());
        pnl_grid.setBackground(this.getBackground());
        
        txt_emailUsername.setForeground(FG_TEXTFIELD);
        txt_emailUsername.setBackground(BG_TEXTFIELD);
        txt_emailUsername.setBorder    (PADDING_TEXTFIELD);
        txt_password     .setForeground(FG_TEXTFIELD);
        txt_password     .setBackground(BG_TEXTFIELD);
        txt_password     .setBorder    (PADDING_TEXTFIELD);
        
        pnl_btn_login.setBackground(this.getBackground());
        
        btn_login.setBackground(Color.GREEN);
        btn_login.setHorizontalAlignment(JLabel.CENTER);
        btn_login.setVerticalAlignment  (JLabel.CENTER);
        btn_login.setFont(new Font("Consolas", Font.PLAIN, 24));
        btn_login.setOpaque(true);
        
        lbl_title.setBackground(this.getBackground());
        lbl_title.setHorizontalAlignment(JLabel.CENTER);
        lbl_title.setVerticalAlignment  (JLabel.CENTER);
        lbl_title.setFont(new Font("Consolas", Font.BOLD, 48));
        lbl_title.setOpaque(true);
        
        pnl_btn_login.add(btn_login, BorderLayout.CENTER);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx   = 0;
        gbc.gridy   = 0;
        gbc.weightx = 1;
        gbc.fill    = GridBagConstraints.HORIZONTAL;
        gbc.insets  = new Insets(10, 0, 10, 0);
        
        pnl_grid.add(lbl_title, gbc);
        
        gbc.ipady = 30;
        gbc.gridy++;
        pnl_grid.add(txt_emailUsernameRounded, gbc);
        
        gbc.gridy++;
        pnl_grid.add(txt_passwordRounded, gbc);
        
        gbc.ipady = 5;
        gbc.gridy++;
        pnl_grid.add(Box.createVerticalStrut(20), gbc);
        
        gbc.gridy++;
        pnl_grid.add(Box.createVerticalStrut(20), gbc);
        
        gbc.gridy++;
        pnl_grid.add(pnl_btn_login, gbc);
        
        gbc.gridy++;
        pnl_grid.add(Box.createVerticalStrut(20), gbc);
        
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
        
        btn_login.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                btn_login_MouseClicked(e);
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
        
        final int PADDING_BTN_LOGIN = (int) (this.getWidth() * 0.3);
        pnl_btn_login.setBorder(BorderFactory.createEmptyBorder(0, PADDING_BTN_LOGIN, 0, PADDING_BTN_LOGIN));
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
    private void btn_login_MouseClicked(java.awt.event.MouseEvent e) {
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

    //<editor-fold defaultstate="collapsed" desc="Consts">
    private final String[] PLACEHOLDER = {
        "Your email or username",
        "Your password"
    };
    private final int ARC = 30;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Components">
    private final PanelMain          pnl_main;
    private       JPanel             pnl_grid;
    private       JPanel             pnl_btn_login;
    private       JLabel             lbl_title;
    private       JLabel             btn_login;
    private       JTextField         txt_emailUsername;
    private       JTextField         txt_password;
    private       RoundedComponentUI layerUI;
    private       JLayer<JComponent> txt_emailUsernameRounded;
    private       JLayer<JComponent> txt_passwordRounded;
    //</editor-fold>
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
