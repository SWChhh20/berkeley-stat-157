import javax.swing.*;

public class LinkedListDeque<Item> {
    private class DLList {
        private Item item;
        private DLList prev;
        private DLList next;

        public DLList(DLList p, Item i, DLList n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private int size = 0;
    private DLList sentinel = new DLList(null, null, null);

    public LinkedListDeque() {
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public void addFirst(Item i) {
        DLList toFront = new DLList(sentinel, i, sentinel.next);
        if (sentinel.prev.equals(sentinel)) {
            sentinel.prev = toFront;
        } else {
            sentinel.next.prev = toFront;
        }
        sentinel.next = toFront;
        size += 1;
    }

    public void addLast(Item i) {
        DLList toLast = new DLList(sentinel.prev, i, sentinel);
        if (sentinel.next.equals(sentinel)) {
            sentinel.next = toLast;
        }
        sentinel.prev.next = toLast;
        sentinel.prev = toLast;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Item removeFirst() {
        if (sentinel.next.equals(sentinel)) {
            return null;
        } else {
            DLList toRemove = sentinel.next;
            DLList movetoFront = toRemove.next;
            sentinel.next = movetoFront;
            movetoFront.prev = sentinel;
            toRemove.prev = null;
            toRemove.next = null;
            size -= 1;
            return toRemove.item;
        }
    }

    public Item removeLast() {
        if (sentinel.prev.equals(sentinel)) {
            return null;
        } else {
            DLList toRemove = sentinel.prev;
            DLList movetoLast = toRemove.prev;
            sentinel.prev = movetoLast;
            movetoLast.next = sentinel;
            toRemove.prev = null;
            toRemove.next = null;
            size -= 1;
            return toRemove.item;
        }
    }

    public void printDeque() {
        DLList p = sentinel.next;
        while (!p.equals(sentinel)) {
            System.out.print(p.item + "");
            p = p.next;
        }
        System.out.print("\n");
    }

    public Item get(int index) {
        DLList p = sentinel.next;
        if (index >= size) {
            return null;
        }
        while (index > 0) {
            p = p.next;
            index -= 1;
        }
        return p.item;
    }

    private Item getRec(DLList lst, int index) {
        if (index == 0) {
            return lst.item;
        } else if (index >= size) {
            return null;
        } else {
            return getRec(lst.next, index - 1);
        }
    }

    public Item getRecursive(int index) {

        return getRec(sentinel.next, index);
    }

    public static void main(String[] args) {
        LinkedListDeque x = new LinkedListDeque();
        x.printDeque();
        x.addFirst(1);
        System.out.print("the size=" + x.size() +"\n");
        x.addFirst(2);
        x.addFirst(3);
        x.addFirst(4);
System.out.print("the size=" + x.size() +"\n");
        x.addLast(7);
        x.addFirst(8);
        x.printDeque();
System.out.print("the item for get() is: " + x.get(2) + "\n");
System.out.print("the item for getRec() is: " + x.getRecursive(2) + "\n");
        x.printDeque();
        x.removeFirst();
        x.printDeque();
        x.removeLast();
        x.printDeque();
        x.removeFirst();
        x.printDeque();
        x.removeFirst();
        x.printDeque();
        x.removeFirst();
        x.printDeque();
    }
}
