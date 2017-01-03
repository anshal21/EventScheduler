package eventschedulerclient;

import details.slotInfo;
import com.sun.org.apache.xalan.internal.lib.ExsltDatetime;
import com.sun.xml.internal.ws.util.StringUtils;
import details.eventInfo;
import details.invitedEvent;
import details.slotFilter;
import details.slotResponse;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author anshal
 */
public class UserHome extends javax.swing.JFrame {

    /**
     * Creates new form UserHome
     */
    ArrayList<slotInfo> bookingData=new ArrayList<slotInfo>();
    ArrayList<eventInfo> eventData=new ArrayList<eventInfo>();
    ArrayList<invitedEvent> inviteData =new ArrayList<invitedEvent>();
    ArrayList<slotResponse> filteredSlots=new ArrayList<slotResponse>();
    public final int SLOT=1,BOOKING=2,ASKBOOKINGS=3,ASK_EVENT=5,INVITATIONS=9,FILE_REQUEST=10,APPROVE=11,ASK_ALLEVENT=15;
    DefaultListModel DLM=new DefaultListModel();
    DefaultListModel SDLM=new DefaultListModel();
    DefaultListModel eventList=new DefaultListModel();
    DefaultListModel slotList=new DefaultListModel();
    DefaultListModel otherEvents=new DefaultListModel();
    functionTools fTools;
    String ActiveVenueName=null;
    int curr_venue_id=0;
    String curr_venue;
    int clientId;
    String Uname,Name;
    Socket sock;
    ArrayList<Integer> slots=new ArrayList<Integer>();
    DataInputStream dis;
    ArrayList<venue_info> vn=new ArrayList<venue_info>();
    DataOutputStream dos; public class venue_info{
        int venue_id;
        String venue_name;
        int seats,ac,projecter;
    }
    int port;
    public void setDateTimeAtrributes(){
        for(int i=0;i<60;i++)
        {
            String s=""+i;
            if(s.length()<2){
                s="0"+s;
            }
            et_min.addItem(s);
            st_min.addItem(s);
        }
        for(int i=0;i<=23;i++){
            String s=""+i;
            if(s.length()<2)
                s="0"+s;
            et_hr.addItem(s);
            st_hr.addItem(s); 
        }
        st_hr.setSelectedIndex(0);
        et_hr.setSelectedIndex(0);
        st_min.setSelectedIndex(0);
        et_min.setSelectedIndex(0);
    }
    public void switchDateTimeAccesibility(){
        if(!fYear.isEnabled()){
            fYear.setEnabled(true);tYear.setEnabled(true);fMonth.setEnabled(true);tMonth.setEnabled(true);fDay.setEnabled(true);tDay.setEnabled(true);
            st_hr.setEnabled(true);st_min.setEnabled(true);et_hr.setEnabled(true);et_min.setEnabled(true);
        }
        else{
            fYear.setEnabled(false);tYear.setEnabled(false);fMonth.setEnabled(false);tMonth.setEnabled(false);fDay.setEnabled(false);tDay.setEnabled(false);
            st_hr.setEnabled(false);st_min.setEnabled(false);et_hr.setEnabled(false);et_min.setEnabled(false);
        }
        
    }
    public UserHome(int ClientId,String Uname,String Name,int port) {
        try {
            initComponents();
            this.clientId=ClientId;
            venue_list.setModel(DLM);
            slot_details.setModel(SDLM);
            bookedSlotList.setModel(slotList);
            eventListing.setModel(eventList);
            otherEventsList.setModel(otherEvents);
            this.Uname=Uname;
            this.port=port;
            this.Name=Name;
            sock=new Socket("localhost", port);
            dis=new DataInputStream(sock.getInputStream());
            dos=new DataOutputStream(sock.getOutputStream());
            setDateTimeAtrributes();
            fTools=new functionTools();
            Welcome.setText("Hello! "+Name);
          
            SwitchPanel(WelcomePanel);
            decorate_arena();
        } catch (IOException ex) {
            Logger.getLogger(UserHome.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public String padString(String s,int len){
        if(s==null)
               return s;
         int l=s.length();
         for(int i=0;i<len-l;i++){
             s=s+" ";
         }
         return s;
    }
    public void SwitchPanel(JPanel jp){
             MainPanel.removeAll();
            MainPanel.repaint();
            MainPanel.revalidate();
            MainPanel.add(jp);
            MainPanel.repaint();
            MainPanel.revalidate();
    }
    public void SendRequest_1(){
        try {
            dos.writeInt(1);
        } catch (IOException ex) {
            Logger.getLogger(UserHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public venue_info read_obj(){
         venue_info tmp=new venue_info();
        try {
            tmp.venue_name=dis.readUTF();
            tmp.venue_id=dis.readInt();
            tmp.seats=dis.readInt();
            tmp.ac=dis.readInt();
            tmp.projecter=dis.readInt();
            return tmp;
        } catch (IOException ex) {
            Logger.getLogger(UserHome.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tmp;
    }
    public void decorate_arena(){
        try {
            
            int sz=dis.readInt();
            System.out.println("this "+sz);
            for(int i=0;i<sz;i++){
                venue_info vi=read_obj();
                vn.add(vi);
                System.out.println(vi.venue_name);
                DLM.addElement(vi.venue_name);
                
            }
        } catch (IOException ex) {
            Logger.getLogger(UserHome.class.getName()).log(Level.SEVERE, null, ex);
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

        Welcome = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        venue_list = new javax.swing.JList();
        MainPanel = new javax.swing.JPanel();
        WelcomePanel = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        slotsPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        bookedSlotList = new javax.swing.JList();
        addEvent = new javax.swing.JButton();
        eventPanel = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        eventListing = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        showDetails = new javax.swing.JButton();
        invitedEvents = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        otherEventsList = new javax.swing.JList();
        downloadInvite = new javax.swing.JButton();
        acceptRequest = new javax.swing.JButton();
        deleteRequest = new javax.swing.JButton();
        InfoPanel = new javax.swing.JPanel();
        venueName = new javax.swing.JLabel();
        capacity = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        AcField = new javax.swing.JLabel();
        ProjField = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        slot_details = new javax.swing.JList();
        book_slot = new javax.swing.JButton();
        year = new javax.swing.JTextField();
        month = new javax.swing.JTextField();
        day = new javax.swing.JTextField();
        filter = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        fYear = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        fMonth = new javax.swing.JTextField();
        fDay = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tMonth = new javax.swing.JTextField();
        tYear = new javax.swing.JTextField();
        tDay = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        st_hr = new javax.swing.JComboBox();
        st_min = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        et_hr = new javax.swing.JComboBox();
        et_min = new javax.swing.JComboBox();
        fileterButton = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        ShowInfo = new javax.swing.JButton();
        bookedSlots = new javax.swing.JButton();
        myEvent = new javax.swing.JButton();
        showEvents = new javax.swing.JButton();
        Invitations = new javax.swing.JButton();
        IamKalam = new javax.swing.JLabel();
        vNum = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Welcome.setFont(new java.awt.Font("Ubuntu", 2, 18)); // NOI18N
        Welcome.setForeground(new java.awt.Color(0, 128, 0));
        Welcome.setText("jLabel1");
        getContentPane().add(Welcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 159, 25));

        venue_list.setBackground(new java.awt.Color(151, 252, 151));
        venue_list.setFont(new java.awt.Font("Ubuntu", 0, 17)); // NOI18N
        venue_list.setForeground(new java.awt.Color(0, 100, 0));
        venue_list.setSelectionBackground(new java.awt.Color(0, 128, 0));
        venue_list.setSelectionForeground(new java.awt.Color(151, 252, 151));
        jScrollPane1.setViewportView(venue_list);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 107, 200, 460));

        MainPanel.setForeground(new java.awt.Color(152, 251, 152));
        MainPanel.setOpaque(false);
        MainPanel.setLayout(new java.awt.CardLayout());

        WelcomePanel.setBackground(new java.awt.Color(152, 251, 152));
        WelcomePanel.setOpaque(false);

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/slogo.png"))); // NOI18N

        javax.swing.GroupLayout WelcomePanelLayout = new javax.swing.GroupLayout(WelcomePanel);
        WelcomePanel.setLayout(WelcomePanelLayout);
        WelcomePanelLayout.setHorizontalGroup(
            WelcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WelcomePanelLayout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(99, Short.MAX_VALUE))
        );
        WelcomePanelLayout.setVerticalGroup(
            WelcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, WelcomePanelLayout.createSequentialGroup()
                .addContainerGap(141, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(88, 88, 88))
        );

        MainPanel.add(WelcomePanel, "card3");

        slotsPanel.setBackground(new java.awt.Color(152, 251, 152));
        slotsPanel.setOpaque(false);

        bookedSlotList.setBackground(new java.awt.Color(255, 255, 153));
        bookedSlotList.setFont(new java.awt.Font("Ubuntu", 0, 17)); // NOI18N
        bookedSlotList.setForeground(new java.awt.Color(0, 100, 0));
        bookedSlotList.setSelectionBackground(new java.awt.Color(0, 128, 0));
        bookedSlotList.setSelectionForeground(new java.awt.Color(255, 215, 0));
        jScrollPane4.setViewportView(bookedSlotList);

        addEvent.setBackground(new java.awt.Color(152, 251, 152));
        addEvent.setFont(new java.awt.Font("Ubuntu", 0, 17)); // NOI18N
        addEvent.setForeground(new java.awt.Color(0, 128, 0));
        addEvent.setText("Add Event");
        addEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEventActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout slotsPanelLayout = new javax.swing.GroupLayout(slotsPanel);
        slotsPanel.setLayout(slotsPanelLayout);
        slotsPanelLayout.setHorizontalGroup(
            slotsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slotsPanelLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(slotsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, slotsPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, slotsPanelLayout.createSequentialGroup()
                        .addComponent(addEvent, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(127, 127, 127))))
        );
        slotsPanelLayout.setVerticalGroup(
            slotsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, slotsPanelLayout.createSequentialGroup()
                .addContainerGap(188, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addEvent)
                .addGap(147, 147, 147))
        );

        MainPanel.add(slotsPanel, "card4");

        eventPanel.setBackground(new java.awt.Color(152, 251, 152));
        eventPanel.setForeground(new java.awt.Color(0, 128, 0));
        eventPanel.setOpaque(false);

        eventListing.setBackground(new java.awt.Color(255, 255, 153));
        eventListing.setFont(new java.awt.Font("Ubuntu", 0, 17)); // NOI18N
        eventListing.setForeground(new java.awt.Color(0, 100, 0));
        eventListing.setSelectionBackground(new java.awt.Color(0, 128, 0));
        eventListing.setSelectionForeground(new java.awt.Color(255, 215, 0));
        jScrollPane5.setViewportView(eventListing);

        jButton1.setBackground(new java.awt.Color(152, 251, 152));
        jButton1.setFont(new java.awt.Font("Ubuntu", 0, 17)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 128, 0));
        jButton1.setText("Edit Event");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        showDetails.setBackground(new java.awt.Color(152, 251, 152));
        showDetails.setFont(new java.awt.Font("Ubuntu", 0, 17)); // NOI18N
        showDetails.setForeground(new java.awt.Color(0, 128, 0));
        showDetails.setText("showDetails");
        showDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showDetailsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout eventPanelLayout = new javax.swing.GroupLayout(eventPanel);
        eventPanel.setLayout(eventPanelLayout);
        eventPanelLayout.setHorizontalGroup(
            eventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eventPanelLayout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addGroup(eventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(showDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(135, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, eventPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
        );
        eventPanelLayout.setVerticalGroup(
            eventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eventPanelLayout.createSequentialGroup()
                .addContainerGap(140, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(showDetails)
                .addGap(87, 87, 87))
        );

        MainPanel.add(eventPanel, "card5");

        invitedEvents.setBackground(new java.awt.Color(152, 251, 152));
        invitedEvents.setOpaque(false);

        otherEventsList.setBackground(new java.awt.Color(255, 255, 153));
        otherEventsList.setFont(new java.awt.Font("Ubuntu", 0, 17)); // NOI18N
        otherEventsList.setForeground(new java.awt.Color(0, 100, 0));
        otherEventsList.setSelectionBackground(new java.awt.Color(0, 128, 0));
        otherEventsList.setSelectionForeground(new java.awt.Color(255, 215, 0));
        jScrollPane6.setViewportView(otherEventsList);

        downloadInvite.setBackground(new java.awt.Color(152, 251, 151));
        downloadInvite.setFont(new java.awt.Font("Ubuntu", 0, 17)); // NOI18N
        downloadInvite.setForeground(new java.awt.Color(0, 128, 0));
        downloadInvite.setText("DownLoad Invite");
        downloadInvite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadInviteActionPerformed(evt);
            }
        });

        acceptRequest.setBackground(new java.awt.Color(152, 251, 151));
        acceptRequest.setFont(new java.awt.Font("Ubuntu", 0, 17)); // NOI18N
        acceptRequest.setForeground(new java.awt.Color(0, 128, 0));
        acceptRequest.setText("Accept Request");
        acceptRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptRequestActionPerformed(evt);
            }
        });

        deleteRequest.setBackground(new java.awt.Color(152, 251, 151));
        deleteRequest.setFont(new java.awt.Font("Ubuntu", 0, 17)); // NOI18N
        deleteRequest.setForeground(new java.awt.Color(0, 128, 0));
        deleteRequest.setText(" Delete Request");
        deleteRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteRequestActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout invitedEventsLayout = new javax.swing.GroupLayout(invitedEvents);
        invitedEvents.setLayout(invitedEventsLayout);
        invitedEventsLayout.setHorizontalGroup(
            invitedEventsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(invitedEventsLayout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(invitedEventsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, invitedEventsLayout.createSequentialGroup()
                        .addGroup(invitedEventsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(invitedEventsLayout.createSequentialGroup()
                                .addComponent(acceptRequest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(deleteRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, invitedEventsLayout.createSequentialGroup()
                        .addComponent(downloadInvite, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(147, 147, 147))))
        );
        invitedEventsLayout.setVerticalGroup(
            invitedEventsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(invitedEventsLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(invitedEventsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acceptRequest)
                    .addComponent(deleteRequest))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(downloadInvite)
                .addContainerGap(256, Short.MAX_VALUE))
        );

        MainPanel.add(invitedEvents, "card6");

        InfoPanel.setBackground(new java.awt.Color(152, 251, 152));
        InfoPanel.setForeground(new java.awt.Color(0, 128, 0));
        InfoPanel.setOpaque(false);

        venueName.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N

        capacity.setText("jLabel1");

        jLabel1.setText("Ac Availiable :");

        jLabel2.setText("Projecter : ");

        AcField.setText("jLabel3");

        ProjField.setText("jLabel4");

        jLabel3.setText("Seats");

        slot_details.setBackground(new java.awt.Color(255, 255, 153));
        slot_details.setFont(new java.awt.Font("Ubuntu", 0, 17)); // NOI18N
        slot_details.setForeground(new java.awt.Color(0, 100, 0));
        slot_details.setSelectionBackground(new java.awt.Color(0, 128, 0));
        slot_details.setSelectionForeground(new java.awt.Color(255, 215, 0));
        jScrollPane3.setViewportView(slot_details);

        book_slot.setBackground(new java.awt.Color(152, 251, 152));
        book_slot.setFont(new java.awt.Font("Ubuntu", 0, 17)); // NOI18N
        book_slot.setForeground(new java.awt.Color(0, 128, 0));
        book_slot.setText("Book Slot");
        book_slot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                book_slotActionPerformed(evt);
            }
        });

        year.setFont(new java.awt.Font("Ubuntu", 0, 17)); // NOI18N

        month.setFont(new java.awt.Font("Ubuntu", 0, 17)); // NOI18N
        month.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthActionPerformed(evt);
            }
        });

        day.setFont(new java.awt.Font("Ubuntu", 0, 17)); // NOI18N

        filter.setFont(new java.awt.Font("Ubuntu", 0, 17)); // NOI18N
        filter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Default", "Morning", "After Noon", "Evening", "Night", "Custom" }));

        jLabel5.setText("Date:");

        fYear.setFont(new java.awt.Font("Ubuntu", 0, 17)); // NOI18N

        jLabel6.setText("from");

        fMonth.setFont(new java.awt.Font("Ubuntu", 0, 17)); // NOI18N

        fDay.setFont(new java.awt.Font("Ubuntu", 0, 17)); // NOI18N

        jLabel7.setText("To");

        tMonth.setFont(new java.awt.Font("Ubuntu", 0, 17)); // NOI18N

        tYear.setFont(new java.awt.Font("Ubuntu", 0, 17)); // NOI18N

        tDay.setFont(new java.awt.Font("Ubuntu", 0, 17)); // NOI18N

        jLabel8.setText("Time:");

        jLabel9.setText("from");

        st_hr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                st_hrActionPerformed(evt);
            }
        });

        jLabel10.setText("To");

        fileterButton.setBackground(new java.awt.Color(152, 251, 152));
        fileterButton.setFont(new java.awt.Font("Ubuntu", 0, 17)); // NOI18N
        fileterButton.setForeground(new java.awt.Color(0, 128, 0));
        fileterButton.setText("Filter");
        fileterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileterButtonActionPerformed(evt);
            }
        });

        jLabel11.setText("YYYY");

        jLabel13.setText("MM");

        jLabel14.setText("DD");

        javax.swing.GroupLayout InfoPanelLayout = new javax.swing.GroupLayout(InfoPanel);
        InfoPanel.setLayout(InfoPanelLayout);
        InfoPanelLayout.setHorizontalGroup(
            InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InfoPanelLayout.createSequentialGroup()
                .addGroup(InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InfoPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(InfoPanelLayout.createSequentialGroup()
                                .addComponent(filter, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 234, Short.MAX_VALUE)
                                .addComponent(fileterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(InfoPanelLayout.createSequentialGroup()
                                .addGroup(InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel8))
                                .addGroup(InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InfoPanelLayout.createSequentialGroup()
                                        .addGroup(InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addGroup(InfoPanelLayout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addGap(7, 7, 7)
                                                .addComponent(fYear, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(fMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(fDay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(235, 235, 235))
                                    .addGroup(InfoPanelLayout.createSequentialGroup()
                                        .addGap(68, 68, 68)
                                        .addGroup(InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel7)
                                            .addGroup(InfoPanelLayout.createSequentialGroup()
                                                .addComponent(st_hr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(st_min, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(38, 38, 38)
                                                .addComponent(jLabel10))
                                            .addGroup(InfoPanelLayout.createSequentialGroup()
                                                .addGroup(InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(InfoPanelLayout.createSequentialGroup()
                                                        .addComponent(jLabel11)
                                                        .addGap(6, 6, 6)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel13)
                                                    .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(day, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel14))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(InfoPanelLayout.createSequentialGroup()
                                                .addComponent(tYear, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tDay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(InfoPanelLayout.createSequentialGroup()
                                                .addGap(27, 27, 27)
                                                .addComponent(et_hr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(et_min, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(book_slot, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addComponent(jScrollPane3))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InfoPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InfoPanelLayout.createSequentialGroup()
                        .addGroup(InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ProjField)
                            .addComponent(capacity, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AcField))
                        .addGap(94, 94, 94))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InfoPanelLayout.createSequentialGroup()
                        .addComponent(venueName, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(118, 118, 118))))
        );
        InfoPanelLayout.setVerticalGroup(
            InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InfoPanelLayout.createSequentialGroup()
                .addGroup(InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InfoPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(venueName, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(capacity))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InfoPanelLayout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(AcField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ProjField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fileterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filter, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(fYear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(tYear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tDay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fDay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(st_hr, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(st_min, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(et_hr, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(et_min, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(book_slot, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(day, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        MainPanel.add(InfoPanel, "card2");

        getContentPane().add(MainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(218, 39, -1, -1));

        ShowInfo.setBackground(new java.awt.Color(152, 251, 152));
        ShowInfo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        ShowInfo.setForeground(new java.awt.Color(0, 128, 0));
        ShowInfo.setText("Show Info");
        ShowInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowInfoActionPerformed(evt);
            }
        });
        getContentPane().add(ShowInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 0, 150, 30));

        bookedSlots.setBackground(new java.awt.Color(152, 251, 152));
        bookedSlots.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        bookedSlots.setForeground(new java.awt.Color(0, 128, 0));
        bookedSlots.setText("Booked Slots");
        bookedSlots.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookedSlotsActionPerformed(evt);
            }
        });
        getContentPane().add(bookedSlots, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 160, 30));

        myEvent.setBackground(new java.awt.Color(152, 251, 152));
        myEvent.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        myEvent.setForeground(new java.awt.Color(0, 128, 0));
        myEvent.setText("My Events");
        myEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myEventActionPerformed(evt);
            }
        });
        getContentPane().add(myEvent, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 160, 30));

        showEvents.setBackground(new java.awt.Color(152, 251, 152));
        showEvents.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        showEvents.setForeground(new java.awt.Color(0, 128, 0));
        showEvents.setText("show events");
        showEvents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showEventsActionPerformed(evt);
            }
        });
        getContentPane().add(showEvents, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 0, 150, 30));

        Invitations.setBackground(new java.awt.Color(152, 251, 152));
        Invitations.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        Invitations.setForeground(new java.awt.Color(0, 128, 0));
        Invitations.setText("Invitations");
        Invitations.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InvitationsActionPerformed(evt);
            }
        });
        getContentPane().add(Invitations, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 30));

        IamKalam.setBackground(new java.awt.Color(152, 251, 152));
        IamKalam.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        IamKalam.setForeground(new java.awt.Color(0, 128, 0));
        IamKalam.setText("    Available Venues");
        getContentPane().add(IamKalam, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 190, -1));

        vNum.setBackground(new java.awt.Color(152, 251, 151));
        vNum.setFont(new java.awt.Font("Ubuntu", 0, 17)); // NOI18N
        vNum.setForeground(new java.awt.Color(0, 128, 0));
        vNum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/bg.jpg"))); // NOI18N
        getContentPane().add(vNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, 0, 870, 590));

        jLabel4.setText("jLabel4");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void getSlots(){
        try {
            dos.writeInt(SLOT);
        } catch (IOException ex) {
            Logger.getLogger(UserHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public slotFilter modifyFilter(slotFilter sf){
        final int DEFAULT=0,MORNING=1,AFTER_NOON=2,EVENING=3,NIGHT=4,CUSTOM=5;
        if(filter.getSelectedIndex()==DEFAULT){
            return sf;
        }
        else if(filter.getSelectedIndex()==MORNING){
            sf.endingTime="1200";
            return sf;
        }
        else if(filter.getSelectedIndex()==AFTER_NOON){
            sf.startTime="1200";
            sf.endingTime="1800";
            return sf;
        }
        else if(filter.getSelectedIndex()==EVENING){
            sf.startTime="1800";
            sf.endingTime="2100";
            return sf;
        }
        else if(filter.getSelectedIndex()==NIGHT){
            sf.startTime="2100";
            sf.endingTime="2400";
            return sf;
        }
        else{
            sf.includeDate=true;
            String startTime=st_hr.getSelectedItem().toString()+st_min.getSelectedItem().toString();
            String endTime=et_hr.getSelectedItem().toString()+et_min.getSelectedItem().toString();
            String StartDate=fYear.getText()+"-"+fMonth.getText()+"-"+fDay.getText();
            String endDate=tYear.getText()+"-"+tMonth.getText()+"-"+tDay.getText();
           
            System.out.println(StartDate+ " "+ endDate);
            sf.startingDate=StartDate;
            sf.endingDate=endDate;
            sf.startTime=startTime;
            sf.endingTime=endTime;
            return sf;

            
        }
        
    }
    public void ShowFilteredInfo(){
        try {
            if(venue_list.isSelectionEmpty()){
                JOptionPane.showMessageDialog(null,"Select Some venue before !!");
                return;
            }
            
            filteredSlots.removeAll(filteredSlots);
            System.out.println("filter size="+filteredSlots.size());
            int p=venue_list.getSelectedIndex();
            System.err.println(p);
            venueName.setText(vn.get(p).venue_name);
            ActiveVenueName=vn.get(p).venue_name;
            capacity.setText(vn.get(p).seats+"");
            curr_venue=vn.get(p).venue_name;
            curr_venue_id=vn.get(p).venue_id;
            int ac=vn.get(p).ac;
            int proj=vn.get(p).projecter;
            if(ac==1){
                AcField.setText("Yes");
            }
            else{
                AcField.setText("No");
            }
            if(proj==1){
                ProjField.setText("Yes");
            }
            else{
                ProjField.setText("No");
            }
            int id=vn.get(p).venue_id;
            slotFilter sf=new slotFilter();
            sf.venueName=ActiveVenueName;
            sf.includeDate=false;
            sf.startTime="0000";
            sf.endingTime="2400";
            sf.startingDate="0000-00-00";
            sf.endingDate="9999-99-99";
            sf=modifyFilter(sf);
            dos.writeInt(SLOT);
            ObjectOutputStream soos=new ObjectOutputStream(sock.getOutputStream());
            ObjectInputStream sois=new ObjectInputStream(sock.getInputStream());
          
            System.out.println("here "+sf.startingDate+" "+sf.endingDate);
            soos.writeObject(sf);
            SDLM.removeAllElements();
            int responseSize=dis.readInt();
            for(int i=0;i<responseSize;i++){
                slotResponse sr=(slotResponse)sois.readObject();
              //  System.out.println(sr.date);
                filteredSlots.add(sr);
                String stime=sr.startingTime.substring(0,2)+":"+sr.startingTime.substring(2);
                String etime=sr.endingTime.substring(0,2)+":"+sr.endingTime.substring(2);
                String tmp=fTools.paddString("", 3)+fTools.paddString((i+1)+"",14)+fTools.paddString(""+(i+1),14)+" "+""
                        + ""+fTools.paddString(ActiveVenueName,14)+" "+fTools.paddString(stime,14)+" "
                        + ""+fTools.paddString(etime,14)+" ";
                if(sr.dateIncluded)
                    tmp=tmp+" "+fTools.paddString(sr.date,14);
                SDLM.addElement(tmp);
            }
            for(int i=0;i<filteredSlots.size();i++){
                System.out.println(filteredSlots.get(i).date);
            }
            System.out.println("done filtering");
            SwitchPanel(InfoPanel);
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(UserHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void ShowInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowInfoActionPerformed

        ShowFilteredInfo();
    }//GEN-LAST:event_ShowInfoActionPerformed

    private void book_slotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_book_slotActionPerformed
        try {
            // TODO add your handling code here:
           
            int ind=slot_details.getSelectedIndex();
            int id=filteredSlots.get(ind).slotId;
            String date=year.getText()+"-"+month.getText()+"-"+day.getText();
            if(filteredSlots.get(ind).dateIncluded==true){
                date=filteredSlots.get(ind).date;
                System.out.println(date+" "+ind);
            }
            if(!fTools.validateDate(date)){
                JOptionPane.showMessageDialog(null,"Enter a valid Date !!");
                return ;
            }
            System.out.println(date);
            dos.writeInt(2);
            dos.writeUTF(date);
            dos.writeInt(curr_venue_id);
            dos.writeInt(id);
            dos.writeInt(clientId);
            int response=dis.readInt();
            if(response==0){
                System.out.println("SLot not availiable :(");
                JOptionPane.showMessageDialog(null,"Slot not available !!");
            }
            else if(response==2){
                System.out.println("You have Already have booked a slot on this date !!!");
                JOptionPane.showMessageDialog(null,"You already have this slot :)");
                
            }
            else {
                System.out.println("Yupp! you reserved the slot");
                JOptionPane.showMessageDialog(null,"You reserved the slot :)");
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(UserHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_book_slotActionPerformed
    public void updateBookedSlots(){
        try {
            // TODO add your handling code here:
            slotList.removeAllElements();
            dos.writeInt(ASKBOOKINGS);
            bookingData.removeAll(bookingData);
            int size=dis.readInt();
            System.out.println("size is :"+size);
            ObjectInputStream ois=new ObjectInputStream(sock.getInputStream());
            for(int i=0;i<size;i++){
                  slotInfo si=(slotInfo)ois.readObject();
                  bookingData.add(si);
                  System.out.println(si);
                  String temp=fTools.paddString("", 3)+fTools.paddString((i+1)+"", 14)+fTools.paddString(si.venueName,14)+" "
                          + " "+fTools.paddString(si.date,14)+" "+fTools.paddString(si.startTime,14)+" "+fTools.paddString(si.endTime,14);
                  slotList.addElement(temp);
            }
            MainPanel.removeAll();
            MainPanel.repaint();
            MainPanel.revalidate();
            MainPanel.add(slotsPanel);
            MainPanel.repaint();
            MainPanel.revalidate();
          
           
        } catch (IOException ex) {
            Logger.getLogger(UserHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
           Logger.getLogger(UserHome.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    private void bookedSlotsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookedSlotsActionPerformed
        updateBookedSlots();
    }//GEN-LAST:event_bookedSlotsActionPerformed

    private void addEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEventActionPerformed
        // TODO add your handling code here:
        if(bookedSlotList.isSelectionEmpty()){
            JOptionPane.showMessageDialog(null,"Select some slot to add event!!");
            return;
        }
        int ind=bookedSlotList.getSelectedIndex();
        if(bookingData.get(ind).event==1){
            System.out.println("Already event present for this venue!!");
            return ;
        }
        System.out.println(bookedSlotList.getSelectedValue());
        slotInfo si=bookingData.get(ind);
        newEvent ne=new newEvent(clientId,si.slotId,si.venueId,si.date,dos,si.bookingId,this);
        ne.setVisible(true);
        ne.setLocationRelativeTo(null);
    }//GEN-LAST:event_addEventActionPerformed

    private void myEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myEventActionPerformed
       try {
           // TODO add your handling code here:
           eventList.removeAllElements();
           eventData.removeAll(eventData);
           System.out.println(eventData.size());
           dos.writeInt(ASK_EVENT);
           int sz=dis.readInt();
           System.out.println("size is :"+sz);
           ObjectInputStream ois=new ObjectInputStream(sock.getInputStream());
           for(int i=0;i<sz;i++){
               eventInfo ei=(eventInfo)ois.readObject();
               eventData.add(ei);
               String tmp=fTools.paddString("", 3)+fTools.paddString(ei.event_name,20)+"  "+fTools.paddString(ei.venueName,20)+""
                       +" "+fTools.paddString(ei.startTime,20)+fTools.paddString(ei.endTime, 20);
               eventList.addElement(tmp);
               System.out.println(ei);
           }
           System.out.println("I am done :'");
           MainPanel.removeAll();
            MainPanel.repaint();
            MainPanel.revalidate();
            MainPanel.add(eventPanel);
            MainPanel.repaint();
            MainPanel.revalidate();
       } catch (IOException ex) {
           Logger.getLogger(UserHome.class.getName()).log(Level.SEVERE, null, ex);
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(UserHome.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_myEventActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(eventListing.isSelectionEmpty()){
            JOptionPane.showMessageDialog(null,"select an event before");
            return ;
        }
        int ind=eventListing.getSelectedIndex();
        editEvent ev=new editEvent(clientId,eventData.get(ind).eventId,dos,dis,sock,port);
        ev.setLocationRelativeTo(null);
        ev.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed
    public void updateInvitations(){
         try {
            // TODO add your handling code here:
            otherEvents.removeAllElements();
            String head=fTools.paddString("", 3)+fTools.paddString("EventName",20)+" "+fTools.paddString("HostName",20)+" "+fTools.paddString("Status", 20);
            otherEvents.addElement(head);
            dos.writeInt(INVITATIONS);
            inviteData.removeAll(inviteData);
            dos.writeInt(clientId);
            int sz=dis.readInt();
            ObjectInputStream ois=new ObjectInputStream(sock.getInputStream());
            for(int i=0;i<sz;i++){
                invitedEvent ive=(invitedEvent)ois.readObject();
                inviteData.add(ive);
                String st=null;
                if(ive.status==0){
                    st="Pending";
                }
                else{
                    st="Accepted";
                }
                String t=fTools.paddString("", 3)+fTools.paddString(ive.eventName,20)+" "+fTools.paddString(ive.hostName,20)+" "+fTools.paddString(st, 20);
                otherEvents.addElement(t);
            }
            MainPanel.removeAll();
            MainPanel.repaint();
            MainPanel.revalidate();
            MainPanel.add(invitedEvents);
            MainPanel.repaint();
            MainPanel.revalidate();
            
            
        } catch (IOException ex) {
            Logger.getLogger(UserHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserHome.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    private void InvitationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InvitationsActionPerformed
        updateInvitations();
    }//GEN-LAST:event_InvitationsActionPerformed

    private void downloadInviteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadInviteActionPerformed
        try {
            // TODO add your handling code here:
             
            if(otherEventsList.isSelectionEmpty()){
                JOptionPane.showMessageDialog(null,"Select some invite before !!");
                 return ;
             }
            
            int ind=otherEventsList.getSelectedIndex()-1;
            String link=inviteData.get(ind).invitationLink;
            if(link==null){
                System.out.println("file not available");
                return ;
            }
            dos.writeInt(10);
            Socket tmpSocket=new Socket("localhost",port);
            dos.writeUTF(inviteData.get(ind).invitationLink);
            String eventName=inviteData.get(ind).eventName;
            int eid=inviteData.get(ind).evenId;
            String directoryPath=System.getProperty("user.home")+"/EventClient";
            File f=new File(directoryPath);
            f.mkdirs();
            f=new File(directoryPath+"/"+eventName+"_"+eid+".pdf");
            if(f.exists())
                f.delete();
            OutputStream os=new FileOutputStream(f);
            InputStream ins=tmpSocket.getInputStream();
            int count=0;
            System.out.println("waiting for the doom ..");
            byte[] buffer=new byte[1024*1024];
            while((count=ins.read(buffer))>0){
                os.write(buffer,0,count);
            }
            ins.close();
            os.close();
            tmpSocket.close();
            
         
            
                    
        } catch (IOException ex) {
            Logger.getLogger(UserHome.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_downloadInviteActionPerformed
    public void sendOpinion(int mood){
        
        int ind=otherEventsList.getSelectedIndex()-1;
        System.out.println(ind);
        try {
            System.out.println(inviteData.get(ind).eventName);
            dos.writeInt(APPROVE);
            int hid=inviteData.get(ind).hostId;
            System.out.println(hid);
            dos.writeInt(hid);
            dos.writeInt(inviteData.get(ind).evenId);
            dos.writeInt(clientId);
            dos.writeInt(mood);
            
        } catch (IOException ex) {
            Logger.getLogger(UserHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void acceptRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptRequestActionPerformed
        // TODO add your handling code here:
        if(otherEventsList.isSelectionEmpty()){
           JOptionPane.showMessageDialog(null,"Select some invite before !!");
            return ;
        }
        System.out.println(inviteData.size());
        sendOpinion(1);
        updateInvitations();
//        
    }//GEN-LAST:event_acceptRequestActionPerformed

    private void deleteRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteRequestActionPerformed
        // TODO add your handling code here:
         if(otherEventsList.isSelectionEmpty()){
            System.out.println("Select some event to accept");
            return ;
        }
        sendOpinion(2);
        updateInvitations();
    }//GEN-LAST:event_deleteRequestActionPerformed
    public void showEventDetails(){
        if(eventListing.isSelectionEmpty())
            return ;
        int ind=eventListing.getSelectedIndex();
        eventDetails ed=new eventDetails(eventData.get(ind).eventId, dis, dos);
        ed.setLocationRelativeTo(null);
        ed.setVisible(true);
        ed.setTitle(eventData.get(ind).event_name);
    }
    private void showDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showDetailsActionPerformed
        // TODO add your handling code here:
        if(eventListing.isSelectionEmpty()){
            JOptionPane.showMessageDialog(null,"select an event before");
            return ;
        }
        showEventDetails();
    }//GEN-LAST:event_showDetailsActionPerformed

    private void monthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_monthActionPerformed

    private void fileterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileterButtonActionPerformed
        // TODO add your handling code here:
        if(filter.getSelectedIndex()==5){
            String startTime=st_hr.getSelectedItem().toString()+st_min.getSelectedItem().toString();
            String endTime=et_hr.getSelectedItem().toString()+et_min.getSelectedItem().toString();
            String StartDate=fYear.getText()+"-"+fMonth.getText()+"-"+fDay.getText();
            String endDate=tYear.getText()+"-"+tMonth.getText()+"-"+tDay.getText();
            if(!fTools.validateDate(StartDate)){
                JOptionPane.showMessageDialog(null,"Enter some valid Dates !!");
                return;
            }
            if(!fTools.validateDate(endDate)){
                JOptionPane.showMessageDialog(null,"Enter some valid Dates !!");
                return;
            }
            if(fTools.compareDates(StartDate,endDate)==1){
                JOptionPane.showMessageDialog(null,"Invalid time Window !!");
                return;
            }
            if(!fTools.isValidWindow(StartDate,endDate)){
                JOptionPane.showMessageDialog(null,"Date Window cannot be greater than 10 days!!");
                return ;
            }
        }
        ShowFilteredInfo();
    }//GEN-LAST:event_fileterButtonActionPerformed

    private void showEventsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showEventsActionPerformed
        try {
            // TODO add your handling code here:
            if(venue_list.isSelectionEmpty()){
                JOptionPane.showMessageDialog(null,"Select some venue before !!");
                return ;
            }
                
            int ind=venue_list.getSelectedIndex();
            dos.writeInt(ASK_ALLEVENT);
            int vid=vn.get(ind).venue_id;
            eventsPanel ep=new eventsPanel(dis, dos, vid);
            ep.setLocationRelativeTo(null);
            ep.setVisible(true);
            ep.setTitle(vn.get(ind).venue_name);
            
            
        } catch (IOException ex) {
            Logger.getLogger(UserHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_showEventsActionPerformed

    private void st_hrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_st_hrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_st_hrActionPerformed
    
    /**
     * @param args the command line arguments
     */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AcField;
    private javax.swing.JLabel IamKalam;
    private javax.swing.JPanel InfoPanel;
    private javax.swing.JButton Invitations;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JLabel ProjField;
    private javax.swing.JButton ShowInfo;
    private javax.swing.JLabel Welcome;
    private javax.swing.JPanel WelcomePanel;
    private javax.swing.JButton acceptRequest;
    private javax.swing.JButton addEvent;
    private javax.swing.JButton book_slot;
    private javax.swing.JList bookedSlotList;
    private javax.swing.JButton bookedSlots;
    private javax.swing.JLabel capacity;
    private javax.swing.JTextField day;
    private javax.swing.JButton deleteRequest;
    private javax.swing.JButton downloadInvite;
    private javax.swing.JComboBox et_hr;
    private javax.swing.JComboBox et_min;
    private javax.swing.JList eventListing;
    private javax.swing.JPanel eventPanel;
    private javax.swing.JTextField fDay;
    private javax.swing.JTextField fMonth;
    private javax.swing.JTextField fYear;
    private javax.swing.JButton fileterButton;
    private javax.swing.JComboBox filter;
    private javax.swing.JPanel invitedEvents;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextField month;
    private javax.swing.JButton myEvent;
    private javax.swing.JList otherEventsList;
    private javax.swing.JButton showDetails;
    private javax.swing.JButton showEvents;
    private javax.swing.JList slot_details;
    private javax.swing.JPanel slotsPanel;
    private javax.swing.JComboBox st_hr;
    private javax.swing.JComboBox st_min;
    private javax.swing.JTextField tDay;
    private javax.swing.JTextField tMonth;
    private javax.swing.JTextField tYear;
    private javax.swing.JLabel vNum;
    private javax.swing.JLabel venueName;
    private javax.swing.JList venue_list;
    private javax.swing.JTextField year;
    // End of variables declaration//GEN-END:variables
}
