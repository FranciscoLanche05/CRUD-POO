public abstract class Persona {
    private String nombre;
    private int edad;
    private String nacionalidad;
    private int id;

    public Persona(int id, String nombre, int edad, String nacionalidad) {
        if (nombre == null || nombre.trim().isEmpty())
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        if (edad < 0 || edad > 120)
            throw new IllegalArgumentException("Edad inválida: " + edad);

        this.id          = id;
        this.nombre      = nombre.trim();
        this.edad        = edad;
        this.nacionalidad = nacionalidad;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public String getNacionalidad() {
        return nacionalidad;
    }
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    public int getId() {
        return id;
    }

    public abstract String describir();
}
