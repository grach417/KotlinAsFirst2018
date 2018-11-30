@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import lesson2.task2.daysInMonth

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main(args: Array<String>) {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */

fun dateStrToDigit(str: String): String {
    val cuttingOnParts = str.split(" ").toList()
    val months = mapOf("января" to 1, "февраля" to 2, "марта" to 3,
            "апреля" to 4, "мая" to 5, "июня" to 6, "июля" to 7, "августа" to 8,
            "сентября" to 9, "октября" to 10, "ноября" to 11, "декабря" to 12)
    return when {
        cuttingOnParts.size != 3 || months[cuttingOnParts[1]] == null ||
                daysInMonth(months[cuttingOnParts[1]]!!, cuttingOnParts[2].toInt()) < cuttingOnParts[0].toInt() -> ""
        else -> String.format("%02d.%02d.%d", cuttingOnParts[0].toInt(),
                months[cuttingOnParts[1]], cuttingOnParts[2].toInt())
    }
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */

fun dateDigitToStr(digital: String): String {
    val cuttingOnParts = digital.split(".").toList()
    val months = mapOf("01" to "января", "02" to "февраля", "03" to "марта",
            "04" to "апреля", "05" to "мая", "06" to "июня", "07" to "июля", "08" to "августа",
            "09" to "сентября", "10" to "октября", "11" to "ноября", "12" to "декабря")
    return when {
        cuttingOnParts.size != 3 || months[cuttingOnParts[1]] == null ||
                daysInMonth(cuttingOnParts[1].toInt(), cuttingOnParts[2].toInt()) < cuttingOnParts[0].toInt() -> ""
        else -> String.format("%d %s %d", cuttingOnParts[0].toInt(),
                months[cuttingOnParts[1]], cuttingOnParts[2].toInt())
    }
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -98 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку
 */
fun flattenPhoneNumber(phone: String): String =
        when {
            Regex("""^(\+\d+)?\(\d+\)\d+|\d+""").matches(phone.replace(Regex("""[\s-]"""), ""))
            -> phone.replace(Regex("""[()\s-]"""), "")
            else -> ""
        }

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int = when {
    !jumps.matches(Regex("""^(?:[-%]|\d+)(?:\s+(?:\d+|[-%]))*$""")) -> -1
    else -> jumps.split(" ").filter { it.matches(Regex("""\d+""")) }.map { it.toInt() }.max() ?: -1
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки вернуть -1.
 */
fun bestHighJump(jumps: String): Int = jumps.split(Regex("""(?<=[-%+])\s""")).filter { it.contains("+") }
        .map { it.split(" ")[0].toInt() }.max() ?: -1

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int = when {
    expression.matches(Regex("""^(?:\d+)(?:\s[-+]\s\d+)*$"""))
    -> expression.split(Regex("""\s+(?=[-+])""")).map { it.replace(" ", "").toInt() }.sum()
    else -> throw IllegalArgumentException()
}


/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    val q = str.split(" ")
    var w = 0
    (1 until q.size).forEach { i ->
        if (q[i - 1].toLowerCase() == q[i].toLowerCase()) return w
        w += q[i - 1].length + 1
    }
    return -1
}


/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String =
        when {
            description.matches(Regex("""(?:[А-Я][а-я]*\s\d+.?\d)(?:;\s[А-Я][а-я]*\s\d+.?\d)*"""))
            -> description.split(Regex("""\s*;\s*""")).maxBy { it.split(" ")[1].toDouble() }
                    ?.split(" ")?.get(0) ?: ""
            else -> ""
        }

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int {
    var res = 0
    var sum = 0
    return when {
        !roman.matches(Regex("""^M{0,3}(?:|CM|DC{0,3}|CD|C{0,3})?(?:XC|LX{0,3}|XL|X{0,3})?(?:IX|VI{0,3}|IV|I{1,3})?$"""))
                || roman.isEmpty() -> -1
        else -> {
            (roman.length - 1 downTo 0).forEach { i ->
                val numbers = mapOf('I' to 1, 'V' to 5, 'X' to 10, 'L' to 50,
                        'C' to 100, 'D' to 500, 'M' to 1000)[roman[i]] ?: 0
                res += when {
                    numbers < sum -> numbers * -1
                    else -> numbers
                }
                sum = numbers
            }
            res
        }
    }
}


/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> {
    var q = 0
    var numberOfCommands = 0
    var number = cells / 2
    val cellsSize = mutableListOf<Int>()
    val furstBrackets = mutableMapOf<Int, Int>()
    val secondBrackets = mutableListOf<Int>()
    (0 until cells).forEach { _ ->
        cellsSize.add(0)
    }
    (0 until commands.length).forEach { i ->
        when (commands[i]) {
            !in setOf('+', '-', '[', ']', '<', '>', ' ') -> throw IllegalArgumentException()
            '[' -> secondBrackets.add(i)
            ']' -> when {
                secondBrackets.isEmpty() -> throw IllegalArgumentException()
                else -> {
                    furstBrackets += secondBrackets.last() to i
                    furstBrackets += i to secondBrackets.last()
                    secondBrackets.remove(secondBrackets.last())
                }
            }
        }
    }
    when {
        secondBrackets.isNotEmpty() -> throw IllegalArgumentException()
        else -> {
            while (++numberOfCommands <= limit && q < commands.length) {
                when (commands[q]) {
                    '+' -> {
                        cellsSize[number]++
                        q++
                    }
                    '-' -> {
                        cellsSize[number]--
                        q++
                    }
                    '<' -> when {
                        --number >= 0 -> q++
                        else -> throw IllegalStateException()
                    }
                    '>' -> when {
                        ++number < cells -> q++
                        else -> throw IllegalStateException()

                    }
                    '[' -> when {
                        cellsSize[number] != 0 -> q++
                        else -> q = furstBrackets[q]!! + 1
                    }
                    ']' -> when {
                        cellsSize[number] == 0 -> q++
                        else -> q = furstBrackets[q]!! + 1
                    }
                    else -> q++
                }
            }
            return cellsSize
        }
    }
}






