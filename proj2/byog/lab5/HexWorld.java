package byog.lab5;

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

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    public static class Position {
        int x;
        int y;

        public Position(int X, int Y) {
            x = X;
            y = Y;
        }
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(4);
        switch (tileNum) {
            case 0:
                return Tileset.WALL;
            case 1:
                return Tileset.FLOWER;
            case 2:
                return Tileset.GRASS;
            case 3:
                return Tileset.WATER;
            default:
                return Tileset.TREE;
        }
    }

    public static void drawVerticalHex(TETile[][] world, Position p, int s, int count) {
        Position nextP = new Position(p.x, p.y);
        TETile t;
        while (count > 0) {
            t = randomTile();
            addHexagon(world, nextP, s, t);
            nextP = new Position(nextP.x, nextP.y + s * 2);
            count--;
        }

    }


    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        Position nextP = new Position(p.x, p.y);
        int upperY = nextP.y + s;
        int lowerY = upperY - 1;
        int dx = p.x;
        int rightBound = dx + s * 3 - 2;
        while (s > 0) {
            for (int i = dx; i < rightBound; i++) {
                world[i][upperY] = t;
                world[i][lowerY] = t;
            }
            s--;
            dx++;
            upperY++;
            lowerY--;
            rightBound--;
        }
    }

    public static Position bottomLeft(Position p, int s) {
        return new Position(p.x - (2 * s - 1), p.y + s);
    }

    public static Position bottomRight(Position p, int s) {
        return new Position(p.x + (2 * s - 1), p.y + s);
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        int size = 2;
        Position p = new Position(20, 0);
        drawVerticalHex(world, p, size, 5);

        Position pl = bottomLeft(p, size);
        drawVerticalHex(world, pl, size, 4);
        drawVerticalHex(world, bottomLeft(pl, size), size, 3);

        Position pr = bottomRight(p, size);
        drawVerticalHex(world, pr, size, 4);
        drawVerticalHex(world, bottomRight(pr, size), size, 3);

        ter.renderFrame(world);
    }
}
