package ua.com.smiddle.core.util.util;

import ua.com.smiddle.core.util.model.interfaces.Action;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author srg on 27.09.16.
 * @project FinesseTester
 */
public class MultiUserForm extends JFrame implements PropertyChangeListener {
    PropertyChangeSupport propertyChangeSupport;
    private JButton btnAdd;
    private JPanel panel;
    private JPanel rootPanel;
    private JScrollPane container;
    private JPanel forContents;
    private MultiUserForm form;
    private Map<String, AgentPanel> panelMap = new ConcurrentHashMap<>();

    public MultiUserForm() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setContentPane(rootPanel);
        form = this;
        forContents.setLayout(new FlowLayout());
        container.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        container.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        container.setViewportBorder(new LineBorder(Color.BLACK));
        container.getViewport().add(forContents);
        propertyChangeSupport = new PropertyChangeSupport(this);
        propertyChangeSupport.addPropertyChangeListener(this);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String loginId = JOptionPane.showInputDialog(form, "Input agent name");
                propertyChangeSupport.firePropertyChange("new_component", null, new AgentPanel(loginId, Action.UNKNOWN));
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("new_component")) {
            if (evt.getNewValue() instanceof AgentPanel) {
                AgentPanel agentPanel = (AgentPanel) evt.getNewValue();
                panelMap.put(agentPanel.getLoginId(), agentPanel);
                forContents.add(agentPanel);
                forContents.revalidate();
                forContents.repaint();
            }
        }
    }
}
