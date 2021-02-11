일급 컬렉션
===================

제가 더 나은 개발자로 한 발자국 나아가기 위해 나아가기 위해 필요하다고 느낀
**일급 컬렉션** 에 대해 정리해보겠습니다.

## **일급 컬렉션** 이란?
Collection을 Wrapping하면서, 그 외 다른 멤버 변수가 없는 상태를 일급 컬렉션이라 합니다.

간단하게 설명해보겠습니다.

```java
Map<String, String> map = new HashMap<>();
map.put("1", "the");
map.put("2", "na");
map.put("3", "un");
```
위의 코드를 Wrapping하는 것을 이야기 합니다.

```java
public class MyCollection {
    private Map<String,String> map;
    
    public MyCollection(Map<String,String> map) {
        this.map = map;
    }
}
```

- Wrapping 함으로써 아래와 같은 장점을 가지게 됩니다. 
    - 비지니스에 종속적인 자료구조
    - Collection의 불변성을 보장
    - 상태와 행위를 한 곳에서 관리
    - 이름이 있는 컬렉션
    
### 비지니스에 종속적인 자료구조

예를 들어 다음과 같은 조건으로 `더나은 멘토 선거`를 만든다고 가정하겠습니다.

- 조건
    - 멘토는 3명만 출마 할 수 있다.
    - 멘토들의 닉네임은 중복될 수 없다.
    
일반적인 구현
```java
public class electionService {
    private static final int MAX_NUMBER_SIZE = 3;

    public void createNumber() { // 검증 로직
        List<Long> numbers = createNonValidateNumbers();
        validateSize(numbers);
        validateNickname(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if(numbers.size != MAX_NUMBER_SIZE) {
            throw new IllegalArgumentException("멘토는 3명만 출마 가능 합니다.");
        }
    }

    private void validateNickname(List<Integer> Nickname) {
        Set<Integer> checkNickname = new HashSet<>(Nickname);
        if(checkNickname.size() != MAX_NUMBER_SIZE) {
            throw new IllegalArgumentException("멘토들의 닉네임은 중복될 수 없습니다.");
        }
    }
}
```
서비스 메소드에서 비지니스 로직을 처리했습니다. 하지만 이럴 경우 큰 문제가 있습니다.

- 서비스에서 모든 비지니스 로직을 처리하게 된다.
- 출마한 멘토가 필요한 모든 장소에서 검증 로직이 필요
- 오늘 새로 들어온 멘티분들은 어떻게 이 검증로직이 필요한지 알 수 있을까요?

등등 모든 코드와 도메인을 알고 있지 않으면 언제든 문제가 발생할 가능성이 있습니다.

그렇다면 이 문제를 깔끔하게 해결할 수 있는 방법은 없을까요?
- 3명의 멘토만 출마 할 수 있고.
- 멘토들의 닉네임은 중복되지 않아야 한다.

이런 자료구조 없을까요? 없으면 직접 만들면 됩니다.

아래와 같이 해당 조건으로만 생성 할 수 있는 자료구조를 만들면 위에서 언급한 문제들이 모두 해결됩니다.
그리고 이런 클래스를 우린 일급 컬렉션이라고 부릅니다.

```java
public class ElectionRegistration {
    private static final int MAX_NUMBER_SIZE = 3;

    private final List<Integer> numbers;

    public ElectionRegistration(List<Integer> numbers) {
        validateSize(numbers);
        validateNickname(numbers);
        this.numbers = numbers;
    }

    private void validateSize(List<Integer> numbers) {
        if(numbers.size != MAX_NUMBER_SIZE) {
            throw new IllegalArgumentException("멘토는 3명만 출마 가능 합니다.");
        }
    }

    private void validateNickname(List<Integer> Nickname) {
        Set<Integer> checkNickname = new HashSet<>(Nickname);
        if(checkNickname.size() != MAX_NUMBER_SIZE) {
            throw new IllegalArgumentException("멘토들의 닉네임은 중복될 수 없습니다.");
        }
    }
}
```
위 코드를 보면,
- 위의 생성자 로직을 통해 3명, 중복되지 않은 닉네임만 가능한 자료구조임을 볼 수 있습니다.
- 비지니스 로직이 모두 일급 객체안에 존재합니다.

이제 멘토가 필요한 모든 로직은 이 일급 컬렉션만 있으면 됩니다.

```java
public class electionRegistration2 {
    public void createElectionRegistration() {
        ElectionRegistration electionRegistration = new ElectionRegistration(createNonValidateNumbers);
    }
}
```

비지니스에 종속적인 자료구조가 만들어져, 이후 발생할 문제가 최소화 되었습니다.


### 불변 자료구조

일급 컬렉션을 사용하게 되면 컬렉션 인스턴스에 `불변`을 보장할 수 있습니다.

즉, 재할당 뿐만 아니라 내부 요소들이 바뀌지 않는 안전한 인스턴스로 만들 수 있습니다.

final 의 의미는 재할당 금지하는 것입니다.

제가 처음 프로그래밍을 배울 때에는 final = 상수 라는 키워드로 final 을 접하였기 때문에 값을 고정시킨다 정도의 의미로 이해하고 있었습니다.

하지만 final 의 키워드를 정확히 알지 못하면 자신의 의도했던 흐름으로 코드가 동작하지 않을 수 있습니다.

```java
public static void main(String[] args) {
  final List<Integer> numbers = new ArrayList<>(Arrays.asList(1,2,3));
  numbers.add(4); // 가능
  numbers = new ArrayList<>(); // 재할당 금지, 컴파일 에러!
}
```

보신것처럼 위에서 값의 추가는 가능하지만, 재할당시 컴파일 에러가 발생합니다. 즉, final은 `재할당`만 금지합니다. 

`불변 객체`는 아주 중요합니다.
각각의 객체들이 절대 값이 바뀔일이 없다는게 보장되면 그만큼 코드를 이해하고 수정하는데 사이드 이펙트가 최소화되기 때문입니다.

final로 그 문제를 해결할 수 없기 때문에 일급 컬렉션 (Frist Class Collection)과 래퍼 클래스 (Wrapper Class) 등의 방법으로 해결해야하만 합니다.
위 예제를 보면 List 타입에 final 키워드를 선언했음에도 언제든지 가지고 있는 요소들이 변경될 수 있음을 알 수 있습니다.

final로 그 문제를 해결할 수 없기 때문에 `일급 컬렉션 (Frist Class Collection)`과 `래퍼 클래스 (Wrapper Class)` 등의 방법으로 해결해야하만 합니다.

그래서 아래와 같이 컬렉션의 값을 변경할 수 있는 메소드가 없는 컬렉션을 만들면 `불변 컬렉션`이 됩니다.

따라서 아래와 같이 `불변 컬렉션`으로 만듭니다.

```java
class Numbers {
  private final List<Integer> list;

  public Numbers(List<Integer> list){
    this.list = new ArrayList<>(list);
  }

  public List<Integer> getList(){
    return Collections.unmodifiableList(list);
  }
}
```

위 클래스는 생성자와 getList()외에 다른 메소드가 없습니다. 즉, 이 클래스의 사용법은 새로 만들거나 값을 가져오는 것뿐인거죠.

List라는 컬렉션에 접근할 수 있는 방법이 없기 때문에 값을 변경/추가가 안됩니다

생성자에서 파라미터로 받아온 컬렉션 객체를 이용하여 필드를 초기화하고

Collections.unmodifiableList() 메소드를 이용하여 getter 를 통한 참조한 필드 요소들이 변경될 위험을 방지한다면

불변함을 보장할 수 있습니다.


### 상태와 행위를 한 곳에서 관리
일급 컬렉션의 또 하나의 장점은 값과 로직이 함께 존재한다는 것입니다. 이는 Enum 장점과도 동일합니다.

예를 들어 여러 멘토들이 있고, 이 중 멘토들에게 지정된 멘티들의 수의 합이 필요하다고 가정해보겠습니다.
일반적으로는 아래와 같이 작성합니다.

```java
    @Test
    public void 로직이_밖에_있는_상태() {
        // given, 값은 여기있는데
        List<Mentor> mentors = Arrays.asList(
                new Mentor(WOODY_MENTEE, 10),
                new Mentor(FOO_MENTEE, 15),
                new Mentor(WOODY_MENTEE, 10),
                new Mentor(SIMBA_MENTEE, 20));
        // when, 계산은 여기서
        Long woodyMenteeSum = mentors.stream()
                .filter(mentor -> mentor.getMentorType().equals(WOODY_MENTEE))
                .mapToLong(Mentor::getSum)
                .sum();
        // then
        assertThat(woodyMenteeSum).isEqualTo(20);
    }
```

여러 멘토들에게 지정된 멘티들의 수가 모여있고, 특정 멘토의 멘티 수만 필요한 상황
- 값을 계산하는 로직이 여러곳에서 중복발생
- 로직 변경시 모든곳을 다 변경해주어야 한다.
- 우디 멘토 이외의 다른 멘토에게 지정된 멘티의 총합도 필요하다면 코드가 흩어질 가능성이 높다.

결국 우디멘토에게 지정된 멘티의 총 수를 뽑으려면 이렇게 해야한다는 계산식을 컬렉션과 함께 두어야 합니다.
만약 우디 멘토 외에 푸, 심바 멘토의 총 멘티수도 필요하다면 더더욱 코드가 흩어질 확률이 높습니다.

그래서 이 문제 역시 일급 컬렉션으로 해결합니다.

```java
public class MentorGroups {
    private List<Mentor> mentors;

    public MentorGroups(List<Mentor> mentors) {
        this.mentors = mentors;
    }

    public Long getWoodyMenteeSum() {
        return mentors.stream()
                .filter(mentor -> MentorType.isWoodyMentee(mentor.getMentorType()))
                .mapToLong(Mentor::getAmount)
                .sum();
    }
}
```

만약 다른 멘토의 멘티 수 총합이 필요하다면 아래와 같이 리팩토링이 가능합니다.

```java
    public class MentorGroups {
        private List<Mentor> mentors;

        public MentorGroups(List<Mentor> mentors) {
            this.mentors = mentors;
        }

        public Long getWoodyMenteeSum() {
            return getFilteredMentors(mentor -> MentorType.isWoodyMentee(mentor.getMentorType()));
        }

        public Long getFooMenteeSum() {
            return getFilteredMentors(mentor -> MentorType.isFooMentee(mentor.getMentorType()));
        }

        private Long getFilteredMentors(Predicate<Mentor> predicate) {
            return mentors.stream()
                    .filter(predicate)
                    .mapToLong(Mentor::getAmount)
                    .sum();
        }
    }
```

이렇게 MentorFroups라는 일급 컬렉션이 생김으로써 상태와 로직이 한 곳에서 관리 됩니다.

```java
    @Test
    public void 로직과_값이_한곳에() {
        // given
        List<Mentor> mentors = Arrays.asList(
                new Mentor(WOODY_MENTEE, 10),
                new Mentor(FOO_MENTEE, 15),
                new Mentor(WOODY_MENTEE, 10),
                new Mentor(SIMBA_MENTEE, 20));

        MentorGroups mentorGroups = new MentorGroups(mentors);

        // when
        Long woodyMenteeSum = mentorGroups.getWoodyMenteeSum();

        // then
        assertThat(woodyMenteeSum).isEqualTo(20);
    }
}
```

### 이름이 있는 컬렉션
- 변수명만으로는 의미를 부여하기 힘든것이 현실.
- 각각의 일급 컬렉션을 만듬으로써 이 컬렉션 기반의 용어 사용, 검색을 사용하면된다.

```java
@Test
public void 컬렉션을_변수명으로() {
    // given
    List<Mentor> WoodyMentees = createWoodyMentees();
    List<Mentor> fooMentees = createFooMentees();
}
```
위 코드의 단점은 뭘까요?
- 검색이 어렵다.
  - 우디 멘티의 그룹이 어떻게 사용되는지 검색시 변수명으로만 검색할 수 있습니다.
  - 이 상황에서 검색은 거의 불가능합니다. 우디 멘티 그룹이라는 뜻은 개발자 마자 다르게 지을 수 있기 때문입니다.
  - 우디 멘티 그룹은 어떤 검색어로 검색이 가능할까요?
- 명확한 표현이 불가능
  - 변수명에 불과하기 때문에 의미를 부여하기가 어렵습니다.
  - 이는 서로 다른 팀에서 의사소통시 보편적인 언어로 사용하기가 어렵다는 것을 이야기합니다.
  - 중요한 값임에도 이를 표현할 명확한 단어가 없다는 것입니다.
  
위 문제 역시 일급컬렉션으로 쉽게 해결이 가능합니다.
우디 멘티 그룹과 푸 멘티 그룹 각각의 일급 컬렉션을 만들면 이 컬렉션 기반으로 용어 사용과 검색을 하면 됩니다.

```java
@Test
public void 일급컬렉션의_이름으로() {
    // given
    WoodyMentees woodyMentees = new WoodyMentees(createWoodyMentees));
    FooMentees fooMentees = new FooMentees(createFooMentees));
}
```

서로 다른 팀에서 사용될 표현은 이제 이 컬렉션에 맞추면 됩니다.
검색 역시 이 컬렉션 클래스를 검색하면 모든 사용 코드를 찾아낼 수 있습니다.

---

일급 컬렉션은 객체지향 코드로 가기 위해 꼭 익혀야할 방법 중 하나입니다.