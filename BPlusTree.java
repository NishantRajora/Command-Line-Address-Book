import java.util.Collections;

public class BPlusTree<K extends Comparable<K>, V> {
    private BPlusTreeNode<K, V> root;
    private final int maxKeys;

    // Constructor for B+ Tree with specified order
    public BPlusTree(int order) {
        this.root = new BPlusTreeNode<>(true);
        this.maxKeys = order - 1; // Max keys in a node
    }

    // Insert method for B+ Tree
    public void insert(K key, V value) {
        BPlusTreeNode<K, V> rootNode = root;
        if (rootNode.keys.size() == maxKeys) {
            BPlusTreeNode<K, V> newRoot = new BPlusTreeNode<>(false);
            newRoot.children.add(rootNode);
            splitChild(newRoot, 0);
            root = newRoot;
        }
        insertNonFull(root, key, value);
    }

    private void splitChild(BPlusTreeNode<K, V> parentNode, int index) {
        BPlusTreeNode<K, V> nodeToSplit = parentNode.children.get(index);
        int medianIndex = maxKeys / 2;
        
        BPlusTreeNode<K, V> newNode = new BPlusTreeNode<>(nodeToSplit.isLeaf);
        parentNode.keys.add(index, nodeToSplit.keys.get(medianIndex));
        parentNode.children.add(index + 1, newNode);

        newNode.keys.addAll(nodeToSplit.keys.subList(medianIndex + 1, nodeToSplit.keys.size()));
        if (nodeToSplit.isLeaf) {
            newNode.values.addAll(nodeToSplit.values.subList(medianIndex + 1, nodeToSplit.values.size()));
            newNode.next = nodeToSplit.next;
            nodeToSplit.next = newNode;
            nodeToSplit.values.subList(medianIndex, nodeToSplit.values.size()).clear();
        } else {
            newNode.children.addAll(nodeToSplit.children.subList(medianIndex + 1, nodeToSplit.children.size()));
            nodeToSplit.children.subList(medianIndex + 1, nodeToSplit.children.size()).clear();
        }
        nodeToSplit.keys.subList(medianIndex, nodeToSplit.keys.size()).clear();
    }

    private void insertNonFull(BPlusTreeNode<K, V> node, K key, V value) {
        int i = Collections.binarySearch(node.keys, key);
        if (node.isLeaf) {
            if (i >= 0) {
                node.values.set(i, value);
            } else {
                i = -i - 1;
                node.keys.add(i, key);
                node.values.add(i, value);
            }
        } else {
            i = i >= 0 ? i + 1 : -i - 1;
            BPlusTreeNode<K, V> child = node.children.get(i);
            if (child.keys.size() == maxKeys) {
                splitChild(node, i);
                if (key.compareTo(node.keys.get(i)) > 0) {
                    i++;
                }
            }
            insertNonFull(node.children.get(i), key, value);
        }
    }

    // Search for a key in the B+ Tree
    public V search(K key) {
        BPlusTreeNode<K, V> currentNode = root;
        while (!currentNode.isLeaf) {
            int i = Collections.binarySearch(currentNode.keys, key);
            i = i >= 0 ? i + 1 : -i - 1;
            currentNode = currentNode.children.get(i);
        }
        int index = Collections.binarySearch(currentNode.keys, key);
        if (index >= 0) {
            return currentNode.values.get(index);
        }
        return null; // Not found
    }
}
