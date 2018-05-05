/**
 * Ётот класс содержит перечисление всех командных слов, используемых в классе
 * »гра известна. — их помощью введенные команды узнаем.
 *
 * @author  (Kydyrbek uulu Bakai,Nurmanbetov Bekbolot)
 * @version (04.05.2018)
 */

class Befehlswoerter
{
    // посто€нный массив с допустимыми командами
    private static final String gueltigeBefehle[] = {
        "вперед", "местоположение", "карта", "вз€ть", "выбросить", "инвентарь", "помощь", "выход"
    };

    /**
     *  онструктор - инициализирует командные слова.
     */
    public Befehlswoerter()
    {
        // нечего делать пр€мо сейчас ...
    }

    /**
     * ѕроверьте правильность заданной строки
     *  оманда есть.
     * @return 'true', если данна€ строка €вл€етс€ действительной
     * иначе команда "false" 
     */
    public boolean istBefehl(String eingabe)
    {
        for(int i = 0; i < gueltigeBefehle.length; i++) {
            if(gueltigeBefehle[i].equals(eingabe))
                return true;
        }
        // ≈сли мы здесь, входа не было
        // найдено в командных словах.
        return false;
    }
}
