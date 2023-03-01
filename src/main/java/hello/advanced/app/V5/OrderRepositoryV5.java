package hello.advanced.app.V5;

import hello.advanced.trace.callback.TraceCallback;
import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class OrderRepositoryV5 {

    private final TraceTemplate traceTemplate;

    public OrderRepositoryV5(LogTrace trace) {
        this.traceTemplate = new TraceTemplate(trace);
    }


    public void save(String itemId) {

        traceTemplate.execute("OrderRepository.save()", new TraceCallback<Void>() {
            @Override
            public Void call() {
                //저장 로직
                if( itemId.equals("ex")) {
                    throw new IllegalArgumentException("예외발생!!");
                }
                sleep(1000);
                return null;
            }
        });
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
