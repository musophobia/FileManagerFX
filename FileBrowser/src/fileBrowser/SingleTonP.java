/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileBrowser;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author USER
 */
public class SingleTonP {
    private static final SingleTonP Instance=new SingleTonP();
    TreeItem <String> root;
    TreeView <String> tree;
    BorderPane borderPane;
    Scene scene;
    public SingleTonP() {
        borderPane = new BorderPane();
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        File dir = new File(s);
        curFile CF = new curFile(dir);
        ViewGenerator viewFactory = new ViewGenerator();
        Tree TD = new Tree(borderPane,dir,CF,viewFactory,null,null);
        ButtonBox B = new ButtonBox(CF,viewFactory,borderPane);
        viewFactory.generate(CF,borderPane);
        TD.TreeV();
        scene = new Scene(borderPane,800,600);
       
        
    }
    public static SingleTonP getInstance(){
        return Instance;
    }
    
    
}
