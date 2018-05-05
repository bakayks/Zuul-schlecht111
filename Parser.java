import java.util.Scanner;

/**
 * ���� ���������� ��������� ���������������� ���� � ����������� ��� �
 * ������� ��� ��������������� ����. ��� ������ ������
 * �� ������ ������ � ������� � �������� ��� �������
 * ���������������� ������� �� ���� ����. 
 * �� ���������� ������� ��� ������ ������� ������.
 * 
 * ������ ����� ����� ��������� ������. ��
 * ���������� ���� � ����� ���������. ����� ����
 * �� �������� ��������� �������, ����� ������ ������������
 * ��� ����������� ������� ���������� ������ �����
 * 
 * @author  (Kydyrbek uulu Bakai,Nurmanbetov Bekbolot)
 * @version (04.05.2018)
 */
class Parser 
{
    private Befehlswoerter befehle;  // �������� �������������� ��������� �����
    private Scanner leser;         // ��������� ��� ��������� ������

    /**
     * ������� ������, ������� ������ ������� � �������.
     */
    public Parser() 
    {
        befehle = new Befehlswoerter();
        leser = new Scanner(System.in);
    }

    /**
     * @return ��������� ������� ������������.
     */
    public Befehl liefereBefehl() 
    {
        String eingabezeile;   // ��� ���� ������ �����
        String wort1 = null;
        String wort2 = null;

        System.out.print("> ");     // ���������

        eingabezeile = leser.nextLine();
        
        // ������� �� ���� ���� � ������
        Scanner zerleger = new Scanner(eingabezeile);
        if(zerleger.hasNext()) {
            wort1 = zerleger.next();     // ������ ������ �����
            if (zerleger.hasNext()) {
                wort2 = zerleger.next();// ������ ������ �����
                // ����������. �� ���������� ��������� ����� ������ �����.
            }
        }
        
        // ������ ���������, �������� �� �������. ���� ��, ������������
        // �� ���������� ��������������� ������ �������. ���� ���, �� ����������
        // ����������� ������� � 'null'.
        if(befehle.istBefehl(wort1)) {
            return new Befehl(wort1, wort2);
        }
        else {
            return new Befehl(null, wort2);
        }
    }

}
