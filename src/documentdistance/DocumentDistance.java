/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package documentdistance;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author deb
 */
public class DocumentDistance extends Application {
    
    TextArea tx1 = new TextArea();
    TextArea tx2 = new TextArea();
    Canvas graphs = new Canvas(800,300);

    DistanceCalculator dist;
    
    
    
    private VBox inputControls(){
        VBox result = new VBox();
        HBox texts = new HBox();
        tx1.textProperty().set(testStr1);
        tx2.textProperty().set(testStr2);
        

        texts.getChildren().addAll(tx1,tx2);
        texts.setPadding(new Insets(3,3,3,3));
        
        
        Button btnCompare = new Button("Compare");
        
        btnCompare.setOnAction(e->{
            StringBuilder text1 = new StringBuilder();
            text1.append(tx1.getText());
            StringBuilder text2 = new StringBuilder();
            text2.append(tx2.getText());
            dist = new DistanceCalculator(text1.toString(), text2.toString());
           
            dist.updateCanvas(graphs);
        });
        
        result.getChildren().addAll(texts,btnCompare);
        
        return result;
    }
    
    /*
     public Canvas primalCanvas(){

         Canvas canvas = new Canvas();
        GraphicsContext gc = canvas.getGraphicsContext2D();
            
            int startX = (int)canvas.getWidth()/2;
            int startY = (int)canvas.getHeight()+20;

            
            gc.setStroke(Color.GREEN);
            gc.strokeLine(startX, startY, startX+100, startY);
            
            gc.setStroke(Color.RED);
            gc.strokeLine(startX, startY, startX, startY+100);
            
            return canvas;
    }
    */
    
    @Override
    public void start(Stage primaryStage) {
                
        VBox texts = inputControls();
        tx1.setPrefSize(400, 600);
        tx2.setPrefSize(400, 600);
        VBox root = new VBox();
        
        
        root.getChildren().addAll(texts,graphs);

        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Document Distance");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private String testStr1 = "We provide you with five acceptance tests that include expected outputs. Those test files enumerated by Test1.java, ..., Test5.java. The last test case is perhaps the most challenging. Your application must conform to all these tests. You are encouraged to create your own test cases to test various edge cases such as empty string or file, two identical files.\n" +
"Hints\n" +
"\n" +
"    How to read words from a file? There are a few different ways to identify words, however the simplest one is to use the split() method along with a regular expression processor, namely split(\"\\\\W\").\n" +
"    Only consider words of length greater than 0! Therefore, check that each string has non-zero length before declaring that it is a word.\n" +
"    How to store words and their multiplicities? Before you insert a word into a map, you have to query the map. The get() method returns either null or the current frequency.\n" +
"    Be clever when you implement the innerProduct() method; try to avoid quadratic runtime implementation.\n" +
"    Be aware that processing large files might lead to an integer overflow, which will cause an incorrect distance between two documents. Your code must be able to handle all given input files.\n" +
"    Start early. Plan your time accordingly. \n" +
"\n" +
"Starter Code\n" +
"\n" +
"Download the starter code for assignment (zip file).\n" +
"\n" +
"Once you finish implementation, you must turn in only Distance.java file.";
    private String testStr2 = "Document similarities are measured based on the content overlap between documents. With the large number of text documents in our life, there is a need toautomatically process those documents for information extraction, similarity clustering, and search applications. There exist a vast number of complex algorithms to solve this problem. One of such algorithms is a cosine similarity - a vector based similarity measure. The cosine distance of two documents is defined by the angle between their feature vectors which are, in our case, word frequency vectors. The word frequency distribution of a document is a mapping from words to their frequency count.\n" +
"\n" +
"where \".\" denotes the dot-product of the two frequency vectors A and B, and ||A|| denotes the length (or norm) of a vector.\n" +
"Objectives\n" +
"\n" +
"    Gain experience in working with List, Set and Map interfaces.\n" +
"    Gain experience working with more realistic problems that are open-ended, with black- box specifications and requirements. \n" +
"\n" +
"Problem Statement\n" +
"\n" +
"In this lab you are to compute a distance (an angle) between two given documents or between two strings using the cosine similarity metric. You start with reading in the text and counting frequency of each word. The word frequency distribution for the text D is Java's Map from words to their frequency counts, which we'll denote as freq(D) . We view freq(D) as a vector of non-negative integers in N-dimensional space. For example, reading the string \"To be or not to be\" results in the following map\n" +
"\n" +
"{be=2, not=1, or=1, to=2}\n" +
"\n" +
"These 4 distinct words make a document representation as a 4-dimensional vector {2, 1, 1, 2} in term space.\n" +
"\n" +
"A word is a sequence of letters [a..zA..Z] that might include digits [0..9] and the undescore character. All delimiters are thrown away and not kept as part of the word. Here are examples of words:\n" +
"\n" +
"abcd\n" +
"abcd12\n" +
"abc_\n" +
"a12cd\n" +
"15111\n" +
"\n" +
"We'll treat all upper-case letters as if they are lower-case, so that \"CMU\" and \"cmu\" are the same word.\n" +
"\n" +
"The Euclidean norm of the frequency vector is defined by\n" +
"\n" +
"where xk denote frequencies for each word in the text. For the above example, the norm is\n" +
"\n" +
"The Dot product (or the inner product) of two frequency vectors X and Y is defined by\n" +
"\n" +
"Here we multiply frequencies xk and yk of the same word in both text documents.\n" +
"\n" +
"Finally, we define a distance between two documents D1 and D2 by using cosine similarity measurement: ";
}
