package Ejercicio1;

import javax.swing.*;
import java.awt.*;

public class Ejercicio1A extends JFrame {
    private Image bandera;
    private Image asta;
    private int posYBandera;
    private final int posFinalY = 0;

    public Ejercicio1A() {

        bandera = new ImageIcon("src/main/resources/img/eu.gif").getImage();
        asta = new ImageIcon("src/main/resources/img/asta.png").getImage();

        posYBandera = 400;

        setTitle("Izamiento de Bandera");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);


        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.drawImage(asta, 50, 0, null);


                g.drawImage(bandera, 70, posYBandera, null);
            }
        };

        add(panel);

        Thread animacion = new Thread(() -> {
            while (posYBandera > posFinalY) {
                posYBandera -= 2;
                panel.repaint();
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        animacion.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Ejercicio1A frame = new Ejercicio1A();
            frame.setVisible(true);
        });
    }
}