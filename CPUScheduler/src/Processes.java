
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Processes extends JFrame implements ActionListener{
    private JTextField[] burstFld;
    private JTextField[] arrivalFld;
    private JLabel burstLbl, arrivalLbl;
    private JButton enterBtn;
    private int procNum;
    
    public Processes(int procNum){
        super("Processes");
        
        this.procNum = procNum;
        
        setLayout(new GridLayout(0,2));
        
        initComponents();
 
        setSize(300,300);
        
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
    
    private void initComponents(){
        burstFld = new JTextField[procNum];
        arrivalFld = new JTextField[procNum];
        burstLbl = new JLabel("Burst Speed");
        arrivalLbl = new JLabel("Arrival Time");
        enterBtn = new JButton("ENTER");
        
        burstLbl.setHorizontalAlignment(JLabel.CENTER);
        arrivalLbl.setHorizontalAlignment(JLabel.CENTER);        
        
        add(burstLbl);
        add(arrivalLbl);               
        for(int i=0;i<procNum;i++){
            burstFld[i] = new JTextField();
            arrivalFld[i] = new JTextField();
            
            add(burstFld[i]);
            add(arrivalFld[i]);
        }
        add(enterBtn);         
        
        enterBtn.addActionListener(this);
    }
    
    private int[] getBurst(){
        int[] burst = new int[procNum];
        
        for(int i=0;i<procNum;i++){
            burst[i] = Integer.parseInt(burstFld[i].getText());
        }
        
        return burst;
    }
    
    private int[] getArrivalTime(){
        int[] arrivalTime = new int[procNum];
        
        for(int i=0;i<procNum;i++){
            arrivalTime[i] = Integer.parseInt(arrivalFld[i].getText());
        }
        
        return arrivalTime;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == enterBtn){
            this.dispose();
        }
    }
    
    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog(null, 
                "How many processes?", 
                "Input", 
                JOptionPane.QUESTION_MESSAGE);
        
        int processes = Integer.parseInt(input);
        
        Processes x = new Processes(processes);
        
    }
}
