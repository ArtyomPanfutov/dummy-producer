package devart.dummyrestproducer.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DummyRest {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final int SIZE = 200_000;
    private static String RESPONSE;

    static {
        List<DummyDto> LIST = new ArrayList<>(SIZE);
        while (LIST.size() <= SIZE) {
            if (LIST.size() % 10000 == 0) {
                System.out.println("Elements size " + LIST.size());
            }

            LIST.add(DummyDto.builder()
                    .string1(RandomStringUtils.randomAlphanumeric(100))
                    .string2(RandomStringUtils.randomAlphanumeric(100))
                    .string3(RandomStringUtils.randomAlphanumeric(100))
                    .string4(RandomStringUtils.randomAlphanumeric(100))
                    .string5(RandomStringUtils.randomAlphanumeric(100))
                    .string6(RandomStringUtils.randomAlphanumeric(100))
                    .string7(RandomStringUtils.randomAlphanumeric(100))
                    .string8(RandomStringUtils.randomAlphanumeric(100))
                    .string9(RandomStringUtils.randomAlphanumeric(100))
                    .build());

        }
        try {
            RESPONSE = OBJECT_MAPPER.writeValueAsString(LIST);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println("Init finished");
    }

    @GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public String get() {
        return RESPONSE;
    }
}
