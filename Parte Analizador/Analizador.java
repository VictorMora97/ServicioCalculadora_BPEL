package ISI.Analizador;

public class Analizador {
	public Resultado analizar(String expresion) {
		
		 String operador="";							 
		 String[] exp=null;		 
		 String[] aux = expresion.split("");
		 int contador = 0;
		 int flagAux = 0;
		 
	     Resultado resultado = new Resultado();		
	     
	     //Compruebo si falta algun parentesis
		 
		 if(aux[0].contains("(") && aux[aux.length-1].contains(")") ) {
								 					 
			 for(int i = 1; i<aux.length-1;i++) {
				 char x = aux[i].charAt(0);
				 int x1 = x;
				 
				 //Si tiene parentesis, compruebo con valores ASCII si son caracteres validos
				 
				 if( (x1<48 || x1>57) && (x1!=42 && x1!=43 && x1!=45 && x1!=47) ) {
				    resultado.setMensajeError("Caracter no valido");
				    resultado.setOperador(0);
					return resultado;
				 }		 
			 }					 				 
		 }else {
			    resultado.setMensajeError("Error con los paréntesis");
			    resultado.setOperador(0);
				 return resultado;
		 }
		 //Compruebo que tiene el minimo de argumentos posibles (5): '(' + 'x' + 'operador' + 'y' + ')'
		 if(expresion.length()<5) {
			    resultado.setMensajeError("Falta de argumentos");
			    resultado.setOperador(0);
				 return resultado;
		 }		 
		 //Obtengo el operador
	     for(int i=2;i<aux.length-2;i++){
	         if( aux[i].contentEquals("+") || aux[i].contentEquals("-") || aux[i].contentEquals("*") || aux[i].contentEquals("/") ){
	             operador =  aux[i];
	             contador++;
	         }			         			        	         			        	 			         
	     }
	     //Compruebo que exista un operador
	     if (contador ==0) {
			    resultado.setMensajeError("Falta un operador");
			    resultado.setOperador(0);
				 return resultado;
	     }
	     //Compruebo que no haya mas de 1 operador
	     if (contador>1) {
			    resultado.setMensajeError("Hay mas de un operador");
			    resultado.setOperador(0);
				 return resultado;
	     }
	     //Asigno codigo de operador, y separo los operandos en 2 numeros
		 if(operador.contentEquals("+")) { //1
			  exp = expresion.split("\\+",2);
			  resultado.setOperador(1);
		 }
		 if(operador.contentEquals("-")) { //2
			  exp = expresion.split("\\-",2);
			  resultado.setOperador(2);
			  flagAux=1;
		 }
		 if(operador.contentEquals("*")) { //3
			  exp = expresion.split("\\*",2);
			  resultado.setOperador(3);
		 }
		 if(operador.contentEquals("/")) { //4
			  exp = expresion.split("\\/",2);
			  resultado.setOperador(4);
			  flagAux=1;
		 }
		 String[] aux1 = exp[0].split("\\(");
		 String[] aux2 = exp[1].split("\\)");
		 
		 try {
		 int numero1 = Integer.parseInt(aux1[1]);				 
		 int numero2 = Integer.parseInt(aux2[0]);

		 //Control de las operaciones resta y division
		 if (flagAux==1 && numero1<numero2) {
			    resultado.setMensajeError("Operacion no valida (numero negativo)");
			    resultado.setOperador(0);
				 return resultado;
		 }										 										     
		    
		    resultado.setNumero1(numero1);
		    resultado.setNumero2(numero2);
		     
		 }catch (Exception e) {
			    resultado.setMensajeError("Expresion mal formada");
			    resultado.setOperador(0);
				 return resultado;
			}
			return resultado;
	
	
	}
}
