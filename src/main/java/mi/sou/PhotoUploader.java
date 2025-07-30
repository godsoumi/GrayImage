package mi.sou;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PhotoUploader extends JFrame {

    private JLabel originalLabel, grayLabel;
    private BufferedImage originalImage, grayImage;

    public PhotoUploader() {
        setTitle("Photo Uploader and Grayscale Converter");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JButton uploadButton = new JButton("Upload Photo");
        JButton openFolderButton = new JButton("Open File Folder");
        JButton convertButton = new JButton("Convert to Grayscale");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(uploadButton);
        buttonPanel.add(openFolderButton);
        buttonPanel.add(convertButton);

        originalLabel = new JLabel("", JLabel.CENTER);
        grayLabel = new JLabel("", JLabel.CENTER);

        JPanel imagePanel = new JPanel(new GridLayout(1, 2));
        imagePanel.add(originalLabel);
        imagePanel.add(grayLabel);

        add(buttonPanel, BorderLayout.NORTH);
        add(imagePanel, BorderLayout.CENTER);

        uploadButton.addActionListener(e -> uploadPhoto());
        openFolderButton.addActionListener(e -> openFileExplorer());
        convertButton.addActionListener(e -> convertToGrayscale());

        setVisible(true);
    }

    private void uploadPhoto() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();
                originalImage = ImageIO.read(file);

                Image scaled = originalImage.getScaledInstance(450, -1, Image.SCALE_SMOOTH);
                originalLabel.setIcon(new ImageIcon(scaled));

                grayLabel.setIcon(null);
                grayLabel.setText("Grayscale Image");

            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error loading image!");
            }
        }
    }

    private void openFileExplorer() {
        try {
            Desktop.getDesktop().open(new File("C:\\"));  // Change as needed
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void convertToGrayscale() {
        if (originalImage == null) {
            JOptionPane.showMessageDialog(this, "Please upload an image first!");
            return;
        }

        grayImage = GrayscaleConverter.convert(originalImage);

        Image scaledGray = grayImage.getScaledInstance(450, -1, Image.SCALE_SMOOTH);
        grayLabel.setIcon(new ImageIcon(scaledGray));
        grayLabel.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PhotoUploader::new);
    }
}



