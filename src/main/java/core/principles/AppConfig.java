package core.principles;

import core.principles.discount.DiscountPolicy;
import core.principles.discount.FixDiscountPolicy;
import core.principles.discount.RateDiscountPolicy;
import core.principles.member.MemberRepository;
import core.principles.member.MemberService;
import core.principles.member.MemberServiceImpl;
import core.principles.member.MemoryMemberRepository;
import core.principles.order.OrderService;
import core.principles.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

  @Configuration
public class AppConfig {

    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository(), discountPolicy()
    //MemoryMemberRepository()가 2번 호출된것 처럼 보이네?

    //Call AppConfig.memberService
    //Call AppConfig.memberRepository
    //Call AppConfig.memberRepository
    //Call AppConfig.orderService
    //Call AppConfig.memberRepository 3번 호출되어야 한다. 코드상으로는

    //Call AppConfig.memberService
    //Call AppConfig.memberRepository
    //Call AppConfig.orderService 동작은 이렇게 했다. 왜?? @Configuration의 마법이다.

    @Bean
    public MemberService memberService() {
        System.out.println("Call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("Call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("Call AppConfig.orderService");
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy()
        );
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy(); 여기만 바꾸면 된다
        return new RateDiscountPolicy();
    }
}
