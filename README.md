
# Wordle

## Description
This project consist of the famous game called Wordle where
a random word is generated and the user has X tries to guess
the word.

Based on the character position of the entered word it will display
a colored background on that character. Displaying:
- A gray background if the character doesn't exist in the word.
- A yellow background if the character exists in the word but isn't in the right position.
- A green background if the character exists and is in the right position.

It had been developed in IntelliJ IDEA with the Kotlin language.

---

## Author

[Ivan Martinez Cañero](https://gitlab.com/ivan.martinez.7e6)

---

## Installing

Use git to clone this repository into your computer.

> git clone https://gitlab.com/ivan.martinez.7e6/wordle


---

## Running the project

Open the project with IntelliJ IDEA and run the file.

### Formatting the Output

In this project we will make use of variables to
color the foreground and background of each character
when needed.

#### Usage

* Write the name or names of the constants you want
  to use before the next word or character
* Use the variable `reset` after the desired word or character to end the formatting

---

## How To Play

- The user has 6 tries to guess the right word
- Each word has 5 characters
- If the background of the character of the guessed word
  changes to **gray** it means it doesn't exist in the word.
- If changes to **yellow** it means it exists in
  the word but is not in the correct position.
- If  changes to **green** it means it is in the
  correct position.

---
### The Menu
The menu will be the first thing the user sees.
- Will greet the user and will offer them a list of options they can choose:
- - `PLAY` Will start the game
- - `HELP` Will explain the rules
- - `HISTORY` Will call the menu for filtering the history of games than were saved
- - `RANKING` Will print the ranking of the users who saved their games
- - `EXIT` Will end the program

![alt text](img/wordleMenu.png "Main menu")



### The Game
- The user can choose to play in Catalan or in English.
- First will ask the user to type their username.
- Then will take a random word from the `wordPool` of the choosen language and will ask the user for a word.
- If the word doesn't have a length of 5 chars or has chars that aren't alphabetical, it will throw a warning and will ask
  for a word again.

##### When the word is accepted and isn't the right guess

- It will scan every char at the `userGuess` word and compare
  the position with the same position of the char of the random
  word.
- At the same time will be colored based on the position and added
  to a variable called `history`
- Then it will be added to a list called `historyList` and with each iteration will print the content
  of the list creating the history of the game
- The program will also print the number of the current round.
- Last it will rest 1 try and will communicate to the user how many tries has.

![alt text](img/wordleColors.png "Colored characters") 


##### When the word is the right word

Will print a congratulation message and will show the tries used.

![alt text](img/wordleRounds.png "History")
![alt text](img/wordleWin.png "Win message")

##### When the user spends all their tries

Will end the program and the game with a lose.

![alt text](img/wordleRoundsLose.png "History")
![alt text](img/wordleLose.png "Lose message")


#### After the game

If the tries reach 0 or the user guesses the word it will ask if the user wants to 
save the game to the database.

![alt text](img/wordleSave.png "Save")

Then will ask to continue playing,
return to the menu or stop playing.

![alt text](img/finalMenu.png "Menu")

### The History
When the user types `HISTORY` in the main menu, it will
show options to filter the database.

And will filter by the instruction

![alt text](img/wordleFiltering.png "Filter by user")

### The Ranking
When the user types `RANKING` in the main menu, it will
show the ranking.

Will only show the users who won their games.

![alt text](img/wordleRanking.png "Ranking")

---

### License

This project is licensed under the [GNU Affero General Public License v3.0](https://choosealicense.com/licenses/agpl-3.0/) 

See the [LICENSE.md](LICENSE) file for details