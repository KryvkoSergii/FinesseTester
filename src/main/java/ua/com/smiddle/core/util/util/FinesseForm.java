package ua.com.smiddle.core.util.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.*;
import ua.com.smiddle.core.util.model.AgentStates;
import ua.com.smiddle.core.util.web.Sender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * Created by srg on 21.09.16.
 */
@org.springframework.stereotype.Component
@Scope("singleton")
public class FinesseForm extends JFrame implements CommandLineRunner {
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
    FinesseForm thisForm;
    @Autowired
    @Qualifier("State")
    private State state;
    @Autowired
    @Qualifier("Login")
    private Login loginDialog;
    @Autowired
    @Qualifier("Sender")
    private Sender sender;
    private JButton btnLogin;
    private JButton btnAction;
    private JComboBox comboBox1;
    private JTextArea LOGSTextArea;
    private JPanel contentPane;
    private AgentStates agentStates = AgentStates.LOGOUT;

    public FinesseForm() {
        super();
        setContentPane(contentPane);
        thisForm = this;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("FINESSE CONNECTOR");
        setVisible(true);
        setSize(300, 300);
        btnLogin.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (agentStates == AgentStates.LOGOUT) {
                    comboBox1.setEnabled(false);
                    btnAction.setEnabled(false);

                } else if (agentStates == AgentStates.LOGIN) {
                    comboBox1.setEnabled(true);
                    btnAction.setEnabled(false);
                }
                loginDialog.setVisible(true);
                login();

            }
        });
    }

    public void login() {
        try {
            sender.login();
            btnLogin.setText("LOGOUT");
            addLog("Logged with " + state.toString());
        } catch (Exception e) {
            addLog(e.getMessage());
        }
    }


    @Override
    public void run(String... strings) throws Exception {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
//                FinesseForm frame = new FinesseForm();
//                ContextLoader.getCurrentWebApplicationContext().getBean(FinesseForm.class);
            }
        });
    }

    private void addException(String message) {
        addLog("EXCEPTION=" + message);
    }

    private void addLog(String message) {
        LOGSTextArea.append(LocalDateTime.now().format(formatter) + " " + message + '\n');
    }
}
