package Frame;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Louis
 */
public class JPanelChamp extends JPanel {

    private JLabel label;
    private JTextField text;
    private String name;

    public JPanelChamp(String name) {
        this.name = name;

        this.setPreferredSize(new java.awt.Dimension(300, 60));
        this.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING, 30, 5));

        this.label = new JLabel(name + " : ");
        this.text = new JTextField("", 20); //Si on met rien en argument alors la box fait 0 de longeur :)
        this.add(label);
        this.add(text);
    }
}
