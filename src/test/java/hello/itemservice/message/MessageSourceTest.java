package hello.itemservice.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.apache.coyote.http11.Constants.a;
import static org.assertj.core.api.Assertions.*;


@SpringBootTest //<-@springBootTest 어노테이션을 달아주면 spring boot가 한번 돌면서 테스트가 시작됨
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

    @Test
    void contextLoads() {
        String result = ms.getMessage("hello", null, null); // code :문자 args:넘길 변수 local: 설정 언어
        assertThat(result).isEqualTo("안녕");
    }
    @Test
    void notFoundMessageCode(){
        //ms.getMessage("no_code", null, null);
        assertThatThrownBy(() -> ms.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }
    @Test
    void notFoundMessageCodeDefaultMessage(){
        String result = ms.getMessage("no_code", null, "기본 메시지", null);
        assertThat(result).isEqualTo("기본 메시지");
    }
    @Test
    void argumentMessage(){
        String message = ms.getMessage("hello.name", new Object[]{"Spring"}, null);
        assertThat(message).isEqualTo("안녕 Spring");
    }
    @Test
    void defaultLang(){
        assertThat(ms.getMessage("hello", null, null)).isEqualTo("안녕");
        assertThat(ms.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕");
    }

    @Test
    void enLang(){
        assertThat(ms.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("hello");
    }


}
