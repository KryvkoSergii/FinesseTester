package ua.com.smiddle.core.util;

import ua.com.smiddle.core.util.model.AgentStates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by srg on 21.09.16.
 */
public class FinesseForm extends JFrame {
    private JButton btnLogin;
    private JButton btnAction;
    private JComboBox comboBox1;
    private JTextArea LOGSTextArea;
    private JPanel containPane;
    private AgentStates state = AgentStates.LOGOUT;

    public FinesseForm() {
        super();
        setContentPane(containPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setName("FINESSE CONNECTOR");
        setVisible(true);
        setSize(300,300);
        btnLogin.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(state==AgentStates.LOGOUT){
                    comboBox1.setEnabled(false);
                    btnAction.setEnabled(false);

                } else if (state==AgentStates.LOGIN){
                    comboBox1.setEnabled(true);
                    btnAction.setEnabled(false);
                }
            }
        });




    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                FinesseForm frame = new FinesseForm();
            }
        });
    }
}
