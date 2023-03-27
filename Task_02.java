//package HW5Java;

// Пусть дан список сотрудников:
// Иван Иванов, Светлана Петрова, Кристина Белова, Анна Мусина, Анна Крутова, Иван Юрин, Петр Лыков
// Павел Чернов, Петр Чернышов, Мария Федорова, Марина Светлова, Мария Савина, Мария Рыкова, Марина Лугова
// Анна Владимирова, Иван Мечников, Петр Петин, Иван Ежов.
//     Написать программу, которая найдет и выведет повторяющиеся имена с количеством повторений.
// Отсортировать по убыванию популярности Имени.

import java.util.Map;
import java.util.HashMap;

public class Task_02 {
    static Map<String, Integer> getCountNames(String[][] namesList) { // получаем количество повторений имен
        Map<String, Integer> namesListResult = new HashMap<>();
        int countNames;
        for (String[] line : namesList) {
            String nameTemp = line[0];
            if (namesListResult.containsKey(nameTemp)) {
                countNames = namesListResult.get(nameTemp);
            } else {
                countNames = 0;
            }
            namesListResult.put(nameTemp, ++countNames);
        }
        return namesListResult;
    }

    static void sortTable(String[][] table) { // сортируем двумерный массив
        for (int i = 0; i < table.length - 1; i++) {
            changeElements(table, i);
        }
    }

    static void changeElements(String[][] table, int index) { // сравниваем соседние элементы и меняем,
        try { // если следующий больше текущего
            Integer tempValueInt = Integer.parseInt(table[index][1]);
            Integer nextValue = Integer.parseInt(table[index + 1][1]);

            if (tempValueInt < nextValue) {
                String tempKey = table[index][0];
                String tempValue = table[index][1];
                table[index][0] = table[index + 1][0];
                table[index + 1][0] = tempKey;
                table[index][1] = table[index + 1][1];
                table[index + 1][1] = tempValue;
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }

    static String[][] convertArrayToTable(String arr[]) { // переводим одномерный массив в двумерный,
        String[][] resultTable = new String[arr.length][2]; // разделяя имена и фамилии
        String[] temp;
        for (int i = 0; i < arr.length; i++) {
            temp = arr[i].split(" ");
            resultTable[i][0] = temp[0];
            resultTable[i][1] = temp[1];
        }
        return resultTable;
    }

    static void sortAndPrintListNames(Map<String, Integer> namesList) {
        String[][] namesArray = new String[namesList.size()][2];
        int i = 0;
        int countNamesNotRepeat = 0;
        for (var element : namesList.entrySet()) { // транформируем Map в массив
            Integer count = element.getValue();
            if (count > 1) {
                namesArray[i][0] = element.getKey();
                namesArray[i++][1] = count.toString();
            } else {
                countNamesNotRepeat++;
            }
        }
        String[][] resultArray = new String[namesArray.length - countNamesNotRepeat][2];
        for (int j = 0; j < resultArray.length; j++) {
            resultArray[j][0] = namesArray[j][0];
            resultArray[j][1] = namesArray[j][1];
        }

        sortTable(resultArray);

        for (String[] item : resultArray) {
            System.out.printf("%s - %s \n", item[0], item[1]);
        }

    }

    public static void main(String[] args) {
        String[] namesList = { "Иван Иванов", "Светлана Петрова", "Кристина Белова", "Анна Мусина",
                "Анна Крутова", "Иван Ежов", "Петр Петин",
                "Иван Юрин", "Петр Лыков", "Павел Чернов", "Петр Чернышов", "Мария Федорова", "Марина Светлова",
                "Мария Савина", "Мария Рыкова", "Марина Лугова", "Анна Владимирова", "Иван Мечников"
        };
        String[][] tableOfSurnamesAndNames = convertArrayToTable(namesList); // переводим в двумерный массив для
                                                                             // разделения имен и фамилий

        sortAndPrintListNames(getCountNames(tableOfSurnamesAndNames));
    }
}