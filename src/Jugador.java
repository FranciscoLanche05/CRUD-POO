public class Jugador extends Persona implements Calificable,Exportable{
    private String posicion;
    private int dorsal;
    private int goles;
    private int asistencias;
    private int minutosJugados;
    private String equipo;


    public Jugador(String nombre,int edad,String nacionalidad,int id, String posicion,int dorsal,int goles,int asistencias,int minutosJugados,String equipo ){
        super(id, nombre, edad, nacionalidad);
        this.posicion=posicion;
        this.dorsal=dorsal;
        this.goles=goles;
        this.asistencias=asistencias;
        this.minutosJugados=minutosJugados;
        this.equipo=equipo;
    }

    public Jugador(int id, String nombre, int edad,
                   String nacionalidad, String posicion,
                   int dorsal, String equipo) {
        super(id, nombre, edad, nacionalidad); // Constructor padre


        if (dorsal < 1 || dorsal > 99)
            throw new IllegalArgumentException("Dorsal inválido: " + dorsal);

        this.posicion       = posicion;
        this.dorsal         = dorsal;
        this.goles          = 0;
        this.asistencias    = 0;
        this.minutosJugados = 0;
        this.equipo         = equipo;
    }

    public int getGoles(){
        return goles;
    }
    public void setGoles(int goles){
        if(goles< 0)throw new IllegalArgumentException("Goles: " + goles);
        this.goles = goles;
    }
    public int    getDorsal()         { return dorsal; }
    public int    getAsistencias()    { return asistencias; }
    public String getPosicion()       { return posicion; }
    public String getEquipo()         { return equipo; }
    public int    getMinutosJugados() { return minutosJugados; }



    @Override
    public double calcularCalificacion() {
        double calif = (goles * 3.0) + (asistencias * 1.5) + ((minutosJugados / 90.0)*0.5);
        return Math.min(calif, 10.0);
    }

    public String getNivelRendimiento() {
        double c = calcularCalificacion();
        if(c >= 8 )return "Estrella";
        if(c >= 6)return "Bueno";
        if(c >= 4 )return "Regular";
        return "Bajo";
    }

    @Override
    public String exportarDatos() {
        return "Jugador: " + getNombre() + " | Equipo: " + equipo + " Goles: " + goles + " | Calif: " + String.format("%.2", calcularCalificacion());
    }
    @Override
    public String exportarCSV() {
        return getId() + "," + getNombre() + ","
                + getNacionalidad() + "," + posicion + ","
                + dorsal + "," + goles + ","
                + asistencias + "," + equipo;
    }

    public double calcularGolesP90(){
        if (minutosJugados == 0) return 0;
        return ((double) goles / (double) minutosJugados) *90;
    }

    public boolean esTitular(){
        return minutosJugados >45;
    }

    @Override
    public String describir() {
        return "⚽ JUGADOR: " + toString()
                + "\n   Posición: " + posicion
                + " | Dorsal: #" + dorsal
                + " | Equipo: " + equipo
                + "\n   Goles: " + goles
                + " | Asistencias: " + asistencias
                + " | Minutos: " + minutosJugados
                + "\n   Calificación: " + String.format("%.2f", calcularCalificacion())
                + " — " + getNivelRendimiento();
    }
}
