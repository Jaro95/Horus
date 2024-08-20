package pl.jaroslaw.exercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {

    Wall wall;

    @BeforeEach
    void setUp() {
        List<Block> blocksList = List.of(new SimpleBlock("Red", "Brick"), new SimpleBlock("Brown", "Wood"),
                new ComplexBlock("Grey", "Box", List.of(new SimpleBlock("White", "Tile"), new SimpleBlock("White", "Board"),
                        new ComplexBlock("Blue", "Container", List.of(new SimpleBlock("Brown", "Wood"), new SimpleBlock("Grey", "Wood"))))));
        wall = new Wall();
        wall.addBlocks(blocksList);
    }

    @Test
    void findBlockByColor() {
        Block blockResult = new SimpleBlock("White", "Brick");

        Optional<Block> blockCheck = wall.findBlockByColor("White");

        assertTrue(blockCheck.isPresent());
        assertEquals(blockResult.color(), blockCheck.get().color());
    }

    @Test
    void findBlocksByMaterial() {
        List<Block> blockListResult = List.of(new SimpleBlock("Brown", "Wood"), new SimpleBlock("Brown", "Wood"), new SimpleBlock("Grey", "Wood"));

        List<Block> checkBlockList = wall.findBlocksByMaterial("Wood");

        assertFalse(checkBlockList.isEmpty());
        assertEquals(blockListResult.size(), checkBlockList.size());
        assertEquals(blockListResult.get(0).material(), checkBlockList.get(0).material());
        assertEquals(blockListResult.get(1).material(), checkBlockList.get(1).material());
        assertEquals(blockListResult.get(2).material(), checkBlockList.get(2).material());
    }

    @Test
    void findBlocksByMaterialBox() {
        List<Block> blockListResult = List.of(new SimpleBlock("Grey", "Box"));


        List<Block> checkBlockList = wall.findBlocksByMaterial("Box");

        assertFalse(checkBlockList.isEmpty());
        assertEquals(blockListResult.size(), checkBlockList.size());
        assertEquals(blockListResult.get(0).material(), checkBlockList.get(0).material());
    }

    @Test
    void count() {
        int countBlockResult = 8;

        int checkCountBlockResult = wall.count();

        assertEquals(countBlockResult, checkCountBlockResult);
    }

    @Test
    void addBlock() {
        Block block = new SimpleBlock("Blue", "Tile");
        int result = wall.count() + 1;

        wall.addBlock(block);

        assertEquals(result, wall.count());
    }

    @Test
    void addBlocks() {
        List<Block> blockList = List.of(new ComplexBlock("Blue", "Container",
                List.of(new SimpleBlock("Brown", "Wood"), new SimpleBlock("Grey", "Wood"))));
        int result = wall.count() + 3;

        wall.addBlocks(blockList);

        assertEquals(result, wall.count());
    }
}