package ExamPreparation.aquarium;

import ExamPreparation.aquarium.core.Engine;
import ExamPreparation.aquarium.core.EngineImpl;


public class Main {
    public static void main(String[] args) {
        Engine engine = new EngineImpl();
        engine.run();
    }
}
