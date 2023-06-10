package sistemaAutogestion;



public class Retorno {
	public enum Resultado {
		OK,ERROR_1,ERROR_2,ERROR_3,ERROR_4,ERROR_5,NO_IMPLEMENTADA
	};
	int valorEntero;
	String valorString;
        boolean valorbooleano;
	public Resultado resultado;

    public Retorno(Resultado resultado) {

        this.resultado = resultado;
    }
    
    //EJEMPLO DE COMO SE PUEDE USAR EL RETORNO
    /*public static void main(String []args){
        Retorno r= new Retorno(Resultado.OK);
        r.valorEntero=10;
        r.valorString="El resultado es " + r.valorEntero;
    }
    */
        
        
        
}
