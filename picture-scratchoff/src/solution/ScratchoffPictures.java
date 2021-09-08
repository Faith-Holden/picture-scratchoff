package solution;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;


public class ScratchoffPictures extends Application{
    Canvas pictureCanvas;
    Canvas coverCanvas = new Canvas();
    ArrayList<Point> revealedPoints = new ArrayList<>();
    int scratchSize = 64;

    public void start (Stage primaryStage){
        BorderPane root = new BorderPane();
        pictureCanvas = new Canvas();

        StackPane canvases = new StackPane();
        canvases.getChildren().addAll(pictureCanvas, coverCanvas);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        Image blueJay = new Image("resources\\images\\bluejay.jpg");
        Image chipmunk = new Image("resources\\images\\chipmunk.jpg");
        Image collie = new Image("resources\\images\\collie.jpg");
        Image elephants = new Image("resources\\images\\elephants.jpg");
        Image fawn = new Image("resources\\images\\fawn.jpg");
        Image lion = new Image("resources\\images\\lion.jpg");
        Image polarBear = new Image("resources\\images\\polar-bear.jpg");
        Image stork = new Image("resources\\images\\stork.jpg");
        Image [] images = new Image[] {blueJay, chipmunk, collie, elephants, fawn, lion, polarBear, stork};

        Button newDrawingButton = new Button("New Scratch-off");
        newDrawingButton.setOnAction(e->{
            Image image = images[(int)(Math.random()*8)];
            drawPictureCanvas(image);
            primaryStage.setMaxWidth(image.getWidth());
            primaryStage.setMaxHeight(image.getHeight());
            primaryStage.setMinHeight(image.getHeight());
            primaryStage.setMinWidth(image.getWidth());
            revealedPoints.clear();
            drawCoverCanvas();
        });

        Button clearCoverButton = new Button("Clear cover");
        clearCoverButton.setOnAction(e->{
            GraphicsContext graphicsContext = coverCanvas.getGraphicsContext2D();
            graphicsContext.clearRect(0,0,coverCanvas.getWidth(),coverCanvas.getHeight());
        });


        ToggleGroup sizeToggle = new ToggleGroup();
        Menu sizeMenu = new Menu("Scratch Size");
        RadioMenuItem tiny = new RadioMenuItem("tiny");
        tiny.setToggleGroup(sizeToggle);
        RadioMenuItem small = new RadioMenuItem("small");
        small.setToggleGroup(sizeToggle);
        RadioMenuItem medium = new RadioMenuItem("medium");
        medium.setToggleGroup(sizeToggle);
        RadioMenuItem large = new RadioMenuItem("large");
        large.setToggleGroup(sizeToggle);
        RadioMenuItem extraLarge = new RadioMenuItem("extra large");
        extraLarge.setToggleGroup(sizeToggle);
        RadioMenuItem huge = new RadioMenuItem("huge");
        huge.setToggleGroup(sizeToggle);
        RadioMenuItem giant = new RadioMenuItem("giant");
        giant.setToggleGroup(sizeToggle);
        sizeMenu.getItems().addAll(tiny, small, medium, large, extraLarge, huge, giant);
        MenuBar menuBar = new MenuBar(sizeMenu);

        int tinyInt = 9;
        int smallInt = 25;
        int medInt = 49;
        int largeInt = 64;
        int extraLargeInt = 81;
        int hugeInt = 100;
        int giantInt = 225;
        tiny.setOnAction(e-> setScratchSize( tinyInt));
        small.setOnAction(e-> setScratchSize( smallInt));
        medium.setOnAction(e-> setScratchSize( medInt));
        large.setOnAction(e-> setScratchSize( largeInt));
        extraLarge.setOnAction(e-> setScratchSize( extraLargeInt));
        huge.setOnAction(e-> setScratchSize( hugeInt));
        giant.setOnAction(e-> setScratchSize( giantInt));


        coverCanvas.setOnMousePressed(e->{
           doMousePressOrDrag(e);
        });
        coverCanvas.setOnMouseDragged(e->{
            doMousePressOrDrag(e);
        });


        HBox buttonsAndMenus = new HBox();
        buttonsAndMenus.getChildren().addAll(newDrawingButton, menuBar, clearCoverButton);
        buttonsAndMenus.setPrefHeight(30);



        root.setCenter(canvases);
        root.setTop(buttonsAndMenus);

        drawPictureCanvas(images[(int)(Math.random()*8)]);

        drawCoverCanvas();

        primaryStage.show();

    }


    private void drawPictureCanvas(Image picture){
        pictureCanvas.setWidth(picture.getWidth());
        pictureCanvas.setHeight(picture.getHeight());
        GraphicsContext graphicsContext = pictureCanvas.getGraphicsContext2D();
        graphicsContext.drawImage(picture, 0,0);


    }

    private void setScratchSize (int newSize){
        scratchSize = newSize;
    }

    private void drawCoverCanvas(){
        coverCanvas.setHeight(pictureCanvas.getHeight());
        coverCanvas.setWidth(pictureCanvas.getWidth());

        GraphicsContext graphicsContext = coverCanvas.getGraphicsContext2D();
        graphicsContext.clearRect(0,0,pictureCanvas.getWidth(),pictureCanvas.getHeight());
        graphicsContext.setFill(Color.rgb(100,100,100, 1));
        graphicsContext.fillRect(0,0,coverCanvas.getWidth(),coverCanvas.getHeight());
        PixelWriter pixWriter = graphicsContext.getPixelWriter();

        if(revealedPoints.size()>0){
            for(Point point : revealedPoints){
                pixWriter.setColor((int)point.xCoord, (int)point.yCoord, Color.rgb(100,100,100,.1));
            }
        }


    }

    private static class Point{
        double xCoord;
        double yCoord;

        Point(double xCoord, double yCoord){
            this.xCoord = xCoord;
            this.yCoord = yCoord;
        }
    }

    private void doMousePressOrDrag(MouseEvent e){

        double xLeft;
        double yTop;

        switch (scratchSize){
            case 9:
                xLeft = (e.getX()-1);
                yTop = (e.getY()/1)-1;
                for(int i = 0; i<3; i++){
                    for(int j=0; j<3; j++){
                        revealedPoints.add(new Point(xLeft+j, yTop+i));
                    }
                }
                break;
            case 25:
                xLeft = (e.getX()/1)-2;
                yTop = (e.getY()/1)-2;
                for(int i = 0; i<5; i++){
                    for(int j=0; j<5; j++){
                        revealedPoints.add(new Point(xLeft+j, yTop+i));
                    }
                }
                break;
            case 49:
                xLeft = (e.getX()/1)-3;
                yTop = (e.getY()/1)-3;
                for(int i = 0; i<7; i++){
                    for(int j=0; j<7; j++){
                        revealedPoints.add(new Point(xLeft+j, yTop+i));
                    }
                }
                break;
            case 64:
                xLeft = (e.getX()/1)-4;
                yTop = (e.getY()/1)-4;
                for(int i = 0; i<8; i++){
                    for(int j=0; j<8; j++){
                        revealedPoints.add(new Point(xLeft+j, yTop+i));
                    }
                }
                break;
            case 81:
                xLeft = (e.getX()/1)-5;
                yTop = (e.getY()/1)-5;
                for(int i = 0; i<9; i++){
                    for(int j=0; j<9; j++){
                        revealedPoints.add(new Point(xLeft+j, yTop+i));
                    }
                }
                break;
            case 100:
                xLeft = (e.getX()/1)-6;
                yTop = (e.getY()/1)-6;
                for(int i = 0; i<10; i++){
                    for(int j=0; j<10; j++){
                        revealedPoints.add(new Point(xLeft+j, yTop+i));
                    }
                }
                break;
            case 225:
                xLeft = (e.getX()/1)-7;
                yTop = (e.getY()/1)-7;
                for(int i = 0; i<15; i++){
                    for(int j=0; j<15; j++){
                        revealedPoints.add(new Point(xLeft+j, yTop+i));
                    }
                }
                break;
        }
        drawCoverCanvas();
    }

    public static void main(String[]args){
        launch(args);
    }
}
