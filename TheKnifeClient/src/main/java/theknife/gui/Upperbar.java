package theknife.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import simple.logging.LoggerUtils;
import theknife.client.Controller;
import theknife.obj.AppPaths;

/**
 * The Upperbar class represents the upper navigation bar of the application.
 * <p>
 * It contains a search bar, an advanced search button, and a login button.<br>
 * The components are arranged in a {@link BorderLayout} and styled with custom UI elements.<br>
 * This class handles events such as button clicks and text input to interact with the rest of the application.
 * </p>
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */
public final class Upperbar extends javax.swing.JPanel 
{
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Creates a new {@code Upperbar} panel and initializes its components.
     * <p>
     * This constructor also sets the main panel to control the screen transitions using {@link PanelMain#showCard}.
     * </p>
     *
     * @param controller the {@link Controller} class that manages the screen layout
     * @param lbl_title the {@link JLabel} title
     */
    public Upperbar(Controller controller, JLabel lbl_title) 
    {
      initComponents();
      this.controller = controller;
      this.lbl_title  = lbl_title;
      initGUI       ();
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Initialization">
    /**
     * Initializes the graphical user interface (GUI) for the upper bar.
     */
    private void initGUI() 
    {
      initFields    ();
      initUpperbar  ();
      initEvents    ();
    }
    
    /**
     * Initializes all the fields in the upper bar.
     */
    private void initFields()
    {
      pnls_upperbar             = new JPanel[3];
      pnl_searchbar             = new JPanel(new BorderLayout());
      txt_searchbar             = new JTextField();
      lbl_logo                  = new JLabel();
      btn_advancedSearch        = new CustomJLabel("", 'A', true);
      btn_login                 = new CustomJLabel("", 'B', true);
      txt_searchbarRounded      = new JLayer<>(txt_searchbar,      LAYER_UI);
      btn_advancedSearchRounded = new JLayer<>(btn_advancedSearch, LAYER_UI);
      pnl_searchbarRounded      = new JLayer<>(pnl_searchbar,      LAYER_UI);
    }
    
    /**
     * Initializes the layout and appearance of the upper bar.
     * Adds the search bar, advanced search button, and login button to the upper bar.
     */
    private void initUpperbar() 
    {
      
      try {
        logo       = ImageIO.read(AppPaths.getRequiredFile("img", "logo.png"));
        logoScaled = logo.getScaledInstance(WIDTH_WEST_EAST, this.getPreferredSize().height, Image.SCALE_SMOOTH);
      } catch (IOException e) {
        LoggerUtils.logSevereAndThrow("Unable to get the logo image", e);
      }
      
      txt_searchbar.setBorder(BorderFactory.createEmptyBorder(0, PADDING_SEARCHBAR, 0, PADDING_SEARCHBAR));
      txt_searchbar.setFont(this.getFont());
        
      btn_advancedSearch.setBackground(BG_ADVANCED_SEARCH_BTN);
      btn_advancedSearch.setPreferredSize(new Dimension(WIDTH_ADVANCED_SEARCH, pnl_searchbar.getPreferredSize().height));
      btn_advancedSearch.setBorder(null);
      btn_advancedSearch.setOpaque(true);
      btn_advancedSearch.setCustomFontSize(40);
      btn_advancedSearch.setHorizontalAlignment(SwingConstants.CENTER);
        
      btn_login.setBackground           (this.getBackground());
      btn_login.setHorizontalAlignment  (JLabel.CENTER);
      btn_login.setVerticalAlignment    (JLabel.CENTER);
      btn_login.setBorder               (null);
      btn_login.setOpaque               (true);
      btn_login.setCustomFontSize       (50);
        
      for (int i = 0; i < pnls_upperbar.length; i++) 
      {
        pnls_upperbar[i] = new JPanel(new BorderLayout());
        pnls_upperbar[i].setPreferredSize(new Dimension(WIDTH_WEST_EAST, this.getPreferredSize().height));
        pnls_upperbar[i].setBackground   (this.getBackground());
      }
      
      lbl_logo.setBackground(this.getBackground());
      lbl_logo.setPreferredSize(pnls_upperbar[0].getPreferredSize());
      lbl_logo.setIcon(new ImageIcon(logoScaled));
        
      pnls_upperbar[1].setBorder(BorderFactory.createEmptyBorder(PADDING_UPPERBAR[1], PADDING_UPPERBAR[0], PADDING_UPPERBAR[1], PADDING_UPPERBAR[0]));
      pnl_searchbar   .setBackground(Color.WHITE);
      pnl_searchbar   .add(txt_searchbarRounded,      BorderLayout.CENTER);
      pnl_searchbar   .add(btn_advancedSearchRounded, BorderLayout.EAST);
      pnls_upperbar[0].add(lbl_logo);
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
    private void initEvents() 
    {
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
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn_advancedSearch_MouseEntered(e);
            } 
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn_advancedSearch_MouseExited(e);
            }
        });
        
        btn_login.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                btn_login_MouseClicked(e);
            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn_login_MouseEntered(e);
            } 
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn_login_MouseExited(e);
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
            
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                txt_searchbar_KeyReleased(e);
            }
        });
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Event Listeners">
    /**
     * Handles the resize event for the upper bar {@link JPanel}.
     * <p>
     * When resized, resizes the padding of the components.
     * </p>
     * 
     * @param e the component event triggered by resizing the GUI application
     */
    private void upperbar_ComponentResized(java.awt.event.ComponentEvent e) 
    {
        int[] padding = {(int) (pnls_upperbar[1].getWidth() * 0.1), (int) (pnls_upperbar[1].getHeight() * 0.2)};
        pnls_upperbar[1].setBorder(BorderFactory.createEmptyBorder(padding[1], padding[0], padding[1], padding[0]));
        
        padding[0] = (int) (txt_searchbar.getWidth() * 0.03);
        txt_searchbar.setBorder(BorderFactory.createEmptyBorder(0, padding[0], 0, padding[0]));
    }
    
    /**
     * Handles the click event for the advanced search button.
     * <p>
     * When clicked, it switches to the "advancedSearch" screen.
     * </p>
     * 
     * @param e the mouse event triggered by clicking the button 
     */
    private void btn_advancedSearch_MouseClicked(java.awt.event.MouseEvent e) 
    {
      txt_searchbar.setText("");
      controller   .getPanelMain().showCard(Page.ADVANCED_SEARCH);
    }
    
    /**
     * Handles the hover event on the advanced search button {@link JLabel}.
     * 
     * @param e the mouse event triggered by hovering to the button.
     */
    private void btn_advancedSearch_MouseEntered(java.awt.event.MouseEvent e) 
    {
      btn_advancedSearch.setCursor      (new Cursor(Cursor.HAND_CURSOR));
      btn_advancedSearch.setBackground  (btn_advancedSearch.getBackground().darker());
    }
    
    /**
     * Handles the exit hover event on the advanced search button {@link JLabel}.
     * 
     * @param e the mouse event triggered by leaving the cursor from the button
     */
    private void btn_advancedSearch_MouseExited(java.awt.event.MouseEvent e) 
    {
      btn_advancedSearch.setBackground(BG_ADVANCED_SEARCH_BTN);
    }
    
    /**
     * Handles the click event for the login button.
     * <p>
     * When clicked, it switches to the "login" screen.
     * </p>
     * 
     * @param e the mouse event triggered by clicking the button 
     */
    private void btn_login_MouseClicked(java.awt.event.MouseEvent e) 
    {
      if(!lbl_title.getText().equals("All restaurants") || !txt_searchbar .getText().trim().isEmpty())
        resetSearch();
      
      if(btn_login.getCharacter() == LOGIN_ICON)
        controller.getPanelMain().showCard(Page.LOGIN);
      else
      {
        revertUI();
        controller.logout();
      }
    }
    
    /**
     * Handles the hover event on the login button {@link JButton}.
     * 
     * @param e the mouse event triggered by hovering to the button.
     */
    private void btn_login_MouseEntered(java.awt.event.MouseEvent e) 
    {
      btn_login.setCursor     (new Cursor(Cursor.HAND_CURSOR));
      btn_login.setBackground (btn_login.getBackground().darker());
    }
    
    /**
     * Handles the exit hover event on the login button {@link JButton}.
     * 
     * @param e the mouse event triggered by leaving the cursor from the button
     */
    private void btn_login_MouseExited(java.awt.event.MouseEvent e) 
    {
      btn_login.setBackground(this.getBackground());
    }
    
    /**
     * Handles the key typed event for the search bar {@link JTextField}.
     * <p>
     * Prevents the user from typing more than the maximum allowed characters in the search bar.
     * </p>
     * 
     * @param e the key event triggered by typing on the {@link JTextField}
     */
    private void txt_searchbar_KeyTyped(java.awt.event.KeyEvent e) 
    {
      if(txt_searchbar.getText().length() >= MAX_SEARCHBAR_LENGTH)
        e.consume();
    }
    
    /**
     * Handles the CTRL + A key pressed and the flag itself.
     * 
     * @param e the key event triggered by pressing some keys  
     */
    private void txt_searchbar_KeyPressed(java.awt.event.KeyEvent e) 
    {
      if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_A) 
      {
        txt_searchbar.selectAll();
        ctrlA_pressed = true;
      }
    }
    
    /**
     * Handles the key released event for the search bar {@link JTextField}.
     * <p>
     * Prevents a bug caused by using CTRL + A + backspace then typing a {@code Character}
     * </p>
     * 
     * @param e the key event triggered by releasing the key on the {@link JTextField}
     */
    private void txt_searchbar_KeyReleased(java.awt.event.KeyEvent e) 
    {        
      if      (ctrlA_pressed && (e.getKeyCode() == KeyEvent.VK_BACK_SPACE))
      {
        txt_searchbar.setText("");
        ctrlA_pressed = false;
      } 
      else if (ctrlA_pressed && Character.isLetterOrDigit(e.getKeyChar())) 
      {
        txt_searchbar.setText(String.valueOf(e.getKeyChar()));
        ctrlA_pressed = false;
      }
        
      lbl_title .setText         (txt_searchbar.getText().isEmpty() ? "All Restaurants":"Searching...");
      controller.searchRestaurant(txt_searchbar.getText());
      
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * Changes the login button text to work as a logout button
     */   
    public void changeUI()
    {
      btn_login     .setCustomFontSize(75);
      btn_login     .setCharacter   (LOGOUT_ICON);
    }
    
    /**
     * Reverts the logout button text to work as a login button
     */  
    private void revertUI()
    {
      btn_login     .setCustomFontSize(50);
      btn_login     .setCharacter   (LOGIN_ICON);

    }
    
    /**
     * Resets search bar 
     */ 
    private void resetSearch()
    {
      txt_searchbar .setText            ("");
      lbl_title     .setText            ("All Restaurants");
      controller    .searchRestaurant   (txt_searchbar.getText());        
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

        setBackground(new java.awt.Color(76, 197, 102));
        setFont(new java.awt.Font("Consolas", 0, 32)); // NOI18N
        setMinimumSize(new java.awt.Dimension(0, 0));
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
    private final Color              BG_ADVANCED_SEARCH_BTN = new Color(171, 171, 171);
    private final int[]              PADDING_UPPERBAR       = {65, 20};
    private final int                WIDTH_WEST_EAST      = 100;
    private final int                WIDTH_ADVANCED_SEARCH  = 60;
    private final int                PADDING_SEARCHBAR      = 14;
    private final int                ARC                    = 50;
    private final int                MAX_SEARCHBAR_LENGTH   = 64;
    private final char               LOGIN_ICON             = 'B';
    private final char               LOGOUT_ICON            = 'C';
    private final RoundedComponentUI LAYER_UI   = new RoundedComponentUI(ARC);
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Components">
    private       JPanel[]           pnls_upperbar;
    private       JPanel             pnl_searchbar;
    private       JTextField         txt_searchbar;
    private final JLabel             lbl_title;
    private       JLabel             lbl_logo;
    private       CustomJLabel       btn_advancedSearch;
    private       CustomJLabel       btn_login;
    private       JLayer<JComponent> txt_searchbarRounded;
    private       JLayer<JComponent> btn_advancedSearchRounded;
    private       JLayer<JComponent> pnl_searchbarRounded;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Fields">
    private final Controller    controller;
    private       boolean       ctrlA_pressed;
    private       BufferedImage logo; 
    private       Image         logoScaled;
    //</editor-fold>
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
