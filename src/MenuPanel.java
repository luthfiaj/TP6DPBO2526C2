import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel{
    // Runnable = "potongan aksi" yang dikirim dari App
    public MenuPanel(Runnable onStart, Runnable onExit) {
        setPreferredSize(new Dimension(360, 640));
        setBackground(Color.red);
        setLayout(new GridBagLayout());

        JLabel title = new JLabel("FLAPPY WO");
        JLabel title1 = new JLabel("Bantu Wowo belajar agar bisa mimpin negara ");
        title.setFont(new Font("Times New Roman", Font.BOLD, 36));
        title.setForeground(Color.black);
        title1.setFont(new Font("Times New Roman", Font.BOLD, 24));
        title1.setForeground(Color.black);

        JButton startButton = new JButton("Mulai Permainan");
        JButton exitButton = new JButton("Keluar");
        startButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        exitButton.setFont(new Font("Times New Roman", Font.BOLD, 18));

        startButton.addActionListener(e -> onStart.run());
        exitButton.addActionListener(e -> onExit.run());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(12, 0, 12, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridy = 0; add(title, gbc);
        gbc.gridy = 1; add(startButton, gbc);
        gbc.gridy = 2; add(exitButton, gbc);
    }
}