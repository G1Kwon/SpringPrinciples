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

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy()
        );
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy(); 여기만 바꾸면 된다
        return new RateDiscountPolicy();
    }
}
