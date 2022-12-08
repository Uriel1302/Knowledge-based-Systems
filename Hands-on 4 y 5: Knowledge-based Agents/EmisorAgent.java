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

                System.out.println("Paciente Arnold, a revisión con los sintomas de RONCHAS, COMEZON Y FIEBRE");
                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                msg.setContent("(paciente (nombre Arnold) (sintoma1 ronchas) (sintoma2 comezon) (sintoma3 fiebre))");
                
		msg.addReceiver(new AID("doctor",AID.ISLOCALNAME));
                send(msg);


 
