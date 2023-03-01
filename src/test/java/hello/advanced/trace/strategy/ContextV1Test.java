package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {

        @Test
        void strategyV0() {
            logic1();
            logic2();
        }

        private void logic1() {
            long startTime = System.currentTimeMillis();
            //비즈니스 로직 실행
            log.info("비즈니스 로직1 실행");
            //비즈니스 로직 종료
            long endTime = System.currentTimeMillis();
            long resultTime = endTime-startTime;
            log.info("resultTime={}",resultTime);
        }

        private void logic2() {
            long startTime = System.currentTimeMillis();
            //비즈니스 로직 실행
            log.info("비즈니스 로직2 실행");
            //비즈니스 로직 종료
            long endTime = System.currentTimeMillis();
            long resultTime = endTime-startTime;
            log.info("resultTime={}",resultTime);
        }


    /**
     *전략 패턴 적용
     */
    @Test
    void strategyV1() {
        Strategy strategyLogic1 = new StrategyLogic1();
        ContextV1 context1 = new ContextV1(strategyLogic1);
        context1.execute();
        Strategy strategyLogic2 = new StrategyLogic2();
        ContextV1 context2 = new ContextV1(strategyLogic2);
        context2.execute();
    }

    /**
     * 익명 내부 클래스로 사용하기
     */
    @Test
    void strategyV2() {
        Strategy strategyLogic1 = new Strategy() {

            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        };
        ContextV1 contextV1 = new ContextV1(strategyLogic1);
        contextV1.execute();

        Strategy strategyLogic2 = new Strategy() {

            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        };
        ContextV1 contextV2 = new ContextV1(strategyLogic1);
        contextV2.execute();
    }

    /**
     *전략 패턴 익명 내부 클래스2
     */
    @Test
    void strategyV3() {
        ContextV1 context1 = new ContextV1(new Strategy() {

            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        });
        context1.execute();
        ContextV1 context2 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        });
        context2.execute();
    }

    /**
     * 전략 패턴, 람다
     */
    @Test
    void strategyV4() {
        ContextV1 context1 = new ContextV1(() -> log.info("비즈니스 로직1 실행"));
        context1.execute();

        ContextV1 context2 = new ContextV1(() -> log.info("비즈니스 로직2 실행"));
        context2.execute();
    }

}
