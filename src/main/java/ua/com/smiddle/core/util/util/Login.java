package ua.com.smiddle.core.util.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

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
    private JLabel lblIP;
    //    private JTextField txtIP;
    private JComboBox cmbIP;

    public Login() {
        setTitle("ENTER DATA");
        setContentPane(contentPane);
        setModal(true);
        pack();
        getRootPane().setDefaultButton(buttonOK);
        setSize(600, 200);
        List<Wrapper> ips = defineIP();

        for (Wrapper w : ips)
            cmbIP.addItem(w);

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
        state.setIP(((Wrapper) cmbIP.getSelectedItem()).getIp());
//        state.setIP(txtIP.getText().trim());
        dispose();
        txtLogin.setText("");
        txtPassword.setText("");
        txtExtension.setText("");
//        txtIP.setText("127.0.0.1");
    }

    private void onCancel() {
        dispose();
    }


    public static List<Wrapper> defineIP() {
        List<Wrapper> wrappers = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            String ip;
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                // filters out 127.0.0.1 and inactive interfaces
//                if (iface.isLoopback() || !iface.isUp())
//                    continue;
                if (!iface.isUp()) continue;
                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    wrappers.add(new Wrapper(iface.getDisplayName(), addr.getHostAddress()));
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        return wrappers;
    }

    public static class Wrapper {
        private String name;
        private String ip;

        public Wrapper(String name, String ip) {
            this.name = name;
            this.ip = ip;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        @Override
        public String toString() {
            return name + " : " + ip;
        }
    }

}
