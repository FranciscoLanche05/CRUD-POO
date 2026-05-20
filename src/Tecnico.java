

public class Tecnico extends Persona implements Calificable{
    private String tactica;
    private int añosExperiencia;
    private int partidosGanados;
    private int partidosTotales;
    private String equipo;



    public Tecnico(String nombre,int edad,String nacionalidad,int id,String tecnica,int añosDeExperiencia,int partidosGanados,int partidosTotales,String equipo) {
        super(id,nombre,edad,nacionalidad);
        this.tactica = tecnica;
        this.añosExperiencia = añosDeExperiencia;
        this.partidosGanados = partidosGanados;
        this.partidosTotales = partidosTotales;
        this.equipo = equipo;
    }
    public String getTactica()         { return tactica; }
    public int    getAñosExperiencia() { return añosExperiencia; }
    public int    getPartidosGanados() { return partidosGanados; }
    public String getEquipo()          { return equipo; }

    // MÉTODO PROPIO — % de efectividad
    public double calcularEfectividad() {
        if (partidosTotales == 0) return 0.0;
        // CONVERSIÓN int → double para división exacta
        return ((double) partidosGanados / partidosTotales) * 100;
    }

    public boolean esExperimentado() {
        return añosExperiencia >= 10;
    }

    @Override
    public double calcularCalificacion() {
        double efectividad = calcularEfectividad();
        double bonusExp    = añosExperiencia * 0.1;
        return Math.min((efectividad / 10.0) + bonusExp, 10.0);
    }

    @Override
    public String getNivelRendimiento() {
        double c = calcularCalificacion();
        return c >= 7 ? "🏆 ÉLITE" : c >= 5 ? "✅ COMPETENTE" : "⚠️ EN DESARROLLO";
    }

    @Override
    public String describir() {
        return "🎽 TÉCNICO: " + toString()
                + "\n   Táctica: " + tactica
                + " | Experiencia: " + añosExperiencia + " años"
                + " | Equipo: " + equipo
                + "\n   Efectividad: " + String.format("%.1f%%", calcularEfectividad())
                + " — " + getNivelRendimiento();
    }


}
