import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import model.CounterModel;


public class Start {
	
	public static void main(String[] args){
		
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
		
		CounterModel.getInstance().initiateNewCounter(1);	
	}
}
