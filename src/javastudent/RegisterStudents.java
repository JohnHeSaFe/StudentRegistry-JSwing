/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javastudent;

import JFrames.MainFrame;
import java.io.BufferedReader;
import java.io.IOException;
import javax.swing.SwingUtilities;

/**
 *
 * @author henar
 */
public class RegisterStudents {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
