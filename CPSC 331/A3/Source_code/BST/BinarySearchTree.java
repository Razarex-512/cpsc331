public class BinarySearchTree<T extends Comparable<T>> {
    private class Node<T extends Comparable<T>> {
        private T value;
        private Node<T> left, right;

        public Node() {
            left = null;
            right = null;
        }
    }

    private Node<T> root;

    public BinarySearchTree() {
        root = null;
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public boolean isFull() {
        return false;
    }

    public void clear() {
        root = null;
    }

    public int size() {
        return recSize(root);
    }

    private int recSize(Node<T> root) {
        if (root == null)
            return 0;
        else
            return recSize(root.left) + recSize(root.right) + 1;
    }

    public boolean contains(T item) {
        return recContains(item, root);
    }

    private boolean recContains(T item, Node<T> root) {
        if (root == null)
            return false;
        else if (item.compareTo(root.value) < 0)
            return recContains(item, root.left);
        else if (item.compareTo(root.value) > 0)
            return recContains(item, root.right);
        else
            return true;
    }

    public void add(T item) {
        root = recAdd(item, root);
    }

    private Node<T> recAdd(T item, Node<T> root) {
        if (root == null) {
            root = new Node<>();
            root.value = item;
        } else if (item.compareTo(root.value) < 0)
            root.left = recAdd(item, root.left);
        else
            root.right = recAdd(item, root.right);
        return root;
    }

    public void remove(T item) {
        root = recRemove(item, root);
    }

    private Node<T> recRemove(T item, Node<T> root) {
        if (root == null)
            return null;

        if (item.compareTo(root.value) < 0)
            root.left = recRemove(item, root.left);
        else if (item.compareTo(root.value) > 0)
            root.right = recRemove(item, root.right);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            else {
                root.value = Minimum(root.right).value;
                root.right = recRemove(root.value, root.right);
            }
        }
        return root;
    }

    private Node<T> Minimum(Node<T> root) {
        while (root.left != null)
            root = root.left;
        return root;
    }

    public BinarySearchTree<T> removeOddValues() {
        BinarySearchTree<T> treeEven = new BinarySearchTree<>();
        root = recRemoveOddValues(root, treeEven);
        return treeEven;
    }

    private Node<T> recRemoveOddValues(Node<T> root, BinarySearchTree<T> treeEven) {
        if (root == null)
            return null;

        root.left = recRemoveOddValues(root.left, treeEven);
        root.right = recRemoveOddValues(root.right, treeEven);

        if (root.value != null && ((Integer) root.value) % 2 != 0) {
            treeEven.add(root.value);
            return recRemove(root.value, root);
        }

        return root;
    }

    public int height() {
        return recHeight(root);
    }

    private int recHeight(Node<T> root) {
        if (root == null)
            return -1;

        int leftHeight = recHeight(root.left);
        int rightHeight = recHeight(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public void printInOrder() {
        System.out.println("In-order traversal:");
        printInOrder(root);
        System.out.println();
    }

    private void printInOrder(Node<T> node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.value + " ");
            printInOrder(node.right);
        }
    }
}