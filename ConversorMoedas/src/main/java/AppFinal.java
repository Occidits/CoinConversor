import javax.swing.*;

public class AppFinal {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
           JFrame frame = new JFrame("Conversor");

           ConverterForm converterForm = new ConverterForm();

           frame.setContentPane(converterForm.getMainPanel());
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           frame.setResizable(false);
           frame.pack();
           frame.setLocationRelativeTo(null);
           frame.setVisible(true);
        });
    }
}
