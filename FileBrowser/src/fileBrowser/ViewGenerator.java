package fileBrowser;

import javafx.scene.layout.BorderPane;

public class ViewGenerator {
    int flag;
    
    public ViewGenerator() {
        flag = 0;
    }

    public void setView(int i) { 
        flag = i;
    }

    public int getView() {
        return flag;
    }
    
    public void generate(curFile cF, BorderPane border){
        if(flag == 0){
            ListsView listView = new ListsView(cF,border);
           // return listView;
            listView.vIEW();
        }
        else{
            TilesView tilesView = new TilesView(cF,border);
         //   return tilesView;
            tilesView.vIEW();
        }
    }
}
