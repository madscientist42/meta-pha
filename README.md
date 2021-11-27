PHA Technologies' OE metadata layer and distribution base for their projects (and anyone else that we consult for).

Supports the "Warrior" (2.7), Zeus (3.0), Dunfell (3.1), Gatesgarth (3.2), and Hardknott (3.3) releases of Yocto/OE as a core for build at this time.

We _typically_ support four of the latest Yocto versions at any one given time.  Honister (3.4) support is expected shortly.  Warrior and Zeuse official support will fall off at that time- mainly because there's a drift in metadata that precludes having more than abouut the last 4 or so as support except for a very short time.

This layer tries to provide good clean support for less supported functionalities of some of the more popular boards from suppliers like SinoVOIP, FriendlyElec, etc.
We have the take that these boards should have support for things similar in nature to the RasbperryPi Foundation's offerings or NVidia's.  We also provide a one-stop
shop for some of the unusual, but still quite useful for embedded stuff like Toxcore.  Enjoy!