# language:ru
@poisk1
Функционал: 444

  Предыстория:
    * открытие поисковика "Яндекс"

  Сценарий: A scenario 2

    * открывается страница "Яндекс"
    * пользователь в поле "Строка поиска" вводит значение "Маркет"
    * пользователь нажимает кнопку "Найти"
#    * пользователь ждет "3" секунд

    * открывается страница "Результаты поиска Яндекс"
    * пользователь нажимает кнопку "Яндекс Маркет"

    * открывается страница "Яндекс.Маркет"
    * пользователь в поле "Строка поиска" вводит значение "Ноутбук Apple AIR"
    * пользователь нажимает кнопку "Найти"

    * пользователь ждет "3" секунд
#    * пользователь переходит по строке "1"

#    * пользователь в поле нажимает ""
#    * пользователь ждет "10" секунд