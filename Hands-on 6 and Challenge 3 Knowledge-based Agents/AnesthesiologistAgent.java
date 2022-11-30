package examples.clips.agents;

import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import net.sf.clipsrules.jni.*;
import jade.lang.acl.ACLMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnesthesiologistAgent extends Agent {

    Environment clips;

    protected void setup() {
        try {
            clips = new Environment();
        } catch (Exception e) {
        }
        addBehaviour(new CyclicBehaviour() {
            
            public void action() {
try{
                ACLMessage msg = receive();
                if (msg != null) {
                    clips.clear();
                    clips.assertString("(patient anesthesize)");
                    clips.load(msg.getContent());
                    clips.eval("(rules)");
                    clips.eval("(facts)");
                    clips.run();
                    ACLMessage msg1 = new ACLMessage(ACLMessage.INFORM);
                    msg1.setContent("src/examples/clips/surgery/rules_surgeon.clp");
                    msg1.addReceiver(new AID("surgeon",AID.ISLOCALNAME));
                    send(msg1);
                    
                } else {
                    block();
                }
 } catch (CLIPSException ex) {
                Logger.getLogger(AnesthesiologistAgent.class.getName()).log(Level.SEVERE, null, ex);
            }

            }
        });
    }
}
