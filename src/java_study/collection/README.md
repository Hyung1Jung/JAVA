Proxy는 범용적인 단어이다.

Spring Proxy, Proxy Pattern, Network Proxy 등 여러가지 단어가 있지만 내가 공부한 것은 Network Proxy이다.

## Proxy 서버?
- 클라이언트와 서버간의 중계 서버로, 통신을 대리 수행하는 서버
- 캐시/ 보안/ 트래픽 분산 등 여러 장점을 가질 수 있음

간단하게 말하면 <U>대신 처리하는 서버</U>라고 할 수 있다.

![2](https://user-images.githubusercontent.com/43127088/107846330-40ce3300-6e26-11eb-8d07-b12f084e2fe9.PNG)


사용자는 클라이언트라고 하겠다. 프록시가 서버와 클라이언트 사이의 위치하고 있는 것을 볼 수 있다.

## Proxy 종류?
- Forward Proxy
- Reverse Proxy

가 있다. 아래를 보자.

## Forward Proxy
일반적으로 말하는 Proxy는 Forward Proxy이다.

흔히 일상에서 듣는 Proxy들은

- Proxy 서버 설정을 한다.
-인터넷 속도를 향상시키기 위해 Proxy 설정을 한다.
- 외국에서 접속하는 것처럼 테스트하기 위해 Proxy 설정을 한다.
- 개인정보를 빼돌린 해커 A씨는 IP추적을 방지하기 위해 Proxy 설정을 한다.
- 이런 말들이 Forward Proxy이다.

Forward Proxy는 인터넷과 클라이언트 사이의 위치하고 있다.

![3](https://user-images.githubusercontent.com/43127088/107846375-8be84600-6e26-11eb-8547-d4c0cc3674fd.PNG)

Forward Proxy가 사이에서 흐름을 대신 처리하고 있다.

### Forward Proxy 특징

 <span style="color:red"> 1. 캐싱 : 클라이언트가 요청한 내용을 캐싱



클라이언트1이 서버에게 "오늘 날씨 어때?" 라고 물어봤다.

서버는 "오늘 비가오는데?" 라고 대답을 해줬다.

그럼 Foward Proxy가 " 오늘 비가 온다" 라고 저장을 해준다. 이것을 캐싱이라고 한다.



그럼 다음 클라이언트2가 또 "오늘 날씨 어때?" 라고 물어봤다.

그럼 Foward Proxy가 " 어 나 그거 알고있는데?" 라고 하며 서버를 거치지 않고, "오늘 비가 온다" 라고 알려준다.

- 전송 시간 절약
- 불필요한 외부 전송
- 외부 요청 감소 -> 네트워크 병목 현상 방지

<span style="color:red"> 2. 익명성 : 클라이언트가 보낸 요청을 감춤



<span style="color:blue">Clients -> Internet -> Servers

클라이언트가 서버로 직접 호출을 할때, 우리가 요청한 것을 그대로 서버에게 전달해준다.

ex) ID를 받을 수도 있고, 장비 정보나 OS 정보를 받을 수도 있다.


<span style="color:blue">Clients -> Forward Proxy -> Internet -> Servers

만약 가운데 Forward Proxy를 넣게 되면 마치 우리가 요청 했지만, Forward Proxy가 요청을 한 것처럼  서버들에게 Forward Proxy 정보를 전달 해 줄 수 있다.


- Server가 응답 받은 요청을 누가 보넀는지 알지 못하게 함
- 그래서 Server가 받은 요청 IP = Proxy IP 가 된다.

### Reverse Proxy
Reverse Proxy는 Forwad Proxy와 비슷하지만, 다른 점이 있다. 인터넷과 서버 사이에 위치하고 있다.

흐름은 Foward proxy와 비슷하다.

### Reverse Proxy 특징

<span style="color:red"> 1. 캐싱 : 클라이언트가 요청한 내용을 캐싱, 위에서 말한 Forward Proxy와 동일

// 내용 Foward proxy와 동일하기 때문에 생략..

<span style="color:red"> 2. 보안 : 서버 정보를 클라이언트로부터 숨김

1. 클라이언트는 요청을 할 때, 서버들을 직접 알지 못하고

2. 클라이언트 입장에서 Server Reverse proxy에게 전달을 한다.

3. 그럼 Reverse Proxy가 자기가 알고 있는 서버들에게 요청을 전달한다.

- Client는 Reverse Proxy를 실제 서버라고 생각하여 요청하고
- 실제 서버의 IP가 노출되지 않는다,

<span style="color:red"> 3. Load Balancing : 부하분산 / 해야할 작업을 나누어 서버의 부하를 분산시키는 것

Reverse Proxy에서 Reverse Proxy을 하는 경우도 있고 하지 않는 경우도 있다. (선택적)


요청들을 각각 원하는 곳에 나눠 주는 것을 Load Balancing 이라고 할 수 있다.  즉, 한마디로 서버들에게 요청을 나눠준다.

그런데 왜 쓸까? Load Balancing를 쓴 배경이 무엇일까?

<span style="color:blue">Client -> Server

서비스가 하나 있다고 가정하자.

사용자가는 한 명 이였고 서버는 잘 돌아가고 있었다.

<span style="color:blue">Client1, Client2, Client3, Client4, Client5........Client100 -> Server

하지만 점점 사용자가 늘어났다. 늘어남에 따라 서버는 부하가 생겼다.이에 따라 개발자들은 이 부하를 어떻게 하면 좋을까? 고민을 한 결과,


"Scale up!, Server의 하드웨어 성능을 높여보자" 라고 생각을 하였다. 메모리도 때려넣고, CPU도 때려넣고 해서Server는 Lv10.Server로 레벨 업을 하였다.


요청을 잘 전달하고 소화를 하고 있엇는데, 점점 또 늘어나다가 결국

<span style="color:blue">Client101, Client102, Client103, Client104, Client105........Client100000이 되어버려서 Lv100.Server을 만들자 라고 생각을 하였다. 하지만 메모리를 꼽을 수 있는 소켓은 한정적이였다.



그래서 어떤 개발자가 또



우리 그럼 "Scale Out!, 여러 대의 Server가 나누어 일을 하게 하자" 라고 생각을 하였다.

그리하여 위에 있는 그림처럼 되었다.


정리하자면, <U>여러 대의 서버가 분산(나누어) 처리할 수 있도록 요청을 나누어주는 서비스</U>



### Load Balancing의 종류



L2, L3, L4, L7가 있다.OSI 7 Layer 기준으로 어떤 것을 나누는지에 따라 다르다.

- L2 : Mac 주소를 바탕으로 Load Balancing
- L3 : IP 주소를바탕으로 Load Balancing
백엔드 개발자는 L4와 L7에 관해 잘 알고 있어야 한다.L4 : Transprot Layer (IP & Port) Level에서 Load Balancing , (TCP/UDP)

- Ex) https://Hyung1Jung.tistory.com/ 로 접근 시 서버 A, 서버 B로 Load Balancing
L7 : Application Layer (User Request) Level에서 Load Balancing, (HTTPS/HTTP/FTP)

- Ex) https://Hyung1Jung.tistory.com/로 접근시 /category와 /search를 담당 서버들로 로드 Load Balancing
- 뒤에 무엇을 붙이느냐, url에 따라서, 혹은 queryParam에 따라서 등 애플리케이션을 요청하는 방법에 따라서 어떤 서버로 Load Balancing 할 것인지가 L7

참고 문헌 :

www.javacodemonk.com/forward-proxy-and-reverse-proxy-server-065e7599

www.accuwebhosting.com/blog/key-benefits-of-using-load-balancing-for-websites/﻿