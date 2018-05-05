/**
 * ������� ����� ������ �������� ���������� � ��������,
 * ������ ������������. � ��������� ����� ���������� �������
 * ������� �� ���� �����: ���� ��������� ����� � ���� �������
 * �����. ��������, ������� ������ ����� - ��� ���
 * ������ ������� � ������.
 * 

* ������� ������������� ��� ������������� ����� ������
 * ���������. ����� ����� ������ �������� �������
 * ����� (����������� ��������� �����), ����� ��������� ����� <null>.
 *
 * ���� ������� ���� ����� ���� ����� ������, �����
 * ������ ����� <null>
 * 
 * @author  (Kydyrbek uulu Bakai,Nurmanbetov Bekbolot)
 * @version (04.05.2018)
 */

class Befehl
{
    private String befehlswort;
    private String zweitesWort;

    /**
     * �������� ������ �������. ��� ����� ������ ���� �������,
     * �� ������ ��� ��� ����� ���� ������.
     * @param first word ������ ����� �������. ������
     * ���� 'null', ���� ��� ������� ��
     * ������ ���� ������� ���������� �����.
     * ������ ����� @param ������ ����� �������.
     */
    public Befehl(String erstesWort, String zweitesWort)
    {
        befehlswort = erstesWort;
        this.zweitesWort = zweitesWort;
    }

    /**
     * ��������� ��������� ����� (������ �����) ���� �������.
     * ���� ������� �� ���� ������, ��������� null.
     * @return ��������� �����.
     */
    public String gibBefehlswort()
    {
        return befehlswort;
    }

    /**
     * @return ������ ����� ���� �������. ��������� ������, ����
     * ������� ����� �� ����.
     */
    public String gibZweitesWort()
    {
        return zweitesWort;
    }

    /**
     * @return 'true', ���� ��� ������� �� ���� ������
     */
    public boolean istUnbekannt()
    {
        return (befehlswort == null);
    }

    /**
     * @return 'true', ���� ��� ������� ����� ������ �����
     */
    public boolean hatZweitesWort()
    {
        return (zweitesWort != null);
    }
}

