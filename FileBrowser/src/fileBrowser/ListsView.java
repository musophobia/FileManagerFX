
package fileBrowser;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class ListsView extends ViewTyp{
    BorderPane border;
    curFile curF;
    File []list;
    TableView <FileAdapter> table;
    public ListsView(curFile L,BorderPane bor) {
        border = bor;
        curF = L;
        File dirF = curF.get();
        list = dirF.listFiles();
       }
    public void vIEW(){
        ObservableList<FileAdapter> files = FXCollections.observableArrayList();
        for(File file:list){
            files.add(new FileAdapter(file));
        }
        TableColumn<FileAdapter, ImageView> iconColumn = new TableColumn<>("Icon");
        iconColumn.setCellValueFactory(new PropertyValueFactory<>("image")); 
        TableColumn<FileAdapter, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<FileAdapter, String> sizeColumn = new TableColumn<>("Size");
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        TableColumn<FileAdapter, String> dateColumn = new TableColumn<>("Date Modified");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        table = new TableView<>();
        table.setRowFactory(tab -> {
            TableRow<FileAdapter> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    FileAdapter rowData = row.getItem();
                    File rowFile = rowData.getF();
                    for(File ff:list){
                        if(ff==rowFile){
                            if(rowFile.isDirectory()){
                                curF.set(rowFile);
                                ListsView lnew = new ListsView(curF,border);
                                lnew.vIEW();
                            }
                            else try {
                                Desktop.getDesktop().edit(ff);
                            } catch (IOException ex) {
                                Logger.getLogger(ListsView.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                        }
                    }
                }
            });
            return row ;
        });
        table.setItems(files);
        table.getColumns().addAll(iconColumn, nameColumn, sizeColumn,dateColumn);
        border.setCenter(table);
    }
}
