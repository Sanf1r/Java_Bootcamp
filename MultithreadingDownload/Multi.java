import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Multi extends Thread {
    private Integer id;
    private ArrayList<String[]> list;

    public Multi(int id, ArrayList<String[]> list) {
        this.id = id;
        this.list = list;
    }

    private void downloadUrl(String url) throws IOException, URISyntaxException {
        URL link = new URL(url);
        String filename = Paths.get(new URI(url).getPath()).getFileName().toString();
        ReadableByteChannel rbc = Channels.newChannel(link.openStream());
        FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "/files/" + filename);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }

    @Override
    public void run() {
        while (!list.isEmpty()) {
            try {
                String[] url = GetUrl.getUrl(list);
                System.out.printf("Thread-%d start download file number %s%n", id, url[0]);
                downloadUrl(url[1]);
                System.out.printf("Thread-%d finish download file number %s%n", id, url[0]);
            } catch (IOException e) {
                System.err.println("Error IO!");
            } catch (URISyntaxException e) {
                System.err.println("Error URI!");
            } catch (NullPointerException e) {
            }

        }
    }
}
