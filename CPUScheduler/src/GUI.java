import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aevan
 */
public class GUI extends JFrame implements ActionListener{
    private ArrayList<JTextField> burstFld,
                                  arrivalFld,
                                  prioFld;
    private JLabel burstLbl,
                   arrivalLbl, 
                   prioLbl;
    
    private JTextField rrQnum;
    
    private JButton fcfsBtn,
                    rrBtn,
                    NonPreBtn,
                    PreBtn,
                    prioBtn,
                    proceedBtn,
                    addBtn;
    
    private CardLayout outputCard,
                        inputCard,
                        varCard;
                       
    
    private JPanel mainPanel,
                    prioPanel,
                    inputPanel,
                    burstPanel,
                    varPanel,
                    arrivalPanel,
                    prioFldPanel,
                    buttonPanel,
                    quantumPanel,
                    chartPanel,
                    tablePanel,
                    addPanel;
    
    private Border border1,
                    fcfsBorder,
                    PreBorder,
                    NonPreBorder,
                    rrBorder,
                    prioBorder,
                    fcfsChBorder,
                    rrChBorder,
                    prioChBorder,
                    PreChBorder,
                    NonPreChBorder;
                    
    private JLabel label,
                    label2,
                    rrQLbl,
                    blankLbl;
    
    private JTable table,
                    prioTable;
    
    private DefaultTableModel tableModel,
                              prioModel;
                                
    
    private JScrollPane genSp,
                        prioOutSp,
                        burstFldSp,
                        arrivalFldSp,
                        prioFldSp;
    
    GridBagConstraints burstConstraints = new GridBagConstraints();
    GridBagConstraints arrivalConstraints = new GridBagConstraints();
    GridBagConstraints prioConstraints = new GridBagConstraints();
    
    private int num;
    private int algorithm;
    
    private static final int FIRST_COME = 0;
    private static final int SJT = 1;
    private static final int SRJT = 2;
    private static final int ROUND_ROBIN = 3;
    private static final int PRIO = 4;
    
    public GUI(int num){
        super("Processes");
        int[] burst = {3,4,3};
        RoundRobin schedule = new RoundRobin(burst, 4);
        ArrayList<Process> list = schedule.processes;
        
        String[] tableName = {"Process","BT","AT","CT","TAT","WT"};
        String[] prioTableName = {"Process","Priority","BT","AT","CT","TAT","WT"};
        
        Process.displayTable(list);
        
        this.num = num;
        
        this.setLayout(new GridLayout(3,1));
        
        border1 = BorderFactory.createLineBorder(Color.white);
        fcfsBorder = BorderFactory.createTitledBorder(border1,"FCFS");
        NonPreBorder = BorderFactory.createTitledBorder(border1,"Non-Preemptive");
        PreBorder = BorderFactory.createTitledBorder(border1,"Preemptive");
        rrBorder = BorderFactory.createTitledBorder(border1,"Round Robin");
        prioBorder = BorderFactory.createTitledBorder(border1,"Priority");
        
        table = new JTable();
        prioTable = new JTable();
        
        tableModel = new DefaultTableModel();
        prioModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(tableName);
        prioModel.setColumnIdentifiers(prioTableName);
        
        table.setModel(tableModel);
        prioTable.setModel(prioModel);
        
        outputCard = new CardLayout();
        inputCard = new CardLayout();
        varCard = new CardLayout();
        
        rrQnum = new JTextField();
        
        fcfsBtn = new JButton("FCFS");
        rrBtn = new JButton("Round Robin");
        NonPreBtn =  new JButton("Non-Preemptive");
        PreBtn = new JButton("Preemptive");
        prioBtn = new JButton("Priority");
        proceedBtn = new JButton("PROCEED");
        addBtn = new JButton("ADD PROCESS");
        
        mainPanel = new JPanel(outputCard);
        prioPanel =  new JPanel();
        buttonPanel = new JPanel();
        inputPanel = new JPanel(new GridLayout(1,3));
        burstPanel = new JPanel(new GridBagLayout());
        varPanel = new JPanel(varCard);
        arrivalPanel = new JPanel(new GridBagLayout());
        prioFldPanel = new JPanel(new GridBagLayout());
        quantumPanel = new JPanel();
        chartPanel = new JPanel();
        tablePanel = new JPanel();
        addPanel = new JPanel();
        
        genSp = new JScrollPane(table);
        prioOutSp = new JScrollPane(prioTable);
        burstFldSp = new JScrollPane(burstPanel);
        arrivalFldSp = new JScrollPane(arrivalPanel);
        prioFldSp = new JScrollPane(prioFldPanel);
        
        burstLbl = new JLabel("Burst Time");
        arrivalLbl = new JLabel("Arrival Time");
        prioLbl = new JLabel("Priority");
        label = new JLabel("test");
        label2 = new JLabel("test2");
        rrQLbl = new JLabel("Quantum Time: ");
        blankLbl = new JLabel("");
        
        burstFld = new ArrayList<>();
        arrivalFld = new ArrayList<>();
        prioFld = new ArrayList<>();
        
        mainPanel.setPreferredSize(new Dimension(500,400));
        inputPanel.setPreferredSize(new Dimension(500,150));
        rrQnum.setPreferredSize(new Dimension(150,25));
        rrQLbl.setPreferredSize(new Dimension(150,25));
        prioOutSp.setPreferredSize(new Dimension(450,250));
        genSp.setPreferredSize(new Dimension(450,250));
        
        buttonPanel.add(fcfsBtn);
        buttonPanel.add(NonPreBtn);
        buttonPanel.add(PreBtn);
        buttonPanel.add(rrBtn);
        buttonPanel.add(prioBtn);
        
        add(buttonPanel);
        
        add(inputPanel);
            inputPanel.add(burstFldSp);
                burstConstraints.gridx=0;
                burstConstraints.gridy=0;
                burstPanel.add(burstLbl,burstConstraints);
            inputPanel.add(varPanel);
                varPanel.add(arrivalFldSp, "ARRIVAL");
                    arrivalConstraints.gridx=0;
                    arrivalConstraints.gridy=0;
                    arrivalPanel.add(arrivalLbl,arrivalConstraints);
                varPanel.add(prioFldSp, "PRIORITY");
                    prioConstraints.gridx=0;
                    prioConstraints.gridy=0;
                    prioFldPanel.add(prioLbl,prioConstraints);
                varPanel.add(quantumPanel, "BLANK");
                    quantumPanel.add(rrQLbl);
                    quantumPanel.add(rrQnum);
                inputPanel.add(addPanel);
                    addPanel.add(addBtn);
                    
                
        add(mainPanel,BorderLayout.SOUTH);
            mainPanel.add(tablePanel,"Table");
                tablePanel.setBorder(fcfsBorder);
                tablePanel.add(genSp);
            mainPanel.add(prioPanel,"Prio");
                prioPanel.setBorder(prioBorder);
                prioPanel.add(prioOutSp);
                
        add(proceedBtn);
                
        fcfsBtn.addActionListener(this);
        NonPreBtn.addActionListener(this);
        rrBtn.addActionListener(this);
        NonPreBtn.addActionListener(this);
        PreBtn.addActionListener(this);
        prioBtn.addActionListener(this);
        proceedBtn.addActionListener(this);
        addBtn.addActionListener(this);
        
        setLayout(new FlowLayout());
        setSize(600,700);
        setPreferredSize(new Dimension(600,700));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }

    private void addField(JTextField field, JPanel panel, GridBagConstraints gbc){
        gbc.gridx=0;
        gbc.gridy++;
        
        System.out.println("gridx: " + gbc.gridx);
        System.out.println("gridy: " + gbc.gridy);
        panel.add(field,gbc);
        pack();
    }
    
    private void layoutField(JLabel label, JTextField field, JPanel panel){
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        int x=0,y=0;
        gbc.gridx=0;
        gbc.gridy=0;
        panel.add(label);

        gbc.gridy=1;
        panel.add(field,gbc);
        panel.revalidate();
        inputPanel.revalidate();
        this.validate();
    }
        
    private void addChart(JPanel panel, ArrayList<Process> process, GridBagConstraints gbc){
        int size = process.size();
        int compl = 0;

        Iterator it = process.iterator();
        Process obj;
        JLabel[] chart = new JLabel[size];        
        Border b1 = BorderFactory.createLineBorder(Color.BLACK);
        Border border;
        
        gbc.fill=0;
        gbc.gridheight=1;
        gbc.gridwidth=1;
        
        for(int i=0;it.hasNext();i++){
            obj = (Process)it.next();
            
            chart[i] = new JLabel("P"+(obj.getID()+1));
            compl += obj.getBurst()+obj.getArrival();
            
            gbc.gridx=i;
            gbc.gridy=6;
            panel.add(chart[i],gbc);
            border = BorderFactory.createTitledBorder(b1,Integer.toString(compl),TitledBorder.RIGHT,TitledBorder.BELOW_BOTTOM);
            chart[i].setBorder(border);
            chart[i].setPreferredSize(new Dimension(70,40));
        }
    }
    
    private void scheduling(){
        if(algorithm == ROUND_ROBIN){
            roundRobin();
        }
        else if(algorithm == PRIO){
            priority();
        }
    }
    
    private void roundRobin(){
        int[] bursts = getBurstArray();
        int quantum = Integer.parseInt(rrQnum.getText());
        RoundRobin schedule = new RoundRobin(bursts, quantum);
        
        String[] list;
        for(int i=0;i<num;i++){
            list = schedule.processes.get(i).toStringArray(false);
            tableModel.setValueAt(list[0], i, 0);
            for(int j=1;j<6;j++){
                tableModel.setValueAt(list[j], i, j);
            }
        }
                
    }
    
    private void priority(){
        int[] bursts = getBurstArray();
        int[] priority = getPriorityArray();
        
        Priority schedule = new Priority(bursts, priority);
        String[] list;
        for(int i=0;i<num;i++){
            list = schedule.processes.get(i).toStringArray(true);
            prioTable.setValueAt(list[0], i, 0);
            for(int j=1;j<7;j++){
                prioTable.setValueAt(list[j], i, j);
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==fcfsBtn){
            varCard.show(varPanel, "ARRIVAL");
            outputCard.show(mainPanel,"Table");
        }
        else if(e.getSource()==NonPreBtn){
            varCard.show(varPanel, "ARRIVAL");
            outputCard.show(mainPanel,"Table");
        }
        else if(e.getSource()==PreBtn){
            varCard.show(varPanel, "ARRIVAL");
            outputCard.show(mainPanel,"Table");
        }
        else if(e.getSource()==rrBtn){
            algorithm = ROUND_ROBIN;
            varCard.show(varPanel, "BLANK");
            outputCard.show(mainPanel,"Table");
        }
        else if(e.getSource()==prioBtn){
            algorithm = PRIO;
            varCard.show(varPanel, "PRIORITY");
            outputCard.show(mainPanel,"Prio");
        }
        else if(e.getSource()==proceedBtn){
            scheduling();
        }
        else if(e.getSource()==addBtn){
            burstFld.add(new JTextField("",5));
            arrivalFld.add(new JTextField("",5));
            prioFld.add(new JTextField("",5));
            
            addField(burstFld.get(num), burstPanel, burstConstraints);
            addField(arrivalFld.get(num), arrivalPanel, arrivalConstraints);
            addField(prioFld.get(num), prioFldPanel, prioConstraints);
            String[] blank1 = new String[6];
            String[] blank2 = new String[6];
            tableModel.insertRow(num, blank1);
            prioModel.insertRow(num, blank2);
            num++;
        }
    }
    
    public static void main(String[] args) {
        GUI x = new GUI(0);
                
    }
    
    private int[] getBurstArray(){
        int[] burst = new int[num];
        
        for(int i=0;i<num;i++){
            burst[i] = Integer.parseInt(burstFld.get(i).getText());
        }
        
        return burst;
    }
    
    private int[] getArrivalArray(){
        int[] arrivalTime = new int[num];
        
        for(int i=0;i<num;i++){
            arrivalTime[i] = Integer.parseInt(arrivalFld.get(i).getText());
        }
        
        return arrivalTime;
    }
    
    private int[] getPriorityArray(){
        int[] priority = new int[num];
        
        for(int i=0;i<num;i++){
            priority[i] = Integer.parseInt(prioFld.get(i).getText());
        }
        
        return priority;
    }
}
