package ch.netzwerg.viz;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;

import java.util.List;
import java.util.function.BiFunction;

public final class Viz {

    public static <T> VizWithData<T> bind(ObservableList<T> data) {
        return new VizWithData<>(data);
    }

    public static final class VizWithData<T> {

        private final ObservableList<T> data;
        private final BiFunction<T, Integer, ? extends Node> onEnterCallback;

        public VizWithData(ObservableList<T> data) {
            this(data, (d, i) -> new Group());
        }

        public VizWithData(ObservableList<T> data, BiFunction<T, Integer, ? extends Node> onEnterCallback) {
            this.data = data;
            this.onEnterCallback = onEnterCallback;
        }

        public VizWithData onEnter(BiFunction<T, Integer, ? extends Node> callback) {
            return new VizWithData<>(data, callback);
        }

        public Group create() {
            Group group = new Group();
            data.addListener((ListChangeListener<T>) l -> {
                List<? extends T> added = l.getAddedSubList();
                onEnter(group, added);
            });

            onEnter(group, data);
            return group;
        }

        // TODO unbind (remove listeners)

        private void onEnter(Group group, List<? extends T> added) {
            for (int i = 0; i < added.size(); i++) {
                T d = added.get(i);
                Node node = onEnterCallback.apply(d, i);
                group.getChildren().add(node);
            }
        }

    }

}
