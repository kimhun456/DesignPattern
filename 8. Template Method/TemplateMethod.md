# Template Method



내가 만들어보는 Coffee 와 TeaBag의 중복된 코드 없애기

```java
abstract class Beverage{
  void makeBeverage() { // final 빼먹음
    boilWater();
    slideUpTaste();
    pourInCup();
    addIngrdeient();
  }
  
  void puorInCup(); 
  void addIngredient();
  void boilWater(); // 공통부분은 충분히 여기서 concrete로 만들 수 있는데 그렇게 하지 않음. 
  void slideUpTaste();
  
}

class Coffee extends Beverage{
  
  @override
  void puurInCup(){
    print("Pour In Cup");
  }
  
}
```

예제로 나온 Coffee와 Tea의 가장 큰 특징은 역시 **순서**가 보장된다는 것이다. 즉 내가 여기서 말하고 있는 순서는 Order가 아닌 어떠한 알고리즘으르 치환될 수 있는거 아닐까?



여기서 내가 짠 makeBeverage가 Template Method다. 이 녀석은 method이기도 하지만 결국에 가장 큰 특징은 어떤 알고리즘에 대한 Template역할을 하기 때문에 Template Method라고 할 수 있다.



### Template Method Pattern

> 템플릿 메소드 패턴에서는 메소드에서 알고리즘의 골격을 정의합니다. 알고리즘의 여러 단계 중 일부는 서브클래스에서 구현 할수 있습니다. 템플릿 메소드를 이용하면 알고리즘의 구조는 그대로 유지하면서 서브클래스에서 특정 단계를 재정의할 수 있습니다.



```java 
abstract class TemplateMethodClass{
  final void tempateMethod(){
    prepare();
    execute();
    after();
    hook();
  }
  
  abstract execute();
  
  void prepare(){
    print("prepare");
  }
  
  void after(){
    print("after");
  }
  
  void hook(){} // Optional하게 처리할 수 있는 녀석.. 근데 이게 필요로 하는 경우라면 TemplateMethod를 쓰는게 맞을까 흠.. --> 필요에 따라 overriding해서 쓰게 만든다는 뜻.
  
}
```



### 할리우드 원칙(이름이 이게 아닌데 분명..)

> 먼저 연락하지 마세요. 저희가 연락드리겠습니다.

의존성 부패를 방지 할 수 있다.  A <- B 이런식으로 extends하고 있다면 B에서 A의 코드를 호출하지 않는 것이라고 볼 수 있다.

~~고수준 구성요소가 저수준 구성요소에게 연락을 해야하고 저수준 구성요소는 고수준 구성요소에게 연락을 하면 안된다는 것이다. 여기서 연락을 내가 아는 의존~~(depends on )~~이라고 생각하면 고수준, 즉 위 레이어에 있는 녀석은 아래에 있는 녀석을 의존하지만 아래에 있는 녀석은 위 레이어에 있는 녀석의 존재 조차 몰라야 된다는 것이다. 이것을 통해 얻을 수 있는 이득은 매우 많은데 첫번째로 CC, 즉 서로 복잡하게 꼬여있는 Depedency Circle을 끊을 수 있다는 점이다. Circular Dependency의 경우 A -> B -> C -> A 로 이어지는 depedency를 처리함으로 인하여 레이어를 확실히 정할 수 있고 각자 맡은 역할이 명확해 진다는 장점이 있다.~~

템플릿 메소드 디자인을 가장 잘 이용하는 것은 Framework를 개발할 때다. 어떤 알고리즘을 정해두고 각 알고리즘 단계마다 어떻게 구현할 지는 사용자한테 맡겨두면 되기 때문이라고 생각된다..! 

ex ) 

```java
// SimpleFrameWork for Load Image
class LoadImage{
  void execute(){
    getFromCache(); // 어떤 캐쉬를 쓸지는 User가 선택할 수 있게 한다면?
    if(notExist) insertCache();
    getFromInputStream(); // 어떤 Uri를 통해서 받아 올지 선택 할 수 있다면?
    showImage(); // 어떤 View를 통해서 보여줄지 선택할 수 있다면?
  }
}
```



결국 이 친구와 Strategy Pattern의 공통점이라 하면 알고리즘 군에 대한 정의를 해준다라는 것이고.. TemplateMethod의 경우는 특정 알고리즘에 대한 좀 더 명확한 순서 및 구현을 하게 되고 이를 달성하는데 **상속**을 사용하고 있다. 하지만 이를 위해 갖게 되는 단점은 TemplateMethod를 상속받은 Concrete Class에 대한 직접적인 의존이 필요하다는 점이다. 이와 달리 Strategy의 경우 알고리즘의 골격(template)을 구현하는 것이 아닌 알고리즘 군을 Runtime에 선택할 수 있도록 **구성**을 사용하고 있다는 점이다.



### 디자인 도구 상자

#### 객체지향 원칙

* 바뀌는 부분은 캡슐화한다.
* 상속보다는 구성을 사용한다.
* 구현이 아닌 인터페이스에 맞춰서 프로그래밍한다.
* 서로 상호작용을 하는 객체 사이에서는 가능하면 느슨하게 결합하는 디자인을 사용해야 한다.
* 클래스는 확장에 대해서는 열려있지만 변경에 대핸서는 닫혀 있어야 한다.
* 친한 친구들 하고만 이야기한다.
* 먼저 연락하지 마세요! 저희가 연락드리겠습니다.

#### 템플릿 메소드 패턴

> 어떤 작업 알고리즘의 골격을 정의합니다. 일부 단계는 서브클래스에서도 구현하도록 할 수 있습니다. 템플릿 메소드를 이용하면 알고리즘의 구조는 그대로 유지하면서 특정 단계만 서브클래스에서 새로 정의하도록 할 수 있습니다.

### 