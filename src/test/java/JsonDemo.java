import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

public class JsonDemo {

    public static void main(String[] args) throws JsonProcessingException {

        Object ans = JsonPath.read(
                "{\"arr\":[{\"name\":\"n1\"},{\"name\":\"n2\"}]}\n",
                "$.arr[*].name"
        );

        System.out.println(ans);
    }

}
