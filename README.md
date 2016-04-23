# ParkSolutions
Business Case: Innovative Technology (MT-BCI04-1)

De range_sensor.py bestand is om de data van een Ultrasonic Sensor (HC-SR04) op te halen met de RaspberryPi 2 model B. De sensor data wordt direct in een MySQL database geplaatst, zodat het uit gelezen kan worden door een webservice. De data die uitgelezen wordt is de afstand tussen de sensor en de bodem van de auto die geparkeerd staat. Als deze afstand kleiner is dan 30 cm is de parkeerplaats bezet. Als deze afstand groter is dan 30 cm is de parkeerplaats vrij en kan er geparkeerd worden. 

Sketch.ino is van de Arduino UNO die ik had gebruikt om de sensor te testen. 

De index.php bestand is de webservice bestand om een JSON object te maken voor de android app die het kan gebruiken om aan te tonen of er een parkeerplaats beschikbaar is of niet. Deze data lees ik uit met de android app die ik heb gebouwd. Het map ParkSolutions/ is de android project map. 

Link naar webservice: http://furkancetin.nl/data/

