package principal;

import java.util.Scanner;

public class Fecha {

	private int dia;
	private int mes;
	private int any;
	private final static String[] dias = {"lunes","martes","miercoles","jueves","viernes","sabado","domingo"};

	public Fecha() {
		this.dia = 1;
		this.mes = 1;
		this.any = 1900;
	}

	public Fecha(int dia, int mes, int any) {
		this.dia = dia;
		this.mes = mes;
		this.any = any;
		valida();
	}

	public static Fecha leer() {
		Scanner sc = new Scanner(System.in);
		int d, m, a;

		System.out.println("Dame el dia:");
		d = sc.nextInt();
		System.out.println("Dame el mes:");
		m = sc.nextInt();
		System.out.println("Dame el año:");
		a = sc.nextInt();	
		
		return new Fecha(d, m, a);	
	}
	public boolean menorQue(Fecha f) {
		return f.diasTranscurridos()<diasTranscurridos();
	}
	public boolean igual(Fecha f) {
		return f.diasTranscurridos()==diasTranscurridos();
	}

	public boolean bisiesto() {
		return bisiesto(this.any);
	}
	
	public String diaSemana() {
		return dias[diasTranscurridos()%7];
	}

	private void valida() {
		if (this.any > 2050 || this.any < 1900)
			this.any = 1900;
		if (this.mes > 12 || this.mes < 1)
			this.mes = 1;
		if (this.dia < 1 || this.dia > diasMes(this.mes))
			this.dia = 1;
	}

	public int diasMes(int mes) {
		switch (mes) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return 31;
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		case 2:
			return (bisiesto()) ? 29 : 28;
		default:
			return 0;

		}
	}
	public int diasTranscurridos() {
		
		int sumaDias=0;
		
		for(int a=this.any-1;a>=1900;a--) {
			if(Fecha.bisiesto(a))
				sumaDias+=366;
			else
				sumaDias+=365;
		}
		
		for(int m=this.mes-1;m>=1;m--) {
			sumaDias+=diasMes(m);
		}
		
		sumaDias+=this.dia-1;
		
		return sumaDias;
	}
	public void fechaTras(long d) {
		
		this.dia=1;
		this.mes=1;
		this.any=1900;
		
		while((bisiesto()&&d>=366) || (!bisiesto()&&d>=365)) {
			
			//d=d-(bisiesto()?366:365);
			if(bisiesto()) {
				d-=366;
			}else {
				d-=365;
			}
			this.any++;
			
		}
		
		while(diasMes(this.mes)<=d) {
			d-=diasMes(this.mes);
			this.mes++;
		}
		
		this.dia+=d;
		
	}
	public void siguiente() {
		fechaTras(diasTranscurridos()+1);
	}
	public void anterior() {
		fechaTras(diasTranscurridos()-1);
	}
	public String corta() {
		return this.dia + "-" + this.mes + "- "+ this.any;
	}

	public static boolean bisiesto(int any) {

		if (any % 400 == 0)
			return true;
		else if (any % 100 != 0 && any % 4 == 0)
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		return dia + "-" + mes + "-" + any;
	}

}
