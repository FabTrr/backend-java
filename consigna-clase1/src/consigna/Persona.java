package consigna;

//las clases siempre son primera letra en mayuscula y en singular

public class Persona {
    //tres tipos de encapsulamiento para los atributos (publico, privado, protegido)
    private String nombre;
    private Integer edad;

    //constructor (para instanciar)
    public Persona(Integer edad, String nombre){
        this.edad = edad;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    //los atributos son privados y las funciones son publicas
    public boolean esMayorEdad(){
        return edad >= 18;
    }
    public boolean checkEdad() {
        boolean respuesta = false;
        if (edad > 0 && edad <= 120) {
            respuesta = true;
        }
        return respuesta;
    }
    public boolean cantidadDeLetras(){
        return nombre.length() > 4;
    }
    public boolean checkAZ(){
        boolean respuesta = false;
        //nueva variable vector
        char[] array = nombre.toCharArray();
        //iter
        for (char letra : array) {
            if(!Character.isLetter(letra)){
                return respuesta;
            }
        }
        respuesta = true;
        return respuesta;
    }
}