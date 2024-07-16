import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

class MyTempConverter
{
    static JFrame myFrame;
    static JTextField myInput;
    static JTextField myOutput;
    static JComboBox<String>myComboFrom;
    static JComboBox<String>myComboTo;
    JButton myBtnConvert;
    JLabel labelfrom;
    JLabel labelto;

    public void showFrame()
    {
        myFrame = new JFrame("Temperature Converter");
        myFrame.setSize(500,400);
        myFrame.setLocationRelativeTo(null);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.getContentPane().setBackground(Color.decode("#c8f2e0"));
 
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS)); 
        panel.setPreferredSize(new Dimension(370,300));
        panel.setMaximumSize(new Dimension(370, 300));
        
        labelfrom = new JLabel("FROM : ");
        labelfrom.setAlignmentX(Component.CENTER_ALIGNMENT);
      
        labelto = new JLabel("TO : ");
        labelto.setAlignmentX(Component.CENTER_ALIGNMENT);

        myInput = new JTextField(10);
        myInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        myInput.setPreferredSize(new Dimension(300,100));
        myInput.setText("Enter Temperature");
        myInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (myInput.getText().equals("Enter Temperature")) {
                    myInput.setText("");
                }
            }
        
            @Override
            public void focusLost(FocusEvent e) {
                if (myInput.getText().isEmpty()) {
                    myInput.setText("Enter Temperature");
                }
            }
        });




        myOutput = new JTextField(10);
        myOutput.setEditable(false);
        myOutput.setBackground(Color.WHITE);
        myOutput.setAlignmentX(Component.CENTER_ALIGNMENT);
        myOutput.setPreferredSize(new Dimension(300,150));

        myOutput.setText("Result");
        myOutput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (myOutput.getText().equals("Result")) {
                    myOutput.setText("");
                }
            }
        
            @Override
            public void focusLost(FocusEvent e) {
                if (myOutput.getText().isEmpty()) {
                    myOutput.setText("Result");
                }
            }
        });

        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};

        myComboFrom = new JComboBox<>(units);
        myComboFrom.setSelectedItem("Celcius");
        myComboFrom.setAlignmentX(Component.CENTER_ALIGNMENT);

        myComboTo = new JComboBox<>(units);
        myComboTo.setSelectedItem("Fahrenheit");
        myComboTo.setAlignmentX(Component.CENTER_ALIGNMENT);

        myBtnConvert = new JButton("CONVERT");
        myBtnConvert.setAlignmentX(Component.CENTER_ALIGNMENT);

        myComboFrom.addItemListener(new ComboFromItemListener(myComboTo));

        myBtnConvert.addActionListener(new ConvertAction());
        myBtnConvert.setBackground(Color.decode("#9be7c7"));

        panel.setBackground(Color.decode("#f4fcf9"));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        panel.add(Box.createVerticalStrut(20)); // Add spacing
        panel.add(labelfrom);
        panel.add(Box.createVerticalStrut(10)); // Add spacing
        panel.add(myComboFrom);
        panel.add(Box.createVerticalStrut(10)); // Add spacing
        panel.add(myInput);
        panel.add(Box.createVerticalStrut(40)); // Add spacing
        panel.add(labelto);
        panel.add(Box.createVerticalStrut(10)); // Add spacing
        panel.add(myComboTo);
        panel.add(Box.createVerticalStrut(10)); // Add spacing
        panel.add(myOutput);
        panel.add(Box.createVerticalStrut(20)); // Add spacing
        panel.add(myBtnConvert);
        panel.add(Box.createVerticalStrut(10)); // Add spacing

        myFrame.setLayout(new GridBagLayout());
        myFrame.add(panel, new GridBagConstraints());

        myFrame.setVisible(true);
    }

}

class ComboFromItemListener implements ItemListener
{
    private JComboBox<String>comboTo;

    public ComboFromItemListener(JComboBox<String>comboTo)
    {
        this.comboTo=comboTo;
    }

    public void itemStateChanged(ItemEvent e)
    {
        if(e.getStateChange()==ItemEvent.SELECTED)
        {
            updateComboTo((String) e.getItem());
        }
    }

    private void updateComboTo(String unitselected)
    {
        comboTo.removeAllItems();
        String[] units = {"Celsius","Fahrenheit","Kelvin"};
        for(String unit : units)
        {
            if(!unit.equals(unitselected))
            {
                comboTo.addItem(unit);
            }
        }
    }
}

class ConvertAction implements ActionListener
{
    public void actionPerformed(ActionEvent e) 
    {
       String from =(String) MyTempConverter.myComboFrom.getSelectedItem();
       String to = (String) MyTempConverter.myComboTo.getSelectedItem();  
       String inputText = MyTempConverter.myInput.getText();

       try
       {
         double inputValue = Double.parseDouble(inputText);
         double outputValue = convert(inputValue,from,to);
         MyTempConverter.myOutput.setText(String.valueOf(outputValue));
        
       }
       
       catch(NumberFormatException ex)
       {
         MyTempConverter.myOutput.setText("Invalid Inpo");
         
       }
    }

    private double convert(double value, String from, String to)
    {
        double result = value;
        if(from.equals("Celsius"))
        {
            if(to.equals("Fahrenheit"))
            {
                result = (value * 9 / 5) + 32;
            }
            else if(to.equals("Kelvin"))
            {
                result = value + 273.15;
            }
        }

        else if(from.equals("Fahrenheit"))
        {
            if(to.equals("Celsius"))
            {
                result = (value - 32) * 5 / 9;
            }
            else if(to.equals("Kelvin"))
            {
                result = (value - 32) * 5 / 9 + 273.15;
            }
        }

        else if(from.equals("Kelvin"))
        {
            if(to.equals("Celsius"))
            {
                result = value - 273.15;
            }
            else if(to.equals("Fahrenheit"))
            {
                result = (value - 273.15) * 9 / 5 + 32;
            }
        }

        return result;
    }
}

public class PRODIGY_SD_01 
{

        public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new MyTempConverter().showFrame();
                }
            });
        }
    
}