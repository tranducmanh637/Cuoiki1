package Do_an_ck1;
import Data.DAO;
import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private DAO dao;

    public LoginFrame() {
        dao = new DAO();
        setTitle("Đăng Nhập");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel usernameLaber = new JLabel("Tài khoản:");
        panel.add(usernameLaber);
        usernameField = new JTextField(20);
        panel.add(usernameField);
        JLabel passwordLabel = new JLabel("Mật khẩu: ");
        panel.add(passwordLabel);
        passwordField = new JPasswordField(20);     
        panel.add(passwordField);
        loginButton = new JButton("Đăng Nhập");
        panel.add(loginButton);
        loginButton.addActionListener(e -> xuLiDangNhap());
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void xuLiDangNhap() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (dao.checkLogin(username, password)) {
            JOptionPane.showMessageDialog(this, "Đăng nhập thành công!");
            new ComputerSalesManager(); 
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu không đúng.");
        }
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}

