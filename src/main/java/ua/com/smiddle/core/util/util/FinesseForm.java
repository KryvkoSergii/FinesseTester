package ua.com.smiddle.core.util.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import ua.com.smiddle.core.util.model.interfaces.ApiError;
import ua.com.smiddle.core.util.model.interfaces.OutboundDialog;
import ua.com.smiddle.core.util.web.Sender;
import ua.com.smiddle.core.util.model.interfaces.Action;

import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    @Autowired
    private Environment environment;
    private JButton btnLogin;
    private JButton btnCall;
    private JComboBox comboBox1;
    private JTextArea LOGSTextArea;
    private JPanel contentPane;
    private JButton btnLogout;
    private JButton btnAnswer;
    private JLabel lblState;
    private JButton btnDrop;
    private JButton btnHold;
    private JButton btnUnhold;
    private JButton btnMultiUserMode;
    private JButton btnSubscribe;
    private JRadioButton rdbUser;
    private JRadioButton rdbServer;
    private JLabel subStatus;
    private JPanel panelRBTN;
    private JPanel subscriptionPanel;
    private JButton btnConsultCall;
    private JButton btnTransfer;
    private JButton btnConference;
    private ButtonGroup radioButtonGroup;
    private Map<Action, JComponent> accessToButtons = new HashMap<>();


    //Constructors
    public FinesseForm() {
        super();
        //== declaration ======
//        try {
//            setIconImage(ImageIO.read(new File("/home/srg/java/logo_s.png")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        setContentPane(contentPane);
        thisForm = this;
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setTitle("FINESSE CONNECTOR");
        setVisible(true);
        setSize(850, 300);
        radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(rdbUser);
        radioButtonGroup.add(rdbServer);
        makeMappingActionsToButtons();

        //== EVENT LISTENERS ===
        stateHandler = new PropertyChangeSupport(this);
        stateHandler.addPropertyChangeListener(new EventListener());

        btnLogin.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!rdbUser.isSelected() && !rdbServer.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Please, select subscription mode");
                } else if (rdbUser.isSelected() && !rdbServer.isSelected()) {
                    loginDialog.setVisible(true);
                    stateHandler.firePropertyChange("set_state", state.getAction(), Action.LOGIN);
                } else if (!rdbUser.isSelected() && rdbServer.isSelected()) {
                    JOptionPane.showMessageDialog(null, "not implemented yet");
                }
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
        btnAnswer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stateHandler.firePropertyChange("do_action", null, Action.ANSWER);
            }
        });
        btnDrop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stateHandler.firePropertyChange("do_action", null, Action.DROP);
            }
        });
        btnCall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phoneNumber = JOptionPane.showInputDialog(null, "Enter phone number", "MAKE A CALL", JOptionPane.INFORMATION_MESSAGE);
                stateHandler.firePropertyChange("do_action", null, new MakeCall(Action.MAKE_CALL, phoneNumber));
            }
        });
        btnConsultCall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phoneNumber = JOptionPane.showInputDialog(null, "Enter phone number", "MAKE A CONSULT CALL", JOptionPane.INFORMATION_MESSAGE);
                stateHandler.firePropertyChange("do_action", null, new MakeCall(Action.CONSULT_CALL, phoneNumber));
            }
        });
        btnHold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stateHandler.firePropertyChange("do_action", null, Action.HOLD);
            }
        });
        btnUnhold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stateHandler.firePropertyChange("do_action", null, Action.RETRIEVE);
            }
        });
        btnMultiUserMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MultiUserForm multiUserForm = new MultiUserForm();
                multiUserForm.setVisible(true);
            }
        });
        btnSubscribe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rdbUser.isSelected())
                    JOptionPane.showMessageDialog(null, "Subscription available on server mode", "SUBSCRIPTION WARNING", JOptionPane.WARNING_MESSAGE);
                if (rdbServer.isSelected()) {
                    String authToken = JOptionPane.showInputDialog(null, "Input authorization token");
                    stateHandler.firePropertyChange("subscription", null, authToken);
                }
            }
        });
        btnTransfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stateHandler.firePropertyChange("do_action", null, Action.TRANSFER);
            }
        });
        btnConference.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stateHandler.firePropertyChange("do_action", null, Action.CONFERENCE);
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

    private void sendDialog(Action action, String dialogId) {
        try {
            sender.sendAction(action, dialogId);
        } catch (Exception e) {
            addException(e.getMessage());
        }
    }

    private void sendMakeCall(Action action, String toAddress) {
        try {
            sender.makeCall(action, toAddress);
        } catch (Exception e) {
            addException(e.getMessage());
        }
    }

    private void sendMakeConsultCall(Action action, String toAddress, String dialogId) {
        try {
            sender.makeConsultCall(action, toAddress, dialogId);
        } catch (Exception e) {
            addException(e.getMessage());
        }
    }

    private void sendSubscription(String action, String authToken) {
        try {
            sender.sendSubscription(action, authToken);
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
        btnSubscribe.setText("subscribe");
        btnLogout.setEnabled(false);
        btnLogin.setEnabled(true);
        btnAnswer.setEnabled(true);
        btnCall.setEnabled(false);
        comboBox1.setEnabled(false);
        btnAnswer.setEnabled(false);
        btnDrop.setEnabled(false);
        btnHold.setEnabled(false);
        btnUnhold.setEnabled(false);
        btnMultiUserMode.setVisible(false);
    }

    public void changeAgentState(Action action) {
        stateHandler.firePropertyChange("event_state", state.getAction(), action);
    }

    public void showError(ApiError error) {
        stateHandler.firePropertyChange("event_error", null, error);
    }

    public void setDialog(OutboundDialog dialog) {
        //incoming call
        if (dialog.getDialogState() != null && dialog.getDialogState().equals("ALERTING")) {
            JOptionPane.showMessageDialog(null, "Incoming call from " + dialog.getFromAddress(), "INCOMING CALL", JOptionPane.INFORMATION_MESSAGE);
            state.setDialogId(dialog.getId());
        }

        if (!state.getDialogId().equals(dialog.getId())) {
            state.setDialogId(dialog.getId());
        }

        stateHandler.firePropertyChange("process_call", null, dialog.getAllowedActions());

    }

    private void makeMappingActionsToButtons() {
        accessToButtons.put(Action.ANSWER, btnAnswer);
        accessToButtons.put(Action.DROP, btnDrop);
        accessToButtons.put(Action.MAKE_CALL, btnCall);
        accessToButtons.put(Action.CONSULT_CALL, btnConsultCall);
        accessToButtons.put(Action.HOLD, btnHold);
        accessToButtons.put(Action.RETRIEVE, btnUnhold);
        accessToButtons.put(Action.TRANSFER, btnTransfer);
        accessToButtons.put(Action.CONFERENCE, btnConference);
    }

    private void setAllButtonToDisable() {
        for (JComponent component : accessToButtons.values()) {
            component.setEnabled(false);
        }
        thisForm.repaint();
    }

    private void setAllButtonToEnable() {
        for (JComponent component : accessToButtons.values()) {
            component.setEnabled(true);
        }
        thisForm.repaint();
    }

    private void setButtonStatesDuringCall(java.util.List<Action> allowedActions) {
        for (Action a : allowedActions) {
            if (accessToButtons.containsKey(a))
                accessToButtons.get(a).setEnabled(true);
        }
        thisForm.repaint();
    }

    //Listeners

    /**
     * слушатель, получающий события на отправку команд на изменение состояния
     */
    public class EventListener implements PropertyChangeListener {
        public EventListener() {
            System.out.println(this.getClass().getName() + " started");
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            try {
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
                        btnAnswer.setEnabled(true);
                        btnDrop.setEnabled(true);
                        btnHold.setEnabled(true);
                        btnUnhold.setEnabled(true);
                    } else if (evt.getNewValue() == Action.NOT_READY) {
                        sendChangeState((Action) evt.getNewValue());
                    } else if (evt.getNewValue() == Action.READY) {
                        sendChangeState((Action) evt.getNewValue());
                    }
                } else if (evt.getPropertyName().equals("event_state")) {
                    state.setAction((Action) evt.getNewValue());
                    lblState.setText(state.getAction().toString());
                    if (((Action) evt.getNewValue()) == Action.LOGOUT) {
                        thisForm.setTitle("FINESSE CONNECTOR");
                    } else {
                        thisForm.setTitle("FINESSE CONNECTOR (" + state.getLoginId() + "):" + environment.getProperty("server.port") + ": ext=" + state.getExtension());
                    }
                } else if (evt.getPropertyName().equals("event_error")) {
                    ApiError error = (ApiError) evt.getNewValue();
                    JOptionPane.showMessageDialog(null, error.getErrorMessage() + " " + error.getErrorType(),
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                } else if (evt.getPropertyName().equals("do_action")) {
                    if (evt.getNewValue() instanceof Action && (evt.getNewValue() == Action.ANSWER || evt.getNewValue() == Action.DROP)) {
                        sendDialog((Action) evt.getNewValue(), state.getDialogId());
                    } else if (evt.getNewValue() instanceof MakeCall && ((MakeCall) evt.getNewValue()).getAction() == Action.MAKE_CALL) {
                        sendMakeCall(((MakeCall) evt.getNewValue()).getAction(), ((MakeCall) evt.getNewValue()).toAddress);
                    } else if (evt.getNewValue() instanceof MakeCall && ((MakeCall) evt.getNewValue()).getAction() == Action.CONSULT_CALL) {
                        sendMakeConsultCall(((MakeCall) evt.getNewValue()).getAction(), ((MakeCall) evt.getNewValue()).toAddress, state.getDialogId());
                    } else if (evt.getNewValue() instanceof Action && (evt.getNewValue() == Action.HOLD || evt.getNewValue() == Action.RETRIEVE)) {
                        sendDialog((Action) evt.getNewValue(), state.getDialogId());
                    } else if (evt.getNewValue() instanceof Action && (evt.getNewValue() == Action.TRANSFER || evt.getNewValue() == Action.CONFERENCE)) {
                        sendDialog((Action) evt.getNewValue(), state.getDialogId());
                    }
                } else if (evt.getPropertyName().equals("subscription")) {
                    if (btnSubscribe.getText().equals("subscribe")) {
                        sendSubscription("subscribe", evt.getNewValue().toString());
                        addLog("subscription ...");
                    } else if (btnSubscribe.getText().equals("unsubscribe")) {
                        sendSubscription("unsubscribe", evt.getNewValue().toString());
                        addLog("unsubscription ...");
                    }
                } else if (evt.getPropertyName().equals("process_call")) {
                    if (evt.getNewValue() != null && !((java.util.List<Action>) evt.getNewValue()).isEmpty()) {
                        setAllButtonToDisable();
                        setButtonStatesDuringCall((java.util.List<Action>) evt.getNewValue());
                    } else setAllButtonToEnable();
                }
            } catch (Exception e) {
                addException("EventListener throw EXCEPTION=" + e.getMessage());
                e.printStackTrace();
            }
        }
    }


    //Inner classes
    class MakeCall {
        private Action action;
        private String toAddress;

        //Constructors
        public MakeCall() {
        }

        public MakeCall(Action action, String toAddress) {
            this.action = action;
            this.toAddress = toAddress;
        }


        //Getters and setters
        public Action getAction() {
            return action;
        }

        public void setAction(Action action) {
            this.action = action;
        }

        public String getToAddress() {
            return toAddress;
        }

        public void setToAddress(String toAddress) {
            this.toAddress = toAddress;
        }
    }
}
