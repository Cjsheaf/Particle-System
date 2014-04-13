package ParticleSystem.ExampleProgram;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ParticleTestMain {
    private static void createAndShowGUI() {
        final ProgramSettings settings = new ProgramSettings();
        
        //Create and set up the window.
        JFrame frame = new JFrame("Scrapper Blaster");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(settings.DEFAULT_SCREEN_WIDTH, settings.DEFAULT_SCREEN_HEIGHT));
        
        //Set up the content pane.
        frame.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        ParticleTestPanel effectPanel = new ParticleTestPanel(settings);
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0.9;
        constraints.weighty = 1.0;
        frame.add(effectPanel, constraints);
        
        //Create the control panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.PAGE_AXIS));
        constraints.weightx = 0.1;
        frame.add(controlPanel, constraints);
        
        final JButton particleNumberButton = new JButton("Number of Particles: LOW");
        particleNumberButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                switch(settings.getGraphicalDetailLevel()) {
                    case 0:
                        settings.setGraphicalDetailLevel(1);
                        particleNumberButton.setText("Number of Particles: MEDIUM");
                        break;
                    case 1:
                        settings.setGraphicalDetailLevel(2);
                        particleNumberButton.setText("Number of Particles: HIGH");
                        break;
                    case 2:
                        settings.setGraphicalDetailLevel(0);
                        particleNumberButton.setText("Number of Particles: LOW");
                        break;
                }
                particleNumberButton.repaint();
            }
        });
        particleNumberButton.setPreferredSize(new Dimension(230, 25));
        particleNumberButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(particleNumberButton);
        
        final JButton effectTypeButton = new JButton("Particle Effect: SLOW EXPLOSION");
        effectTypeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                switch(settings.getParticleEvent()) {
                    case 0:
                        settings.setParticleEvent(1);
                        effectTypeButton.setText("Particle Effect: FAST EXPLOSION");
                        break;
                    case 1:
                        settings.setParticleEvent(2);
                        effectTypeButton.setText("Particle Effect: SHOWER");
                        break;
                    case 2:
                        settings.setParticleEvent(3);
                        effectTypeButton.setText("Particle Effect: SPRAY");
                        break;
                    case 3:
                        settings.setParticleEvent(0);
                        effectTypeButton.setText("Particle Effect: SLOW EXPLOSION");
                        break;
                }
                particleNumberButton.repaint();
            }
        });
        effectTypeButton.setPreferredSize(new Dimension(230, 25));
        effectTypeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(effectTypeButton);
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        
        effectPanel.initialize();
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}