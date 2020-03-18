# Adapter and Facade Pattern



## Adapter 

> 어떤 인터페이스를 클라이언트에서 요구하는 형태의 인터페이스에 적응시켜주는 역할을 한다. 느낌이 마치 스펀지같은게 둘 사이를 지탱해 주고 있는 느낌이라고 볼 수 있다. 

특정 interface를 반드시 사용해야하는 녀석이 있다면 

```java
interface Media{
    void play();
}

interface Player{
    void playMedia(Media media);
}

interface SomeOldMedia{
    void singASong();
}

```

이런식으로 있을 때 SomeOldMedia를 Media로 쓸 수 있도록  MediaAdapter를 추가해봤다.

```java
public class MediaAdapter implements Media{
   
    SomeOldMedia old;
    public MediaAdapter(SomeOldMedia old){
        this.old = old;
    }
    
    @override
    public play(){
        old.singASong();
    }
   
}

public class ConcretePlayer implements Player{
    
    @override
    public void playMedia(Media media){
        println("play Media once");
        media.play();
    }
}

```

이런식으로 원하는 Interface에 맞는 Adapter를 만들어서 넣어주면 사용할수 있다.  여기서 사용한 것은 객체 어댑터로써 Composition을 사용했다고 볼 수 있다.

이 패턴 또한 Client와 Adaptee를 분리하는 패턴이다.  즉, **관심의 분리**를 사용하는 패턴이다.



## 어댑터 패턴

> 한 클래스의 인터페이스를 클라이언트에서 사용하고자 하는 다른 인터페이스로 변환한다. 어댑터를 이용하면 인터페이스 호환성 문제 떄문에 같이 쓸 수 없는 클래스들을 연결해서 쓸 수 있다.



### 클래스 어댑터 vs 객체 어댑터

클래스 어댑터는 다중 상속을 사용하기 때문에 사용하기 어렵다.. 객체어댑터의 경우 구성을 사용하기 때문에 Super Adaptee를 상속받는 Child Adaptee도 사용가능하다. 또한 객체 구성을 사용한다는 것은 여러 장점을 가지고 있는데 앞에서 배운 것처럼 Runtime에 객체를 바꿀수 있고, 이를 통하여 좀 더 넓은 확장성을 갖을 수 있게 된다.



### 브레인파워 

* AC 어댑터 중에는 인터페이스만 바꿀 뿐 아니라 서지 프로텍션이라든가 지시등이라든가 벨 소리 기능등의 추가기능을 갖춘 것도 있습니다. 이런 기능을 추가하기 위해서는 어떤 패턴을 사용하겠습니까?? (Decorator??)잘모르겠다.



~~또다른 야생의 패턴이 나타났다~~

인터페이스를 단순화 or 인터페이스를 변경한다. Facade패턴-! 

* Facade : 겉모양 or 외관 -> 즉 이쁘게 만들어 주는 패턴이라는 뜻이다.

## Facade

퍼사드 패턴을 사용해서 복잡한 시스템을 훤씬 간단한 시스템으로 바꿀수가 있다.

### Facade vs Adapter

​	얘네 둘의 가장 큰 차이점은 바로 용도이다. Facade의 경우 어떤 서브시스템에 대한 간단한 인터페이스 제공을 위하여 쓰이는 것이고 Adapter의 경우 인터페이스를 adapt, 즉 적응시키기 위하여 쓰이는 패턴이라고 할 수 있다.



## Facade Pattern

> ​	어떤 서브시스템이 일련의 인터페이스에 대한 통합된 인터페이스를 제공한다. 퍼사드에서는 고수준 인터페이스를 정의하여 서브시스템을 더 쉽게 사용 할 수 있다.



### 디자인 원칙

#### 	최소 지식 원칙 - 정말 친한 친구하고만 얘기하라.

 최소한의 친구들만 얘기하는 것이 좋다.. 우리로 생각하면 Matrix 지표중에 CBO ( Connection between Object)를 줄이는데 힘쓰는 경우가 있는데 이런걸 생각하면 좋다. 정말 최소한의 친구와만 얘기하는 경우 그에 대한 보상으로 훨씬 간결한 코드가 가능해지고 영향을 받게되는 Object들도 줄일 수가 있다.

* 객체 자체
* 메소드에 매개변수로 전달된 객체
* 그 메소드에서 생성하거나 인스턴스를 만든 객체
* 그 객체에 속하는 구성요소

~~이것만 살펴보면 Layer를 지켜서 바로 아랫단과 자기 자신만 보고있는거 같은데 흠...~~

즉 이런 코드를 피하라는 뜻이다.

```java
class SomeThing{
    public float getPercentage(){
        return getWeatherCenter().getKorea().getTemprature().toPercentage();
    }
}
```

이런식으로 쓰게되면 타고 들어가고 타고 들어가고 타고 들어가야하는 코드가 되어버린다.

**모든 원칙은 적재적소에 쓰여야 한다.**

이런식으로 생각하면 Facade의 경우에는 최소지식원칙을 제대로 지키고 있다고 생각 할 수 있다.



### 디자인 도구상자

#### 객체 지향 원칙

* 바뀌는 부분은 캡슐화 한다.
* 상속보다는 구성을 사용한다.
* 구현이 아닌 인터페이스에 맞춰서 프로그래밍한다.
* 서로 상호작용을 하는 객체 사이에서는 가능하면 느슨하게 결합하는 디자인을 사용해야 한다.
* 클래스는 확장에 대해서는 열려있지만 변경에 대해서는 닫혀있어야한다.
* 추상화 된것에 의존하라. 구상 클래스에 의존하지 않도록 해야한다.
* 친한 친구들하고만 이야기한다.



#### 어댑터 패턴

> ​	클래스의 인터페이스를 클라이언트에서 요구하는 다른 인터페이스로 변환합니다. 인터페이스가 호환되지 않아 쓸 수 없었던 클래스들을 같이 사용할 수 있게 해 줍니다.

#### 퍼사드 패턴

> ​	서브시스템에 있는 일련의 인터페읏에 대한 통합 인터페이스를 제공합니다. 퍼사드 패턴에서는 서브시스템을 더 쉽게 사용 할 수있는게 해주는 고수준 인터페이스를 정의합니다.
