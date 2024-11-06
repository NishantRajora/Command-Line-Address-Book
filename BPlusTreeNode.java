import java.util.ArrayList;
import java.util.List;

public class BPlusTreeNode<K extends Comparable<K>, V> {
    boolean isLeaf;
    List<K> keys;
    List<BPlusTreeNode<K, V>> children; // For internal nodes
    List<V> values; // Only used in leaf nodes
    BPlusTreeNode<K, V> next; // Pointer to the next leaf node (for leaf nodes only)

    // Constructor
    public BPlusTreeNode(boolean isLeaf) {
        this.isLeaf = isLeaf;
        this.keys = new ArrayList<>();
        if (isLeaf) {
            this.values = new ArrayList<>();
            this.next = null;
        } else {
            this.children = new ArrayList<>();
        }
    }
}
