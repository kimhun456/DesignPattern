# State Pattern



상태 객체들을 별도의 코드에 집어넣고 어떤 행동이 일어나면 현재 상태 객체에서 필요한 작업을 처리하게 하는것



새로운 디자인을 적용하는 과정?

1. 뽑기기계와 관련된 모든 행동에 대한 메소드가 들어있는 State interface를 정의

   ```java
   interface State{
       State insertCoin();
       State returnCoin();
   }
   ```

2. 기계의 모든 상태에 대해서 상태 클래스를 구현해야 합니다. 기계가 어떤 상태에 있다면, 그상태에 해당하는 상태 클래스가 모든 작업을 책임져야한다.

   ```java
   class NoCoinState implements State{
       
       @override
       State insertCoin(){
           print("already insert coin");
           return CoinState;
       }
       
       @override
       State returnCoin(){
           print("return coin");
           return NoCoinState;
       }
   }
   ```

3. 마지막으로 조건문 코드를 모두 없애고 상태 클래스에 모든 작업을 위임한다.

> 이건 느낌이 약간 Behavior에 대한 정의를 State에 모두 해두고 그걸 구현하는 느낌이기 떄문에 행동에 대한 캡슐화가 아닌가? ㅇㅅㅇ?

```java
class GumballMachine{
    State noCoinState;
    State coinState;
    
    State state = noCoinState;
    
    int coinCount;
    
    public GumballMachine{
        noCoinState = new NoCoinState(this);
        coinState = new CoinState(this);
        coinCount = 0;
    }
    
    public insertCoin(){
        coinCount ++;
        state.insertCoin();
    }
    
    public returnCoin(){
        if(coinCount>0){
            coinCount--;
        }
        state.returnCoin();
    }
    
}
```



상태가 행동에 대한 책임을 가지고 있는 것...! 상태가 상태를 set하게 되는 패턴인거 같다.!



## State Pattern

> **스테이트 패턴**을 이용하면 객체의 내부 상태가 바뀜에 따라서 객체의 행동을 바꿀수 있습니다. 마치 객체의 클래스가 바뀌는 것과 같은 결과를 얻을 수 있습니다.

상태를 별도의 클래스로 캡슐화한 다음에 현재 상태를 나타내는 개체에게 행동을 위임하기 때문에 내부 상태가 바뀜에 따라 행동이 달라짐 -> Client입장에서는 다른 객체 같다는 느낌을 받을 수 있다. 스테이트 패턴의 경우 IF문을 통하여 힘들게 설정하는 State관리를 쉽게 해주는 패턴이라고 볼 수 있다.





### 객체지향  원칙

* 바뀌는 부분은 캡슐화 한다.
* 상속 보다는 구성을 사용한다.
* 구현이 아닌 인터페이스에 맞춰서 프로그래밍한다.
* 서로 상호작용을 하는 객체 사이에서는 가능하면 느슨하게 결합하는 디자인을 사용해야 한다.
* 클래스는 확장에 대해서는 열려 있지만 변경에 대해서는 닫혀 있어야한다.
* 추상화된 것에 의존하라. 구상 클래스에 의존하지 않아야 한다.
* 친한 친구들과만 연락한다.
* 먼저 연락하지 마세요 저희가 연락 드리겠습니다.

### 스테이트 패턴

> ​	내부 상태가 바뀜에 따라 객체의 행동이 바뀔 수 있도록 해줍니다. 마치 객체의 클래스가 바뀌는 것 같은 결과를 얻을 수 있습니다.