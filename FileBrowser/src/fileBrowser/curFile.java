package fileBrowser;
import java.io.*;

public class curFile {
    File cur;

    public curFile(File file) {
        cur = file;
    }

    public void set(File file) {
        cur = file;
    }
    public File get() {
        return cur;
    }
}
