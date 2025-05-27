package theknife.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.JTextField;
import theknife.Controller;
import theknife.obj.restaurant.Restaurant;
import theknife.obj.user.User;

/**
 * The Home class represents the home screen of the application.
 * <p>
 * It contains a list of restaurants found by typing on the {@code Upperbar} {@link JTextField}.<br>
 * The components are arranged in a {@link BorderLayout} and properly styled.<br>
 * This class handles events such as button clicks and text input to interact with the rest of the application.
 * </p>
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */
public final class Home extends javax.swing.JPanel 
{

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Creates a new {@code Home} panel and initializes its components.
     * <p>
     * This constructor also sets the main panel to control the screen transitions using {@link PanelMain#showCard}.
     * </p>
     *
     * @param controller the {@link Controller} class that manages the screen layout
     */
    public Home(Controller controller) 
    {
      initComponents();
      this.controller = controller;
      initGUI       ();
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Initialization">
    /**
     * Initializes the graphical user interface (GUI) for the home page.
     */
    private void initGUI() 
    {
      initFields();
      initHome  ();
      initEvents();
    }

    /**
     * Initializes the basic fields of the {@code Home} panel.
     */
    private void initFields() 
    {
      listModel = new DefaultListModel<>();
    
      for(Restaurant restaurant : controller.getRestaurants().getList())
        listModel.addElement(restaurant);

      list_restaurants = new JList<>(listModel);

      list_restaurants.setCellRenderer(new ListCellRenderer<Restaurant>() 
      {
        private final PreviewRestaurant preview = new PreviewRestaurant(controller);

        @Override
        public Component getListCellRendererComponent(JList<? extends Restaurant> list, Restaurant value, int index, boolean isSelected, boolean cellHasFocus) 
        {
          Color bg = (index % 2 == 0) ? BG_RESTAURANT_PNL_EVEN : BG_RESTAURANT_PNL_ODD;
          preview.updateWith(value, bg);
          return preview;
        }
      }); 

      lbl_title             = new JLabel        (TITLE);
      btn_addRestaurant     = new JLabel        (wrapTextHTML(ADD_RESTAURANT));
      btn_favourite         = new JLabel        (FAVOURITE);
      btn_reviews           = new JLabel        (REVIEWS);
      btn_restaurantOwned   = new JLabel        (wrapTextHTML(OWNED_RESTAURANTS));
      upperbar              = new Upperbar      (controller, lbl_title);
      pnl_home              = new CustomJPanel  (new GridBagLayout(), Page.HOME);
      pnl_sideBar           = new JPanel        (new GridLayout(8, 1, 10, 0));
      scrlPnl_restaurants   = new JScrollPane   (list_restaurants, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
      btns                  = new JLabel[] 
      {
          btn_addRestaurant,
          btn_favourite,
          btn_reviews,
          btn_restaurantOwned
      };
    }
    
    /**
     * Initializes the layout and appearance of the home page.
     */
    private void initHome() 
    {
      this.setLayout(new BorderLayout());
      
      pnl_home        .setBackground(this.getBackground());           
      
      pnl_sideBar     .setBackground(new Color(153, 255, 153));
      
      list_restaurants.setBackground(BG_RESTAURANT_PNL_ODD);  
      
      scrlPnl_restaurants.getVerticalScrollBar()      .setUI(new CustomJScrollBar());
      scrlPnl_restaurants.getHorizontalScrollBar()    .setUI(new CustomJScrollBar());
      scrlPnl_restaurants.setBorder                   (PADDING_SCROLLPANE);
       
      lbl_title.setBackground           (BG_TITLE);
      lbl_title.setForeground           (FG_DEFAULT);
      lbl_title.setHorizontalAlignment  (JLabel.CENTER);
      lbl_title.setVerticalAlignment    (JLabel.CENTER);
      lbl_title.setFont                 (new Font(this.getFont().getFontName(), this.getFont().getStyle(), 48));
      lbl_title.setOpaque               (false);
      
      for (int i = 0; i < btns.length; i++) {
        btns[i].setBackground         (i % 2 == 0 ? BG_SIDEBAR_BTN_EVEN : BG_SIDEBAR_BTN_ODD);
        btns[i].setForeground         (FG_DEFAULT);
        btns[i].setHorizontalAlignment(JLabel.CENTER);
        btns[i].setVerticalAlignment  (JLabel.CENTER);
        btns[i].setFont               (new Font(this.getFont().getFontName(), this.getFont().getStyle(), 18));
        btns[i].setOpaque             (true);
      }
      
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.gridx   = 0;
      gbc.gridy   = 0;
      gbc.weightx = 0.25;
      gbc.ipadx   = 30;
      gbc.insets  = INSETS;
      gbc.anchor  = GridBagConstraints.CENTER;
      gbc.fill    = GridBagConstraints.VERTICAL;
      pnl_home.add(lbl_title, gbc);
        
      gbc.gridy++;
      gbc.weighty = 0.75;
      gbc.ipadx   = 0;
      gbc.insets  = INSETS_ZERO;
      gbc.fill    = GridBagConstraints.BOTH;
      pnl_home.add(scrlPnl_restaurants, gbc);
       
      this.add(upperbar,    BorderLayout.NORTH);
      this.add(pnl_home,    BorderLayout.CENTER);
      this.add(pnl_sideBar, BorderLayout.WEST);
    }
    
    /**
     * Sets up event listeners for user interaction.
     */
    private void initEvents() 
    {      
      list_restaurants.addMouseListener(new MouseAdapter() 
      {
        @Override
        public void mouseClicked(MouseEvent e) 
        {
          int index = list_restaurants.locationToIndex(e.getPoint());
          if (index >= 0) 
          {
            Restaurant    selectedRestaurant    = listModel.getElementAt(index);
            RestaurantGUI restaurantGUI         = new RestaurantGUI     (controller, selectedRestaurant);
            controller.getPanelMain().getPanel().add(restaurantGUI, Page.RESTAURANT);
            controller.getPanelMain().showCard(Page.RESTAURANT);
          }
        }
      });

      btn_addRestaurant.addMouseListener(new MouseAdapter() 
      {
        @Override
        public void mouseClicked(MouseEvent e) 
        {
          btn_addRestaurant_MouseClicked(e);
        }
        @Override
        public void mouseEntered(MouseEvent e) 
        {
          btn_addRestaurant_MouseEntered(e);
        }
        @Override
        public void mouseExited(MouseEvent e) 
        {
          btn_addRestaurant_MouseExited(e);
        }
      });
            
      btn_favourite.addMouseListener(new MouseAdapter() 
      {
        @Override
        public void mouseClicked(MouseEvent e) 
        {
          btn_favourite_MouseClicked(e);
        }
        @Override
        public void mouseEntered(MouseEvent e) 
        {
          btn_favourite_MouseEntered(e);
        }
        @Override
        public void mouseExited(MouseEvent e) 
        {
          btn_favourite_MouseExited(e);
        }
      });
            
      btn_reviews.addMouseListener(new MouseAdapter() 
      {
        @Override
        public void mouseClicked(MouseEvent e) 
        {
          btn_reviews_MouseClicked(e);
        }
        @Override
        public void mouseEntered(MouseEvent e) 
        {
          btn_reviews_MouseEntered(e);
        }
        @Override
        public void mouseExited(MouseEvent e) 
        {
          btn_reviews_MouseExited(e);
        }
      });
      
      btn_restaurantOwned.addMouseListener(new MouseAdapter() 
      {
        @Override
        public void mouseClicked(MouseEvent e) 
        {
          btn_restaurantOwned_MouseClicked(e);
        }
        @Override
        public void mouseEntered(MouseEvent e) 
        {
          btn_restaurantOwned_MouseEntered(e);
        }
        @Override
        public void mouseExited(MouseEvent e) 
        {
          btn_restaurantOwned_MouseExited(e);
        }
      });
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Event Listeners">    
    /**
     * Handles the click event for the addRestaurant button.
     * <p>
     * When clicked, it switches to the "Add Restaurant" screen.
     * </p>
     * 
     * @param e the mouse event triggered by clicking the button 
     */
    private void btn_addRestaurant_MouseClicked(MouseEvent e) 
    {
      addRestaurant = new AddRestaurant(controller);
      controller.getPanelMain().getPanel().add(addRestaurant, Page.ADD_RESTAURANT);
      controller.getPanelMain().showCard(Page.ADD_RESTAURANT);
    }
    
    /**
     * Handles the hover event on the addRestaurant button {@link JButton}.
     * 
     * @param e the mouse event triggered by hovering to the button.
     */
    private void btn_addRestaurant_MouseEntered(java.awt.event.MouseEvent e) 
    {
      btn_addRestaurant.setCursor       (new Cursor(Cursor.HAND_CURSOR));
      btn_addRestaurant.setBackground   (btn_addRestaurant.getBackground().darker());
    }
    
    /**
     * Handles the exit hover event on the addRestaurant button {@link JButton}.
     * 
     * @param e the mouse event triggered by leaving the cursor from the button
     */
    private void btn_addRestaurant_MouseExited(java.awt.event.MouseEvent e)
    {
      btn_addRestaurant.setBackground(BG_SIDEBAR_BTN_ODD);
    }
    
    /**
     * Handles the click event for the favourite button.
     * <p>
     * When clicked, it loads the favourite list of logged customer.
     * </p>
     * 
     * @param e the mouse event triggered by clicking the button 
     */
    private void btn_favourite_MouseClicked(MouseEvent e) 
    {
      controller.viewUserList();
    }
    
    /**
     * Handles the hover event on the favourite button {@link JButton}.
     * 
     * @param e the mouse event triggered by hovering to the button.
     */
    private void btn_favourite_MouseEntered(java.awt.event.MouseEvent e) 
    {
      btn_favourite.setCursor       (new Cursor(Cursor.HAND_CURSOR));
      btn_favourite.setBackground   (btn_addRestaurant.getBackground().darker());
    }
    
     /**
     * Handles the exit hover event on the favourite button {@link JButton}.
     * 
     * @param e the mouse event triggered by leaving the cursor from the button
     */
    private void btn_favourite_MouseExited(java.awt.event.MouseEvent e) 
    {
      btn_favourite.setBackground(BG_SIDEBAR_BTN_ODD);
    }
    
    /**
     * Handles the click event for the reviews button.
     * <p>
     * When clicked, it loads the favourite list of logged customer.
     * </p>
     * 
     * @param e the mouse event triggered by clicking the button 
     */
    private void btn_reviews_MouseClicked(MouseEvent e) 
    {
        CustomerReviews customerReviews = new CustomerReviews(controller);
        controller.getPanelMain().getPanel().add(customerReviews, Page.CUSTOMER_REVIEWS);
        controller.getPanelMain().showCard(Page.CUSTOMER_REVIEWS);
    }
    
    /**
     * Handles the hover event on the reviews button {@link JButton}.
     * 
     * @param e the mouse event triggered by hovering to the button.
     */
    private void btn_reviews_MouseEntered(java.awt.event.MouseEvent e) 
    {
      btn_reviews.setCursor       (new Cursor(Cursor.HAND_CURSOR));
      btn_reviews.setBackground   (btn_reviews.getBackground().darker());
    }
    
     /**
     * Handles the exit hover event on the reviews button {@link JButton}.
     * 
     * @param e the mouse event triggered by leaving the cursor from the button
     */
    private void btn_reviews_MouseExited(java.awt.event.MouseEvent e) 
    {
      btn_reviews.setBackground(BG_SIDEBAR_BTN_ODD);
    }
    
     /**
     * Handles the click event for the Owned Restaurant button.
     * <p>
     * When clicked, it loads the Owned list of logged restaurateur.
     * </p>
     * 
     * @param e the mouse event triggered by clicking the button 
     */
    private void btn_restaurantOwned_MouseClicked(MouseEvent e) 
    {
      controller.viewUserList();
    }
    
    /**
     * Handles the hover event on the Owned Restaurant {@link JButton}.
     * 
     * @param e the mouse event triggered by hovering to the button.
     */
    private void btn_restaurantOwned_MouseEntered(java.awt.event.MouseEvent e) 
    {
      btn_restaurantOwned.setCursor       (new Cursor(Cursor.HAND_CURSOR));
      btn_restaurantOwned.setBackground   (btn_addRestaurant.getBackground().darker());
    }
    
    /**
     * Handles the exit hover event on the Owned Restaurant button {@link JButton}.
     * 
     * @param e the mouse event triggered by leaving the cursor from the button
     */
    private void btn_restaurantOwned_MouseExited(java.awt.event.MouseEvent e) 
    {
      btn_restaurantOwned.setBackground(BG_SIDEBAR_BTN_ODD);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * Visualizes the result of the {@link AdvancedSearch}.
     * @param restaurants the list of results filtered
     */
    public void visualizeAdvancedSearchResult(List<Restaurant> restaurants)
    {
      listModel.clear(); 
      if(!restaurants.isEmpty())
      {
        for(Restaurant restaurant : restaurants) 
          listModel.addElement(restaurant);
      
        list_restaurants.setModel(listModel);
      
        lbl_title.setText("Results...");
      }
      else
        lbl_title.setText("No results found!");
    }
    /**
     * Method that search restaurants by name.
     * @param name the name of the {@link Restaurant}
     */
    public void list_restaurants_searchRestaurants(String name)
    {      
      listModel.clear();
      name = name.toLowerCase();
      
      for(Restaurant restaurant : controller.getRestaurants().getList()) 
        if(restaurant.getNormalizedName().startsWith(name))
          listModel.addElement(restaurant);
      
      list_restaurants.setModel(listModel);
    }
    
    /**
     * Let the user see his list(Favorite or Owned Restaurants).
     * @param restaurants gets user list
     * @param role gets user role
     */
    public void list_restaurants_viewUserList(List<Integer> restaurants, String role)
    {      
      listModel.clear();
      
      for(Integer index: restaurants)
        listModel.addElement(controller.getRestaurants().getList().get(index-1));
  
      lbl_title.setText(role.equals("customer") ? "Favorite Restaurant":"Owned Restaurant");
      lbl_title.repaint();
      list_restaurants.setModel(listModel);
    }
    
    /**
     * Changes the {@code Home} UI related to the {@link User} role
     * @param role - user role
     */
    public void UILoggedUser(String role)
    {
      upperbar.changeUI();
      pnl_sideBar.setPreferredSize(new Dimension(SIDEBAR_WIDTH, 0));
      pnl_sideBar.removeAll();
      switch (role) 
      {
        case "customer":
          pnl_sideBar.add(btn_favourite);
          pnl_sideBar.add(btn_reviews);
          break;
        case "restaurateur":
          pnl_sideBar.add(btn_restaurantOwned);
          pnl_sideBar.add(btn_addRestaurant);
          break;
      }
    }
    
    /**
     * Removes the sidebar from the {@link Home} page.
     */
    public void removeSideBar() 
    {
      pnl_sideBar.removeAll();
      pnl_sideBar.setPreferredSize(null);
    }
    
    /**
     * This method handles the width of a text inside a {@link JComponent}.
     * 
     * @param text - the {@code JComponent}'s text
     * @return the {@code JComponent}'s text wrapped in HTML and basic CSS
     */
    private String wrapTextHTML(String text) 
    {
      return "<html>" + text + "</html>";
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

        setBackground(new java.awt.Color(50, 255, 47));
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
    private final Color  FG_DEFAULT             = Color.BLACK;
    private final Color  BG_TITLE               = new Color(101, 179, 0);
    private final Color  BG_RESTAURANT_PNL_EVEN = new Color(88, 138, 12);
    private final Color  BG_RESTAURANT_PNL_ODD  = new Color(98, 150, 18);
    private final Color  BG_SIDEBAR_BTN_EVEN    = new Color(150, 232, 79);
    private final Color  BG_SIDEBAR_BTN_ODD     = new Color(135, 209, 71);
    private final Border PADDING_SCROLLPANE     = BorderFactory.createEmptyBorder(30, 60, 30, 60);
    private final Insets INSETS                 = new Insets(20, 0, 0, 0);
    private final Insets INSETS_ZERO            = new Insets(0, 0, 0, 0);  
    private final String TITLE                  = "All restaurants";  
    private final String ADD_RESTAURANT         = "Add restaurant";  
    private final String FAVOURITE              = "Favourites";  
    private final String REVIEWS                = "Reviews";  
    private final String OWNED_RESTAURANTS      = "Owned restaurants";  
    private final int    SIDEBAR_WIDTH          = 100;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Components">
    private Upperbar                        upperbar;
    private CustomJPanel                    pnl_home;
    private JPanel                          pnl_sideBar;
    private DefaultListModel<Restaurant>    listModel;
    private JList<Restaurant>               list_restaurants; 
    private JScrollPane                     scrlPnl_restaurants;
    private JLabel                          lbl_title;
    private JLabel                          btn_favourite;
    private JLabel                          btn_reviews;
    private JLabel                          btn_restaurantOwned;
    private JLabel                          btn_addRestaurant;
    private JLabel[]                        btns;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Fields">
    private final Controller    controller;
    private       AddRestaurant addRestaurant;
    //</editor-fold>
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
