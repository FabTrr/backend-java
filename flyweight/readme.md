## Consigna: patrón flyweight

Implementar el patrón de diseño Flyweight en Java para optimizar el uso de memoria creando un gran número de objetos de tipo arbol.

### Clases Principales:

1. Arbol: Representa la estructura de un árbol con atributos como alto, ancho y color. Esta clase también incluye getters y setters para acceder y modificar estos atributos.

2. ArbolFactory: Gestiona la creación y almacenamiento de árboles. Utiliza el patrón Flyweight para reutilizar árboles existentes y evitar crear duplicados. También utiliza un HashMap para almacenar y recuperar los árboles creados previamente.

3. Bosque: Contiene el método main donde se realiza la prueba del sistema. En este caso, se crean 500000 árboles rojos y 500000 árboles verdes utilizando la fábrica de árboles. Luego, se muestra cuánta memoria se está utilizando antes y después de crear los árboles para evaluar la eficiencia del patrón Flyweight en la optimización de la memoria.

## Enunciado

En un negocio necesitan crear árboles para poder ver cuánto lugar ocuparían. El programa que tiene actualmente posee un elevado consumo de los recursos. Necesita crear 1.000.000 árboles. El proceso para crear los árboles son, cada árbol tiene un Alto, Ancho, color y tipo de árbol. Los tipos de árboles que hay son:

- Ornamentales
  - Alto: 200.
  - Horizontal: 400.
  - Color: verde.
- Frutales
  - Alto: 500.
  - Horizontal: 300.
  - Color: rojo.
- Florales
  - Alto: 100.
  - Horizontal: 200.
  - Color: celeste.

El bosque es un conjunto de árboles y permitirá plantar los árboles. ArbolFactory será el lugar donde se almacenarán los diferentes tipos de árboles. Permitirá, antes de crear el objeto, validar si ya existe uno idéntico al que se le está solicitando. De ser así, regresa el objeto existente; de no existir, crea el nuevo objeto y lo almacena en caché para ser reutilizado más adelante.

Se necesita un sistema que muestre 1.000.000 de árboles la mitad rojo y la otra mitad verde y que luego informe por pantalla los árboles y ver cuánta memoria se está usando.

Usando esta sentencia se podrá ver la memoria usada:

```java
Runtime runtime = Runtime.getRuntime();
System.out.println("Memoria usada: " + (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024));