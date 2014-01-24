import controller.ViewController;
import model.BankModel;
import model.CounterModel;
import view.MainWindow;
import entities.Cart;
import entities.Counter;


public class Start {

	public static void main(String[] args){
		
		CounterModel.getInstance().initiateNewCounter(1);
		
		/*MainWindow mw = MainWindow.getInstance();
		System.out.println(mw);
		
		BankModel bm = BankModel.getInstance();
		System.out.println(bm);
		
		Counter cnt = new Counter(1);
		System.out.println(cnt.getCounterQR());
		
		CounterModel.getInstance().initiateNewCounter(1);
		Counter cnt2 = CounterModel.getInstance().getCounter(1);
		
		cnt.retrieveCurrentCustomerCart();
		Cart cart = cnt.getCurrentCart();
		System.out.println(cart.getCartPrice());
		
		cart.initiateSurvey();
		
		System.out.println(cart.getSurvey().productsLeftToCheck());
		System.out.println(cart.getSurvey().productIsScannedByCustomer("EAN123"));
		System.out.println(cart.getSurvey().productsLeftToCheck());
		System.out.println(cart.getSurvey().productIsScannedByCustomer("EAN125"));*/		
	}
	
}
