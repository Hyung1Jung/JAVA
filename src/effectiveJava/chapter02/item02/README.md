# 객체 생성과 파괴 (2장)

---

## Item2 : 생성자에 매개변수가 많으면 빌더를 고려하라

### 점층적 생성자 패턴
- 매개변수를 늘려가는 점층적 생성자 패턴을 쓸 수는 있지만, 매개변수 개수가 많아지면 클라이언트 코드를 작성하거나 읽기가 어렵다.
코드를 읽을 떄 각 값의 의미가 무엇인지 헷갈릴 것이고, 매개변수가 몇 개인지도 주의해서 세어 보아야 할 것이다. 타입이 
같은 매개변수가 연달아 늘어서 있으면 찾기 어려운 버그로 이어질 수 있다. 클라이언트가 실수로 매개변수의 순서를 바꿔 건네줘도 컴파일러는 
  알아채지 못하고, 결국 엉뚱한 동작을 하게 된다. 
  
### 선택 매개변수가 많을 때 활용할 수 있는 두 번째 대안인 자바빈즈패턴(JavaBeans pattern)
- 매개변수가 없는 생성자로 객체를 만든 후, 세터 메서드들을 호출해 원하는 매개변수의 값을 설정하는 방식.
- 예) `private int size; , public void setSize(int val) { size = val; }`
- 하지만 `단점`이 있다. 자바빈즈패턴에서는 객체 하나를 만들려면 메서드를 여러 개 호출해야 하고, 객체가 완전히 생성되기 전까
지는 일관성이 무너진 상태에 놓이게 된다.
- 생성자와 달리 매개변수들이 유효한지를 확인하는 장치가 사라진다. 일관성이 깨진 객체가 만들어지면, 버그를 심은 코드와
그 버그 때문에 런타임에 문제를 겪는 코드가 물리적으로 멀리 떨어져 있을 것이므로 디버깅도 만만치 않다.
- 이처럼 일관성이 무너지는 문제 떄문에 자바빈즈 패턴에서는 클래스를 불변(아이템 17)로 만들 수 없으며 스레드 안전성을 얻으려면
프로그래머가 추가 작업을 해줘야만 한다.
  
### 점층적 생성자 패턴의 안정성과 자바빈즈 패턴의 가독성을 겸비한 빌더 패턴(Bulider pattern)
- 빌더는 생성할 클래스 안에 정적 멤버 클래스로 만들어두는게 보통이다.
- 클라이언트는 필요한 객체를 직접 만드는 대신, 필수 매개변수만으로 생성자(or 정적팩토리)를 호출해 빌더 객체를 얻는다. 그 다음에 setter를 이용해 선택 매개변수들을 설정한다. 
  마지막으로 매개변수가 없는 build 메서드를 호출해 드디어 우리에게 필요한 (불변) 객체를 얻는다.
  
빌더가 어찌 동작하는지 코드로 살펴보자.


```java
public class NutritionFactsBuilder {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public static class Builder {
        // 필수 매개변수
        private final int servingSize;
        private final int servings;

        // 선택 매개변수 - 기본값으로 초기화.
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbohydrate = 0;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int val) {
            calories = val;
            return this;
        }

        public Builder fat(int val) {
            fat = val;
            return this;
        }

        public Builder sodium(int val) {
            sodium = val;
            return this;
        }

        public Builder carbohydrate(int val) {
            carbohydrate = val;
            return this;
        }

        public NutritionFactsBuilder build() {
            return new NutritionFactsBuilder(this);
        }
    }

    private NutritionFactsBuilder(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }
}
```
현재 NutritionFactsBuilder 클래스는 불변이며, 모든 매개변수의 기본값들을 한곳에 모았다. setter 메서드들은 빌더 자신을 
반환하기 때문에 연쇄적으로 호출 할 수 있다. 이런 방식을 플루언트 API, 메서드 연쇄(Method Chaning) 이라 한다.

```java
NutritionFactsBuilder cola = new NutritionFactsBuilder.Builder(240, 8)
                .calories(100).sodium(35).carbohydrate(27).build();
```
잘못된 매개변수를 일찍 발견하려면 빌더의 생성자와 메서드에서 입력 매개변수를 검사하고, 
build 메서드가 호출하는 생성자에서 여러 매개변수에 걸친 불변식(invariant)을 검사하자.

공격에 대비해 불변식을 보장하려면 빌더로부터 매개변수를 복사한 후 해당 객체 필드들도 검사해야한다. 검사해서 잘못된 점을 발견하면
어떤 매개변수가 잘못되었는지를 자세히 알려주는 메시지를 담아 IllegalArgumentException을 던지면 된다.

### 빌더 패턴은 계층적으로 설계된 클래스와 함께 쓰기에 좋다.

각 계층에 클래스 관련 빌더를 멤버로 정의하자. 추상 클래스는 추상 빌더를, 구체 클래스는 구체 빌더를 갖게 한다.
다음은 피자의 다양한 종류를 표현하는 계층구조의 루트에 놓인 추상 클래스다.
```java
public abstract class Pizza {
    public enum Topping {
        HAM, MUSHROOM, ONION, PEPPER, SAUSAGE
    }

    final Set<Topping> toppings;
    
    abstract static class Builder<T extends Builder<T>> {
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping) {
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }

        abstract Pizza build();

        // 하위 클래스는 이 메서드를 재정의(overriding)하여
        // "this"를 반환하도록 해야 한다
        protected abstract T self();
    }

    Pizza(Builder<?> builder) {
        toppings = builder.toppings.clone();
    }
} 
```

Pizza.Builder 클래스는 재귀적 타입한정을 이용하는 제네릭 타입이다. 여기에 추상 메서드인 self()를 더해 하위 클래스에서는 
형변환하지 않고, 메서드 연쇄를 지원할 수 있다. self 타입이 없는 자바를 위한 이 우회 방법을 시뮬레이트한 셀프 타입(simulated self-type) 관용구라 한다.

### 공변 반환 티이핑(covariant return typing) 에제
```java
public class NyPizza extends Pizza {
   public enum Size { SMALL, MEDIUM, LARGE }
   private final Size size;

   public static class Builder extends Pizza.Builder<Builder> {
      private final Size size;

      public Builder(Size size) {
         this.size = Objects.requireNonNull(size);
      }

      @Override public NyPizza build() {
         return new NyPizza(this);
      }

      @Override protected Builder self() { return this; }
   }

   private NyPizza(Builder builder) {
      super(builder);
      size = builder.size;
   }
}
```

```java
public class Calzone extends Pizza {
   private final boolean sauceInside;

   public static class Builder extends Pizza.Builder<Builder> {
      private boolean sauceInside = false;

      public Builder sauceInside() {
         sauceInside = true;
         return this;
      }

      @Override public Calzone build() {
         return new Calzone(this);
      }

      @Override protected Builder self() { return this; }
   }

   private Calzone(Builder builder) {
      super(builder);
      sauceInside = builder.sauceInside;
   }
}
```

- 각 하위 클래스의 빌더가 정의한 bulid 메서드는 해당하는 구체 하위 클래스를 반환하도록 선언한다. MyPizza.Builder은 NyPizza를 반환하고,
Calzone.Builder는 Calzone을 반환한다는 뜻이다. 
- 하위 클래스의 메서드가 상위 클래스의 메서드가 정의한 반환 타입이 아닌, 그 하위 타입을 반환하는 기능을 `공변 변환 타이핑`이라 한다.
- 이 기능을 이용하면 클라이언트가 형변환에 신경 쓰지 않고도 빌더를 사용할 수 있다

### 계층적 빌더를 사용하는 클라이언트 코드
```java
public class Main {
    public static void main(String[] args) {
        NYPizza pizza = new NYPizza.Builder(SMALL)
                .addTopping(SAUSAGE)
                .addTopping(ONION)
                .build();

        Calzone calzone = new Calzone.Builder()
                .addTopping(HAM)
                .sauceInside()
                .build();
    }
}
```

- 생성자로는 누릴 수 없는 사소한 이점으로, 빌더를 이용하면 가변인수 매개변수를 여러 개 사용할 수 있다. 1addTopping1 메서드가 이렇게 구현된 예다.
- 빌더 패턴은 상당히 유연하다. 빌더 하나로 여러 객체를 순회하면서 만들 수 있고, 빌더에 넘기는 매개변수에 따라 다른 객체를 만들 수도 있다.

### 빌더 패턴 단점
- 객체를 만들려면, 그에 앞서 빌더부터 만들어야 한다. 빌더 생성 비용이 크지는 않지만 성능에 민감한 상황에서는 문제가 될 수 있다.
- 점층적 생성자 패턴 보다는 코드가 장황해서 매개변수가 4개 이상은 되어야 값어치를 한다. 하지만 API는 시간이 지날수록 매개변수가 많아지는 경향이 있음을 명심하자.

### 빌더 패턴과 자바 빈즈 패턴의 차이?
`빌더 패턴과 자바 빈즈 패턴의 가장 큰 차이점은 앞에서도 말한, 불변성에 있다.
자바 빈즈 패턴은 객체를 생성한 '후', 값을 setter 메서드를 통해 넣는다. 그렇기에 객체 사용 도중 실수로, 혹은 악의적인 목적으로 setter 메서드를 통해 유효하지 않은 값이나 null값, 혹은 정확하지 않은 값이 들어갈 수 있다.
반면, 빌더 패턴은 객체 생성 '전', 값을 setter 메서드를 통해 넣는다. 그리고 다 넣었다면 마지막에 build 메서드를 호출하여 객체를 생성한다.
그렇기 때문에 객체 사용 중에 값이 변경될 우려가 없으며, 불변성과 안정성이 올라간다.
당연하지만, 빌더 패턴 사용시에는 public setter 메서드를 선언해서는 안된다.`


