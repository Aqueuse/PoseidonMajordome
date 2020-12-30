package projectmanagement;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.util.Objects;

public class FileTree extends JTree {
    public FileTree(String path) {
        super(scan(new File(path)));
    }

    public static DefaultMutableTreeNode scan (File node) {
        DefaultMutableTreeNode fileTree = new DefaultMutableTreeNode(node.getName());

        if (node.isDirectory())
            for (File child: Objects.requireNonNull(node.listFiles())) {
                fileTree.add(scan(child));
            }

        return fileTree;
    }
}
