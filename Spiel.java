/**
 * Это основной класс приложения «Мир Зууля».
 * «Мир Зууля» - очень простой текстовая
 * Приключенческая игра.
 * 
 *  Чтобы играть, нужно создать экземпляр этого класса и
 * на него вызывается метод «play».
 * Этот экземпляр создает и инициализирует все остальные объекты
 * приложение: оно создает все комнаты и парсер и
 * начинается игра. Он также оценивает команды, которые
 * Parser обеспечивает и обеспечивает их выполнение.
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
     * Создайте игру и инициализируйте внутреннюю карту комнаты.
     */
    public Spiel() 
    {
        raeumeAnlegen();
        parser = new Parser();
        spieler = new Spieler();
    }

    /**
     * Создайте все комнаты и соедините их выходы друг с другом
     */
    private void raeumeAnlegen()
    {
        Raum zabroshka,koridor, garderob, podval, kuhnya, kladovaya,lestnichnaya, doroga, sarai;
        // создать комнаты
        zabroshka = new Raum("у входа в заброшенный дом");
        koridor = new Raum("в большом коридоре");
        garderob = new Raum("в пыльной гардеробной");
        podval = new Raum("в темном подвале");
        kuhnya = new Raum("в адской кухне");
        kladovaya = new Raum("в старой кладовой");
        lestnichnaya = new Raum("в комнате со скрипящей лестницей,ведущей на второй этаж");
        doroga = new Raum("по дороге в старый сарай");
        sarai = new Raum("в холодном сарае");
        
        // инициализировать выходы
        zabroshka.setzeAusgaenge(koridor, null, null, null);
        koridor.setzeAusgaenge(lestnichnaya, kuhnya, zabroshka, garderob);
        garderob.setzeAusgaenge(podval, koridor, null, null);
        podval.setzeAusgaenge(null, null, garderob, null);
        kuhnya.setzeAusgaenge(kladovaya, null, null, koridor);
        kladovaya.setzeAusgaenge(null, doroga, kuhnya, null);
        lestnichnaya.setzeAusgaenge(null, null, koridor, null);
        doroga.setzeAusgaenge(sarai, null, null, kladovaya);
        sarai.setzeAusgaenge(null, null, doroga, null);
        
        koridor.setPredmety("зонтик", new Gegenstand("зонтик", " Черный, с рукояткой. Можно использовать как оружие ", 0.5));
        koridor.setPredmety("зажигалка", new Gegenstand("зажигалка", " Прямоугольный,синий и маленький . Полезная вещь ", 0.02 ));
        garderob.setPredmety("шляпа", new Gegenstand("шляпа", " Черный, с рукояткой. Можно использовать как оружие ", 0.2));
        garderob.setPredmety("пальто", new Gegenstand("пальто", " Серое пальто большого размера,определенно мужское ", 0.7));
        garderob.setPredmety("щетка", new Gegenstand("щетка", " Черный, с рукояткой. Можно использовать как оружие ", 0.03));
        garderob.setPredmety("туфли", new Gegenstand("туфли", " Коричневые из натуральной кожи. Можно использовать как улику ", 0.3));
        garderob.setPredmety("носок", new Gegenstand("носок", " Черный,динный. Можно использовать как улику", 0.01));
        aktuellerRaum = zabroshka;  // игра начинается у входа в заброшенный дом
    }

    /**
     * Основной метод игры. Запуск до конца игры
     */
    public void spielen() 
    {            
        willkommenstextAusgeben();

        // Основной цикл. Здесь мы неоднократно читаем команды
        // и выполняем их до окончания игры.
                
        boolean beendet = false;
        while (! beendet) {
            Befehl befehl = parser.liefereBefehl();
            beendet = verarbeiteBefehl(befehl);
        }
        System.out.println("Уже сдаешься? Приходи,когда наберешься смелости!");
    }

    /**
     * Приветственное сообщение для игрока
     * Небольшое описание игры
     */
    private void willkommenstextAusgeben()
    {
        System.out.println();
        System.out.println("Добро пожаловать в мир Джона Бенедикта!");
        System.out.println("Джон  загадочная, невероятно интересная игра. Это игра от первого лица.                ");
        System.out.println("Для того, чтобы пройти её,ты должен включить всю свою смекалку и быть смелым,отчаянным.");
        System.out.println("Введите «помощь» и прочтите правила игры.");
        System.out.println();
    }

    private void RaumInfoAusgeben(){
                System.out.println("*******************************************");
        System.out.println("||Ты сейчас " + aktuellerRaum.gibBeschreibung());
        System.out.print("||Доступные двери: ");
        if(aktuellerRaum.gibAktuellerRaum("север") != null)
            System.out.print("'север' ");
        if(aktuellerRaum.gibAktuellerRaum("восток") != null)
            System.out.print("'восток' ");
        if(aktuellerRaum.gibAktuellerRaum("юг") != null)
            System.out.print("'юг' ");
        if(aktuellerRaum.gibAktuellerRaum("запад") != null)
            System.out.print("'запад' ");
        System.out.println();
        System.out.println("*******************************************");
                System.out.println("Список предметов:");
        aktuellerRaum.getPredmety();
        System.out.println("");
    }
    
    /**
     * Обработать данную команду (выполнить ее).
     * Команда @param для обработки
     * @return 'true', если команда завершает игру,иначе 'false'
     */
    private boolean verarbeiteBefehl(Befehl befehl) 
    {
        boolean moechteBeenden = false;

        if(befehl.istUnbekannt()) {
            System.out.println("Не неси чушь...");
            return false;
        }

        String befehlswort = befehl.gibBefehlswort();
        if (befehlswort.equals("помощь"))
            hilfstextAusgeben();
            
        else if (befehlswort.equals("вперед"))
            wechsleRaum(befehl);
            
        else if (befehlswort.equals("выход")) 
            moechteBeenden = beenden(befehl);
            
        else if (befehlswort.equals("местоположение")) 
            RaumInfoAusgeben();
            
        else if (befehlswort.equals("взять"))
            nehmPredmet(befehl);
        
        else if (befehlswort.equals("инвентарь"))
            spieler.getPredmety();
            
        else if (befehlswort.equals("выбросить"))
            auswerfenPredmet(befehl);
            
        return moechteBeenden;
    }

    // Выполнение пользовательских команд:
    /**
     * Дайте справочную информацию.
     * Здесь мы приводим описание и правила игры
     * и список командных слов.
     */
    private void hilfstextAusgeben() 
    {
        System.out.println("||*******************************************************************************************************||");
        System.out.println("||                                            Правила игры:                                              ||");
        System.out.println("||1)Вас зовут Джон Бенедикт.Вы-детектив.                                                                 ||");
        System.out.println("||2)Вам предстоит раскрыть дело. А именно,изучить дом,отыскать секретные комнаты,найти ключевые вещи     ||");
        System.out.println("||2)И самое главное,вы должны расследовать убийство человека,                                            ||");
        System.out.println("||совершенного в этом доме и разгадать эту тайну!                                                        ||");
        System.out.println("||3)Вам предстоит посетить все комнаты дома,где было совершено убийство                                  ||");
        System.out.println("||4)У вас есть ограниченные права на ошибку, также у вас имеется большой портфель для хранения предметов.||");
        System.out.println("||5)Помните,этот дом полон загадок и тайн.И неожиданных сюрпризов. Обдумывайте каждое действие.          ||");
        System.out.println("||6) Удачной игры!                                                                                       ||");
        System.out.println("||*******************************************************************************************************||");
        System.out.println("||                                      Доступны следующие команды:                                      ||");
        System.out.println("||       «вперед», «местоположение», «карта», «подойти», «взять», «положить», «помощь», «выход».         ||");
        System.out.println("||*Чтобы перемещаться введи: «вперед» (направление).                                                     ||");
        System.out.println("||*Чтобы узнать свое местоположение введи: «местоположение».                                             ||");
        System.out.println("||*Чтобы получить карту заброшенного дома введи: «карта».                                                ||");
        System.out.println("||*Чтобы подойти к какому-то объекту или предмету введи: «подойти» (предмет или объект).                 ||");
        System.out.println("||*Чтобы взять предмет введи: «взять» (предмет).                                                         ||");
        System.out.println("||*Чтобы положить предмет в рюкзак: «положить» (предмет).                                                ||");
        System.out.println("||*Чтобы получить информацию об игре введи: «помощь».                                                    ||");
        System.out.println("||*Чтобы выйти из игры введи: «выход».                                                                   ||");
        System.out.println("||*******************************************************************************************************||");        
    }

    /**
     * Попробуйте изменить комнату. Если есть выход,
     * перейдите в новую комнату, иначе сообщите об ошибке
     */
    private void wechsleRaum(Befehl befehl) 
    {
        if(!befehl.hatZweitesWort()) {
            // Если нет второго слова, мы не знаем, где ..
            System.out.println("Куда ты готов пойти?");
            return;
        }

        String richtung = befehl.gibZweitesWort();

        // Мы пытаемся покинуть комнату.
        Raum naechsterRaum = null;
        if(richtung.equals("север")) {
            naechsterRaum = aktuellerRaum.gibAktuellerRaum(richtung);
        }
        if(richtung.equals("восток")) {
            naechsterRaum = aktuellerRaum.gibAktuellerRaum(richtung);
        }
        if(richtung.equals("юг")) {
            naechsterRaum = aktuellerRaum.gibAktuellerRaum(richtung);
        }
        if(richtung.equals("запад")) {
            naechsterRaum = aktuellerRaum.gibAktuellerRaum(richtung);
        }

        if (naechsterRaum == null) {
            System.out.println("Здесь нет двери!");
        }
        else {
            aktuellerRaum = naechsterRaum;
            RaumInfoAusgeben();
        }
    }

    private void nehmPredmet(Befehl befehl)
    {
        if(!befehl.hatZweitesWort()) {
            // Если нет второго слова, мы не знаем, где ..
            System.out.println("Выбери предмет");
            return;
        }
        
        String richtung = befehl.gibZweitesWort();
        
        if(aktuellerRaum.gibPredmet(richtung) != null)
        {
            System.out.println("********************************************");
            Gegenstand predmet = aktuellerRaum.gibPredmet(richtung);
            if(spieler.setPredmet(predmet) == true){
                System.out.println("Вы взяли: "+richtung);
                aktuellerRaum.deletePredmet(richtung); 
                spieler.setPredmet(predmet);                
            }
            System.out.println("********************************************");            
        }
    }
    
    private void auswerfenPredmet(Befehl befehl)
    {
        if(!befehl.hatZweitesWort()) {
            // Если нет второго слова, мы не знаем, где ..
            System.out.println("Выбери предмет");
            return;
        }
        
        String richtung = befehl.gibZweitesWort();
        
        if(spieler.getPredmet(richtung) != null)
        {
            System.out.println("********************************************");
            System.out.println("Вы выбросили из портфеля: "+richtung);
            Gegenstand predmet = spieler.getPredmet(richtung);
            spieler.deletePredmet(richtung);
            aktuellerRaum.setPredmety(predmet.gibName(), predmet);
            System.out.println("********************************************");            
        }
    }
    
    /**
     * "выход" был введен.Проверьте остальную часть команды,
     * действительно ли игра должна быть выполнена.
     * @return 'true', если команда завершает игру, иначе 'false'.
     */
    private boolean beenden(Befehl befehl) 
    {
        if(befehl.hatZweitesWort()) {
            System.out.println("Что нужно остановить?");
            return false;
        }
        else {
            return true;  // Игра должна быть закончена.
        }
    }
}
