package pl.jaroslaw.exercise;

import java.util.List;

interface CompositeBlock extends Block {
    List<Block> blocks();
}
