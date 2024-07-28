package com.pixabyte.helpdeskapi.problem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node {
    private String content;
    private Node next;
    private Node other;

    public Node(String content, Node next, Node other) {
        this.content = content;
        this.next = next;
        this.other = other;
    }

    public static Node create(String content) {
        return new Node(content, null, null);
    }


}
