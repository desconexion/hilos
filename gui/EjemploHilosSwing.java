package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EjemploHilosSwing extends JFrame {
    private JLabel etiqueta;

    public EjemploHilosSwing() {
        super("Ejemplo de Hilos en Java Swing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        etiqueta = new JLabel("Contador: 0");
        add(etiqueta, BorderLayout.CENTER);

        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);

        // Creación y inicio del hilo
        Thread hilo = new Thread(new Actualizador());
        hilo.start();
    }

    // Clase interna que implementa Runnable para el hilo
    private class Actualizador implements Runnable {
        public void run() {
            int i = 0;
            
            while (true) {
                try {
                    // Actualizar la etiqueta con el valor del contador
                    final int contador = i;
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            etiqueta.setText("Contador: " + contador);
                        }
                    });

                    // Incrementar el contador
                    i++;

                    // Pausa de 1 segundo entre actualizaciones
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        // Crear y mostrar la ventana de la aplicación
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EjemploHilosSwing();
            }
        });
    }
}

