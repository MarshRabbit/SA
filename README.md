## Simulated Annealing

### 1. Simulated Annealing 이란

모의 담금질 기법 (Simulated Annealing)은 높은 온도에서 분자들의 움직임이 활발하다가 온도가 점차 낮아질수록 분자의 움직임이 점차 낮아지다가 움직임을 멈춰 결정체로 변화는 과정을 모방한 해 탐색 알고리즘이다. 이를 응용하여 처음에는 매우 넓은 범위를 이동하며 값을 찾다가 점점 온도가 낮아질수록 움직임이 감소해 점차 안정화 되면서 최선의 해를 찾는다. 

Simulated Annealing 알고리즘은 해를 반복해 개선함으로써, 현재의 해 근방에 있는 해를 임의로 찾는데, 그때에 주어진 함수의 값과 전역 인자 T가 영향을 준다.



![그림1](https://user-images.githubusercontent.com/80511335/121688023-f7ed6900-cafd-11eb-9dcd-51ea950c9f8e.png)

![Hill_Climbing_with_Simulated_Annealing](https://user-images.githubusercontent.com/80511335/121688057-ff147700-cafd-11eb-9f6c-2ce3800c8f5d.gif)

(처음 온도가 높을 때에는 현재 해와 먼 범위까지 다음해로 정하지만 온도가 점차 낮아질수록 범위가 줄어들고 결국엔 해에 도달한다.)


### 2. 탐색과정

우선 초기온도 T와, 냉각률 a를 설정하고 다른 해를 찾기 위해 이동할 확률 P=e<sup>(-d/t)</sup> 로 설정.

1) 임의의 초기해를 설정한 후 탐색을 시작한다.

2) 현재 해와 이웃하는 해를 비교한다.

- 이웃해가 더 좋을 경우 이웃해를 현재해로 설정한다.
- 이웃해가 더 안 좋을 경우 확률 P에 따라서 우수하지 않는 값이 현재해로 될 수 있는 기회를 준다.

3) 현재 온도에 냉각률을 곱해 점차 온도를 떨어트려 자유롭게 탐색할 확률을 줄이고 이 과정을 반복해 해를 구한다



### 3.  3~4차 함수의 전역 최적점

코드: src/com/company/

4차 함수 f1(x) = -3x<sup>4</sup> + 4x<sup>3</sup> -1, 3차 함수 f2(x) = -2x<sup>3</sup> -2x<sup>2</sup> + 84x 로 설정

함수의 전역 최적점 찾기는 더 나은 후보해를 선택하는 기준에 따라서 함수의 최대값이나 최소값을 구하는 것이다.

설정한 4차함수 f1(x)는 위로 볼록한 그래프로 최대값이 존재한다. 하지만 3차함수 f2(x)는 3차함수의 특성상 최대값과 최소값이 무한대로 발산하기 때문에 존재하지 않는다. 그렇기 때문에 해가 갈 수 있는 곳을 제한해 지역 최적점을 찾아 보았다.

이때 f1(x)의 전역 최적점은  -4.09일때 -240.18의 값을 가지고, f2(x)는 1에서 극댓값 0을 가진다.

결과값:

<img width="212" alt="스크린샷 2021-06-11 오후 9 44 49" src="https://user-images.githubusercontent.com/80511335/121688294-41d64f00-cafe-11eb-88c2-459049dea7d2.png">


### 4. Parameter Estimation

코드: src/parameter

연령대를 독립변수로 놓고 그에 따른 교통사고 중상자수를 종속변수로 모델을 설정했다.

이때 각 연령별로 12세  0으로, 13-20세를 1로, 21-30세가 2 인 식으로 65세 이상까지 x가 0에서 7까지 그에 따른 중상자수를 y값으로 갖는다.

<img width="148" alt="스크린샷 2021-06-11 오후 9 14 45 1" src="https://user-images.githubusercontent.com/80511335/121688577-94b00680-cafe-11eb-8709-038a7edde6a1.png">


![그래프1](https://user-images.githubusercontent.com/80511335/121688604-9b3e7e00-cafe-11eb-8aec-0ec42f95615b.png)



Simulated Annealing 방식으로 파라미터값을 구해 Curve Fitting 해보니 다음과 같은 값들이 나왔다.

250.96x^3 - 2720.13x^2 + 8443.52x + 4024.78

76.53x^3 - 1604.39x^2 + 8467.81x + 584.30

127.28x^3 - 2011.68x^2 + 7843.66x + 1999.02

431.79x^3 - 4192.62x^2 + 10802.33x + 1171.89

49.00x^3 - 751.76x^2 + 2873.77x + 6563.50

123.86x^3 - 856.49x^2 + 993.01x + 10287.44

48.09x^3 - 1377.73x^2 + 7540.85x + 614.16

184.46x^3 - 1699.15x^2 + 4695.08x + 1445.30

261.31x^3 - 2658.73x^2 + 7873.00x + 1046.18

134.77x^3 - 2142.39x^2 + 9589.59x + 1474.10

272.62x^3 - 2695.97x^2 + 7668.54x + 6687.97

35.34x^3 - 628.73x^2 + 3411.98x + 7362.56

574.41x^3 - 6089.26x^2 + 16428.18x + 741.65

69.30x^3 - 1243.80x^2 + 6402.95x + 1455.91

172.47x^3 - 2097.77x^2 + 7673.52x + 2845.38

15.87x^3  - 715.53x^2 + 3964.73x + 3706.52

304.08x^3 - 3283.59x^2 + 11139.74x + 1083.44 

198.99x^3 - 2128.88x^2 + 7071.47x + 2175.96 

261.63x^3 - 2783.22x^2 + 8290.63x + 3935.61

327.09x^3 - 3400.03x^2 + 9736.13x + 1160.38

이 중 198.99x^3 + -2128.88x^2 + 7071.47x + 2175.96 는

<img width="222" alt="스크린샷 2021-06-11 오후 9 30 24" src="https://user-images.githubusercontent.com/80511335/121688670-ad202100-cafe-11eb-9466-905e60789794.png">

의 그래프를 보인다. 



코드를 설정함에 따라서 다양한 curve fiting을 구할 수 있었다. 하지만 구해온 데이터가 독립변수의 수는 작은데 종속변수의 값은 어마어마하게 큰값을 가져 Simulated Annealing 방식으로 구하려 해도 curve fitting 모형이 거의 비슷하게 나오지는 않았다. 독립변수로 나타낼 수 있는 종속변수가 많으면 많을수록, 종속변수의 값이 너무 크거나 작지 않으면 선형모델을 거의 완벽하게 구할 수 있을것 같다.
