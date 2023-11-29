package view;

import controller.MatrixSwingController;
import view.enums.MatrixOperationActionEnum;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents a Swing view for matrix operations.
 * It includes options to create matrices, perform operations like determinant and transposition,
 * and display the results.
 *
 * @author Bartek
 * @version 1.0
 */
public class MatrixOperationSwingView extends JPanel {

    /**
     * The controller handling matrix operations.
     */
    private MatrixSwingController controller;

    /**
     * Panel for selecting matrix size.
     */
    private JPanel matrixSizePannel;

    /**
     * ComboBox for selecting rows.
     */
    private JComboBox rowComboBox;

    /**
     * ComboBox for selecting columns.
     */
    private JComboBox columnsComboBox;

    /**
     * Button to create a matrix.
     */
    private JButton createMatrixButton;

    /**
     * Button to return or close the application.
     */
    private JButton returnButton;

    /**
     * Panel to contain the matrix input fields.
     */
    private JPanel matrixGridPannel;

    /**
     * Panel to display the matrix grid.
     */
    private JPanel matrixGrid;

    /**
     * Text fields representing matrix input elements.
     */
    private JTextField[][] matrixInputFields;

    /**
     * Panel for matrix operation buttons.
     */
    private JPanel operationPanel;

    /**
     * Button to perform determinant calculation.
     */
    private JButton determinantButton;

    /**
     * Button to perform matrix transposition.
     */
    private JButton transposeButton;

    /**
     * Panel to display the result of matrix operations.
     */
    private JPanel resultPanel;

    /**
     * Number of rows in the matrix.
     */
    private int numberOfRows;

    /**
     * Number of columns in the matrix.
     */
    private int numberOfColumns;

    /**
     * Initializes the MatrixOperationSwingView with a provided MatrixSwingController.
     *
     * @param controller The MatrixSwingController handling matrix operations.
     */
    public MatrixOperationSwingView(MatrixSwingController controller) {
        this.controller = controller;
    }

    /**
     * Initializes the view by setting layout and preparing the size selection panel.
     */
    public void init() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        prepareSizeSelectionPanel();
    }


    /**
     * Prepares the panel for selecting matrix size with row and column ComboBoxes.
     * Sets up buttons to create a matrix and close the application.
     */
    private void prepareSizeSelectionPanel() {
        matrixSizePannel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Integer[] numbers = {1, 2, 3, 4, 5};

        rowComboBox = new JComboBox<>(numbers);
        rowComboBox.setPreferredSize(new Dimension(200, 75));

        columnsComboBox = new JComboBox<>(numbers);
        columnsComboBox.setPreferredSize(new Dimension(200, 75));

        createMatrixButton = new JButton("Create matrix");
        createMatrixButton.setPreferredSize(new Dimension(200, 75));

        returnButton = new JButton("Close");
        returnButton.setPreferredSize(new Dimension(75, 75));

        createMatrixButton.addActionListener(e -> {
            this.numberOfRows = rowComboBox.getSelectedItem() != null ? (int) rowComboBox.getSelectedItem() : 0;
            this.numberOfColumns = columnsComboBox.getSelectedItem() != null ? (int) columnsComboBox.getSelectedItem() : 0;
            reloadMatrix();
            controller.addElementToHistory("Create Matrix button was pressed");
        });

        returnButton.addActionListener(e -> {
            controller.addElementToHistory("Exit button was pressed");
            this.controller.exitApp();
        });

        matrixSizePannel.add(rowComboBox);
        matrixSizePannel.add(columnsComboBox);
        matrixSizePannel.add(createMatrixButton);
        matrixSizePannel.add(returnButton);

        add(matrixSizePannel);
    }

    /**
     * Prepares the panel for inputting matrix elements based on the selected size.
     */
    private void prepareMatrixInputPanel() {
        matrixGridPannel = new JPanel(new FlowLayout());
        matrixGrid = new JPanel(new GridLayout(this.numberOfRows, this.numberOfColumns, 20, 20));
        matrixInputFields = new JTextField[this.numberOfRows][this.numberOfColumns];

        Font font = new Font("Arial", Font.PLAIN, 18);

        for (int y = 0; y < this.numberOfRows; y++) {
            for (int x = 0; x < this.numberOfColumns; x++) {
                JTextField field = new JTextField(5);
                field.setFont(font);
                matrixInputFields[y][x] = field;
                matrixGrid.add(matrixInputFields[y][x]);
            }
        }
        matrixGrid.setSize(50, 50);
        matrixGridPannel.add(matrixGrid);
        add(matrixGridPannel);
    }

    /**
     * Prepares the panel for matrix operation buttons (determinant and transpose).
     */
    private void prepareMatrixOperationPanel() {
        operationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        determinantButton = new JButton("Determinant");
        determinantButton.setPreferredSize(new Dimension(300, 75));
        transposeButton = new JButton("Transpose");
        transposeButton.setPreferredSize(new Dimension(300, 75));
        operationPanel.add(determinantButton);
        operationPanel.add(transposeButton);

        determinantButton.addActionListener(e -> {

            controller.addElementToHistory("Determinant button was pressed");
            if (this.numberOfColumns != this.numberOfRows) {
                JOptionPane.showMessageDialog(null, "Matrix has to be square!", "Invalid Dimensions",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                controller.matrixOperation(MatrixOperationActionEnum.DETERMINANT,
                        numberOfRows, numberOfColumns, readValuefromTextBoxes());
            }
        });

        transposeButton.addActionListener(e -> {
            controller.addElementToHistory("Transposition button was pressed");
            controller.matrixOperation(MatrixOperationActionEnum.TRANSPOSITION,
                    numberOfRows, numberOfColumns, readValuefromTextBoxes());
        });

        add(operationPanel);
    }

    /**
     * Reads values from the text fields representing matrix elements.
     *
     * @return List of strings representing matrix element values.
     */
    private java.util.List<String> readValuefromTextBoxes() {

        java.util.List<String> readValue = new ArrayList<>();

        for (int y = 0; y < numberOfRows; y++) {
            for (int x = 0; x < numberOfColumns; x++) {

                readValue.add(matrixInputFields[y][x].getText());
            }
        }

        return readValue;
    }

    /**
     * Displays the result of transposition operation in the GUI.
     *
     * @param result The transposed matrix elements as a List of Lists of Doubles.
     */
    public void presentTranspositionResult(List<List<Double>> result) {

        if (resultPanel != null) {
            remove(resultPanel);
            resultPanel.removeAll();
        }

        resultPanel = new JPanel(new FlowLayout());

        JPanel matrixResultGird = new JPanel(new GridLayout(this.numberOfColumns, this.numberOfRows, 20, 20));

        Font font = new Font("Arial", Font.PLAIN, 18);

        for (int y = 0; y < this.numberOfColumns; y++) {
            for (int x = 0; x < this.numberOfRows; x++) {
                JTextField field = new JTextField(5);
                field.setFont(font);
                field.setText(result.get(y).get(x).toString());
                field.setEditable(false);
                matrixResultGird.add(field);
            }
        }

        JLabel titleLabel = new JLabel("Result:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 15));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        resultPanel.add(titleLabel);
        resultPanel.add(matrixResultGird);
        add(resultPanel);

    }

    /**
     * Displays the result of determinant calculation in the GUI.
     *
     * @param result The determinant value as a Double.
     */
    public void presentDeterminantResult(Double result) {

        if (resultPanel != null) {
            remove(resultPanel);
            resultPanel.removeAll();
        }

        resultPanel = new JPanel(new FlowLayout());

        Font font = new Font("Arial", Font.PLAIN, 30);

        JTextField field = new JTextField(5);
        field.setFont(font);
        field.setText(result.toString());
        field.setEditable(false);

        JLabel titleLabel = new JLabel("Result:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 15));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        resultPanel.add(titleLabel);
        resultPanel.add(field);
        add(resultPanel);

    }

    /**
     * Reloads the matrix view, clearing and preparing it for new matrix input or operations.
     */
    private void reloadMatrix() {
        if (matrixGrid != null) {
            Arrays.fill(matrixInputFields, null);
            matrixGridPannel.removeAll();
            matrixGrid.removeAll();
            operationPanel.removeAll();
            removeAll();
            add(matrixSizePannel);
        }
        prepareMatrixInputPanel();
        prepareMatrixOperationPanel();
        revalidate();
    }
}
