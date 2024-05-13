package ru.iteco.fmhandroid.ui.common;

public class TestData {

    private final String[] quotes = {
            "«Хоспис для меня - это то, каким должен быть мир.\"",
            "Хоспис в своем истинном понимании - это творчество",
            "“В хосписе не работают плохие люди” В.В. Миллионщикова\"",
            "«Хоспис – это философия, из которой следует сложнейшая наука медицинской помощи умирающим и искусство ухода, в котором сочетается компетентность и любовь» С. Сандерс",
            "Служение человеку с теплом, любовью и заботой",
            "\"Хоспис продлевает жизнь, дает надежду, утешение и поддержку.\"",
            "\"Двигатель хосписа - милосердие плюс профессионализм\"\nА.В. Гнездилов, д.м.н., один из пионеров хосписного движения.",
            "Важен каждый!"
    };

    private final String[] missionDescriptions = {
            "\"Ну, идеальное устройство мира в моих глазах. Где никто не оценивает, никто не осудит, где говоришь, и тебя слышат, где, если страшно, тебя обнимут и возьмут за руку, а если холодно тебя согреют.” Юля Капис, волонтер",
            "Нет шаблона и стандарта, есть только дух, который живет в разных домах по-разному. Но всегда он добрый, любящий и помогающий.",
            "Все сотрудники хосписа - это адвокаты пациента, его прав и потребностей. Поиск путей решения различных задач - это и есть хосписный индивидуальный подход к паллиативной помощи.",
            "“Творчески и осознанно подойти к проектированию опыта умирания. Создать пространство физическое и психологическое, чтобы позволить жизни отыграть себя до конца. И тогда человек не просто уходит с дороги. Тогда старение и умирание могут стать процессом восхождения до самого конца” \n" +
                    "Би Джей Миллер, врач, руководитель проекта \"Дзен-хоспис\"",
            "\"Если пациента нельзя вылечить, это не значит, что для него ничего нельзя сделать. То, что кажется мелочью, пустяком в жизни здорового человека - для пациента имеет огромный смысл.\"",
            "\" Хоспис - это мои новые друзья. Полная перезагрузка жизненных ценностей. В хосписе нет страха и одиночества.\"\n" +
                    "Евгения Белоусова, дочь пациентки Ольги Васильевны",
            "\"Делай добро... А добро заразительно. По-моему, все люди милосердны. Нужно просто говорить с ними об этом, суметь разбудить в них чувство сострадания, заложенное от рождения\" - В.В. Миллионщикова",
            "\"Каждый, кто оказывается в стенах хосписа, имеет огромное значение в жизни хосписа и его подопечных\""
    };
    private final String[] link = {
            "https://vhospice.org/#/privacy-policy/",
            "https://vhospice.org/#/terms-of-use"
    };
    private final String[] dateCreateNews = {
            "25.05.2024",
    };

    public String getValidLogin() {
        return "login2";
    }

    public String getValidPassword() {
        return "password2";
    }

    public String getNoValidLogin() {
        return "login";
    }

    public String getNoValidPassword() {
        return "password";
    }

    public String getNameNews() {
        return "Тестовое объявление...";
    }

    public int lengthQuotes() {
        return quotes.length;
    }

    public String getQuoteTitleByIndex(int index) {
        if (index >= 0 && index < quotes.length) {
            return quotes[index];
        } else {
            return null;
        }
    }

    public String getQuoteDescriptionByIndex(int index) {
        if (index >= 0 && index < missionDescriptions.length) {
            return missionDescriptions[index];
        } else {
            return null;
        }
    }

    public String getLinkContainingSubstring(String substring) {
        for (String link : link) {
            if (link.contains(substring)) {
                return link;
            }
        }
        return null;
    }

    public String getDateCreateNews(int index) {
        return dateCreateNews[index];
    }
    public String getDateFilterNews(String date) {
        String dateFromFilter = "20.05.2024";
        String dateToFilter = "28.05.2024";
        switch (date) {
            case "fromFilter":
                return dateFromFilter;
            case "toFilter":
                return dateToFilter;
            default:
                return "";
        }
    }
}
