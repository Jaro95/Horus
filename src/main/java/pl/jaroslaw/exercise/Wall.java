package pl.jaroslaw.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure {

    private final List<Block> blocks = new ArrayList<>();

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return findBlockByColor(blocks, color);
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        List<Block> result = new ArrayList<>();
        findBlocksByMaterial(blocks, material, result);
        return result;
    }

    @Override
    public int count() {
        return count(blocks);
    }

    public void addBlock(Block block) {
        blocks.add(block);
    }

    public void addBlocks(List<Block> blocks) {
        this.blocks.addAll(blocks);
    }

    private Optional<Block> findBlockByColor(List<Block> blocks, String color) {
        for (Block block : blocks) {
            if (block.color().equals(color)) {
                return Optional.of(block);
            }
            if (block instanceof CompositeBlock) {
                return findBlockByColor(((CompositeBlock) block).blocks(), color);
            }
        }
        return Optional.empty();
    }

    private void findBlocksByMaterial(List<Block> blocks, String material, List<Block> result) {
        for (Block block : blocks) {
            if (block.material().equals(material)) {
                result.add(block);
            }
            if (block instanceof CompositeBlock) {
                findBlocksByMaterial(((CompositeBlock) block).blocks(), material, result);
            }
        }
    }

    private int count(List<Block> blocks) {
        int total = 0;
        for (Block block : blocks) {
            total++;
            if (block instanceof CompositeBlock) {
                total += count(((CompositeBlock) block).blocks());
            }
        }
        return total;
    }
}
