package server;

import javax.swing.SwingUtilities;

public class Main {
    public static SocketController socketController;
    public static MainScreen mainScreen;

    public static void main(String[] args) {
        // Khởi động giao diện đăng nhập
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }

    public static void showMainScreen() {
        mainScreen = new MainScreen();
        mainScreen.setVisible(true);
    }
}
