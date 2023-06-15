package uzBox.filesystem.directory.requests;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class DirectoryRequestConstructor{
    final private String sessionUUID;
    private Request directoryRequest;

    public DirectoryRequestConstructor(String sessionUUID) {
        this.sessionUUID = sessionUUID;
    }

    public void constructCreateDirectoryRequest(String path){
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create("{\n\"path\": \"" + path + "\"\n}",
                mediaType);
        createRequest("directory/createDirectory", body);
    }

    public void constructDeleteDirectoryRequest(String path){
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create("{\n\"path\": \"" + path + "\"\n}",
                mediaType);
        createRequest("directory/deleteDirectory", body);
    }

    public void constructListDirectoryRequest(String path){
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create("{\n\"path\": \"" + path + "\"\n}",
                mediaType);
        createRequest("directory/listDirectory", body);
    }

    public void constructRenameDirectoryRequest(String oldpath, String newpath){
        MediaType mediaType = MediaType.parse("application/json");
        //Nie dziala XD
        RequestBody body = RequestBody.create("'oldPath': " + oldpath + "\n 'newPath': " + newpath,
                mediaType);
        createRequest("directory/renameDirectory", body);
    }

    private void createRequest(String endpoint, RequestBody body) {
        this.directoryRequest = new Request.Builder()
                .url("http://localhost:5050/" + endpoint)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", sessionUUID)
                .build();
    }
    public Request getDirectoryRequest(){
        return this.directoryRequest;
    }
}
