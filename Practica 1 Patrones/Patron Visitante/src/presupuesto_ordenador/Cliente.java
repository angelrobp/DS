package presupuesto_ordenador;

import java.util.Random;

public class Cliente {

	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Random r= new Random();
		int tipoCliente = r.nextInt(100);
		
		VisitantePrecio vp = new VisitantePrecio();
		VisitanteMarca vm = new VisitanteMarca();
		VisitantePrecioNeto vpn = new VisitantePrecioNeto();
		
		Bus b = new Bus("Bus ISA", 50);
		Disco d = new Disco("Disco SSD Samsung 850 Evo", 130);
		Tarjeta t = new Tarjeta("Tarjeta Grafica MSI GeForce GTX 1080Ti", 599);
		
		b.aceptar(vp, tipoCliente);
		d.aceptar(vp, tipoCliente);
		t.aceptar(vp, tipoCliente);
		
		b.aceptar(vpn, tipoCliente);
		d.aceptar(vpn, tipoCliente);
		t.aceptar(vpn, tipoCliente);
		
		b.aceptar(vm, tipoCliente);
		d.aceptar(vm, tipoCliente);
		t.aceptar(vm, tipoCliente);
		
		System.out.println("Descuento de cliente: " + tipoCliente + "%");
		System.out.println("Componentes: \n" + vm.getMarcas());
		System.out.println("Precio total neto: " + vpn.getPrecioAcumulado() + "€");
		System.out.println("Precio total con descuento del equipo: " + vp.getPrecioAcumulado() + "€");
		

	}

}
