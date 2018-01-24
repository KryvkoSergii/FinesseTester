package ua.com.smiddle.core.util.util;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import ua.com.smiddle.core.util.model.interfaces.Action;

/**
 * @author srg on 27.09.16.
 * @project FinesseTester
 */
public class AgentPanel extends JPanel {
    private String loginId;
    private Action state;
    private JLabel lblTitle1 = new JLabel("Agent ID:");
    private JLabel lblTitle2 = new JLabel("CURRENT STATE:");
    private JLabel agentName;
    private JLabel agentState;
    private AgentPanel panel;

    public AgentPanel() {
    }

    public AgentPanel(String loginId, Action state) {
        this.loginId = loginId;
        this.state = state;
        init();
    }


    public Action getState() {
        return state;
    }

    public void setState(Action state) {
        this.state = state;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    private void init() {
        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.LEFT);
        setLayout(layout);
        agentName = new JLabel(loginId);
        agentState = new JLabel(Action.UNKNOWN.name());
        panel = this;
        panel.add(lblTitle1,0);
        panel.add(agentName,1);
        panel.add(lblTitle2,2);
        panel.add(agentState,3);
        panel.setVisible(true);
        panel.setBorder(new LineBorder(Color.BLACK));
        this.setSize(400,70);
    }


}
