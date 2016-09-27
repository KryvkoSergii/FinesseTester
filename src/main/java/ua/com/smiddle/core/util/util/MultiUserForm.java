package ua.com.smiddle.core.util.util;

import ua.com.smiddle.core.util.model.interfaces.*;
import ua.com.smiddle.core.util.model.interfaces.Action;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author srg on 27.09.16.
 * @project FinesseTester
 */
public class MultiUserForm extends JFrame{
    private JButton btnAdd;
    private JPanel contentPane;
    private MultiUserForm form;

    public MultiUserForm() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(850, 300);
        form = this;
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String loginId = JOptionPane.showInputDialog(form,"INPUT AGENT NAME");
                AgentPanel agentPanel = new AgentPanel(loginId, Action.UNKNOWN);
                contentPane.add(agentPanel);

            }
        });
    }
}
