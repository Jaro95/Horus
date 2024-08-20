package pl.jaroslaw.exercise;

import java.util.List;

public record ComplexBlock(String color, String material, List<Block> blocks) implements CompositeBlock {

}
