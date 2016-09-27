package ua.com.smiddle.core.util.util;

import javax.swing.*;
import java.awt.*;

import ua.com.smiddle.core.util.model.interfaces.Action;

/**
 * @author srg on 27.09.16.
 * @project FinesseTester
 */
public class AgentPanel extends JPanel {
    private String loginId;
    private Action state;
    private JLabel lblTitle1;
    private JLabel agentName;
    private JLabel agentState;
    private LayoutManager layout;

    public AgentPanel() {

    }

    public AgentPanel(String loginId, Action state) {
        this.loginId = loginId;
        this.state = state;
    }

    private void init() {
        layout = new FlowLayout();
        this.setLayout(layout);
        lblTitle1 = new JLabel("Agent ID:");
        agentName = new JLabel("...");
        agentState = new JLabel(Action.UNKNOWN.name());
        this.add(lblTitle1,0);
        this.add(agentName,0);
        this.add(agentState,0);
    }


}
