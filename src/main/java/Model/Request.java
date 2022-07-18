package Model;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;

public class Request implements Serializable {
    public static final long serialVersionUID = 2L;
    public final String action;
    public final HashMap<String, String> params;
    private File file;

    public Request(String action, HashMap<String, String> params) {
        this.action = action;
        this.params = params;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "Request{" +
                "action='" + action + '\'' +
                ", params=" + params +
                '}';
    }
}


