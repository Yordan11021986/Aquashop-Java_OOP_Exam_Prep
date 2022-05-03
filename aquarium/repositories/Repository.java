package ExamPreparation.aquarium.repositories;

import ExamPreparation.aquarium.models.decorations.Decoration;

public interface Repository  {
    void add(Decoration decoration);

    boolean remove(Decoration decoration);

    Decoration findByType(String type);
}
