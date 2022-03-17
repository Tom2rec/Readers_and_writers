1. Algorytm - implementacja problemu "czytelników i pisarzy"
    Synchronizacji dostępu do jednego zasobu dwóch rodzajów procesów: dokonujących (pisarze) i niedokonujących (czytelnicy) w nim zmian.
    Jednoczesny dostęp do zasobu może uzyskać do 5 czytelników. Pisarz może otrzymać tylko dostęp wyłączny.
    Równocześnie z pisarzem dostępu do zasobu nie może otrzymać ani inny pisarz, ani czytelnik.
    Implementacja zakłada równoczesne wyeliminowanie możliwości zagłodzenia obu typów procesów.

    Ilość czytelników przechowywana jest w zmiennej readersInLibrary.
    Ilość pisarzy przechowywana jest w zmiennej writersInLibrary.
    Zmienne te umożliwiją podejmowanie wątkom odpowiednich decyzji.

    Wątek czytelnika oczekuje na wejście do czytelni do momentu w którym spełnione są powyższe warunki.
    Zmiana jego stanu na runnable jest możliwa poprzez wywołanie przez pisarza metody signalAll() na warunku dla czytelników.
    Pisarz wywołuje również metodę signal() na warunku dla pisarzy aby zapobiec sytuacji będzie blokada.
    Wątek pisarza oczekuje na wejście do czytelni do momentu w którym spełnione są powyższe warunki.
    Zmiana jego stanu na runnable jest możliwa poprzez wywołanie przez czytelnika metody signal() na warunku dla pisarzy,
    gdy liczba czytelników w bibliotece jest równa 0.

2. Projekt składa się z 4 modułów

    a) library - moduł biblioteki (zasób)
    
    b) reader - moduł czytelnika
    
    c) writer - moduł pisarza
    
    d) launcher - moduł zapewniający główną metodę uruchamijącą alogorytm

3. Sposób uruchomienia

    cd Readers_and_writers
    
    mvn package
    
    cd launcher/target
    
    java -jar tturek-zadanie2.jar

4. Komunikaty (n == id, gdzie n to dowolna liczba):

  a) czytelnik
  
    - INFO: READER ID: [n] wants to go into Library - gdy wątek czytelnika chce odczytać zasób
    
    - INFO: READER ID: [n] has entered the library - gdy wątek czytelnika wszedł do czytelni
    
    - INFO: READER ID: [n] has read book: XXX - gdy wątek czytelnika odczytał zasób, gdzie XXX - treść zasobu
    
    - INFO: READER ID: [n] has left the library - gdy wątek czytelniak opuścił zasób

  b) pisarz
  
    - INFO: WRITER ID: [n] wants to go into Library - gdy wątek pisarza chce zapisać zasób
    
    - INFO: WRITER ID: [n] has entered the library - gdy wątek pisarza wszedł do czytelni
    
    - INFO: WRITER ID: [n] has written the book - gdy wątek pisarza zapisał zasób,
    
    - INFO: WRITER ID: [n] has left the library - gdy wątek pisarza opuścił zasób
    
