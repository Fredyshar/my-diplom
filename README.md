# Приложение «Мобильный хоспис»
## Процедура запуска автотестов:

Pre-condition:
- Устаноленный android studio с эмулятором на API 29. 
- На эмуляторе отключена анимация.

Steps:
1. Клонировать проект - https://github.com/Fredyshar/my_diplom.git
2. Открыть его в Android Studio
3. Запустить настроенный эмулятор
4. В терминале выполнить команду:
'adb shell am instrument -w -m --no-window-animation -e package ru.iteco.fmhandroid.ui.tests -e debug false ru.iteco.fmhandroid.test/androidx.test.runner.AndroidJUnitRunner'
или на папке с тестами нажать правой кнопкой и выбрать запуск всех тестов.
