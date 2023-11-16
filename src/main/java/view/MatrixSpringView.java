package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * Matrix Spring GUI class
 *
 * @author Bartek
 * @version 2.0
 */
public class MatrixSpringView {

    private JFrame frame;
    private JPanel mainPanel; //Grid for all components

    private JPanel matrixSizePannel; //Grid for selecting matrix size - flow
    private JComboBox rowComboBox;
    private JComboBox columnsComboBox;
    private JButton createMatrix;


    private JPanel matrixGridPannel;
    private JPanel matrixGrid;
    private JTextField[][] matrixInputFields;

    private JPanel operationPanel;
    private JButton determinantButton;
    private JButton transposeButton;

    private void prepareSizeSelectionPanel() {
        matrixSizePannel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Integer[] numbers = {1,2,3,4,5};

        rowComboBox = new JComboBox<>(numbers);
        rowComboBox.setPreferredSize(new Dimension(200, 75));

        columnsComboBox = new JComboBox<>(numbers);
        columnsComboBox.setPreferredSize(new Dimension(200, 75));

        createMatrix = new JButton("Create matrix");
        createMatrix.setPreferredSize(new Dimension(200, 75));

        createMatrix.addActionListener(e -> {
            showMatrix( (int) rowComboBox.getSelectedItem(), (int) columnsComboBox.getSelectedItem());
        });

        matrixSizePannel.add(rowComboBox);
        matrixSizePannel.add(columnsComboBox);
        matrixSizePannel.add(createMatrix);

        mainPanel.add(matrixSizePannel);

    }

    private void prepareMatrixInputPanel(int numberOfRows, int numberOfColumns) {
        matrixGridPannel = new JPanel(new FlowLayout());
        matrixGrid = new JPanel(new GridLayout(numberOfRows, numberOfColumns, 20, 20));
        matrixInputFields = new JTextField[numberOfRows][numberOfColumns];

        for(int y = 0; y < numberOfRows; y++) {
            for(int x = 0; x < numberOfColumns; x++) {
                JTextField field = new JTextField(15);
                matrixInputFields[y][x] = field;
                matrixGrid.add(matrixInputFields[y][x]);
            }
        }
        matrixGrid.setSize(50, 50);
        matrixGridPannel.add(matrixGrid);
        mainPanel.add(matrixGridPannel);
    }

    private void prepareMatrixOperationPanel() {
        operationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        determinantButton = new JButton("Determinant");
        determinantButton.setPreferredSize(new Dimension(300, 75));
        transposeButton = new JButton("Transpose");
        transposeButton.setPreferredSize(new Dimension(300, 75));
        operationPanel.add(determinantButton);
        operationPanel.add(transposeButton);

        determinantButton.addActionListener(e -> {
            //Send to controller
        });

        transposeButton.addActionListener(e -> {
            //Send to controller
        });

        mainPanel.add(operationPanel);
    }

    private void showMatrix(int numberOfRows, int numberOfColumns) {
        if(matrixGrid != null) {
            Arrays.fill(matrixInputFields, null);
            matrixGridPannel.removeAll();
            matrixGrid.removeAll();
            operationPanel.removeAll();
            mainPanel.removeAll();
            mainPanel.add(matrixSizePannel);
        }
        prepareMatrixInputPanel(numberOfRows, numberOfColumns);
        prepareMatrixOperationPanel();
        frame.revalidate();
    }


    public void run() {
        JFrame.setDefaultLookAndFeelDecorated(false);

        frame = new JFrame("Matrix Calculator");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(800, 600);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));


        prepareSizeSelectionPanel();

        frame.add(mainPanel);

        frame.setVisible(true);
    }

}
