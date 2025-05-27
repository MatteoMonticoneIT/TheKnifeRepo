package theknife.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import simple.file.CSV;
import theknife.Controller;
import theknife.obj.AppPaths;
import theknife.gui.PanelMain;

/**
 * A panel that represents the advanced search screen in the application.
 * <p>
 * This class contains the graphical elements for the advanced search interface, including a button that switches the view to the home screen when clicked.<br>
 * It is managed by the {@link PanelMain} class using a {@link CardLayout}.
 * </p>
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
*/
public final class AdvancedSearch extends javax.swing.JPanel 
{

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Creates a new {@code AdvancedSearch} {@link JPanel} and initializes it's components.
     * <p>
     * This constructor also sets the main panel to control the screen transitions using {@link PanelMain#showCard}.
     * </p>
     *
     * @param controller the {@link Controller} class that manages the screen layout
     */
    public AdvancedSearch(Controller controller) 
    {
      initComponents();
      this.controller = controller;
      initGUI       ();
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Initialization">
    /**
     * Initializes the graphical user interface (GUI) for the {@code AdvancedSearch} page.
     */
    private void initGUI() 
    {
      initFields();
      initAdvancedSearch();
      initEvents();
    }
    
    /**
     * Initializes the basic fields of the {@code AdvancedSearch} panel.
     */
    private void initFields() 
    {
      pnl_filters            = new JPanel(new GridBagLayout());
      pnl_btn_apply          = new JPanel(new BorderLayout());
      pnl_btn_cancel         = new JPanel(new BorderLayout());
      pnl_ratingBar          = new JPanel(new GridLayout(1, RATINGS));
      pnl_priceBar           = new JPanel(new GridLayout(1, PRICES));
      pnl_btns               = new JPanel(new GridLayout(1, 2, 10, 10));
      pnl_cuisines           = new JPanel(new GridLayout((int) Math.ceil(CHKBX_CUISINE_TXT.length / 2), 2, 10, 10));
      pnl_services           = new JPanel(new GridLayout((int) Math.ceil(CHKBX_SERVICE_TXT.length / 2), 2, 10, 10));
      scrlPnl_filters        = new JScrollPane(pnl_filters,  JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      scrlPnl_cuisines       = new JScrollPane(pnl_cuisines, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      lbl_title              = new JLabel(TITLE);
      lbl_stars              = new CustomJLabel[RATINGS];
      lbl_prices             = new JLabel[PRICES];
      btn_apply              = new JLabel(APPLY_FILTERS);
      btn_cancel             = new JLabel(CANCEL);
      txt_location           = new JTextField(PLACEHOLDER[0]);
      chkbx_cuisines         = new JCheckBox[CHKBX_CUISINE_TXT.length];
      chkbx_services         = new JCheckBox[CHKBX_SERVICE_TXT.length];
      scrlPnl_filtersRounded = new JLayer<>(scrlPnl_filters, PNL_LAYER_UI);
      txt_locationRounded    = new JLayer<>(txt_location,    TXT_LAYER_UI);
      btn_applyRounded       = new JLayer<>(btn_apply,       BTN_LAYER_UI);
      btn_cancelRounded      = new JLayer<>(btn_cancel,      BTN_LAYER_UI);
    }
    
    /**
     * Initializes the layout and appearance of the {@code AdvancedSearch} page.
     */
    private void initAdvancedSearch() 
    {
      this.setLayout(new BorderLayout());
       
      pnl_filters   .setBackground(BG_PNL_FILTERS);
       
      pnl_cuisines  .setBackground(BG_PNL_CHKBXS);
      pnl_cuisines  .setBorder    (PADDING_PANEL_CHKBXS);
       
      pnl_services  .setBackground(BG_PNL_CHKBXS);
      pnl_services  .setBorder    (PADDING_PANEL_CHKBXS);
       
      pnl_ratingBar .setBackground(BG_STAR_LBL);
       
      pnl_priceBar  .setBackground(BG_PRICE_LBL);
        
      pnl_btns      .setBackground(this.getBackground());
        
      pnl_btn_apply .setBackground(pnl_btns.getBackground());
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
        
      scrlPnl_filters .getVerticalScrollBar()      .setUI(new CustomJScrollBar());
      scrlPnl_filters .getHorizontalScrollBar()    .setUI(new CustomJScrollBar());
      scrlPnl_filters .setBorder                   (BorderFactory.createEmptyBorder());
        
      scrlPnl_cuisines.getVerticalScrollBar()      .setUI(new CustomJScrollBar());
      scrlPnl_cuisines.getHorizontalScrollBar()    .setUI(new CustomJScrollBar());
      scrlPnl_cuisines.setPreferredSize            (new Dimension(0, SCRLPNL_CUISINES_HEIGHT));
      scrlPnl_cuisines.setBorder                   (BorderFactory.createEmptyBorder());
        
      lbl_title.setBackground           (this.getBackground());
      lbl_title.setForeground           (FG_DEFAULT);
      lbl_title.setHorizontalAlignment  (JLabel.CENTER);
      lbl_title.setVerticalAlignment    (JLabel.CENTER);
      lbl_title.setFont                 (new Font(this.getFont().getFontName(), this.getFont().getStyle(), 48));
      lbl_title.setOpaque               (true);
        
      for (int i = 0; i < lbl_stars.length; i++) 
      {
        lbl_stars[i] = new CustomJLabel("", EMPTY_STAR);
        lbl_stars[i].setBackground          (BG_STAR_LBL);
        lbl_stars[i].setForeground          (FG_DEFAULT);
        lbl_stars[i].setCharacterColor      (BG_STAR_CHAR);
        lbl_stars[i].setHorizontalAlignment (JLabel.CENTER);
        lbl_stars[i].setVerticalAlignment   (JLabel.CENTER);
        lbl_stars[i].setFont                (this.getFont());
        lbl_stars[i].setCustomFontSize      (64f);
        lbl_stars[i].setOpaque              (true);
        pnl_ratingBar.add                   (lbl_stars[i]);
      }
        
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
        
      txt_location.setBackground    (BG_TEXTFIELD);
      txt_location.setForeground    (FG_PLACEHOLDER);
      txt_location.setBorder        (PADDING_TEXTFIELD);
      txt_location.setPreferredSize (new Dimension(this.getPreferredSize().width, TEXTFIELD_HEIGHT));
      txt_location.setFont          (new Font(this.getFont().getFontName(), this.getFont().getStyle(), 28));
        
      btn_apply.setBackground           (BG_APPLY_BTN);
      btn_apply.setForeground           (FG_DEFAULT);
      btn_apply.setHorizontalAlignment  (JLabel.CENTER);
      btn_apply.setVerticalAlignment    (JLabel.CENTER);
      btn_apply.setFont                 (new Font(this.getFont().getFontName(), this.getFont().getStyle(), 28));
      btn_apply.setOpaque               (true);
        
      btn_cancel.setBackground          (BG_CANCEL_BTN);
      btn_cancel.setForeground          (FG_DEFAULT);
      btn_cancel.setHorizontalAlignment (JLabel.CENTER);
      btn_cancel.setVerticalAlignment   (JLabel.CENTER);
      btn_cancel.setFont                (new Font(this.getFont().getFontName(), this.getFont().getStyle(), 28));
      btn_cancel.setOpaque              (true);
       
      pnl_btn_apply .add(btn_applyRounded,  BorderLayout.CENTER);
      pnl_btn_cancel.add(btn_cancelRounded, BorderLayout.CENTER);
      
      pnl_btns.setPreferredSize (new Dimension(this.getWidth(), PNL_BTNS_HEIGHT));
      pnl_btns.add              (pnl_btn_apply,  BorderLayout.CENTER);
      pnl_btns.add              (pnl_btn_cancel, BorderLayout.EAST);
      
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
      gbc.weightx   = 0.3;
      gbc.weighty   = 1;
      gbc.gridwidth = 1;
      gbc.fill      = GridBagConstraints.BOTH;
      gbc.insets    = INSETS;
      pnl_filters.add(lbl_guides[0], gbc);
        
      gbc.gridx++;
      gbc.weightx = 0.7;
      pnl_filters.add(pnl_ratingBar, gbc);
        
      gbc.gridy++;
      gbc.gridx--;
      gbc.weightx = 1;
      gbc.gridwidth++;
      pnl_filters.add(lbl_guides[1], gbc);
        
      gbc.gridy++;
      pnl_filters.add(txt_locationRounded, gbc);
        
      gbc.gridy++;
      gbc.gridwidth--;
      gbc.weightx = 0.3;
      pnl_filters.add(lbl_guides[2], gbc);
       
      gbc.gridx++;
      gbc.weightx = 0.7;
      pnl_filters.add(pnl_priceBar, gbc);
        
      gbc.gridy++;
      gbc.gridx--;
      gbc.weightx = 1;
      gbc.gridwidth++;
      pnl_filters.add(lbl_guides[3], gbc);
        
      gbc.gridy++;
      pnl_filters.add(scrlPnl_cuisines, gbc);
       
      gbc.gridy++;
      pnl_filters.add(lbl_guides[4], gbc);
        
      gbc.gridy++;
      pnl_filters.add(pnl_services, gbc);
        
      this.add(lbl_title,              BorderLayout.NORTH);
      this.add(scrlPnl_filtersRounded, BorderLayout.CENTER);
      this.add(pnl_btns,               BorderLayout.SOUTH);
        
    }
    
    /**
     * Sets up event listeners for user interaction.
     */
    private void initEvents() 
    {
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                advancedSearch_ComponentResized(e);
            }
        });
        
        scrlPnl_filters.addMouseWheelListener((java.awt.event.MouseWheelEvent e) -> {
            scrlPnl_filters_MouseWheelMoved(e);
        });
        
        scrlPnl_cuisines.addMouseWheelListener((java.awt.event.MouseWheelEvent e) -> {
            scrlPnl_cuisines_MouseWheelMoved(e);
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
        
        btn_apply.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                btn_apply_MouseClicked(e);
            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn_apply_MouseEntered(e);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn_apply_MouseExited(e);
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
        
        for (JLabel lbl : lbl_stars) {
            lbl.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    lbl_star_MouseClicked(e);
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    lbl_star_MouseExited(e);
                }
            
            });
            
            lbl.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                @Override
                public void mouseMoved(java.awt.event.MouseEvent e) {
                    lbl_star_MouseMoved(e);
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
     * Handles the resize event for the {@code AdvancedSearch} {@link JPanel}.
     * <p>
     * When resized, resizes the padding of the components.
     * </p>
     * 
     * @param e the component event triggered by resizing the GUI application
     */
    private void advancedSearch_ComponentResized(java.awt.event.ComponentEvent e) {
        int[] padding = {(int) (this.getWidth() * 0.005), (int) (this.getHeight() * 0.0025)};
        this.setBorder(BorderFactory.createEmptyBorder(padding[1], padding[0], padding[1], padding[0]));
        
        final int PADDING_BTN = (int) (this.getWidth() * 0.005);
        pnl_btn_apply .setBorder(BorderFactory.createEmptyBorder(PADDING_BTN, PADDING_BTN, PADDING_BTN, PADDING_BTN));
        pnl_btn_cancel.setBorder(BorderFactory.createEmptyBorder(PADDING_BTN, PADDING_BTN, PADDING_BTN, PADDING_BTN));
    }
    
    /**
     * Handles the {@link JScrollPane} {@link JScrollBar}.
     * 
     * @param e the component event triggered by wheel-scrolling using the mouse.
     */
    private void scrlPnl_filters_MouseWheelMoved(java.awt.event.MouseWheelEvent e) {
        int notches = e.getWheelRotation();
        int fasterScroll = notches * 40;

        JScrollBar vertical = scrlPnl_filters.getVerticalScrollBar();
        vertical.setValue(vertical.getValue() + fasterScroll);
    }
    
    /**
     * Handles the {@link JScrollPane} {@link JScrollBar}.
     * 
     * @param e the component event triggered by wheel-scrolling using the mouse.
     */
    private void scrlPnl_cuisines_MouseWheelMoved(java.awt.event.MouseWheelEvent e) {
        int notches = e.getWheelRotation();
        int fasterScroll = notches * 40;

        JScrollBar vertical = scrlPnl_cuisines.getVerticalScrollBar();
        vertical.setValue(vertical.getValue() + fasterScroll);
    }
    
    /**
     * Handles the gaining focus event on the location {@link JTextField}.
     * 
     * @param e the mouse event triggered by gaining focus
     */
    private void txt_location_FocusGained(java.awt.event.FocusEvent e) {
        if (txt_location.getText().equals(PLACEHOLDER[0])) {
            txt_location.setText("");
            txt_location.setForeground(FG_DEFAULT);
        }
    }
    
    /**
     * Handles the losing focus event on the location {@link JTextField}.
     * 
     * @param e the mouse event triggered by gaining focus
     */
    private void txt_location_FocusLost(java.awt.event.FocusEvent e) {
        if (txt_location.getText().isEmpty()) {
            txt_location.setText(PLACEHOLDER[0]);
            txt_location.setForeground(FG_PLACEHOLDER);
        }
    }
    
    /**
     * Handles the click event on the cancel button {@link JLabel}.
     * <p>
     * When the button is clicked, the view switches to the {@code Home} screen.
     * </p>
     * 
     * @param e the mouse event triggered by clicking the button 
     */
    private void btn_cancel_MouseClicked(java.awt.event.MouseEvent e) {
        txt_location.setText(PLACEHOLDER[0]);
        txt_location.setForeground(FG_PLACEHOLDER);
        controller.getPanelMain().showCard(Page.HOME);
    }
    
    /**
     * Handles the hover event on the cancel button {@link JLabel}.
     * 
     * @param e the mouse event triggered by hovering to the button
     */
    private void btn_cancel_MouseEntered(java.awt.event.MouseEvent e) {
        btn_cancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn_cancel.setBackground(btn_cancel.getBackground().darker());
    }
    
    /**
     * Handles the exit hover event on the cancel button {@link JLabel}.
     * 
     * @param e the mouse event triggered by leaving the cursor from the button
     */
    private void btn_cancel_MouseExited(java.awt.event.MouseEvent e) {
        btn_cancel.setBackground(BG_CANCEL_BTN);
    }
    
    /**
     * Handles the click event on the apply button {@link JLabel}.
     * <p>
     * When the button is clicked, the filters will be applied and the view switches to the {@code Home} screen.
     * </p>
     * 
     * @param e the mouse event triggered by clicking the button 
     */
    private void btn_apply_MouseClicked(java.awt.event.MouseEvent e) 
    {
      txt_location.setText          (PLACEHOLDER[0]);
      txt_location.setForeground    (FG_PLACEHOLDER);
      controller  .advancedSearch   ((txt_location.getText().trim().isEmpty()) ? null:(txt_location.getText().equals(PLACEHOLDER[0])) ? null:txt_location.getText(), 
                                     (starClicked)  ? starRating    :null, 
                                     (priceClicked) ? indexPrice+1  :null, 
                                     returnCuisineValues    (), 
                                     returnServicesValues   ());
      controller  .getPanelMain     ().showCard(Page.HOME);
    }
    
    /**
     * Handles the hover event on the apply button {@link JLabel}.
     * 
     * @param e the mouse event triggered by hovering to the button
     */
    private void btn_apply_MouseEntered(java.awt.event.MouseEvent e) {
        btn_apply.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn_apply.setBackground(btn_apply.getBackground().darker());
    }
    
    /**
     * Handles the exit hover event on the apply button {@link JLabel}.
     * 
     * @param e the mouse event triggered by leaving the cursor from the button
     */
    private void btn_apply_MouseExited(java.awt.event.MouseEvent e) {
        btn_apply.setBackground(BG_APPLY_BTN);
    }
    
    /**
     * Handles the click event on the star {@link JLabel}.
     * <p>
     * When the label is clicked, it checks if the rating given has a half star or not.
     * </p>
     * 
     * @param e the mouse event triggered by clicking the label 
     */
    private void lbl_star_MouseClicked(java.awt.event.MouseEvent e) {
        starClicked = true;
        for (int i = 0; i < lbl_stars.length; i++) {
            if (lbl_stars[i].equals(e.getSource())) {
                indexStar = i;
                lbl_stars[i].setCharacter(e.getX() <= lbl_stars[i].getWidth() / 2 ? HALF_STAR : FULL_STAR);
                starRating = e.getX() <= lbl_stars[i].getWidth() / 2 ? i + 0.5 : i + 1;
                break;
            }
            lbl_stars[i].setCharacter(FULL_STAR);
        }
    }
    
    /**
     * Handles the hover event on the stars {@link JLabel}.
     * 
     * @param e the mouse event triggered by hovering to the label
     */
    private void lbl_star_MouseMoved(java.awt.event.MouseEvent e) {
        for (CustomJLabel lbl : lbl_stars) 
            lbl.setCharacter(EMPTY_STAR);
            
        for (CustomJLabel lbl : lbl_stars) {
            if (lbl.equals(e.getSource())) {
                lbl.setCharacter(e.getX() <= lbl.getWidth() / 2 ? HALF_STAR : FULL_STAR);
                break;
            }
            lbl.setCharacter(FULL_STAR);
        }
    }
    
    /**
     * Handles the exit hover event on the stars {@link JLabel}.
     * 
     * @param e the mouse event triggered by leaving the cursor from the label
     */
    private void lbl_star_MouseExited(java.awt.event.MouseEvent e) {
        for (CustomJLabel lbl : lbl_stars) 
            lbl.setCharacter(EMPTY_STAR);
        
        if (starClicked) {
            for (int i = 0; i < indexStar + 1; i++) 
                lbl_stars[i].setCharacter(FULL_STAR);
            if (starRating - indexStar == 0.5)
                lbl_stars[indexStar].setCharacter(HALF_STAR);
        }
    }
    
    /**
     * Handles the click event on the price {@link JLabel}.
     * <p>
     * When the label is clicked, it process the price interval.
     * </p>
     * 
     * @param e the mouse event triggered by clicking the label 
     */
    private void lbl_price_MouseClicked(java.awt.event.MouseEvent e) {
        prevIndexPrice = indexPrice;
        for (int i = 0; i < lbl_prices.length; i++) {
            if (lbl_prices[i].equals(e.getSource())) {
                indexPrice = i;
                break;
            }
        }
        if (priceClicked) {
            if (prevIndexPrice != indexPrice)
                lbl_prices[prevIndexPrice].setBackground(BG_PRICE_LBL);
        } else {
            lbl_prices[indexPrice].setBackground(BG_PRICE_DARKER);
        }
        priceClicked = true;
    }
    
    /**
     * Handles the hover event on the price {@link JLabel}.
     * 
     * @param e the mouse event triggered by hovering to the label
     */
    private void lbl_price_MouseMoved(java.awt.event.MouseEvent e) {
        for (JLabel lbl : lbl_prices) {
            if (lbl.equals(e.getSource())) {
                lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
                lbl.setBackground(BG_PRICE_DARKER);
            }
        }
    }
    
    /**
     * Handles the exit hover event on the price {@link JLabel}.
     * 
     * @param e the mouse event triggered by leaving the cursor from the label
     */
    private void lbl_price_MouseExited(java.awt.event.MouseEvent e) {
        for (JLabel lbl : lbl_prices) 
            lbl.setBackground(BG_PRICE_LBL);
        
        if (priceClicked)
            for (int i = 0; i < indexPrice; i++) 
                lbl_prices[indexPrice].setBackground(BG_PRICE_DARKER);
    }
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Methods"> 
    /**
     * Returns all cuisines values whether they are selected or otherwise.
     * 
     * @return all cuisines {@code boolean} value based on selection (item selected)
     */
    private boolean[] returnCuisineValues()
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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(153, 255, 102));
        setFont(new java.awt.Font("Consolas", 0, 16)); // NOI18N
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
    private final File               PROGRAM_DATASET         = AppPaths.getRequiredFile("data", "program_dataset.csv");
    private final Color              FG_DEFAULT              = Color.BLACK;
    private final Color              FG_PLACEHOLDER          = Color.GRAY;
    private final Color              BG_TEXTFIELD            = new Color(255, 255, 255, 192);
    private final Color              BG_APPLY_BTN            = new Color(0, 255, 0, 192);
    private final Color              BG_CANCEL_BTN           = new Color(255, 64, 0, 192);
    private final Color              BG_PNL_FILTERS          = new Color(95, 199, 40);
    private final Color              BG_PNL_CHKBXS           = new Color(61, 166, 5);
    private final Color              BG_STAR_LBL             = new Color(85, 191, 33);
    private final Color              BG_STAR_CHAR            = new Color(255, 215, 0);
    private final Color              BG_PRICE_LBL            = new Color(85, 191, 33);
    private final Color              BG_PRICE_DARKER         = new Color(74, 150, 36);
    private final Border             PADDING_TEXTFIELD       = BorderFactory.createEmptyBorder(0, 10, 0, 10);
    private final Border             PADDING_PANEL_CHKBXS    = BorderFactory.createEmptyBorder(20, 20, 20, 20);
    private final Insets             INSETS                  = new Insets(20, 10, 20, 10);
    private final String[]           CHKBX_CUISINE_TXT       = CSV.read(PROGRAM_DATASET, "CUISINES")             .toArray(new String[0]);
    private final String[]           CHKBX_SERVICE_TXT       = CSV.read(PROGRAM_DATASET, "SERVICES")             .toArray(new String[0]);
    private final String[]           LBL_GUIDE_TXT           = CSV.read(PROGRAM_DATASET, "GUIDES_ADVANCEDSEARCH").toArray(new String[0]);
    private final String[]           PRICE_TAGS              = CSV.read(PROGRAM_DATASET, "PRICE_TAGS")           .toArray(new String[0]);
    private final String[]           PLACEHOLDER             = new String[]{"Your location"};
    private final String             TITLE                   = "Filters";
    private final String             APPLY_FILTERS           = "Apply filters";
    private final String             CANCEL                  = "Cancel";
    private final char               EMPTY_STAR              = 'A';
    private final char               HALF_STAR               = 'B';
    private final char               FULL_STAR               = 'C';
    private final int                ARC_PANEL               = 50;
    private final int                ARC_TEXTFIELD           = 50;
    private final int                ARC_BUTTON              = 50;
    private final int                PRICES                  = 4;
    private final int                RATINGS                 = 5;
    private final int                TEXTFIELD_HEIGHT        = 40;
    private final int                SCRLPNL_CUISINES_HEIGHT = 600;
    private final int                PNL_BTNS_HEIGHT         = 80;
    private final RoundedComponentUI TXT_LAYER_UI            = new RoundedComponentUI(ARC_TEXTFIELD);
    private final RoundedComponentUI BTN_LAYER_UI            = new RoundedComponentUI(ARC_BUTTON);
    private final RoundedComponentUI PNL_LAYER_UI            = new RoundedComponentUI(ARC_PANEL);
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Components">
    private JPanel             pnl_filters;
    private JPanel             pnl_btn_apply;
    private JPanel             pnl_btn_cancel;
    private JPanel             pnl_ratingBar;
    private JPanel             pnl_priceBar;
    private JPanel             pnl_btns;
    private JPanel             pnl_cuisines;
    private JPanel             pnl_services;
    private JScrollPane        scrlPnl_filters;
    private JScrollPane        scrlPnl_cuisines;
    private JLabel             lbl_title;
    private CustomJLabel[]     lbl_stars;
    private JLabel[]           lbl_prices;
    private JLabel             btn_apply;
    private JLabel             btn_cancel;
    private JTextField         txt_location;
    private JCheckBox[]        chkbx_cuisines; 
    private JCheckBox[]        chkbx_services; 
    private JLayer<JComponent> scrlPnl_filtersRounded;
    private JLayer<JComponent> txt_locationRounded;
    private JLayer<JComponent> btn_applyRounded;
    private JLayer<JComponent> btn_cancelRounded;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Fields">
    private final Controller controller;
    private       boolean    starClicked;
    private       boolean    priceClicked;
    private       double     starRating;
    private       int        indexStar  = -1;
    private       int        indexPrice = -1;
    private       int        prevIndexPrice;
    //</editor-fold>
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
