# Club Registry System 

#### Οδηγίες εκτέλεσης API εφαρμογής:


Προαπαιτούμενα:
  - Docker
  - docker-compose 
  - Java 11
  - mvn


1ος τρόπος εκτέλεσης

```sh
mvn clean install -DskipTests
docker-compose  up -build -d
```

2ος τρόπος εκτέλεσης
```sh
docker-compose up —build mysql-db
mvn clean install -DskipTests
mvn spring-boot:run
```

### Οδηγίες εκτέλεσης frontend εφαρμογής

Για να εκτελέσουμε μεμονωμένα την εφαρμογή του Frontend μπορούμε να τρέξουμε την εντολή:
```sh
 docker-compose up  frontend –build,
```
εφόσον τρέχει το σύνολο τον containers της Rest API  εφαρμογής που απευθύνεται στο εσωτερικό σύστημα.

Αλλιώς αν θέλουμε να το τρέξουμε τοπικά χωρίς την χρήση των containers :
```sh
cd clubRegistryFrontend
python -m venv venv
source venv/bin/activate
pip install -r requirements.txt
cd clubRegistryFrontend
python manage.py runserver
```
