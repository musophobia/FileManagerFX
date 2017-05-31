package fileBrowser;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;

public class TilesView extends ViewTyp implements EventHandler<MouseEvent>{
    File []list;
    int size;
    BorderPane borderPane;
    Button []buttons;
    curFile curF;
    
    public TilesView(curFile CF,BorderPane Border) {
        curF = CF;
        borderPane = Border;
        File dir = curF.get();
        list = dir.listFiles();
        if(list==null) size = 0;
        else size = list.length;
        buttons = new Button[size];
        for(int i=0;i<size;i++){
            String s = list[i].getName();
            ImageView image = new GetIcon(list[i]).getImage();
            buttons[i] = new Button(s, image);
            buttons[i].setOnMousePressed(this);
            buttons[i].setMaxSize(60,80);
            buttons[i].setContentDisplay(ContentDisplay.TOP);
           
        }
    }
    public void vIEW(){
        TilePane tilePan=new TilePane();
        tilePan.setMinSize(540, 600);
        tilePan.setHgap(15);
        tilePan.setVgap(15);
        tilePan.getChildren().addAll(buttons);
        ScrollPane scroll = new ScrollPane(tilePan);
        borderPane.setCenter(scroll);
    }
    
    @Override
    public void handle(MouseEvent event) {
        if(event.getClickCount()==2){
            int t = 0;
            for(int i=0;i<size;i++){
                if(event.getSource() == buttons[i]) {
                    if(list[t].isDirectory()){
                        curF.set(list[t]);
                        TilesView Tview = new TilesView(curF,borderPane);
                        Tview.vIEW();
            }
            else   { try {
                Desktop.getDesktop().edit(list[i]);
                    } catch (IOException ex) {
                        Logger.getLogger(TilesView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    }
                        
           
                }
            }
            
        }
    }
}
