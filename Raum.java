import java.util.*;
/**
 * Этот класс моделирует комнаты в мире Зуула.
 *«Дом» представляет собой место в виртуальном ландшафте
 * Игра. Одна комната соединена с другими комнатами над выходами.
 * Возможные выходы расположены на севере, востоке, юге и западе.
 * Для каждого направления в номере есть ссылка на него
 * смежная комната. 
 * @author  (Kydyrbek uulu Bakai,Nurmanbetov Bekbolot)
 * @version (04.05.2018)
 */
class Raum 
{
    private String beschreibung;
    private HashMap<String, Raum> uni;
    private HashMap<String, Gegenstand> predmety;
    /**
     * Создайте комнату с описанием. Комната
     * изначально не имеет выхода.
     * Описание @param содержит описание в форме
     * «на кухне» или «на спортивной площадке».
     */
    public Raum(String beschreibung) 
    {
        this.beschreibung = beschreibung;        
        uni = new HashMap<String, Raum>();
        predmety = new HashMap<String, Gegenstand>();
    }

    /**
     * Определите выходы этой комнаты. Каждое направление
     * либо ведет в другую комнату, либо является "нулевым"
     * (без выхода).
     * @param north Северный вход.
     * @param east Восточный вход.
     * @param юг южный вход.
     * @param west Западный вход.
     */
    public void setzeAusgaenge(Raum norden, Raum osten,
                               Raum sueden, Raum westen) 
    {
        if(norden != null){
            uni.put("север", norden);
        }
        if(osten != null){
            uni.put("восток", osten);
        }
        if(sueden != null){
            uni.put("юг", sueden);
        }
        if(westen != null){
            uni.put("запад", westen);
        }
    }
    
    public void setPredmety(String nazvanie, Gegenstand predmety)
    {
        this.predmety.put(nazvanie, predmety);
    }

    /**
     * @return Описание этого номера.
     */
    public String gibBeschreibung()
    {
         return beschreibung;
    }
    
    /**
     * @return Описание этого номера.
     */
    public void getPredmety()
    {
        for(HashMap.Entry<String, Gegenstand> ak : predmety.entrySet())
        {
            Gegenstand value = ak.getValue();
            value.getPredmet();
        }
    }
    
    public Gegenstand gibPredmet(String klyuch)
    {
        return predmety.get(klyuch);
    }
    
    public void deletePredmet(String klyuch)
    {
        predmety.remove(klyuch);
    }
    
    /**
     * @return Описание этого номера.
     */
    public Raum gibAktuellerRaum(String napravleniye)
    {
         return uni.get(napravleniye);
    }

}
