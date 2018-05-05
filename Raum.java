import java.util.*;
/**
 * ���� ����� ���������� ������� � ���� �����.
 *���� ������������ ����� ����� � ����������� ���������
 * ����. ���� ������� ��������� � ������� ��������� ��� ��������.
 * ��������� ������ ����������� �� ������, �������, ��� � ������.
 * ��� ������� ����������� � ������ ���� ������ �� ����
 * ������� �������. 
 * @author  (Kydyrbek uulu Bakai,Nurmanbetov Bekbolot)
 * @version (04.05.2018)
 */
class Raum 
{
    private String beschreibung;
    private HashMap<String, Raum> uni;
    private HashMap<String, Gegenstand> predmety;
    /**
     * �������� ������� � ���������. �������
     * ���������� �� ����� ������.
     * �������� @param �������� �������� � �����
     * ��� ����� ��� ��� ���������� ��������.
     */
    public Raum(String beschreibung) 
    {
        this.beschreibung = beschreibung;        
        uni = new HashMap<String, Raum>();
        predmety = new HashMap<String, Gegenstand>();
    }

    /**
     * ���������� ������ ���� �������. ������ �����������
     * ���� ����� � ������ �������, ���� �������� "�������"
     * (��� ������).
     * @param north �������� ����.
     * @param east ��������� ����.
     * @param �� ����� ����.
     * @param west �������� ����.
     */
    public void setzeAusgaenge(Raum norden, Raum osten,
                               Raum sueden, Raum westen) 
    {
        if(norden != null){
            uni.put("�����", norden);
        }
        if(osten != null){
            uni.put("������", osten);
        }
        if(sueden != null){
            uni.put("��", sueden);
        }
        if(westen != null){
            uni.put("�����", westen);
        }
    }
    
    public void setPredmety(String nazvanie, Gegenstand predmety)
    {
        this.predmety.put(nazvanie, predmety);
    }

    /**
     * @return �������� ����� ������.
     */
    public String gibBeschreibung()
    {
         return beschreibung;
    }
    
    /**
     * @return �������� ����� ������.
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
     * @return �������� ����� ������.
     */
    public Raum gibAktuellerRaum(String napravleniye)
    {
         return uni.get(napravleniye);
    }

}
