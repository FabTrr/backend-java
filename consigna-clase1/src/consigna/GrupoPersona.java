package consigna;

import java.util.ArrayList;
import java.util.List;

public class GrupoPersona {
    // tipos de coleciones:
    //mapas (permite guardar objetos y claves)
    // array list (permite elementos duplicados)
    // setmaps (no permite elementos duplicados)
    private List<Persona> personas;

    public GrupoPersona() {
        personas= new ArrayList<>();
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void agregarPersona(Persona persona){
        boolean checkMayoria= persona.esMayorEdad();
        boolean checkLetras= persona.cantidadDeLetras();
        boolean soloLetras= persona.checkAZ();
        boolean chekEdad= persona.checkEdad();
        if(checkMayoria&&checkLetras&&chekEdad&&soloLetras){
            personas.add(persona);
        }else {
            System.out.println("No se pudo agregar a la persona: "+persona.getNombre()+" :"+persona.getEdad());
        }
    }
}
