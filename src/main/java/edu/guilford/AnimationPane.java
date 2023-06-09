package edu.guilford;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class AnimationPane extends Pane{
    //we choose Pane so we can place and move obects anywhere we want
    //attributes for the size of the scene where this will displayed
    private int width;
    private int height;
    private Circle circEvent;
    private int dx = 2;
    private int dy = 3;

    //constructor
    public AnimationPane(int width, int height){
        this.width = width;
        this.height = height;

        //let us add a circle to the pane at a random location
        Circle circle = new Circle(50, Color.RED);
        circle.setCenterX(Math.random()*width);
        circle.setCenterY(Math.random()*height);
        this.getChildren().add(circle);

        //javafx animation are based on transitions: a starting point, an ending point, and a duration
        //Example: fade the circle from opacity 1 to opacity 0 (transparent) over 2 seconds
        //Build a fade transition objec twith the circle as the target
        FadeTransition ft = new FadeTransition(Duration.millis(2000), circle);
        //Set the properties of the transition
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setCycleCount(4);
        ft.setAutoReverse(true);
        //Start the transition
        //ft.play();

        //let us move the circle around the screen using a specified path
        //we will use a PathTransition object (from the animation package), with the circle as a target
        //now it is a PathTransition object, so we need to specify the path
        Path path = new Path();
        path.getElements().add(new MoveTo(200, 300));
        path.getElements().add(new LineTo(300, 200));
        path.getElements().add(new CubicCurveTo(100, 100, 200, 200, 50, 50));
        //Now we can add or build the PathTransition object with this path and the circle as the target
        PathTransition pt = new PathTransition(Duration.millis(2000), path, circle);
        //Set the properties of the transition
        pt.setCycleCount(4);
        pt.setAutoReverse(true);
        //Start the transition
        //pt.play();

        //keyframe animation: set particular goals to be attained at particular times
        //we will use a KeyFrame object (from the animation package); done with a keyframe and 
        //one or more keyvalues
        KeyValue kv = new KeyValue(circle.centerXProperty(), 600);
        KeyValue kv2 = new KeyValue(circle.centerYProperty(), 100);
        //take two seconds to get there
        KeyFrame kf = new KeyFrame(Duration.millis(2000), kv, kv2);
        //add another frame to change the radius of the circle
        KeyValue kv3 = new KeyValue(circle.radiusProperty(), 200);
        KeyFrame kf2 = new KeyFrame(Duration.millis(2), kv3);
        Timeline tl = new Timeline(kf, kf2);

        //By default, all transitions are done in parallel, but we can do them sequentially instead
        //we will use a sequential transition object (from the animation package)
        SequentialTransition seqT = new SequentialTransition(ft, pt, tl);
        //Start the transition
        //seqT.getChildren().addAll(ft, pt);
        seqT.play();

        //Let us add a circle to the pane at a random location
        circEvent = new Circle(50, Color.THISTLE);
        getChildren().add(circEvent);

        circEvent.setCenterX(Math.random()*width);
        circEvent.setCenterY(Math.random()*height);
        Timeline tlCirc = new Timeline();
        KeyFrame kfCirc = new KeyFrame(Duration.millis(10), new CircAnimate());
        tlCirc.getKeyFrames().add(kfCirc);
        tlCirc.setCycleCount(Timeline.INDEFINITE);
        tlCirc.play();

}

     //inner class to animate teh circle; it is an eventhandler interface specifically for 
        //ActionEvents
        private class CircAnimate implements EventHandler<ActionEvent> {
            @Override
            public void handle(ActionEvent e){
                //move the circle
                circEvent.setCenterX(circEvent.getCenterX() + dx);
                circEvent.setCenterY(circEvent.getCenterY() + dy);
                //check for boundaries
                if(circEvent.getCenterX() < circEvent.getRadius() || circEvent.getCenterX() > width - circEvent.getRadius()){
                    dx *= -1;
                }
                if(circEvent.getCenterY() < circEvent.getRadius() || circEvent.getCenterY() > height - circEvent.getRadius()){
                    dy *= -1;
                }
            }
        }
}
