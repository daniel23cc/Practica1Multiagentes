[![logo](https://www.gnu.org/graphics/gplv3-127x51.png)](https://choosealicense.com/licenses/gpl-3.0/)
# Guión de Prácticas
## 1. Introducción: Crear un primer agente

El objetivo de esta práctica es una primera toma de contacto con el [sistema multi-agente](https://jade.tilab.com/) que se utilizará a lo largo de la asignatura.

Los **objetivos** de esta práctica son los siguientes:

-   Presentar la estructura que debe tener un proyecto **NetBeans** que utilizaremos a lo largo del desarrollo de las prácticas.
-   Presentar la estructura general que deberá tener una clase que represente un agente en el desarrollo de las prácticas.
-   Por último, la creación de un primer agente para tomar contacto con la biblioteca multi-agente que se utilizará.

### 1.1 Crear el Proyecto NetBeans

Vamos a presentar la estructura general que deberán tener todos los proyectos que se crearán para las prácticas. El entorno de desarrollo elegido es NetBeans 11.2, aunque será válido siempre que sea NetBeans 11.x. No estará permitido otro entorno de desarrollo en las prácticas. Además debemos asegurarnos que la versión de Java sea la 1.8.
Mediante el asistente, pinchamos en el icono ![][proyecto], seleccionaremos la opción de creación de un proyecto Maven para una aplicación Java y pulsamos el botón siguiente:

![][imagen1]

El nombre del proyecto es **PrimerAgente** y para el *Group Id* utilizaremos `com.uja.ssmmaa` y el indicador del curso en el que estamos, por ejemplo, `curso1920`. 

![][imagen2]

Pulsamos el botón de finalización y el asistente crea la estructura básica de nuestro proyecto. En la estructura del proyecto buscamos la carpeta *Proyect Files* y abrimos el fichero `pom.xml`. Este fichero es el que nos permitirá localizar las dependencias relacionadas con el proyecto. Nuestro proyecto necesita una biblioteca externa para trabajar con la plataforma de agentes. La información relativa a las líneas que deberá tener nuestro archivo `pom.xml`, para nuestro proyecto, las podemos encontrar en la dirección de la biblioteca [Jade](https://jade.tilab.com/developers/maven/). La información mínima que deberemos añadir al fichero será la siguiente: 

```xml
<repositories>
    <repository>
        <id>tilab</id>
        <url>https://jade.tilab.com/maven/</url> 
    </repository>
</repositories>

<dependencies>
    <dependency> 
        <groupId>com.tilab.jade</groupId> 
        <artifactId>jade</artifactId> 
        <version>4.5.0</version>
    </dependency>
</dependencies>
```

De esta forma ya tendremos configuradas las dependencias necesarias para el desarrollo de nuestro proyecto. De este modo funcionará independientemente del ordenador donde desarrollemos nuestro proyecto. También podemos clonar el proyecto y personalizarlo para que se adapte a nuestras necesidades concretas.

Para evitar problemas con la caducidad de los certificados en el servidor tenemos que configurar una directiva para la compilación del proyecto:

![][config1]

Elegimos la opción de configurar una nueva y la añadimos dándole un nombre:

![][config2]

Y para finalizar incluimos las directivas que permiten descargar la biblioteca aunque el certificado en el servidor esté caducado:

![][config3]

El siguiente paso es configurar la ejecución de nuestro proyecto para poder realizar las pruebas necesarias del mismo. Para ello seleccionamos las propiedades del proyecto y localizamos la propiedad que se refiere a la ejecución. Configuramos esta propiedad con las opciones que se muestran en la imagen:

![][imagen3] 

Para finalizar la configuración de nuestro proyecto debemos crear los paquetes necesarios donde se incluirán las clases, tomamos como base el paquete que ya está creado en la estructura del proyecto, que permitan resolver el problema que tengamos planteado. Como norma general deberemos necesitar, para esta primera práctica, los siguientes paquetes:

-   Paquete `agentes`: en este paquete incluimos todas las clases que van a representar los agentes presentes en la solución de nuestro problema.
-   Paquete `gui`: en este paquete incluimos todas las clases que representarán la interfaz gráfica que tenga nuestro proyecto. Estas interfaces serán la visualización que tengamos de nuestros agentes dentro del sistema. 
    
De esta forma ya tenemos el proyecto **NetBeans** finalizado y podremos empezar a incluir las clases necesarias que nos permitan resolver nuestro problema. 

### 1.2. Estructura para una Clase de Agente

Ahora se presenta la estructura general que deberá tener una clase que representará nuestro agente en el proyecto. Esta estructura se utilizará como punto de partida y posteriormente deberéis personalizarla para adaptarla al propósito que deba tener nuestro agente en el sistema.

Como norma de estilo, todas las clases que representen a un agente deberán estar incluidas en el paquete `agentes` de vuestro proyecto y siempre empezarán por la palabra `Agente`. En el ejemplo que nos ocupa será `AgentePlantilla`, que representa la estructura que deberán tener cada uno de los agentes que creemos en nuestros proyectos, pero para el desarrollo de las prácticas se utilizará un nombre que sea apropiado.

El punto de partida es clonar este proyecto en vuestro ordenador y podemos ver la declaración que deben tener los agentes:

```java
/**
 *
 * @author pedroj
 * Esqueleto de agente para la estructura general que deben tener todos los
 * agentes
 */
public class AgentePlantilla extends Agent {
    //Variables del agente
...
```

Una vez que hemos definido nuestro agente incluimos las variables que sean necesarias[^nota1]. Otra particularidad que debemos tener presente es que no será necesario declarar ningún constructor. El siguiente paso es implementar los métodos `setup()`, es lo primero que hace el agente cuando se crea en la plataforma, y `takeDown()`, que es lo último que hace al abandonar la plataforma. 

#### Método `setup()`

Este es el método donde estará la inicialización del agente y la inclusión de las tareas principales del mismo. El método se invoca cuando el gestor de la plataforma[^nota2] crea el agente en un contenedor. 

```java
@Override
protected void setup() {
    //Inicialización de las variables del agente
       
    //Configuración del GUI
       
    //Registro del agente en las Páginas Amarrillas
       
    //Registro de la Ontología
       
    System.out.println("Se inicia la ejecución del agente: " + this.getName());
       
    //Añadir las tareas principales
}
```

En la plantilla se describen un orden, como norma de estilo, que deberá seguir los distintos elementos presentes en el método. No todos los elementos estarán presentes en todos los agentes que creemos al principio, pero sí estarán en los agentes que desarrollaremos más adelante en las prácticas de la asignatura.

El registro en las páginas amarillas será necesario para aquellos agentes que proveen de servicios a otros agentes. Los agentes que no estén en ese grupo no es obligatorio su registro, porque no serán buscados por otros agentes.

Para añadir una tarea que el agente deba completar se invocará el método `addBehaviour(` [Behaviour](https://jade.tilab.com/doc/api/jade/core/behaviours/Behaviour.html) `)`. De esta forma la tarea se añadirá a las que deba completar el agente y su ejecución se realiza en una arquitectura *single thread*. No se tendrá que tener cuidado por las secciones críticas pero sí para la sincronización entre diferentes tareas disponibles para su realización por parte del agente.

#### Método `takeDown()`

El método será invocado cuando el agente finalice su ejecución o en el código se invoque el método `doDelete()` de forma explícita.

```java
@Override
protected void takeDown() {
    //Eliminar registro del agente en las Páginas Amarillas
       
    //Liberación de recursos, incluido el GUI
       
    //Despedida
    System.out.println("Finaliza la ejecución del agente: " + this.getName());
}
```

En el código se muestran las acciones que deberán hacerse en este método, son todas aquellas necesarias para que la finalización del agente se realice de forma ordenada y sin dejar recursos ocupados en el sistema.

Para completar la clase del agente se incluirán los métodos propios para su normal funcionamiento, incluyendo los métodos de acceso para los atributos definidos previamente. Por último, se incluirán las clases internas que representan las tareas[^nota3] propias que el agente deberá llevar a cabo. Al declararse como clases internas tendrán acceso directo a las variables del agente. Más adelante se mostrará un ejemplo relativo y en posteriores guiones se presentarán agentes que implementen diferentes tareas y se consolidarán los conocimientos necesarios para desarrollar cualquier agente que complete una tarea específica.

#### Ejecución de un agente 

Con la configuración que se ha preparado del proyecto, si lo ejecutamos, se nos presentará el agente `rma` de la plataforma. Tendrá una apariencia similar a la que se muestra:

![](https://lh4.googleusercontent.com/zBE0ETts5LsvBmubxTID40VI7L1NePSiS7ybnAX9dxLyUAUsYSiVvnxQcFibYYFzyPS9jEw4visxh7R8qgs7oRTYyGVEAapSvTuDwPX7V1vkEdf48-Sp6QRU0Pr89dzggpL8ukkd)

Este agente está presente únicamente en el contenedor principal de la plataforma y de esta forma podremos trabajar con ella de una manera más cómoda. Si queremos ejecutar un agente, debemos seleccionar el contenedor donde lanzarlo y pulsar en el icono para crear un agente ![][crearAgente]:

![](https://lh3.googleusercontent.com/urDFqcvS7bOhFBBygfpBvgcBnYxY5rQgpBcmV0zbdeHdSBbKfXbgkU1waOVH0MwP44jRpaxefA1hzaOPUbgm9szFMsdPUh8BaFVMyQuC8nFLSKiFLYm_YM0FFMemljgZdG11y1Xu)

En la figura anterior vemos la ventana que se muestra para la creación del agente. Debemos elegir un nombre y la clase a la que pertenece el agente. Para ello podemos pulsar en el botón desplegable que nos mostrará todas las clases de agentes que conoce nuestro proyecto, vemos que se incluye también el paquete al que pertenece la clase. Seleccionamos la clase a la que corresponde el agente que queremos que se añada a la plataforma.

![](https://lh3.googleusercontent.com/GTTzk_8lNUHBeM0WHNNVeF37a91px3Go01oxlGYa4p1nLs3POUHSFhGNGH06TUhblGVtNkApKLhJQzF8neC7v9dTvTs1OK_l_VjyVjiz07Pk0Tu1VdkZll7reZ8ORI3ilmHe8RQO)

Si completamos todos los pasos de forma apropiada se mostrará en el contenedor nuestro nuevo agente. Dependiendo de las capacidades del agente se podrá tener una visión gráfica o no del mismo. Mas adelante se mostrará un agente que tiene una interfaz gráfica y además realizará tareas que se mostrarán en la misma.

#### Prueba de ejecución

Probar la ejecución del proyecto, `PrimerAgente`, y comprobar si produce algún resultado. Modificar el resultado para que solo muestre su nombre y no los detalles de la plataforma de agentes.

### 1.3 Definición del primer agente y tarea

Se va a definir un agente que tiene el siguiente comportamiento:

1.  Crea una ventana que servirá para mostrar los mensajes del agente. Además tendrá un botón que finalizará el agente mediante un cuadro de diálogo para confirmarlo.    
2.  Registra el agente en servicio de páginas amarillas.
3.  Se diseña una tarea para el agente que sea periódica y que se repita cada `TIEMPO_CICLO` segundos. La acción de la tarea será incrementar un contador del agente que mostrará el número de veces que se ha ejecutado la tarea.
  
Creamos el proyecto **NetBeans** cuyo nombre será `AgenteDemo`, siguiendo las indicaciones que se han presentado previamente en este guión. Una vez que se ha finalizado con la configuración del proyecto se crean dos paquetes:

-   Un paquete `gui`, donde se incluirán dos clases, una para presentar la interfaz del agente y otra con el cuadro de diálogo para confirmar la finalización del mismo.
-   Un paquete `agentes`, en este paquete se creará la clase `AgenteDemo` que contiene la implementación de nuestro agente.
    
Antes de pasar con la presentación del agente se definirán las dos clases del paquete `gui` con aquellos elementos que son los más interesantes.

#### Clase `AgenteDemoJFrame`

Mediante el asistente creamos una clase `JFrame` con el nombre de `AgenteDemoJFrame` . Realizamos el diseño gráfico mediante el botón de diseño. 

![][imagen4]

El asistente nos crea la estructura de la clase que personalizaremos para que se adapte a nuestras necesidades. De la clase que nos crea el asistente hay que eliminar el método `main(.)` ya que la creación de está clase estará en el método `setup()` del agente.

La ventana contendrá los siguientes elementos:  
-   `JTextArea`: Para presentar los mensajes del agente asociado.  
-   `JButton`: Para lanzar el cuadro de diálogo que nos permitirá finalizar con la ejecución del agente.
    
El diseño de la interfaz debe atender unas normas de estilo apropiadas para satisfacer los objetivos del agente, en el ejemplo se presenta uno muy simple para presentar la forma en que se puede hacer.

![][imagen5]
  
Los elementos de personalización son los siguientes atributos privados:
-   Un atributo que nos permitirá enlazar el JFrame con su agente.  
-   Un atributo para crear el cuadro de diálogo.

```java
/**
 *
 * @author pedroj
 */
public class AgenteDemoJFrame extends javax.swing.JFrame {
    private AgenteDemo myAgent;
    private FinalizacionDialog finalizacion;
...
```

El constructor deberá dar valor al atributo que nos permitirá enlazar con el agente y también cambiaremos el título con el nombre del agente para que se tenga una visualización clara que esa interfaz corresponde a ese agente.

```java
/**
 * Creates new form AgenteDemoJFrame
 */
public AgenteDemoJFrame(AgenteDemo myAgent) {
    initComponents();
    this.myAgent = myAgent;
        
    this.setTitle(this.myAgent.getName());
}
```

Para poder incluir un mensaje en la interfaz se debe crear un método público para que lo pueda invocar el agente y así presentar los mensajes por el `JFrame`. Para finalizar debemos crear el método de acción del botón y capturar la finalización del `JFrame` para crear el cuadro de diálogo que pondrá fin a la ejecución del agente.

```java
public void presentarSalida (String msg) {
    salidaTexto.append(msg);
}

private void formWindowClosing(java.awt.event.WindowEvent evt) {                                   
    // TODO add your handling code here:
    finalizacion = new FinalizacionDialog(this, true, myAgent);
    finalizacion.setVisible(true);
}                                  

private void botonFinActionPerformed(java.awt.event.ActionEvent evt) {                                         
    // TODO add your handling code here:
    myAgent.doDelete();
}                                        
```

#### Clase `FinalizacionDialog`

Esta clase la crearemos mediante el asistente de `JDialog` y realizaremos un diseño donde presentaremos dos `JButton`, uno para confirmar la finalización de la ejecución del agente y otro para cancelar el cuadro de diálogo.

![][imagen6]

Esta clase deberá disponer de un atributo privado para saber el agente que tiene asociado y así poder finalizar con su ejecución. El constructor deberá inicializar este atributo y asegurarnos que se crea de forma modal, así el usuario deberá centrarse en este cuadro de diálogo y no interactuar con la interfaz del agente de ninguna otra forma. 

```java
public class FinalizacionDialog extends javax.swing.JDialog {
    private AgenteDemo myAgent;

    /**
     * Creates new form SalidaDialog
     */
    public FinalizacionDialog(java.awt.Frame parent, boolean modal, AgenteDemo myAgent) {
        super(parent, modal);
        initComponents();
        this.myAgent = myAgent;
        this.setTitle("Finalizar " + this.myAgent.getName());
    }
...
```

El botón de cancelación o la finalización del cuadro de diálogo no deben finalizar la ejecución del agente. Solo en el botón de finalización invocamos el método `doDelete()` del agente, que forzará la finalización del mismo.

```java
private void botonFinActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
    myAgent.doDelete();
}
private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
    this.dispose();
} 
```

#### Clase `AgenteDemo`

Esta clase la creamos en el paquete `agentes` y tendrá la implementación de las acciones que se han descrito anteriormente. El agente necesita de dos atributos, uno para llevar el número de ejecuciones y otro para tener acceso a su interfaz gráfica. Ambos atributos serán privados para que solo el agente tenga acceso a ellos.

```java
public class AgenteDemo extends Agent {
    private AgenteDemoJFrame myGui;
    private int ejecuciones;
...
```

##### Método `setup()`

La inicialización del agente implica la creación de la interfaz y la presentación en ella del mensaje de inicio. Además se inicializa el atributo de ejecuciones.

```java
@Override
protected void setup() {
    //Configuración del GUI y presentación
    myGui = new AgenteDemoJFrame(this);
    myGui.setVisible(true);
    myGui.presentarSalida("Se inicia la ejecución de " + this.getName() + "\n");
        
    //Incialización de variables
    ejecuciones = 0;
...
```

El siguiente paso es el registro en el servicio de páginas amarillas, en case se realizará una explicación más detallada de qué elementos son importantes en este paso.

```java
...
//Registro en Página Amarillas
DFAgentDescription dfd = new DFAgentDescription();
dfd.setName(getAID())
ServiceDescription sd = new ServiceDescription();
sd.setType("Tipo de Servicio");
sd.setName("Nombre del Servicio");
dfd.addServices(sd);
try {
    DFService.register(this, dfd);
} catch (FIPAException fe) {
    fe.printStackTrace();
}
...
```

Para finalizar el método se crea la tarea cíclica que debe realizar el agente y se añade a las tareas que debe completar.

```java
...
// Se añaden las tareas principales
addBehaviour(new TareaEjemplo(this, TIEMPO_CICLO));
...
```

##### Método `takeDown()`

Al igual que el proceso de registro hay que llevar el proceso de baja en las páginas amarillas. Se debe realizar en este método para que el agente ya no pueda ser localizado cuando no esté en la plataforma. 

```java
...
//Desregistro de las Páginas Amarillas
try {
	DFService.deregister(this);
} catch (FIPAException fe) {
    fe.printStackTrace();
}
...
```

El único recurso que tiene nuestro agente es su interfaz que deberemos liberar. Para finalizar presentaremos el mensaje de despedida en la consola. Como ya no disponemos de interfaz hay que realizarlo allí. Además, es mejor hacerlo así para que tengamos constancia que nuestro agente ha finalizado, de otro modo el mensaje se perdería.

```java
...
//Se liberan los recuros y se despide
myGui.dispose();
System.out.println("Finaliza la ejecución de " + this.getName());
...
```

Al tratarse de un agente bastante simple no tiene necesidad de métodos auxiliares para su funcionamiento.

##### Clase `TareaEjemplo`
  
Como la única tarea que realiza nuestro agente es propia la implementaremos como una clase interna. La ventaja es que tendremos acceso a los atributos de nuestro agente. Si fuera una tarea compartida entre más de un agente, lo propio sería crear un paquete de tareas donde se incluirían las clases que representan estas tareas.

El nombre que utilizaremos para este tipo de clases siempre constará, como norma de estilo, del prefijo `Tarea`. De esta forma será sencillo identificar en el código todos los tipos de clases presentes y su funcionalidad principal. Como la tarea será cíclica pero se ejecutará con una periodicidad deberemos heredar de la clase [TickerBehaviour](https://jade.tilab.com/doc/api/jade/core/behaviours/TickerBehaviour.html)[^nota4]. 

```java
//Clases que representan las tareas del agente
public class TareaEjemplo extends TickerBehaviour {
    //Tarea de ejemplo que se repite cada TIEMPO_CICLO segundos
    public TareaEjemplo(Agent a, long period) {
	    super(a, period);
    }
...
```

De esta clase solo debemos implementar el método `onTick()` donde se indicará la acción a realizar cuando se haya cumplido el periodo. En nuestro caso incrementar el atributo que lleva la cuenta de las ejecuciones y presentar un mensaje en la interfaz que dejará constancia de la ejecución.

```java
@Override
protected void onTick() {
    ejecuciones++;
    myGui.presentarSalida("\nEjecución número: " + ejecuciones);
}
```

De esta forma queda completo el proyecto con las especificaciones que se habían pedido del mismo.

### 1.4 Modificación Propuesta

Tomando como punto de partida la [etiqueta v2.0](https://gitlab.com/ssmmaa/guionsesion1/-/archive/v2.0/guionsesion1-v2.0.zip), del proyecto, realizar una modificación en la interfaz para que no se pueda finalizar la ejecución del agente hasta que no haya completado, al menos, 4 ejecuciones de la tarea `TareaEjemplo`.

Además, crear una nueva tarea al agente que realice lo siguiente:

-   Cada vez que se complete una tarea cuyo número de ejecución sea **par**:
	- muestre una secuencia de números, tantos como ejecuciones se hayan alcanzado.
-   Cada vez que se complete una tarea cuyo número de ejecución se **impar**:
	- muestre la secuencia de fibonacci, tantos como ejecuciones se hayan alcanzado.
-   Cuando se alcancen las 10 ejecuciones finalizará esta tarea.

---
[proyecto]: https://gitlab.com/ssccdd/guionsesion1/raw/master/img/nuevoProyecto.jpg
[imagen1]: https://gitlab.com/ssmmaa/guionsesion1/raw/master/img/imagen1.jpg "Nuevo Proyecto"
[imagen2]: https://gitlab.com/ssmmaa/guionsesion1/raw/master/img/imagen2.jpg "Nombre Proyecto"
[imagen3]: https://gitlab.com/ssmmaa/guionsesion1/raw/master/img/imagen3.jpg "Ejecución Proyecto"
[imagen4]: https://gitlab.com/ssmmaa/guionsesion1/raw/master/img/imagen4.jpg "Crear JFrame"
[imagen5]: https://gitlab.com/ssmmaa/guionsesion1/raw/master/img/imagen5.jpg
[imagen6]: https://gitlab.com/ssmmaa/guionsesion1/raw/master/img/imagen6.jpg
[config1]:https://gitlab.com/ssmmaa/guionsesion1/-/raw/master/img/config.jpg "Configurar compilación"
[config2]:https://gitlab.com/ssmmaa/guionsesion1/-/raw/master/img/config1.jpg "Crear directiva"
[config3]:https://gitlab.com/ssmmaa/guionsesion1/-/raw/master/img/config2.jpg "Añadir las directivas"
[crearAgente]: https://gitlab.com/ssmmaa/guionsesion1/raw/master/img/crearAgente.jpg

[^nota1]: Las variables del agente se declaran privadas como norma de estilo.
[^nota2]: Por el agente gestor de la plataforma `ams`.
[^nota3]: Definición de las clases que heredan de [Behaviour](https://jade.tilab.com/doc/api/jade/core/behaviours/Behaviour.html)
[^nota4]: API de programación de [JADE](https://jade.tilab.com/doc/api/index.html)
<!--stackedit_data:
eyJoaXN0b3J5IjpbMTM2Njg4MDAwNSwtNzQ2MjQ4MjYzLDc3Mj
UwODgxLDQ4MDQzNTA5NCwxMDg2NDM0Njg1LC0xNjE3MDE5NTky
LDYzMzI2MzcyNywtMTA1NzQzNjk5OCwxNjM1OTkxNDQ1LDgyMT
cyNDQ1OSwxMDAwODcyOTk2LDE3NDk1NDI0NzBdfQ==
-->
