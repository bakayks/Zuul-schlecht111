/**
 * Объекты этого класса содержат информацию о командах,
 * введен пользователь. В настоящее время существует команда
 * состоит из двух строк: одно командное слово и одна секунда
 * Слово. Например, команда «взять карту» - это две
 * Строки «взять» и «карта».
 * 

* Команды действительны для пользователей этого класса
 * Проверено. Когда игрок вводит неверную команду
 * имеет (неизвестное командное слово), тогда командное слово <null>.
 *
 * Если команда была всего лишь одним словом, тогда
 * второе слово <null>
 * 
 * @author  (Kydyrbek uulu Bakai,Nurmanbetov Bekbolot)
 * @version (04.05.2018)
 */

class Befehl
{
    private String befehlswort;
    private String zweitesWort;

    /**
     * Создайте объект команды. Оба слова должны быть указаны,
     * но каждый или оба могут быть «ноль».
     * @param first word Первое слово команды. должен
     * быть 'null', если эта команда не
     * должен быть отмечен признанной игрой.
     * Второе слово @param Второе слово команды.
     */
    public Befehl(String erstesWort, String zweitesWort)
    {
        befehlswort = erstesWort;
        this.zweitesWort = zweitesWort;
    }

    /**
     * Поставьте командное слово (первое слово) этой команды.
     * Если команда не была понята, результат null.
     * @return Командное слово.
     */
    public String gibBefehlswort()
    {
        return befehlswort;
    }

    /**
     * @return Второе слово этой команды. Поставьте «ноль», если
     * Второго слова не было.
     */
    public String gibZweitesWort()
    {
        return zweitesWort;
    }

    /**
     * @return 'true', если эта команда не была понята
     */
    public boolean istUnbekannt()
    {
        return (befehlswort == null);
    }

    /**
     * @return 'true', если эта команда имеет второе слово
     */
    public boolean hatZweitesWort()
    {
        return (zweitesWort != null);
    }
}

