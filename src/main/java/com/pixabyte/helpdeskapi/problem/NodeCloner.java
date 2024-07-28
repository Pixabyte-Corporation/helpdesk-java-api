package com.pixabyte.helpdeskapi.problem;

import java.util.HashMap;
import java.util.Map;

public class NodeCloner {

    public Node clone(Node root) {
        Map<Node, Node> mapOriginalToClone = new HashMap<>();
        Node current = root.getNext();

        Node rootClone = Node.create(root.getContent());
        mapOriginalToClone.put(root, rootClone);
        Node latest = rootClone;

        while (current != null) {
            Node newNode = Node.create(current.getContent());
            mapOriginalToClone.put(current, newNode);
            latest.setNext(newNode);
            latest = newNode;
            current = current.getNext();
        }

        current = root;
        while (current != null) {
            Node currentCloneEquivalent = mapOriginalToClone.get(current);
            Node currentOtherCloneEquivalent = mapOriginalToClone.get(current.getOther());
            currentCloneEquivalent.setOther(currentOtherCloneEquivalent);
            current = current.getNext();
        }
        printList(root);
        System.out.println("-----");
        System.out.println("-----");
        System.out.println("-----");
        printList(rootClone);
        return null;
    }

    public void printList(Node root) {
        Node current = root;
        while (current != null) {
            String next = current.getNext() != null? current.getNext().getContent(): "null";
            String other = current.getOther() != null? current.getOther().getContent(): "null";
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Node Content: " + current.getContent() + "--" + System.identityHashCode(current) + "--");
            stringBuilder.append(" Next: " + next);
            stringBuilder.append(" Other: " + other);
            System.out.println(stringBuilder.toString());
            current = current.getNext();
        }

    }

}
