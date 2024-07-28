package com.pixabyte.helpdeskapi.problem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeClonerTest {

    @Test
    void testSomething() {
        var nodeCloner = new NodeCloner();
        Node f = new Node("F", null, null);
        Node b = new Node("B", f, null);
        Node d = new Node("D", b, f);
        Node c = new Node("C", d, b);
        Node a = new Node("A", c, c);
        f.setOther(d);
        b.setOther(b);
        nodeCloner.clone(a);
    }

}