// Copyright 2011-2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.security.zynamics.zylib.gui.zygraph.editmode.actions;

import com.google.security.zynamics.zylib.gui.zygraph.editmode.IStateAction;
import com.google.security.zynamics.zylib.gui.zygraph.editmode.helpers.CMouseCursorHelper;
import com.google.security.zynamics.zylib.yfileswrap.gui.zygraph.AbstractZyGraph;
import com.google.security.zynamics.zylib.yfileswrap.gui.zygraph.IZyEditModeListener;
import com.google.security.zynamics.zylib.yfileswrap.gui.zygraph.edges.ZyGraphEdge;
import com.google.security.zynamics.zylib.yfileswrap.gui.zygraph.edges.ZyInfoEdge;
import com.google.security.zynamics.zylib.yfileswrap.gui.zygraph.editmode.states.CEdgeClickedRightState;
import com.google.security.zynamics.zylib.yfileswrap.gui.zygraph.nodes.ZyGraphNode;

import java.awt.event.MouseEvent;


public class CDefaultEdgeClickedRightAction<NodeType extends ZyGraphNode<?>, EdgeType extends ZyGraphEdge<?, ?, ?>>
    implements IStateAction<CEdgeClickedRightState<NodeType, EdgeType>> {
  @Override
  public void execute(final CEdgeClickedRightState<NodeType, EdgeType> state, final MouseEvent event) {
    CMouseCursorHelper.setDefaultCursor(state.getGraph());

    final AbstractZyGraph<NodeType, EdgeType> graph = state.getGraph();

    @SuppressWarnings("unchecked")
    final EdgeType edgeT = (EdgeType) state.getEdge();
    if (edgeT instanceof ZyInfoEdge) {
      return;
    }

    final double x = graph.getEditMode().translateX(event.getX());
    final double y = graph.getEditMode().translateY(event.getY());

    for (final IZyEditModeListener<NodeType, EdgeType> listener : state.getStateFactory()
        .getListeners()) {
      try {
        listener.edgeClicked(edgeT, event, x, y);
      } catch (final Exception exception) {
        // TODO: (timkornau): implement a useful logging.
      }
    }
  }
}
