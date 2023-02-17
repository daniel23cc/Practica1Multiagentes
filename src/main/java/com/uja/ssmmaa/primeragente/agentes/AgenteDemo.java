/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uja.ssmmaa.primeragente.agentes;

import com.uja.ssmmaa.primeragente.gui.AgenteDemoJFrame;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

/**
 *
 * @author pedroj
 */
public class AgenteDemo extends Agent {

    // Constantes
    public static final long TIEMPO_CICLO = 5000; // 10 seg.

    // Variables del agente
    private AgenteDemoJFrame myGui;
    private int ejecuciones;
    private int EJECUCIONES_MAXIMAS = 5;
    //private AgenteMonitor agMonitor;

    /**
     * Se ejecuta cuando se inicia el agente
     */
    @Override
    protected void setup() {
        //Configuración del GUI y presentación
        System.getProperty("java.classpath");
        myGui = new AgenteDemoJFrame(this);
        myGui.setVisible(true);
        myGui.presentarSalida("Se inicializa la ejecución de " + this.getName() + "\n");

        //Incialización de variables
        ejecuciones = 0;

        //obtengo el argumento
        Object[] args = getArguments();

        if (args != null && args.length > 0) {
            String argumento = (String) args[0];
            EJECUCIONES_MAXIMAS = Integer.parseInt(argumento);
        }
        //inicializacion agente monitor
        //agMonitor=new AgenteMonitor();
        //Registro de la Ontología
        //Registro en Página Amarillas
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("Tipo de Servicioo");
        sd.setName("Nombre del Servicioo");
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }

        // Se añaden las tareas principales
        addBehaviour(new TareaEjemplo(this, TIEMPO_CICLO));
    }

    /**
     * Se ejecuta al finalizar el agente
     */
    @Override
    protected void takeDown() {
        //Desregistro de las Páginas Amarillas
        try {
            DFService.deregister(this);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }

        //Se liberan los recuros y se despide
        myGui.dispose();
        System.out.println("Finaliza la ejecución de " + this.getName());
    }

    //Métodos del agente
    //Clases que representan las tareas del agente
    public class TareaEjemplo extends TickerBehaviour {

        //Tarea de ejemplo que se repite cada 10 segundos
        public TareaEjemplo(Agent a, long period) {
            super(a, period);
        }

        @Override
        protected void onTick() {
            ejecuciones++;
            myGui.presentarSalida("\nEjecución número: " + ejecuciones + "\n");
            System.out.println("\nEjecución número: " + ejecuciones + "\n");
            if (ejecuciones == EJECUCIONES_MAXIMAS) {
                myAgent.doDelete();
                System.out.println("Finaliza la ejecución de " + this.myAgent.getName() + " por llegar a las " + ejecuciones + " ejecuciones");
            } else {
                if (ejecuciones % 2 == 0) {
                    myGui.presentarSalida("       Nº ejecuc par --> Generando secuencia de " + ejecuciones + " num aleatorios: ");
                    for (int i = 0; i < ejecuciones; i++) {
                        myGui.presentarSalida((int) (Math.floor(Math.random() * 10) + 1) + ", ");
                    }
                } else {
                    int firstTerm = 0;
                    int secondTerm = 1;
                    myGui.presentarSalida("     Nº ejecuc impar --> Generando sec fibonacci de " + ejecuciones + " num: ");
                    myGui.presentarSalida(firstTerm + ", " + secondTerm + ", ");

                    // Genera los términos de la secuencia de Fibonacci
                    for (int i = 2; i < ejecuciones; i++) {
                        int nextTerm = firstTerm + secondTerm;
                        myGui.presentarSalida(nextTerm + ", ");

                        firstTerm = secondTerm;
                        secondTerm = nextTerm;
                    }
                }
            }
        }

    }

}
