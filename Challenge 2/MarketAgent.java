import jade.core.Agent;
import jade.core.behaviours.Behaviour;

import net.sf.clipsrules.jni.Environment;

public class MarketAgent extends Agent{
    
    Environment clips;

    protected void setup() {
        try {
            clips = new Environment();
        } catch (Exception e) {
        }
        addBehaviour(new TellBehaviour());
        addBehaviour(new AskBehaviour());
    }
    private class TellBehaviour extends Behaviour {

        boolean tellDone = false;
        @Override
        public void action() {
            System.out.println("Tell es ejecutado");
            try {
                clips.clear();
                clips.load("src/clips/market/templates.clp");
                clips.load("src/clips/market/facts.clp");
                clips.load("src/clips/market/rules.clp");
                clips.reset();
                clips.eval("(facts)");
            } catch (Exception e) {
            }
            tellDone = true;
        }
        public boolean done(){
            if(tellDone)
                return true;
            else
                return false;
        }
    }
        private class AskBehaviour extends Behaviour {

            boolean askDone = false;
            @Override
            public void action() {
                System.out.println("Ask es ejecutado");
                try {
                    clips.run();
                    clips.clear();
                } catch (Exception e) {
                }
                askDone = true;
            }
            @Override
            public boolean done() {
                if (askDone) {
                    return true;
                } else {
                    return false;
                }

            }
            @Override
            public int onEnd() {
                myAgent.doDelete();
                return super.onEnd();
            }
        }
}
