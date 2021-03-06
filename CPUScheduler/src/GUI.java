import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class GUI extends JFrame implements ActionListener{
    private final ArrayList<JTextField> burstFld,
                                  arrivalFld,
                                  prioFld;
    
    private final JTextField rrQnum;
    
    private final JButton fcfsBtn,
                    rrBtn,
                    NonPreBtn,
                    PreBtn,
                    prioBtn,
                    proceedBtn,
                    addBtn,
                    removeBtn,
                    creditsBtn;
    
    private final CardLayout inputCard,
                       varCard,
                       tableCard;
    
    private final JPanel mainPanel,
                    tablePanel,
                    generalTablePanel,
                    prioTablePanel,
                    inputPanel,
                    burstPanel,
                    varPanel,
                    arrivalPanel,
                    prioFldPanel,
                    algoButtonPanel,
                    quantumPanel,
                    chartPanel,
                    opBtnPanel,
                    averagePanel;
    
    private final Border border1,
                    fcfsBorder,
                    PreBorder,
                    NonPreBorder,
                    rrBorder,
                    prioBorder,
                    burstFldBorder,
                    arrivalFldBorder,
                    prioFldBorder,
                    chartBorder,
                    algoBtnBorder,
                    opBtnBorder,
                    averageBorder;
                    
    private final JLabel rrQLbl,
                         atatLbl,
                         atatVal,
                         awtLbl,
                         awtVal;
    
    private final JTable table,
                    prioTable;
    
    private final NoEditTableModel tableModel,
                              prioModel;
                                
    private final JScrollPane genSp,
                        prioOutSp,
                        burstFldSp,
                        arrivalFldSp,
                        prioFldSp,
                        chartSp;
    
    GridBagConstraints burstConstraints, 
                       arrivalConstraints, 
                       prioConstraints;
    
    private int num;
    private int algorithm;
    
    private static final int FIRST_COME = 0;
    private static final int SJF = 1;
    private static final int SRTF = 2;
    private static final int ROUND_ROBIN = 3;
    private static final int PRIO = 4;
    
    public GUI(){
        super("CPU Scheduler");
        
        String[] tableName = {"Process","BT","AT","CT","TAT","WT"};
        String[] prioTableName = {"Process","Priority","BT","AT","CT","TAT","WT"};
        
        this.num = 0;
        
        this.setLayout(new GridLayout(4,1));
        burstConstraints = new GridBagConstraints();
        arrivalConstraints = new GridBagConstraints();
        prioConstraints = new GridBagConstraints();
        
        border1 = BorderFactory.createLineBorder(Color.white);
        fcfsBorder = BorderFactory.createTitledBorder(border1,"First Come First Serve");
        NonPreBorder = BorderFactory.createTitledBorder(border1,"Shortest Job First");
        PreBorder = BorderFactory.createTitledBorder(border1,"Shortest Remaining Time First");
        rrBorder = BorderFactory.createTitledBorder(border1,"Round Robin");
        prioBorder = BorderFactory.createTitledBorder(border1,"Priority");
        burstFldBorder = BorderFactory.createTitledBorder(border1,"Burst Time");
        arrivalFldBorder = BorderFactory.createTitledBorder(border1,"Arrival Time");
        prioFldBorder = BorderFactory.createTitledBorder(border1,"Priority");
        chartBorder = BorderFactory.createTitledBorder(border1,"Gantt Chart");
        algoBtnBorder = BorderFactory.createTitledBorder(border1,"Scheduling Algorithms");
        opBtnBorder = BorderFactory.createTitledBorder(border1,"Options");
        averageBorder = BorderFactory.createTitledBorder(border1,"Performance");
        
        table = new JTable();
        prioTable = new JTable();
        
        tableModel = new NoEditTableModel();
        prioModel = new NoEditTableModel();
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
        NonPreBtn =  new JButton("SJF");
        PreBtn = new JButton("SRTF");
        prioBtn = new JButton("Priority");
        proceedBtn = new JButton("PROCEED");
        addBtn = new JButton("ADD");
        removeBtn = new JButton("REMOVE");
        creditsBtn = new JButton("CREDITS");
        
        mainPanel = new JPanel();
        tablePanel = new JPanel(tableCard);
        prioTablePanel =  new JPanel();
        generalTablePanel = new JPanel();
        algoButtonPanel = new JPanel();
        inputPanel = new JPanel(new GridLayout(1,3));
        burstPanel = new JPanel(new GridBagLayout());
        varPanel = new JPanel(varCard);
        arrivalPanel = new JPanel(new GridBagLayout());
        prioFldPanel = new JPanel(new GridBagLayout());
        quantumPanel = new JPanel();
        chartPanel = new JPanel(new GridBagLayout());
        opBtnPanel = new JPanel(new FlowLayout());
        averagePanel = new JPanel(new GridBagLayout());
        
        genSp = new JScrollPane(table);
        prioOutSp = new JScrollPane(prioTable);
        burstFldSp = new JScrollPane(burstPanel);
        arrivalFldSp = new JScrollPane(arrivalPanel);
        prioFldSp = new JScrollPane(prioFldPanel);
        chartSp = new JScrollPane(chartPanel);

        rrQLbl = new JLabel("Quantum Time: ");
        atatLbl = new JLabel("Ave. Turnaround Time");
        awtLbl = new JLabel("Ave. Waiting Time");
        atatVal = new JLabel();
        awtVal = new JLabel();
        
        burstFld = new ArrayList<>();
        arrivalFld = new ArrayList<>();
        prioFld = new ArrayList<>();
        
        mainPanel.setPreferredSize(new Dimension(500,400));
        inputPanel.setPreferredSize(new Dimension(500,150));
        rrQnum.setPreferredSize(new Dimension(150,25));
        rrQLbl.setPreferredSize(new Dimension(150,25));
        prioOutSp.setPreferredSize(new Dimension(450,250));
        genSp.setPreferredSize(new Dimension(450,250));
        chartSp.setPreferredSize(new Dimension(450, 100));
        averagePanel.setPreferredSize(new Dimension(300, 100));
        
        algoButtonPanel.add(fcfsBtn);
        algoButtonPanel.add(NonPreBtn);
        algoButtonPanel.add(PreBtn);
        algoButtonPanel.add(rrBtn);
        algoButtonPanel.add(prioBtn);
        
        add(algoButtonPanel);
        algoButtonPanel.setBorder(algoBtnBorder);
        
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
                inputPanel.add(opBtnPanel);
                    opBtnPanel.setBorder(opBtnBorder);
                    opBtnPanel.add(addBtn);   
                    opBtnPanel.add(removeBtn);
                    opBtnPanel.add(proceedBtn);
                    opBtnPanel.add(creditsBtn);
        add(averagePanel);
            averagePanel.setBorder(averageBorder);
            layoutAveragePanel();
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridy = 0;
            gbc.gridx = 0;
            gbc.insets = new Insets(10,10,10,10);
            averagePanel.add(atatLbl,gbc);
            gbc.gridy = 1;
            gbc.gridx = 0;
            averagePanel.add(atatVal,gbc);
            gbc.gridy = 0;
            gbc.gridx = 5;
            averagePanel.add(awtLbl,gbc);
            gbc.gridy = 1;
            gbc.gridx = 5;
            averagePanel.add(awtVal,gbc);
            
                
        add(mainPanel,BorderLayout.SOUTH);
            mainPanel.add(tablePanel);
                tablePanel.setBorder(fcfsBorder);
                tablePanel.add(generalTablePanel,"GENERAL_TABLE");
                    generalTablePanel.add(genSp);
                tablePanel.add(prioTablePanel, "PRIO_TABLE");
                    prioTablePanel.add(prioOutSp);
            mainPanel.add(chartSp);
            chartSp.setBorder(chartBorder);
                
        fcfsBtn.addActionListener(this);
        NonPreBtn.addActionListener(this);
        rrBtn.addActionListener(this);
        NonPreBtn.addActionListener(this);
        PreBtn.addActionListener(this);
        prioBtn.addActionListener(this);
        proceedBtn.addActionListener(this);
        addBtn.addActionListener(this);
        removeBtn.addActionListener(this);
        creditsBtn.addActionListener(this);
        
        setLayout(new FlowLayout());
        setSize(600,800);
        setPreferredSize(new Dimension(600,800));
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
        field.setText("");
        panel.remove(field);
        gbc.gridx=0;
        gbc.gridy--;
        panel.revalidate();
        panel.repaint();
        pack();
        
    }
        
    private void addChart(JPanel panel, JScrollPane sp, ArrayList<Process> process){
        int size = process.size();
        int compl;
        
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
        Iterator it = process.iterator();
        Process obj;
        JLabel[] chart = new JLabel[size];        
        Border b1 = BorderFactory.createLineBorder(Color.BLACK);
        Border border;
        
        panel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();

        int x=0;
        int y=0;
        gbc.fill=0;
        for(int i=0;it.hasNext();i++){
            obj = (Process)it.next();

            chart[i] = new JLabel("P"+(obj.getID()+1));
            compl = process.get(i).getCompletion();
            gbc.gridx=x++;
            gbc.gridy=y;
            panel.add(chart[i],gbc);
            border = BorderFactory.createTitledBorder(b1,Integer.toString(compl),TitledBorder.RIGHT,TitledBorder.BELOW_BOTTOM);
            chart[i].setBorder(border);
            chart[i].setPreferredSize(new Dimension(30,40));
        }
        
        pack();
    }
    
    private void scheduling() throws ZeroQuantumTimeException{
        switch (algorithm) {
            case FIRST_COME:
                fcfs();
                break;
            case SJF:
                sjf();
                break;
            case SRTF:
                srtf();
                break;
            case ROUND_ROBIN:
                roundRobin();
                break;
            case PRIO:
                priority();
                break;
            default:
                break;
        }
    }
    
    private void fcfs(){
        int[] bursts = getBurstArray();
        int[] arrival = getArrivalArray();
        FCFS schedule = new FCFS(bursts, arrival);
        
        String[] list;
        for(int i=0;i<num;i++){
            list = schedule.processes.get(i).toStringArray(false);
            tableModel.setValueAt(list[0], i, 0);
            for(int j=1;j<6;j++){
                tableModel.setValueAt(list[j], i, j);
            }
        }    
        
        atatVal.setText(String.format("%.2fms", schedule.getAvgtat()));
        awtVal.setText(String.format("%.2fms", schedule.getAvgwt()));
        
        Collections.sort(schedule.processes, new SortByArrival());
        addChart(chartPanel, chartSp, schedule.processes);
    }
    
    private void sjf(){
        int[] bursts = getBurstArray();
        int[] arrival = getArrivalArray();
        SJF schedule = new SJF(bursts, arrival);
        
        String[] list;
        for(int i=0;i<num;i++){
            list = schedule.processes.get(i).toStringArray(false);
            tableModel.setValueAt(list[0], i, 0);
            for(int j=1;j<6;j++){
                tableModel.setValueAt(list[j], i, j);
            }
        }    
        
        atatVal.setText(String.format("%.2fms", schedule.getAvgtat()));
        awtVal.setText(String.format("%.2fms", schedule.getAvgwt()));
        
        Collections.sort(schedule.processes, new SortByCompletion());
        addChart(chartPanel, chartSp, schedule.processes);
    }
    
    private void srtf(){
        int[] bursts = getBurstArray();
        int[] arrival = getArrivalArray();
        SRT schedule = new SRT(bursts, arrival);
        
        String[] list;
        for(int i=0;i<num;i++){
            list = schedule.processes.get(i).toStringArray(false);
            tableModel.setValueAt(list[0], i, 0);
            for(int j=1;j<6;j++){
                tableModel.setValueAt(list[j], i, j);
            }
        }
        
        atatVal.setText(String.format("%.2fms", schedule.getAvgtat()));
        awtVal.setText(String.format("%.2fms", schedule.getAvgwt()));
        
        addChart(chartPanel, chartSp, schedule.ganttBar);
    }
    
    private void roundRobin() throws ZeroQuantumTimeException{
        int[] bursts = getBurstArray();
        int quantum = Integer.parseInt(rrQnum.getText());
        
        if(quantum < 1){
            throw new ZeroQuantumTimeException();
        }
        
        RoundRobin schedule = new RoundRobin(bursts, quantum);
        
        String[] list;
        for(int i=0;i<num;i++){
            list = schedule.processes.get(i).toStringArray(false);
            tableModel.setValueAt(list[0], i, 0);
            for(int j=1;j<6;j++){
                tableModel.setValueAt(list[j], i, j);
            }
        }    

        atatVal.setText(String.format("%.2fms", schedule.getAvgtat()));
        awtVal.setText(String.format("%.2fms", schedule.getAvgwt()));
        
        addChart(chartPanel, chartSp, schedule.ganttBar);
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
        
        atatVal.setText(String.format("%.2fms", schedule.getAvgtat()));
        awtVal.setText(String.format("%.2fms", schedule.getAvgwt()));
        
        Collections.sort(schedule.processes, new SortByPriority());
        addChart(chartPanel, chartSp, schedule.processes);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==fcfsBtn){
            algorithm = FIRST_COME;
            varCard.show(varPanel, "ARRIVAL");
            tableCard.show(tablePanel,"GENERAL");
            tablePanel.setBorder(fcfsBorder);
        }
        else if(e.getSource()==NonPreBtn){
            algorithm = SJF;
            varCard.show(varPanel, "ARRIVAL");
            tableCard.show(tablePanel,"GENERAL");
            tablePanel.setBorder(NonPreBorder);
        }
        else if(e.getSource()==PreBtn){
            algorithm = SRTF;
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
            try{
                if(num == 0)
                    throw new NullPointerException();
                scheduling();
            }
            catch(NumberFormatException ex){
                errorMessage("Invalid Input\nPlease enter positive integers only","Empty Fields");
            }
            catch(NullPointerException ex){
                errorMessage("There are no fields!","No Fields");
            }
            catch(ZeroQuantumTimeException ex){
                errorMessage("Quantum Time cannot be less than 1", "Zero Quantum Time");
            }
                
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
            try{
                removeField(burstFld.get(num-1), burstPanel, burstConstraints);
                removeField(arrivalFld.get(num-1), arrivalPanel, arrivalConstraints);
                removeField(prioFld.get(num-1), prioFldPanel, prioConstraints);
                tableModel.removeRow(num-1);
                prioModel.removeRow(num-1);
            }
            catch(ArrayIndexOutOfBoundsException ex){
                errorMessage("Panel is already empty!", "Empty Panel");
                return;
            }
            num--;
        }
        else if(e.getSource()==creditsBtn){
            Credits credits = new Credits();
        }
    }
    
    public static void main(String[] args) {
        // Look and feel
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                System.out.println(info.getName());
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
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
    
    public void errorMessage(String message, String error){
        JOptionPane.showMessageDialog(this, message, error, JOptionPane.ERROR_MESSAGE);
    }
    
    private void layoutAveragePanel(){

    }
   
}
