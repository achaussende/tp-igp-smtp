#Message Module

This module contains class for generating SMTP messages.
The list of available messages is below.

##List of available messages :

Messages with information to parse are defined in specific object. They are marked with a "O".
You can test them with their static function matches(), parse and build them with constructor.

Messages unmarked with O are simple messages, they are defined in SMTPMessage Enum.
You can test them with 

```java
SMTPMessage.matches(SMTPMessage smtpMessage, String message)
```

###Client Messages:
* EHLO <client> O
* QUIT (Not tested)
* MAIL FROM:<adresse> O

###Server Messages:
* 220 <domain> SMTP Server Ready  O
* 221 <domain> Service closing transmission channel  O
* 250 OK 
* 250 <domain> O
* 354 Start mail input 
* 503 Bad sequence of commands 
* 550 No such user 

