package core.principles.singleton;

import core.principles.AppConfig;
import core.principles.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        //1. 조회 : 호출할 떄마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        //2. 조회 : 호출할 떄마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 =! memberService2
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singleTonServiceTest(){

        //private으로 생성자를 막아서 컴파일 오류가 발생한다.
        //new SingletonService();

        //1. 조회 : 호출할 떄마다 같은 객체를 생성
        SingletonService singletonService1 = SingletonService.getInstance();
        //2. 조회 : 호출할 떄마다 같은 객체를 생성
        SingletonService singletonService2 = SingletonService.getInstance();

        //3. 참조값이 같은 것을 확인
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        //same 자바의 ==. 객체 인스턴스 참조 비교
        //equal 자바의 equals.
        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("Spring Containner and SingleTon")
    void springContainer() {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        //1. 조회 : 호출할 떄마다 같은 객체를 생성
        MemberService memberService1 = applicationContext.getBean("memberService", MemberService.class);
        //2. 조회 : 호출할 떄마다 같은 객체를 생성
        MemberService memberService2 = applicationContext.getBean("memberService", MemberService.class);

        //3. 참조값이 같은 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //same 자바의 ==. 객체 인스턴스 참조 비교
        //equal 자바의 equals.
        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }
}
