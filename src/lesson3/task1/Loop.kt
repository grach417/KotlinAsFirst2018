@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import lesson1.task1.sqr
import java.lang.Math.PI
import java.lang.Math.pow
import kotlin.math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var numbers = 0
    var nuM = n
    do {
        numbers++
        nuM /= 10
    } while (pow(nuM.toDouble(), 2.0) > 0)
    return numbers
}

/**
 *
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var q: Int
    var nuM = 0
    var nfib = 0
    for (i in 1..n) {
        if (i == 1) {
            nfib = 1
        } else {
            q = nuM
            nuM = nfib
            nfib += q
        }
    }
    return nfib
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    val max = max(m, n)
    val min = min(m, n)
    var k = 1
    while ((max * k).rem(min) != 0) k++
    return max * k
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var q = n
    if (n.rem(2) == 0) q = 2
    else for (i in 3..n / 3 step 2) {
        if (n.rem(i) == 0) {
            q = i
            break
        }
    }
    return q
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int = n / minDivisor(n)

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var q = m
    var w = n
    while ((q != 1) && (w != 1)) {
        when {
            max(q, w).rem(min(q, w)) == 0 -> return false
            q > w -> q %= w
            else -> w %= q
        }
    }
    return true
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean = sqr(sqrt(n.toDouble()).toInt()) in m..n

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var steps = 0
    var num = x
    while (num > 1) {
        steps++
        if (num % 2 == 0) num /= 2 else {
            num = 3 * num + 1
        }
    }
    return steps
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double  {
    val u = x.rem(PI * 2)
    var f = 1
    var w = 1.0
    var b = 1
    var s = 0.0
    while (abs(pow(u, w) / factorial(f)) >= eps) {
        s += b * pow(u, w) / factorial(f)
        b *= -1
        w += 2
        f += 2
    }
    return s
}


/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    val unit = x.rem(PI * 2)
    var f = 2
    var w = 2.0
    var b = -1
    var s = 1.0
    while (abs(pow(unit, w) / factorial(f)) >= eps) {
        s += b * pow(unit, w) / factorial(f)
        b *= -1
        w += 2
        f += 2
    }
    return s
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var num = 0.0
    var mun = 0.0
    val quan = digitNumber(n)
    if (quan == 1) num = n.toDouble()
    else for (i in 1..quan) {
        num += (n % pow(10.0, i * 1.0) - mun) / pow(10.0, (i - 1) * 1.0) * pow(10.0, (quan - i).toDouble())
        mun = n % pow(10.0, i * 1.0)
    }
    return num.toInt()
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean = n == revert(n)



/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var q = n.rem(10)
    var w = 0
    for (i in 0 until digitNumber(n)) {
        w += q
        q *= 10
    }
    return w != n
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int{
    var q = 0
    var l = 0
    var w: Int
    while (l < n) {
        q++
        w = q * q
        while (w > 0) {
            l++
            w /= 10
        }
    }
    return (q * q % pow(10.0, (l - n + 1) * 1.0) / pow(10.0, (l - n) * 1.0)).toInt()
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var q = 1
    var l = 1
    while (l < n) {
        q++
        var fbnch = fib(q)
        while (fbnch > 0) {
            l++
            fbnch /= 10
        }
    }
    return (fib(q) % pow(10.0, (l - n + 1) * 1.0) / pow(10.0, (l - n) * 1.0)).toInt()
}
