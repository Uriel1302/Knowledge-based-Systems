
package examples.clips.agents;

import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import net.sf.clipsrules.jni.*;
import jade.lang.acl.ACLMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChiefSurgeonAgent extends Agent {

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
                    clips.assertString("(patient ready)");
                    clips.load(msg.getContent());
                    clips.eval("(rules)");
                    clips.eval("(facts)");
                    clips.run();

                    ACLMessage msg1 = new ACLMessage(ACLMessage.INFORM);
                    msg1.setContent("src/examples/clips/surgery/rules_anesthesiologist.clp");
                    msg1.addReceiver(new AID("anestesio",AID.ISLOCALNAME));
                    send(msg1);
                    
                } else {
                    block();
                }
 } catch (CLIPSException ex) {
                Logger.getLogger(ChiefSurgeonAgent.class.getName()).log(Level.SEVERE, null, ex);
            }

            }
        });
    }
}
