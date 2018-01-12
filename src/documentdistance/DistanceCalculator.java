/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package documentdistance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author deb
 */
public class DistanceCalculator {
    
    HashMap<String, Integer> document1;
    HashMap<String, Integer> document2;
    int[] doc1Count;
    int[] doc2Count;
    DocumentPreparator prepper;
    double distance;
    
    public DistanceCalculator(String str1, String str2){
        this.prepper = new DocumentPreparator(str1, str2);
        
        this.document1 = prepper.document1;
        this.document2 = prepper.document2;
        this.doc1Count = prepper.document1Count;
        this.doc2Count = prepper.document2Count;
        
        for(int i = 0; i < doc1Count.length; i++){
            System.out.print(doc1Count[i] + ",");
        }
        
        System.out.println("");
        
        for(int i = 0; i < doc2Count.length; i++){
            System.out.print(doc2Count[i] + ",");
        }
        System.out.println("");
        
        /*
        for(Map.Entry<String,Integer> entry : document1.entrySet()){
            int doc2val = document2.get(entry.getKey());
            System.out.println(entry.toString() + " doc2 val= " + doc2val);
        }
       
        
        System.out.println("\n\n\n document1 len: " + document1.size());
        System.out.println("\n\n\n document2 len: " + document2.size());
        */

        this.distance = this.DocumentDistance(doc1Count, doc2Count);
        System.out.println("distance of two documents is: " + this.distance);
    }
     
    private double innerProd(int[] a, int[] b){
        double result = 0;
        if(a.length == b.length){
            for(int i = 0; i < a.length; i++){
                result += a[i] * b[i];
            }
        }else{
            System.out.println("a and b must be the same length for inner product");
        }
        
        return result;
    }
    
    private double norm(int[] v){
        double result = 0;
        for(int i = 0; i < v.length; i++){
            result += Math.pow(v[i], 2);
        }
        
        return Math.sqrt(result);
    }
    
    private double DocumentDistance(int[] a, int[] b){

        //study this
        double dotProd = innerProd(a,b);
        //study this
        double length = (norm(a) * norm(b));
        System.out.println("norm(a) = " + norm(a) + " norm(b) = " + norm(b));
        //radians degree issues???
        System.out.println("dot product: " + dotProd + "lenght: " + length + "dot/len a b = " + dotProd/length);
        //should check is division gives a num thats not correct for arccos func...
        double result = Math.acos(dotProd/length);
        if(Double.isNaN(result)) {
            result = 0;
        }
        return result;
    }
    
    public Stage outputGraphs(){
        Stage stage = new Stage();
        Group root = new Group();
        Canvas result = new Canvas(400, 300);
        if(this != null){
            GraphicsContext gc = result.getGraphicsContext2D();

            double angle = distance;
            
            int startX = (int)result.getWidth()/2;
            int startY = (int)result.getHeight()/2;
            int length = 100;

            double endX = startX + (int)(Math.cos(angle) * length);
            double endY = startY + (int)(Math.sin(angle) * length);
            
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(2); 
            gc.strokeLine(startX, startY, endX, endY);
            gc.strokeLine(startX, startY, startX+100, startY);
            
            gc.setStroke(Color.GREEN);
            gc.strokeLine(startX, startY, startX+100, startY);
            
            gc.setStroke(Color.RED);
            gc.strokeLine(startX, startY, startX, startY+100);

        }
        root.getChildren().add(result);
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        return stage;

    }
    
    public void updateCanvas(Canvas canvas){
        if(this != null){
            
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.setFill(Color.WHITE);
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());    
            double angle = this.distance;
            
            int startX = (int)10;
            int startY = 10;
            int length = 1000;

            double endX = startX + (int)(Math.cos(angle) * length);
            double endY = startY + (int)(Math.sin(angle) * length);
            
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(2); 
            gc.strokeLine(startX, startY, endX, endY);
            
            gc.setStroke(Color.GREEN);
            gc.strokeLine(startX, startY, startX+canvas.getWidth(), startY);
            
            gc.setStroke(Color.RED);
            gc.strokeLine(startX, startY, startX, startY+canvas.getHeight());
            

        }
    }
    
    
}
