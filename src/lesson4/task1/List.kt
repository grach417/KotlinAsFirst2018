@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence", "NAME_SHADOWING")

package lesson4.task1

import lesson1.task1.discriminant
import java.lang.Math.pow
import kotlin.math.pow
import kotlin.math.sqrt
import lesson3.task1.maxDivisor

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = sqrt(v.map { it * it }.sum())

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = if (list.isEmpty()) 0.0 else (list.sum() / list.size)

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val m = mean(list)
    (0 until list.size).forEach { i ->
        list[i] -= m
    }
    return list
}


/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double {
    val c = mutableListOf<Double>()
    (0 until a.size).forEach { i ->
        c.add(a[i] * b[i])
    }
    return c.sum()
}


/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double {
    val r = mutableListOf<Double>()
    (0 until p.size).forEach { i ->
        r.add(p[i] * pow(x, i.toDouble()))
    }
    return r.sum()
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    if (list.isNotEmpty()) for (i in 1 until list.size) list[i] += list[i - 1]
    return list
}


/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val q = mutableListOf<Int>()
    var w = n
    (2..maxDivisor(w)).forEach { i ->
        while (w % i == 0) {
            w /= i
            q.add(i)
        }
    }
    return when {
        q.isEmpty() -> listOf(n)
        else -> q.sorted()
    }
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString("*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val l = mutableListOf<Int>()
    var num = n
    while (num > 0) {
        l.add(num % base)
        num /= base
    }
    return l.reversed()
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String = n.toString(base)

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    val q = digits.reversed()
    var w = q[0].toDouble()
    for (i in 1 until q.size) w += q[i] * base.toDouble().pow(i)
    return w.toInt()
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int = str.toInt(base)

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    val q = mapOf("M" to 1000, "CM" to 900, "D" to 500, "CD" to 400, "C" to 100, "XC" to 90,
            "L" to 50, "XL" to 40, "X" to 10, "IX" to 9, "V" to 5, "IV" to 4, "I" to 1)
    var w = n
    val e = mutableListOf<String>()
    q.forEach { (r, a) ->
        while (w >= a) {
            e.add(r)
            w -= a
        }
    }
    return e.joinToString(separator = "")
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    fun dop(n: Int): List<String> =
            when (n % 10) {
                1 -> listOf("один", "", "сто", "одна тысяча")
                2 -> listOf("два", "двадцать", "двести", "две тысячи")
                3 -> listOf("три", "тридцать", "триста", "три тысячи")
                4 -> listOf("четыре", "сорок", "четыреста", "четыре тысячи")
                5 -> listOf("пять", "пятьдесят", "пятьсот", "пять тысяч")
                6 -> listOf("шесть", "шестьдесят", "шестьсот", "шесть тысяч")
                7 -> listOf("семь", "семьдесят", "семьсот", "семь тысяч")
                8 -> listOf("восемь", "восемьдесят", "восемьсот", "восемь тысяч")
                9 -> listOf("девять", "девяносто", "девятьсот", "девять тысяч")
                else -> listOf("", "", "", "")
            }

    var q = n
    var l = listOf<String>()
    l += when (q % 100) {
        10 -> "десять"
        11 -> "одиннадцать"
        12 -> "двенадцать"
        13 -> "тринадцать"
        14 -> "четырнадцать"
        15 -> "пятнадцать"
        16 -> "шестнадцать"
        17 -> "семнадцать"
        18 -> "восемнадцать"
        19 -> "девятнадцать"
        else -> dop(q)[0]
    }
    q /= 10
    l += dop(q)[1]
    q /= 10
    l += dop(q)[2]
    q /= 10
    l += when (q % 100) {
        10 -> "десять тысяч"
        11 -> "одиннадцать тысяч"
        12 -> "двенадцать тысяч"
        13 -> "тринадцать тысяч"
        14 -> "четырнадцать тысяч"
        15 -> "пятнадцать тысяч"
        16 -> "шестнадцать тысяч"
        17 -> "семнадцать тысяч"
        18 -> "восемнадцать тысяч"
        19 -> "девятнадцать тысяч"
        else -> dop(q)[3]
    }
    if (q % 10 == 0 && q > 0) l += "тысяч"
    q /= 10
    l += dop(q)[1]
    q /= 10
    l += dop(q)[2]
    return l.reversed().filter { it != "" }.joinToString(separator = " ")
}