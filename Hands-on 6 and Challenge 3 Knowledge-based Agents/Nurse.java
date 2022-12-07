package examples.clips.agents;

import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import net.sf.clipsrules.jni.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Nurse extends Agent{
    Environment clips;
    protected void setup() {
        try {
            clips = new Environment();
        } catch (Exception e) {
        }
        addBehaviour(new OneShotBehaviour() {
            public void action() {
                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                msg.setContent("src/examples/clips/surgery/rules_nurse.clp");
                msg.addReceiver(new AID("chief",AID.ISLOCALNAME));
                send(msg);
            }
        });
    }
}
