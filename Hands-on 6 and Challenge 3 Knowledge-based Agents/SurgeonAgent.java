package examples.clips.agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import net.sf.clipsrules.jni.*;
import jade.lang.acl.ACLMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SurgeonAgent extends Agent {

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
                    clips.assertString("(patient anesthetized)");
                    clips.load(msg.getContent());
                    clips.eval("(rules)");
                    clips.eval("(facts)");
                    clips.run();
                    
                } else {
                    block();
                }

     } catch (CLIPSException ex) {
                Logger.getLogger(SurgeonAgent.class.getName()).log(Level.SEVERE, null, ex);
            }

            }
        });
    }
}
