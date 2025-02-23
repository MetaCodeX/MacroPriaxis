package macropriaxis;

/**
 *
 * @author MetaCodeX
 */
import macropriaxis.gui.TriQiArea;

public class main {

    public static void main(String[] args) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TriQiArea().setVisible(true); 
            }
        });
    }
}   