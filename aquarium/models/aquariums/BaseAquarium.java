package ExamPreparation.aquarium.models.aquariums;

import ExamPreparation.aquarium.models.decorations.Decoration;
import ExamPreparation.aquarium.models.fish.Fish;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static ExamPreparation.aquarium.common.ConstantMessages.NOT_ENOUGH_CAPACITY;
import static ExamPreparation.aquarium.common.ExceptionMessages.AQUARIUM_NAME_NULL_OR_EMPTY;

public abstract class BaseAquarium implements Aquarium {
    private String name;
    private int capacity;
    private Collection<Decoration> decorations;
    private Collection<Fish> fish;

    public BaseAquarium(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.decorations = new ArrayList<>();
        this.fish = new ArrayList<>();
    }

    @Override
    public int calculateComfort() {
        int comfort = this.decorations.stream()
                .mapToInt(d -> d.getComfort())
                .sum();

        return comfort;
    }

    @Override
    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        if (name == null || name.trim().length() < 1) {
            throw new NullPointerException(AQUARIUM_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public void addFish(Fish fish) {
        if (this.fish.size() >= this.capacity) {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY);
        }
        this.fish.add(fish);
    }

    @Override
    public void removeFish(Fish fish) {
        this.fish.remove(fish);
    }

    @Override
    public void addDecoration(Decoration decoration) {
        this.decorations.add(decoration);
    }

    @Override
    public void feed() {
        this.fish.forEach(f -> f.eat());
    }

    @Override
    public String getInfo() {
        StringBuilder builder = new StringBuilder();
        builder
                .append(String.format("%s (%s):", this.getName(), this.getClass().getSimpleName()))
                .append(System.lineSeparator())
                .append(String.format("Fish: %s", this.fish.isEmpty() ? "none" : printFish()))
                .append(System.lineSeparator())
                .append(String.format("Decorations: %d", this.decorations.size()))
                .append(System.lineSeparator())
                .append(String.format("Comfort: %d", this.calculateComfort()));

        return builder.toString().trim();
    }

    private String printFish() {
        StringBuilder builder = new StringBuilder();

        for (Fish fish1 : fish) {
            builder.append(fish1.getName()).append(" ");
        }
        return builder.toString().trim();
    }

    @Override
    public Collection<Fish> getFish() {
        return Collections.unmodifiableCollection(this.fish);
    }

    @Override
    public Collection<Decoration> getDecorations() {
        return Collections.unmodifiableCollection(this.decorations);
    }
}
