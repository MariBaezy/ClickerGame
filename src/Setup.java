import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Setup extends Application {

    private Button[][] movingButtons;
    private Timer timer;
    private final String[] shapes = {"Square", "Triangle", "Circle", "Diamond"};

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Shape Clicker");

        GridPane gridPane = new GridPane();
        gridPane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> {
            if (movingButtons == null) {
                addMovingButtons(gridPane);
                startButton.setVisible(false);
                startGame();
            }
        });

        startButton.setPrefSize(200, 50);
        gridPane.add(startButton, 0, 1);

        Scene scene = new Scene(gridPane, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void startGame() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    for (Button[] row : movingButtons) {
                        for (Button button : row) {
                            button.setGraphic(null);
                        }
                    }
                });
            }
        }, 10000); // Shows buttons for 10 seconds
    }

    private void addMovingButtons(GridPane gridPane) {
        movingButtons = new Button[3][3];
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button();
                button.setPrefSize(150, 150);
                String shape = shapes[random.nextInt(shapes.length)];
                switch (shape) {
                    case "Square":
                        button.setGraphic(createSquare());
                        break;
                    case "Triangle":
                        button.setGraphic(createTriangle());
                        break;
                    case "Circle":
                        button.setGraphic(createCircle());
                        break;
                    case "Diamond":
                        button.setGraphic(createDiamond());
                        break;
                }
                movingButtons[i][j] = button;
                gridPane.add(button, j, i);
            }
        }
    }

    private Rectangle createSquare() {
        Rectangle square = new Rectangle(100, 100);
        square.setFill(Color.BLACK);
        return square;
    }

    //creates circle object
        private Circle createCircle() {
        Circle circle = new Circle(50);
        circle.setFill(Color.BLACK);
        return circle;
    }

    //creates triangle object
        private Polygon createTriangle() {
        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(50.0, 0.0,
                100.0, 100.0,
                0.0, 100.0);
        triangle.setFill(Color.BLACK);
        return triangle;
    }

    //creates diamond object
        private Polygon createDiamond() {
        Polygon diamond = new Polygon();
        diamond.getPoints().addAll(50.0, 0.0,
                100.0, 50.0,
                50.0, 100.0,
                0.0, 50.0);
        diamond.setFill(Color.BLACK);
        return diamond;
    }
}















