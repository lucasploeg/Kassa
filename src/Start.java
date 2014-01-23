import model.CounterModel;
import controller.Server;
import entities.Cart;
import entities.Counter;
import entities.Survey;


public class Start {

	public static void main(String[] args){
		/*MainWindow mw = MainWindow.getInstance();
		System.out.println(mw);
		
		BankModel bm = BankModel.getInstance();
		System.out.println(bm);
		
		Counter cnt = new Counter(1);
		System.out.println(cnt.getCounterQR());*/
		
		CounterModel.getInstance().initiateNewCounter(1);
		Counter cnt = CounterModel.getInstance().getCounter(1);
		
		cnt.retrieveCurrentCustomerCart();
		Cart cart = cnt.getCurrentCart();
		System.out.println(cart.getCartPrice());
		
		cart.initiateSurvey();
		
		System.out.println(cart.getSurvey().productsLeftToCheck());
		System.out.println(cart.getSurvey().productIsScannedByCustomer("EAN123"));
		System.out.println(cart.getSurvey().productsLeftToCheck());
		System.out.println(cart.getSurvey().productIsScannedByCustomer("EAN125"));		
	}
	
}
