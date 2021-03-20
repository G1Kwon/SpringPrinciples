package core.principles.componentscan;

import core.principles.AutoAppConfig;
import core.principles.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = applicationContext.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
