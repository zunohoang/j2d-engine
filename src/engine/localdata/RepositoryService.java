package engine.localdata;

import engine.utils.JsonManager;
import engine.utils.LOG_TYPE;
import engine.utils.Logger;

import java.util.ArrayList;
import java.util.List;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class RepositoryService {
    private static RepositoryService instance;
    private String repositoryPath;
    private List<RepositoryModel> repositories;
    private List<JsonManager.JsonObject> jsonObjects;

    private RepositoryService() {
        this.repositoryPath = "assets/repositories";
        // tim kiem cac file trong thu muc assets/repositories va luu vao list
        jsonObjects = getRepositoryByPath(repositoryPath);
    }

    public static RepositoryService getInstance() {
        if (instance == null) {
            instance = new RepositoryService();
        }
        return instance;
    }

    public RepositoryModel addRepository(RepositoryModel repository) {
        if (repositories == null) {
            return null;
        }
        repositories.add(repository);
        return repository;
    }

    public JsonManager.JsonObject getObjectInRepository(String name) {
        if (jsonObjects == null) {
            Logger.log(this, "No JSON objects found in the repository", LOG_TYPE.ERROR);
            return null;
        }
        for (JsonManager.JsonObject jsonObject : jsonObjects) {
            if (jsonObject.get("name").equals(name)) {
                Logger.log(this, "Found JSON object: " + name);
                return jsonObject;
            }
        }
        Logger.log(this, "JSON object not found: " + name, LOG_TYPE.ERROR);
        return null;
    }

    public RepositoryModel getRepository(String name) {
        if (repositories == null) {
            return null;
        }
        for (RepositoryModel repository : repositories) {
            if (repository.getName().equals(name)) {
                return repository;
            }
        }
        return null;
    }

    public List<JsonManager.JsonObject> getRepositoryByPath(String path) {
        // Đường dẫn đến thư mục cần liệt kê
        Path directoryPath = Paths.get(path);

        // Kiểm tra xem đường dẫn có tồn tại và có phải là thư mục không
        if (Files.exists(directoryPath) && Files.isDirectory(directoryPath)) {

            // Sử dụng DirectoryStream
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(directoryPath)) {
                for (Path path1 : stream) {
                    if (Files.isRegularFile(path1)) {
                        Logger.log(this, "File: " + path1.getFileName());
                    } else if (Files.isDirectory(path1)) {
                        Logger.log(this, "Thư mục: " + path1.getFileName());
                    }
                }
            } catch (IOException e) {
                Logger.log(this, "Lỗi khi đọc thư mục: " + e.getMessage(), LOG_TYPE.ERROR);
            }

            // Lọc file theo pattern
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(directoryPath, "*.json")) {
                List<JsonManager.JsonObject> jsonObjects = new ArrayList<>();
                for (Path path1 : stream) {
                    JsonManager.JsonObject jsonObject = JsonManager.readObjectFromFile(path1.toString());
                    if (jsonObject != null) {
                        jsonObjects.add(jsonObject);
                        Logger.log(this, "File JSON: " + path1.getFileName());
                    }
                }
                return jsonObjects;
            } catch (IOException e) {
                Logger.log(this, "Lỗi khi đọc file JSON: " + e.getMessage(), LOG_TYPE.ERROR);
                return null;
            }
        } else {
            Logger.log(this, "Đường dẫn không tồn tại hoặc không phải là thư mục", LOG_TYPE.ERROR);
        }

        return null;
    }

    public String getRepositoryPath() {
        return repositoryPath;
    }

    public void setRepositoryPath(String repositoryPath) {
        this.repositoryPath = repositoryPath;
    }
}
