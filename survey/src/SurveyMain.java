/*----------------|
|   CS350         |
|   Project #2    |
|   Trevor Murphy |
|-----------------*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

/**
 * Main class for Survey Project.
 * Extends JFrame to build main window.
 */
public class SurveyMain extends JFrame implements ActionListener {
    MyLabel  idLabel;
    MyLabel  zipCodeLabel;
    MyLabel  carrierLabel;
    MyLabel  ratingLabel;
    MyLabel  servicesLabel;
    JButton addButton;
    JButton modifyButton;
    JButton removeButton;
    JButton removeAllButton;
    JButton saveButton;
    JButton openButton;
    DefaultListModel model = new DefaultListModel();
    JList list;
    JScrollPane scrollPane;

    public SurveyMain() {
        super("Cell Service Servey");
        init();
    }

    /**
     * Used if argument file is found.
     * @param argFile
     */
    public SurveyMain(File argFile) {
        super("Cell Service Survey");
        init();
        try {
            readFile(argFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init() {
        Container c = getContentPane();
        c.setLayout(null);

        list = new JList(model);
        list.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12)); // Monospace lines up correctly
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(0);
        scrollPane = new JScrollPane(list);
        scrollPane.setSize(755, 420);
        scrollPane.setLocation(50, 60);
        add(scrollPane);
        addLabels(c);
        addButtons(c);

        setSize(850, 600);
        setResizable(false);
        setLocation(100, 100);
        setLocationRelativeTo(null); // Start in center of screen.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void addButtons(Container c) {
        addButton = new JButton("Add");
        addButton.setSize(100, 50);
        addButton.setLocation(50, 500);
        addButton.addActionListener(this);
        c.add(addButton);

        modifyButton = new JButton("Modify");
        modifyButton.setSize(100, 50);
        modifyButton.setLocation(180, 500);
        modifyButton.addActionListener(this);
        c.add(modifyButton);

        removeButton = new JButton("Remove");
        removeButton.setSize(100, 50);
        removeButton.setLocation(310, 500);
        removeButton.addActionListener(this);
        c.add(removeButton);

        removeAllButton = new JButton("Remove All");
        removeAllButton.setSize(100, 50);
        removeAllButton.setLocation(440, 500);
        removeAllButton.addActionListener(this);
        c.add(removeAllButton);

        saveButton = new JButton("Save");
        saveButton.setSize(100, 50);
        saveButton.setLocation(570, 500);
        saveButton.addActionListener(this);
        c.add(saveButton);

        openButton = new JButton("Open");
        openButton.setSize(100, 50);
        openButton.setLocation(700, 500);
        openButton.addActionListener(this);
        c.add(openButton);
    }

    public void addLabels(Container c) {
        idLabel = new MyLabel("Participant ID");
        idLabel.setLocation(50, 10);
        c.add(idLabel);

        zipCodeLabel = new MyLabel("Zip Code");
        zipCodeLabel.setLocation(225, 10);
        c.add(zipCodeLabel);

        carrierLabel = new MyLabel("Carrier");
        carrierLabel.setLocation(400, 10);
        c.add(carrierLabel);

        ratingLabel = new MyLabel("Rating");
        ratingLabel.setLocation(575, 10);
        c.add(ratingLabel);

        servicesLabel = new MyLabel("Services");
        servicesLabel.setLocation(750, 10);
        c.add(servicesLabel);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==addButton) {
            new SurveyDialog(this, model, false, -1);
        }

        if (e.getSource()==modifyButton) {
            if (list.getSelectedIndex() != -1) {
                new SurveyDialog(this, model, true, list.getSelectedIndex());
            }

        }

        if (e.getSource()==removeButton) {
            if (list.getSelectedIndex() != -1) {
                new SurveyDialog(true, list.getSelectedIndex());
                model.removeElementAt(list.getSelectedIndex());
            }
        }

        if (e.getSource()==removeAllButton) {
            model.removeAllElements();
            new SurveyDialog();
        }

        if (e.getSource()==saveButton) {
            try {
                saveFile();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
        }

        if (e.getSource()==openButton) {
            try {
                openFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void saveFile() throws FileNotFoundException, UnsupportedEncodingException {
        JFileChooser dialog = new JFileChooser();
        dialog.setVisible(true);
        int returnVal = dialog.showSaveDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File newFile = dialog.getSelectedFile();
            try {
                PrintWriter writer = new PrintWriter(newFile, "UTF-8");
                for (int i = 0; i < model.size(); i++) {
                    writer.println(model.elementAt(i));
                }
                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private void openFile() throws IOException {
        JFileChooser dialog = new JFileChooser();
        dialog.setVisible(true);
        int returnVal = dialog.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File newFile = dialog.getSelectedFile();
            readFile(newFile);
        }
    }

    private void readFile(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String line;
        model.clear();
        SurveyDialog d = new SurveyDialog();
        while ((line = br.readLine()) != null) {
            model.addElement(line);
            d.processFile(line);
        }
        br.close();
    }


    public static void main(String[] args) throws IOException {
        if (args.length == 1) {
            File argFile = new File(args[0]);
            new SurveyMain(argFile);
        } else {
            new SurveyMain();
        }

    }
}
