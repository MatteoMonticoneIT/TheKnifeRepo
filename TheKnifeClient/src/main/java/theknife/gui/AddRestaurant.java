/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theknife.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import simple.file.CSV;
import simple.util.StringUtils;
import theknife.client.Controller;
import theknife.obj.lists.ListOwned;
import theknife.obj.AppPaths;
import theknife.obj.InputPattern;
import theknife.obj.restaurant.Restaurant;

/**
 * {@code AddRestaurant} is a page where you can add a {@link Restaurant} to the dataset and your {@link ListOwned}.
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */
public class AddRestaurant extends javax.swing.JPanel 
{

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Creates a new {@code AddRestaurant} {@link JPanel} and initializes its components.
     * <p>
     * This constructor also sets the main panel to control the screen transitions using {@link PanelMain#showCard}.
     * </p>
     *
     * @param controller the {@link Controller} class that manages the screen layout
     */
    public AddRestaurant(Controller controller) 
    {
      initComponents();
      this.controller = controller;
      initGUI();
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Initialization">
    /**
     * Initializes the graphical user interface (GUI) for the {@code AddRestaurant} page.
     */
    private void initGUI() 
    {
      initFields();
      initRegister();
      initEvents();
    }
    
    /**
     * Initializes the basic fields of the {@code AddRestaurant} {@link JPanel}.
     */
    private void initFields() 
    {
      pnl_grid              = new JPanel(new GridBagLayout());
      pnl_btn_add           = new JPanel(new BorderLayout());
      pnl_btn_cancel        = new JPanel(new BorderLayout());
      pnl_btns              = new JPanel(new GridLayout(1, 2, 10, 10));
      pnl_priceBar          = new JPanel(new GridLayout(1, PRICES));
      pnl_cuisines          = new JPanel(new GridLayout((int) Math.ceil(CHKBX_CUISINE_TXT.length / 2), 2, 10, 10));
      pnl_services          = new JPanel(new GridLayout((int) Math.ceil(CHKBX_SERVICE_TXT.length / 2), 2, 10, 10));
      scrlPnl_grid          = new JScrollPane(pnl_grid,     JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      scrlPnl_cuisines      = new JScrollPane(pnl_cuisines, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      lbl_title             = new JLabel(TITLE);
      lbl_prices            = new JLabel[PRICES];
      btn_add               = new JLabel(ADD);
      btn_cancel            = new JLabel(CANCEL);
      txt_name              = new JTextField(PLACEHOLDER[0]);
      txt_address           = new JTextField(PLACEHOLDER[1]);
      txt_country           = new JTextField(PLACEHOLDER[2]);
      txt_city              = new JTextField(PLACEHOLDER[3]);
      txt_latitude          = new JTextField(PLACEHOLDER[4]);
      txt_longitude         = new JTextField(PLACEHOLDER[5]);
      txt_currency          = new JTextField(PLACEHOLDER[6]);
      txt_phoneNo           = new JTextField(PLACEHOLDER[7]);
      txt_url               = new JTextField(PLACEHOLDER[8]);
      txt_webUrl            = new JTextField(PLACEHOLDER[9]);
      txt_award             = new JTextField(PLACEHOLDER[10]);
      txts                  = new JTextField[] {
        txt_name,
        txt_address,
        txt_country,
        txt_city,
        txt_latitude,
        txt_longitude,
        txt_currency,
        txt_phoneNo,
        txt_url,
        txt_webUrl,
        txt_award
      };
      txt_description       = new JTextArea();
      scrlPnl_description   = new JScrollPane(txt_description, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      chkbx_greenStar       = new CustomJCheckBox(AppPaths.getRequiredFile("img", "Stars.ttf"));
      chkbx_cuisines        = new JCheckBox[CHKBX_CUISINE_TXT.length];
      chkbx_services        = new JCheckBox[CHKBX_SERVICE_TXT.length];
      scrlPnl_gridRounded   = new JLayer<>(scrlPnl_grid,  PNL_LAYER_UI);
      txt_nameRounded       = new JLayer<>(txt_name,      TXT_LAYER_UI);
      txt_addressRounded    = new JLayer<>(txt_address,   TXT_LAYER_UI);
      txt_countryRounded    = new JLayer<>(txt_country,   TXT_LAYER_UI);
      txt_cityRounded       = new JLayer<>(txt_city,      TXT_LAYER_UI);
      txt_latitudeRounded   = new JLayer<>(txt_latitude,  TXT_LAYER_UI);
      txt_longitudeRounded  = new JLayer<>(txt_longitude, TXT_LAYER_UI);
      txt_currencyRounded   = new JLayer<>(txt_currency,  TXT_LAYER_UI);
      txt_phoneNoRounded    = new JLayer<>(txt_phoneNo,   TXT_LAYER_UI);
      txt_urlRounded        = new JLayer<>(txt_url,       TXT_LAYER_UI);
      txt_webUrlRounded     = new JLayer<>(txt_webUrl,    TXT_LAYER_UI);
      btn_addRounded        = new JLayer<>(btn_add,       BTN_LAYER_UI);
      btn_cancelRounded     = new JLayer<>(btn_cancel,    BTN_LAYER_UI);
    }
    
    /**
     * Initializes the layout and appearance of the {@code AddRestaurant} page.
     */
    private void initRegister() 
    {
        this.setLayout(new BorderLayout());
       
        pnl_grid      .setBackground(BG_PNL_GRID);

        pnl_cuisines  .setBackground(BG_PNL_CHKBXS);
        pnl_cuisines  .setBorder    (PADDING_PANEL_CHKBXS);

        pnl_services  .setBackground(BG_PNL_CHKBXS);
        pnl_services  .setBorder    (PADDING_PANEL_CHKBXS);
        
        pnl_btns      .setBackground(this.getBackground());

        pnl_btn_add   .setBackground(pnl_btns.getBackground());
        pnl_btn_cancel.setBackground(pnl_btns.getBackground());
        
        for (int i = 0; i < chkbx_cuisines.length; i++) 
        {
          chkbx_cuisines[i] = new JCheckBox(CHKBX_CUISINE_TXT[i]);
          chkbx_cuisines[i].setFont(this.getFont());
          pnl_cuisines.add(chkbx_cuisines[i]);
        }

        for (int i = 0; i < chkbx_services.length; i++) 
        {
          chkbx_services[i] = new JCheckBox(CHKBX_SERVICE_TXT[i]);
          chkbx_services[i].setFont(this.getFont());
          pnl_services.add(chkbx_services[i]);
        }
        
        chkbx_greenStar.setForeground              (Color.GREEN);
        chkbx_greenStar.setSelected                (true);
        chkbx_greenStar.setCharacter               (EMPTY_STAR);
        
        scrlPnl_grid.getVerticalScrollBar()      .setUI(new CustomJScrollBar());
        scrlPnl_grid.getHorizontalScrollBar()    .setUI(new CustomJScrollBar());
        scrlPnl_grid.setBorder                   (BorderFactory.createEmptyBorder());
        
        scrlPnl_cuisines.getVerticalScrollBar()      .setUI(new CustomJScrollBar());
        scrlPnl_cuisines.getHorizontalScrollBar()    .setUI(new CustomJScrollBar());
        scrlPnl_cuisines.setPreferredSize            (new Dimension(0, SCRLPNL_CUISINES_HEIGHT));
        scrlPnl_cuisines.setBorder                   (BorderFactory.createEmptyBorder());
        
        scrlPnl_description.getVerticalScrollBar()      .setUI(new CustomJScrollBar());
        scrlPnl_description.getHorizontalScrollBar()    .setUI(new CustomJScrollBar());
        scrlPnl_description.setPreferredSize            (new Dimension(0, TEXTAREA_HEIGHT));
        scrlPnl_description.setBorder                   (BorderFactory.createEmptyBorder());
        
        lbl_title.setBackground(this.getBackground());
        lbl_title.setForeground(FG_DEFAULT);
        lbl_title.setHorizontalAlignment(JLabel.CENTER);
        lbl_title.setVerticalAlignment  (JLabel.CENTER);
        lbl_title.setFont(new Font(this.getFont().getFontName(), this.getFont().getStyle(), 48));
        lbl_title.setOpaque(true);

        for (int i = 0; i < lbl_prices.length; i++) 
        {
          lbl_prices[i] = new JLabel          (PRICE_TAGS[i]);
          lbl_prices[i].setBackground         (BG_PRICE_LBL);
          lbl_prices[i].setForeground         (FG_DEFAULT);
          lbl_prices[i].setHorizontalAlignment(JLabel.CENTER);
          lbl_prices[i].setVerticalAlignment  (JLabel.CENTER);
          lbl_prices[i].setFont               (this.getFont());
          lbl_prices[i].setOpaque             (true);
          pnl_priceBar.add                    (lbl_prices[i]);
        }
        
        for (JTextField txt : txts) 
        {
            txt.setBackground(BG_TEXTFIELD);
            txt.setForeground(FG_PLACEHOLDER);
            txt.setBorder(PADDING_TEXTFIELD);
            txt.setFont(this.getFont());
            txt.setPreferredSize(new Dimension(0, TEXTFIELD_HEIGHT));
        }
        
        txt_description.setBackground(BG_TEXTAREA);
        txt_description.setForeground(FG_DEFAULT);
        txt_description.setBorder(PADDING_TEXTAREA);
        txt_description.setFont(this.getFont());
        
        btn_add.setBackground(BG_REGISTER_BTN);
        btn_add.setForeground(FG_DEFAULT);
        btn_add.setHorizontalAlignment(JLabel.CENTER);
        btn_add.setVerticalAlignment  (JLabel.CENTER);
        btn_add.setFont(new Font(this.getFont().getFontName(), this.getFont().getStyle(), 28));
        btn_add.setOpaque(true);
        
        btn_cancel.setBackground(BG_CANCEL_BTN);
        btn_cancel.setForeground(FG_DEFAULT);
        btn_cancel.setHorizontalAlignment(JLabel.CENTER);
        btn_cancel.setVerticalAlignment  (JLabel.CENTER);
        btn_cancel.setFont(new Font(this.getFont().getFontName(), this.getFont().getStyle(), 28));
        btn_cancel.setOpaque(true);
        
        pnl_btn_add   .add(btn_addRounded, BorderLayout.CENTER);
        pnl_btn_cancel.add(btn_cancelRounded,   BorderLayout.CENTER);
        
        pnl_btns.setPreferredSize(new Dimension(this.getWidth(), PNL_BTNS_HEIGHT));
        pnl_btns.add             (pnl_btn_add,    BorderLayout.CENTER);
        pnl_btns.add             (pnl_btn_cancel, BorderLayout.EAST);
      
        JLabel[] lbl_guides = new JLabel[LBL_GUIDE_TXT.length];
        for (int i = 0; i < lbl_guides.length; i++) 
        {
          lbl_guides[i] = new JLabel          (LBL_GUIDE_TXT[i]);
          lbl_guides[i].setBackground         (this.getBackground().darker());
          lbl_guides[i].setHorizontalAlignment(JLabel.CENTER);
          lbl_guides[i].setVerticalAlignment  (JLabel.CENTER);
          lbl_guides[i].setFont               (new Font(this.getFont().getFontName(), this.getFont().getStyle(), 36));
          lbl_guides[i].setOpaque             (true);
        }
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx     = 0;
        gbc.gridy     = 0;
        gbc.ipady     = 30;
        gbc.weightx   = 1;
        gbc.weighty   = 1;
        gbc.gridwidth = 4;
        gbc.fill      = GridBagConstraints.BOTH;
        gbc.insets    = INSETS;
        pnl_grid.add(txt_nameRounded, gbc);
        
        gbc.gridy++;
        pnl_grid.add(txt_addressRounded, gbc);
        
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.weightx   = 0.25;
        pnl_grid.add(txt_countryRounded, gbc);
        
        gbc.gridx++;
        pnl_grid.add(txt_cityRounded, gbc);
        
        gbc.gridx++;
        pnl_grid.add(txt_latitudeRounded, gbc);
        
        gbc.gridx++;
        pnl_grid.add(txt_longitudeRounded, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        pnl_grid.add(txt_currencyRounded, gbc);
        
        gbc.gridx++;
        pnl_grid.add(txt_phoneNoRounded, gbc);
        
        gbc.gridx++;
        pnl_grid.add(txt_urlRounded, gbc);
        
        gbc.gridx++;
        pnl_grid.add(txt_webUrlRounded, gbc);
        
        gbc.gridx     = 0;
        gbc.weightx   = 1;
        gbc.gridy++;
        gbc.gridwidth = 4;
        pnl_grid.add(pnl_priceBar, gbc);
        
        gbc.gridy++;
        pnl_grid.add(txt_award, gbc);
        
        gbc.gridy++;
        pnl_grid.add(chkbx_greenStar, gbc);
        
        gbc.gridy++;
        pnl_grid.add(lbl_guides[0], gbc);
        
        gbc.gridy++;
        pnl_grid.add(scrlPnl_cuisines, gbc);
        
        gbc.gridy++;
        pnl_grid.add(lbl_guides[1], gbc);
        
        gbc.gridy++;
        pnl_grid.add(pnl_services, gbc);
        
        gbc.gridy++;
        pnl_grid.add(lbl_guides[2], gbc);
        
        gbc.gridy++;
        pnl_grid.add(scrlPnl_description, gbc);
        
        this.add(lbl_title,           BorderLayout.NORTH);
        this.add(scrlPnl_gridRounded, BorderLayout.CENTER);
        this.add(pnl_btns,            BorderLayout.SOUTH);
    }
    
    /**
     * Sets up event listeners for user interaction.
     */
    private void initEvents() 
    {
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                addRestaurant_ComponentResized(e);
            }
        });

        chkbx_greenStar         .addItemListener        ((java.awt.event.ItemEvent e) -> 
        {
          chkbx_greenStar_ItemStateChanged              (e);
        });
        
        scrlPnl_grid.addMouseWheelListener((java.awt.event.MouseWheelEvent e) -> {
            scrlPnl_grid_MouseWheelMoved(e);
        });
        
        scrlPnl_cuisines.addMouseWheelListener((java.awt.event.MouseWheelEvent e) -> {
            scrlPnl_cuisines_MouseWheelMoved(e);
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
        
        for (JTextField txt : txts) {
            txt.addFocusListener(new java.awt.event.FocusAdapter() {
                @Override
                public void focusGained(java.awt.event.FocusEvent e) {
                    txt_FocusGained(e);
                }

                @Override
                public void focusLost(java.awt.event.FocusEvent e) {
                    txt_FocusLost(e);
                }
            });
        }
        
        for (JLabel lbl : lbl_prices) {
            lbl.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    lbl_price_MouseClicked(e);
                }
                
                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    lbl_price_MouseExited(e);
                }
            });
            
            lbl.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                @Override
                public void mouseMoved(java.awt.event.MouseEvent e) {
                    lbl_price_MouseMoved(e);
                }
            });
        }
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Event Listeners">
    /**
     * Handles the resize event for the {@code AddRestaurant} {@link JPanel}.
     * <p>
     * When resized, resizes the padding of the components.
     * </p>
     * 
     * @param e the component event triggered by resizing the GUI application
     */
    private void addRestaurant_ComponentResized(java.awt.event.ComponentEvent e) 
    {
      int[] padding = {(int) (this.getWidth() * 0.0005), (int) (this.getHeight() * 0.00025)};
      this.setBorder(BorderFactory.createEmptyBorder(padding[1], padding[0], padding[1], padding[0]));
        
      final int PADDING_BTN = (int) (this.getWidth() * 0.01);
      pnl_btn_add   .setBorder(BorderFactory.createEmptyBorder(PADDING_BTN, PADDING_BTN, PADDING_BTN, PADDING_BTN));
      pnl_btn_cancel.setBorder(BorderFactory.createEmptyBorder(PADDING_BTN, PADDING_BTN, PADDING_BTN, PADDING_BTN));
    }

    /**
     * Handles the item state when clicking the {@link JCheckBox}.
     *
     * @param e the item event triggered by clicking the checkbox
     */
    private void chkbx_greenStar_ItemStateChanged     (java.awt.event.ItemEvent e) 
    {
      chkbx_greenStar.setCharacter(e.getStateChange() % 2 != 0 ? EMPTY_STAR : FULL_STAR);
    }
    
    /**
     * Handles the {@link JScrollPane} {@link JScrollBar}.
     * 
     * @param e the component event triggered by wheel-scrolling using the mouse.
     */
    private void scrlPnl_grid_MouseWheelMoved(java.awt.event.MouseWheelEvent e) 
    {
        int notches = e.getWheelRotation();
        int fasterScroll = notches * 40;

        JScrollBar vertical = scrlPnl_grid.getVerticalScrollBar();
        vertical.setValue(vertical.getValue() + fasterScroll);
    }
    
    /**
     * Handles the {@link JScrollPane} {@link JScrollBar}.
     * 
     * @param e the component event triggered by wheel-scrolling using the mouse.
     */
    private void scrlPnl_cuisines_MouseWheelMoved(java.awt.event.MouseWheelEvent e) 
    {
        int notches = e.getWheelRotation();
        int fasterScroll = notches * 40;

        JScrollBar vertical = scrlPnl_cuisines.getVerticalScrollBar();
        vertical.setValue(vertical.getValue() + fasterScroll);
    }
    
    /**
     * Handles the gaining focus event on the {@link JTextField}.
     * 
     * @param e the mouse event triggered by gaining focus
     */
    private void txt_FocusGained(java.awt.event.FocusEvent e) 
    {
      for (int i = 0; i < txts.length; i++) 
        if (txts[i].equals(e.getSource())) 
          if (txts[i].getText().equals(PLACEHOLDER[i])) 
          {
            txts[i].setText("");
            txts[i].setForeground(FG_DEFAULT);
            break;
          }      
    }
    
    /**
     * Handles the losing focus event on the {@link JTextField}.
     * 
     * @param e the mouse event triggered by gaining focus
     */
    private void txt_FocusLost(java.awt.event.FocusEvent e) 
    {
      for (int i = 0; i < txts.length; i++) 
        if (txts[i].equals(e.getSource())) 
          if (txts[i].getText().isEmpty()) 
          {
            txts[i].setText(PLACEHOLDER[i]);
            txts[i].setForeground(FG_PLACEHOLDER);
            break;
          }      
    }
    
    /**
     * Handles the click event on the add button {@link JLabel}.
     * <p>
     * When the button is clicked, the restaurant will be added to the {@link ListOwned} of the {@code Restaurateur} and to the dataset.
     * </p>
     * 
     * @param e the mouse event triggered by clicking the button 
     */
    private void btn_add_MouseClicked(java.awt.event.MouseEvent e) 
    {
      boolean error = false;
      boolean[] booleanCuisines = returnCuisinesValues  ();
      boolean[] booleanServices = returnServicesValues  ();
      String cuisines = "";
      String services = "";
      
      KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();      

      if(txt_name       .getText().equals(PLACEHOLDER[0])   || !InputPattern.match(InputPattern.RESTAURANT_NAME,                    txt_name        .getText()))
      {
        error = true;
        txt_name        .setBackground   (Color.RED);
      }
      if(txt_address    .getText().equals(PLACEHOLDER[1])   || !InputPattern.match(InputPattern.RESTAURANT_ADDRESS,                 txt_address     .getText()))
      {
        error = true;
        txt_address     .setBackground   (Color.RED);
      }
      if(txt_country    .getText().equals(PLACEHOLDER[2])   || !InputPattern.match(InputPattern.RESTAURANT_COUNTRY,                 txt_country     .getText()))
      {
        error = true;
        txt_country     .setBackground   (Color.RED);
      }
      if(txt_city       .getText().equals(PLACEHOLDER[3])   || !InputPattern.match(InputPattern.RESTAURANT_CITY,                    txt_city        .getText()))
      {
        error = true;
        txt_city        .setBackground   (Color.RED);
      }
      if(txt_latitude   .getText().equals(PLACEHOLDER[4])   || !InputPattern.match(InputPattern.RESTAURANT_LATITUDE_AND_LONGITUDE,  txt_latitude    .getText()))
      {
        error = true;
        txt_latitude    .setBackground   (Color.RED);
      }     
      if(txt_longitude  .getText().equals(PLACEHOLDER[5])   || !InputPattern.match(InputPattern.RESTAURANT_LATITUDE_AND_LONGITUDE,  txt_longitude   .getText()))
      {
        error = true;
        txt_longitude   .setBackground   (Color.RED);
      }
      if(txt_currency   .getText().equals(PLACEHOLDER[6])   || !InputPattern.match(InputPattern.RESTAURANT_CURRENCY,                txt_currency    .getText()))
      {
        error = true;
        txt_currency    .setBackground   (Color.RED);
      }   
      if(txt_phoneNo    .getText().equals(PLACEHOLDER[7])   || !InputPattern.match(InputPattern.RESTAURANT_PHONE_NUMBER,            txt_phoneNo     .getText()))
      {
        error = true;
        txt_phoneNo     .setBackground   (Color.RED);
      } 
      if(txt_url        .getText().equals(PLACEHOLDER[8])   || !InputPattern.match(InputPattern.RESTAURANT_URL,                     txt_url          .getText()))
      {
        error = true;
        txt_url         .setBackground   (Color.RED);
      }
      if(txt_webUrl     .getText().equals(PLACEHOLDER[9])   || !InputPattern.match(InputPattern.RESTAURANT_WEBURL,                  txt_webUrl       .getText()))
      {
        error = true;
        txt_webUrl      .setBackground   (Color.RED);
      } 
      if(txt_award      .getText().equals(PLACEHOLDER[10]) || txt_award.getText().trim().isEmpty())
      {
        error = true;
        txt_award       .setBackground   (Color.RED);
      }
      if(txt_description.getText().trim().isEmpty())
      {
        error = true;
        txt_description .setBackground   (Color.RED);
      }  
      if(booleanCuisines == null)
      {
        error = true;          
      }
      else
        cuisines = String.join(", ", controller.getListCuisines(booleanCuisines));
      
      if(booleanServices == null)
      {
        error = true;         
      }
      else
        services = String.join(", ", controller.getListServices(booleanServices));
      
      if(indexPrice==0)
      {
        error = true;
      }
      
      if(!error)
      {
        Restaurant restaurant = new Restaurant(                       txt_name          .getText(),
                                                StringUtils.normalize(txt_name          .getText()),
                                                                      indexPrice,
                                                                      txt_currency      .getText(),
                                                                      txt_phoneNo       .getText(),
                                                                      txt_url           .getText(),
                                                                      txt_webUrl        .getText(),
                                                                      txt_award         .getText(),
                                                                      chkbx_greenStar   .isSelected(),
                                                                      cuisines,
                                                                      services,
                                                                      txt_description   .getText(),
                                                                      txt_country       .getText(),
                                                                      txt_city          .getText(),
                                                                      txt_address       .getText(),
                                                Double      .valueOf (txt_latitude      .getText()),
                                                Double      .valueOf (txt_latitude      .getText())
                                              );
        
        controller.addRestaurant            (restaurant);     
        resetPage                           ();
        controller.getPanelMain().showCard  (Page.HOME);         
      }     
    }
    
    /**
     * Handles the hover event on the add button {@link JLabel}.
     * 
     * @param e the mouse event triggered by hovering to the button
     */
    private void btn_add_MouseEntered(java.awt.event.MouseEvent e) 
    {
      btn_add.setCursor    (new Cursor(Cursor.HAND_CURSOR));
      btn_add.setBackground(btn_add.getBackground().darker());
    }
    
    /**
     * Handles the exit hover event on the add button {@link JLabel}.
     * 
     * @param e the mouse event triggered by leaving the cursor from the button
     */
    private void btn_add_MouseExited(java.awt.event.MouseEvent e) 
    {
      btn_add.setBackground(BG_REGISTER_BTN);
    }
    
    /**
     * Handles the click event on the cancel button {@link JLabel}.
     * <p>
     * When the button is clicked, the view switches to the {@code Home} screen canceling the adding procedure.
     * </p>
     * 
     * @param e the mouse event triggered by clicking the button 
     */
    private void btn_cancel_MouseClicked(java.awt.event.MouseEvent e) 
    {
      resetPage();
      controller.getPanelMain().showCard(Page.HOME);
      controller.getPanelMain().getPanel().remove(this);
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
    
    /**
     * Handles the click event on the price {@link JLabel}.
     * <p>
     * When the label is clicked, it process the price interval.
     * </p>
     * 
     * @param e the mouse event triggered by clicking the label 
     */
    private void lbl_price_MouseClicked(java.awt.event.MouseEvent e) 
    {
      prevIndexPrice = indexPrice;
      for (int i = 0; i < lbl_prices.length; i++) 
        if (lbl_prices[i].equals(e.getSource())) 
        {
          indexPrice = i;
           break;
        }
    
      if (priceClicked) 
      {       
        if (prevIndexPrice != indexPrice)
          lbl_prices[prevIndexPrice].setBackground(BG_PRICE_LBL);
      } 
      else 
        lbl_prices[indexPrice].setBackground(BG_PRICE_DARKER);
      priceClicked = true;
    }
    
    /**
     * Handles the hover event on the price {@link JLabel}.
     * 
     * @param e the mouse event triggered by hovering to the label
     */
    private void lbl_price_MouseMoved(java.awt.event.MouseEvent e)
    {
      for (JLabel lbl : lbl_prices) 
        if (lbl.equals(e.getSource())) 
        {
          lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
          lbl.setBackground(BG_PRICE_DARKER);
        }      
    }
    
    /**
     * Handles the exit hover event on the price {@link JLabel}.
     * 
     * @param e the mouse event triggered by leaving the cursor from the label
     */
    private void lbl_price_MouseExited(java.awt.event.MouseEvent e)
    {
      for (JLabel lbl : lbl_prices) 
        lbl.setBackground(BG_PRICE_LBL);
        
      if (priceClicked)
        for (int i = 0; i < indexPrice; i++) 
          lbl_prices[indexPrice].setBackground(BG_PRICE_DARKER);
    }
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * Clears textfields of any previous data or state.
     */
    private void resetPage()
    {
      JTextField[] fields = 
      {
        txt_name,
        txt_address,
        txt_country,
        txt_city,
        txt_latitude,
        txt_longitude,
        txt_currency,
        txt_phoneNo,
        txt_url,
        txt_webUrl
      };
        
      for (int i = 0; i < fields.length; i++) 
      {
        fields[i].setText       (PLACEHOLDER[i]);
        fields[i].setForeground (FG_PLACEHOLDER);
        fields[i].setBackground (BG_TEXTFIELD);
      }
    }  
    
    /**
     * Returns all cuisines values whether they are selected or otherwise.
     * 
     * @return all cuisines {@code boolean} value based on selection (item selected)
     */
    private boolean[] returnCuisinesValues()
    {
      boolean[] cuisines= new boolean[chkbx_cuisines.length];
      
      boolean flag = false;
      for(int i=0; i<chkbx_cuisines.length; i++)
      {
        cuisines[i] = chkbx_cuisines[i].isSelected();
        if(chkbx_cuisines[i].isSelected())
          flag = true;
      }
      
      return (flag) ? cuisines:null;
    }
    
    /**
     * Returns all services values whether they are selected or otherwise.
     * 
     * @return all services {@code boolean} value based on selection (item selected)
     */
    private boolean[] returnServicesValues()
    {
      boolean[] services = new boolean[chkbx_services.length];
      
      boolean flag = false;
      for(int i=0; i<chkbx_services.length; i++)
      {
        services[i] = chkbx_services[i].isSelected();
        if(chkbx_services[i].isSelected())
          flag = true;
      }
      
      return (flag) ? services:null;
    }
    //</editor-fold>
    
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(153, 255, 102));
        setFont(new java.awt.Font("Consolas", 0, 16)); // NOI18N

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
    private final File               PROGRAM_DATASET           = AppPaths.getRequiredFile("data", "program_dataset.csv");
    private final Color              FG_DEFAULT                = Color.BLACK;
    private final Color              FG_PLACEHOLDER            = Color.GRAY;
    private final Color              BG_REGISTER_BTN           = new Color(0, 255, 0, 192);
    private final Color              BG_CANCEL_BTN             = new Color(255, 64, 0, 192);
    private final Color              BG_PRICE_LBL              = new Color(85, 191, 33);
    private final Color              BG_PNL_GRID               = new Color(95, 199, 40);
    private final Color              BG_PNL_CHKBXS             = new Color(61, 166, 5);
    private final Color              BG_TEXTFIELD              = new Color(255, 255, 255, 192);
    private final Color              BG_TEXTAREA               = new Color(29, 158, 14);
    private final Color              BG_PRICE_DARKER           = new Color(74, 150, 36);
    private final Border             PADDING_TEXTFIELD         = BorderFactory.createEmptyBorder(0, 10, 0, 10);
    private final Border             PADDING_TEXTAREA          = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    private final Border             PADDING_PANEL_CHKBXS      = BorderFactory.createEmptyBorder(20, 20, 20, 20);
    private final Insets             INSETS                    = new Insets(5, 5, 5, 5);
    private final String[]           CHKBX_CUISINE_TXT         = CSV.read(PROGRAM_DATASET, "CUISINES")            .toArray(new String[0]);
    private final String[]           CHKBX_SERVICE_TXT         = CSV.read(PROGRAM_DATASET, "SERVICES")            .toArray(new String[0]);
    private final String[]           LBL_GUIDE_TXT             = CSV.read(PROGRAM_DATASET, "GUIDES_ADDRESTAURANT").toArray(new String[0]);
    private final String[]           PRICE_TAGS                = CSV.read(PROGRAM_DATASET, "PRICE_TAGS")          .toArray(new String[0]);
    private final String[]           PLACEHOLDER               = {
        "Name",
        "Address",
        "Country",
        "City",
        "Latitude",
        "Longitude",
        "Currency",
        "Phone number",
        "Url",
        "Website url",
        "Award"
    };
    private final String             TITLE                     = "Add Restaurant";
    private final String             ADD                       = "Add";
    private final String             CANCEL                    = "Cancel";
    private final char               EMPTY_STAR                = 'A';
    private final char               FULL_STAR                 = 'C';
    private final int                SCRLPNL_CUISINES_HEIGHT   = 600;
    private final int                ARC_BUTTON                = 50;
    private final int                ARC_TEXTFIELD             = 50;
    private final int                ARC_PANEL                 = 50;
    private final int                TEXTFIELD_HEIGHT          = 30;
    private final int                TEXTAREA_HEIGHT           = 240;
    private final int                PRICES                    = 4;
    private final int                PNL_BTNS_HEIGHT           = 80;
    private final RoundedComponentUI TXT_LAYER_UI              = new RoundedComponentUI(ARC_TEXTFIELD);
    private final RoundedComponentUI BTN_LAYER_UI              = new RoundedComponentUI(ARC_BUTTON);
    private final RoundedComponentUI PNL_LAYER_UI              = new RoundedComponentUI(ARC_PANEL);
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Components">
    private JPanel             pnl_grid;
    private JPanel             pnl_btns;
    private JPanel             pnl_btn_add;
    private JPanel             pnl_btn_cancel;
    private JPanel             pnl_priceBar;
    private JPanel             pnl_cuisines;
    private JPanel             pnl_services;
    private JScrollPane        scrlPnl_grid;
    private JScrollPane        scrlPnl_cuisines;
    private JScrollPane        scrlPnl_description;
    private JLabel             lbl_title;
    private JLabel[]           lbl_prices;
    private JLabel             btn_add;
    private JLabel             btn_cancel;
    private JTextField         txt_name;
    private JTextField         txt_address;
    private JTextField         txt_country;
    private JTextField         txt_city;
    private JTextField         txt_latitude;
    private JTextField         txt_longitude;
    private JTextField         txt_currency;
    private JTextField         txt_phoneNo;
    private JTextField         txt_url;
    private JTextField         txt_webUrl;
    private JTextField         txt_award;
    private JTextField[]       txts;
    private JTextArea          txt_description;
    private CustomJCheckBox    chkbx_greenStar;
    private JCheckBox[]        chkbx_cuisines; 
    private JCheckBox[]        chkbx_services; 
    private JLayer<JComponent> scrlPnl_gridRounded;
    private JLayer<JComponent> txt_nameRounded;
    private JLayer<JComponent> txt_addressRounded;
    private JLayer<JComponent> txt_countryRounded;
    private JLayer<JComponent> txt_cityRounded;
    private JLayer<JComponent> txt_latitudeRounded;
    private JLayer<JComponent> txt_longitudeRounded;
    private JLayer<JComponent> txt_currencyRounded;
    private JLayer<JComponent> txt_phoneNoRounded;
    private JLayer<JComponent> txt_urlRounded;
    private JLayer<JComponent> txt_webUrlRounded;
    private JLayer<JComponent> btn_addRounded;
    private JLayer<JComponent> btn_cancelRounded;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Fields">
    private final Controller controller;
    private       boolean    priceClicked;
    private       int        indexPrice = -1;
    private       int        prevIndexPrice;
    //</editor-fold>
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
