package uzBox.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Response;
import uzBox.filesystem.directory.Directory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JsonResponseDecoder {

    ObjectMapper jsonMapper;

    public JsonResponseDecoder() {
        jsonMapper = new ObjectMapper();
    }

    public List<Directory> getDirectoryResponseToList(Response response, String path){
        List<Directory> directories = new ArrayList<>();
        JsonNode jsonNode = null;
        try {
            String message = Objects.requireNonNull(response.body())
                    .string();
            jsonNode = jsonMapper.readTree(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(jsonNode.isNull()){
            return directories;
        }
        JsonNode directoriesnode = jsonNode.get("directories");
        directoriesnode.iterator().forEachRemaining(node -> {
            directories.add(new Directory(node.asText(), path + node.asText()));
        });
        return directories;
    }
}
