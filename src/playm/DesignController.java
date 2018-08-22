/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package playm;

import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javax.swing.Timer;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class DesignController implements Initializable {

    File f=new File("F:\\dj");
    File f1[]=f.listFiles();
    Media md;
    MediaPlayer m;
    @FXML Slider s1;
    @FXML Slider s2;
    
    RotateTransition r=new RotateTransition();
    PathTransition p=new PathTransition();

    @FXML ImageView im;
    @FXML ImageView i;
    @FXML ImageView ic;
    @FXML ImageView icd;
    @FXML ImageView isd;
    @FXML ImageView mf;
    @FXML ImageView rewind;
    @FXML ListView <String> li;
    @FXML ImageView id;
    
    @FXML Label songname=new Label();
    @FXML Label tym=new Label();
    @FXML Label tim=new Label();
   
    ActionListener a1;
    Timer t;
    
  
   @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        
        for(File ff:f1)
    {
    
        li.getItems().add(ff.getAbsolutePath());

       
    }
    
   li.setVisible(false);
    
              }
    
 
 
 
 
 
    
    public void button(MouseEvent e){
     
        
        isd.setVisible(true);
        ic.setVisible(false);
        id.setVisible(true);
        mf.setVisible(true);
        
     
     
     String path=li.getSelectionModel().getSelectedItem();
        md=new Media(new File(path).toURI().toString());
        m=new MediaPlayer(md);
        m.play();
    
     r.setNode(im);
     r.setDuration(Duration.seconds(40));
     r.setToAngle(360);
     r.setCycleCount(RotateTransition.INDEFINITE);
     r.play();
           
     
     
     
     songname.setTextFill(Color.PINK);
        songname.setText(path);
       Line l=new Line(200,00,450,00);
       p.setDuration(Duration.seconds(20));
       p.setNode(songname);
       p.setCycleCount(PathTransition.INDEFINITE);
       p.setPath(l);
       p.play();
        
       
       
       m.setOnReady(new Runnable(){

            @Override
            public void run() {
     
           tym.setTextFill(Color.WHITE);
           tym.setText(String.valueOf(m.getTotalDuration().toMinutes()));
            }
            
            
        });
        
        s2.setValue(m.getVolume()*100);
        s2.valueProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable observable) {
           m.setVolume(s2.getValue()/100);
           
            }
          
       });
        a1=new ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                   
                
                tim.textProperty().bind(Bindings.createStringBinding(()->{
        Duration time=m.getCurrentTime();
        return String.format("%02d:%04.1f",(int)time.toMinutes()%60,time.toSeconds()%60);
    },
            m.currentTimeProperty()));
 tim.setTextFill(Color.WHITE);
      
         s1.setMax(md.getDuration().toSeconds());
                
          if(s1.getValue()<s1.getMax())
          {
              
             
           s1.setValue(s1.getValue()+1);
        
                
   
          }
            else
          {
             
              
              
          
              t.stop();
              
              }
            
            }
        };
        t=new Timer(1000,a1);
        t.start();
         
        }
    
   
    
    
    
    
    public void pause(MouseEvent e){

    id.setVisible(false);
    
    ic.setVisible(true);
      
    m.pause();
    r.pause();
    p.pause();
    t.stop();
     }
    
   
    
    public void play(MouseEvent e){
        
        
        
       ic.setVisible(false);
       
       id.setVisible(true);
       
       m.play();
       r.play();
       p.play();
       t.start();
   }

    
    
    
    public void musiclist(MouseEvent ae){

        li.setVisible(true);
        
        
       i.setVisible(false);
    }
 
    
    
    
    
    public void musiclisthide(ActionEvent ae){

        li.setVisible(false);
        
       i.setVisible(true);
    
    }

        

    
    
    
    public void back(MouseEvent e){
  
       m.seek(m.getStartTime());
      s1.setValue(0);
       m.play();
       
    } 




    public void volumemute(ActionEvent e){
   
     s2.setValue(m.getVolume()/100);
     }





    public void next(MouseEvent e){
   
            m.stop();
            r.pause();
     
     p.pause();
     id.setVisible(false);
     ic.setVisible(true);
     s1.setValue(0);
     t.stop();
}

    }    
    

