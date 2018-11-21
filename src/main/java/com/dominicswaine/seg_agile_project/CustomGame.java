package main.java.com.dominicswaine.seg_agile_project;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class CustomGame {

    private JFrame frame;
    private JPanel containerOfEverything;
    private JPanel top;
    private JPanel middle;
    private JPanel bottom;
    private JPanel containerOfTextBoxes;
    private JPanel containerOfBackButton;
    private JLabel title;
    private JLabel text;
    private JComboBox playerSelector;
    private JTextField kazanField;
    private JLabel kazanLabel;
    private JTextField hole1;
    private JLabel label1;
    private JTextField hole2;
    private JLabel label2;
    private JTextField hole3;
    private JLabel label3;
    private JTextField hole4;
    private JLabel label4;
    private JTextField hole5;
    private JLabel label5;
    private JTextField hole6;
    private JLabel label6;
    private JTextField hole7;
    private JLabel label7;
    private JTextField hole8;
    private JLabel label8;
    private JTextField hole9;
    private JLabel label9;
    private JButton saveButton;
    private JButton startGameButton;
    private JButton backButton;

    public CustomGame() {

        //Initialising the Frame

        frame  = new JFrame();
        frame.setSize(new Dimension(1920, 1080));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Making the labels

        kazanLabel = new JLabel("Kazan:", SwingConstants.RIGHT);
        kazanLabel.setBorder(new EmptyBorder(0,0,0,15));
        label1 = new JLabel("Hole 2:", SwingConstants.RIGHT);
        label1.setBorder(new EmptyBorder(0,0,0,15));
        label2 = new JLabel("Hole 4:", SwingConstants.RIGHT);
        label2.setBorder(new EmptyBorder(0,0,0,15));
        label3 = new JLabel("Hole 6:", SwingConstants.RIGHT);
        label3.setBorder(new EmptyBorder(0,0,0,15));
        label4 = new JLabel("Hole 8:", SwingConstants.RIGHT);
        label4.setBorder(new EmptyBorder(0,0,0,15));
        label5 = new JLabel("Hole 1:", SwingConstants.RIGHT);
        label5.setBorder(new EmptyBorder(0,0,0,15));
        label6 = new JLabel("Hole 3:", SwingConstants.RIGHT);
        label6.setBorder(new EmptyBorder(0,0,0,15));
        label7 = new JLabel("Hole 5:", SwingConstants.RIGHT);
        label7.setBorder(new EmptyBorder(0,0,0,15));
        label8 = new JLabel("Hole 7:", SwingConstants.RIGHT);
        label8.setBorder(new EmptyBorder(0,0,0,15));
        label9 = new JLabel("Hole 9:", SwingConstants.RIGHT);
        label9.setBorder(new EmptyBorder(0,0,0,15));

        //Initialising the buttons

        saveButton = new JButton("Save");
        saveButton.setFont(new Font("Arial", Font.BOLD, 18));
        startGameButton = new JButton("Start");
        startGameButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 18));

        //Initialising the text fields

        kazanField = new JTextField();
        hole1 = new JTextField();
        hole2 = new JTextField();
        hole3 = new JTextField();
        hole4 = new JTextField();
        hole5 = new JTextField();
        hole6 = new JTextField();
        hole7 = new JTextField();
        hole8 = new JTextField();
        hole9 = new JTextField();

        //Constructing the panels

        top = new JPanel(new BorderLayout());
        middle = new JPanel();
        middle.setLayout(new BoxLayout(middle, BoxLayout.Y_AXIS));
        bottom = new JPanel(new FlowLayout());
        bottom.add(saveButton);
        bottom.add(startGameButton);
        containerOfBackButton = new JPanel(new BorderLayout());
        containerOfBackButton.add(backButton, BorderLayout.WEST);
        containerOfTextBoxes = new JPanel(new GridLayout(2,0));
        containerOfTextBoxes.setBorder(new EmptyBorder(0,0,0,90));
        containerOfTextBoxes.add(kazanLabel);
        containerOfTextBoxes.add(kazanField);
        containerOfTextBoxes.add(label1);
        containerOfTextBoxes.add(hole1);
        containerOfTextBoxes.add(label2);
        containerOfTextBoxes.add(hole2);
        containerOfTextBoxes.add(label3);
        containerOfTextBoxes.add(hole3);
        containerOfTextBoxes.add(label4);
        containerOfTextBoxes.add(hole4);
        containerOfTextBoxes.add(label5);
        containerOfTextBoxes.add(hole5);
        containerOfTextBoxes.add(label6);
        containerOfTextBoxes.add(hole6);
        containerOfTextBoxes.add(label7);
        containerOfTextBoxes.add(hole7);
        containerOfTextBoxes.add(label8);
        containerOfTextBoxes.add(hole8);
        containerOfTextBoxes.add(label9);
        containerOfTextBoxes.add(hole9);

        containerOfEverything = new JPanel();
        containerOfEverything.setLayout(new BoxLayout(containerOfEverything, BoxLayout.Y_AXIS));
        containerOfEverything.add(top);
        containerOfEverything.add(middle);

        //Initialising the text

        title = new JLabel("Custom Game",SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        text = new JLabel("Insert some text here explaining this page.",SwingConstants.CENTER);
        text.setFont(new Font("Arial", Font.BOLD, 20));

        //Initialising the dropdown

        playerSelector = new JComboBox();
        playerSelector.setFont(new Font("Arial", Font.PLAIN, 18));
        playerSelector.addItem("Player 1");
        playerSelector.addItem("Player 2");

        //Setting the locations of the components

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.add(Box.createVerticalGlue());

        textPanel.add(title);
        textPanel.add(Box.createRigidArea(new Dimension(0, 305)));
        textPanel.add(text);
        this.title.setAlignmentX(this.title.CENTER_ALIGNMENT);
        text.setAlignmentX(text.CENTER_ALIGNMENT);
        textPanel.add(Box.createVerticalGlue());

        top.add(textPanel);
        top.add(containerOfBackButton, BorderLayout.NORTH);
        playerSelector.setMaximumSize(playerSelector.getPreferredSize());
        containerOfTextBoxes.setMaximumSize(new Dimension(1300, 30));
        middle.add(Box.createRigidArea(new Dimension(0,45)));
        middle.add(playerSelector);
        middle.add(Box.createRigidArea(new Dimension(0,45)));
        middle.add(containerOfTextBoxes);
        middle.add(Box.createRigidArea(new Dimension(0,45)));
        middle.add(bottom);

        frame.add(containerOfEverything);
        frame.setVisible(true);

    }

    //Run this class

    public static void main(String[] args) {

        new CustomGame();

    }

}