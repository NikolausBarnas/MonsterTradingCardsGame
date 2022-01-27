# MonsterTradingCardsGame: Protocol

## Preface

MonsterTradingCardsGame or MTCG, which is how it will be referred to in
this Protocol, is a Project where players can get together, gather cards,
battle for glory and a high ranking on the official scoreboard. As of 
right now, only the backend and server logic are implemented successfully.


## Design

In terms of architecture, MTCG was originally designed to be as 
understandable, flexible and maintainable as possible, but as the
project progressed, certain structures had to be removed. As for
project/folder structure, the project is divided into several 
packages that each hold certain thematic ideologies.

Firstly, the server package holds all classes that are necessary and thematically
capable of belonging to the server logic. The Server class accepts 
incoming requests, whereas the Response class writes HTTP/1.1 responses
back to the OutputStream. The ClientHandler class handles each request
by calling methods in the HttpRequestParser to critically dissect each
request and make it accessible for future logic. 

Secondly, the repository package contains all the logic for managing and 
dealing with the aforementioned request. The RequestHandler class
is responsible for deciding which directive and method classes need
to be used in order to process the request accordingly. In the directive
package, each directive implements the DirectiveDAOHandler which handles
each directive by serializing/deserializing each request and returning
an appropriate response. Hence, the directive package takes on the 
responsibility of controlling and maintaining the business logic inside
the system. Furthermore, most repositories are encapsulated by their
respective packages (e.g.: card, credentials, deck, ...) and contain
all necessary methods to perform several specific CRUD operations to
access and modify the database. 


Thirdly, the hashing package contains the appropriate logic to encipher
or decipher passwords in order to make the safe to store in the database.

Lastly, the database package is responsible for establishing a database 
connection and is constructed to be able to be easily extended for further
requirements. 

## Failures

The initial design of a dynamic repository pattern was lacking in
foresight, and finally ended up being unsuccessful. Several DAO interfaces
as well as a general repository were removed, as a more specific
high level seemed to fit better with the task at hand. Moreover, 
as this is my first implementation of a REST-based API in Java,
I had to spent more time in figuring out HOW to implement all the 
necessary requirements than HOW BEST to implement them. As Refactoring
will definitely be necessary in the future, I am still of the opinion
that the implemented features are S.O.L.I.D. enough to be extended
quite easily. 

## Selected Solutions

In terms of solutions, there is no one right answer. A variability of 
multiple problem-solving approaches and a definite hierarchy were 
absolutely vital. The S.O.L.I.D. principles were my guidance throughout
this project, although my inexperience certainly played a part in
leading to some failures which were already mentioned in the
section above. One specific pattern will stand out within
the project, the singleton pattern, which is ideal for 
making use of not having to instantiate new objects if there is no
need to do so. 

## The reasoning behind Unit Tests

Unit tests should always be well constructed and useful,
which is why I decided to test even the simplest features
of the project. All unit tests are testing for one or 
several specific features/methods at different stages
of the program.

## Link to Github

https://github.com/NikolausBarnas/MonsterTradingCardsGame
