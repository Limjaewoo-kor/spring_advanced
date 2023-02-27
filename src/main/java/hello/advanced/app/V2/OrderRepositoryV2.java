package hello.advanced.app.V2;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class OrderRepositoryV2 {

    private final HelloTraceV2 trace;


    public void save(TraceId traceId, String itemId) {


        TraceStatus status = null;
        try {
            status = trace.beginSync(traceId,"OrderRepository.save()");

            //저장 로직
            if( itemId.equals("ex")) {
                throw new IllegalArgumentException("예외발생!!");
            }
            sleep(1000);

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e; //예외를 꼭 다시 던져주어야 한다.
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
