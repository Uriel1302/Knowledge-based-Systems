package examples.clips.agents;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import net.sf.clipsrules.jni.*;
import jade.lang.acl.ACLMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReceptorAgent extends Agent {
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
                    clips.load("src/examples/clips/patologias/templates.clp");
                    String message = msg.getContent();
                    clips.assertString(message);
                    clips.run();
                    clips.load("src/examples/clips/patologias/rules.clp");
                    clips.eval("(facts)");
                    clips.eval("(rules)");
                    clips.run();
                    clips.clear();
                    
                } else {
		System.out.println("DOCTOR ESPERANDO PACIENTE...");
                    block();
                }
 } catch (CLIPSException ex) {
                Logger.getLogger(ReceptorAgent.class.getName()).log(Level.SEVERE, null, ex);
            }

            }
        });
    }
    
}
