
package fileBrowser;

import java.io.File;
import java.text.SimpleDateFormat;
import javafx.scene.image.ImageView;


public class FileAdapter {
    ImageView Image;
    String Name;
    String Size;
    String DateMod;
    File file;

    public FileAdapter(File fil) {
        file = fil;
        Name = file.getName();
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        DateMod = date.format(file.lastModified());
        Size = String.valueOf(file.length());
        ImageView imageView = new GetIcon(file).getImage();
        Image = imageView;
        
    }

    public void setImage(ImageView Image) {
        this.Image = Image;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setSize(String Size) {
        this.Size = Size;
    }

    public void setDateMod(String DateMod) {
        this.DateMod = DateMod;
    }
    
    public String getDate() {
        return DateMod;
    }

    public ImageView getImage() {
        return Image;
    }

    public String getName() {
        return Name;
    }

    public String getSize() {
        return Size;
    }

    public File getF() {
        return file;
    }
}
