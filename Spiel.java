/**
 * ��� �������� ����� ���������� ���� ������.
 * ���� ������ - ����� ������� ���������
 * ��������������� ����.
 * 
 *  ����� ������, ����� ������� ��������� ����� ������ �
 * �� ���� ���������� ����� �play�.
 * ���� ��������� ������� � �������������� ��� ��������� �������
 * ����������: ��� ������� ��� ������� � ������ �
 * ���������� ����. �� ����� ��������� �������, �������
 * Parser ������������ � ������������ �� ����������.
 * 
 * @author  (Kydyrbek uulu Bakai,Nurmanbetov Bekbolot)
 * @version (04.05.2018)
 */

class Spiel 
{
    private Parser parser;
    private Raum aktuellerRaum;
    private Spieler spieler;
        
    /**
     * �������� ���� � ��������������� ���������� ����� �������.
     */
    public Spiel() 
    {
        raeumeAnlegen();
        parser = new Parser();
        spieler = new Spieler();
    }

    /**
     * �������� ��� ������� � ��������� �� ������ ���� � ������
     */
    private void raeumeAnlegen()
    {
        Raum zabroshka,koridor, garderob, podval, kuhnya, kladovaya,lestnichnaya, doroga, sarai;
        // ������� �������
        zabroshka = new Raum("� ����� � ����������� ���");
        koridor = new Raum("� ������� ��������");
        garderob = new Raum("� ������� �����������");
        podval = new Raum("� ������ �������");
        kuhnya = new Raum("� ������ �����");
        kladovaya = new Raum("� ������ ��������");
        lestnichnaya = new Raum("� ������� �� ��������� ���������,������� �� ������ ����");
        doroga = new Raum("�� ������ � ������ �����");
        sarai = new Raum("� �������� �����");
        
        // ���������������� ������
        zabroshka.setzeAusgaenge(koridor, null, null, null);
        koridor.setzeAusgaenge(lestnichnaya, kuhnya, zabroshka, garderob);
        garderob.setzeAusgaenge(podval, koridor, null, null);
        podval.setzeAusgaenge(null, null, garderob, null);
        kuhnya.setzeAusgaenge(kladovaya, null, null, koridor);
        kladovaya.setzeAusgaenge(null, doroga, kuhnya, null);
        lestnichnaya.setzeAusgaenge(null, null, koridor, null);
        doroga.setzeAusgaenge(sarai, null, null, kladovaya);
        sarai.setzeAusgaenge(null, null, doroga, null);
        
        koridor.setPredmety("������", new Gegenstand("������", " ������, � ���������. ����� ������������ ��� ������ ", 0.5));
        koridor.setPredmety("���������", new Gegenstand("���������", " �������������,����� � ��������� . �������� ���� ", 0.02 ));
        garderob.setPredmety("�����", new Gegenstand("�����", " ������, � ���������. ����� ������������ ��� ������ ", 0.2));
        garderob.setPredmety("������", new Gegenstand("������", " ����� ������ �������� �������,����������� ������� ", 0.7));
        garderob.setPredmety("�����", new Gegenstand("�����", " ������, � ���������. ����� ������������ ��� ������ ", 0.03));
        garderob.setPredmety("�����", new Gegenstand("�����", " ���������� �� ����������� ����. ����� ������������ ��� ����� ", 0.3));
        garderob.setPredmety("�����", new Gegenstand("�����", " ������,������. ����� ������������ ��� �����", 0.01));
        aktuellerRaum = zabroshka;  // ���� ���������� � ����� � ����������� ���
    }

    /**
     * �������� ����� ����. ������ �� ����� ����
     */
    public void spielen() 
    {            
        willkommenstextAusgeben();

        // �������� ����. ����� �� ������������ ������ �������
        // � ��������� �� �� ��������� ����.
                
        boolean beendet = false;
        while (! beendet) {
            Befehl befehl = parser.liefereBefehl();
            beendet = verarbeiteBefehl(befehl);
        }
        System.out.println("��� ��������? �������,����� ���������� ��������!");
    }

    /**
     * �������������� ��������� ��� ������
     * ��������� �������� ����
     */
    private void willkommenstextAusgeben()
    {
        System.out.println();
        System.out.println("����� ���������� � ��� ����� ���������!");
        System.out.println("����  ����������, ���������� ���������� ����. ��� ���� �� ������� ����.                ");
        System.out.println("��� ����, ����� ������ �,�� ������ �������� ��� ���� �������� � ���� ������,���������.");
        System.out.println("������� �������� � �������� ������� ����.");
        System.out.println();
    }

    private void RaumInfoAusgeben(){
                System.out.println("*******************************************");
        System.out.println("||�� ������ " + aktuellerRaum.gibBeschreibung());
        System.out.print("||��������� �����: ");
        if(aktuellerRaum.gibAktuellerRaum("�����") != null)
            System.out.print("'�����' ");
        if(aktuellerRaum.gibAktuellerRaum("������") != null)
            System.out.print("'������' ");
        if(aktuellerRaum.gibAktuellerRaum("��") != null)
            System.out.print("'��' ");
        if(aktuellerRaum.gibAktuellerRaum("�����") != null)
            System.out.print("'�����' ");
        System.out.println();
        System.out.println("*******************************************");
                System.out.println("������ ���������:");
        aktuellerRaum.getPredmety();
        System.out.println("");
    }
    
    /**
     * ���������� ������ ������� (��������� ��).
     * ������� @param ��� ���������
     * @return 'true', ���� ������� ��������� ����,����� 'false'
     */
    private boolean verarbeiteBefehl(Befehl befehl) 
    {
        boolean moechteBeenden = false;

        if(befehl.istUnbekannt()) {
            System.out.println("�� ���� ����...");
            return false;
        }

        String befehlswort = befehl.gibBefehlswort();
        if (befehlswort.equals("������"))
            hilfstextAusgeben();
            
        else if (befehlswort.equals("������"))
            wechsleRaum(befehl);
            
        else if (befehlswort.equals("�����")) 
            moechteBeenden = beenden(befehl);
            
        else if (befehlswort.equals("��������������")) 
            RaumInfoAusgeben();
            
        else if (befehlswort.equals("�����"))
            nehmPredmet(befehl);
        
        else if (befehlswort.equals("���������"))
            spieler.getPredmety();
            
        else if (befehlswort.equals("���������"))
            auswerfenPredmet(befehl);
            
        return moechteBeenden;
    }

    // ���������� ���������������� ������:
    /**
     * ����� ���������� ����������.
     * ����� �� �������� �������� � ������� ����
     * � ������ ��������� ����.
     */
    private void hilfstextAusgeben() 
    {
        System.out.println("||*******************************************************************************************************||");
        System.out.println("||                                            ������� ����:                                              ||");
        System.out.println("||1)��� ����� ���� ��������.��-��������.                                                                 ||");
        System.out.println("||2)��� ��������� �������� ����. � ������,������� ���,�������� ��������� �������,����� �������� ����     ||");
        System.out.println("||2)� ����� �������,�� ������ ������������ �������� ��������,                                            ||");
        System.out.println("||������������ � ���� ���� � ��������� ��� �����!                                                        ||");
        System.out.println("||3)��� ��������� �������� ��� ������� ����,��� ���� ��������� ��������                                  ||");
        System.out.println("||4)� ��� ���� ������������ ����� �� ������, ����� � ��� ������� ������� �������� ��� �������� ���������.||");
        System.out.println("||5)�������,���� ��� ����� ������� � ����.� ����������� ���������. ����������� ������ ��������.          ||");
        System.out.println("||6) ������� ����!                                                                                       ||");
        System.out.println("||*******************************************************************************************************||");
        System.out.println("||                                      �������� ��������� �������:                                      ||");
        System.out.println("||       �������, ���������������, ������, ��������, �������, ����������, ��������, ������.         ||");
        System.out.println("||*����� ������������ �����: ������� (�����������).                                                     ||");
        System.out.println("||*����� ������ ���� �������������� �����: ���������������.                                             ||");
        System.out.println("||*����� �������� ����� ������������ ���� �����: ������.                                                ||");
        System.out.println("||*����� ������� � ������-�� ������� ��� �������� �����: �������� (������� ��� ������).                 ||");
        System.out.println("||*����� ����� ������� �����: ������� (�������).                                                         ||");
        System.out.println("||*����� �������� ������� � ������: ���������� (�������).                                                ||");
        System.out.println("||*����� �������� ���������� �� ���� �����: ��������.                                                    ||");
        System.out.println("||*����� ����� �� ���� �����: ������.                                                                   ||");
        System.out.println("||*******************************************************************************************************||");        
    }

    /**
     * ���������� �������� �������. ���� ���� �����,
     * ��������� � ����� �������, ����� �������� �� ������
     */
    private void wechsleRaum(Befehl befehl) 
    {
        if(!befehl.hatZweitesWort()) {
            // ���� ��� ������� �����, �� �� �����, ��� ..
            System.out.println("���� �� ����� �����?");
            return;
        }

        String richtung = befehl.gibZweitesWort();

        // �� �������� �������� �������.
        Raum naechsterRaum = null;
        if(richtung.equals("�����")) {
            naechsterRaum = aktuellerRaum.gibAktuellerRaum(richtung);
        }
        if(richtung.equals("������")) {
            naechsterRaum = aktuellerRaum.gibAktuellerRaum(richtung);
        }
        if(richtung.equals("��")) {
            naechsterRaum = aktuellerRaum.gibAktuellerRaum(richtung);
        }
        if(richtung.equals("�����")) {
            naechsterRaum = aktuellerRaum.gibAktuellerRaum(richtung);
        }

        if (naechsterRaum == null) {
            System.out.println("����� ��� �����!");
        }
        else {
            aktuellerRaum = naechsterRaum;
            RaumInfoAusgeben();
        }
    }

    private void nehmPredmet(Befehl befehl)
    {
        if(!befehl.hatZweitesWort()) {
            // ���� ��� ������� �����, �� �� �����, ��� ..
            System.out.println("������ �������");
            return;
        }
        
        String richtung = befehl.gibZweitesWort();
        
        if(aktuellerRaum.gibPredmet(richtung) != null)
        {
            System.out.println("********************************************");
            Gegenstand predmet = aktuellerRaum.gibPredmet(richtung);
            if(spieler.setPredmet(predmet) == true){
                System.out.println("�� �����: "+richtung);
                aktuellerRaum.deletePredmet(richtung); 
                spieler.setPredmet(predmet);                
            }
            System.out.println("********************************************");            
        }
    }
    
    private void auswerfenPredmet(Befehl befehl)
    {
        if(!befehl.hatZweitesWort()) {
            // ���� ��� ������� �����, �� �� �����, ��� ..
            System.out.println("������ �������");
            return;
        }
        
        String richtung = befehl.gibZweitesWort();
        
        if(spieler.getPredmet(richtung) != null)
        {
            System.out.println("********************************************");
            System.out.println("�� ��������� �� ��������: "+richtung);
            Gegenstand predmet = spieler.getPredmet(richtung);
            spieler.deletePredmet(richtung);
            aktuellerRaum.setPredmety(predmet.gibName(), predmet);
            System.out.println("********************************************");            
        }
    }
    
    /**
     * "�����" ��� ������.��������� ��������� ����� �������,
     * ������������� �� ���� ������ ���� ���������.
     * @return 'true', ���� ������� ��������� ����, ����� 'false'.
     */
    private boolean beenden(Befehl befehl) 
    {
        if(befehl.hatZweitesWort()) {
            System.out.println("��� ����� ����������?");
            return false;
        }
        else {
            return true;  // ���� ������ ���� ���������.
        }
    }
}
