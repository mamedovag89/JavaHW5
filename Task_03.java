import java.util.ArrayList;

// На шахматной доске расставить 8 ферзей так, чтобы они не били друг друга.
// И вывести Доску.
// Пример вывода доски 8x8
// 0x000000
// 0000x000
// 00x00000
public class Task_03 {
    static boolean isIntersection(ArrayList<Integer> list, int x, int y) {
        for (int i = 0; i < x; i++) {
            if (x == i || list.get(i) == y) { // проверка пересечений по линиям
                return true;
            }
            // далее-4 цикла для проверки пересечений по диагонали
            int xTemp = i;
            int yTemp = list.get(i);
            while (xTemp < 8 && xTemp >= 0 && yTemp < 8 && yTemp >= 0) {
                if (xTemp == x && yTemp == y) {
                    return true;
                }
                xTemp++;
                yTemp++;
            }
            xTemp = i;
            yTemp = list.get(i);
            while (xTemp < 8 && xTemp >= 0 && yTemp < 8 && yTemp >= 0) {
                if (xTemp == x && yTemp == y) {
                    return true;
                }
                xTemp--;
                yTemp--;
            }
            xTemp = i;
            yTemp = list.get(i);
            while (xTemp < 8 && xTemp >= 0 && yTemp < 8 && yTemp >= 0) {
                if (xTemp == x && yTemp == y) {
                    return true;
                }
                xTemp++;
                yTemp--;
            }
            xTemp = i;
            yTemp = list.get(i);
            while (xTemp < 8 && xTemp >= 0 && yTemp < 8 && yTemp >= 0) {
                if (xTemp == x && yTemp == y) {
                    return true;
                }
                xTemp--;
                yTemp++;
            }
        }
        return false;
    }

    static ArrayList<Integer> createListPositionsQueens() {
        ArrayList<Integer> listTable = new ArrayList<>();
        listTable.add(0, (int) (Math.random() * (7))); // определяем случайный элемент первой строки
        int i = 1;
        while (i < 8 && i == listTable.size()) {
            ArrayList<Integer> listTemp = copyList(listTable); // временный массив для подбора номера столбца
            while (listTemp.size() < 8) {
                int j = getRandomColumn(listTemp);
                listTemp.add(j);
                if (!isIntersection(listTable, i, j)) { // проверка пересечений с выбранным столбцом
                    listTable.add(i, j); // если нет пересечений, то добавляем в основной лист
                    break; // и завершаем внутренний цикл
                }
            }
            i++;
        }
        return listTable;
    }

    static ArrayList<Integer> copyList(ArrayList<Integer> list) { // функция для копирования основного массива в текущий
        ArrayList<Integer> resultList = new ArrayList<>();
        for (Integer element : list) {
            resultList.add(element);
        }
        return resultList;
    }

    static void printChessBord(ArrayList<Integer> list) { // печатаем шахматную доску с расположением ферзей
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (j == list.get(i)) {
                    System.out.print("X  ");
                } else {
                    System.out.print("0  ");
                }
            }
            System.out.println();
        }
    }

    static int getRandomColumn(ArrayList<Integer> list) { // функция для поиска номера столбца, отсутствующего
                                                          // в списке
        int result = (int) (Math.random() * (8));
        while (list.contains(result)) {
            result = (int) (Math.random() * (8));
        }
        return result;
    }

    // определяем место ферзи на доске в виде адреса в массиве,
    // где индекс - номер строки, значение - номер столбца
    public static void main(String[] args) {
        try {
            int countAttempt = 50; // определяем количество попыток поиска решений
            ArrayList<Integer> listChessBord = createListPositionsQueens();
            while (listChessBord.size() < 8 && countAttempt > 0) {
                listChessBord = createListPositionsQueens();
                countAttempt--;
            }
            if (listChessBord.size() == 8) {
                System.out.printf("We could found solution after %d attempt:\n", 50 - countAttempt);
                printChessBord(listChessBord);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}