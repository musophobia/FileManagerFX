
package fileBrowser;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import static javafx.embed.swing.SwingFXUtils.toFXImage;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileSystemView;


public class GetIcon {
    ImageView image;
    File file;
    public GetIcon(File f) {
        file = f;
    }

    public ImageView getImage() {
        ImageIcon icon=(ImageIcon) FileSystemView.getFileSystemView().getSystemIcon(file);
        Image iconImage= icon.getImage();
        WritableImage i=toFXImage((BufferedImage) iconImage,null);
        ImageView ii=new ImageView(i);
        return ii;
    }
    
    
}
