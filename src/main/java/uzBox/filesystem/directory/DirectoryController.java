package uzBox.filesystem.directory;

import uzBox.filesystem.directory.requests.DirectoryRequestConstructor;
import uzBox.filesystem.directory.requests.DirectoryRequestHandler;

import java.util.List;

public class DirectoryController {
    private final DirectoryRequestConstructor directoryRequestConstructor;
    private final DirectoryRequestHandler directoryRequestHandler;

    public DirectoryController(String sessionUUID) {
        this.directoryRequestConstructor = new DirectoryRequestConstructor(sessionUUID);
        this.directoryRequestHandler = new DirectoryRequestHandler(directoryRequestConstructor);
    }

    public String createDirectory(String path) {
        this.directoryRequestConstructor.constructCreateDirectoryRequest(path);
        return getResponse();
    }

    public String deleteDirectory(String path){
        this.directoryRequestConstructor.constructDeleteDirectoryRequest(path);
        return getResponse();
    }

    public List<Directory> listDirectory(String path){
        this.directoryRequestConstructor.constructListDirectoryRequest(path);
        if(getResponse() == "200"){
            return directoryRequestHandler.getDirectoryList(path);
        }
        return List.of();
    }

    public String renameDirectory(String oldpath, String newpath){
        this.directoryRequestConstructor.constructRenameDirectoryRequest(oldpath, newpath);
        return getResponse();
    }

    private String getResponse() {
        directoryRequestHandler.sendDirectoryRequest();
        if(directoryRequestHandler.isServerAlive()){
            if(directoryRequestHandler.isDirectoryRequestSuccessful()){
                return "200";
            }
            return directoryRequestHandler.getErrorMessage();
        }
        return "Cannot connect to server";
    }
    }

