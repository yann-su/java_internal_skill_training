package task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import entity.ListeningDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

    public static void main(String[] args) {
        String pattern = "^1,13,.*";
        String string1 = "1,13,3";
        boolean matches = Pattern.matches(pattern, string1);
        System.out.println(matches);


        List<Long> integerArrayList = new ArrayList<>(Arrays.asList(10015L,10074L,10075L,10077L,10080L,10081L));
        long id = 10015;
        System.out.println(integerArrayList.contains(id));

        ListeningDetails listeningDetails = new ListeningDetails();
        listeningDetails.setBusiness("aaa");
        listeningDetails.setPlaylistId(123131141L);

        SerializeConfig serializeConfig=new SerializeConfig();
        serializeConfig.propertyNamingStrategy= PropertyNamingStrategy.SnakeCase;
        String valueString = JSON.toJSONString(listeningDetails,serializeConfig);
        String jsonStr = "{\"business\":\"aaa\",\"playlist_id\":123131141}";
        ListeningDetails listeningDetails1 = JSON.parseObject(jsonStr, ListeningDetails.class);

        System.out.println(valueString);






    }
}
