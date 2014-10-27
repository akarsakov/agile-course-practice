package ru.unn.agile.LeftistHeap.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class LeftistHeapTest {

    @Test
    public void canCreateLeftistHeap() {
        LeftistHeap heap = new LeftistHeap();
        assertNotNull(heap);
    }

    @Test
    public void canCreateEmptyHeap() {
        LeftistHeap heap = new LeftistHeap();

        boolean isEmpty = heap.IsEmpty();

        assertTrue(isEmpty);
    }

    @Test
    public void canAddElementToHeap() {
        LeftistHeap heap = new LeftistHeap();

        heap.Add(1);

        assertFalse(heap.IsEmpty());
    }
}
