import java.util.Scanner;

/**
 * Этот анализатор считывает пользовательский ввод и преобразует его в
 * Команды для приключенческой игры. При каждом звонке
 * он читает строку с консоли и пытается это сделать
 * интерпретировать команду из двух слов. 
 * он возвращает команду как объект команды класса.
 * 
 * Парсер имеет набор известных команд. он
 * сравнивает вход с этими командами. Когда вход
 * не содержит известной команды, тогда парсер возвращается
 * как неизвестная команда отмеченный объект назад
 * 
 * @author  (Kydyrbek uulu Bakai,Nurmanbetov Bekbolot)
 * @version (04.05.2018)
 */
class Parser 
{
    private Befehlswoerter befehle;  // содержит действительные командные слова
    private Scanner leser;         // Поставщик для введенных команд

    /**
     * Создает парсер, который читает команды с консоли.
     */
    public Parser() 
    {
        befehle = new Befehlswoerter();
        leser = new Scanner(System.in);
    }

    /**
     * @return Следующая команда пользователя.
     */
    public Befehl liefereBefehl() 
    {
        String eingabezeile;   // для всей строки ввода
        String wort1 = null;
        String wort2 = null;

        System.out.print("> ");     // подсказка

        eingabezeile = leser.nextLine();
        
        // Найдите до двух слов в строке
        Scanner zerleger = new Scanner(eingabezeile);
        if(zerleger.hasNext()) {
            wort1 = zerleger.next();     // читаем первое слово
            if (zerleger.hasNext()) {
                wort2 = zerleger.next();// читаем второе слово
                // Примечание. Мы игнорируем остальную часть строки ввода.
            }
        }
        
        // Теперь проверьте, известна ли команда. Если да, сгенерируйте
        // мы генерируем соответствующий объект команды. Если нет, мы генерируем
        // неизвестную команду с 'null'.
        if(befehle.istBefehl(wort1)) {
            return new Befehl(wort1, wort2);
        }
        else {
            return new Befehl(null, wort2);
        }
    }

}
