package theknife.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import theknife.Controller;
import theknife.obj.InputPattern;
import theknife.obj.user.Restaurateur;

/**
 * A panel that represents the register screen in the application.
 * <p>
 * This class contains the graphical elements for the register interface, including a button that switches the view to the login screen when clicked.<br>
 * It is managed by the {@link PanelMain} class using a {@link CardLayout}.
 * </p>
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */
public class RegisterRestaurateur extends CustomJPanel 
{

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Creates a new {@code RegisterRestaurateur} {@link JPanel} and initializes its components.
     * <p>
     * This constructor also sets the main panel to control the screen transitions using {@link PanelMain#showCard}.
     * </p>
     *
     * @param controller the {@link Controller} class that manages the screen layout
     * @param page to let CustomJPanel know which page is it
     */
    public RegisterRestaurateur(Controller controller, String page) 
    {
      super         (page);
      initComponents();
      this.controller = controller;
      initGUI       ();
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Initialization">
    /**
     * Initializes the graphical user interface (GUI) for the {@code Register} page.
     */
    private void initGUI() 
    {
      initFields();
      initRegister();
      initEvents();
    }
    
    /**
     * Initializes the basic fields of the {@code Register} panel.
     */
    private void initFields() 
    {
      pnl_grid              = new JPanel(new GridBagLayout());
      pnl_password          = new JPanel(new GridBagLayout());
      pnl_birthDateLocation = new JPanel(new GridBagLayout());
      pnl_btn_register      = new JPanel(new BorderLayout());
      pnl_btn_cancel        = new JPanel(new BorderLayout());
      lbl_title             = new JLabel(Page.REGISTER_RESTAURATEUR);
      btn_register          = new JLabel(Page.REGISTER);
      btn_cancel            = new JLabel(CANCEL);
      txt_firstName         = new JTextField(PLACEHOLDER[0]);
      txt_lastName          = new JTextField(PLACEHOLDER[1]);
      txt_birthDate         = new JTextField(PLACEHOLDER[2]);
      txt_location          = new JTextField(PLACEHOLDER[3]);
      txt_email             = new JTextField(PLACEHOLDER[4]);
      txt_username          = new JTextField(PLACEHOLDER[5]);
      txt_password          = new JPasswordField(PLACEHOLDER[6]);
      chkbx_seePassword     = new CustomJCheckBox();
      txt_firstNameRounded  = new JLayer<>(txt_firstName, TXT_LAYER_UI);
      txt_lastNameRounded   = new JLayer<>(txt_lastName,  TXT_LAYER_UI);
      txt_birthDateRounded  = new JLayer<>(txt_birthDate,  TXT_LAYER_UI);
      txt_locationRounded   = new JLayer<>(txt_location,  TXT_LAYER_UI);
      txt_emailRounded      = new JLayer<>(txt_email,     TXT_LAYER_UI);
      txt_usernameRounded   = new JLayer<>(txt_username,  TXT_LAYER_UI);
      txt_passwordRounded   = new JLayer<>(txt_password,  TXT_LAYER_UI);
      btn_registerRounded   = new JLayer<>(btn_register,  BTN_LAYER_UI);
      btn_cancelRounded     = new JLayer<>(btn_cancel,    BTN_LAYER_UI); 
    }
    
    /**
     * Initializes the layout and appearance of the {@code Register} page.
     */
    private void initRegister() 
    {
        this.setLayout(new BorderLayout());
        
        pnl_grid               .setOpaque(false);
        pnl_password           .setOpaque(false);
        pnl_birthDateLocation  .setOpaque(false);
        pnl_btn_cancel         .setOpaque(false);
        pnl_btn_register       .setOpaque(false);
        
        lbl_title.setBackground(this.getBackground());
        lbl_title.setForeground(FG_DEFAULT);
        lbl_title.setHorizontalAlignment(JLabel.CENTER);
        lbl_title.setVerticalAlignment  (JLabel.CENTER);
        lbl_title.setFont(new Font(this.getFont().getFontName(), this.getFont().getStyle(), 32));
        lbl_title.setOpaque(false);
        
        txt_firstName.setBackground(BG_TEXTFIELD);
        txt_firstName.setForeground(FG_PLACEHOLDER);
        txt_firstName.setBorder    (PADDING_TEXTFIELD);
        
        txt_lastName.setBackground(BG_TEXTFIELD);
        txt_lastName.setForeground(FG_PLACEHOLDER);
        txt_lastName.setBorder    (PADDING_TEXTFIELD);
        
        txt_birthDate.setBackground(BG_TEXTFIELD);
        txt_birthDate.setForeground(FG_PLACEHOLDER);
        txt_birthDate.setBorder    (PADDING_TEXTFIELD);
        
        txt_location.setBackground(BG_TEXTFIELD);
        txt_location.setForeground(FG_PLACEHOLDER);
        txt_location.setBorder    (PADDING_TEXTFIELD);
        
        txt_email.setBackground(BG_TEXTFIELD);
        txt_email.setForeground(FG_PLACEHOLDER);
        txt_email.setBorder    (PADDING_TEXTFIELD);
        
        txt_username.setBackground(BG_TEXTFIELD);
        txt_username.setForeground(FG_PLACEHOLDER);
        txt_username.setBorder    (PADDING_TEXTFIELD);
        
        chkbx_seePassword.setSelected   (true);
        chkbx_seePassword.setCharacter  (EYE_OFF);
        
        txt_password.setBackground  (BG_TEXTFIELD);
        txt_password.setForeground  (FG_PLACEHOLDER);
        txt_password.setBorder      (PADDING_TEXTFIELD);
        txt_password.setEchoChar    ((char) 0);
        
        btn_register.setBackground          (BG_REGISTER_BTN);
        btn_register.setForeground          (FG_DEFAULT);
        btn_register.setHorizontalAlignment (JLabel.CENTER);
        btn_register.setVerticalAlignment   (JLabel.CENTER);
        btn_register.setFont                (this.getFont());
        btn_register.setOpaque              (true);
        
        btn_cancel.setBackground            (BG_CANCEL_BTN);
        btn_cancel.setForeground            (FG_DEFAULT);
        btn_cancel.setHorizontalAlignment   (JLabel.CENTER);
        btn_cancel.setVerticalAlignment     (JLabel.CENTER);
        btn_cancel.setFont                  (this.getFont());
        btn_cancel.setOpaque                (true);
        
        pnl_btn_register.add(btn_registerRounded, BorderLayout.CENTER);
        pnl_btn_cancel  .add(btn_cancelRounded,   BorderLayout.CENTER);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx     = 0;
        gbc.gridy     = 0;
        gbc.gridwidth = 2;
        gbc.weightx   = 1;
        gbc.fill      = GridBagConstraints.HORIZONTAL;
        pnl_grid.add(lbl_title, gbc);
        
        gbc.gridy++;
        gbc.ipady = 30;
        gbc.gridwidth--;
        gbc.insets = INSETS;
        pnl_grid.add(txt_firstNameRounded, gbc);
        
        gbc.gridx++;
        pnl_grid.add(txt_lastNameRounded, gbc);
        
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx     = 0;
        gbc1.gridy     = 0;
        gbc1.weightx   = 0.1;
        gbc1.weighty   = 1;
        gbc1.fill      = GridBagConstraints.BOTH;
        pnl_birthDateLocation.add(txt_birthDateRounded, gbc1);
        
        gbc1.gridx++;
        gbc1.weightx = 0.9;
        pnl_birthDateLocation.add(txt_locationRounded, gbc1);
        
        gbc.gridy++;
        gbc.gridx--;
        gbc.gridwidth++;
        pnl_grid.add(pnl_birthDateLocation, gbc);
        
        gbc.gridy++;
        gbc.gridx--;
        pnl_grid.add(txt_emailRounded, gbc);
        
        gbc.gridy++;
        pnl_grid.add(txt_usernameRounded, gbc);
        
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx     = 0;
        gbc2.gridy     = 0;
        gbc2.weightx   = 1;
        gbc2.weighty   = 1;
        gbc2.ipadx     = 40;
        gbc2.fill      = GridBagConstraints.BOTH;
        pnl_password.add(txt_passwordRounded, gbc2);
        
        gbc2.gridx++;
        gbc2.weightx = 0;
        gbc2.insets  = new Insets(0, -15, 0, 0);
        pnl_password.add(chkbx_seePassword, gbc2);
        
        gbc.gridy++;
        pnl_grid.add(pnl_password, gbc);
        
        gbc.gridy++;
        gbc.ipady = 5;
        pnl_grid.add(pnl_btn_register, gbc);
        
        gbc.gridy++;
        pnl_grid.add(pnl_btn_cancel, gbc);
        
        this.add(pnl_grid,  BorderLayout.CENTER);
    }
    
    /**
     * Sets up event listeners for user interaction.
     */
    private void initEvents() {
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                registerRestaurateur_ComponentResized(e);
            }
        });
        
        txt_firstName.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                txt_firstName_FocusGained(e);
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                txt_firstName_FocusLost(e);
            }
        });
        
        txt_lastName.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                txt_lastName_FocusGained(e);
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                txt_lastName_FocusLost(e);
            }
        });
        
        txt_birthDate.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                txt_birthDate_FocusGained(e);
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                txt_birthDate_FocusLost(e);
            }
        });
        
        txt_location.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                txt_location_FocusGained(e);
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                txt_location_FocusLost(e);
            }
        });
        
        txt_email.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                txt_email_FocusGained(e);
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                txt_email_FocusLost(e);
            }
        });
        
        txt_username.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                txt_username_FocusGained(e);
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                txt_username_FocusLost(e);
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
        
        txt_password.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                txt_password_KeyTyped(e);
            }
            
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                txt_password_KeyPressed(e);
            }
            
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                txt_password_KeyReleased(e);
            }
        });
        
        chkbx_seePassword.addItemListener((java.awt.event.ItemEvent e) -> {
            chkbx_seePassword_ItemStateChanged(e);
        });
        
        btn_register.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                btn_register_MouseClicked(e);
            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn_register_MouseEntered(e);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn_register_MouseExited(e);
            }
        });
        
        btn_cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                btn_cancel_MouseClicked(e);
            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn_cancel_MouseEntered(e);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn_cancel_MouseExited(e);
            }
        });
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Event Listeners">
    /**
     * Handles the resize event for the {@code RegisterRestaurateur} {@link JPanel}.
     * <p>
     * When resized, resizes the padding of the components.
     * </p>
     * 
     * @param e the component event triggered by resizing the GUI application
     */
    private void registerRestaurateur_ComponentResized(java.awt.event.ComponentEvent e) 
    {
      int[] padding = {(int) (this.getWidth() * 0.2), (int) (this.getHeight() * 0.0375)}; //0 = width; 1 = height;
      this.setBorder(BorderFactory.createEmptyBorder(padding[1], padding[0], padding[1], padding[0]));
        
      final int PADDING_BTN = (int) (this.getWidth() * 0.1);
      pnl_btn_register.setBorder(BorderFactory.createEmptyBorder(0, PADDING_BTN, 0, PADDING_BTN));
      pnl_btn_cancel  .setBorder(BorderFactory.createEmptyBorder(0, PADDING_BTN, 0, PADDING_BTN));
    }
    
    /**
     * Handles the gaining focus event on the first name {@link JTextField}.
     * 
     * @param e the mouse event triggered by gaining focus
     */
    private void txt_firstName_FocusGained(java.awt.event.FocusEvent e) 
    {
      if(txt_firstName.getBackground() == Color.RED)
        txt_firstName.setBackground (BG_TEXTFIELD);
      
      if(txt_firstName.getText().equals(PLACEHOLDER[0])) 
      {
        txt_firstName.setText       ("");
        txt_firstName.setForeground (FG_DEFAULT);
      }   
    }
    
    /**
     * Handles the losing focus event on the first name {@link JTextField}.
     * 
     * @param e the mouse event triggered by gaining focus
     */
    private void txt_firstName_FocusLost(java.awt.event.FocusEvent e) 
    {
      if(txt_firstName.getText().isEmpty()) 
      {
        txt_firstName.setText       (PLACEHOLDER[0]);
        txt_firstName.setForeground (FG_PLACEHOLDER);
      }
    }
    
    /**
     * Handles the gaining focus event on the last name {@link JTextField}.
     * 
     * @param e the mouse event triggered by gaining focus
     */
    private void txt_lastName_FocusGained(java.awt.event.FocusEvent e)
    {
      if(txt_lastName.getBackground() == Color.RED)
        txt_lastName.setBackground  (BG_TEXTFIELD);
      
      if (txt_lastName.getText().equals(PLACEHOLDER[1])) 
      {
        txt_lastName.setText        ("");
        txt_lastName.setForeground  (FG_DEFAULT);
      }
    }
    
    /**
     * Handles the losing focus event on the last name {@link JTextField}.
     * 
     * @param e the mouse event triggered by gaining focus
     */
    private void txt_lastName_FocusLost(java.awt.event.FocusEvent e) 
    {
      if(txt_lastName.getText().isEmpty()) 
      {
        txt_lastName.setText        (PLACEHOLDER[1]);
        txt_lastName.setForeground  (FG_PLACEHOLDER);
      }
    }
    
    /**
     * Handles the gaining focus event on the birthDate {@link JTextField}.
     * 
     * @param e the mouse event triggered by gaining focus
     */
    private void txt_birthDate_FocusGained(java.awt.event.FocusEvent e) 
    {
      if(txt_birthDate.getBackground() == Color.RED)
        txt_birthDate.setBackground (BG_TEXTFIELD);
      
      if(txt_birthDate.getText().equals(PLACEHOLDER[2])) 
      {
        txt_birthDate.setText       ("");
        txt_birthDate.setForeground (FG_DEFAULT);
      }
    }
    
    /**
     * Handles the losing focus event on the birthDate {@link JTextField}.
     * 
     * @param e the mouse event triggered by gaining focus
     */
    private void txt_birthDate_FocusLost(java.awt.event.FocusEvent e)
    {
      if(txt_birthDate.getText().isEmpty()) 
      {
        txt_birthDate.setText       (PLACEHOLDER[2]);
        txt_birthDate.setForeground (FG_PLACEHOLDER);
      }
    }
    
    /**
     * Handles the gaining focus event on the location {@link JTextField}.
     * 
     * @param e the mouse event triggered by gaining focus
     */
    private void txt_location_FocusGained(java.awt.event.FocusEvent e) 
    {
      if(txt_location.getBackground() == Color.RED)
        txt_location.setBackground  (BG_TEXTFIELD);
            
      if(txt_location.getText().equals(PLACEHOLDER[3])) 
      {
        txt_location.setText        ("");
        txt_location.setForeground  (FG_DEFAULT);
      }
    }
    
    /**
     * Handles the losing focus event on the location {@link JTextField}.
     * 
     * @param e the mouse event triggered by gaining focus
     */
    private void txt_location_FocusLost(java.awt.event.FocusEvent e) 
    {
      if(txt_location.getText().isEmpty()) 
      {
        txt_location.setText        (PLACEHOLDER[3]);
        txt_location.setForeground  (FG_PLACEHOLDER);
      }
    }
    
    /**
     * Handles the gaining focus event on the email {@link JTextField}.
     * 
     * @param e the mouse event triggered by gaining focus
     */
    private void txt_email_FocusGained(java.awt.event.FocusEvent e) 
    {
      if(txt_email.getBackground() == Color.RED)
        txt_email.setBackground (BG_TEXTFIELD);
      
      if(txt_email.getText().equals(PLACEHOLDER[4])) 
      {
        txt_email.setText       ("");
        txt_email.setForeground (FG_DEFAULT);
      }
    }
    
    /**
     * Handles the losing focus event on the email {@link JTextField}.
     * 
     * @param e the mouse event triggered by gaining focus
     */
    private void txt_email_FocusLost(java.awt.event.FocusEvent e) 
    {
      if(txt_email.getText().isEmpty()) 
      {
        txt_email.setText       (PLACEHOLDER[4]);
        txt_email.setForeground (FG_PLACEHOLDER);
      }
    }
    
    /**
     * Handles the gaining focus event on the username {@link JTextField}.
     * 
     * @param e the mouse event triggered by gaining focus
     */
    private void txt_username_FocusGained(java.awt.event.FocusEvent e) 
    {
      if(txt_username.getBackground() == Color.RED)
        txt_username.setBackground  (BG_TEXTFIELD);
      
      if(txt_username.getText().equals(PLACEHOLDER[5])) 
      {
        txt_username.setText        ("");
        txt_username.setForeground  (FG_DEFAULT);
      }
    }
    
    /**
     * Handles the losing focus event on the username {@link JTextField}.
     * 
     * @param e the mouse event triggered by gaining focus
     */
    private void txt_username_FocusLost(java.awt.event.FocusEvent e)
    {
      if (txt_username.getText().isEmpty()) 
      {
        txt_username.setText        (PLACEHOLDER[5]);
        txt_username.setForeground  (FG_PLACEHOLDER);
      }
    }
    
    /**
     * Handles the gaining focus event on the password {@link JTextField}.
     * 
     * @param e the mouse event triggered by gaining focus
     */
    private void txt_password_FocusGained(java.awt.event.FocusEvent e) 
    {
      if(txt_password.getBackground() == Color.RED)
        txt_password.setBackground  (BG_TEXTFIELD);
      
      if (String.valueOf(txt_password.getPassword()).equals(PLACEHOLDER[6])) 
      {
        txt_password.setText        ("");
        txt_password.setEchoChar    (DEFAULT_PASSWORD_ECHOCHAR);
        txt_password.setForeground  (FG_DEFAULT);
      }
    }
    
    /**
     * Handles the losing focus event on the password {@link JTextField}.
     * 
     * @param e the mouse event triggered by gaining focus
     */
    private void txt_password_FocusLost(java.awt.event.FocusEvent e) 
    {
      if (String.valueOf(txt_password.getPassword()).isEmpty()) 
      {
        txt_password.setText        (PLACEHOLDER[6]);
        txt_password.setEchoChar    ((char) 0);
        txt_password.setForeground  (FG_PLACEHOLDER);
      }
    }
    
    /**
     * Handles the key pressed for the {@link JPasswordField} password
     * 
     * @param e the key event triggered by typing on it
     */
    private void txt_password_KeyTyped(java.awt.event.KeyEvent e) 
    {
      if(ctrlA_pressed && (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)) 
      {
        txt_password.setText("");
        ctrlA_pressed = false;
      } 
      else if(ctrlA_pressed && Character.isLetterOrDigit(e.getKeyChar())) 
      {
        txt_password.setText(String.valueOf(e.getKeyChar()));
        ctrlA_pressed = false;
      }
    }
    
    /**
     * Handles the keys pressed in order to not get the special characters.
     * 
     * @param e the key event triggered by pressing some keys  
     */
    private void txt_password_KeyPressed(java.awt.event.KeyEvent e) 
    {
      if (txt_password.getEchoChar() == DEFAULT_PASSWORD_ECHOCHAR && !chkbx_seePassword.isSelected())
        txt_password.setEchoChar((char) 0);
      
      if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_A) 
      {
        txt_password.selectAll();
        ctrlA_pressed = true;
      }
    }
    
    /**
     * Handles the password field text.
     * 
     * @param e the key event triggered by releasing a key  
     */
    private void txt_password_KeyReleased(java.awt.event.KeyEvent e) 
    {
      System.out.println(String.valueOf(txt_password.getPassword()));
    }
          
    /**
     * Handles the item state when clicking the {@link JCheckBox}.
     * 
     * @param e the item event triggered by clicking the checkbox
     */
    private void chkbx_seePassword_ItemStateChanged     (java.awt.event.ItemEvent e) 
    {
      chkbx_seePassword.setCharacter(e.getStateChange() % 2 != 0 ? EYE_OFF : EYE);
        if (!txt_password.getBackground().equals(FG_PLACEHOLDER) && !String.valueOf(txt_password.getPassword()).equals(PLACEHOLDER[6])) 
          txt_password.setEchoChar(e.getStateChange() % 2 != 0 ? DEFAULT_PASSWORD_ECHOCHAR : (char) 0);
    }
    
    /**
     * Handles the click event on the register button {@link JLabel}.
     * <p>
     * When the button is clicked, the view switches to the {@code Login} screen canceling the login procedure.
     * </p>
     * 
     * @param e the mouse event triggered by clicking the button 
     */
    private void btn_register_MouseClicked(java.awt.event.MouseEvent e) 
    {
      boolean error = false;
      
      KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
      
      if(                                        txt_firstName.getText().equals    (PLACEHOLDER[0])    || !InputPattern.match(InputPattern.FIRST_NAME,                txt_firstName.getText()))
      {
           error = true;
           txt_firstName.setBackground       (Color.RED);
      }
      if(                                        txt_lastName .getText().equals    (PLACEHOLDER[1])    || !InputPattern.match(InputPattern.LAST_NAME,                 txt_lastName .getText()))
      {
           error = true;
           txt_lastName .setBackground       (Color.RED);
      }
      if((!txt_birthDate.getText().isEmpty() && !txt_birthDate.getText().equals    (PLACEHOLDER[2])    && !InputPattern.match(InputPattern.BIRTH_DATE,                txt_birthDate.getText())))
      {
           error = true;
           txt_birthDate.setBackground        (Color.RED);
      }
      if(                                        txt_location .getText().equals    (PLACEHOLDER[3])    || !InputPattern.match(InputPattern.ADDRESS,                   txt_location .getText()))
      {
           error = true;
           txt_location .setBackground       (Color.RED);
      }
      if(                                        txt_email    .getText().equals    (PLACEHOLDER[4])    || !InputPattern.match(InputPattern.EMAIL,                     txt_email    .getText()))
      {
           error = true;
           txt_email    .setBackground       (Color.RED);
      }     
      if(                                        txt_username .getText().equals    (PLACEHOLDER[5])    || !InputPattern.match(InputPattern.USERNAME,                  txt_username .getText()))
      {
           error = true;
           txt_username .setBackground       (Color.RED);
      }
      if(                                        txt_password .getPassword().equals(PLACEHOLDER[6])    || !InputPattern.match(InputPattern.PASSWORD,   String.valueOf(txt_password .getPassword())))
      {
           error = true;
           txt_password .setBackground       (Color.RED);
      }
         
      if(!error)
      {
        Restaurateur restaurateur = new Restaurateur(txt_firstName.getText(),
                                                     txt_lastName .getText(),
                                                     txt_birthDate.getText().equals(PLACEHOLDER[2]) ? null : txt_birthDate.getText(),
                                                     txt_location .getText(),
                                                     txt_username .getText(),
                                                     txt_email    .getText(),
                                      String.valueOf(txt_password .getPassword())); 
        
        if(controller .RegisterRestaurateur     (restaurateur))
        {
          resetPage();
          controller  .getPanelMain().showCard  (Page.HOME);  
        }
        else
          txt_username.setBackground            (Color.RED);
        
      }     

    }
    
    /**
     * Handles the hover event on the register button {@link JLabel}.
     * 
     * @param e the mouse event triggered by hovering to the button
     */
    private void btn_register_MouseEntered(java.awt.event.MouseEvent e) 
    {
      btn_register.setCursor    (new Cursor(Cursor.HAND_CURSOR));
      btn_register.setBackground(btn_register.getBackground().darker());
    }
    
    /**
     * Handles the exit hover event on the register button {@link JLabel}.
     * 
     * @param e the mouse event triggered by leaving the cursor from the button
     */
    private void btn_register_MouseExited(java.awt.event.MouseEvent e) 
    {
      btn_register.setBackground(BG_REGISTER_BTN);
    }
    
    /**
     * Handles the click event on the cancel button {@link JLabel}.
     * <p>
     * When the button is clicked, the view switches to the {@code Login} screen canceling the login procedure.
     * </p>
     * 
     * @param e the mouse event triggered by clicking the button 
     */
    private void btn_cancel_MouseClicked(java.awt.event.MouseEvent e) 
    {
      resetPage();
      controller.getPanelMain().showCard(Page.LOGIN_RESTAURATEUR);
    }
    
    /**
     * Handles the hover event on the cancel button {@link JLabel}.
     * 
     * @param e the mouse event triggered by hovering to the button
     */
    private void btn_cancel_MouseEntered(java.awt.event.MouseEvent e) 
    {
      btn_cancel.setCursor      (new Cursor(Cursor.HAND_CURSOR));
      btn_cancel.setBackground  (btn_cancel.getBackground().darker());
    }
    
    /**
     * Handles the exit hover event on the cancel button {@link JLabel}.
     * 
     * @param e the mouse event triggered by leaving the cursor from the button
     */
    private void btn_cancel_MouseExited(java.awt.event.MouseEvent e) 
    {
      btn_cancel.setBackground(BG_CANCEL_BTN);
    }
    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * Clears textfields of any previous data or state
     */
    private void resetPage()
    {
      JTextField[] fields = 
      {
        txt_firstName,
        txt_lastName,
        txt_birthDate,
        txt_location,
        txt_email,
        txt_username
      };
        
      for (int i = 0; i < fields.length; i++) 
      {
        fields[i].setText       (PLACEHOLDER[i]);
        fields[i].setForeground (FG_PLACEHOLDER);
        fields[i].setBackground (BG_TEXTFIELD);
      }
        
      chkbx_seePassword.setSelected     (true);
      txt_password     .setText         (PLACEHOLDER[PLACEHOLDER.length - 1]);
      txt_password     .setForeground   (FG_PLACEHOLDER);
      txt_password     .setBackground   (BG_TEXTFIELD);
      txt_password     .setEchoChar     ((char) 0);         
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
        setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N

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
    private final Color              FG_PLACEHOLDER            = Color.GRAY;
    private final Color              BG_REGISTER_BTN           = new Color(0, 255, 0, 192);
    private final Color              BG_CANCEL_BTN             = new Color(255, 64, 0, 192);
    private final Color              BG_TEXTFIELD              = new Color(255, 255, 255, 192);
    private final Border             PADDING_TEXTFIELD         = BorderFactory.createEmptyBorder(0, 10, 0, 10);
    private final Insets             INSETS                    = new Insets(5, 2, 5, 2);
    private final String[]           PLACEHOLDER               = {
        "First name",
        "Last name",
        "Birth date",
        "Location",
        "Email",
        "Username",
        "Password"
    };
    private final String             CANCEL                    = "Cancel";
    private final char               DEFAULT_PASSWORD_ECHOCHAR = '*';
    private final char               EYE                       = 'B';
    private final char               EYE_OFF                   = 'A';
    private final int                ARC_TEXTFIELD             = 50;
    private final int                ARC_BUTTON                = 50;
    private final RoundedComponentUI TXT_LAYER_UI              = new RoundedComponentUI(ARC_TEXTFIELD);
    private final RoundedComponentUI BTN_LAYER_UI              = new RoundedComponentUI(ARC_BUTTON);
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Components">
    private       JPanel             pnl_grid;
    private       JPanel             pnl_password;
    private       JPanel             pnl_birthDateLocation;
    private       JPanel             pnl_btn_register;
    private       JPanel             pnl_btn_cancel;
    private       JLabel             lbl_title;
    private       JLabel             btn_register;
    private       JLabel             btn_cancel;
    private       JTextField         txt_firstName;
    private       JTextField         txt_lastName;
    private       JTextField         txt_birthDate;
    private       JTextField         txt_location;
    private       JTextField         txt_email;
    private       JTextField         txt_username;
    private       JPasswordField     txt_password;
    private       CustomJCheckBox     chkbx_seePassword;
    private       JLayer<JComponent> txt_firstNameRounded;
    private       JLayer<JComponent> txt_lastNameRounded;
    private       JLayer<JComponent> txt_birthDateRounded;
    private       JLayer<JComponent> txt_locationRounded;
    private       JLayer<JComponent> txt_emailRounded;
    private       JLayer<JComponent> txt_usernameRounded;
    private       JLayer<JComponent> txt_passwordRounded;
    private       JLayer<JComponent> btn_registerRounded;
    private       JLayer<JComponent> btn_cancelRounded;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Fields">
    private final Controller controller;
    private       boolean    ctrlA_pressed;
    //</editor-fold>
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
