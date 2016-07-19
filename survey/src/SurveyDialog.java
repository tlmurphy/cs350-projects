/*----------------|
|   CS350         |
|   Project #2    |
|   Trevor Murphy |
|-----------------*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * SurveyDialog serves as the Dialog window
 * for inputting survey information.
 */
public class SurveyDialog extends JDialog implements ActionListener {
    private JTextField myTextField;
    private JButton submit;
    private JButton cancelButton;
    private ButtonGroup carrierGroup = new ButtonGroup();
    private JRadioButton[] carriers = new JRadioButton[5];
    private ButtonGroup ratingGroup = new ButtonGroup();
    private JRadioButton[] ratings = new JRadioButton[5];
    private JCheckBox voice;
    private JCheckBox textMessaging;
    private JCheckBox dataPlan;
    private CSample surveyResults;
    private DefaultListModel model;
    private boolean modifyFlag; // Used to determine if modifying entry.
    private int index;
    private static ArrayList samples = new ArrayList<CSample>(); // Keep track of survey samples.

    public SurveyDialog(JFrame owner, DefaultListModel model, boolean modifyFlag, int index) {
        setTitle("Add a Survey Sample");
        this.model = model;
        this.modifyFlag = modifyFlag;
        this.index = index;

        // If not modifying create new sample
        if (!modifyFlag) {
            surveyResults = new CSample();
            samples.add(surveyResults);
        } else {
            surveyResults = (CSample) samples.get(index); // Get a previous made sample
        }

        Container c = getContentPane();
        c.setLayout(null);

        addLabels(c);
        addButtons(c);

        myTextField = new JTextField(surveyResults.getZipCode());
        myTextField.setSize(70, 30);
        myTextField.setLocation(120, 80);
        c.add(myTextField);

        setSize(600, 600);
        setLocationRelativeTo(owner);
        setVisible(true);
    }

    // This constructor is used when a sample is being removed.
    public SurveyDialog(boolean beingRemoved, int index) {
        if (beingRemoved) {
            samples.remove(index);
        }
    }

    // This constructor is used when the Jlist is being cleared.
    public SurveyDialog() {
        samples.clear();
    }

    private void addLabels(Container c) {
        MyLabel idLabel = new MyLabel("Participant ID");
        idLabel.setLocation(20, 20);
        c.add(idLabel);

        MyLabel displayedID = new MyLabel(surveyResults.getId());
        displayedID.setLocation(120, 20);
        displayedID.setForeground(Color.MAGENTA);
        c.add(displayedID);

        MyLabel zipCodeLabel = new MyLabel("Zipcode");
        zipCodeLabel.setLocation(20, 70);
        c.add(zipCodeLabel);

        MyLabel carrierLabel = new MyLabel("Carrier");
        carrierLabel.setLocation(20, 120);
        c.add(carrierLabel);

        MyLabel ratingLabel = new MyLabel("Rating");
        ratingLabel.setLocation(350, 120);
        c.add(ratingLabel);

        MyLabel servicesLabel = new MyLabel("What services do you subscribe?");
        servicesLabel.setLocation(20, 400);
        c.add(servicesLabel);
    }

    private void addButtons(Container c) {
        carriers[0] = new JRadioButton("AT&T");
        carriers[1] = new JRadioButton("T-Mobile");
        carriers[2] = new JRadioButton("Verizon");
        carriers[3] = new JRadioButton("Sprint");
        carriers[4] = new JRadioButton("Others");

        int y = 170;
        for (JRadioButton carrier : carriers) {
            carrier.setLocation(40, y);
            carrier.setSize(100, 20);
            c.add(carrier);
            carrierGroup.add(carrier);
            y += 40;
        }

        ratings[0] = new JRadioButton("Excellent(E)");
        ratings[1] = new JRadioButton("Very Good(V)");
        ratings[2] = new JRadioButton("Good(G)");
        ratings[3] = new JRadioButton("Fair(F)");
        ratings[4] = new JRadioButton("Poor(P)");

        int y2 = 170;
        for (JRadioButton rating : ratings) {
            rating.setLocation(370, y2);
            rating.setSize(200, 20);
            c.add(rating);
            ratingGroup.add(rating);
            y2 += 40;
        }

        voice = new JCheckBox("Voice(V)");
        voice.setSize(200, 20);
        voice.setLocation(40, 460);

        textMessaging = new JCheckBox("Text Messaging(T)");
        textMessaging.setSize(200, 20);
        textMessaging.setLocation(240, 460);

        dataPlan = new JCheckBox("Data Plan(D)");
        dataPlan.setSize(200, 20);
        dataPlan.setLocation(440, 460);

        c.add(voice);
        c.add(textMessaging);
        c.add(dataPlan);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        cancelButton.setSize(100, 30);
        cancelButton.setLocation(350, 510);
        c.add(cancelButton);

        submit = new JButton("Submit");
        submit.addActionListener(this);
        submit.setSize(100, 30);
        submit.setLocation(120, 510);
        c.add(submit);

        // If modifying, initialize the appropriate components.
        if (modifyFlag) {
            for (JRadioButton b : carriers) {
                if (b.getText().equals(surveyResults.getCarrier())) {
                    b.setSelected(true);
                }
            }

            for (JRadioButton b : ratings) {
                if (surveyResults.getRating() == b.getText().charAt(0)) {
                    b.setSelected(true);
                }
            }

            if (surveyResults.getServices().indexOf('V') != -1) { voice.setSelected(true); }
            if (surveyResults.getServices().indexOf('T') != -1) { textMessaging.setSelected(true); }
            if (surveyResults.getServices().indexOf('D') != -1) { dataPlan.setSelected(true); }
        }
        setResizable(false);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==submit) {
            String errorString = ""; // Build error string
            boolean noZip = myTextField.getText().length() != 5 || !isNumeric(myTextField.getText());
            boolean noCarrier = carrierGroup.getSelection() == null;
            boolean noRating = ratingGroup.getSelection() == null;

            if(noZip || noCarrier || noRating) {
                if (noZip) {
                    errorString = errorString + "ERROR: This is not a valid zip code!\n";
                }

                if (noCarrier) {
                    errorString = errorString + "ERROR: No carrier was selected!\n";
                }

                if (noRating) {
                    errorString = errorString + "ERROR: No rating was selected!\n";
                }

                JOptionPane.showMessageDialog(this,
                        errorString,
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                surveyResults.setZipCode(myTextField.getText());
                for (JRadioButton b : carriers) {
                    if (b.isSelected()) {
                        surveyResults.setCarrier(b.getText());
                    }
                }

                for (JRadioButton b : ratings) {
                    if (b.isSelected()) {
                        surveyResults.setRating(b.getText().charAt(0));
                    }
                }

                if   (voice.isSelected()) { surveyResults.setVoice(true); }
                else { surveyResults.setVoice(false); }

                if   (textMessaging.isSelected()) { surveyResults.setTextMessaging(true); }
                else { surveyResults.setTextMessaging(false); }

                if   (dataPlan.isSelected()) { surveyResults.setDataPlan(true); }
                else { surveyResults.setDataPlan(false); }

                if (modifyFlag) { model.setElementAt(surveyResults.returnedSample(), index); } else {
                    model.addElement(surveyResults.returnedSample()); }

                setVisible(false);
            }
        }
        else if(e.getSource()==cancelButton) {
            if (!modifyFlag) {
                samples.remove(samples.size() - 1);
                surveyResults.canceledEntry();
            }
            setVisible(false);
        }
    }

    /**
     * Use regex to determine if string is numeric
     * @param str input string
     * @return true if numeric, false otherwise.
     */
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    /**
     * Build CSample based on input file (reads from one line of file).
     * @param line line of file to be processed.
     */
    public void processFile(String line) {
        String idString      = line.substring(0, 6).trim();
        String zipString     = line.substring(25, 30).trim();
        String carrierString = line.substring(49, 60).trim();
        char ratingChar      = line.substring(70, 78).trim().charAt(0);
        String serviceString = line.substring(90).trim();

        surveyResults = new CSample();
        surveyResults.setId(idString);
        surveyResults.setZipCode(zipString);
        surveyResults.setCarrier(carrierString);
        surveyResults.setRating(ratingChar);
        if (serviceString.indexOf('V') != -1) { surveyResults.setVoice(true); }
        if (serviceString.indexOf('T') != -1) { surveyResults.setTextMessaging(true); }
        if (serviceString.indexOf('D') != -1) { surveyResults.setDataPlan(true); }
        samples.add(surveyResults);
    }
}