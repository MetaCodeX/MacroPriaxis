package macropriaxis;
/**
 *
 * @author MetaCodeX
 */
import macropriaxis.gui.indexgui; 
public class main {

    public static void main(String[] args) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new indexgui().setVisible(true); 
            }
        });
    }
}