package main.java.com.dominicswaine.seg_agile_project;
import java.util.HashMap;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

//Maybe have a running total of all the korgools

public class CustomGame {

    private JFrame frame;
    private JPanel containerOfEverything;
    private JPanel containerOfBackButton;
    private JPanel containerOfTextAndDropdown;
    private JPanel containerOfTextBoxes;
    private JPanel containerOfCancelTuz;
    private JPanel containerOfSaveAndStart;
    private ButtonGroup buttonGroup1;
    private ButtonGroup buttonGroup2;
    private JRadioButton tuzCanceller;
    private JButton backButton; //fix
    private JButton saveButton;
    private JButton startButton;
    private Boolean isPlayer;
    private HashMap<String, JSpinner> map;


    public CustomGame() {

        //Initialising the Frame

        frame  = new JFrame();
        frame.setSize(new Dimension(450, 700));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //What is this?

        //Initialise Button Groups and Tuz Canceller

        buttonGroup1 = new ButtonGroup();
        buttonGroup2 = new ButtonGroup();
        tuzCanceller = new JRadioButton();

        //Making the Panels

        makePanels();

        //Creating the hashmap

        map = new HashMap<>();

        //Populate the panels

        populateBackButtonContainer();
        populateTextAndDropdownContainer();
        populateContainerOfTextBoxes();
        populateContainerOfCancelTuz();
        populateContainerOfSaveAndStart();

        //Add everything to contianer of everything which you then add to frame.

        containerOfEverything.add(containerOfBackButton);
        containerOfEverything.add(containerOfTextAndDropdown);
        containerOfEverything.add(containerOfTextBoxes);
        containerOfEverything.add(containerOfCancelTuz);
        containerOfEverything.add(containerOfSaveAndStart);
        frame.add(containerOfEverything);
        frame.setVisible(true);

        //By default, settings first apply to player.

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
        title.setFont(new Font("Arial", Font.BOLD, 18));

        /*JLabel instructions = new JLabel("To begin a custom game, first use the dropdown \n " +
                "to select who the parameters will apply to; \n " +
                "you, or your opponent. You are then able to \n " +
                "specify the amount of Korgools per Kazan and Hole, \n" +
                "and also whether a hole is a Tuz.");

        containerOfTextAndDropdown.add(instructions);
        */

        containerOfTextAndDropdown.add(title);
        containerOfTextAndDropdown.add(Box.createVerticalStrut(100));

        JComboBox dropdown = new JComboBox();
        dropdown.setFont(new Font("Arial", Font.PLAIN, 14));
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

        if (i == 0) { //For Kazan, we want no tuz radio button.

            label = new JLabel("Kazan:");
            JLabel emptyLabel = new JLabel(""); //fix
            unit.add(emptyLabel, BorderLayout.EAST);

        }

        else {

            label = new JLabel("Hole " + i + ":");
            JRadioButton tuzController = new JRadioButton();
            unit.add(tuzController, BorderLayout.EAST);

        }

        SpinnerModel spinnerSettings = new SpinnerNumberModel(0, 0, 150, 1);
        JSpinner textField = new JSpinner(spinnerSettings);
        map.put(label.getText(), textField);
        panelContainingTextField.add(textField, BorderLayout.CENTER);
        panelContainingTextField.setBorder(new EmptyBorder(5,0, 5, 0 ));
        unit.add(label, BorderLayout.NORTH);
        unit.add(panelContainingTextField, BorderLayout.CENTER);
        unit.setBorder(new EmptyBorder(5,50,5,50));

        return unit;

    }

    public void populateContainerOfCancelTuz() {

        tuzCanceller = new JRadioButton();
        JLabel cancelTuz = new JLabel("Cancel Tuz:");
        containerOfCancelTuz.add(cancelTuz);
        containerOfCancelTuz.add(tuzCanceller);

    }


    public void populateContainerOfSaveAndStart() {

        saveButton = new JButton("Save");
        saveButton.setFont(new Font("Arial", Font.BOLD, 14));
        startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.BOLD, 14));
        containerOfSaveAndStart.add(saveButton);
        containerOfSaveAndStart.add(startButton);

    }

    public void changePlayer() {

        isPlayer =!isPlayer;

    }
    
    public void checkValues() {

        HashMap<String, JSpinner> incorrectFields = new HashMap<>();

        for (String key : map.keySet()) {

            if (!key.equals("Kazan:")) {

                map.get(key).getValue();

            }

        }

    }


    //Run this class

    public static void main(String[] args) {

        new CustomGame();

    }

}
