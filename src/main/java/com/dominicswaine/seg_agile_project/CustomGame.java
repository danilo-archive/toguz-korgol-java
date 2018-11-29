package com.dominicswaine.seg_agile_project.Board;
import java.util.Arrays;
import java.util.HashMap;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CustomGame {

    private JFrame frame;
    private JPanel  containerOfEverything, containerOfBackButton, containerOfTextAndDropdown,
            containerOfSelections, containerOfSpinners, containerOfCancelTuz, containerOfSaveAndStart;
    private JButton backButton, saveButton, startButton;
    private JComboBox dropdown;
    private ButtonGroup buttonGroup;
    private JRadioButton tuzCanceller;
    private Boolean isPlayer;
    private HashMap<String, JSpinner> mapOfSpinners;
    private int[] playerValues, opponentValues;
    private String playerTuz, opponentTuz;
    private JLabel totalKorgools, playerTuzLabel, opponentTuzLabel;

    public CustomGame() {

        //Initialise the frame

        frame  = new JFrame();
        frame.setSize(new Dimension(475, 700));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //What is this?

        //Initialise necessary variables

        buttonGroup = new ButtonGroup();
        isPlayer = true;
        mapOfSpinners = new HashMap<>();
        playerValues = new int[10];
        opponentValues = new int[10];
        playerTuz = "0";
        opponentTuz = "0";

        //Make and populate the panels

        makePanels();
        populatePanels();
        containerOfEverything.add(containerOfBackButton);
        containerOfEverything.add(containerOfTextAndDropdown);
        containerOfEverything.add(containerOfSelections);
        containerOfEverything.add(containerOfSpinners);
        containerOfEverything.add(containerOfCancelTuz);
        containerOfEverything.add(containerOfSaveAndStart);
        frame.add(containerOfEverything);
        frame.setVisible(true);

    }

    public void makePanels() {

        containerOfEverything = new JPanel();
        containerOfEverything.setLayout(new BoxLayout(containerOfEverything, BoxLayout.Y_AXIS));
        containerOfBackButton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        containerOfTextAndDropdown = new JPanel();
        containerOfTextAndDropdown.setLayout(new BoxLayout(containerOfTextAndDropdown, BoxLayout.Y_AXIS));
        containerOfSelections = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 0));
        containerOfSpinners = new JPanel(new GridLayout(0,2));
        containerOfCancelTuz = new JPanel(new FlowLayout());
        containerOfSaveAndStart = new JPanel(new FlowLayout());

    }

    public void populateBackButtonContainer() {

        backButton = new JButton("Back");
        backButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        containerOfBackButton.add(backButton);

    }

    public void populateTextAndDropdownContainer() {

        JLabel title = new JLabel("Custom Game");
        title.setFont(new Font("Tahoma", Font.BOLD, 18));
        title.setAlignmentX(title.CENTER_ALIGNMENT);

        JLabel instructions = new JLabel("<html><p style=\"text-align:justify\">To begin a custom game, first use the dropdown" +
                " to select who the parameters will apply to; " +
                "you, or your opponent. You are then able to  " +
                "specify the amount of Korgools per Kazan and Hole, " +
                "and also whether a hole is a Tuz. Note that the total " +
                "number of Korgools must be exactly 162, the two Tuz's " +
                "cannot be the same, and no Tuz can be 9.</p></html>");

        instructions.setFont(new Font("Tahoma", Font.PLAIN, 14));
        instructions.setAlignmentX(instructions.CENTER_ALIGNMENT);
        containerOfTextAndDropdown.add(title);
        containerOfTextAndDropdown.setBorder(new EmptyBorder(0,20, 0, 20 ));
        containerOfTextAndDropdown.add(Box.createVerticalStrut(20));
        containerOfTextAndDropdown.add(instructions);
        containerOfTextAndDropdown.add(Box.createVerticalStrut(20));
        dropdown = new JComboBox();
        dropdown.setFont(new Font("Tahoma", Font.PLAIN, 14));
        dropdown.addItem("You (White Side)");
        dropdown.addItem("Opponent (Black Side)");
        dropdown.setMaximumSize(dropdown.getPreferredSize());
        dropdown.setToolTipText("Select who you want the settings to apply to");
        dropdown.addActionListener(e -> changePlayer());
        containerOfTextAndDropdown.add(dropdown);
        containerOfTextAndDropdown.add(Box.createVerticalStrut(20));

    }

    public void populateContainerOfSelections() {

        totalKorgools = new JLabel("Total Korgools: 0");
        totalKorgools.setFont(new Font("Tahoma", Font.BOLD, 13));
        playerTuzLabel = new JLabel("Your Tuz: None");
        playerTuzLabel.setForeground(Color.BLUE);
        playerTuzLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        opponentTuzLabel = new JLabel("Opponent Tuz: None");
        opponentTuzLabel.setForeground(Color.RED);
        opponentTuzLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        containerOfSelections.add(playerTuzLabel);
        containerOfSelections.add(totalKorgools);
        containerOfSelections.add(opponentTuzLabel);
        containerOfSelections.setBorder(new EmptyBorder(10,0, 10, 0 ));

    }

    public void populateContainerOfSpinners() {

        for (int i = 0; i < 10; i++) {

            containerOfSpinners.add(makeUnit(i));

        }

    }

    public JPanel makeUnit(int i) {

        JPanel unit = new JPanel(new BorderLayout());
        JLabel label;
        JPanel panelContainingSpinner = new JPanel(new BorderLayout());

        if (i == 0) { //For Kazan, we want no tuz radio button

            label = new JLabel("Kazan:");
            unit.add(Box.createHorizontalStrut(21), BorderLayout.EAST);

        }

        else {

            label = new JLabel("Hole " + i + ":");
            JRadioButton tuzController = new JRadioButton();
            tuzController.setName("" + i); //Setting an ID for each radio button - Hole 1 has ID 1, Hole 8 has ID 8, hole 9 and cancel have ID 0
            tuzController.addActionListener((e -> checkRadioButtons(tuzController.getName())));
            buttonGroup.add(tuzController);
            unit.add(tuzController, BorderLayout.EAST);

        }

        label.setBorder(new EmptyBorder(0, 0 ,0 , 10));
        label.setFont(new Font("Tahoma", Font.PLAIN, 14));
        SpinnerModel spinnerSettings = new SpinnerNumberModel(0, 0, 162, 1);
        JSpinner spinner = new JSpinner(spinnerSettings);
        spinner.setName("" + i); //Setting an ID for each spinner, Kazan has ID 0, Hole x has ID x
        spinner.addChangeListener(e -> obtainValue(spinner.getName()));
        mapOfSpinners.put("" + i, spinner);
        panelContainingSpinner.add(spinner, BorderLayout.CENTER);
        panelContainingSpinner.setBorder(new EmptyBorder(5,0, 5, 0 ));
        unit.add(label, BorderLayout.WEST);
        unit.add(panelContainingSpinner, BorderLayout.CENTER);
        unit.setBorder(new EmptyBorder(5,50,5,50));

        return unit;

    }

    public void populateContainerOfCancelTuz() {

        tuzCanceller = new JRadioButton();
        buttonGroup.add(tuzCanceller);
        tuzCanceller.setName("0");
        tuzCanceller.addActionListener((e -> checkRadioButtons(tuzCanceller.getName())));
        JLabel cancelTuz = new JLabel("Cancel Tuz:");
        cancelTuz.setFont(new Font("Tahoma", Font.PLAIN, 14));
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

    public void populatePanels() {

        populateBackButtonContainer();
        populateTextAndDropdownContainer();
        populateContainerOfSelections();
        populateContainerOfSpinners();
        populateContainerOfCancelTuz();
        populateContainerOfSaveAndStart();

    }

    //Listeners

    public void changePlayer() {

        isPlayer =!isPlayer;

        if (isPlayer) {

            dropdown.setToolTipText("Settings apply to you");

            //Populate spinners with old values

            for (int i = 0; i < 10; i ++) {

                JSpinner currentSpinner = mapOfSpinners.get("" + i);
                currentSpinner.setValue(playerValues[i]);
            }

        }

        else {

            dropdown.setToolTipText("Settings apply to your opponent");

            for (int i = 0; i < 10; i ++) {

                JSpinner currentSpinner = mapOfSpinners.get("" + i);
                currentSpinner.setValue(opponentValues[i]);
            }


        }

        buttonGroup.clearSelection(); //Deselects the radio button

    }

    public void obtainValue(String idOfSpinner) {

        JSpinner currentSpinner = mapOfSpinners.get(idOfSpinner);
        int currentValue = (Integer)currentSpinner.getValue();

        if (isPlayer) {

            playerValues[Integer.parseInt(idOfSpinner)] = currentValue;
            //In this array, index represents the ID of the spinner. Kazan has ID/indx 0, Hole x has ID/indx x

        }

        else {

            opponentValues[Integer.parseInt(idOfSpinner)] = currentValue;

        }

        updateContainerOfSelections();

        System.out.println("Player Values: " + Arrays.toString(playerValues));
        System.out.println("Opponent Values: " + Arrays.toString(opponentValues));

    }


    public void checkValues() {

        int sumOfPlayerAndOpponentValues = sumOfPlayerAndOpponentValues();

        if (sumOfPlayerAndOpponentValues != 162) {

            JOptionPane.showMessageDialog(frame,
                    "Total number of Korgools must be exactly 162, please change your values.",
                    "Warning",
                    JOptionPane.ERROR_MESSAGE);

        }

    }

    public void checkRadioButtons(String radioID) {

        if (radioID.equals("0")) { //If the tuz remains 0, it means that no tuz was selected

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

                buttonGroup.clearSelection();

            }

            else {

                JOptionPane.showMessageDialog(frame,
                        "The player and opponent tuz's cannot match.",
                        "Warning",
                        JOptionPane.ERROR_MESSAGE);

                buttonGroup.clearSelection();

            }

        }

        updateContainerOfSelections();

    }

    //Helper functions

    public void updateContainerOfSelections() {

        int sumOfPlayerAndOpponentValues = sumOfPlayerAndOpponentValues();
        totalKorgools.setText("Total Korgools: " + (sumOfPlayerAndOpponentValues));

        if (playerTuz.equals("0") && opponentTuz.equals("0")) {

            playerTuzLabel.setText("Your Tuz: None");
            opponentTuzLabel.setText("Opponent Tuz: None");

        }

        else if (playerTuz.equals("0") && !opponentTuz.equals("0") ) {

            playerTuzLabel.setText("Your Tuz: None");
            opponentTuzLabel.setText("Opponent Tuz: " + opponentTuz);

        }

        else if (opponentTuz.equals("0") && !playerTuz.equals("0")) {

            opponentTuzLabel.setText("Opponent Tuz: None");
            playerTuzLabel.setText("Your Tuz: " + playerTuz);

        }

        else {

            playerTuzLabel.setText("Your Tuz: " + playerTuz);
            opponentTuzLabel.setText("Opponent Tuz: " + opponentTuz);

        }

    }

    public int sumOfPlayerAndOpponentValues() {

        int sumOfPlayerValues = 0;
        int sumOfOpponentValues = 0;

        for (int i = 0; i < 10; i++) {

            sumOfPlayerValues = sumOfPlayerValues + playerValues[i];
            sumOfOpponentValues = sumOfOpponentValues + opponentValues[i];

        }

        int total = sumOfPlayerValues + sumOfOpponentValues;
        return total;

    }

    public static void main(String[] args) {

        new CustomGame();

    }

}
