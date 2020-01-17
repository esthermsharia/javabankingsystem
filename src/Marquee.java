

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.Timer;


public class Marquee {
    

 
  
    /*public void display() {
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
             
        
       
       //try{
          //Authentification ob=new Authentification();
        //String s=ob.getMessage();
        //try{
        
        //}catch(Exception e){}
        //JOptionPane.showMessageDialog(null,s);
              String s  ="There will be home visits for all loan clients in Taveta Branch..."
                + "All the concerned staff are advised to attend to the same"
                + "...All cashiers are going to have their annual meeting in"
                + " Nairobi next month on 15th...There will be a team from head "
                + "office that will be vising Tommorow...You are reminded of our"
                + " organisations key values...1.Honesty...2.Accountability...3."
                + "Professionalism...4.Team work. Remember that \'The customer is always the king\'" ;
        MarqueePanel mp = new MarqueePanel(s, 100);
     
        mp.start();}
       //catch(SQLException e){JOptionPane.showMessageDialog(null,e);}}
    

    //}
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
        //Home ob=new Home();
        //ob.jTextField2.setFont(new Font("Serif", Font.PLAIN, 36));
        /// ob.jTextField2.setText(sb.toString());
         //ob.jTextField2.setBackground(Color.CYAN);
        //this.add(label);
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        index++;
        if (index > s.length() - n) {
            index = 0;
        }
        try{
            
         
        }catch(Exception p){}
        Home ob=new Home();
        
         ob.jTextField2.setText(s.substring(index, index + n));
        
         
    }}*/
}

