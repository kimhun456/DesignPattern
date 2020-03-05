



# Command Pattern

> 커맨드 패턴을 이용하면 요구 사항을 객체로 캡술화 할 수 있고, 매개 변수를 써서 여러가지 다른 요구사항을 집어 넣을 수 있습니다. 또한 요청 내역을 큐에 저장하거나 로그로 기록할 수 있으며, 작업 취소 기능도 지원한다.

Layer분리를 통하여 **요청하는 객체**와 **요청을 처리하는 객체**가 서로를 모르는게 핵심인 듯하다.

* 리시버 - 요구사항을 수행하는 객체
* 커맨드 - execute()라는 method를 가지고 있는 객체
* 인보커 - 커맨드를 실행해달라고 요청하는 객체

```java
interface Command{ // 얘를 구현한 Concrete객체가 Receiver를 가지고 execute()함수를 구현한다.
   void execute();
}

class TurnOnSomeThingCommand implements Command {
   
    SomeThing mSomeThing;
  
    TurnOnSomeThingCommand(SomeThing someThing){
        mSomeThing = someThing;
    }
    
    @Override
    void execute(){
        mSomething.turnOn();
    }
}
```

```java
class SomeThing{ // Receiver -> 커맨드 객체가 가지고 있게 되는 임의의 객체
    void turnOn(){
        System.out.println("turnOn");
    }
    void turnOff(){
        System.out.println("turnOff");
    }
}
```

```java
public class SomeInvoker{ // Invoker -> Command 객체를 가지고 있고 이를 실행하는 역할.
    private List<Command> commands;
    
    public void setCommand(Command command){
        commands.add(command);
    }
    public void click(){
        commands.forEach(command -> command.execute());
    }
}
```

>  커맨드객체는 일련의 행동을 특정 리시버하고 연결시킴으로서 요구 사항을 캡슐화 한 것이라는 점을 이미 배움

 가장 중요한 점은 - 특정인터페이스 만을 구현해두고 그 커맨드 객체가 실제로 어떤 일을하는지는 신경 쓸 필요가 없다는 점..



### 커맨드 패턴 디자인 사용시 인보커와 리시버가 분리되는 이유는?

​	Invoker는 Command만 call하고 Command가 Receiver만을 갖고 있기 때문에 Invoker와 Receiver는 서로의 존재를 바라보지 않는다. 

#### Command Pattern 의 특징

* Method의 캡슐화
* 실제로 요구사항을 실행하는 Receiver와 요구사항을 실행하는 Invoker가 떨어지게 된다.



## NoCommand객체 

> ​	일종의 null객체 딱히 리턴할게 없으면 넣어줘서 NPE를 막는건데..! 과연 이게 쓸모가 있을까 NoCommand같이 Null을 피하게 하는 객체들 때문에 더욱 찾기 어려운 버그가 생기는걸 많이 봤는데??!

### 디자인 도구 상자

#### 	객체지향 원칙

* 바뀌는 부분은 캡슐화한다.
* 상속보다는 구성을 사용한다.
* 구현이 아닌 인터페이스에 맞춰서 프로그래밍한다.
* 서로 상호작용을 하는 객체 사이에서는 가능하면 느슨하게 결합하는 디자인을 사용해야한다.
* 클래스는 확장에 대해서만 열려있지만 변경에 대해서는 닫혀있어야한다.(OCP)
* 추상화 된 것에 의존하라. 구상클래스에 의존하지 않도록 한다.

#### 커맨드 패턴 

> 요청 내역을 객체로 캡슐화하여 클라이언트를 서로 다른 요청내역에 따라 매개변수화 할 수 있습니다. 요청을 큐에 저장하거나 로그로 기록할 수 있고 작업취소 기능을 지원할 수도 있다.