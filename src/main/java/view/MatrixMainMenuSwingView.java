package view;

import controller.MatrixSwingController;
import view.enums.MainMenuActionEnum;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the main menu view for a matrix calculator application,
 * allowing users to start or quit the program.
 *
 * @author Bartek
 * @version 1.0
 */
public class MatrixMainMenuSwingView extends JPanel {

    /**
     * Button to start the matrix calculator application.
     */
    private JButton startButton;

    /**
     * Button to quit or exit the matrix calculator application.
     */
    private JButton quitButton;

    /**
     * Label displaying the title of the matrix calculator.
     */
    private JLabel titleLabel;

    /**
     * Layout for organizing the components in the view.
     */
    private GridLayout layout;

    /**
     * The MatrixSwingController associated with this view.
     */
    private final MatrixSwingController controller;

    /**
     * Initializes the MatrixMainMenuSwingView with a provided MatrixSwingController.
     *
     * @param controller The MatrixSwingController handling interactions and view transitions.
     */
    public MatrixMainMenuSwingView(MatrixSwingController controller) {
        this.controller = controller;

        titleLabel = new JLabel("Matrix calculator");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        startButton = new JButton("Start");
        quitButton = new JButton("Quit");

        layout = new GridLayout(2, 1);

        setLayout(layout);
    }

    /**
     * Initializes the main menu view by setting up buttons and their actions.
     * Users can start or quit the application by clicking the corresponding buttons.
     */
    public void init() {
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.add(startButton);
        buttonPanel.add(quitButton);

        add(titleLabel);
        add(buttonPanel);

        startButton.addActionListener(e -> {
            controller.addElementToHistory("Button Start pressed");
            controller.switchFromMainMenuView(MainMenuActionEnum.START);
        });

        quitButton.addActionListener(e -> {
            controller.addElementToHistory("Button Exit pressed");
            controller.switchFromMainMenuView(MainMenuActionEnum.EXIT);
        });

        setVisible(true);
    }
}