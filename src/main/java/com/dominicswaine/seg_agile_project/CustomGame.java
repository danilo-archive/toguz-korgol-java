package main.java.com.dominicswaine.seg_agile_project;
import java.util.Arrays;
import java.util.HashMap;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CustomGame {

    private JFrame frame;
    private JPanel containerOfEverything;
    private JPanel containerOfBackButton;
    private JPanel containerOfTextAndDropdown;
    private JPanel containerOfTextBoxes;
    private JPanel containerOfCancelTuz;
    private JPanel containerOfSaveAndStart;
    private ButtonGroup buttonGroup;
    private JRadioButton tuzCanceller;
    private JButton backButton; 
    private JButton saveButton;
    private JButton startButton;
    private Boolean isPlayer;
    private HashMap<String, JSpinner> map; 
    private JComboBox dropdown;
    private int[] playerValues;
    private int[] opponentValues;
    private String playerTuz;
    private String opponentTuz;


    public CustomGame() {

        frame  = new JFrame();
        frame.setSize(new Dimension(450, 700));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
        buttonGroup = new ButtonGroup();
        makePanels();
        map = new HashMap<>();
        playerValues = new int[10];
        opponentValues = new int[10];
        populateBackButtonContainer();
        populateTextAndDropdownContainer();
        populateContainerOfTextBoxes();
        populateContainerOfCancelTuz();
        populateContainerOfSaveAndStart();
        containerOfEverything.add(containerOfBackButton);
        containerOfEverything.add(containerOfTextAndDropdown);
        containerOfEverything.add(containerOfTextBoxes);
        containerOfEverything.add(containerOfCancelTuz);
        containerOfEverything.add(containerOfSaveAndStart);
        frame.add(containerOfEverything);
        frame.setVisible(true);
        isPlayer = true;
        
    }

    public void makePanels() {

        containerOfEverything = new JPanel();
        containerOfEverything.setLayout(new BoxLayout(containerOfEverything, BoxLayout.Y_AXIS));
        containerOfBackButton = new JPanel(new FlowLayout());
        containerOfTextAndDropdown = new JPanel();
        containerOfTextAndDropdown.setLayout(new BoxLayout(containerOfTextAndDropdown, BoxLayout.Y_AXIS));
        containerOfTextBoxes = new JPanel(new GridLayout(0,2));
        containerOfCancelTuz = new JPanel(new FlowLayout());
        containerOfSaveAndStart = new JPanel(new FlowLayout());

    }

    public void populateBackButtonContainer() {

        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        containerOfBackButton.setBorder(new EmptyBorder(0,0,0,345));
        containerOfBackButton.add(backButton);

    }

    public void populateTextAndDropdownContainer() {

        JLabel title = new JLabel("Custom Game");
        title.setAlignmentX(title.CENTER_ALIGNMENT);
        title.setFont(new Font("Tahoma", Font.BOLD, 18));

        /*JLabel instructions = new JLabel("To begin a custom game, first use the dropdown \n " +
                "to select who the parameters will apply to; \n " +
                "you, or your opponent. You are then able to \n " +
                "specify the amount of Korgools per Kazan and Hole, \n" +
                "and also whether a hole is a Tuz.");

        containerOfTextAndDropdown.add(instructions);
        */

        containerOfTextAndDropdown.add(title);
        containerOfTextAndDropdown.add(Box.createVerticalStrut(150));
        dropdown = new JComboBox();
        dropdown.setFont(new Font("Tahoma", Font.PLAIN, 14));
        dropdown.addItem("You");
        dropdown.addItem("Opponent");
        dropdown.setMaximumSize(dropdown.getPreferredSize());
        dropdown.setToolTipText("Select who you want the settings to apply to");
        dropdown.addActionListener(e -> changePlayer());
        containerOfTextAndDropdown.add(dropdown);
        containerOfTextAndDropdown.add(Box.createVerticalStrut(20));

    }

    public void populateContainerOfTextBoxes() {

        for (int i = 0; i < 10; i++) {

            containerOfTextBoxes.add(makeUnit(i));

        }

    }

    public JPanel makeUnit(int i) {

        JPanel unit = new JPanel(new BorderLayout());
        JLabel label;
        JPanel panelContainingTextField = new JPanel(new BorderLayout());

        if (i == 0) { 

            label = new JLabel("Kazan:");
            JLabel emptyLabel = new JLabel(""); 
            unit.add(emptyLabel, BorderLayout.EAST);

        }

        else {

            label = new JLabel("Hole " + i + ":");
            JRadioButton tuzController = new JRadioButton();
            tuzController.setName("" + i); 
            tuzController.addActionListener((e -> checkRadioButtons(tuzController.getName())));
            buttonGroup.add(tuzController);
            unit.add(tuzController, BorderLayout.EAST);

        }

        SpinnerModel spinnerSettings = new SpinnerNumberModel(0, 0, 162, 1);
        JSpinner textField = new JSpinner(spinnerSettings);
        textField.setName("" + i); //Setting an ID for each spinner
        textField.addChangeListener(e -> obtainValue(textField.getName()));
        map.put("" + i, textField);
        panelContainingTextField.add(textField, BorderLayout.CENTER);
        panelContainingTextField.setBorder(new EmptyBorder(5,0, 5, 0 ));
        unit.add(label, BorderLayout.NORTH);
        unit.add(panelContainingTextField, BorderLayout.CENTER);
        unit.setBorder(new EmptyBorder(5,50,5,50));

        return unit;

    }

    public void populateContainerOfCancelTuz() {

        tuzCanceller = new JRadioButton();
        buttonGroup.add(tuzCanceller);
        tuzCanceller.setName("0");
        tuzCanceller.addActionListener((e -> checkRadioButtons(tuzCanceller.getName())));
        JLabel cancelTuz = new JLabel("Cancel Tuz:");
        containerOfCancelTuz.add(cancelTuz);
        containerOfCancelTuz.add(tuzCanceller);

    }


    public void populateContainerOfSaveAndStart() {

        saveButton = new JButton("Save");
        saveButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        startButton = new JButton("Start");
        startButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        startButton.addActionListener(e -> checkValues());
        containerOfSaveAndStart.add(saveButton);
        containerOfSaveAndStart.add(startButton);

    }

    public void changePlayer() {

        isPlayer =!isPlayer;

        if (isPlayer) {

            dropdown.setToolTipText("Settings apply to you");

            for (int i = 0; i < 10; i ++) {

                JSpinner currentSpinner = map.get("" + i);
                currentSpinner.setValue(playerValues[i]);
            }

        }

        else {

            dropdown.setToolTipText("Settings apply to your opponent");

            for (int i = 0; i < 10; i ++) {

                JSpinner currentSpinner = map.get("" + i);
                currentSpinner.setValue(opponentValues[i]);
            }


        }

    }

    public void checkValues() {

        int sumOfPlayerValues = 0;
        int sumOfOpponentValues = 0;

        for (int i = 0; i < 10; i++) {

            sumOfPlayerValues = sumOfPlayerValues + playerValues[i];
            sumOfOpponentValues = sumOfOpponentValues + opponentValues[i];

        }

        if (sumOfPlayerValues + sumOfOpponentValues > 162) {

            JOptionPane.showMessageDialog(frame,
                    "Total number of Korgools cannot exceed 162, please change your values.",
                    "Warning",
                    JOptionPane.ERROR_MESSAGE);

        }

    }


    public void obtainValue(String idOfSpinner) { 

        JSpinner currentSpinner = map.get(idOfSpinner);
        int currentValue = (Integer)currentSpinner.getValue();

        if (isPlayer) {

            playerValues[Integer.parseInt(idOfSpinner)] = currentValue;

        }

        else {

            opponentValues[Integer.parseInt(idOfSpinner)] = currentValue;

        }

        System.out.println("Player Values: " + Arrays.toString(playerValues));
        System.out.println("Opponent Values: " + Arrays.toString(opponentValues));

    }

    public void checkRadioButtons(String radioID) {

        if (radioID.equals("0")) { 

            if (isPlayer) {

                playerTuz = "0";
                System.out.println("PlayerTuz: " + playerTuz);

            }

            else {

                opponentTuz = "0";
                System.out.println("OpponentTuz: " + opponentTuz);

            }

        }

        else if (isPlayer && !radioID.equals(opponentTuz)  && !radioID.equals("9")) {

            playerTuz = radioID;
            System.out.println("PlayerTuz: " + playerTuz);

        }

        else if (!isPlayer && !radioID.equals(playerTuz) && !radioID.equals("9")) {

            opponentTuz = radioID;
            System.out.println("OpponentTuz: " + opponentTuz);

        }

        else {

            if (radioID.equals("9")) {

                JOptionPane.showMessageDialog(frame,
                        "The tuz cannot be 9.",
                        "Warning",
                        JOptionPane.ERROR_MESSAGE);

            }

            else {

                JOptionPane.showMessageDialog(frame,
                        "The player and opponent tuz's cannot match.",
                        "Warning",
                        JOptionPane.ERROR_MESSAGE);

            }

        }

    }


    public static void main(String[] args) {

        new CustomGame();

    }

}
