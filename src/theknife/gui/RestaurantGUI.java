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
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import simple.file.CSV;
import theknife.Controller;
import theknife.obj.AppPaths;
import theknife.obj.restaurant.Restaurant;

/**
 *
 * @author Matteo Monticone
 */
public class RestaurantGUI extends javax.swing.JPanel
{

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Creates a new {@code RestaurantGUI} panel and initializes its components.
     * <p>
     * This constructor also sets the restaurant to insert the data needed to have a preview of the restaurant itself.
     * </p>
     *
     * @param controller the {@link Controller} class that manages the screen layout
     * @param restaurant the {@link Restaurant} to set for the {@code RestaurantGUI}
     */
    public RestaurantGUI(Controller controller, Restaurant restaurant) 
    {
        initComponents();
        this.controller = controller;
        this.restaurant = restaurant;
        initGUI();
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Initialization">
    /**
     * Initializes the graphical user interface (GUI) for the {@code PreviewRestaurant} page.
     */
    private void initGUI() 
    {
        initFields();
        initPreviewRestaurant();
        initEvents();
    }
    
    /**
     * Initializes the basic fields of the {@code PreviewRestaurant} panel.
     */
    private void initFields() 
    {
        pnl_content           = new JPanel(new GridBagLayout());
        pnl_leftContent       = new JPanel(new GridBagLayout());
        pnl_leftSection       = new JPanel(new GridBagLayout());
        pnl_section           = new JPanel(new GridBagLayout());
        pnl_reviews           = new JPanel(new GridLayout(10, 1, 0, 10));
        pnl_btns              = new JPanel(new GridLayout(1, 2, 0, 10));
        pnl_btn_addReview     = new JPanel(new BorderLayout());
        pnl_btn_back          = new JPanel(new BorderLayout());
        lbl_name              = new JLabel(restaurant.getName());
        lbl_price             = new JLabel(String.valueOf(restaurant.getPrice()));
        lbl_currency          = new JLabel(restaurant.getCurrency());
        lbl_phoneNo           = new JLabel(restaurant.getPhoneNumber());
        lbl_location          = new JLabel(restaurant.getCountry() + ", " + restaurant.getCity());
        lbl_address           = new JLabel(wrapTextHTML(restaurant.getAddress()));
        lbl_latitude          = new JLabel(String.valueOf(restaurant.getLatitude()));
        lbl_longitude         = new JLabel(String.valueOf(restaurant.getLongitude()));
        lbl_url               = new JLabel(restaurant.getUrl());
        lbl_webUrl            = new JLabel(restaurant.getWebsiteUrl());
        lbl_award             = new JLabel(restaurant.getAward());
        lbl_greenStar         = new JLabel(restaurant.isGreenStar() ? GREENSTAR_TRUE : GREENSTAR_FALSE);
        lbl_services          = new JLabel(wrapTextHTML(restaurant.getServicesAvailable()));
        lbl_rating            = new JLabel("Overall rating: " + String.valueOf(restaurant.getRating()));
        lbls                  = new JLabel[] 
        {
            lbl_name,
            lbl_price,
            lbl_currency,
            lbl_phoneNo,
            lbl_location,
            lbl_address,
            lbl_latitude,
            lbl_longitude,
            lbl_url,
            lbl_webUrl,
            lbl_award,
            lbl_greenStar,
            lbl_services,
            lbl_rating
        };
        lbls_leftSection      = new JLabel[] 
        {
            lbl_price,
            lbl_location,
            lbl_phoneNo,
            lbl_award,
            lbl_greenStar
        };
        lbls_section          = new JLabel[] 
        {
            lbl_address,
            lbl_url,
            lbl_webUrl,
            lbl_services
        };
        btn_addReview         = new JLabel     (ADD_REVIEW);
        btn_back              = new JLabel     (BACK);
        btn_addReviewRounded  = new JLayer<>   (btn_addReview, BTN_LAYERUI);
        btn_backRounded       = new JLayer<>   (btn_back,      BTN_LAYERUI);
        txt_description       = new JTextArea  (restaurant.getDescription());
        scrlPnl_description   = new JScrollPane(txt_description, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrlPnl_reviews       = new JScrollPane(pnl_reviews,     JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }
    
    /**
     * Initializes the layout and appearance of the home page.
     */
    private void initPreviewRestaurant() 
    {
        this.setLayout(new BorderLayout());
        
        pnl_leftSection.setBackground(this.getBackground());
        pnl_section    .setBackground(this.getBackground());
        
        pnl_leftContent.setPreferredSize(new Dimension(LEFT_CONTENT_WIDTH, 0));
        
        pnl_reviews.setBackground(BG_DEFAULT);
        
        pnl_btns.setBackground(this.getBackground());
        pnl_btns.setBorder    (BorderFactory.createEmptyBorder());
        
        pnl_btn_addReview.setBackground(this.getBackground());
        pnl_btn_back     .setBackground(this.getBackground());
        
        for (JLabel lbl : lbls) 
        {
            lbl.setBackground           (BG_DEFAULT);
            lbl.setForeground           (FG_DEFAULT);
            lbl.setHorizontalAlignment  (JLabel.LEFT);
            lbl.setVerticalAlignment    (JLabel.CENTER);
            lbl.setFont                 (this.getFont());
            lbl.setBorder               (PADDING_LBL);
            lbl.setOpaque               (true);
        }
        lbl_name.setBackground          (BG_NAME);
        lbl_name.setHorizontalAlignment (JLabel.CENTER);
        lbl_name.setPreferredSize       (new Dimension(0, LBL_NAME_HEIGHT));
        
        lbl_rating.setFont(new Font(this.getFont().getFontName(), this.getFont().getStyle(), 28));
        lbl_rating.setHorizontalAlignment(JLabel.CENTER);
        
        scrlPnl_description.setBackground                     (this.getBackground());
        scrlPnl_description.setBorder                         (BORDER_PNL);
        scrlPnl_description.getVerticalScrollBar()      .setUI(new CustomScrollBar());
        scrlPnl_description.getHorizontalScrollBar()    .setUI(new CustomScrollBar());
        
        txt_description.setBackground   (this.getBackground());
        txt_description.setBorder       (PADDING_TXT);
        txt_description.setLineWrap     (true);
        txt_description.setWrapStyleWord(true);
        txt_description.setEditable     (false);
        
        btn_addReview.setBackground         (BG_ADDREVIEW_BTN);
        btn_addReview.setForeground         (FG_DEFAULT);
        btn_addReview.setHorizontalAlignment(JLabel.CENTER);
        btn_addReview.setVerticalAlignment  (JLabel.CENTER);
        btn_addReview.setFont               (new Font(this.getFont().getFontName(), this.getFont().getStyle(), 28));
        btn_addReview.setOpaque             (true);
        
        btn_back.setBackground              (BG_BACK_BTN);
        btn_back.setForeground              (FG_DEFAULT);
        btn_back.setHorizontalAlignment     (JLabel.CENTER);
        btn_back.setVerticalAlignment       (JLabel.CENTER);
        btn_back.setFont                    (new Font(this.getFont().getFontName(), this.getFont().getStyle(), 28));
        btn_back.setOpaque                  (true);
        
        pnl_btn_addReview.add(btn_addReviewRounded, BorderLayout.CENTER);
        pnl_btn_back     .add(btn_backRounded,      BorderLayout.CENTER);
        
        pnl_btns.setPreferredSize(new Dimension(this.getWidth(), PNL_BTNS_HEIGHT));
        pnl_btns.add(pnl_btn_addReview, BorderLayout.CENTER);
        pnl_btns.add(pnl_btn_back,      BorderLayout.EAST);
        
        JLabel[] lbl_guidesLeftSection = new JLabel[GUIDES_LEFT_SECTION.length];
        for (int i = 0; i < lbl_guidesLeftSection.length; i++) 
        {
            lbl_guidesLeftSection[i] = new JLabel           (GUIDES_LEFT_SECTION[i]);
            lbl_guidesLeftSection[i].setBackground          (this.getBackground());
            lbl_guidesLeftSection[i].setHorizontalAlignment (JLabel.CENTER);
            lbl_guidesLeftSection[i].setVerticalAlignment   (JLabel.CENTER);
            lbl_guidesLeftSection[i].setFont                (this.getFont());
            lbl_guidesLeftSection[i].setBorder              (PADDING_LBL);
            lbl_guidesLeftSection[i].setOpaque              (true);
        }
        JLabel[] lbl_guidesSection = new JLabel[GUIDES_SECTION.length];
        for (int i = 0; i < lbl_guidesSection.length; i++) 
        {
            lbl_guidesSection[i] = new JLabel               (GUIDES_SECTION[i]);
            lbl_guidesSection[i].setBackground              (this.getBackground());
            lbl_guidesSection[i].setPreferredSize           (new Dimension(CONTENT_GUIDES_WIDTH, 0));
            lbl_guidesSection[i].setHorizontalAlignment     (JLabel.CENTER);
            lbl_guidesSection[i].setVerticalAlignment       (JLabel.CENTER);
            lbl_guidesSection[i].setFont                    (this.getFont());
            lbl_guidesSection[i].setBorder                  (PADDING_LBL);
            lbl_guidesSection[i].setOpaque                  (true);
        }
        
        GridBagConstraints gbc_leftSection = new GridBagConstraints();
        gbc_leftSection.gridx     = 0;
        gbc_leftSection.gridy     = 0;
        gbc_leftSection.weightx   = 0.3;
        gbc_leftSection.weighty   = 1;
        gbc_leftSection.ipady     = 15;
        gbc_leftSection.gridwidth = 1;
        gbc_leftSection.fill      = GridBagConstraints.BOTH;
        for (JLabel lbl : lbl_guidesLeftSection) 
        {
            pnl_leftSection.add(lbl, gbc_leftSection);
            gbc_leftSection.gridy++;
        }
        gbc_leftSection.gridy = 0;
        gbc_leftSection.gridx++;
        for (JLabel lbl : lbls_leftSection) 
        {
            if (gbc_leftSection.gridy > 2) 
            {
                gbc_leftSection.gridwidth = 2;
                gbc_leftSection.gridx     = 0;
            }
            pnl_leftSection.add(lbl, gbc_leftSection);
            gbc_leftSection.gridy++;
        }
        
        GridBagConstraints gbc_leftContent = new GridBagConstraints();
        gbc_leftContent.gridx   = 0;
        gbc_leftContent.gridy   = 0;
        gbc_leftContent.weightx = 1;
        gbc_leftContent.fill    = GridBagConstraints.BOTH;
        pnl_leftContent.add(pnl_leftSection, gbc_leftContent);
        
        gbc_leftContent.gridy++;
        gbc_leftContent.weighty = 1;
        pnl_leftContent.add(scrlPnl_description, gbc_leftContent);
        
        GridBagConstraints gbc_section = new GridBagConstraints();
        gbc_section.gridx     = 0;
        gbc_section.gridy     = 0;
        gbc_section.weighty   = 1;
        gbc_section.ipady     = 15;
        gbc_section.fill      = GridBagConstraints.BOTH;
        for (JLabel lbl : lbl_guidesSection) {
            pnl_section.add(lbl, gbc_section);
            gbc_section.gridy++;
        }
        gbc_section.gridy = 0;
        gbc_section.weightx = 1;
        gbc_section.gridx++;
        for (JLabel lbl : lbls_section) {
            pnl_section.add(lbl, gbc_section);
            gbc_section.gridy++;
        }
        
        GridBagConstraints gbc_content = new GridBagConstraints();
        gbc_content.gridx   = 0;
        gbc_content.gridy   = 0;
        gbc_content.weightx = 1;
        gbc_content.fill    = GridBagConstraints.BOTH;
        pnl_content.add(pnl_section, gbc_content);
        
        gbc_content.gridy++;
        pnl_content.add(lbl_rating, gbc_content);
        
        gbc_content.weighty = 1;
        gbc_content.gridy++;
        pnl_content.add(scrlPnl_reviews, gbc_content);
        
        this.add(lbl_name,        BorderLayout.NORTH);
        this.add(pnl_content,     BorderLayout.CENTER);
        this.add(pnl_leftContent, BorderLayout.WEST);
        this.add(pnl_btns,        BorderLayout.SOUTH);
    }
    
    /**
     * Sets up event listeners for user interaction.
     */
    private void initEvents() 
    {
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                restaurantGUI_ComponentResized(e);
            }
        });
        
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
        
        btn_addReview.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                btn_addReview_MouseClicked(e);
            }
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn_addReview_MouseEntered(e);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn_addReview_MouseExited(e);
            }
        });
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Event Listeners">
    /**
     * Handles the resize event for the {@code RestaurantGUI} {@link JPanel}.
     * <p>
     * When resized, resizes the padding of the components.
     * </p>
     * 
     * @param e the component event triggered by resizing the GUI application
     */
    private void restaurantGUI_ComponentResized(java.awt.event.ComponentEvent e) 
    {
        final int PADDING_BTN = (int) (this.getWidth() * 0.01);
        pnl_btn_addReview.setBorder(BorderFactory.createEmptyBorder(PADDING_BTN, PADDING_BTN, PADDING_BTN, PADDING_BTN));
        pnl_btn_back     .setBorder(BorderFactory.createEmptyBorder(PADDING_BTN, PADDING_BTN, PADDING_BTN, PADDING_BTN));
    }
    
    /**
     * Handles the click event on the cancel button {@link JLabel}.
     * <p>
     * When the button is clicked, the view switches to the {@code Home} screen canceling the login procedure.
     * </p>
     * 
     * @param e the mouse event triggered by clicking the button 
     */
    private void btn_back_MouseClicked(java.awt.event.MouseEvent e) {
        controller.getPanelMain().showCard(Page.HOME);
        controller.getPanelMain().getPanel().remove(this);
    }
    
    /**
     * Handles the hover event on the cancel button {@link JLabel}.
     * 
     * @param e the mouse event triggered by hovering to the button
     */
    private void btn_back_MouseEntered(java.awt.event.MouseEvent e) {
        btn_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn_back.setBackground(btn_back.getBackground().darker());
    }
    
    /**
     * Handles the exit hover event on the cancel button {@link JLabel}.
     * 
     * @param e the mouse event triggered by leaving the cursor from the button
     */
    private void btn_back_MouseExited(java.awt.event.MouseEvent e) {
        btn_back.setBackground(BG_BACK_BTN);
    }
    
    /**
     * Handles the click event on the apply button {@link JLabel}.
     * <p>
     * When the button is clicked, the filters will be applied and the view switches to the {@code Home} screen.
     * </p>
     * 
     * @param e the mouse event triggered by clicking the button 
     */
    private void btn_addReview_MouseClicked(java.awt.event.MouseEvent e) {
        
    }
    
    /**
     * Handles the hover event on the apply button {@link JLabel}.
     * 
     * @param e the mouse event triggered by hovering to the button
     */
    private void btn_addReview_MouseEntered(java.awt.event.MouseEvent e) {
        btn_addReview.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn_addReview.setBackground(btn_addReview.getBackground().darker());
    }
    
    /**
     * Handles the exit hover event on the apply button {@link JLabel}.
     * 
     * @param e the mouse event triggered by leaving the cursor from the button
     */
    private void btn_addReview_MouseExited(java.awt.event.MouseEvent e) {
        btn_addReview.setBackground(BG_ADDREVIEW_BTN);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * This method handles the width of a text inside a {@link JComponent}.
     * 
     * @param text - the {@code JComponent}'s text
     * @return the {@code JComponent}'s text wrapped in HTML and basic CSS
     */
    private String wrapTextHTML(String text) {
        return "<html>" + text + "</html>";
    }
    //</editor-fold>
    
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(53, 216, 58));
        setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        setPreferredSize(new java.awt.Dimension(554, 300));

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
    private final File               programDataset       = AppPaths.getDataFile("data", "program_dataset.csv");
    private final Color              BG_NAME              = new Color(173, 199, 2);
    private final Color              BG_DEFAULT           = new Color(139, 232, 26);
    private final Color              FG_DEFAULT           = Color.BLACK;
    private final Color              BG_ADDREVIEW_BTN     = new Color(0, 255, 0, 192);
    private final Color              BG_BACK_BTN          = new Color(255, 64, 0, 192);
    private final Border             PADDING_LBL          = BorderFactory.createEmptyBorder(0, 5, 0, 5);
    private final Border             PADDING_TXT          = BorderFactory.createEmptyBorder(3, 3, 3, 3);
    private final Border             BORDER_PNL           = BorderFactory.createLineBorder(Color.BLACK, 3);
    private final String[]           GUIDES_LEFT_SECTION  = CSV.read(programDataset, "GUIDES_LEFT_SECTION").toArray(new String[0]);
    private final String[]           GUIDES_SECTION       = CSV.read(programDataset, "GUIDES_SECTION"     ).toArray(new String[0]);
    private final String             GREENSTAR_TRUE       = "Green";
    private final String             GREENSTAR_FALSE      = "No Green";
    private final String             ADD_REVIEW           = "Add review";
    private final String             BACK                 = "Back";
    private final int                LBL_NAME_HEIGHT      = 80;
    private final int                ARC                  = 50;
    private final int                LEFT_CONTENT_WIDTH   = 300;
    private final int                CONTENT_GUIDES_WIDTH = 100;
    private final int                PNL_BTNS_HEIGHT      = 80;
    private final RoundedComponentUI BTN_LAYERUI          = new RoundedComponentUI(ARC);

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Components">
    private JPanel             pnl_leftSection;
    private JPanel             pnl_leftContent;
    private JPanel             pnl_section;
    private JPanel             pnl_content;
    private JPanel             pnl_reviews;
    private JPanel             pnl_btns;
    private JPanel             pnl_btn_addReview;
    private JPanel             pnl_btn_back;
    private JScrollPane        scrlPnl_description;
    private JScrollPane        scrlPnl_reviews;
    private JTextArea          txt_description;
    private JLabel             lbl_name;
    private JLabel             lbl_price;
    private JLabel             lbl_currency;
    private JLabel             lbl_phoneNo;
    private JLabel             lbl_location;
    private JLabel             lbl_address;
    private JLabel             lbl_latitude;
    private JLabel             lbl_longitude;
    private JLabel             lbl_url;
    private JLabel             lbl_webUrl;
    private JLabel             lbl_award;
    private JLabel             lbl_greenStar;
    private JLabel             lbl_services;
    private JLabel             lbl_rating;
    private JLabel[]           lbls;
    private JLabel[]           lbls_leftSection;
    private JLabel[]           lbls_section;
    private JLabel             btn_addReview;
    private JLabel             btn_back;
    private JLayer<JComponent> btn_addReviewRounded;
    private JLayer<JComponent> btn_backRounded;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Fields">
    private final Controller controller;
    private final Restaurant restaurant;
    //</editor-fold>
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
