import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Detector {

    public static void main(String[] args) {

        JFrame startFrame = new JFrame("🌈 Gay Detector 🌈");
        startFrame.setSize(400, 300);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setLayout(null);

        JLabel startLabel = new JLabel("       Gay Detector ");
        startLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        startLabel.setBounds(50, 50, 300, 30);
        startFrame.add(startLabel);

        JButton detectBtn = new JButton("Start Detector");
        detectBtn.setFont(new Font("Arial", Font.BOLD, 14));
        detectBtn.setBounds(120, 120, 150, 50);
        startFrame.add(detectBtn);

        detectBtn.addActionListener(e -> {
            startFrame.dispose();
            openGayQuestionWindow();
        });

        startFrame.setVisible(true);
    }

    public static void openGayQuestionWindow() {

        JFrame frame = new JFrame("Gay Detector Result");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(255, 182, 193); // LightPink
                Color color2 = new Color(221, 160, 221); // Plum
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(null);
        frame.setContentPane(panel);

        JLabel label = new JLabel("Are you gay?");
        label.setFont(new Font("Papyrus", Font.BOLD, 22));
        label.setBounds(130, 30, 200, 30);
        panel.add(label);

        JButton yesBtn = new JButton("YES");
        yesBtn.setFont(new Font("Arial", Font.BOLD, 16));
        yesBtn.setBounds(100, 120, 80, 40);
        panel.add(yesBtn);

        JButton noBtn = new JButton("NO");
        noBtn.setFont(new Font("Arial", Font.BOLD, 16));
        noBtn.setBounds(220, 120, 80, 40);
        panel.add(noBtn);


        yesBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Congrats! You are gay 😆❤️");
        });


        noBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                int x = (int)(Math.random() * (panel.getWidth() - noBtn.getWidth()));
                int y = (int)(Math.random() * (panel.getHeight() - noBtn.getHeight()));
                noBtn.setLocation(x, y);


                try {
                    File soundFile = new File("no_sound.wav"); // must exist in project folder
                    AudioInputStream audio = AudioSystem.getAudioInputStream(soundFile);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audio);
                    clip.start();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    ex.printStackTrace();
                }
            }
        });

        frame.setVisible(true);
    }
}