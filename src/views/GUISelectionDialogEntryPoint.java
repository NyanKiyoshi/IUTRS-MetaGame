package views;

import controllers.CRUDPersonnage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Override the {@link OutputStream}'s {@link OutputStream#write(int)} method to redirect it
 * to a given {@link JTextArea}.
 */
class TextAreaOutputStream extends OutputStream {
    private JTextArea textArea;

    /**
     * A custom output stream to write to a given text area.
     * @param textArea the component to redirect (and write) the output stream data.
     */
    TextAreaOutputStream(JTextArea textArea) {
        this.textArea = textArea;
    }

    /**
     * Clears the text area's content.
     */
    public void clear() {
        this.textArea.setText("");
    }

    /**
     * Redirect the written data to the given swing text area,
     * and scroll the text area's caret to the end of the document.
     *
     * @param bytes the bytes to write.
     */
    @Override
    public void write(int bytes) {
        textArea.append(String.valueOf((char) bytes));
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}

/**
 * The main window is a JDialog to add a proper a X11 WM_WINDOW_ROLE and WM_WINDOW_TYPE to a pop-up.
 * Preventing the window from stretching itself all over the place on tilling WMs (e.g.: i3wm).
 */
class GUISelectionDialogEntryPoint extends JDialog {
    private JTextArea outputTextArea = new JTextArea(50, 10);
    private TextAreaOutputStream outputStream = new TextAreaOutputStream(outputTextArea);

    private SpinnerModel spinnerModel = new SpinnerNumberModel(
        /* SpinnerNumberModel(default, min, max, step) */
        1, 1, BaseSelectionDialogProgram.maxScenarioNumber, 1);
    private JSpinner scenarioNumberField = new JSpinner(spinnerModel);

    /**
     * The entry point to the GUI scenario selection dialog.
     * This will redirect the stdout and stderr to a text area field
     * and prompt the user to select a scenario to run. The user will
     * be able to run multiple scenarios.
     */
    private GUISelectionDialogEntryPoint() {
        // Construct the base dialog
        super();

        // Initialize the base GUI
        JButton submitButton = new JButton("OK");
        PrintStream printStream = new PrintStream(outputStream);
        GridBagConstraints constraints = new GridBagConstraints();

        // Don't allow the user to edit the text area's content,
        // but keep allowing the user to interact with it.
        outputTextArea.setEditable(false);

        // Redirect stdout and stderr to the custom output stream,
        // going to the text area
        System.setOut(printStream);
        System.setErr(printStream);

        // Create the base GUI layout
        setLayout(new GridBagLayout());
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;

        // Make the GUI fill the whole available space
        constraints.fill = GridBagConstraints.BOTH;

        // Create the input fields
        add(new JLabel("Numéro du scénario :"), constraints);
        constraints.gridx += 1;
        add(scenarioNumberField, constraints);

        // Create the submit button
        constraints.gridx += 1;
        add(submitButton, constraints);

        // Append the text area with a scroll panel at the bottom left,
        // and make it fill the whole available width
        constraints.gridwidth = constraints.gridx + 1;
        constraints.gridx = 0;      // back to the far-left
        constraints.gridy = 1;      // new line
        constraints.weightx = 1.0;  // fill the whole x axis
        constraints.weighty = 1.0;  // fill the whole y axis

        // Wrap the text area around a scrolling pane
        add(new JScrollPane(outputTextArea), constraints);

        // Set the frame behaviors
        this.setMinimumSize(new Dimension(500, 500));
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // Set the submit button click handler
        submitButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // Clear and clean up the output redirection
                outputStream.clear();

                try {
                    // Attempt to execute the selected scenario,
                    // Should fail if:
                    //      - The user put something wrong (somehow, but should be handled);
                    //      - The scenario has a bug and crashed.
                    // If it does fail, a error dialog will be shown (see below).
                    BaseSelectionDialogProgram.getScenario((int) scenarioNumberField.getValue() - 1);
                }
                catch (Exception exc) {
                    // Retrieve the error message
                    String errorMessage = exc.getMessage();
                    if (errorMessage.isEmpty()) {
                        errorMessage = "Something went wrong (see the logs).";
                    }

                    // Show a error dialog with the exception message
                    JOptionPane.showMessageDialog(
                        null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);

                    // Log the stack trace to the redirected stderr
                    exc.printStackTrace();
                }

                // Clean up everything created to let room for other launches;
                // as the default scenarios are not doing it.
                CRUDPersonnage.tuerEtToutSupprimer();
            }
        });
    }

    /**
     * The GUI interface's entry point.
     *
     * @param args The passed command line arguments (unused).
     */
    public static void main(String[] args) {
        new GUISelectionDialogEntryPoint().setVisible(true);
    }
}
