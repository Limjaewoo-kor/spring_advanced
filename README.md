# spring_advanced
ThreadLocal


동시성 문제  /  

싱글톤으로 등록된 스프링 빈을 사용할 때는 피할 수 없는 것은 동시성 문제이다.
FieldLogTrace 는 싱글톤으로 등록된 스프링 빈이다. 
이 객체의 인스턴스가 애플리케이션에 딱 1 존재한다는 뜻이다. 
이렇게 하나만 있는 인스턴스의 FieldLogTrace.traceIdHolder 필드를 여러 쓰레드가 동시에 접근하기 때문에 문제가 발생한다.
실무에서 한번 나타나면 개발자를 가장 괴롭히는 문제도 바로 이러한 동시성 문제이다. 

동시성 문제를 해결하기 위해 쓰레드 로컬을 사용할 수 있다.

하지만 쓰레드 로컬에는 중요한 주의사항이 있는데, 사용후에 반드시 remove해줘야한다.
그렇지 않으면 사용한 쓰레드가 삭제되지않고 쓰레드풀에 들어갔다가 다른 사용자에게 재호출 되기때문에 타인에게 다른사람의 정보가 노출 될 수 있다.

필터나 인터셉터에서 나갈때 clear해서 깔끔하게 제거해주는 작업을 해주면 좋다.