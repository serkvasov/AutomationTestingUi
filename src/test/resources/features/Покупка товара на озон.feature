# language:ru
@case3 @all
Функционал: Озон

  Предыстория:
    * открытие поисковика "Гугл"

  Сценарий: Покупка товара на озон

    * открывается страница "Гугл"
    * пользователь в поле "Строка поиска" вводит значение "ОЗОН" и нажимает Enter
    * открывается страница "Результаты поиска Гугл"
    * проверяет, что на странице содержится текст "OZON — интернет-магазин. Миллионы товаров по"
    * кликает по значению "https://www.ozon.ru/"
    * открывается страница "Озон"
    * пользователь в поле "Строка поиска" вводит значение "Корм сухой Royal Canin Exigent 35/30 Savoir Sensation, для привередливых кошек, 400 г" и нажимает Enter
    * пользователь нажимает элемент "В корзину"
    * пользователь нажимает элемент "Корзина"
    * открывается страница "Корзина Озон"
    * проверяет, что на странице содержится текст "Корм сухой Royal Canin \'Exigent 35/30 Savoir Sensation\', для привередливых кошек, 400 г"
    * пользователь нажимает кнопку "Удалить"
    * во всплывающем окне Удаление товаров нажимает кнопку Удалить
    * проверяет отсутствие текста "Корм сухой Royal Canin \'Exigent 35/30 Savoir Sensation\', для привередливых кошек, 400 г"
    * проверяет, что на странице содержится текст "Корзина пуста"

