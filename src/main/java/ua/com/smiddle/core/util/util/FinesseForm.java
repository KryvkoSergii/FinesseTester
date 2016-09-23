package ua.com.smiddle.core.util.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Scope;
import ua.com.smiddle.core.util.model.Action;
import ua.com.smiddle.core.util.web.Sender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by srg on 21.09.16.
 */
@org.springframework.stereotype.Component("FinesseForm")
@Scope("singleton")
public class FinesseForm extends JFrame implements CommandLineRunner {
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
    FinesseForm thisForm;
    private PropertyChangeSupport stateHandler;
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
    private JButton btnCall;
    private JComboBox comboBox1;
    private JTextArea LOGSTextArea;
    private JPanel contentPane;
    private JButton btnLogout;
    private JButton btnAnswer;
    private JLabel lblState;


    //Constructors
    public FinesseForm() {
        super();
        //== declaration ======
        setContentPane(contentPane);
        thisForm = this;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("FINESSE CONNECTOR");
        setVisible(true);
        setSize(650, 300);
        setInitUnknown();
        //== EVENT LISTENERS ===
        stateHandler = new PropertyChangeSupport(this);
        stateHandler.addPropertyChangeListener(new StateListener());

        btnLogin.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginDialog.setVisible(true);
                stateHandler.firePropertyChange("set_state", state.getAction(), Action.LOGIN);
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = ((JComboBox) e.getSource()).getSelectedIndex();
                switch (choice) {
                    case 0:
                        stateHandler.firePropertyChange("set_state", state.getAction(), Action.NOT_READY);
                        break;
                    case 1:
                        stateHandler.firePropertyChange("set_state", state.getAction(), Action.READY);
                        break;
                    default:
//                        JOptionPane.showMessageDialog(null, "UNKNOWN STATE", String.valueOf("STATE CODE " + choice), JOptionPane.WARNING_MESSAGE);
                        break;
                }
            }
        });
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stateHandler.firePropertyChange("set_state", state.getAction(), Action.LOGOUT);
            }
        });
    }


    //Methods
    private void sendLOGIN() {
        try {
            sender.login();
        } catch (Exception e) {
            addException(e.getMessage());
            return;
        }
    }

    private void sendChangeState(Action action) {
        try {
            sender.change_state(action);
        } catch (Exception e) {
            addException(e.getMessage());
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

    public void addException(String message) {
        addLog("EXCEPTION=" + message);
    }

    public void addLog(String message) {
        LOGSTextArea.append(LocalDateTime.now().format(formatter) + " " + message + '\n');
    }

    private void setInitUnknown() {
        btnLogout.setEnabled(false);
        btnLogin.setEnabled(true);
        btnAnswer.setEnabled(false);
        btnCall.setEnabled(false);
        comboBox1.setEnabled(false);
    }

    public void changePropertyTo(Action action) {
        stateHandler.firePropertyChange("event_state", state.getAction(), action);
    }


    //Listeners
    /**
     * слушатель, получающий события на отправку команд на изменение состояния
     */
    public class StateListener implements PropertyChangeListener {
        public StateListener() {
            System.out.println(this.getClass().getName() + " started");
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals("set_state")) {
                if (evt.getNewValue() == Action.LOGOUT) {
                    sendChangeState((Action) evt.getNewValue());
                    btnLogout.setEnabled(true);
                    btnLogin.setEnabled(true);
                    btnAnswer.setEnabled(true);
                    btnCall.setEnabled(true);
                    comboBox1.setEnabled(true);
                } else if (evt.getNewValue() == Action.LOGIN) {
                    sendLOGIN();
                    btnLogout.setEnabled(true);
                    btnLogin.setEnabled(true);
                    btnAnswer.setEnabled(true);
                    btnCall.setEnabled(true);
                    comboBox1.setEnabled(true);
                } else if (evt.getNewValue() == Action.NOT_READY) {
                    sendChangeState((Action) evt.getNewValue());
                    btnLogout.setEnabled(true);
                    btnLogin.setEnabled(true);
                    btnAnswer.setEnabled(true);
                    btnCall.setEnabled(true);
                    comboBox1.setEnabled(true);
                } else if (evt.getNewValue() == Action.READY) {
                    sendChangeState((Action) evt.getNewValue());
                    btnLogout.setEnabled(true);
                    btnLogin.setEnabled(true);
                    btnAnswer.setEnabled(true);
                    btnCall.setEnabled(true);
                    comboBox1.setEnabled(true);
                }
            } else if (evt.getPropertyName().equals("event_state")) {
                state.setAction((Action) evt.getNewValue());
                lblState.setText(state.getAction().toString());
            }
        }
    }
}
