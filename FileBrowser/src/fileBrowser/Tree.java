package fileBrowser;

import java.io.File;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class Tree{
    int size;
    int countClick;
    Tree []childTreeDirectory;
    curFile location;
    boolean eX = false;
    ViewGenerator vTp;
    BorderPane borderPane;
    TreeView treeView;
    TreeItem <String> []treeItem;
    TreeItem <String> CurrPos;
    TreeItem <String> rootNode;
    TreeItem <String> first;
    File[] listFiles;
    
    public Tree(BorderPane border,File f,curFile L,ViewGenerator view,TreeItem selectNode,TreeItem root) {
        first=new TreeItem("\\");
        borderPane = border;
        countClick=0;
        location = L;
        vTp = view;
        if (selectNode!=null) CurrPos = selectNode;
        else{
            String fi=f.getName();
            ImageView ii = new GetIcon(f).getImage();
            CurrPos = new TreeItem(fi,ii);
            rootNode = CurrPos;
      //      File []roots=File.listRoots();
        //    for(File fe:roots ){
          //      String s=fe.getAbsolutePath();
            //    ImageView im = new GetIcon(fe).getImage();
              //  rootNode.getChildren().add(new TreeItem(s,im));
              
          //  }
        }
        
        listFiles = f.listFiles();
        if(listFiles==null) size = 0;
        else size = listFiles.length;
        treeItem = new TreeItem[size];
        childTreeDirectory = new Tree[size];
        for(int i=0;i<size;i++){
            ImageView iViewT = new GetIcon(listFiles[i]).getImage();
            String SS =listFiles[i].getName();
            treeItem[i] = new TreeItem(SS,iViewT);
            CurrPos.getChildren().add(treeItem[i]);   
        } 
       /* int treeItemC=0;
        for(File lf:listFiles){
            String SS =lf.getName();
            ImageView iViewT = new GetIcon(lf).getImage();
            TreeItem tI = new TreeItem(SS,iViewT);
            treeItem[treeItemC]=tI;
            CurrPos.getChildren().add(tI);
            treeItemC++;
        }
        */
    }
    public void TreeV(){
     //   rootNode.setExpanded(true);
        treeView = new TreeView<>(rootNode);
        treeView.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent mouseEvent){            
                TreeItem<String> item = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
                if(item==null) Modificate_();
                else {
                    String addressItem = (String) item.getValue();
                    while(item.getParent()!=rootNode){
                        item = item.getParent();
                        addressItem = item.getValue() +"/"+ addressItem;
                    }
                //    addressItem=item.getValue()+"/"+addressItem;
                location.set(new File(addressItem));
                vTp.generate(location,borderPane);
                }   
            }
        });
        treeView.setMaxSize(200, 500);
        borderPane.setLeft(treeView);
    }
    
    public void Modificate_() {
        if(CurrPos.isExpanded()){
            if(eX==false){
                for(int i=0;i<size;i++){
                    if(listFiles[i].isDirectory()){
                        childTreeDirectory[i] = new Tree(borderPane,listFiles[i],location,vTp,treeItem[i],rootNode);
                    }
                }
                eX=true;
            }
            else{
                for(int i=0;i<size;i++){
                    if(listFiles[i].isDirectory()){
                        childTreeDirectory[i].Modificate_();
                    }
                }
            }
        }
        if(CurrPos == rootNode) TreeV();
    }
}
