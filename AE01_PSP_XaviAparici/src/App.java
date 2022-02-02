
import java.util.ArrayList;
import java.util.Scanner;

public class App {
	public static void sayHello() {
		System.out.println("Hola mundo");
	}
	
	public static void mostrarCompañeros() {
		String[] lista = {"Jorge","Joan","Eric","Ximo","Fran","Xavi"};//inizializamos y declaramos el array con los nombres
		ArrayList<String> lista2 = new ArrayList<String>();//declaramos el ArrayList
		
		System.out.println();
		System.out.println("Array: ");
		for(int j=0;j<lista.length;j++) {//mostramos el array
			System.out.println(lista[j]);
		}
		
		for(int i=0;i<lista.length;i++) { //llenamos el arraylist
			lista2.add(lista[i]);
		}
		
		System.out.println();
		System.out.println("ArrayList: ");
		for(int k=0;k<lista2.size();k++) {//mostramos el arraylist
			System.out.println(lista2.get(k));
		}
	}
	
	public static void sumaPares() {
		Scanner teclado = new Scanner(System.in);
		int numParar,suma=0;
		System.out.println("Introduce un número: ");
		numParar = teclado.nextInt();//pedimos el numero al que tenemos que llegar
		
		for(int i=0; i<=numParar; i=i+2) {//vamos acumulando los pares con el bucle
			suma = suma + i; //en la variable suma
		}
		
		System.out.println("La suma es: "+suma);//mostramos la suma
	}
	
	public static void factorial() {
		Scanner teclado = new Scanner(System.in);
		int num,factorial=1;
		System.out.println("Introduce un nombre para calcular su factorial: ");
		num = teclado.nextInt();
		for(int i = num; i>0; i--) {
			factorial = factorial * i;
		}
		System.out.println("El factorial de "+num+" es: "+factorial);
	}
	
	public static void mayor() {
		int[] numeros = {1,2,3,4,5,6,7,8,9};
		int aux=0;
		for(int i=0;i<numeros.length;i++) {
			if(aux<numeros[i]) {
				aux=numeros[i];
			}
		}
		System.out.println("El mayor es: "+aux);
	}
	
	public static void inverso() {
		Scanner teclado = new Scanner(System.in);
		int[] n = new int[5];
		int suma=0;
		for(int i=0;i<n.length;i++) {
			System.out.println("Introduce un numero:");
			n[i]=teclado.nextInt();
			suma = suma + n[i];
		}
		for(int j=n.length-1; j>=0;j--) {
			System.out.print(n[j]+" ");
		}
		System.out.println();
		System.out.println("Suma total: "+suma);
	}
	
	public static void desarrollador() {
		Scanner teclado = new Scanner(System.in);
		String nombre;
		double experiencia;
		System.out.println("Introduce tu nombre: ");
		nombre = teclado.nextLine();
		System.out.println("Introduce los años de experiencia: ");
		experiencia = teclado.nextDouble();
		if(experiencia<1) {
			System.out.println("Desarrollador Junior L1 - 15000-18000");
		}
		else if(experiencia>=1 && experiencia<=2) {
			System.out.println("Desarrollador Junior L2 - 18000-22000");
		}
		else if(experiencia>2 && experiencia<=5) {
			System.out.println("Desarrollador Senior L1 - 22000-28000");
		}
		else if(experiencia>5 && experiencia<=8) {
			System.out.println("Desarrollador Senior L2 - 28000-36000");
		}
		else if(experiencia>8) {
			System.out.println("Analista / Arquitecto. Salario a convertir en base a rol");
		}
	}
	
	public static void primo() {
		Scanner teclado = new Scanner(System.in);
		int n1,n2;
		System.out.println("Introduce dos numeros enteros: ");
		n1 = teclado.nextInt();
		n2 = teclado.nextInt();
		
		for(;n1<=n2;n1++) {
			System.out.print(n1);
			if(n1==2 || n1==3 || n1==5 || n1==7 || n1==11) {
				System.out.print(" es primo");
			}
			else if(n1%2==0 || n1%3==0 || n1%5==0 || n1%7==0 || n1%11==0) {
				System.out.print(" no es primo");
			}
			else if(n1==1) {
				System.out.print(" no es primo");
			}
			else {
				System.out.print(" es primo");
			}
			System.out.println();
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner teclado = new Scanner(System.in);
		
		int menu;
		
		
		System.out.println("***MENU***");
		System.out.println("1. Hola mundo");
		System.out.println("2. ");
		System.out.println("3. ");
		System.out.println("4. ");
		System.out.println("5. ");
		System.out.println("6. ");
		System.out.println("7. ");
		System.out.println("8. ");
		System.out.println();
		System.out.println("Introduce una opción: ");
		menu = teclado.nextInt();
		
		switch(menu) {
		case 1:
			sayHello();
			break;
			
		case 2:
			mostrarCompañeros();
			break;
			
		case 3:
			sumaPares();
			break;
		
		case 4:
			factorial();
			break;
			
		case 5:
			mayor();
			break;
			
		case 6:
			inverso();
			break;
			
		case 7:
			desarrollador();
			break;
			
		case 8:
			primo();
			break;
		}

	}

}
