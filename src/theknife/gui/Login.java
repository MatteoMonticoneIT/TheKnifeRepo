package theknife.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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

/**
 * A panel that represents the login screen in the application.
 * <p>
 * This class contains the graphical elements for the login interface, including
 * a button that switches the view to the home screen when clicked.<br>
 * It is managed by the {@link PanelMain} class using a {@link CardLayout}.
 * </p>
 *
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */
public final class Login extends javax.swing.JPanel 
{

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Creates a new {@code Login} {@link JPanel} and initializes its
     * components.
     * <p>
     * This constructor also sets the main panel to control the screen
     * transitions using {@link PanelMain#showCard}.
     * </p>
     *
     * @param controller the {@link Controller} class that manages the screen layout
     */
    public       Login                                  (Controller controller)
    {
        initComponents();
        this.controller = controller;
        initGUI();
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Initialization">
    /**
     * Initializes the graphical user interface (GUI) for the {@code Login}
     * page.
     */
    private void initGUI                                () 
    {
      initFields();
      initLogin();
      initEvents();
    }

    /**
     * Initializes the basic fields of the {@code Login} panel.
     */
    private void initFields                             () 
    {
      pnl_grid                    = new JPanel            (new GridBagLayout());
      pnl_password                = new JPanel            (new GridBagLayout());
      pnl_btn_login               = new JPanel            (new BorderLayout());
      pnl_btn_cancel              = new JPanel            (new BorderLayout());
      pnl_btn_register            = new JPanel            (new BorderLayout());
      pnl_btn_loginAsRestaurateur = new JPanel            (new BorderLayout());
      lbl_title                   = new JLabel            (Page.LOGIN);
      btn_login                   = new JLabel            (Page.LOGIN);
      btn_cancel                  = new JLabel            (CANCEL);
      btn_register                = new JLabel            (REGISTER);
      btn_loginAsRestaurateur     = new JLabel            (LOGIN_AS_RESTAURATEUR);
      txt_emailUsername           = new JTextField        (PLACEHOLDER[0]);
      txt_password                = new JPasswordField    (PLACEHOLDER[1]);
      chkbx_seePassword           = new CustomCheckBox    ();
      txt_emailUsernameRounded    = new JLayer<>          (txt_emailUsername, TXT_LAYER_UI);
      txt_passwordRounded         = new JLayer<>          (txt_password,      TXT_LAYER_UI);
      btn_loginRounded            = new JLayer<>          (btn_login,         BTN_LAYER_UI);
      btn_cancelRounded           = new JLayer<>          (btn_cancel,        BTN_LAYER_UI);
    }
    
    /**
     * Initializes the layout and appearance of the {@code Login} page.
     */
    private void initLogin                              () 
    {
      this                          .setLayout                  (new BorderLayout());

      pnl_grid                      .setBackground              (this.getBackground());
      pnl_password                  .setBackground              (this.getBackground());
      pnl_btn_login                 .setBackground              (this.getBackground());
      pnl_btn_cancel                .setBackground              (this.getBackground());
      pnl_btn_register              .setBackground              (this.getBackground());
      pnl_btn_loginAsRestaurateur   .setBackground              (this.getBackground());

      lbl_title                     .setBackground              (this.getBackground());
      lbl_title                     .setForeground              (FG_DEFAULT);
      lbl_title                     .setHorizontalAlignment     (JLabel.CENTER);
      lbl_title                     .setVerticalAlignment       (JLabel.CENTER);
      lbl_title                     .setFont                    (new Font(this.getFont().getFontName(), this.getFont().getStyle(), 48));
      lbl_title                     .setOpaque                  (true);

      txt_emailUsername             .setBackground              (BG_TEXTFIELD);
      txt_emailUsername             .setForeground              (FG_PLACEHOLDER);
      txt_emailUsername             .setBorder                  (PADDING_TEXTFIELD);

      txt_password                  .setBackground              (BG_TEXTFIELD);
      txt_password                  .setForeground              (FG_PLACEHOLDER);
      txt_password                  .setBorder                  (PADDING_TEXTFIELD);
      txt_password                  .setEchoChar                ((char) 0);

      chkbx_seePassword             .setSelected                (true);
      chkbx_seePassword             .setCharacter(EYE_OFF);
      
      btn_login                     .setBackground              (BG_LOGIN_BTN);
      btn_login                     .setForeground              (FG_DEFAULT);
      btn_login                     .setHorizontalAlignment     (JLabel.CENTER);
      btn_login                     .setVerticalAlignment       (JLabel.CENTER);
      btn_login                     .setFont                    (this.getFont());
      btn_login                     .setOpaque                  (true);

      btn_cancel                    .setBackground              (BG_CANCEL_BTN);
      btn_cancel                    .setForeground              (FG_DEFAULT);
      btn_cancel                    .setHorizontalAlignment     (JLabel.CENTER);
      btn_cancel                    .setVerticalAlignment       (JLabel.CENTER);
      btn_cancel                    .setFont                    (this.getFont());
      btn_cancel                    .setOpaque                  (true);

      btn_register                  .setBackground              (this.getBackground());
      btn_register                  .setForeground              (FG_DEFAULT);
      btn_register                  .setVerticalAlignment       (JLabel.CENTER);
      btn_register                  .setFont                    (new Font(this.getFont().getFontName(), this.getFont().getStyle(), 18));
      btn_register                  .setOpaque                  (true);

      btn_loginAsRestaurateur       .setBackground              (this.getBackground());
      btn_loginAsRestaurateur       .setForeground              (FG_DEFAULT);
      btn_loginAsRestaurateur       .setVerticalAlignment       (JLabel.CENTER);
      btn_loginAsRestaurateur       .setFont                    (new Font(this.getFont().getFontName(), this.getFont().getStyle(), 18));
      btn_loginAsRestaurateur       .setOpaque                  (true);

      pnl_btn_login                 .add                        (btn_loginRounded, BorderLayout.CENTER);
      pnl_btn_cancel                .add                        (btn_cancelRounded, BorderLayout.CENTER);
      pnl_btn_register              .add                        (btn_register, BorderLayout.CENTER);
      pnl_btn_loginAsRestaurateur   .add                        (btn_loginAsRestaurateur, BorderLayout.CENTER);

      GridBagConstraints            gbc                         = new GridBagConstraints();
      gbc                           .gridx                      = 0;
      gbc                           .gridy                      = 0;
      gbc                           .weightx                    = 1;
      gbc                           .fill                       = GridBagConstraints.HORIZONTAL;
      pnl_grid                      .add                        (lbl_title, gbc);
      gbc                           .gridy++;
      gbc                           .ipady                      = 30;
      gbc                           .insets                     = INSETS;
      pnl_grid                      .add                        (txt_emailUsernameRounded, gbc);

      GridBagConstraints            gbc1                        = new GridBagConstraints();
      gbc1                          .gridx                      = 0;
      gbc1                          .gridy                      = 0;
      gbc1                          .weightx                    = 1;
      gbc1                          .weighty                    = 1;
      gbc1                          .fill                       = GridBagConstraints.BOTH;
      gbc1                          .ipadx                      = 40;
      pnl_password                  .add                        (txt_passwordRounded, gbc1);

      gbc1                          .gridx++;
      gbc1                          .weightx                    = 0;
      gbc1                          .insets                     = new Insets(0, -15, 0, 0);
      pnl_password                  .add                        (chkbx_seePassword, gbc1);

      gbc                           .gridy++;
      pnl_grid                      .add                        (pnl_password, gbc);

      gbc                           .gridy++;
      gbc                           .gridx--;
      gbc                           .ipady                      = 0;
      gbc                           .fill                       = GridBagConstraints.NONE;
      gbc                           .anchor                     = GridBagConstraints.WEST;
      pnl_grid                      .add                        (pnl_btn_register, gbc);

      gbc                           .gridy++;
      pnl_grid                      .add                        (pnl_btn_loginAsRestaurateur, gbc);

      gbc                           .gridy++;
      gbc                           .ipady                      = 5;
      gbc                           .fill                       = GridBagConstraints.HORIZONTAL;
      pnl_grid                      .add                        (pnl_btn_login, gbc);

      gbc                           .gridy++;
      pnl_grid                      .add                        (pnl_btn_cancel, gbc);

      this                          .add                        (pnl_grid, BorderLayout.CENTER);
    }

    /**
     * Sets up event listeners for user interaction.
     */
    private void initEvents                             () 
    {
      this                      .addComponentListener   (new java.awt.event.ComponentAdapter() 
      {
        @Override
        public void componentResized                    (java.awt.event.ComponentEvent e) 
        {
          login_ComponentResized                        (e);
        }
      });

      txt_emailUsername         .addFocusListener       (new java.awt.event.FocusAdapter() 
      {
        @Override
        public void focusGained                         (java.awt.event.FocusEvent e) 
        {
          txt_emailUsername_FocusGained                 (e);
        }

        @Override
        public void focusLost                           (java.awt.event.FocusEvent e) 
        {
          txt_emailUsername_FocusLost                   (e);
        }
      });

      txt_password              .addFocusListener       (new java.awt.event.FocusAdapter() 
      {
        @Override
        public void focusGained                         (java.awt.event.FocusEvent e) 
        {
          txt_password_FocusGained                      (e);
        }

        @Override
        public void focusLost                           (java.awt.event.FocusEvent e) 
        {
          txt_password_FocusLost                        (e);
        }
      });

      txt_password              .addKeyListener         (new java.awt.event.KeyAdapter() 
      {
        @Override
        public void keyTyped                            (java.awt.event.KeyEvent e) 
        {
          txt_password_KeyTyped                         (e);
        }

        @Override
        public void keyPressed                          (java.awt.event.KeyEvent e) 
        {
          txt_password_KeyPressed                       (e);
        }

        @Override
        public void keyReleased                         (java.awt.event.KeyEvent e) 
        {
          txt_password_KeyReleased                      (e);
        }
      });

      chkbx_seePassword         .addItemListener        ((java.awt.event.ItemEvent e) -> 
      {
        chkbx_seePassword_ItemStateChanged              (e);
      });

      btn_login                 .addMouseListener       (new java.awt.event.MouseAdapter() 
      {
        @Override
        public void mouseClicked                        (java.awt.event.MouseEvent e) 
        {
          btn_login_MouseClicked                        (e);
        }

        @Override
        public void mouseEntered                        (java.awt.event.MouseEvent e) 
        {
          btn_login_MouseEntered                        (e);
        }

        @Override
        public void mouseExited                         (java.awt.event.MouseEvent e) 
        {         
          btn_login_MouseExited                         (e);
        }
      });

      btn_cancel                .addMouseListener       (new java.awt.event.MouseAdapter() 
      {
        @Override
        public void mouseClicked                        (java.awt.event.MouseEvent e) 
        {
          btn_cancel_MouseClicked                       (e);
        }

        @Override
        public void mouseEntered                        (java.awt.event.MouseEvent e) 
        {
          btn_cancel_MouseEntered                       (e);
        }

        @Override
        public void mouseExited                         (java.awt.event.MouseEvent e) 
        {
          btn_cancel_MouseExited                        (e);
        }
      });

      btn_register              .addMouseListener       (new java.awt.event.MouseAdapter() 
      {
        @Override
        public void mouseClicked                        (java.awt.event.MouseEvent e) 
        {
          btn_register_MouseClicked                     (e);
        }

        @Override
        public void mouseEntered                        (java.awt.event.MouseEvent e) 
        {
          btn_register_MouseEntered                     (e);
        }

        @Override
        public void mouseExited                         (java.awt.event.MouseEvent e) 
        {
          btn_register_MouseExited                      (e);
        }
      });

      btn_loginAsRestaurateur   .addMouseListener       (new java.awt.event.MouseAdapter() 
      {
        @Override
        public void mouseClicked                        (java.awt.event.MouseEvent e) 
        {
          btn_loginAsRestaurateur_MouseClicked          (e);
        }

        @Override
        public void mouseEntered                        (java.awt.event.MouseEvent e) 
        {
          btn_loginAsRestaurateur_MouseEntered          (e);
        }

        @Override
        public void mouseExited                         (java.awt.event.MouseEvent e) 
        {
          btn_loginAsRestaurateur_MouseExited           (e);
        }
      });
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Event Listeners">
    /**
     * Handles the resize event for the {@code Login} {@link JPanel}.
     * <p>
     * When resized, resizes the padding of the components.
     * </p>
     *
     * @param e the component event triggered by resizing the GUI application
     */
    private void login_ComponentResized                 (java.awt.event.ComponentEvent e) 
    {
      int[]     padding       = {(int) (this.getWidth() * 0.2), (int) (this.getHeight() * 0.1)};
      this            .setBorder(BorderFactory.createEmptyBorder(padding[1], padding[0], padding[1], padding[0]));

      final int PADDING_BTN   =  (int) (this.getWidth() * 0.1);
      pnl_btn_login   .setBorder(BorderFactory.createEmptyBorder(0, PADDING_BTN, 0, PADDING_BTN));
      pnl_btn_cancel  .setBorder(BorderFactory.createEmptyBorder(0, PADDING_BTN, 0, PADDING_BTN));
    }

    /**
     * Handles the gaining focus event on the email or username
     * {@link JTextField}.
     *
     * @param e the mouse event triggered by gaining focus
     */
    private void txt_emailUsername_FocusGained          (java.awt.event.FocusEvent e) 
    {
      if(txt_emailUsername.getText().equals(PLACEHOLDER[0])) 
      {
        txt_emailUsername.setText       ("");
        txt_emailUsername.setForeground (FG_DEFAULT);
      }
    }

    /**
     * Handles the losing focus event on the email or username
     * {@link JTextField}.
     *
     * @param e the mouse event triggered by gaining focus
     */
    private void txt_emailUsername_FocusLost            (java.awt.event.FocusEvent e) 
    {
      if (txt_emailUsername.getText().isEmpty()) 
      {
        txt_emailUsername.setText       (PLACEHOLDER[0]);
        txt_emailUsername.setForeground (FG_PLACEHOLDER);
      }
    }

    /**
     * Handles the gaining focus event on the password {@link JTextField}.
     *
     * @param e the mouse event triggered by gaining focus
     */
    private void txt_password_FocusGained               (java.awt.event.FocusEvent e) 
    {
      if (String.valueOf(txt_password.getPassword()).equals(PLACEHOLDER[1])) 
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
    private void txt_password_FocusLost                 (java.awt.event.FocusEvent e) 
    {
      if (String.valueOf(txt_password.getPassword()).isEmpty()) 
      {
        txt_password.setText        (PLACEHOLDER[1]);
        txt_password.setEchoChar    ((char) 0);
        txt_password.setForeground  (FG_PLACEHOLDER);
      }
    }

    /**
     * Handles the key pressed for the {@link JPasswordField} password
     *
     * @param e the key event triggered by typing on it
     */
    private void txt_password_KeyTyped                  (java.awt.event.KeyEvent e) 
    {
      if      (ctrlA_pressed && (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)) 
      {
        txt_password.setText("");
        ctrlA_pressed = false;
      } 
      else if (ctrlA_pressed && Character.isLetterOrDigit(e.getKeyChar())) 
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
    private void txt_password_KeyPressed                (java.awt.event.KeyEvent e) 
    {
      if (txt_password.getEchoChar() == DEFAULT_PASSWORD_ECHOCHAR && !chkbx_seePassword.isSelected()) {
        txt_password.setEchoChar((char) 0);
      }
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
    private void txt_password_KeyReleased               (java.awt.event.KeyEvent e) 
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
        if (!txt_password.getBackground().equals(FG_PLACEHOLDER) && !String.valueOf(txt_password.getPassword()).equals(PLACEHOLDER[1])) 
          txt_password.setEchoChar(e.getStateChange() % 2 != 0 ? DEFAULT_PASSWORD_ECHOCHAR : (char) 0);
    }

    /**
     * Handles the click event on the login button {@link JLabel}.
     * <p>
     * When the button is clicked, the parameters added to the textfields will
     * be handled and checked if the account exists: <br>
     * If so then the account is logged in and the view switches to the
     * {@code Home} screen. <br>
     * Otherwise it will generate an error referring to the parameters.
     * </p>
     *
     * @param e the mouse event triggered by clicking the button
     */
    private void btn_login_MouseClicked                 (java.awt.event.MouseEvent e) 
    {
      String user     =                txt_emailUsername.getText    ();
      String password = String.valueOf(txt_password     .getPassword());
      if(controller.LoginClient(user, password))
        controller.getPanelMain().showCard(Page.HOME);
    }

    /**
     * Handles the hover event on the login button {@link JLabel}.
     *
     * @param e the mouse event triggered by hovering to the button
     */
    private void btn_login_MouseEntered                 (java.awt.event.MouseEvent e) 
    {
      btn_login.setCursor       (new Cursor(Cursor.HAND_CURSOR));
      btn_login.setBackground   (btn_login.getBackground().darker());
    }

    /**
     * Handles the exit hover event on the login button {@link JLabel}.
     *
     * @param e the mouse event triggered by leaving the cursor from the button
     */
    private void btn_login_MouseExited                  (java.awt.event.MouseEvent e) 
    {
      btn_login.setBackground(BG_LOGIN_BTN);
    }

    /**
     * Handles the click event on the cancel button {@link JLabel}.
     * <p>
     * When the button is clicked, the view switches to the {@code Home} screen
     * canceling the login procedure.
     * </p>
     *
     * @param e the mouse event triggered by clicking the button
     */
    private void btn_cancel_MouseClicked                (java.awt.event.MouseEvent e) 
    {
      txt_emailUsername .setText        (PLACEHOLDER[0]);
      txt_emailUsername .setForeground  (FG_PLACEHOLDER);

      chkbx_seePassword .setSelected    (true);
      txt_password      .setText        (PLACEHOLDER[1]);
      txt_password      .setForeground  (FG_PLACEHOLDER);
      txt_password      .setEchoChar    ((char) 0);

      controller.getPanelMain().showCard(Page.HOME);
    }

    /**
     * Handles the hover event on the cancel button {@link JLabel}.
     *
     * @param e the mouse event triggered by hovering to the button
     */
    private void btn_cancel_MouseEntered                (java.awt.event.MouseEvent e) 
    {
      btn_cancel.setCursor    (new Cursor(Cursor.HAND_CURSOR));
      btn_cancel.setBackground(btn_cancel.getBackground().darker());
    }

    /**
     * Handles the exit hover event on the cancel button {@link JLabel}.
     *
     * @param e the mouse event triggered by leaving the cursor from the button
     */
    private void btn_cancel_MouseExited                 (java.awt.event.MouseEvent e) 
    {
      btn_cancel.setBackground(BG_CANCEL_BTN);
    }

    /**
     * Handles the click event on the register button {@link JLabel}.
     * <p>
     * When the button is clicked the view switches to the {@code Register}
     * screen canceling the login procedure.
     * </p>
     *
     * @param e the mouse event triggered by clicking the button
     */
    private void btn_register_MouseClicked              (java.awt.event.MouseEvent e) 
    {
      chkbx_seePassword .setSelected    (true);
      txt_emailUsername .setText        (PLACEHOLDER[0]);
      txt_emailUsername .setForeground  (FG_PLACEHOLDER);

      txt_password      .setText        (PLACEHOLDER[1]);
      txt_password      .setForeground  (FG_PLACEHOLDER);
      txt_password      .setEchoChar    ((char) 0);

      controller.getPanelMain().showCard(Page.REGISTER);
    }

    /**
     * Handles the hover event on the register button {@link JLabel}.
     *
     * @param e the mouse event triggered by hovering to the button
     */
    private void btn_register_MouseEntered              (java.awt.event.MouseEvent e) 
    {
      btn_register.setForeground(Color.BLUE);
      btn_register.setCursor    (new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Handles the exit hover event on the register button {@link JLabel}.
     *
     * @param e the mouse event triggered by leaving the cursor from the button
     */
    private void btn_register_MouseExited               (java.awt.event.MouseEvent e) 
    {
      btn_register.setForeground(FG_DEFAULT);
    }

    /**
     * Handles the click event on the login as restaurateur button
     * {@link JLabel}.
     * <p>
     * When the button is clicked the view switches to the
     * {@code LoginAsRestaurateur} screen canceling the login procedure.
     * </p>
     *
     * @param e the mouse event triggered by clicking the button
     */
    private void btn_loginAsRestaurateur_MouseClicked   (java.awt.event.MouseEvent e) 
    {
      txt_emailUsername .setText        (PLACEHOLDER[0]);
      txt_emailUsername .setForeground  (FG_PLACEHOLDER);

      txt_password      .setText        (PLACEHOLDER[1]);
      txt_password      .setForeground  (FG_PLACEHOLDER);
      txt_password      .setEchoChar    ((char) 0);

      controller.getPanelMain().showCard(Page.LOGIN_RESTAURATEUR);
    }

    /**
     * Handles the hover event on the register button {@link JLabel}.
     *
     * @param e the mouse event triggered by hovering to the button
     */
    private void btn_loginAsRestaurateur_MouseEntered   (java.awt.event.MouseEvent e) 
    {
      btn_loginAsRestaurateur.setForeground (Color.BLUE);
      btn_loginAsRestaurateur.setCursor     (new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Handles the exit hover event on the register button {@link JLabel}.
     *
     * @param e the mouse event triggered by leaving the cursor from the button
     */
    private void btn_loginAsRestaurateur_MouseExited    (java.awt.event.MouseEvent e) 
    {
      btn_loginAsRestaurateur.setForeground(FG_DEFAULT);
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
        setMinimumSize(new java.awt.Dimension(0, 0));
        setPreferredSize(new java.awt.Dimension(400, 300));

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
    private final Color              BG_LOGIN_BTN              = new Color(0, 255, 0, 192);
    private final Color              BG_CANCEL_BTN             = new Color(255, 64, 0, 192);
    private final Color              BG_TEXTFIELD              = new Color(255, 255, 255, 192);
    private final Border             PADDING_TEXTFIELD         = BorderFactory.createEmptyBorder(0, 10, 0, 10);
    private final Insets             INSETS                    = new Insets(8, 0, 8, 0);
    private final String[]           PLACEHOLDER               = 
    {
        "Your email or username",
        "Your password"
    };
    private final String             CANCEL                    = "Cancel";
    private final String             REGISTER                  = "Still not our customer yet? Sign up here!";
    private final String             LOGIN_AS_RESTAURATEUR     = "Are you a restaurateur? Log in here!";
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
    private       JPanel             pnl_btn_login;
    private       JPanel             pnl_btn_cancel;
    private       JPanel             pnl_btn_register;
    private       JPanel             pnl_btn_loginAsRestaurateur;
    private       JLabel             lbl_title;
    private       JLabel             btn_login;
    private       JLabel             btn_cancel;
    private       JLabel             btn_register;
    private       JLabel             btn_loginAsRestaurateur;
    private       JTextField         txt_emailUsername;
    private       JPasswordField     txt_password;
    private       CustomCheckBox     chkbx_seePassword;
    private       JLayer<JComponent> txt_emailUsernameRounded;
    private       JLayer<JComponent> txt_passwordRounded;
    private       JLayer<JComponent> btn_loginRounded;
    private       JLayer<JComponent> btn_cancelRounded;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Fields">
    private final Controller    controller;
    private       boolean       ctrlA_pressed;
    //</editor-fold>
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
