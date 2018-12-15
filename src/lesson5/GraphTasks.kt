@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson5

import java.util.*
import kotlin.math.max

/**
 * Эйлеров цикл.
 * Средняя
 *
 * Дан граф (получатель). Найти по нему любой Эйлеров цикл.
 * Если в графе нет Эйлеровых циклов, вернуть пустой список.
 * Соседние дуги в списке-результате должны быть инцидентны друг другу,
 * а первая дуга в списке инцидентна последней.
 * Длина списка, если он не пуст, должна быть равна количеству дуг в графе.
 * Веса дуг никак не учитываются.
 *
 * Пример:
 *
 *      G -- H
 *      |    |
 * A -- B -- C -- D
 * |    |    |    |
 * E    F -- I    |
 * |              |
 * J ------------ K
 *
 * Вариант ответа: A, E, J, K, D, C, H, G, B, C, I, F, B, A
 *
 * Справка: Эйлеров цикл -- это цикл, проходящий через все рёбра
 * связного графа ровно по одному разу
 */

/**
 * Информация о Эйлеровых циклах взята с ресурса: https://neerc.ifmo.ru/wiki/index.php?title=Алгоритм_построения_Эйлерова_цикла
 */

fun Graph.checkEulerLoop(): Boolean {

    for(vertex in vertices) {

        if(getNeighbors(vertex).size % 2 == 0)
            return true
    }
    return false
}

/**
 * T = O(E), где E - ребра графа
 * R = O(V + E), где V - вершины графа
 */

fun Graph.findEulerLoop(): List<Graph.Edge> {

    if (!this.checkEulerLoop()) return emptyList()

    val stack = Stack <Graph.Vertex>()

    val answer = ArrayDeque<Graph.Vertex>()
    stack.push(vertices.first())

    val edgesList = mutableSetOf<Graph.Edge>()
    edgesList.addAll(edges)

    while (!stack.isEmpty()) {

        val lastElement = stack.lastElement()

        for (vertex in vertices) {

            val newEdge = getConnection(lastElement, vertex)
            if (edgesList.contains(newEdge) && newEdge != null) {

                stack.push(vertex)
                edgesList.remove(newEdge)
                break
            }
        }
        if (lastElement == stack.last()) {
            stack.pop()
            answer.add(lastElement)
        }
    }

    val completeAnswer = mutableListOf<Graph.Edge>()

    (0..answer.size - 2).forEach {
        getConnection(answer.poll(), answer.first)?.let { it1 -> completeAnswer.add(it1) }
    }

    return completeAnswer
}

/**
 * Минимальное остовное дерево.
 * Средняя
 *
 * Дан граф (получатель). Найти по нему минимальное остовное дерево.
 * Если есть несколько минимальных остовных деревьев с одинаковым числом дуг,
 * вернуть любое из них. Веса дуг не учитывать.
 *
 * Пример:
 *
 *      G -- H
 *      |    |
 * A -- B -- C -- D
 * |    |    |    |
 * E    F -- I    |
 * |              |
 * J ------------ K
 *
 * Ответ:
 *
 *      G    H
 *      |    |
 * A -- B -- C -- D
 * |    |    |
 * E    F    I
 * |
 * J ------------ K
 */
fun Graph.minimumSpanningTree(): Graph {
    TODO()
}

/**
 * Максимальное независимое множество вершин в графе без циклов.
 * Сложная
 *
 * Дан граф без циклов (получатель), например
 *
 *      G -- H -- J
 *      |
 * A -- B -- D
 * |         |
 * C -- F    I
 * |
 * E
 *
 * Найти в нём самое большое независимое множество вершин и вернуть его.
 * Никакая пара вершин в независимом множестве не должна быть связана ребром.
 *
 * Если самых больших множеств несколько, приоритет имеет то из них,
 * в котором вершины расположены раньше во множестве this.vertices (начиная с первых).
 *
 * В данном случае ответ (A, E, F, D, G, J)
 *
 * Эта задача может быть зачтена за пятый и шестой урок одновременно
 */
fun Graph.largestIndependentVertexSet(): Set<Graph.Vertex> {

    val startVertex = this.vertices.first()

    val child = mutableSetOf<Graph.Vertex>()
    val grandchild = mutableSetOf<Graph.Vertex>(startVertex)

    findChildren(startVertex, child, grandchild)

    return if (grandchild.size >= child.size) grandchild
    else child
}

fun Graph.findChildren(vertex: Graph.Vertex, child: MutableSet<Graph.Vertex>, grandchild: MutableSet<Graph.Vertex>) {

    for (i in getNeighbors(vertex)) {

        if (!child.contains(i) && !grandchild.contains(i)) {

            if (grandchild.contains(vertex)) {
                child.add(i)
            } else {
                grandchild.add(i)
            }
            findChildren(i, child, grandchild)
        }
    }
}


/**
 * Наидлиннейший простой путь.
 * Сложная
 *
 * Дан граф (получатель). Найти в нём простой путь, включающий максимальное количество рёбер.
 * Простым считается путь, вершины в котором не повторяются.
 * Если таких путей несколько, вернуть любой из них.
 *
 * Пример:
 *
 *      G -- H
 *      |    |
 * A -- B -- C -- D
 * |    |    |    |
 * E    F -- I    |
 * |              |
 * J ------------ K
 *
 * Ответ: A, E, J, K, D, C, H, G, B, F, I
 */
fun Graph.longestSimplePath(): Path {
    TODO()
}