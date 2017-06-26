package principal;


public class Fachada {
	private  static Fachada instance;
	
	public static Fachada getInstance(){
		if(Fachada.instance == null){
			Fachada.instance = new Fachada();
		}
		return Fachada.instance;
	}

}
