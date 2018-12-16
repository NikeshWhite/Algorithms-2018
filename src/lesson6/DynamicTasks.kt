@file:Suppress("UNUSED_PARAMETER")

package lesson6

/**
 * Наибольшая общая подпоследовательность.
 * Средняя
 *
 * Дано две строки, например "nematode knowledge" и "empty bottle".
 * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
 * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
 * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
 * Если общей подпоследовательности нет, вернуть пустую строку.
 * При сравнении подстрок, регистр символов *имеет* значение.
 */
fun longestCommonSubSequence(first: String, second: String): String {

    /**
     * T = O(mn), где m и n - строки и столбцы построенной матрицы
     * R = O(mn), где m и n - строки и столбцы построенной матрицы
     */

    var answer = ""

    var m = first.length
    var n = second.length

    val matrix: Array<Array<Int>> = Array(m+1) { Array(n+1) { 0 } }

    for (i in 1..m) {

        for (j in 1..n) {

            if (first[i-1] == second[j-1]) {

                matrix[i][j] = matrix[i-1][j-1] + 1
            }
            else {
                matrix[i][j] = Math.max(matrix[i-1][j], matrix[i][j-1])
            }
        }
    }

    while (m > 0 && n > 0) {
        when {
            first[m-1] == second[n - 1] -> {
                answer = first[m - 1] + answer
                m--
                n--
            }
            matrix[m][n] == matrix[m - 1][n] -> m--
            else -> n--
        }
    }

    return answer
}

/**
 * Наибольшая возрастающая подпоследовательность
 * Средняя
 *
 * Дан список целых чисел, например, [2 8 5 9 12 6].
 * Найти в нём самую длинную возрастающую подпоследовательность.
 * Элементы подпоследовательности не обязаны идти подряд,
 * но должны быть расположены в исходном списке в том же порядке.
 * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
 * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
 * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
 */
fun longestIncreasingSubSequence(list: List<Int>): List<Int> {

    /**
     * T = O(n^2), где n - размер списка целых чисел
     * R = O(n), где n - размер списка целых чисел
     */

    if (list.isEmpty() || list.size == 1) return list

    val size = list.size
    val restAnswer = Array(size) {-1}
    val maxLength = Array(size) {1}

    for (i in 0 until size) {
        for (j in 0 until i) {
            if (list[j] < list[i] && maxLength[j] + 1 > maxLength[i]) {
                maxLength[i] = maxLength[j] + 1
                restAnswer[i] = j
            }
        }
    }

    var pos = 0
    var length = maxLength[0]

    for (i in 0 until maxLength.size) {
        if (maxLength[i] > length) {
            pos = i
            length = maxLength[i]
        }
    }

    val answer = mutableListOf<Int>()

    while (pos != -1) {
        answer.add(list[pos])
        pos = restAnswer[pos]
    }

    return answer.reversed()
}

/**
 * Самый короткий маршрут на прямоугольном поле.
 * Сложная
 *
 * В файле с именем inputName задано прямоугольное поле:
 *
 * 0 2 3 2 4 1
 * 1 5 3 4 6 2
 * 2 6 2 5 1 3
 * 1 4 3 2 6 2
 * 4 2 3 1 5 0
 *
 * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
 * В каждой клетке записано некоторое натуральное число или нуль.
 * Необходимо попасть из верхней левой клетки в правую нижнюю.
 * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
 * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
 *
 * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
 */
fun shortestPathOnField(inputName: String): Int {
    TODO()
}

// Задачу "Максимальное независимое множество вершин в графе без циклов"
// смотрите в уроке 5