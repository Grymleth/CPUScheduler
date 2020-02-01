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
import java.util.Collections;
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
    
    private JTextField rrQnum;
    
    private JButton fcfsBtn,
                    rrBtn,
                    NonPreBtn,
                    PreBtn,
                    prioBtn,
                    proceedBtn,
                    addBtn,
                    removeBtn;
    
    private CardLayout inputCard,
                       varCard,
                       tableCard;
    
    private JPanel mainPanel,
                    tablePanel,
                    generalTablePanel,
                    prioTablePanel,
                    inputPanel,
                    burstPanel,
                    varPanel,
                    arrivalPanel,
                    prioFldPanel,
                    buttonPanel,
                    quantumPanel,
                    chartPanel,
                    addPanel;
    
    private Border border1,
                    fcfsBorder,
                    PreBorder,
                    NonPreBorder,
                    rrBorder,
                    prioBorder,
                    burstFldBorder,
                    arrivalFldBorder,
                    prioFldBorder,
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
    
    public GUI(){
        super("CPU Scheduler");
        int[] burst = {3,4,3};
        RoundRobin schedule = new RoundRobin(burst, 4);
        ArrayList<Process> list = schedule.processes;
        
        String[] tableName = {"Process","BT","AT","CT","TAT","WT"};
        String[] prioTableName = {"Process","Priority","BT","AT","CT","TAT","WT"};
        
        Process.displayTable(list);
        
        this.num = 0;
        
        this.setLayout(new GridLayout(3,1));
        
        border1 = BorderFactory.createLineBorder(Color.white);
        fcfsBorder = BorderFactory.createTitledBorder(border1,"FCFS");
        NonPreBorder = BorderFactory.createTitledBorder(border1,"Non-Preemptive");
        PreBorder = BorderFactory.createTitledBorder(border1,"Preemptive");
        rrBorder = BorderFactory.createTitledBorder(border1,"Round Robin");
        prioBorder = BorderFactory.createTitledBorder(border1,"Priority");
        burstFldBorder = BorderFactory.createTitledBorder(border1,"Burst Time");
        arrivalFldBorder = BorderFactory.createTitledBorder(border1,"Arrival Time");
        prioFldBorder = BorderFactory.createTitledBorder(border1,"Priority");
        
        table = new JTable();
        prioTable = new JTable();
        
        tableModel = new DefaultTableModel();
        prioModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(tableName);
        prioModel.setColumnIdentifiers(prioTableName);
        
        table.setModel(tableModel);
        prioTable.setModel(prioModel);
        
        inputCard = new CardLayout();
        varCard = new CardLayout();
        tableCard = new CardLayout();
        
        rrQnum = new JTextField();
        
        fcfsBtn = new JButton("FCFS");
        rrBtn = new JButton("Round Robin");
        NonPreBtn =  new JButton("Non-Preemptive");
        PreBtn = new JButton("Preemptive");
        prioBtn = new JButton("Priority");
        proceedBtn = new JButton("PROCEED");
        addBtn = new JButton("ADD");
        removeBtn = new JButton("REMOVE");
        
        mainPanel = new JPanel();
        tablePanel = new JPanel(tableCard);
        prioTablePanel =  new JPanel();
        generalTablePanel = new JPanel();
        buttonPanel = new JPanel();
        inputPanel = new JPanel(new GridLayout(1,3));
        burstPanel = new JPanel(new GridBagLayout());
        varPanel = new JPanel(varCard);
        arrivalPanel = new JPanel(new GridBagLayout());
        prioFldPanel = new JPanel(new GridBagLayout());
        quantumPanel = new JPanel();
        chartPanel = new JPanel(new GridBagLayout());
        addPanel = new JPanel();
        
        genSp = new JScrollPane(table);
        prioOutSp = new JScrollPane(prioTable);
        burstFldSp = new JScrollPane(burstPanel);
        arrivalFldSp = new JScrollPane(arrivalPanel);
        prioFldSp = new JScrollPane(prioFldPanel);
        
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
                burstFldSp.setBorder(burstFldBorder);
            inputPanel.add(varPanel);
                varPanel.add(arrivalFldSp, "ARRIVAL");
                    arrivalFldSp.setBorder(arrivalFldBorder);
                varPanel.add(prioFldSp, "PRIORITY");
                    prioFldSp.setBorder(prioFldBorder);
                varPanel.add(quantumPanel, "QUANTUM");
                    quantumPanel.add(rrQLbl);
                    quantumPanel.add(rrQnum);
                inputPanel.add(addPanel);
                    addPanel.add(addBtn);   
                    addPanel.add(removeBtn);
                
        add(mainPanel,BorderLayout.SOUTH);
            mainPanel.add(tablePanel);
                tablePanel.setBorder(fcfsBorder);
                tablePanel.add(generalTablePanel,"GENERAL_TABLE");
                    generalTablePanel.add(genSp);
                tablePanel.add(prioTablePanel, "PRIO_TABLE");
                    prioTablePanel.add(prioOutSp);
            mainPanel.add(chartPanel);
                
        add(proceedBtn);
                
        fcfsBtn.addActionListener(this);
        NonPreBtn.addActionListener(this);
        rrBtn.addActionListener(this);
        NonPreBtn.addActionListener(this);
        PreBtn.addActionListener(this);
        prioBtn.addActionListener(this);
        proceedBtn.addActionListener(this);
        addBtn.addActionListener(this);
        removeBtn.addActionListener(this);
        
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
        panel.revalidate();
        panel.repaint();
    }
    
    private void removeField(JTextField field, JPanel panel, GridBagConstraints gbc){
        panel.remove(field);
        gbc.gridx=0;
        gbc.gridy--;
        panel.revalidate();
        panel.repaint();
        pack();
        
    }
        
    private void addChart(JPanel panel, ArrayList<Process> process){
        int size = process.size();
        int compl = 0;

        Iterator it = process.iterator();
        Process obj;
        JLabel[] chart = new JLabel[size];        
        Border b1 = BorderFactory.createLineBorder(Color.BLACK);
        Border border;
        
        panel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
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
        
        addChart(chartPanel, schedule.ganttBar);
        
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
        Collections.sort(schedule.processes, new SortByPriority());
        addChart(chartPanel, schedule.processes);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==fcfsBtn){
            varCard.show(varPanel, "ARRIVAL");
            tableCard.show(tablePanel,"GENERAL");
            tablePanel.setBorder(fcfsBorder);
        }
        else if(e.getSource()==NonPreBtn){
            varCard.show(varPanel, "ARRIVAL");
            tableCard.show(tablePanel,"GENERAL");
            tablePanel.setBorder(NonPreBorder);
        }
        else if(e.getSource()==PreBtn){
            varCard.show(varPanel, "ARRIVAL");
            tableCard.show(tablePanel,"GENERAL");
            tablePanel.setBorder(PreBorder);
        }
        else if(e.getSource()==rrBtn){
            algorithm = ROUND_ROBIN;
            varCard.show(varPanel, "QUANTUM");
            tableCard.show(tablePanel,"GENERAL_TABLE");
            tablePanel.setBorder(rrBorder);
        }
        else if(e.getSource()==prioBtn){
            algorithm = PRIO;
            varCard.show(varPanel, "PRIORITY");
            tableCard.show(tablePanel,"PRIO_TABLE");
            tablePanel.setBorder(prioBorder);
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
            tableModel.insertRow(num, new String[6]);
            prioModel.insertRow(num, new String[7]);
            num++;
        }
        else if(e.getSource()==removeBtn){
            removeField(burstFld.get(num-1), burstPanel, burstConstraints);
            removeField(arrivalFld.get(num-1), arrivalPanel, arrivalConstraints);
            removeField(prioFld.get(num-1), prioFldPanel, prioConstraints);
            
            num--;
        }
    }
    
    public static void main(String[] args) {
        GUI x = new GUI();
                
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
