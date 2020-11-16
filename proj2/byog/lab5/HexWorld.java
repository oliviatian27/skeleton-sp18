package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    public static void addHexagon(TETile[][] world ){
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
//        world[5][5]=Tileset.FLOWER;
        for(int y=0;y<2*5;y+=1){
            int x=
        }
    }

     public static void main(String[] args) {
        TERenderer ter=new TERenderer();
        ter.initialize(WIDTH,HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        addHexagon(world);

        ter.renderFrame(world);
    }
}
