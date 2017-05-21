package ch.netzwerg.viz;

import ch.netzwerg.viz.scale.ScaleLinear;
import ch.netzwerg.viz.scale.ScaleOrdinal;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class VizApp extends Application {

    private static final double WIDTH = 800;
    private static final double HEIGHT = 800;
    private static final String TITLE = "VizFX Demo";

    private final class Person {

        private final String name;
        private final int age;
        private final ShirtSize shirtSize;

        private Person(String name, int age, ShirtSize shirtSize) {
            this.name = name;
            this.age = age;
            this.shirtSize = shirtSize;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public ShirtSize getShirtSize() {
            return shirtSize;
        }

    }

    private enum ShirtSize {
        S, M, L
    }

    @Override
    public void start(Stage stage) {
        ObservableList<Person> people = FXCollections.observableArrayList(
                new Person("alice", 42, ShirtSize.M),
                new Person("bob", 50, ShirtSize.L),
                new Person("emma", 13, ShirtSize.S)
        );

        ScaleLinear xScale = new ScaleLinear(0, 2, 0d, 200d);
        ScaleLinear yScale = new ScaleLinear(13d, 50d, 100d, 0);
        ScaleOrdinal<ShirtSize, Color> colorScale = new ScaleOrdinal<>(
                Color.YELLOW,
                Color.DARKORANGE,
                Color.DARKRED
        );

        Group viz = Viz
                .bind(people)
                .onEnter((d, i) -> {
                    Label label = new Label(d.getName());
                    label.setTextAlignment(TextAlignment.CENTER);

                    Circle circle = new Circle(10);
                    Color fill = colorScale.toScreen(d.getShirtSize());

                    circle.setFill(fill);

                    VBox box = new VBox(label, circle);
                    box.setAlignment(Pos.CENTER);

                    box.setTranslateX(xScale.toScreen(i));
                    box.setTranslateY(yScale.toScreen(d.getAge()));

                    return box;

                })
                .create();

        Pane container = new StackPane(viz);

        Scene scene = new Scene(container);
        stage.setScene(scene);

        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.setTitle(TITLE);
        stage.show();

    }

}
