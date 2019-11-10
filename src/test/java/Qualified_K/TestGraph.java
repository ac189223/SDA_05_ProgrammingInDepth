package Qualified_K;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests of method from Graph
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class TestGraph {
    private Graph graph = new Graph();

    @Test
    public void testOfFirstWay() {
        int[] from1 = new int[]{0, 0, 1, 2};
        int[] to1 = new int[]{1, 2, 3, 0};
        assertEquals(3, graph.fastestRoute(from1, to1, 2, 3));
        assertEquals(-1, graph.fastestRoute(from1, to1, 10, 40));
    }

    @Test
    public void testOfSecondWay() {
        int[] from2 = new int[]{0, 5, 10, 15, 20, 25, 30, 35};
        int[] to2 = new int[]{5, 10, 15, 20, 25, 30, 35, 40};
        assertEquals(8, graph.fastestRoute(from2, to2, 0, 40));
        assertEquals(4, graph.fastestRoute(from2, to2, 0, 20));
        assertEquals(4, graph.fastestRoute(from2, to2, 15, 35));
        assertEquals(-1, graph.fastestRoute(from2, to2, 20, 5));
        assertEquals(-1, graph.fastestRoute(from2, to2, 0, 2));
    }

    @Test
    public void testOfBigWay() {
        int[] from3 = new int[50];
        int[] to3 = new int[50];
        for (int i = 0; i < 50; i++) {
            from3[i] = i;
            to3[i] = i + 1;
        }
        assertEquals(50, graph.fastestRoute(from3, to3, 0, 50));
        assertEquals(-1, graph.fastestRoute(from3, to3, 10, 0));
    }
}
