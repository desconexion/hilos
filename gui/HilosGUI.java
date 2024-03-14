package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HilosGUI extends JFrame {
    private JButton startButton;
    private JTextArea textArea;
    private Hilo hilo;

    public HilosGUI() {
        setTitle("Aplicación Gráfica con Hilos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        startButton = new JButton("Iniciar Proceso");
        textArea = new JTextArea();

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.setEnabled(false);
                hilo = new Hilo();
                hilo.start();
            }
        });

        JPanel panel = new JPanel();
        panel.add(startButton);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    class Hilo extends Thread {
        @Override
        public void run() {
            for (int i = 0; i <= 10; i++) {
                try {
                    // Simulando una tarea larga
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final int progreso = i;
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        textArea.append("Progreso: " + progreso + "\n");
                        textArea.setCaretPosition(textArea.getDocument().getLength());
                    }
                });
            }
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    startButton.setEnabled(true);
                }
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                HilosGUI app = new HilosGUI();
                app.setVisible(true);
            }
        });
    }
}
