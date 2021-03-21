package core.principles.order;

import core.principles.annotation.MainDiscountPolicy;
import core.principles.discount.DiscountPolicy;
import core.principles.discount.FixDiscountPolicy;
import core.principles.discount.RateDiscountPolicy;
import core.principles.member.Member;
import core.principles.member.MemberRepository;
import core.principles.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
//    private DiscountPolicy discountPolicy;
//    private final MemberRepository memberRepository;
//    private final DiscountPolicy discountPolicy;

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

/*    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        System.out.println("2. discountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        System.out.println("2. memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }*/


    @Autowired //생성자가 하나만 있으니 안써도 된다.
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        System.out.println("1. memberRepository = " + memberRepository);
        System.out.println("1. discountPolicy = " + discountPolicy);
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
