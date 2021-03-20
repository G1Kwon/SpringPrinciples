package core.principles.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonService {

    //1. static 영역에 객체를 1개만 생성한다.
    private static final SingletonService instance = new SingletonService();

    //2. public 으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
    public static SingletonService getInstance() {
        return instance;
    }

    //3. private constructor를 선언해서 외부에서 new 키워드를 통한 객체 생성을 못하게 한다.
    private SingletonService () {
    }

    public void Logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
