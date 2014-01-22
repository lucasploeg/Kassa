import entities.Counter;
import model.BankModel;
import view.MainWindow;


public class Start {

	public static void main(String[] args){
		MainWindow mw = MainWindow.getInstance();
		System.out.println(mw);
		
		BankModel bm = BankModel.getInstance();
		System.out.println(bm);
		
		Counter cnt = new Counter(1);
		System.out.println(cnt.getCounterQR());
		
	}
	
}
