import java.util.*;
/**
 * �����
 * 
 * @author (Kydyrbek uulu Bakai,Nurmanbetov Bekbolot) 
 * @version (04.05.2018)
 */
public class Spieler
{
    private final Integer HEALTH = 3;
    private String gun;
    private Integer health;
    private ArrayList<String> orujie;
    private HashMap<String, Gegenstand> portfel;
    private String name;
    private Double vesPredmetov;
    /**
     * ����������� ��� �������� ������ ������
     */
    public Spieler()
    {   
        name = "���� ��������";
        gun = null;
        health = HEALTH;
        vesPredmetov = 0.0;
        orujie = new ArrayList<String>();
        portfel = new HashMap<String, Gegenstand>();
    }
    
   public Integer getHealth(){
    return health;
    } 
    
   public String getGun(){
    return gun;
   }  

   public boolean setPredmet(Gegenstand gegenstand){
        Double ves = gegenstand.gibVes();
        if(vesPredmetov < 1.0){
             vesPredmetov = vesPredmetov + ves / 2.0;
             portfel.put(gegenstand.gibName(), gegenstand);
             return true;
        }else{
            System.out.println("�� ������ �� ������ �������. ��������� �������� ��������");
        }
        return false;
   }  

   public Gegenstand getPredmet(String klyuch){
        return portfel.get(klyuch);
   }  
   
   public Double gibVesPredmetov(){
     return vesPredmetov;
    }
   
   public void getPredmety()
    {
        System.out.println("*******************************************************************");
        System.out.println("� ��� � ���������:  ");
        for(HashMap.Entry<String, Gegenstand> ak : portfel.entrySet())
        {
            Gegenstand value = ak.getValue();
            value.getPredmet();
        }
        System.out.println("����� ��� �����: "+vesPredmetov);
        System.out.println("��� �������� ����� ���������: 1 ��.");
        System.out.println("*******************************************************************");          
   }
   
   public void deletePredmet(String predmet){
        portfel.remove(predmet);
        Gegenstand gegen = portfel.get(predmet);
        Double ves = gegen.gibVes();
        vesPredmetov = vesPredmetov - ves;
   }
}
