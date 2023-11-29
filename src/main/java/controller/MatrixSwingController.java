package controller;

import model.Matrix;
import model.MatrixService;
import model.exceptions.MatrixParseException;
import view.MatrixMainMenuSwingView;
import view.MatrixOperationSwingView;
import view.enums.MainMenuActionEnum;
import view.enums.MatrixOperationActionEnum;

import javax.swing.*;
import java.awt.*;

/**
 * Controller class managing the Swing-based Matrix Calculator application.
 *
 * @author Bartek
 * @version 1.0
 */
public class MatrixSwingController extends JFrame {

    /**
     * Tabbed pane to manage different views.
     */
    private JTabbedPane tabbedPane;

    /**
     * Panel representing the current active view.
     */
    private JPanel currentPanel;

    /**
     * Panel to display history.
     */
    private JPanel history;

    /**
     * Model to manage the history list.
     */
    private DefaultListModel<String> listModel;

    /**
     * List to display the history of actions.
     */
    private JList<String> historyList;

    /**
     * Main menu view for the matrix calculator.
     */
    private MatrixMainMenuSwingView mainMenu;

    /**
     * Matrix operation view for the matrix calculator.
     */
    private MatrixOperationSwingView operationView;

    /**
     * Service class handling matrix operations.
     */
    private MatrixService matrixService;

    /**
     * The current matrix instance.
     */
    private Matrix matrix;

    /**
     * Initializes the MatrixSwingController setting up the JFrame and necessary components.
     */
    public MatrixSwingController() {
        setTitle("Matrix Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        matrixService = new MatrixService();
        tabbedPane = new JTabbedPane();
        history = new JPanel(new FlowLayout());
        add(tabbedPane);


        listModel = new DefaultListModel<>();
        listModel.addElement("Program start");

        historyList = new JList<>(listModel);

        JScrollPane scrollPane = new JScrollPane(historyList);
        scrollPane.setPreferredSize(new Dimension(800, 600));

        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        history.add(scrollPane);

    }

    /**
     * Exits the application.
     */
    public void exitApp() {
        System.exit(0);
    }

    /**
     * Performs the matrix operation based on the specified action, size, and data.
     *
     * @param action      The type of matrix operation to perform.
     * @param sizeRows    The number of rows in the matrix.
     * @param sizeColumns The number of columns in the matrix.
     * @param data        List of strings representing matrix data.
     */
    public void matrixOperation(MatrixOperationActionEnum action, int sizeRows, int sizeColumns, java.util.List<String> data) {
        if (!prepareMatrix(sizeRows, sizeColumns, data)) {
            return;
        }
        switch (action) {
            case DETERMINANT:
                handleDeterminant();
                break;
            case TRANSPOSITION:
                handleTransposition();
                break;
        }
    }

    /**
     * Adds an element to the history list.
     *
     * @param element The element to be added to the history.
     */
    public void addElementToHistory(String element) {
        listModel.addElement(element);
        historyList.setModel(listModel);
        history.repaint();
    }

    /**
     * Handles the transposition operation on the matrix view.
     */
    private void handleTransposition() {
        operationView.presentTranspositionResult(matrixService.transposeMatrix(this.matrix).getMat());
        revalidate();
    }

    /**
     * Handles the determinant operation on the matrix view.
     */
    private void handleDeterminant() {
        operationView.presentDeterminantResult(this.matrixService.calculateDeterminant(this.matrix));
        revalidate();
    }

    /**
     * Prepares the matrix based on the provided size and data.
     *
     * @param sizeRows    The number of rows in the matrix.
     * @param sizeColumns The number of columns in the matrix.
     * @param data        List of strings representing matrix data.
     * @return True if matrix preparation succeeds, false otherwise.
     */
    private boolean prepareMatrix(int sizeRows, int sizeColumns, java.util.List<String> data) {
        try {
            this.matrix = new Matrix(sizeRows, sizeColumns, data);
            return true;
        } catch (MatrixParseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Invalid Character", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }

    /**
     * Switches from the main menu view based on the provided action.
     *
     * @param action The action to be performed after switching from the main menu.
     */
    public void switchFromMainMenuView(MainMenuActionEnum action) {
        switch (action) {
            case START:
                showOperationView();
                break;
            case EXIT:
                exitApp();
                break;
        }
    }

    /**
     * Displays the main menu view in the application.
     */
    private void showMainMenu() {
        if (currentPanel != null) {
            tabbedPane.remove(currentPanel);
            tabbedPane.remove(history);
        }

        mainMenu = new MatrixMainMenuSwingView(this);
        mainMenu.init();

        currentPanel = mainMenu;

        tabbedPane.add("Main page", currentPanel);

        tabbedPane.add("History", history);

        revalidate();
    }

    /**
     * Displays the operation view for matrix calculations in the application.
     */
    private void showOperationView() {

        tabbedPane.remove(currentPanel);
        tabbedPane.remove(history);

        operationView = new MatrixOperationSwingView(this);
        operationView.init();
        this.add(operationView);

        currentPanel = operationView;

        tabbedPane.add("Main page", currentPanel);
        tabbedPane.add("History", history);

        revalidate();
    }

    /**
     * Runs the Swing-based Matrix Calculator application by displaying the main menu view.
     */
    public void runSwingApp() {
        showMainMenu();
    }
}
