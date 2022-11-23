Name: Mirza Ana-Maria

Group: 321CA

# OOP Homework 1 - GwentStone



Description
-
The game GwentStone, played by two players, is simulated through a series of
commands given at input. Each player receives a deck of cards and a hero, and
executes a number of actions in his turn, until 'endPlayerTurn' command is
given. At his turn, the player can place a card on the table, use the ability of
one of the cards on the table, attack another card/hero, use an environment card
from his hand, or use his hero's ability, as long as he has enough mana to do 
it. At the end of each player's turn, his cards are unfreezed, if necessary.
When both players finished their turns, a new round starts, meaning both receive
a card from their decks into their hands and the mana, which starts from 1 and
increments every round until it reaches 10. The game ends whenever one of the 
hero's health becomes 0. Since the AI can specify input for multiple games, each
player remembers how many games were played and how many of them were won by
him.

Implementation
-
The game is implemented in the class 'Program' which uses a Singleton Pattern to
ensure the instantiation of a single object throughout the multiple game
simulations. The program uses two repetitive structures of type 'for' to
implement the multiple games and commands given by the AI. 

### Storing of input

In order to store the input information about the players, the class 'Upload'
defines methods for the initialization of the class 'Player', which stores the
players' decks, hands, heroes, rounds, mana, total games played, and games won.
The first Player is used to keep track of the 'playerIdx', which states which
player's turn is currently, and the 'round', which keeps track of the rounds
so as to beggin the new turn correctly.

The program also uses another class 'Table' to store information about the
game table, using an array of 'Card' arrays, with first two arrays being the
two rows used by the second player, and the last two for the first player.
Each row has space for exactly 5 cards, and uses certain rules by which only
certain minion cards can be placed on it.

### Flow of the game

Each command has associated a class that implements the interface 'Command' and
is instantiated with the help of the class 'Upload' in the main program, based by
its name. After instantiation, the method 'action', overriden by each specific
command class, is called, and the command's action is executed. The 'action' method
includes dealing with error scenarios, saving the corresponding error messages,
providing information about the players or table content, and calling a card
ability method if necessary. This implementation allows for the addition of new
commands without having to change any code in the main program.

### Usage of cards

The minion cards, the environment cards, and the hero cards, extended from the
parent class 'Card', define the specific fields contained by the cards and the
abilities of each specific card by overriding the parent method 'ability' with
the specific actions of the card in order to ensure unity and incapsulation, as
well as extensibility of the code if future cards will be added.

Difficulties Encountered
-
One of the difficulties encountered was writing in Json an object. The difficulty
arose when the one of the card's information saved in the output ArrayNode
changed, as the card's new specifications were overwritten everywhere. Instead
of using deep copy, as others suggested, I found an easier solution by using
another method of the ObjectNode, called 'set', with 'valueToTree' used on the
object to be saved as output. This method saved much time as it avoided
copying manually everything from the object or by deep copy-ing every object.

Feedback
-
Overall, the homework was decent: not too impossible, and pretty well
documented. As for the amount, it felt a little overwhelming taking into
account the other homeworks, but it was manageable with the deadline extension.
Also, regarding the Json, the materials provided were very helpful as we had
no notion of it beforehand.
