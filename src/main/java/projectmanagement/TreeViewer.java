package projectmanagement;

import globalwindow.TreeNavigator;

public class TreeViewer {
    public static void exploreProject(String sourceDirectoryLocation) {
//        JScrollPane scrollPane = new JScrollPane();
        FileTree projectTree = new FileTree(sourceDirectoryLocation);

        projectTree.setVisibleRowCount(15);
        projectTree.setRootVisible(true);
        projectTree.setVisible(true);

//        scrollPane.setPreferredSize(new Dimension(TreeNavigator.treePanel.getWidth(),TreeNavigator.treePanel.getHeight()));

//      scrollPane.add(projectTree);
        TreeNavigator.treePanel.add(projectTree);
        TreeNavigator.treePanel.revalidate();
    }
}


