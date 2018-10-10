import javax.swing.*;
import java.awt.*;

public class Main
{
    public static void main(String[] args) {
        // SCORE
        JFrame obj = new JFrame();
        // Gameplay!
        Gameplay gameplay = new Gameplay();

        // settings for JFrame
        obj.setBounds(10,10,905,700);
        obj.setBackground(Color.GRAY);
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.setTitle("Gameplay");
        obj.add(gameplay);
}
}
