-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Wersja serwera:               8.0.18 - MySQL Community Server - GPL
-- Serwer OS:                    Win64
-- HeidiSQL Wersja:              10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Zrzut struktury bazy danych druk
CREATE DATABASE IF NOT EXISTS `druk` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `druk`;

-- Zrzut struktury tabela druk.billing_info
CREATE TABLE IF NOT EXISTS `billing_info` (
  `billing_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `card_number` varchar(16) NOT NULL,
  `expiration_date` date NOT NULL,
  `security_code` int(11) NOT NULL,
  `billing_address` varchar(45) NOT NULL,
  PRIMARY KEY (`billing_id`),
  UNIQUE KEY `billing_ID_UNIQUE` (`billing_id`),
  UNIQUE KEY `card_number_UNIQUE` (`card_number`),
  KEY `fk_billing_info_users_idx` (`user_id`),
  CONSTRAINT `fk_billing_info_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Zrzucanie danych dla tabeli druk.billing_info: ~2 rows (około)
/*!40000 ALTER TABLE `billing_info` DISABLE KEYS */;
REPLACE INTO `billing_info` (`billing_id`, `user_id`, `card_number`, `expiration_date`, `security_code`, `billing_address`) VALUES
	(1, 1, '75483928593', '2020-10-21', 324, 'Gdzieś w lubuskim'),
	(2, 2, '3212142343653473', '2020-06-10', 322, 'Zasiedmiogrodowe Lesie');
/*!40000 ALTER TABLE `billing_info` ENABLE KEYS */;

-- Zrzut struktury tabela druk.cart
CREATE TABLE IF NOT EXISTS `cart` (
  `user_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  KEY `cart_item_id_idx` (`item_id`),
  KEY `cart_user_id` (`user_id`),
  CONSTRAINT `cart_item_id` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`),
  CONSTRAINT `cart_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Zrzucanie danych dla tabeli druk.cart: ~0 rows (około)
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;

-- Zrzut struktury tabela druk.history
CREATE TABLE IF NOT EXISTS `history` (
  `history_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  PRIMARY KEY (`history_id`),
  UNIQUE KEY `history_id_UNIQUE` (`history_id`),
  KEY `history_user_id_idx` (`user_id`),
  KEY `history_order_id_idx` (`order_id`),
  CONSTRAINT `history_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `history_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Zrzucanie danych dla tabeli druk.history: ~0 rows (około)
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
/*!40000 ALTER TABLE `history` ENABLE KEYS */;

-- Zrzut struktury tabela druk.item
CREATE TABLE IF NOT EXISTS `item` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(45) NOT NULL,
  `quantity` int(11) NOT NULL,
  `type` varchar(45) NOT NULL,
  `price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `description` varchar(2000) NOT NULL,
  `image_url` varchar(150) NOT NULL,
  `seller_id` int(11) NOT NULL,
  PRIMARY KEY (`item_id`),
  UNIQUE KEY `item_id_UNIQUE` (`item_id`),
  KEY `fk_item_users1_idx` (`seller_id`),
  CONSTRAINT `fk_item_users1` FOREIGN KEY (`seller_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Zrzucanie danych dla tabeli druk.item: ~24 rows (około)
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
REPLACE INTO `item` (`item_id`, `item_name`, `quantity`, `type`, `price`, `description`, `image_url`, `seller_id`) VALUES
	(1, 'Brother QL-1110NWB', 1, 'Drukarki etykiet', 1289.00, 'Drukarka etykiet QL-1110NWB stanowi idealne rozwiązanie do pokojów pocztowych, magazynu lub w inne miejsca, które wymagają wydruku wysokiej jakości etykiet wysyłkowych zawierających kody kreskowe, obrazy oraz teksty. Drukarka etykiet wysyłkowych o szerokości wydruku 101.6mm. Oferuje maksymalną kompatybilność z różnymi pakietami oprogramowania do etykietowania wysyłek.', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/2/pr_2019_2_22_12_0_42_797_00.jpg', 1),
	(2, 'Brother PT-E550WVP', 3, 'Drukarki etykiet', 799.99, 'PT-E550WVP - drukarka etykiet klasy przemysłowej, która doskonale sprawdzi się w pracy instalatorów, elektryków oraz monterów sieci teleinformatycznych, telekomunikacyjnych, systemów audio/wideo i bezpieczeństwa, potrzebujących niezawodnego narzędzia do profesjonalnego drukowania etykiet. Model ten charakteryzuje się wysoką prędkością druku, możliwością podłączenia do komputera oraz obsługą sieci bezprzewodowej.', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/2/pr_2019_2_22_10_4_23_418_02.jpg', 1),
	(3, 'Brother PT-P300BT Cube', 9, 'Drukarki etykiet', 219.00, 'PT-P300BT to pierwsza na rynku drukarka etykiet dedykowana smartfonom. Ten elegancki i kompaktowy model oferuje łączność Bluetooth, wysoką prędkość druku i pozwala na drukowanie na etykietach różnych rozmiarów, kolorów oraz typów.', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/2/pr_2019_2_22_10_13_8_627_02.jpg', 1),
	(4, 'Brother QL-700', 1, 'Drukarki etykiet', 339.00, 'QL-700 to lekka drukarka etykiet, która drukuje z prędkością 93 etykiet na minutę  (150 mm na sekundę). Urządzenie zostało przygotowane z myślą o wszechstronnym wykorzystaniu w biurach, sklepach, placówkach ochrony zdrowia, magazynach, hotelach, restauracjach oraz domach. Za pomocą QL-700 oznakowanie kopert, nośników CD/DVD, identyfikatorów, materiałów biurowych czy środków trwałych staje się proste, szybkie, wygodne i wyjątkowo trwałe. Urządzenie może wydrukować etykietę o minimalnej długości 12,7 mm oraz o maksymalnej długości 1 m. QL-700 również posiada automatyczną gilotynę.', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2017/11/pr_2017_11_24_12_4_12_135_00.jpg', 1),
	(5, 'Brother P-touch CUBE Plus', 2, 'Drukarki etykiet', 469.00, 'Stylowa, kompaktowa drukarka umożliwia druk etykiet na wiele sposobów. Zaprojektuj etykiety na swoim komputerze typu PC lub Mac. Dodaj obrazy, kody kreskowe, logo, ramki oraz symbole. Łączność Bluetooth pozwala na użycie aplikacji iPrint&Label lub P-touch Design&Print, aby stworzyć i wydrukować etykietę z urządzenia mobilnego. Wbudowana bateria litowo-jonowa z możliwością ładowania poprzez USB pozwala na wydruk etykiety na żądanie w dowolnym momencie.', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/2/pr_2019_2_22_11_5_57_455_02.jpg', 1),
	(6, 'HP Color Laser 150nw', 5, 'Drukarki laserowe', 629.00, 'Uniwersalna drukarka HP Color Laser 150nw pozwoli Ci zaoszczędzić czas i zredukować koszty zarówno w domu, jak i małej firmie. Wysoka ekonomia pracy i energooszczędność idą w parze jakością oraz szybkością wydruku. Co więcej, dzięki pojemnemu podajnikowi papieru jest zawsze gotowa do pracy, a wymiana i serwis eksploatacyjny są bajecznie proste.', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/7/pr_2019_7_9_10_23_33_167_05.jpg', 1),
	(7, 'Lexmark C3224dw', 10, 'Drukarki laserowe', 679.00, 'Utrzymuj produktywność w firmie na wysokim poziomie z drukarką Lexmark C3224dw. Minimalizuj koszty i zadbaj o wydajność pracy. Niezawodna, szybka i trwała drukarka Lexmark C3224dw zapewni Ci najwyższą jakość i szybkość wydruku w Twojej firmie. Dzięki inteligentnym sposobom pracy uzyskasz niski koszt całkowity, zwiększając efektywność wykonywanych zadań. Co więcej, rozwiązania mobilne zapewniają elastyczność i wygodę w zakresie druku ważnych dokumentów.', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/7/pr_2019_7_27_7_57_0_678_00.jpg', 1),
	(8, 'Brother HL-L8260CDW', 5, 'Drukarki laserowe', 1389.00, 'Tam, gdzie konieczna jest niezawodność działania i szybkość wydruku, drukarka Brother HL-L8260CDW sprawdzi się doskonale. Wszechstronne zastosowanie, ekonomia pracy oraz doskonały stosunek kosztów utrzymania do liczby wydrukowanych stron docenią zarówno biura, jak i duże korporacje. Dodatkowo szybka i łatwa wymiana elementów eksploatacyjnych sprawia, że drukarka jest zawsze gotowa do pracy.', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2017/7/pr_2017_7_5_9_59_51_732.jpg', 1),
	(9, 'HP Color LaserJet Pro M254nw', 3, 'Drukarki laserowe', 739.00, 'W biurach i korporacjach liczy się oferowana przez drukarkę HP Color LaserJet Pro M254nw duża szybkość wydruku, rewelacyjna niezawodność i ekonomia pracy. Urządzenie jest przy tym proste w obsłudze, co pozwoli zaoszczędzić czas i koszty eksploatacyjne. W efekcie drukarka jest zawsze gotowa na nowe wyzwania i setki kolejnych dokumentów do wydruku.', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2017/11/pr_2017_11_17_13_51_23_283_00.jpg', 1),
	(10, 'OKI C542dn', 8, 'Drukarki laserowe', 1199.00, 'Drukarkę OKI C542dn zaprojektowano z myślą o większej wydajności w biurach i korporacjach. Zoptymalizowana pod kątem komfortu i łatwej obsługi, zapewnia zwiększone bezpieczeństwo dokumentów oraz ekonomiczne drukowanie. OKI C542dn idealnie sprawdzi się w firmach, które oczekują wysokiej jakości druku. Usprawnij przepływ pracy, drukując szybko i wydajnie.', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/8/pr_2019_8_21_10_34_55_527_00.jpg', 1),
	(11, 'Gembird FlashForge Creator PRO', 2, 'Drukarki 3D', 4699.00, 'Drukarka 3D Flashforge Creator PRO to przyjazne i łatwe w użyciu urządzenie, które z idealną precyzją przenosi wirtualne projekty na fizyczny efekt końcowy. Wyposażona w wiele wszechstronnych funkcji, Creator Pro umożliwia tworzenie praktycznych i ciekawych obiektów dla potrzeb domowych, biznesowych lub edukacyjnych.', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/4/pr_2019_4_2_12_13_8_786_00.jpg', 1),
	(12, 'Zortrax Inventure', 5, 'Drukarki 3D', 8999.00, 'Zortrax Inventure pracuje w technologii LPD Plus. Dwie dysze naprzemiennie deponują materiał bazowy, z którego wykonany jest model, oraz podporowy, z którego wykonane są rozpuszczalne w wodzie struktury podporowe. W ten sposób Zortrax Inventure może drukować modele o geometrii niemożliwej do uzyskania na drukarkach FDM pracujących tylko z jednym materiałem.', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/3/pr_2019_3_14_15_30_2_213_00.jpg', 1),
	(13, 'Gembird FlashForge Inventor', 2, 'Drukarki 3D', 5549.00, 'W czasach, gdy druk 3D staje się coraz bardziej powszechny, wymaga się, aby proces ten był łatwy i przyjazny. Drukarka 3D FlashForge Inventor to intuicyjne i łatwe w użyciu urządzenie, które w prosty sposób zamieni Twój wirtualny projekt w namacalny obiekt. Wyposażona w wiele wszechstronnych funkcji, Inventor umożliwia tworzenie praktycznych i ciekawych obiektów dla potrzeb domowych, biznesowych lub edukacyjnych.', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/4/pr_2019_4_2_13_5_20_269_00.jpg', 1),
	(14, 'Gembird FlashForge Inventor 2', 1, 'Drukarki 3D', 3419.00, 'Przyjazna i łatwa w użyciu oraz wyposażona w wiele funkcji, drukarka 3D FlashForge Inventor 2 pozwala tworzyć praktyczne i ciekawe przedmioty na potrzeby domowe lub edukacyjne. Całkowicie zamknięta obudowa z czujnikiem otwarcia drzwi gwarantuje bezpieczeństwo i ochronę przed dziećmi. Wyposażona w innowacyjny system chłodzenia zapewnia jeszcze lepszą jakość wydruków. FlashForge Inventor 2 otwiera drogę do nieograniczonego świata druku 3D.', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/4/pr_2019_4_2_13_17_16_430_00.jpg', 1),
	(15, 'Gembird FlashForge Adventurer 3', 1, 'Drukarki 3D', 2299.00, 'Gembird FlashForge Adventurer 3 to przyjazna i łatwa w użyciu, innowacyjna drukarka 3D. Wyposażona w wiele wszechstronnych funkcji, Adventurer3 umożliwia tworzenie praktycznych i ciekawych obiektów dla potrzeb domowych lub edukacyjnych. Urządzenie charakteryzuje się całkowicie zamkniętą obudową, ma wszechstronną, zamykaną komorę drukującą, a także zawiera łatwe w użyciu oprogramowanie FlashPoint.', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/4/pr_2019_4_2_12_4_43_452_00.jpg', 1),
	(16, 'HP Sprocket 200', 5, 'Drukarki termosublimacyjne', 429.00, 'Spraw sobie domowy fotolab z drukarką do zdjęć HP Sprocket 200. Fotografia to Twoje hobby i sposób na relaks? A może po prostu chcesz kilka zdjęć z albumu powiesić na ścianie? Wystarczy odpowiedni papier i już po chwili cieszyć się będziesz wspaniałą fotografią o rewelacyjnej jakości. Wysoka rozdzielczość wydruku oraz naturalnie oddane kolory zachwycą każdego, komu zachcesz pochwalić się swoim dziełem.', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/6/pr_2019_6_3_8_57_28_958_00.jpg', 1),
	(17, 'Canon SELPHY CP1300', 10, 'Drukarki termosublimacyjne', 599.00, 'Z drukarką Canon SELPHY CP1300 bez trudu wzbogacisz swój domowy album o kolejne wspaniałe fotografie. Wystarczy odpowiedni papier fotograficzny i chwila cierpliwości, aby móc się pochwalić kolejnym wspaniałym kadrem. Co więcej, drukarka do zdjęć stanowi idealne uzupełnienie domowego fotolabu każdego hobbysty i grafika, zapewniając rewelacyjną jakość i kolory Twoich dzieł.', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2017/8/pr_2017_8_18_15_30_28_131_00.jpg', 1),
	(18, 'Canon Pixma iX6850', 5, 'Drukarki atramentowe', 689.00, 'Tam, gdzie konieczna jest niezawodność działania i szybkość wydruku, drukarka Canon Pixma iX6850 sprawdzi się doskonale. Wszechstronne zastosowanie, ekonomia pracy oraz doskonały stosunek kosztów utrzymania do liczby wydrukowanych stron docenią zarówno biura, jak i duże korporacje. Dodatkowo szybka i łatwa wymiana elementów eksploatacyjnych sprawia, że drukarka jest zawsze gotowa do pracy.', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2017/7/pr_2017_7_5_9_33_9_124.jpg', 1),
	(19, 'Epson EcoTank L120', 19, 'Drukarki atramentowe', 499.00, 'Epson EcoTank L120 to niedroga w eksploatacji drukarka, przeznaczona dla domu oraz małego biura. Urządzenie charakteryzuje się wysoką rozdzielczością wydruku, realistycznymi kolorami oraz wyraźnym, czarnym tekstem. EcoTank L120 jest intuicyjna i łatwa w użytkowaniu, do tego obsługuje wiele formatów nośników.', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/3/pr_2019_3_8_12_13_4_124_00.jpg', 1),
	(20, 'HP OfficeJet Pro 6230', 10, 'Drukarki atramentowe', 219.00, 'HP OfficeJet Pro 6230 to idealne rozwiązanie, gdy musisz szybko wydrukować dokumenty przed wyjściem albo prowadzisz małą firmę i potrzebujesz od czasu do czasu skorzystać z drukarki. Pojemny podajnik papieru, duża szczegółowość druku i oszczędność tuszu świetnie sprawdzą się w domu i małym biurze. Dodatkowo ekonomia pracy i łatwa wymiana elementów eksploatacyjnych zredukują koszty obsługi.', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2017/7/pr_2017_7_5_8_39_16_40.jpg', 1),
	(21, 'Epson WorkForce WF-7210DTW', 5, 'Drukarki atramentowe', 779.00, 'Zapewnij sobie komfort drukowania w domu i pracy dzięki drukarce Epson WorkForce WF-7210DTW. Ta kompaktowa drukarka umożliwia niedrogie drukowanie wysokiej jakości dokumentów i grafik. Jest także łatwa w obsłudze. Bezprzewodowy interfejs pozwala na wygodne drukowanie bezpośrednio ze smartfonów i tabletów. Epson WorkForce WF-7210DTW zmieści się niemal w każdym zakątku Twojego domowego biura, pozwalając Ci utrzymać ład i porządek.', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2017/10/pr_2017_10_11_10_52_46_85_00.jpg', 1),
	(22, 'Epson WF-100W', 2, 'Drukarki atramentowe', 999.00, 'Drukowanie w podróżach służbowych to nic trudnego, gdy masz przy sobie drukarkę Epson WF-100W. Niewielka waga, wymiary oraz wbudowana bateria sprawiają, że bez trudu zmieści się w bagażu podręcznym. Jest też bajecznie prosta w obsłudze. Wystarczy sparować ja z laptopem, smartfonem lub innymi urządzeniami mobilnymi za pośrednictwem W-Fi lub kabla USB i możesz przystąpić do druku.', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,pr_2016_3_11_12_45_56_430.jpg', 1),
	(23, 'Lexmark B2236dw', 9, 'Drukarki laserowe', 489.00, 'Lexmark B2236dw to niedroga w eksploatacji drukarka, przeznaczona dla domu oraz małego biura. Urządzenie charakteryzuje się wysoką rozdzielczością wydruku. Lexmark B2236dw jest intuicyjna i łatwa w użytkowaniu, do tego obsługuje wiele formatów nośników. Dzięki modułowi automatycznego druku dwustronnego zaoszczędzisz nie tylko papier, ale też cenny czas.', 'https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/4/pr_2019_4_10_10_19_55_691_00.jpg', 1),
	(24, 'Konica Minolta Bizhub C258', 2, 'Kserokopiarki', 6150.00, 'Nowe kolorowe rozwiązanie dla biznesu Konica Minolta Bizhub C258 otwiera całą nową gamę urządzeń. Bizhub C258 posiada teraz nowy, pojemnościowy 9-calowy kolorowy wyświetlacz z intuicyjnym interfejsem użytkownika, który pozwala klientom skonfigurować funkcje stosownie do jego potrzeb. Wiele nowych rozwiązań w dziedzinie ochrony środowiska takich jak trwały wkład światłoczuły, energooszczędne skanery LED.', 'http://kseromaks.pl/wp-content/uploads/2016/12/bizhub_C258_STAND_FALLBACK-300x285.jpg', 1);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;

-- Zrzut struktury tabela druk.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL,
  `order_date` date NOT NULL,
  `order_status` varchar(45) NOT NULL,
  `total` decimal(10,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`order_id`),
  UNIQUE KEY `order_id_UNIQUE` (`order_id`),
  KEY `fk_orders_users1_idx` (`customer_id`),
  CONSTRAINT `fk_orders_users1` FOREIGN KEY (`customer_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Zrzucanie danych dla tabeli druk.orders: ~17 rows (około)
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
REPLACE INTO `orders` (`order_id`, `customer_id`, `order_date`, `order_status`, `total`) VALUES
	(22, 1, '2020-01-28', 'Paid', 1309.00),
	(23, 1, '2020-01-28', 'Paid', 239.00),
	(24, 1, '2020-01-28', 'Waiting for payment', 489.00),
	(25, 1, '2020-01-28', 'Paid', 2399.00),
	(26, 1, '2020-01-28', 'Paid', 5569.00),
	(27, 1, '2020-01-28', 'Waiting for payment', 509.00),
	(28, 1, '2020-01-28', 'Paid', 1019.00),
	(29, 2, '2020-01-28', 'Paid', 234.00),
	(30, 2, '2020-01-28', 'Paid', 1389.00),
	(31, 2, '2020-01-28', 'Paid', 1309.00),
	(32, 2, '2020-01-28', 'Waiting for payment', 514.00),
	(33, 1, '2020-01-28', 'Paid', 899.99),
	(34, 1, '2020-01-28', 'Waiting for payment', 3036.00),
	(35, 1, '2020-01-28', 'Paid', 2108.99),
	(36, 1, '2020-01-28', 'Waiting for payment', 4197.98),
	(37, 1, '2020-01-28', 'Paid', 6455.00),
	(38, 2, '2020-01-28', 'Paid', 788.00);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;

-- Zrzut struktury tabela druk.payment
CREATE TABLE IF NOT EXISTS `payment` (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT,
  `payment_type` varchar(45) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `amount` decimal(10,2) NOT NULL DEFAULT '0.00',
  `seller_id` int(11) NOT NULL,
  `billing_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`payment_id`),
  UNIQUE KEY `payment_id_UNIQUE` (`payment_id`),
  KEY `fk_payment_customer_idx` (`customer_id`),
  KEY `fk_payment_seller_idx` (`seller_id`),
  KEY `fk_payment_billing_idx` (`billing_id`),
  KEY `fk_payment_order_idx` (`order_id`),
  CONSTRAINT `fk_payment_billing` FOREIGN KEY (`billing_id`) REFERENCES `billing_info` (`billing_id`),
  CONSTRAINT `fk_payment_customer` FOREIGN KEY (`customer_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `fk_payment_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `fk_payment_seller` FOREIGN KEY (`seller_id`) REFERENCES `item` (`seller_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Zrzucanie danych dla tabeli druk.payment: ~17 rows (około)
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
REPLACE INTO `payment` (`payment_id`, `payment_type`, `customer_id`, `amount`, `seller_id`, `billing_id`, `order_id`, `status`) VALUES
	(20, 'card', 1, 1309.00, 1, 1, 22, 'COMPLETED'),
	(21, 'card', 1, 239.00, 1, 1, 23, 'COMPLETED'),
	(22, 'card', 1, 489.00, 1, 1, 24, 'PENDING'),
	(23, 'paypall', 1, 2399.00, 1, 1, 25, 'COMPLETED'),
	(24, 'card', 1, 5569.00, 1, 1, 26, 'COMPLETED'),
	(25, 'card', 1, 509.00, 1, 1, 27, 'PENDING'),
	(26, 'card', 1, 1019.00, 1, 1, 28, 'COMPLETED'),
	(27, 'blik', 2, 234.00, 1, 1, 29, 'COMPLETED'),
	(28, 'blik', 2, 1389.00, 1, 1, 30, 'COMPLETED'),
	(29, 'card', 2, 1309.00, 1, 1, 31, 'COMPLETED'),
	(30, 'paypall', 2, 514.00, 1, 1, 32, 'PENDING'),
	(31, 'paypall', 1, 899.99, 1, 1, 33, 'COMPLETED'),
	(32, 'card', 1, 3036.00, 1, 1, 34, 'PENDING'),
	(33, 'card', 1, 2108.99, 1, 1, 35, 'COMPLETED'),
	(34, 'card', 1, 4197.98, 1, 1, 36, 'PENDING'),
	(35, 'card', 1, 6455.00, 1, 1, 37, 'COMPLETED'),
	(36, 'card', 2, 788.00, 1, 1, 38, 'COMPLETED');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;

-- Zrzut struktury tabela druk.review
CREATE TABLE IF NOT EXISTS `review` (
  `review_ID` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `description` varchar(150) NOT NULL,
  `rating` int(11) NOT NULL,
  PRIMARY KEY (`review_ID`),
  UNIQUE KEY `review_ID_UNIQUE` (`review_ID`),
  KEY `fk_customer_review_id_idx` (`customer_id`),
  KEY `fk_review_item_id_idx` (`item_id`),
  CONSTRAINT `fk_customer_review_id` FOREIGN KEY (`customer_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `fk_review_item_id` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Zrzucanie danych dla tabeli druk.review: ~3 rows (około)
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
REPLACE INTO `review` (`review_ID`, `customer_id`, `item_id`, `description`, `rating`) VALUES
	(6, 2, 1, 'Nie polecam, nie drukuje w 3d', 1),
	(8, 1, 1, 'Super drukarka polecam. 10/10', 5),
	(9, 1, 4, 'Działa', 4);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;

-- Zrzut struktury tabela druk.shipment
CREATE TABLE IF NOT EXISTS `shipment` (
  `shipment_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `tracking_number` varchar(45) NOT NULL,
  `returnAddress` varchar(45) NOT NULL,
  `carrier` varchar(45) NOT NULL,
  `charge` decimal(10,2) DEFAULT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`shipment_id`),
  UNIQUE KEY `shipment_id_UNIQUE` (`shipment_id`),
  KEY `fk_shipment_customer_idx` (`customer_id`),
  KEY `fk_shipment_orders1` (`order_id`),
  CONSTRAINT `fk_shipment_customer` FOREIGN KEY (`customer_id`) REFERENCES `orders` (`customer_id`),
  CONSTRAINT `fk_shipment_orders1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Zrzucanie danych dla tabeli druk.shipment: ~17 rows (około)
/*!40000 ALTER TABLE `shipment` DISABLE KEYS */;
REPLACE INTO `shipment` (`shipment_id`, `order_id`, `customer_id`, `tracking_number`, `returnAddress`, `carrier`, `charge`, `status`) VALUES
	(18, 22, 1, 'lsqhnvfqkx', 'górzyn', 'pigeon', 20.00, 'SEND'),
	(19, 23, 1, 'lsqhnvfqkx', 'górzyn', 'pigeon', 20.00, 'SEND'),
	(20, 24, 1, 'lsqhnvfqkx', 'górzyn', 'pigeon', 20.00, 'SEND'),
	(21, 25, 1, 'lsqhnvfqkx', 'górzyn', 'pigeon', 100.00, 'SEND'),
	(22, 26, 1, 'rajgkbosyl', 'dsad', 'dhl', 20.00, 'SEND'),
	(23, 27, 1, 'slhvvrlzec', 'dsa', 'dhl', 20.00, 'WAITING FOR PAYMENT'),
	(24, 28, 1, 'andsllzejv', 'dsad', 'dhl', 20.00, 'SEND'),
	(25, 29, 2, 'riwzmpzesb', 'Uz', 'inPost', 15.00, 'SEND'),
	(26, 30, 2, 'hoceleghoy', 'dasd', 'pigeon', 100.00, 'SEND'),
	(27, 31, 2, 'fqpfyolbqx', 'dsad', 'dhl', 20.00, 'SEND'),
	(28, 32, 2, 'quetgsyulk', 'dsad', 'inPost', 15.00, 'WAITING FOR PAYMENT'),
	(29, 33, 1, 'kwpauqlnxe', 'Zielona góra', 'pigeon', 100.00, 'SEND'),
	(30, 34, 1, 'yyyibjxczj', 'Gurenge', 'dhl', 20.00, 'WAITING FOR PAYMENT'),
	(31, 35, 1, 'bnchzsfhii', 'dsad', 'dhl', 20.00, 'SEND'),
	(32, 36, 1, 'jokbsiregp', 'dsad', 'dhl', 20.00, 'WAITING FOR PAYMENT'),
	(33, 37, 1, 'xlommwdade', 'dsad', 'dhl', 20.00, 'SEND'),
	(34, 38, 2, 'zemwyktmjz', 'dsad', 'pigeon', 100.00, 'SEND');
/*!40000 ALTER TABLE `shipment` ENABLE KEYS */;

-- Zrzut struktury tabela druk.users
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_email` varchar(80) NOT NULL,
  `user_password` varchar(30) NOT NULL,
  `user_firstname` varchar(45) NOT NULL,
  `user_middlename` varchar(45) NOT NULL,
  `user_lastname` varchar(45) NOT NULL,
  `account_type` varchar(20) NOT NULL DEFAULT 'customer',
  `username` varchar(45) NOT NULL,
  `isActive` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_ID_UNIQUE` (`user_id`),
  UNIQUE KEY `user_email_UNIQUE` (`user_email`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Zrzucanie danych dla tabeli druk.users: ~4 rows (około)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
REPLACE INTO `users` (`user_id`, `user_email`, `user_password`, `user_firstname`, `user_middlename`, `user_lastname`, `account_type`, `username`, `isActive`) VALUES
	(1, 'bartosz_musielak@hotmail.com', 'notadmin', 'Bartosz', '', 'Musielak', 'admin', 'adminus', 1),
	(2, 'fakemail@imfake.fake', 'ekaf', 'Fake', 'Definnitely', 'NotReal', 'customer', 'fake', 1),
	(3, 'test@test.pl', 'test', 'Test', '', 'Test', 'customer', 'Test', 1),
	(4, 'ddsa@dsad.pl', 'dsadsa', 'dsa', 'dsad', 'dsadsa', 'customer', 'dsad', 1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
