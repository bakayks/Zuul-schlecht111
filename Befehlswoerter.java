/**
 * ���� ����� �������� ������������ ���� ��������� ����, ������������ � ������
 * ���� ��������. � �� ������� ��������� ������� ������.
 *
 * @author  (Kydyrbek uulu Bakai,Nurmanbetov Bekbolot)
 * @version (04.05.2018)
 */

class Befehlswoerter
{
    // ���������� ������ � ����������� ���������
    private static final String gueltigeBefehle[] = {
        "������", "��������������", "�����", "�����", "���������", "���������", "������", "�����"
    };

    /**
     * ����������� - �������������� ��������� �����.
     */
    public Befehlswoerter()
    {
        // ������ ������ ����� ������ ...
    }

    /**
     * ��������� ������������ �������� ������
     * ������� ����.
     * @return 'true', ���� ������ ������ �������� ��������������
     * ����� ������� "false" 
     */
    public boolean istBefehl(String eingabe)
    {
        for(int i = 0; i < gueltigeBefehle.length; i++) {
            if(gueltigeBefehle[i].equals(eingabe))
                return true;
        }
        // ���� �� �����, ����� �� ����
        // ������� � ��������� ������.
        return false;
    }
}
