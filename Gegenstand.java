
/**
 * Write a description of class Gegenstand here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gegenstand
{
    private String nazvanie;
    private String opisanie;
    private Double ves;

    /**
     * Constructor for objects of class Gegenstand
     */
    public Gegenstand(String nazvanie,String opisanie,Double ves)
    {
        this.nazvanie = nazvanie;
        this.opisanie = opisanie;
        this.ves = ves;
    }
    
    public void getPredmet()
    {
        System.out.println("��������: "+nazvanie+",  ��������: "+opisanie+",  ���: "+ves);
    }

    public String gibName(){
        return nazvanie;
    }
    
    public Double gibVes(){
        return ves;
    }
}
