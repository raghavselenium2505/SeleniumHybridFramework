package com.gps.development;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.prefs.Preferences;

public class InterviewPracticeApp {

    /* ================= DATA MODEL ================= */
    static class User {
        String name, phone, email, password, imagePath;
        User(String n,String p,String e,String pw,String img){
            name=n; phone=p; email=e; password=pw; imagePath=img;
        }
    }

    static HashMap<String, User> users = new HashMap<>();
    static User currentUser = null;

    /* ================= PERSISTENCE ================= */
    static Preferences prefs = Preferences.userRoot().node("InterviewPracticeApp");
    static final String LAST_USER = "lastUser";

    /* ================= UI CORE ================= */
    static JFrame frame;
    static CardLayout cardLayout;
    static JPanel mainPanel;

    /* ================= SIGN IN ================= */
    static JTextField loginPhoneField;
    static JPasswordField loginPasswordField;

    /* ================= SIGN UP ================= */
    static JTextField nameField, phoneField, emailField, imagePathField;
    static JPasswordField passwordField;
    static JLabel imagePreviewLabel;
    static JLabel removeImageLink, resetImageLink;
    static String tempImagePath = null;

    /* ================= PROFILE ================= */
    static JButton profileBtn;
    static JLabel profileImageLabel, profileNameLabel, profilePhoneLabel, profileEmailLabel;

    public static void main(String[] args) {

        frame = new JFrame("Interview Questions Practice");
        frame.setSize(900,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(authPanel(),"AUTH");
        mainPanel.add(homePanel(),"HOME");
        mainPanel.add(profilePanel(),"PROFILE");
        mainPanel.add(resetPasswordPanel(),"RESET");

        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /* ================= AUTH ================= */
    private static JPanel authPanel(){
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Sign In", signInPanel());
        tabs.addTab("Sign Up", signUpPanel());

        JPanel p=new JPanel(new BorderLayout());
        p.add(tabs);
        return p;
    }

    /* ================= SIGN IN ================= */
    private static JPanel signInPanel(){
        JPanel p=new JPanel(null);

        JLabel title=new JLabel("Sign In",SwingConstants.CENTER);
        title.setBounds(200,60,500,40);
        title.setFont(new Font("Segoe UI",Font.BOLD,26));
        p.add(title);

        p.add(label("Mobile Number",250,160));
        loginPhoneField=field(400,160); restrictPhone(loginPhoneField); p.add(loginPhoneField);

        p.add(label("Password",250,210));
        loginPasswordField=new JPasswordField();
        loginPasswordField.setBounds(400,210,250,30);
        p.add(loginPasswordField);

        JButton loginBtn=new JButton("Login");
        loginBtn.setBounds(400,260,120,35);
        loginBtn.addActionListener(e->login());
        p.add(loginBtn);

        JLabel forgot=new JLabel("<HTML><U>Forgot Password?</U></HTML>");
        forgot.setBounds(400,310,200,25);
        forgot.setForeground(Color.BLUE);
        forgot.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        forgot.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                handleForgotPassword();
            }
        });
        p.add(forgot);

        return p;
    }

    /* ================= SIGN UP ================= */
    private static JPanel signUpPanel(){
        JPanel p=new JPanel(null);

        JLabel title=new JLabel("Sign Up",SwingConstants.CENTER);
        title.setBounds(200,30,500,40);
        title.setFont(new Font("Segoe UI",Font.BOLD,28));
        p.add(title);

        p.add(label("Name *",200,120));
        nameField=field(350,120); p.add(nameField);

        p.add(label("Phone *",200,170));
        phoneField=field(350,170); restrictPhone(phoneField); p.add(phoneField);

        p.add(label("Password *",200,220));
        passwordField=new JPasswordField();
        passwordField.setBounds(350,220,250,30);
        p.add(passwordField);

        p.add(label("Email",200,270));
        emailField=field(350,270); p.add(emailField);

        p.add(label("Profile Image",200,320));
        imagePathField=field(350,320);
        imagePathField.setEditable(true);
        p.add(imagePathField);

        JButton upload=new JButton("Upload");
        upload.setBounds(610,320,90,30);
        upload.addActionListener(e->chooseImage());
        p.add(upload);

        JLabel hint = new JLabel("* Only .png and .jpg files are allowed");
        hint.setForeground(Color.RED);
        hint.setBounds(350,350,300,20);
        p.add(hint);

        imagePreviewLabel=new JLabel();
        imagePreviewLabel.setBounds(720,300,80,80);
        imagePreviewLabel.setBorder(new LineBorder(Color.GRAY));
        p.add(imagePreviewLabel);

        removeImageLink=createLink("Remove Image",350,380);
        resetImageLink=createLink("Reset Image",450,380);
        p.add(removeImageLink);
        p.add(resetImageLink);

        JButton register=new JButton("Register");
        register.setBounds(350,420,120,35);
        register.addActionListener(e->register());
        p.add(register);

        JButton reset=new JButton("Reset");
        reset.setBounds(490,420,120,35);
        reset.addActionListener(e->resetRegistration());
        p.add(reset);

        hideImageLinks();
        return p;
    }

    /* ================= HOME ================= */
    private static JPanel homePanel(){
        JPanel root=new JPanel(new BorderLayout());

        JPanel top=new JPanel(new BorderLayout());
        JButton back=new JButton("Back");
        back.addActionListener(e->cardLayout.show(mainPanel,"AUTH"));
        top.add(back,BorderLayout.WEST);

        profileBtn=new JButton();
        profileBtn.setPreferredSize(new Dimension(48,48));
        profileBtn.setBorderPainted(false);
        profileBtn.setContentAreaFilled(false);

        JPopupMenu menu=new JPopupMenu();
        JMenuItem profile=new JMenuItem("My Profile");
        JMenuItem logout=new JMenuItem("Logout");
        menu.add(profile); menu.add(logout);

        profileBtn.addActionListener(e->menu.show(profileBtn,0,profileBtn.getHeight()));
        profile.addActionListener(e->cardLayout.show(mainPanel,"PROFILE"));
        logout.addActionListener(e->logout());

        top.add(profileBtn,BorderLayout.EAST);
        root.add(top,BorderLayout.NORTH);

        JLabel center=new JLabel("Choose Mode of Action",SwingConstants.CENTER);
        center.setFont(new Font("Segoe UI",Font.BOLD,30));
        root.add(center,BorderLayout.CENTER);

        return root;
    }

    /* ================= PROFILE ================= */
    private static JPanel profilePanel(){
        JPanel p=new JPanel(null);

        JLabel title=new JLabel("My Profile",SwingConstants.CENTER);
        title.setBounds(250,40,400,40);
        title.setFont(new Font("Segoe UI",Font.BOLD,28));
        p.add(title);

        profileImageLabel=new JLabel();
        profileImageLabel.setBounds(390,110,120,120);
        p.add(profileImageLabel);

        profileNameLabel=new JLabel();
        profileNameLabel.setBounds(350,290,400,30);
        p.add(profileNameLabel);

        profilePhoneLabel=new JLabel();
        profilePhoneLabel.setBounds(350,320,400,30);
        p.add(profilePhoneLabel);

        profileEmailLabel=new JLabel();
        profileEmailLabel.setBounds(350,350,400,30);
        p.add(profileEmailLabel);

        JButton back=new JButton("Back");
        back.setBounds(410,420,100,35);
        back.addActionListener(e->cardLayout.show(mainPanel,"HOME"));
        p.add(back);

        return p;
    }

    /* ================= RESET PASSWORD ================= */
    private static JPanel resetPasswordPanel(){
        JPanel p=new JPanel(null);

        JLabel title=new JLabel("Reset Password",SwingConstants.CENTER);
        title.setBounds(200,100,500,40);
        title.setFont(new Font("Segoe UI",Font.BOLD,26));
        p.add(title);

        JPasswordField np=new JPasswordField();
        JPasswordField cp=new JPasswordField();
        np.setBounds(400,220,250,30);
        cp.setBounds(400,270,250,30);

        p.add(label("New Password",250,220));
        p.add(np);
        p.add(label("Confirm Password",250,270));
        p.add(cp);

        JButton ok=new JButton("OK");
        ok.setBounds(460,330,100,35);
        ok.addActionListener(e->{
            if(np.getPassword().length==0 || cp.getPassword().length==0){
                JOptionPane.showMessageDialog(frame,"Password fields cannot be empty");
                return;
            }
            if(!new String(np.getPassword()).equals(new String(cp.getPassword()))){
                JOptionPane.showMessageDialog(frame,"Passwords do not match");
                return;
            }
            currentUser.password=new String(np.getPassword());
            JOptionPane.showMessageDialog(frame,"Password reset successful");
            cardLayout.show(mainPanel,"AUTH");
        });
        p.add(ok);

        return p;
    }

    /* ================= LOGIC ================= */
    private static void register(){
        String phone=phoneField.getText().trim();
        if(phone.isEmpty()){
            JOptionPane.showMessageDialog(frame,"Please enter mobile number");
            return;
        }
        if(users.containsKey(phone)){
            JOptionPane.showMessageDialog(frame,"User already exists");
            return;
        }
        currentUser=new User(
                nameField.getText(),
                phone,
                emailField.getText(),
                new String(passwordField.getPassword()),
                tempImagePath
        );
        users.put(phone,currentUser);
        updateProfileUI();
        cardLayout.show(mainPanel,"HOME");
    }

    private static void login(){
        if(loginPhoneField.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(frame,"Please enter mobile number");
            return;
        }
        String phone=loginPhoneField.getText().trim();
        if(!users.containsKey(phone)){
            JOptionPane.showMessageDialog(frame,"Please register before signing in");
            return;
        }
        User u=users.get(phone);
        if(!u.password.equals(new String(loginPasswordField.getPassword()))){
            JOptionPane.showMessageDialog(frame,"Invalid password");
            return;
        }
        currentUser=u;
        updateProfileUI();
        cardLayout.show(mainPanel,"HOME");
    }

    private static void handleForgotPassword(){
        if(loginPhoneField.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(frame,"Please enter mobile number");
            return;
        }
        String phone=loginPhoneField.getText().trim();
        if(!users.containsKey(phone)){
            JOptionPane.showMessageDialog(frame,"Mobile number not registered");
            return;
        }
        currentUser=users.get(phone);
        cardLayout.show(mainPanel,"RESET");
    }

    private static void logout(){
        currentUser=null;
        cardLayout.show(mainPanel,"AUTH");
    }

    /* ================= IMAGE HELPERS ================= */
    private static void chooseImage(){
        JFileChooser ch=new JFileChooser();
        ch.setFileFilter(new FileNameExtensionFilter("PNG/JPG","png","jpg","jpeg"));
        if(ch.showOpenDialog(frame)==JFileChooser.APPROVE_OPTION){
            tempImagePath=ch.getSelectedFile().getAbsolutePath();
            imagePathField.setText(tempImagePath);
            imagePreviewLabel.setIcon(scale(tempImagePath,80));
            showImageLinks();
        }
    }

    private static void resetRegistration(){
        nameField.setText("");
        phoneField.setText("");
        passwordField.setText("");
        emailField.setText("");
        imagePathField.setText("");
        imagePreviewLabel.setIcon(null);
        tempImagePath=null;
        hideImageLinks();
    }

    private static void showImageLinks(){
        removeImageLink.setVisible(true);
        resetImageLink.setVisible(true);
    }

    private static void hideImageLinks(){
        removeImageLink.setVisible(false);
        resetImageLink.setVisible(false);
    }

    /* ================= UI HELPERS ================= */
    private static JLabel createLink(String text,int x,int y){
        JLabel l=new JLabel("<HTML><U>"+text+"</U></HTML>");
        l.setBounds(x,y,100,20);
        l.setForeground(Color.BLUE);
        l.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        l.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                tempImagePath=null;
                imagePathField.setText("");
                imagePreviewLabel.setIcon(null);
                hideImageLinks();
            }
        });
        return l;
    }

    private static void updateProfileUI(){
        profileNameLabel.setText("Name : "+currentUser.name);
        profilePhoneLabel.setText("Phone : "+currentUser.phone);
        profileEmailLabel.setText("Email : "+(currentUser.email.isEmpty()?"N/A":currentUser.email));

        ImageIcon icon=(currentUser.imagePath!=null)?
                scale(currentUser.imagePath,120):
                new ImageIcon(createInitialAvatar(currentUser.name.substring(0,1)));

        profileImageLabel.setIcon(icon);
        profileBtn.setIcon(scale(icon,48));
    }

    private static ImageIcon scale(String path,int size){
        return new ImageIcon(new ImageIcon(path).getImage()
                .getScaledInstance(size,size,Image.SCALE_SMOOTH));
    }

    private static ImageIcon scale(ImageIcon icon,int size){
        return new ImageIcon(icon.getImage()
                .getScaledInstance(size,size,Image.SCALE_SMOOTH));
    }

    private static void restrictPhone(JTextField f){
        f.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                if(!Character.isDigit(e.getKeyChar())||f.getText().length()>=10)
                    e.consume();
            }
        });
    }

    private static JLabel label(String t,int x,int y){
        JLabel l=new JLabel(t);
        l.setBounds(x,y,150,25);
        return l;
    }

    private static JTextField field(int x,int y){
        JTextField f=new JTextField();
        f.setBounds(x,y,250,30);
        return f;
    }

    private static BufferedImage createInitialAvatar(String ch){
        BufferedImage img=new BufferedImage(120,120,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g=img.createGraphics();
        g.setColor(new Color(63,81,181));
        g.fillOval(0,0,120,120);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Segoe UI",Font.BOLD,48));
        g.drawString(ch.toUpperCase(),45,75);
        g.dispose();
        return img;
    }
}
