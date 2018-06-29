package builder;

public class Product {

	private final String identificador;
	private final String clave;
	private String marca;
	private String modelo;
	
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getIdentificador() {
		return identificador;
	}

	public String getClave() {
		return clave;
	}

	
	
	@Override
	public String toString() {
		return "Automovil: [identificador=" + identificador + ", clave=" + clave + ", marca=" + marca + ", modelo="
				+ modelo + "]";
	}

	private Product (BuilderProduct builder) {
		this.identificador = builder.identificador;
		this.clave = builder.clave;
		this.marca = builder.marca;
		this.modelo = builder.modelo;
	}
	
	public static class BuilderProduct implements Builder{
		private final String identificador;
		private final String clave;
		private String marca;
		private String modelo;
		
		public BuilderProduct(String id, String clave) {
			this.identificador = id;
			this.clave = clave;
		}
		public BuilderProduct marca(String marca) {
			this.marca = marca;
			return this;
		}
		public BuilderProduct modelo(String modelo) {
			this.modelo = modelo;
			return this;
		}
		@Override
		public Product build() {
			return new Product(this);
		}
	}
}
