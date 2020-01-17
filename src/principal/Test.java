package principal;

public class Test {

	public static void main(String[] args) {

		Fecha f = new Fecha(27, 2, 2021);
		f.siguiente();
		f.siguiente();
		f.siguiente();

		System.out.println(f);
	}

}
