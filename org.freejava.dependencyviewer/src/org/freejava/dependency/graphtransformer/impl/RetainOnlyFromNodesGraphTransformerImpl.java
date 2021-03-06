package org.freejava.dependency.graphtransformer.impl;

import java.util.HashSet;
import java.util.Set;

import org.freejava.dependency.graphtransformer.GraphTransformer;
import org.freejava.dependency.model.Edge;
import org.freejava.dependency.model.Graph;
import org.freejava.dependency.model.Vertex;

public class RetainOnlyFromNodesGraphTransformerImpl<T> implements GraphTransformer<T> {

    public Graph<T> transform(Graph<T> graph) {

        Set<Vertex<T>> fromNodes = new HashSet<Vertex<T>>();
        for (Edge<T> edge : graph.getEdges()) {
            fromNodes.add(edge.getFrom());
        }

        Set<Edge<T>> edges = new HashSet<Edge<T>>();
        for (Edge<T> edge : graph.getEdges()) {
            if (fromNodes.contains(edge.getFrom()) && fromNodes.contains(edge.getTo())) {
                edges.add(edge);
            }
        }

        Graph<T> result = new Graph<T>(edges);

        return result;
    }


}