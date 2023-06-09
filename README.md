# TestITGeo
Test Tasks from IT GEO 2023

В этом репозитории находятся решения двух задач из тестового задания от IT GEO.
Ниже представлен текст заданий.

Задача 1.
Даны N прямоугольников. Они могут перекрывать друг друга частично либо полностью. Стороны всех прямоугольников параллельны координатным осям. Найти площадь покрытую этими прямоугольниками.

Решение первой задачи находится в пакете ru.rectangles. Алгоритм решения состоит из четырех шагов:
1. Собрать массив абсцисс вершин всех прямоугольников.
2. Разрезать все прямоугольники вертикальными линиями в полученных точках.
3. Объединить все прямоугольники с одинаковыми координатами по оси абсцисс.
4. Посчитать и просуммровать площади всех полученных прямоугольников.
Далее остается только вывести полученное значение.

Задача 2.
У продавца есть монеты достоинством p1,p2,p3...pk рублей. Сколькими способами можно разменять сумму в N рублей?

Решение второй задачи находится в пакете ru.coins. Задача решается рекурсивно.
Во внешнем цикле определяется максимально возможный номинал монеты pi (все варианты от p1 до pk), затем происходит вызов рекурсивной функции.
В рекурсивной функции считается текущий остаток. Если остаток равен нулю, то возвращается единица, что означает, что найден еще один вариант размена монет. Если остаток оказался отрицательным, возвращается 0, это означает, что такой вариант размена невозможен, новых вариантов найдено не было.
Если остаток положителен, розыгрыш продолжается. В цикле определяется максимально возможный номинал монеты pj (все варианты от p1 до pi) и происходит вызов рекурсивной функции для каждой  из pj.
В конце концов все возможные варианты размена будут найдены, выведены и посчитанны.
