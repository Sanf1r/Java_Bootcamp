import java.util.ArrayList;

public class GetUrl {
    public static synchronized String[] getUrl(ArrayList<String[]> list) {
        String[] url = null;
        for (String[] data : list) {
            if (data != null) {
                url = data;
                list.remove(data);
                break;
            }
        }
        return url;
    }
}
