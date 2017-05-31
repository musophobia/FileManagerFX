package fileBrowser;

import java.io.File;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class ButtonBox  implements EventHandler<MouseEvent>{
    BorderPane border;
    Button back,listVw,tileVw;
    curFile currentLocation;
    File file;
    ViewGenerator viewGen;
 //   ViewTyp viewFac;
    public ButtonBox(curFile cF,ViewGenerator view, BorderPane ber) {
        viewGen = view;
        border = ber;
        currentLocation = cF;
        file = cF.get();
        back = new Button("back");
        back.setOnMousePressed(this);
        listVw = new Button("ListView");
        listVw.setOnMousePressed(this);
        tileVw = new Button("TilesView");
        tileVw.setOnMousePressed(this);
        BorderPane aaa=new BorderPane();
        aaa.setPadding(new Insets(10,200,10,400));
        aaa.setTop(listVw);
        aaa.setRight(back);
        aaa.setBottom(tileVw);
        border.setBottom(aaa);
    }
    
    @Override
    public void handle(MouseEvent event) {
        if(event.getSource() == back){
            File f = currentLocation.get();
            if(f.getParentFile()!=null){
                currentLocation.set(f.getParentFile());
                viewGen.generate(currentLocation,border);
            }
            return;
        }
         if(event.getSource() == listVw){
            if(viewGen.getView() != 0){
                viewGen.setView(0);
                viewGen.generate(currentLocation,border);
            } 
            return;
        }
        if(event.getSource() == tileVw){
            if(viewGen.getView() != 1){
                viewGen.setView(1);
                viewGen.generate(currentLocation,border);
            } 
           return;
        }
       
       
        } 
       
}   
