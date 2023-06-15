package uzBox.filesystem.directory.requests;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import uzBox.filesystem.directory.Directory;
import uzBox.json.JsonErrorMessageHandler;
import uzBox.json.JsonResponseDecoder;

import java.io.IOException;
import java.util.List;

public class DirectoryRequestHandler {
    private DirectoryRequestConstructor directoryRequestConstructor;
    private OkHttpClient client;
    private JsonResponseDecoder jsonResponseDecoder;
    private Response response;

    public DirectoryRequestHandler(DirectoryRequestConstructor directoryRequestConstructor) {
        this.directoryRequestConstructor = directoryRequestConstructor;
        this.client = new OkHttpClient().newBuilder()
                .build();
        this.jsonResponseDecoder = new JsonResponseDecoder();
    }

    public void sendDirectoryRequest(){
        Request directoryRequest = directoryRequestConstructor.getDirectoryRequest();
        try {
            this.response = client.newCall(directoryRequest).execute();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Directory> getDirectoryList(String path){
        return jsonResponseDecoder.getDirectoryResponseToList(response, path);
    }
    public String getErrorMessage(){
        JsonErrorMessageHandler jsonErrorMessageHandler = new JsonErrorMessageHandler();
        return jsonErrorMessageHandler.retrieveError(this.response);
    }
    public boolean isDirectoryRequestSuccessful(){
        return this.response.isSuccessful();
    }

    public boolean isServerAlive(){
        if(this.response == null|| this.response.code() == 404){
            return false;
        }
        return true;
    }
}
