# Complex-Quantum-Reals
This repository is about a new way to consider numbers. The approach attempts to involve quantum theory at the heart of quantum bits to introduce a relativity on the question of whether a number is a pure real number, a pure imaginary number or a complex number.

As a summary, we propose an algebra where items are of the form
q= x(p00|0> + p01|1>) + iy(p10|0> + p11|1>)
p00, p01, p10, p11 : [0,1]
p00 + p01 = 1 && p10 + p11 = 1
i^{2}=-1

Following the values of probability terms p00, p01, p10, p11, term q can be a pure real number 0 or  x, a pure imaginary number iy or a complex number x+iy. What is interesting here is that we don't care about it, throughout our operations, until we really want to know the real state with the consequence of degenerating q once for all. The degeneration operation resolve $p00|0> + p01|1>$ and $p10|0> + p11|1>$ to $0$ or $1$.

The algebra is provided with two operations for now: the addition (+) and the multiplication (•).

q_1= x_{1}(p_{1}00|0> + p_{1}01|1>) + iy_{1}(p_{1}10|0> + p_{1}11|1>)
q_2= x_{2}(p_{2}00|0> + p_{2}01|1>) + iy_{2}(p_{2}10|0> + p_{2}11|1>)

The possible real parts of $ q_1 + q_2 $ are $0$, $x_{1}((1-p_{1}01*p_{2}00)|0> + p_{1}01*p_{2}00|1>)$, $x_{2}((1-p_{2}01*p_{1}00)|0> + p_{2}01*p_{1}00|1>)$ and $(x_{1}+x_{2})((1-p_{2}01*p_{1}01)|0> + p_{2}01*p_{1}01|1>)$.

The possible imaginary parts of $ q_1 + q_2 $ are $0$, $iy_{1}((1-p_{1}11*p_{2}10)|0> + p_{1}11*p_{2}10|1>)$, $iy_{2}((1-p_{2}11*p_{1}10)|0> + p_{2}11*p_{1}10|1>)$ and $i(y_{1}+y_{2})((1-p_{2}11*p_{1}11)|0> + p_{2}11*p_{1}11|1>)$.

Thus,  q_1 + q_2  produces 16 items that will resolve to one if the degeneration is performed.

Following the same reasoning, q_1 • q_2 produces 256 items that will resolve to one if the degeneration is performed.
