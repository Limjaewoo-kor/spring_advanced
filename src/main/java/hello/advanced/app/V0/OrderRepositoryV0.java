package hello.advanced.app.V0;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class OrderRepositoryV0 {

    public void save(String itemId) {
        //저장 로직
        if( itemId.equals("ex")) {
            throw new IllegalArgumentException("예외발생!!");
        }
        sleep(1000);

    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
