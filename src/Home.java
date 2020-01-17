
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JLabel; 
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.*;
import javax.swing.JTextField;  
import static com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolver.length;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileFilter;
import net.proteanit.sql.DbUtils;

public class Home extends javax.swing.JFrame {
      
    
    Connection conn;
    ResultSet rs;
    PreparedStatement pst;
    public File file;
    public String imagelink;
    Thread t1 = new Thread(new MarqueeTest());
     Timer t=new Timer(1000,new Listener());
     
     
    
        
    public class filetypefilter extends FileFilter{
        private  String extension;
        private String description;
        
        public filetypefilter(String extension,String description){
            this.extension=extension;
            this.description=description;
        
    }
        
        @Override
        public boolean accept(File file){
            if(file.isDirectory()){
                return true;
            }return file.getName().endsWith(extension);
        }
        
        @Override
        public String getDescription(){
            return description+String.format("(*%s)",extension);
        }
    
                }
    
  
    private static Home home=new Home();
    
    private Home() {
        initComponents();
        conn=DBconnect.connectDb();
        
         t.start();  
         t1.start();//start displaying the marquee
          }
    
    public static Home getInstance(){//since the constructor  has a private access this method has to be used
        //so that we get a single instance of the Home class, this prevents overlapping of the text
        //displayed in form of a marquee.
        return home;
        
    }
    public void clearFields(){
         jTextField3.setText("");
            jTextField5.setText("");
            jTextField7.setText("");
            jTextField16.setText("");
            jTextField9.setText("");
            jTextField17.setText("");
            jTextField11.setText("");
            jTextField12.setText("");
            jTextField13.setText("");
            jTextField15.setText("");
            jTextField14.setText("");
            jTextField10.setText("");
            jTextField18.setText("");
    }
   
        
    
    public String setDate(){
        Calendar now=Calendar.getInstance(); 
              int hour24=now.get(Calendar.HOUR_OF_DAY);
              int min=now.get(Calendar.MINUTE);
              int sec=now.get(Calendar.SECOND);
               String amorpm;
               int hour12=0;
               if(hour24<12){hour12=hour24; amorpm="am";}
               else if(hour24==12){amorpm="pm";hour12=hour24;}
               else {hour12=hour24-12;amorpm="pm";}
              String months[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", 
              "Oct", "Nov", "Dec"};
              GregorianCalendar cal=new GregorianCalendar();
              String month=months[cal.get(Calendar.MONTH)];//this method returns an integer
              String date= Integer.toString(cal.get(Calendar.DATE));
              String year= Integer.toString(cal.get(Calendar.YEAR));
              String fulldate=month+" "+date+" "+year+"  "+"   "+hour12+":"+min+":"+sec+" "+amorpm;  
              return fulldate;
    }
    
   

          class Listener implements ActionListener{
             public void actionPerformed(ActionEvent e){
              Calendar now=Calendar.getInstance(); 
              int hour24=now.get(Calendar.HOUR_OF_DAY);//there is a difference between get our of day and get hour get hour of day will give the hour in 24 hour clock system
               String amorpm;
               int hour12;
               
               if(hour24<12){hour12=hour24; amorpm="am";}
               else if(hour24==12){hour12=hour24; amorpm="pm";}
               else{hour12=hour24-12;amorpm="pm";}
              int min=now.get(Calendar.MINUTE);
              int sec=now.get(Calendar.SECOND);
             
              String months[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", 
              "Oct", "Nov", "Dec"};
              GregorianCalendar cal=new GregorianCalendar();
              String month=months[cal.get(Calendar.MONTH)];//this method returns an integer
              String date= Integer.toString(cal.get(Calendar.DATE));
              String year= Integer.toString(cal.get(Calendar.YEAR));
              String fulldate=month+" "+date+" "+year+"  "+"Time:";  
              
              
             jTextField8.setText(fulldate+hour12+":"+min+":"+sec+" "+amorpm);
              
             
             
             }
         
         
         
         
         
         
         
         
     }
          public void setNews(String message){
          String news=message;
          //jTextField32.setText(news);
          }
    public  class MarqueeTest implements Runnable{
  
    public void display() {
        Calendar now=Calendar.getInstance(); 
              int hour24=now.get(Calendar.HOUR_OF_DAY);
              int min=now.get(Calendar.MINUTE);
              int sec=now.get(Calendar.SECOND);
               String amorpm;
               int hour12=0;
               if(hour24<12){hour12=hour24; amorpm="am";}
               else if(hour24==12){amorpm="pm";hour12=hour24;}
               else {hour12=hour24-12;amorpm="pm";}
              String months[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", 
              "Oct", "Nov", "Dec"};
              GregorianCalendar cal=new GregorianCalendar();
              String month=months[cal.get(Calendar.MONTH)];//this method returns an integer
              String date= Integer.toString(cal.get(Calendar.DATE));
              String year= Integer.toString(cal.get(Calendar.YEAR));
              String fulldate=month+" "+date+" "+year; 
             
        
       
              
              
               String message;
          
         String sql1="SELECT message FROM announceMents WHERE code=?";
        try{
        pst=conn.prepareStatement(sql1);
            pst.setString(1,"tdy");
             rs=pst.executeQuery();
             rs=pst.executeQuery();
            if(rs.next()){
                message=rs.getString("Message");
                //jTextField32.setText(message);
            MarqueePanel mp = new MarqueePanel(message, 60);
     
             mp.start();}
            
    
            
            }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
        
    }
                //The below sample string was used to test the marquee output.
              String s  ="There will be home visits for all loan clients in Taveta Branch..."
                + "All the concerned staff are advised to attend to the same"
                + "...All cashiers are going to have their annual meeting in"
                + " Nairobi next month on 15th...There will be a team from head "
                + "office that will be vising Tommorow...You are reminded of our"
                + " organisations key values...1.Honesty...2.Accountability...3."
                + "Professionalism...4.Team work. Remember that \'The customer is always the king\'" ;

    

    
    class MarqueePanel implements ActionListener {

    private static final int RATE = 12;
    private final Timer timer = new Timer(1000 / RATE, this);
    
    private final String s;
    private final int n;
    private int index=0;

    public MarqueePanel(String s, int n) {
        if (s == null || n < 1) {
            //throw new IllegalArgumentException("Null string or n < 1");
        }
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            sb.append(' ');//This appends an empty string sequence in the string before the String characters are displayed
        }
        this.s = sb + s + sb;
        this.n = n;
        jTextField2.setFont(new Font("Serif", Font.PLAIN, 36));
         jTextField2.setText(sb.toString());
         jTextField2.setBackground(Color.CYAN);
     
    }

    public void start() {
        timer.start();
    }

   public void stop() {
      timer.stop();
    }

    
    public void actionPerformed(ActionEvent e) {
        index++;
        if (index > s.length() - n) {
            index = 0;
        }
        try{

         
        }catch(Exception p){}
        
         jTextField2.setText(s.substring(index, index + n));
        
         
    }}
    public void run(){
        display();
    }
}

    
    
    
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField4 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jTextField24 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jTextField23 = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jTextField31 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jButton17 = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jTextField29 = new javax.swing.JTextField();
        jTextField28 = new javax.swing.JTextField();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        jButton18 = new javax.swing.JButton();
        jTextField30 = new javax.swing.JTextField();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jTextField27 = new javax.swing.JTextField();
        jButton16 = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jTextField32 = new javax.swing.JTextField();
        jTextField33 = new javax.swing.JTextField();
        jTextField34 = new javax.swing.JTextField();
        jTextField35 = new javax.swing.JTextField();
        jTextField36 = new javax.swing.JTextField();
        jTextField37 = new javax.swing.JTextField();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jButton14 = new javax.swing.JButton();
        jTextField25 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jTextField26 = new javax.swing.JTextField();
        jButton21 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jTextField38 = new javax.swing.JTextField();
        jTextField39 = new javax.swing.JTextField();
        jTextField40 = new javax.swing.JTextField();
        jTextField41 = new javax.swing.JTextField();
        jTextField42 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField3 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        jTextField18 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 51, 153));
        setForeground(new java.awt.Color(255, 51, 102));

        jTextField4.setEditable(false);
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jTextField6.setEditable(false);

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(0, 153, 153));
        jButton7.setText("GO");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 0));
        jLabel11.setText("Date");

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextField1.setBorder(null);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField8.setEditable(false);
        jTextField8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField8.setForeground(new java.awt.Color(0, 102, 51));

        jPanel5.setForeground(new java.awt.Color(0, 153, 102));
        jPanel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jTabbedPane1.setForeground(new java.awt.Color(0, 153, 102));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 102), 2), "Deposit", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(255, 102, 102))); // NOI18N

        jTextField19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField19ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 51, 51));
        jButton8.setText("cancel");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 153, 51));
        jLabel19.setText("Customer Account No");

        jTextField20.setEditable(false);

        jTextField21.setEditable(false);
        jTextField21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField21ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(255, 51, 51));
        jButton9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 51, 51));
        jButton9.setText("GO");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 153, 51));
        jLabel21.setText("Image");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 153, 51));
        jLabel23.setText("Deposit Amount");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 153, 51));
        jLabel20.setText("Full Name");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 153, 51));
        jLabel22.setText("Search Customer by ID/PassportNo");

        jButton10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 51, 51));
        jButton10.setText("GO");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 153, 51));
        jLabel24.setText("Account Balance");

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(0, 153, 51));
        jButton5.setText("Deposit");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 102, 102));
        jButton11.setText("Reset");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 153, 0));
        jLabel30.setText("Deposit Type");

        jComboBox4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox4.setForeground(new java.awt.Color(51, 153, 0));
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Normal_Deposit", "Mpesa_Mpesa_Deposit" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        jLabel31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 51)));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(jLabel24)
                    .addComponent(jLabel21))
                .addGap(7, 7, 7)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)))
                .addGap(8, 8, 8)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(0, 46, Short.MAX_VALUE)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(jButton11)
                                        .addGap(28, 28, 28)
                                        .addComponent(jButton8)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton5))
                                    .addComponent(jButton9)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(jLabel22)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField24)
                                    .addComponent(jComboBox4, 0, 155, Short.MAX_VALUE))))
                        .addContainerGap())
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(jLabel30)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel22)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(jLabel20)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel21)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5)
                            .addComponent(jButton8)
                            .addComponent(jButton11))
                        .addGap(45, 45, 45))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Deposits", jPanel2);

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 102), 2), "Payments", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(255, 102, 102))); // NOI18N

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 153, 51));
        jLabel34.setText("Full Name");

        jTextField31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField31ActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 153, 51));
        jLabel40.setText("Payment Amount");

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 153, 51));
        jLabel36.setText("Image");

        jButton17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton17.setForeground(new java.awt.Color(255, 102, 51));
        jButton17.setText("Cancel");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 153, 51));
        jLabel38.setText("Payment Type");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 153, 51));
        jLabel33.setText("Account Number");

        jTextField29.setEditable(false);

        jTextField28.setEditable(false);

        jComboBox5.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Normal_Payment", "Mpesa_Payment" }));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 153, 51));
        jLabel37.setText("Search customer by ID/Passport");

        jButton18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton18.setForeground(new java.awt.Color(51, 204, 255));
        jButton18.setText("Reset");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton19.setForeground(new java.awt.Color(255, 51, 51));
        jButton19.setText("GO");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton20.setForeground(new java.awt.Color(255, 51, 51));
        jButton20.setText("GO");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jTextField27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField27ActionPerformed(evt);
            }
        });

        jButton16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton16.setForeground(new java.awt.Color(102, 102, 255));
        jButton16.setText("Pay");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 153, 51));
        jLabel35.setText("Account Balance");

        jLabel39.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 102)));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel38)
                                .addGap(142, 142, 142))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel34)
                                    .addComponent(jLabel35)
                                    .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextField28, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField29, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton20)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                                .addComponent(jLabel37)
                                .addGap(58, 58, 58)))
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton19, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel36)
                        .addGap(110, 110, 110)
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(109, 109, 109)
                                .addComponent(jLabel40)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton17)
                                .addGap(18, 18, 18)
                                .addComponent(jButton16)))))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37)
                    .addComponent(jButton20))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton19))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel40)
                                    .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton18)
                                    .addComponent(jButton17)
                                    .addComponent(jButton16))))))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Payments", jPanel3);

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255), 2), "Transfer", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(102, 204, 255))); // NOI18N

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(153, 153, 255));
        jLabel41.setText("Sender details");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(153, 153, 255));
        jLabel42.setText("Recepient Details");

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel43.setText("Account No");

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel44.setText("Name");

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel45.setText("Account Balance");

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel46.setText("Transfer Amount");

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel47.setText("Account No");

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel48.setText("Name");

        jTextField33.setEditable(false);

        jTextField34.setEditable(false);

        jTextField35.setEditable(false);

        jTextField37.setEditable(false);

        jButton22.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton22.setText("ok");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton23.setText("Cancel");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton23)
                        .addGap(34, 34, 34)
                        .addComponent(jButton22))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel43)
                                    .addComponent(jLabel44)
                                    .addComponent(jLabel45)
                                    .addComponent(jLabel46))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField35, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                    .addComponent(jTextField32)
                                    .addComponent(jTextField33)
                                    .addComponent(jTextField34))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel47)
                                    .addComponent(jLabel48))
                                .addGap(18, 18, 18))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addComponent(jLabel41)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel42)
                            .addComponent(jTextField36, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                            .addComponent(jTextField37))))
                .addGap(76, 76, 76))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jLabel42))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(jLabel47)
                    .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(jLabel48)
                    .addComponent(jTextField33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(jTextField35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton22)
                    .addComponent(jButton23))
                .addGap(50, 50, 50))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Transfer", jPanel8);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 102, 102));
        jLabel25.setText("Total Operating cash");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 102, 102));
        jLabel27.setText("Cash Limit Status");

        jButton12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 102, 102));
        jButton12.setText("Total Deposits Details");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton13.setForeground(new java.awt.Color(255, 102, 102));
        jButton13.setText("Total Payments Details");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 51, 51));
        jLabel28.setText("Cash Operations");

        jComboBox3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBox3.setForeground(new java.awt.Color(255, 102, 102));
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Request", "Repatriate" }));

        jButton14.setText("Go");

        jTextField25.setEditable(false);

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setText("Transactions details");

        jTextField26.setEditable(false);

        jButton21.setText("Refresh");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addGap(62, 62, 62)
                                .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(123, 123, 123)
                                .addComponent(jLabel27)
                                .addGap(57, 57, 57)
                                .addComponent(jTextField26, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton14))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jButton21)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28)
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(130, 130, 130))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel27)
                    .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                        .addComponent(jLabel28)
                        .addGap(42, 42, 42)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton14))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton21)
                        .addGap(117, 117, 117)
                        .addComponent(jLabel29)
                        .addGap(18, 18, 18)
                        .addComponent(jButton12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton13)))
                .addGap(35, 35, 35))
        );

        jTabbedPane1.addTab("Cash Management", jPanel4);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 922, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 353, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("ERP login", jPanel6);

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 204, 255), 2), "Customer Search", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(0, 204, 153))); // NOI18N

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel53.setText("Available Balance");

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel49.setText("Customer ID");

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel52.setText("Account Type");

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel50.setText("Full Name");

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel51.setText("Account Balance");

        jTextField39.setEditable(false);
        jTextField39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField39ActionPerformed(evt);
            }
        });

        jTextField40.setEditable(false);

        jTextField41.setEditable(false);

        jTextField42.setEditable(false);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel49)
                    .addComponent(jLabel50)
                    .addComponent(jLabel52)
                    .addComponent(jLabel51)
                    .addComponent(jLabel53))
                .addGap(38, 38, 38)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField38, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField39, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                    .addComponent(jTextField40)
                    .addComponent(jTextField41)
                    .addComponent(jTextField42))
                .addContainerGap(112, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(jTextField38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(jTextField39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel52)
                    .addComponent(jTextField41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(jTextField42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(258, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Search Customer", jPanel1);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 51), 2), "Customer Account Creation", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24), new java.awt.Color(255, 102, 102))); // NOI18N
        jPanel9.setPreferredSize(new java.awt.Dimension(846, 400));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 153, 0));
        jLabel2.setText("First Name");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 153, 0));
        jLabel3.setText("Middle Name");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 153, 0));
        jLabel4.setText("Last Name");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 153, 0));
        jLabel5.setText("ID/Passport No");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 153, 0));
        jLabel6.setText("DOB");

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(51, 153, 0));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female", " " }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 153, 0));
        jLabel7.setText("Gender");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 153, 0));
        jLabel8.setText("Pysical Address");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 153, 0));
        jLabel9.setText("Email address");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 153, 0));
        jLabel10.setText("Phone number");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 153, 0));
        jLabel12.setText("Date Created");

        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(51, 153, 0));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Savings Account", "Fixed Deposit Account", "current Account" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });

        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });

        jTextField12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField12ActionPerformed(evt);
            }
        });

        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 204, 0));
        jLabel13.setText("Account No");

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(51, 204, 0));
        jButton2.setText("Generate");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 204, 0));
        jLabel14.setText("Balance");

        jTextField14.setEditable(false);
        jTextField14.setForeground(new java.awt.Color(255, 102, 102));
        jTextField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
            }
        });

        jTextField15.setEditable(false);
        jTextField15.setForeground(new java.awt.Color(255, 0, 0));
        jTextField15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField15ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 153, 0));
        jButton3.setText("Create");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 204, 0));
        jLabel16.setText("Account Type");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 102, 102));
        jLabel17.setText("Staff in charge");

        jTextField10.setEditable(false);
        jTextField10.setForeground(new java.awt.Color(255, 51, 51));
        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 204, 0));
        jLabel15.setText("Next 0f kin");

        jTextField16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField16ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 204, 0));
        jLabel18.setText("Next of kin phone no");

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 204, 0));
        jButton4.setText("Reset");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 153, 0));
        jLabel32.setText("Passport Size photo");

        jButton15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton15.setForeground(new java.awt.Color(51, 153, 0));
        jButton15.setText("Browse and upload");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jTextField18.setEditable(false);

        jTextField22.setEditable(false);
        jTextField22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField22.setForeground(new java.awt.Color(255, 51, 51));
        jTextField22.setText("not set");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel7)
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField3)
                    .addComponent(jTextField5)
                    .addComponent(jTextField7)
                    .addComponent(jTextField9)
                    .addComponent(jTextField11)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField12)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                    .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel18)
                            .addComponent(jLabel16)
                            .addComponent(jLabel10)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField14)
                            .addComponent(jTextField15)
                            .addComponent(jComboBox2, 0, 206, Short.MAX_VALUE)
                            .addComponent(jTextField17)
                            .addComponent(jTextField10)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextField13))
                                .addContainerGap())
                            .addComponent(jTextField18)))
                    .addComponent(jTextField16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel2))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))))
                        .addGap(6, 6, 6))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(5, 5, 5)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel14)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel32)
                        .addComponent(jButton15)
                        .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4)
                        .addComponent(jButton3)))
                .addGap(75, 75, 75))
        );

        jTabbedPane1.addTab("Account Creation", jPanel9);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 922, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 353, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Refferals", jPanel7);

        jTextField2.setEditable(false);
        jTextField2.setBorder(null);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jButton6.setText("Hide news");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jTextField2)
                        .addGap(34, 34, 34))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addContainerGap(846, Short.MAX_VALUE))
                    .addComponent(jTabbedPane1)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addGap(38, 38, 38)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(258, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 102));
        jLabel1.setText("logged in as");

        jButton1.setIcon(new javax.swing.ImageIcon("C:\\Users\\LATITUDE E5430\\Documents\\NetBeansProjects\\DigySacco\\Icons\\logout.png")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(232, 232, 232)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(31, 31, 31)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(218, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 808, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton7)
                        .addGap(16, 16, 16)))
                .addGap(71, 71, 71))
            .addGroup(layout.createSequentialGroup()
                .addGap(252, 252, 252)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 695, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(543, 543, 543)
                .addComponent(jButton7)
                .addGap(18, 18, 18)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setSize(new java.awt.Dimension(1113, 689));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
           // TODO add your handling code here:
         setVisible(false);
         
         
         
         
        
         
     
        
       

        
       
        Authentification obj=new Authentification();
        obj.setVisible(true);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
       if(jTextField3.getText().isEmpty()){JOptionPane.showMessageDialog(null,"Enter the customer's first Name");}
      else if(jTextField5.getText().isEmpty()){JOptionPane.showMessageDialog(null,"Enter the customer's middle Name");} 
      else if(jTextField7.getText().isEmpty()){JOptionPane.showMessageDialog(null,"Enter the customer's last Name");}  
      else if(jTextField9.getText().isEmpty()){JOptionPane.showMessageDialog(null,"Enter the customer's ID or Passport No");}  
      else if(((JTextField)jDateChooser2.getDateEditor().getUiComponent()).getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Enter customer's date of birth");}  
      else if(jTextField11.getText().isEmpty()){JOptionPane.showMessageDialog(null,"Enter the customer's Physical address");}     
      else if(jTextField12.getText().isEmpty()){JOptionPane.showMessageDialog(null,"Enter the customer's Email address");}     
      else if(jTextField13.getText().isEmpty()){JOptionPane.showMessageDialog(null,"Enter the customer's Phone Number");}   
      else if(jTextField16.getText().isEmpty()){JOptionPane.showMessageDialog(null,"Enter the customer's Next of kin");}  
      else if(jTextField17.getText().isEmpty()){JOptionPane.showMessageDialog(null,"Enter the customer's Next of kin phone No");}       
        
      else{
       jTextField10.setText(jTextField1.getText());
        Random rn=new Random();
         int accountno=rn.nextInt(100000)+100000;
         jTextField15.setText(Integer.toString(accountno));
         jTextField18.setText(setDate());
         jTextField14.setText("0.0");
         jDateChooser2.setEnabled(false);
         jButton2.setEnabled(false);
         jTextField3.setEditable(false);
         jTextField5.setEditable(false);
         jTextField7.setEditable(false);
         jTextField16.setEditable(false);
         jTextField9.setEditable(false);
         jTextField17.setEditable(false);
         jTextField11.setEditable(false);
         jTextField12.setEditable(false);
         jTextField13.setEditable(false);
         jTextField15.setEditable(false);
         jTextField14.setEditable(false);
         jTextField10.setEditable(false);
         jTextField18.setEditable(false);
         jComboBox1.setEnabled(false);
         jComboBox2.setEnabled(false);
         jButton15.setEnabled(false);
            }
         
  
         
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField15ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jTextField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField12ActionPerformed

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         
     
      if(jTextField3.getText().isEmpty()){JOptionPane.showMessageDialog(null,"Enter the customer's first Name");}
      else if(jTextField5.getText().isEmpty()){JOptionPane.showMessageDialog(null,"Enter the customer's middle Name");} 
      else if(jTextField7.getText().isEmpty()){JOptionPane.showMessageDialog(null,"Enter the customer's last Name");}  
      else if(jTextField9.getText().isEmpty()){JOptionPane.showMessageDialog(null,"Enter the customer's ID or Passport No");}  
      else if(((JTextField)jDateChooser2.getDateEditor().getUiComponent()).getText().isEmpty()){
         JOptionPane.showMessageDialog(null,"Enter customer's date of birth");}  
    else if(jTextField11.getText().isEmpty()){JOptionPane.showMessageDialog(null,"Enter the customer's Physical address");}     
    else if(jTextField12.getText().isEmpty()){JOptionPane.showMessageDialog(null,"Enter the customer's Email address");}     
    else if(jTextField13.getText().isEmpty()){JOptionPane.showMessageDialog(null,"Enter the customer's Phone Number");}   
    else if(jTextField16.getText().isEmpty()){JOptionPane.showMessageDialog(null,"Enter the customer's Next of kin");}  
    else if(jTextField17.getText().isEmpty()){JOptionPane.showMessageDialog(null,"Enter the customer's Next of kin phone No");}       
    else if(jTextField15.getText().isEmpty()){JOptionPane.showMessageDialog(null,"Click generate to produce customer account number");}   
    else if((jTextField22.getText()).equals("not set")){JOptionPane.showMessageDialog(null,"Upload customer image");}
    
    else{int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "By clicking create you are responsible and liable to creation of this account by ensuring that all the necessary documents are attached.", "security box", dialogButton);
       if(dialogResult == 0) {
       
    
      String sql="INSERT INTO customer_accounts (first_Name, middle_Name,"
                 + "last_Name,Next_of_kin,Next_of_kin_Phone_No,ID_or_passport_No,DOB,Gender,"
                 + "Physical_Address,Email_address,"
                 + "Phone_No,Account_Type,Date_Created,Account_No,image_link,Balance,Staff) "
                 + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        try{
            
            try{
            BufferedImage image=ImageIO.read(file);
            //String location="F:projectImages/"+file.getName();
            String format="png";
            
            //ImageIO.write(image,format,new File(location));
            
 
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
            
            
            
            
            
            
            BufferedImage image=ImageIO.read(file);
            String location="F:projectImages/"+file.getName();
             String format="png";
            ImageIO.write(image,format,new File(location));
            pst=conn.prepareStatement(sql);
            pst.setString(1,jTextField3.getText());
            pst.setString(2,jTextField5.getText());
            pst.setString(3,jTextField7.getText());
            pst.setString(4,jTextField16.getText());
            pst.setString(5,jTextField17.getText());
            pst.setString(6,jTextField9.getText());
            pst.setString(7,((JTextField)jDateChooser2.getDateEditor().getUiComponent()).getText());
            pst.setString(8,(String) jComboBox1.getSelectedItem());
            pst.setString(9,jTextField11.getText());
            pst.setString(10,jTextField12.getText());
            pst.setString(11,jTextField13.getText());
            pst.setString(12,(String) jComboBox2.getSelectedItem());
            pst.setString(13,jTextField18.getText());
            pst.setString(14,jTextField15.getText());
            pst.setString(15,location);
            pst.setDouble(16,Double.parseDouble(jTextField14.getText()));
            pst.setString(17,jTextField10.getText());
       
            
            pst.execute();
            
            JOptionPane.showMessageDialog(null,"Customer Account Successfully Created");
            jTextField3.setEditable(true);
            jTextField5.setEditable(true);
            jTextField7.setEditable(true);
            jTextField16.setEditable(true);
            jTextField9.setEditable(true);
            jTextField17.setEditable(true);
            jTextField11.setEditable(true);
            jTextField12.setEditable(true);
            jTextField13.setEditable(true);
            jTextField15.setEditable(true);
            jTextField14.setEditable(true);
            jTextField10.setEditable(true);
            jTextField18.setEditable(true);
            jComboBox1.setEnabled(true);
            jButton15.setEnabled(true);
            jComboBox2.setEnabled(true);
            jDateChooser2.setEnabled(true);

            clearFields();
            jTextField22.setText("not set");
            jButton2.setEnabled(true);
            
                
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);
            
        }}else{clearFields();}
    }                                   
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
         clearFields();
        jTextField22.setText("not set");
        jButton2.setEnabled(true);
        jDateChooser2.setEnabled(true);
        jButton15.setEnabled(true);
        jTextField3.setEditable(true);
            jTextField5.setEditable(true);
            jTextField7.setEditable(true);
            jTextField16.setEditable(true);
            jTextField9.setEditable(true);
            jTextField17.setEditable(true);
            jTextField11.setEditable(true);
            jTextField12.setEditable(true);
            jTextField13.setEditable(true);
            jTextField15.setEditable(true);
            jTextField14.setEditable(true);
            jTextField10.setEditable(true);
            jTextField18.setEditable(true);
            jComboBox1.setEnabled(true);
            jComboBox2.setEnabled(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        try{
        double current_Balance=Double.parseDouble(jTextField21.getText());
         double deposit_Amount=Double.parseDouble(jTextField24.getText());
        try{
         if(jTextField19.getText().isEmpty()){
           JOptionPane.showMessageDialog(null,"Enter the customers account Number");}
         
        else if(jTextField24.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Enter the deposit Amount");
        }
              
        else if(jTextField20.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Enter the customer's account Number");}
        else if(deposit_Amount<=0){
            JOptionPane.showMessageDialog(null,"Invalid Deposit Amount");}
         else{
         
         double newBalance=current_Balance+deposit_Amount;
        String sql="UPDATE customer_accounts SET Balance=? WHERE account_No=?";

        try{pst=conn.prepareStatement(sql);
            pst.setDouble(1,newBalance);
            pst.setString(2,jTextField19.getText());
            pst.executeUpdate();
            ImageIcon ob=new ImageIcon(imagelink);
            
            JOptionPane.showMessageDialog(null,"Confirm "+jTextField20.getText()+ "'s image:  deposit of ksh "+jTextField24.getText(),"security",JOptionPane.INFORMATION_MESSAGE,ob);
            JOptionPane.showMessageDialog(null,"Amount successfully deposited");
            
            rs.close();
            pst.close();
            //Recording the transaction
            
            String transaction="Cash Normal Deposit";
            String sql2="INSERT INTO transactions_record "
                    + "(customer_Name,Account_No,Transaction_Type,Amount,Time,Served_by)"
                    + " VALUES (?,?,?,?,?,?)";
            try{
            pst=conn.prepareStatement(sql2);
            pst.setString(1,jTextField20.getText());
            pst.setString(2,jTextField19.getText());
            pst.setString(3,(String) jComboBox4.getSelectedItem());
            pst.setDouble(4,Double.parseDouble(jTextField24.getText()));       
            pst.setString(5,setDate());
            pst.setString(6,jTextField1.getText());
            pst.execute();
                pst.close();
                rs.close();
                jTextField19.setEditable(true);
            jTextField19.setText("");
            jTextField20.setText("");
            jTextField21.setText("");
            jTextField24.setText("");
            jLabel31.setIcon(new ImageIcon("")); 
            jButton10.setEnabled(true);
                
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);
                    
                    }}
        
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
        }catch(NumberFormatException e) {JOptionPane.showMessageDialog(null,"Invalid amount");}
    }//GEN-LAST:event_jButton5ActionPerformed
    
    private void jTextField19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField19ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Contact Administrator");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
             if(jTextField19.getText().isEmpty()){
           JOptionPane.showMessageDialog(null,"Enter the customers account Number");}
             else{
        String sql="SELECT First_Name,Middle_Name,Last_Name,image_link,Balance FROM customer_accounts WHERE Account_No=?";
        
        try{
            pst=conn.prepareStatement(sql);
            pst.setString(1,jTextField19.getText());
            rs=pst.executeQuery();
            
            if(rs.next()){
                String col1=rs.getString("First_Name");               
                String col2=rs.getString("Middle_Name");
                String col3=rs.getString("Last_Name");
                String col4=rs.getString("image_link");
                imagelink=col4;
                String fullname=col1+" "+col2+" "+col3;
                jTextField20.setText(fullname);
                String col5=rs.getString("Balance");
                jTextField21.setText(col5);
                jLabel31.setIcon(new ImageIcon(col4));
                jTextField19.setEditable(false);
                jButton10.setEnabled(false);
            }else{
                JOptionPane.showMessageDialog(null,"Customer Account No does not exist");
            }

            
            
             }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }}
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        jTextField19.setEditable(true);
        jTextField19.setText("");
        jTextField20.setText("");
        jTextField21.setText("");
        jTextField24.setText("");
        jLabel31.setIcon(new ImageIcon("")); 
        jButton10.setEnabled(true);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        
        Deposits ob=new Deposits();
        ob.setVisible(true);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jTextField21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField21ActionPerformed

    private void jTextField16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField16ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        
        JFileChooser jFileChooser=new JFileChooser();
        jFileChooser.setDialogTitle("Please Select a photo");
        jFileChooser.setFileFilter(new Home.filetypefilter(".jpg",".png"));
        int result=jFileChooser.showOpenDialog(null);
        jFileChooser.setMultiSelectionEnabled(false);
         result=JFileChooser.APPROVE_OPTION;
         if(result==JFileChooser.APPROVE_OPTION){
            
              file=jFileChooser.getSelectedFile();
               if (file!=null){
              
              JOptionPane.showMessageDialog(null,"image successfully uploaded");
              jTextField22.setText("uploaded");}

          else{jTextField22.setText("not set");}}
        
        
        
        
        
        
        
        
        
        
        
        
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
         try{
         double payment_Amount=Double.parseDouble(jTextField31.getText());
         double current_Balance=Double.parseDouble(jTextField29.getText());
         if(jTextField27.getText().isEmpty()){
           JOptionPane.showMessageDialog(null,"Enter the customers account Number");}
         
        else if(jTextField31.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Enter the Payment Amount");
        }
        //if the below field is not set then it means that the user has not enetered the account
        else if(jTextField28.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Enter the Customer's account Number");}
        
        else if(payment_Amount>current_Balance){
            JOptionPane.showMessageDialog(null,"Payment amount is more than the available balance");
        }
        
        else if(payment_Amount==0){
            JOptionPane.showMessageDialog(null,"Cannot pay Nill");
        }
        else if(payment_Amount<0){
            JOptionPane.showMessageDialog(null,"Invalid Amount");}
         else{
         //double current_Balance=Double.parseDouble(jTextField29.getText());
         //double payment_Amount=Double.parseDouble(jTextField31.getText());
         double newBalance=current_Balance-payment_Amount;
        String sql="UPDATE customer_accounts SET Balance=? WHERE account_No=?";

        try{pst=conn.prepareStatement(sql);
            pst.setDouble(1,newBalance);
            pst.setString(2,jTextField27.getText());
            pst.executeUpdate();
            ImageIcon ob=new ImageIcon(imagelink);
            
            JOptionPane.showMessageDialog(null,"Confirm "+jTextField28.getText()+ "'s image:payment of ksh "+jTextField31.getText(),"security",JOptionPane.INFORMATION_MESSAGE,ob);
            JOptionPane.showMessageDialog(null,"Amount successfully payed");
            
            rs.close();
            pst.close();
            //Recording the transaction
            
           
            String sql2="INSERT INTO transactions_record "
                    + "(customer_Name,Account_No,Transaction_Type,Amount,Time,Served_by)"
                    + " VALUES (?,?,?,?,?,?)";
            try{
            pst=conn.prepareStatement(sql2);
            pst.setString(1,jTextField28.getText());
            pst.setString(2,jTextField27.getText());
            pst.setString(3,(String) jComboBox5.getSelectedItem());
            pst.setDouble(4,Double.parseDouble(jTextField31.getText()));       
            pst.setString(5,setDate());
            pst.setString(6,jTextField1.getText());
            pst.execute();
                pst.close();
                rs.close();
                jTextField27.setEditable(true);
            jTextField27.setText("");
            jTextField28.setText("");
            jTextField29.setText("");
            jTextField31.setText("");
            jTextField30.setText("");
            jLabel39.setIcon(new ImageIcon("")); 
            jButton20.setEnabled(true);
                
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
            }catch(Exception e){JOptionPane.showMessageDialog(null,e);
                    
            }}
        
            }catch(Exception e) {JOptionPane.showMessageDialog(null,"Invalid Amount");}                               
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jTextField27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField27ActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
             if(jTextField27.getText().isEmpty()){
           JOptionPane.showMessageDialog(null,"Enter the customers account Number");}
             else{
        String sql="SELECT First_Name,Middle_Name,Last_Name,image_link,Balance FROM customer_accounts WHERE Account_No=?";
        
        try{
            pst=conn.prepareStatement(sql);
            pst.setString(1,jTextField27.getText());
            rs=pst.executeQuery();
            
            if(rs.next()){
                String col1=rs.getString("First_Name");               
                String col2=rs.getString("Middle_Name");
                String col3=rs.getString("Last_Name");
                String col4=rs.getString("image_link");
                imagelink=col4;
                String fullname=col1+" "+col2+" "+col3;
                jTextField28.setText(fullname);
                String col5=rs.getString("Balance");
                jTextField29.setText(col5);
                jLabel39.setIcon(new ImageIcon(col4));
                jTextField27.setEditable(false);
                jButton20.setEnabled(false);
            }else{
                JOptionPane.showMessageDialog(null,"Customer Account No does not exist");
            }

            
            
             }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }}
                                             

    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        jTextField27.setEditable(true);
        jTextField27.setText("");
        jTextField28.setText("");
        jTextField29.setText("");
        jTextField31.setText("");
        jTextField31.setText("");
        jLabel39.setIcon(new ImageIcon("")); 
        jButton20.setEnabled(true);
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jTextField31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField31ActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jTextField31ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Contact Administrator");
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        payments ob=new payments();
        ob.setVisible(true);
        
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if(jButton6.getText().equals("Hide news")){
            jTextField2.setVisible(false);
            jButton6.setText("Show news");
        }
        else if(jButton6.getText().equals("Show news")){
            jTextField2.setVisible(true);
        
        jButton6.setText("Hide news");} 
        
        
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
        
        String sql="select SUM(Balance)  AS \"totalbalance\" FROM customer_accounts";
        
        try{
            pst=conn.prepareStatement(sql);
          
            rs=pst.executeQuery();
            
            if(rs.next()){
                
                double balance=rs.getDouble("totalbalance");
                
                jTextField25.setText(Double.toString(balance));
                
                //checking the status
                if(balance>300){
                    
                    jTextField26.setText("above limit");
                    jTextField26.setForeground(Color.red);
                  
                }else if(balance>100&&balance<300){
                    jTextField26.setText("within range");
                }
                else if(balance<100){
                     jTextField26.setText("low-request ");
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
           
        }
        
        
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jTextField39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField39ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField39ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            
          
            public void run() {
                  
                ///new Home().setVisible(true);
                 //class already initialized in the constructor.
                //new MarqueeTest().display();//This has already been defined in the constructor and must not be defined here
                
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    public static javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextField jTextField35;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JTextField jTextField37;
    private javax.swing.JTextField jTextField38;
    private javax.swing.JTextField jTextField39;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField40;
    private javax.swing.JTextField jTextField41;
    private javax.swing.JTextField jTextField42;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}

