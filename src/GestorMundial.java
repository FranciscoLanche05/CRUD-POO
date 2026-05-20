import java.util.ArrayList;
import java.util.Scanner;

public class GestorMundial {
    private ArrayList<Jugador> jugadores;
    private ArrayList<Tecnico> tecnicos;
    private Scanner sc;
    private int contadorId;


    public GestorMundial() {
        jugadores = new ArrayList<>();
        tecnicos = new ArrayList<>();
        sc = new Scanner(System.in);
        contadorId = 1;

    }

    private int leerEntero(String campo,int min,int max){
        while(true){
            System.out.println(campo + "(" + min + "_" + max + "): ");
            String texto = sc.nextLine().trim();
            try{
                int valor = Integer.parseInt(texto);
                if(valor<min || valor>max){
                    System.out.println("Debe estar entre " + min + " e " + max + ": ");
                    continue;
                }
                return valor;


            }catch(NumberFormatException e){
                System.out.println("'" + texto + "' no es un numero entero valido");
            }
        }
    }


    private double leerDouble (String campo,double min,double max){
        while(true){
            System.out.println(campo + ": ");
            String texto = sc.nextLine().trim();
            try{
                double valor = Double.parseDouble(texto);
                if(valor<min || valor>max){
                    System.out.println("Debe estar entre " + min + " e " + max + ": ");
                    continue;
                }
                return valor;
            }catch(NumberFormatException e){
                System.out.println("No es un decimal valido");
            }
        }
    }


    //======== CREATE =========
    public void agregarJugador(){
        System.out.println("\n REGISTRO DE NUEVOS JUGADORES ");
        try{
            System.out.println("Nombre: ");
            String nombre = sc.nextLine();

            System.out.println("Edad: ");
            int edad = leerEntero("Edad",15,50);

            System.out.println("Nacionalidad: ");
            String nacionalidad = sc.nextLine();

            System.out.println("Posicion: (Delantero/MedioCampista/Defensa/Portero)");
            String posicion = sc.nextLine();

            System.out.println("Dorsal: ");
            int dorsal = leerEntero("Dorsal", 1,99);

            System.out.println("Equipo: ");
            String equipo = sc.nextLine();

            Jugador j = new Jugador(contadorId++, nombre,edad, nacionalidad,posicion,dorsal,equipo);
            jugadores.add(j);
            System.out.println("✅ Jugador registrado. Total: " + jugadores.size());
        }catch (IllegalArgumentException e){
            System.out.println("Error de validacion: "+ e.getMessage());
        }
    }

    //====== READ =======
    public void listarJugadores() {
        System.out.println("\n LISTA DE JUGADORES (" + jugadores.size() + " registrados)");
        if (jugadores.isEmpty()) {
            System.out.println("⚠️ No hay jugadores registrados aún.");
            return;
        }
        for (Jugador j : jugadores) {
            System.out.println(j.describir()); // POLIMORFISMO
            System.out.println("─────────────────────");
        }
    }

    public void buscarYMostrar(){
        System.out.println("ingrese el id del jugador a mostrar: ");
        int id = sc.nextInt();
        buscarJugadorPorId(id);
    }

    public Jugador buscarJugadorPorId(int id) throws JugadorNoEncontradoException {
        for (Jugador j : jugadores) {
            if (j.getId() == id) return j; // encontrado
        }
        throw new JugadorNoEncontradoException(id); // excepción personalizada
    }
    // ══════════════ UPDATE ══════════════
    public void actualizarGoles() {
        System.out.print("\n✏️ ID del jugador a actualizar: ");
        try {
            int id     = leerEntero("ID", 1, Integer.MAX_VALUE);
            Jugador j  = buscarJugadorPorId(id); // puede lanzar excepción

            System.out.print("Nuevos goles: ");
            int goles = leerEntero("Goles", 0, 999);

            j.setGoles(goles); // Setter con validación
            System.out.println("✅ Actualizado: " + j.exportarDatos());

        } catch (JugadorNoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    // ══════════════ DELETE ══════════════
    public void eliminarJugador() {
        System.out.print("\n🗑️ ID del jugador a eliminar: ");
        try {
            int id    = leerEntero("ID", 1, Integer.MAX_VALUE);
            Jugador j = buscarJugadorPorId(id);

            jugadores.remove(j); // ← REMOVE del ArrayList
            System.out.println("✅ Jugador '" + j.getNombre() + "' eliminado.");
            System.out.println("   Quedan: " + jugadores.size() + " jugadores.");

        } catch (JugadorNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    // ══════════════ ESTADÍSTICAS ══════════════
    public void mostrarEstadisticas() {
        System.out.println("\n📊 ESTADÍSTICAS DEL MUNDIAL 2026");
        System.out.println("Total jugadores: " + jugadores.size());

        int    totalGoles = 0;
        Jugador topGoleador = null;

        for (Jugador j : jugadores) {
            totalGoles += j.getGoles();
            if (topGoleador == null || j.getGoles() > topGoleador.getGoles())
                topGoleador = j;
        }

        System.out.println("Total goles: " + totalGoles);
        if (topGoleador != null)
            System.out.println("🥇 Goleador: " + topGoleador.getNombre()
                    + " con " + topGoleador.getGoles() + " goles");
    }




}
