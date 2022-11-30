package examples.clips.agents;
import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import net.sf.clipsrules.jni.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmisorAgent extends Agent {
    protected void setup() {
        addBehaviour(new OneShotBehaviour() {
            public void action() {

                System.out.println("Paciente Pablo, a revisión con los sintomas de FIEBRE, CANSANCIO Y DOLOR DE ESTOMAGO");
                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                msg.setContent("(paciente (nombre Pablo)(sintoma1 cansancio) (sintoma2 fiebre) (sintoma3 dolor-estomago))");
                msg.addReceiver(new AID("doctor",AID.ISLOCALNAME));
                send(msg);


            }
        });
    }   
}
