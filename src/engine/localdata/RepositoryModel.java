package engine.localdata;

public class RepositoryModel {
    private String name;
    private String path;
    private String type;
    private String version;

    public RepositoryModel(String name, String path, String type, String version) {
        this.name = name;
        this.path = path;
        this.type = type;
        this.version = version;
    }

    public void showData(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
