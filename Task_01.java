// Реализуйте структуру телефонной книги с помощью HashMap, учитывая,
// что 1 человек может иметь несколько телефонов.
// Добавить функции 1) Добавление номера
// 2) Вывод всего
// Пример:
// Я ввожу: 1
// К: Введите фамилию
// Я: Иванов
// К: Введите номер
// Я: 1242353
// К: Введите 1) Добавление номера
// 2) Вывод всего
// Я ввожу: 1
// К: Введите фамилию
// Я: Иванов
// К: Введите номер
// Я: 547568
// К: Введите 1) Добавление номера
// 2) Вывод всего
// Я: 2
// Иванов: 1242353, 547568

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Task_01 {
    static String getKeyByValue(Map<String, String> phoneList, String value) {
        for (var item : phoneList.entrySet()) {
            if (value.equals(item.getValue())) {
                return item.getKey();
            }
        }
        return "";
    }

    static void printPhoneList(Map<String, String> phoneList) {
        System.out.println("Телефонный справочник: ");
        Map<String, String> phoneListResult = new HashMap<>();

        for (var element : phoneList.entrySet()) {
            StringBuilder stb = new StringBuilder();
            String phoneTemp = element.getKey();
            String surnameTemp = element.getValue();

            if (phoneListResult.containsValue(surnameTemp)) {
                String keyOld = getKeyByValue(phoneListResult, surnameTemp);
                System.out.println(keyOld);
                if (!keyOld.equals("")) {// && phoneListResult.get(keyOld).equals(surnameTemp)) {
                    String temp = stb.append(keyOld).append("; ").append(phoneTemp).toString();
                    System.out.println(temp);
                    phoneListResult.put(temp, surnameTemp);
                    phoneListResult.remove(keyOld);
                }
            } else {
                phoneListResult.put(phoneTemp, surnameTemp);
                System.out.printf("Вы добавили в текущий список новую запись:\n фамилия - %s, номер телефона: %s \n", surnameTemp, phoneTemp);
            }
            System.out.println(phoneListResult.toString());
        }

        for (var element : phoneListResult.entrySet()) {
            System.out.printf("Фамилия : %-10s телефоны: %s\n", element.getValue(), element.getKey());
        }
    }

    public static void main(String[] args) {
        System.out.println("Для добавления номера - нажмите 1 \nдля вывода всего списка - нажмите 2 ");
        Scanner scn = new Scanner(System.in);
        try {
            Map<String, String> phoneList = new HashMap<>();
            phoneList.put("9169227690", "Сидоров");
            phoneList.put("9114550274", "Камина");
            while (scn.hasNext()) {
                String numberOperation = scn.nextLine();
                if (!numberOperation.equals("1") && !numberOperation.equals("2")) {
                    System.out.println("Вы ввели некорректный номер операции!!!");
                    break;
                } else if (numberOperation.equals("1")) {
                    System.out.println("Введите фамилию: ");
                    String surname = scn.nextLine();
                    System.out.println("Введите номер телефона: ");
                    String phoneNumber = scn.nextLine();
                    phoneList.put(phoneNumber, surname);
                    System.out.printf("Вы добавили в справочник новую запись:\n фамилия - %s, номер телефона: %s \n", surname, phoneNumber);
                    System.out.println(phoneList.toString());
                    System.out.println("Для добавления номера - нажмите 1 \nдля вывода всего списка - нажмите 2 ");
                } else {
                    printPhoneList(phoneList);
                    System.out.println("Для добавления номера - нажмите 1 \nдля вывода всего списка - нажмите 2 ");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        scn.close();
    }
}