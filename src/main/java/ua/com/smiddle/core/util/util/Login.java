package ua.com.smiddle.core.util.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ua.com.smiddle.core.util.web.Sender;

import javax.swing.*;
import java.awt.event.*;

@Component("Login")
@Scope("singleton")
public class Login extends JDialog {
    @Autowired
    @Qualifier("State")
    private State state;


    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtLogin;
    private JTextField txtExtension;
    private JPasswordField txtPassword;

    public Login() {
        setTitle("ENTER DATA");
        setContentPane(contentPane);
        setModal(true);
        pack();
        getRootPane().setDefaultButton(buttonOK);
        setSize(300, 150);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        state.setLoginId(txtLogin.getText().trim());
        state.setPassword(txtPassword.getText().trim());
        state.setExtension(txtExtension.getText().trim());
        dispose();
        txtLogin.setText("");
        txtPassword.setText("");
        txtExtension.setText("");
    }

    private void onCancel() {
        dispose();
    }


}
